package net.karen.mccoursemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

public class ModSaplingBlock extends SaplingBlock {
    private final Supplier<Block> blockToSurviveOn;

    public ModSaplingBlock(TreeGrower treeGrower,
                           Properties properties, Supplier<Block> block) {
        super(treeGrower, properties);
        this.blockToSurviveOn = block;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state,
                                 @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return blockToSurviveOn.get() == state.getBlock();
    }
}