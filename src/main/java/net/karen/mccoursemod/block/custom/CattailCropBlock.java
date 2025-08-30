package net.karen.mccoursemod.block.custom;

import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TriState;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.NotNull;

public class CattailCropBlock extends CropBlock {
    public static final int FIRST_STAGE_MAX_AGE = 7;
    public static final int SECOND_STAGE_MAX_AGE = 1;
    private static final VoxelShape[] SHAPE_BY_AGE =
            new VoxelShape[]{ Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
                              Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                              Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
                              Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                              Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
                              Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
                              Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
                              Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                              Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 8);

    public CattailCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level,
                                        @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }

    @Override
    public void randomTick(@NotNull BlockState state, ServerLevel level,
                           @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) { return; }
        if (level.getRawBrightness(pos, 0) >= 9) {
            int currentAge = this.getAge(state);
            if (currentAge < this.getMaxAge()) {
                float f = getGrowthSpeed(state, level, pos);
                if (CommonHooks.canCropGrow(level, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
                    if (currentAge == FIRST_STAGE_MAX_AGE) {
                        if (level.getBlockState(pos.above(1)).is(Blocks.AIR)) {
                            level.setBlock(pos.above(1), this.getStateForAge(currentAge + 1), 2);
                        }
                    }
                    else { level.setBlock(pos, this.getStateForAge(currentAge + 1), 2); }
                    CommonHooks.fireCropGrowPost(level, pos, state);
                }
            }
        }
    }

    @Override
    public void growCrops(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
        int nextAge = this.getAge(state) + this.getBonemealAgeIncrease(level);
        int maxAge = this.getMaxAge();
        if (nextAge > maxAge) { nextAge = maxAge; }
        if (this.getAge(state) == FIRST_STAGE_MAX_AGE && level.getBlockState(pos.above(1)).is(Blocks.AIR)) {
            level.setBlock(pos.above(1), this.getStateForAge(nextAge), 2);
        }
        else { level.setBlock(pos, this.getStateForAge(nextAge - SECOND_STAGE_MAX_AGE), 2); }
    }

    @Override
    public @NotNull TriState canSustainPlant(@NotNull BlockState state, @NotNull BlockGetter level,
                                             @NotNull BlockPos soilPosition, @NotNull Direction facing,
                                             @NotNull BlockState plant) {
        return TriState.DEFAULT;
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        return super.canSurvive(state, level, pos) ||
               (level.getBlockState(pos.below(1)).is(this) &&
                level.getBlockState(pos.below(1)).getValue(AGE) == 7);
    }

    @Override
    public int getMaxAge() { return FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE; }

    @Override
    protected @NotNull ItemLike getBaseSeedId() { return ModItems.CATTAIL_SEEDS.get(); }

    @Override
    public @NotNull IntegerProperty getAgeProperty() { return AGE; }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(AGE); }
}