package net.karen.mccoursemod.block.custom;

import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class DiceBlock extends Block {
    public static EnumProperty<Direction> FACING = BlockStateProperties.FACING;

    public DiceBlock(Properties properties) { super(properties); }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext placeContext) {
        return this.defaultBlockState().setValue(FACING, getRandomDirection());
    }

    public BlockState getRandomBlockState() {
        return this.defaultBlockState().setValue(FACING, getRandomDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private Direction getRandomDirection() {
        Direction[] dirs = new Direction[] { Direction.UP, Direction.NORTH, Direction.EAST,
                                             Direction.SOUTH, Direction.WEST, Direction.DOWN };
        return dirs[RandomSource.create().nextIntBetweenInclusive(0, dirs.length-1)];
    }
}