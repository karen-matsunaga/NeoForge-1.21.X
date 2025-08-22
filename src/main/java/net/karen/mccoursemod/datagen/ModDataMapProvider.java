package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
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
            .add(ModItems.GROWTH.getId(), new Compostable(0.5F), false);
    }
}