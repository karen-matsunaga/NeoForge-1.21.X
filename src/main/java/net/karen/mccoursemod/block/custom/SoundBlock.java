package net.karen.mccoursemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
                level.playSound(null, pos, SoundEvents.NOTE_BLOCK_BANJO.value(),
                                SoundSource.BLOCKS, 1F, 1F);
                return InteractionResult.SUCCESS;
            }
            else { // If not crouching on block
                level.playSound(null, pos, SoundEvents.NOTE_BLOCK_COW_BELL.value(),
                                SoundSource.BLOCKS, 1F, 1F);
                return InteractionResult.CONSUME;
            }
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    // Entity in block
    @Override
    public void stepOn(Level level, @NotNull BlockPos pos,
                       @NotNull BlockState state, @NotNull Entity entity) {
        level.playSound(entity, pos, SoundEvents.NOTE_BLOCK_BIT.value(),
                        SoundSource.BLOCKS, 1F, 1F);
        super.stepOn(level, pos, state, entity);
    }
}