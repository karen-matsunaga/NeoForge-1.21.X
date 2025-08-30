package net.karen.mccoursemod.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.DataMapHooks;
import org.jetbrains.annotations.NotNull;
import java.util.Optional;

public class ModWaxingItem extends Item {
    public ModWaxingItem(Properties properties) {
        super(properties);
    }

    // DEFAULT METHOD - Depends on Ruby's stage NORMAL block transform onto other Ruby stage WAXED blocK
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        Optional<InteractionResult.Success> waxedBlock = getWaxed(blockstate).map((state) -> {
            Player player = context.getPlayer();
            ItemStack itemstack = context.getItemInHand();
            if (player instanceof ServerPlayer serverplayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverplayer, blockpos, itemstack);
            }
            itemstack.shrink(1);
            level.setBlock(blockpos, state, 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, state));
            level.levelEvent(player, 3003, blockpos, 0);
            return InteractionResult.SUCCESS;
        });
        if (waxedBlock.isPresent()) { return InteractionResult.SUCCESS; }
        else { return InteractionResult.PASS; }
    }

    public static Optional<BlockState> getWaxed(BlockState state) {
        return Optional.ofNullable(DataMapHooks.getBlockWaxed(state.getBlock()))
                       .map((block) -> block.withPropertiesOf(state));
    }
}