package net.karen.mccoursemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class DegradableRubyBlock extends Block implements GemDegradable {
    private final GemDegradationLevel degradationLevel;

    public DegradableRubyBlock(GemDegradationLevel degradationLevel,
                               Properties properties) {
        super(properties); this.degradationLevel = degradationLevel;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level,
                           @NotNull BlockPos pos, @NotNull RandomSource random) {
        this.changeOverTime(state, level, pos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return GemDegradable.getNext(state.getBlock()).isPresent();
    }

    @Override
    public @NotNull GemDegradationLevel getAge() { return degradationLevel; }
}