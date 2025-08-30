package net.karen.mccoursemod.block.custom;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import java.util.Optional;
import java.util.function.Supplier;

public interface GemDegradable
       extends ChangeOverTimeBlock<GemDegradable.GemDegradationLevel> {
    // Depends on Ruby's stage block transform onto other Ruby stage block
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK =
            Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
                     .put(ModBlocks.RUBY_BLOCK.get(), ModBlocks.RUBY_BLOCK_1.get())
                     .put(ModBlocks.RUBY_BLOCK_1.get(), ModBlocks.RUBY_BLOCK_2.get())
                     .put(ModBlocks.RUBY_BLOCK_2.get(), ModBlocks.RUBY_BLOCK_3.get()).build());

    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK =
            Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    static Optional<Block> getPrevious(Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }

    static Block getFirst(Block getBlock) {
        Block block = getBlock;
        for (Block block1 = PREVIOUS_BY_BLOCK.get().get(getBlock);
             block1 != null;
             block1 = PREVIOUS_BY_BLOCK.get().get(block1)) {
             block = block1;
        }
        return block;
    }

    static Optional<BlockState> getPrevious(BlockState state) {
        return getPrevious(state.getBlock()).map((block) -> block.withPropertiesOf(state));
    }

    static Optional<Block> getNext(Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
    }

    static BlockState getFirst(BlockState state) {
        return getFirst(state.getBlock()).withPropertiesOf(state);
    }

    default @NotNull Optional<BlockState> getNext(BlockState state) {
        return getNext(state.getBlock()).map((block) -> block.withPropertiesOf(state));
    }

    default float getChanceModifier() {
        return this.getAge() == GemDegradationLevel.UNAFFECTED ? 0.75F : 1.0F;
    }

    enum GemDegradationLevel {
        UNAFFECTED, EXPOSED, WEATHERED, DEGRADED;
    }
}