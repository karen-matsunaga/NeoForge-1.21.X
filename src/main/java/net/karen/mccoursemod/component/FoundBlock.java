package net.karen.mccoursemod.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Objects;

public record FoundBlock(BlockState block, BlockPos position) {
    public static final Codec<FoundBlock> CODEC =
           RecordCodecBuilder.create(instance ->
                     instance.group(BlockState.CODEC.fieldOf("block").forGetter(FoundBlock::block),
                                    BlockPos.CODEC.fieldOf("position").forGetter(FoundBlock::position))
                             .apply(instance, FoundBlock::new));

    // CUSTOM METHOD - METAL DETECTOR found an ore
    public String getOutputString() {
        return "Valuable Found: " + block.getBlock().getName().getString() +
               " at [X: " + position.getX() + ", Y: " + position.getY() + ", Z: " + position.getZ() + "]";
    }

    // CUSTOM METHOD - Block HASH is equals with Position HASH
    @Override
    public int hashCode() {
        return Objects.hash(this.block, this.position);
    }

    // CUSTOM METHOD - Block detected is equals with Position founded
    @Override
    public boolean equals(Object object) {
        if (object == this) { return true; }
        else {
            return object instanceof FoundBlock(BlockState foundedBlock, BlockPos foundedPosition) &&
                   this.block == foundedBlock && this.position == foundedPosition;
        }
    }
}