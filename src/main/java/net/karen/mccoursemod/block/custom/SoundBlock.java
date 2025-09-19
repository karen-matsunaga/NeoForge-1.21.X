package net.karen.mccoursemod.block.custom;

import net.karen.mccoursemod.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class SoundBlock extends Block {
    public SoundBlock(Properties properties) { super(properties); }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                                        @NotNull Player player, @NotNull BlockHitResult hitResult) {
        // Sounds when player clicked in block
        if (!level.isClientSide() && player.getUsedItemHand() == InteractionHand.MAIN_HAND) {
            if (player.isCrouching()) { // If crouching on block
                Utils.blockSound(level, false, null, pos, SoundEvents.NOTE_BLOCK_BANJO.value());
                return InteractionResult.SUCCESS;
            }
            else { // If not crouching on block
                Utils.blockSound(level, false, null, pos, SoundEvents.NOTE_BLOCK_COW_BELL.value());
                return InteractionResult.CONSUME;
            }
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    // Entity in block
    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos pos,
                       @NotNull BlockState state, @NotNull Entity entity) {
        Utils.blockSound(level, true, entity, pos, SoundEvents.NOTE_BLOCK_BIT.value());
        super.stepOn(level, pos, state, entity);
    }
}