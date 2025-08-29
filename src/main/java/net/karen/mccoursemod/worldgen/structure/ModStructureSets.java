package net.karen.mccoursemod.worldgen.structure;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public interface ModStructureSets {
    // Register all custom structure sets
    ResourceKey<StructureSet> KAUPEN_HOUSE = create("kaupen_house");
    ResourceKey<StructureSet> STORAGE_PLATFORM = create("storage_platform");

    // CUSTOM METHOD - Register all custom structure sets
    static ResourceKey<StructureSet> create(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

    // DEFAULT METHOD - Data Generation of structure sets for each structure and jigsaw structure (JSON file)
    static void bootstrap(BootstrapContext<StructureSet> context) {
        // STRUCTURE
        HolderGetter<Structure> structure = context.lookup(Registries.STRUCTURE);

        // KAUPEN HOUSE - CUSTOM STRUCTURE SET
        context.register(KAUPEN_HOUSE, new StructureSet(
                         structure.getOrThrow(ModStructures.KAUPEN_HOUSE),
                         new RandomSpreadStructurePlacement(20, 6, RandomSpreadType.LINEAR, 446981001)));

        // STORAGE PLATFORM - CUSTOM JIGSAW STRUCTURE SET
        context.register(STORAGE_PLATFORM, new StructureSet(
                         structure.getOrThrow(ModStructures.STORAGE_PLATFORM),
                         new RandomSpreadStructurePlacement(18, 9, RandomSpreadType.LINEAR, 750545120)));
    }
}