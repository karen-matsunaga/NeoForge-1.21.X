package net.karen.mccoursemod.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.karen.mccoursemod.util.ChatUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Objects;

public record FoundBlock(BlockState block, BlockPos position,
                         ResourceKey<Level> dimension) {
    public static final Codec<FoundBlock> CODEC =
           RecordCodecBuilder.create(instance ->
                                    // FOUND BLOCK
                     instance.group(BlockState.CODEC.fieldOf("block").forGetter(FoundBlock::block),
                                    // BLOCK POSITION
                                    BlockPos.CODEC.fieldOf("position").forGetter(FoundBlock::position),
                                    // DIMENSION
                                    ResourceKey.codec(Registries.DIMENSION).fieldOf("dimension")
                                                                           .forGetter(FoundBlock::dimension))
                             .apply(instance, FoundBlock::new));

    // CUSTOM METHOD - METAL DETECTOR found an ore
    public String getOutputString() {
        String dimensionPath = dimension.location().getPath();
        return "Valuable Found: " + block.getBlock().getName().getString() +
               " at [X: " + position.getX() + ", Y: " + position.getY() + ", Z: " + position.getZ() + "] " +
               ChatUtils.dimensionName(dimensionPath) + " dimension!";
    }

    // CUSTOM METHOD - Block HASH is equals with Position HASH
    @Override
    public int hashCode() {
        return Objects.hash(this.block, this.position, this.dimension);
    }

    // CUSTOM METHOD - Block detected is equals with Position founded
    @Override
    public boolean equals(Object object) {
        if (object == this) { return true; }
        else {
            return object instanceof FoundBlock(BlockState foundedBlock, BlockPos foundedPosition,
                                                ResourceKey<Level> foundedDimension) &&
                   this.block == foundedBlock && this.position == foundedPosition && this.dimension == foundedDimension;
        }
    }
}