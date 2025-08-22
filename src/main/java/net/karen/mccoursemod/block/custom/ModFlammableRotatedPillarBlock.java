package net.karen.mccoursemod.block.custom;

import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level,
                               @NotNull BlockPos pos, @NotNull Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level,
                               @NotNull BlockPos pos, @NotNull Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level,
                                  @NotNull BlockPos pos, @NotNull Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(@NotNull BlockState state, UseOnContext context,
                                                     @NotNull ItemAbility itemAbility, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(ModBlocks.BLOODWOOD_LOG)) {
                return ModBlocks.STRIPPED_BLOODWOOD_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.BLOODWOOD_WOOD)) {
                return ModBlocks.STRIPPED_BLOODWOOD_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.WALNUT_LOG)) {
                return ModBlocks.STRIPPED_WALNUT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.WALNUT_WOOD)) {
                return ModBlocks.STRIPPED_WALNUT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}