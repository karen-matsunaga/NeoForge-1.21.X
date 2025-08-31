package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.*;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput,
                                 CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider provider) {
        // ** CUSTOM furnace fuels items **
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
            .add(ModItems.STARLIGHT_ASHES.getId(), new FurnaceFuel(1200), false)
            .add(ModItems.FROSTFIRE_ICE.getId(), new FurnaceFuel(2400), false)
            .add(ModItems.PEAT_BRICK.getId(), new FurnaceFuel(400), false)
            .add(ModItems.COFFEE, new FurnaceFuel(1200), false);

        // ** CUSTOM compostables items **
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
            .add(ModItems.RADISH_SEEDS.getId(), new Compostable(0.25F), false)
            .add(ModItems.RADISH.getId(), new Compostable(0.45F), false)
            .add(ModItems.KOHLRABI_SEEDS.getId(), new Compostable(0.25F), false)
            .add(ModItems.KOHLRABI.getId(), new Compostable(0.45F), false)
            .add(ModItems.GROWTH.getId(), new Compostable(0.5F), false)
            .add(ModItems.COFFEE.getId(), new Compostable(0.65F), false)
            .add(ModBlocks.WALNUT_LEAVES.getId(), new Compostable(0.3F), false)
            .add(ModBlocks.BLOODWOOD_LEAVES.getId(), new Compostable(0.3F), false)
            .add(ModBlocks.COLORED_LEAVES.getId(), new Compostable(0.3F), false)
            .add(ModBlocks.WALNUT_SAPLING.getId(), new Compostable(0.3F), false)
            .add(ModBlocks.BLOODWOOD_SAPLING.getId(), new Compostable(0.3F), false)
            .add(ModBlocks.SNAPDRAGON.getId(), new Compostable(0.65F), false);

        // ** CUSTOM waxable blocks **
        this.builder(NeoForgeDataMaps.WAXABLES)
            .add(ModBlocks.RUBY_BLOCK.getId(), new Waxable(ModBlocks.WAXED_RUBY_BLOCK.get()), false)
            .add(ModBlocks.RUBY_BLOCK_1.getId(), new Waxable(ModBlocks.WAXED_RUBY_BLOCK_1.get()), false)
            .add(ModBlocks.RUBY_BLOCK_2.getId(), new Waxable(ModBlocks.WAXED_RUBY_BLOCK_2.get()), false)
            .add(ModBlocks.RUBY_BLOCK_3.getId(), new Waxable(ModBlocks.WAXED_RUBY_BLOCK_3.get()), false);

        // ** CUSTOM strippable blocks **
        this.builder(NeoForgeDataMaps.STRIPPABLES)
            .add(ModBlocks.WALNUT_WOOD.getId(), new Strippable(ModBlocks.STRIPPED_WALNUT_WOOD.get()), false)
            .add(ModBlocks.BLOODWOOD_WOOD.getId(), new Strippable(ModBlocks.STRIPPED_BLOODWOOD_WOOD.get()), false)
            .add(ModBlocks.WALNUT_LOG.getId(), new Strippable(ModBlocks.STRIPPED_WALNUT_LOG.get()), false)
            .add(ModBlocks.BLOODWOOD_LOG.getId(), new Strippable(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()), false);
    }
}