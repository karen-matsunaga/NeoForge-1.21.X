package net.karen.mccoursemod.item.custom;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.DataMapHooks;
import org.jetbrains.annotations.NotNull;
import java.util.Optional;
import java.util.function.Supplier;

public class ModWaxingItem extends Item {
    // Depends on Ruby's stage block transform onto other Ruby stage block
    public static final Supplier<BiMap<Block, Block>> WAXABLES =
           Suppliers.memoize(() ->
           (BiMap) ImmutableBiMap.builder().put(ModBlocks.RUBY_BLOCK.get(), ModBlocks.WAXED_RUBY_BLOCK.get())
                                           .put(ModBlocks.RUBY_BLOCK_1.get(), ModBlocks.WAXED_RUBY_BLOCK_1.get())
                                           .put(ModBlocks.RUBY_BLOCK_2.get(), ModBlocks.WAXED_RUBY_BLOCK_2.get())
                                           .put(ModBlocks.RUBY_BLOCK_3.get(), ModBlocks.WAXED_RUBY_BLOCK_3.get())
                                           .build());

    public static final Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK =
           Suppliers.memoize(() -> ((BiMap)WAXABLES.get()).inverse());

    public ModWaxingItem(Properties properties) {
        super(properties);
    }

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