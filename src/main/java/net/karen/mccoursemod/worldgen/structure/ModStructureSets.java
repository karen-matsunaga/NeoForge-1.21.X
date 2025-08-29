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
    ResourceKey<StructureSet> KAUPEN_HOUSE = create("kaupen_house");

    // CUSTOM METHOD - Register all custom structure sets
    static ResourceKey<StructureSet> create(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

    static void bootstrap(BootstrapContext<StructureSet> context) {
        // STRUCTURE
        HolderGetter<Structure> structure = context.lookup(Registries.STRUCTURE);
        // KAUPEN HOUSE - CUSTOM STRUCTURE SET
        context.register(KAUPEN_HOUSE, new StructureSet(
                         structure.getOrThrow(ModStructures.KAUPEN_HOUSE),
                         new RandomSpreadStructurePlacement(20, 6, RandomSpreadType.LINEAR, 446981001)));
    }
}