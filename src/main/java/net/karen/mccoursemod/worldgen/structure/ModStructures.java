package net.karen.mccoursemod.worldgen.structure;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import java.util.Map;

public class ModStructures {
    // Register all custom structures
    public static final ResourceKey<Structure> KAUPEN_HOUSE = create("kaupen_house");
    public static final ResourceKey<Structure> STORAGE_PLATFORM = create("storage_platform");

    // CUSTOM METHOD - Register all custom structures
    private static ResourceKey<Structure> create(String name) {
        return ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

    // DEFAULT METHOD - Data Generation of structure for each structure and jigsaw structure (JSON file)
    public static void bootstrap(BootstrapContext<Structure> context) {
        // BIOME
        HolderGetter<Biome> biome = context.lookup(Registries.BIOME);
        // TEMPLATE POOL
        HolderGetter<StructureTemplatePool> templatePool = context.lookup(Registries.TEMPLATE_POOL);

        // KAUPEN HOUSE - CUSTOM STRUCTURE
        context.register(KAUPEN_HOUSE, new JigsawStructure(
                         new Structure.StructureSettings.Builder(biome.getOrThrow(ModTags.Biomes.HAS_KAUPEN_HOUSE))
                                      .spawnOverrides(Map.of(MobCategory.CREATURE,
                                                      new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE,
                                                      WeightedList.of(new MobSpawnSettings.SpawnerData(EntityType.PARROT,
                                                                                                       1,
                                                                                                       4)))))
                                      .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                                      .terrainAdapation(TerrainAdjustment.BEARD_THIN).build(),
                                                      templatePool.getOrThrow(ModPools.KAUPEN_HOUSE),
                                                      2,
                                                      ConstantHeight.of(VerticalAnchor.absolute(0)),
                                                      false,
                                                      Heightmap.Types.WORLD_SURFACE_WG));

        // STORAGE PLATFORM - CUSTOM JIGSAW STRUCTURE
        context.register(STORAGE_PLATFORM, new JigsawStructure(
                         new Structure.StructureSettings.Builder(biome.getOrThrow(ModTags.Biomes.HAS_STORAGE_PLATFORM))
                                      .spawnOverrides(Map.of(MobCategory.CREATURE,
                                                      new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE,
                                                      WeightedList.of(new MobSpawnSettings.SpawnerData(EntityType.PARROT,
                                                                                                       1,
                                                                                                       4)))))
                                      .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                                      .terrainAdapation(TerrainAdjustment.BEARD_BOX).build(),
                                                      templatePool.getOrThrow(ModPools.STORAGE_PLATFORM_START),
                                                      2,
                                                      ConstantHeight.of(VerticalAnchor.absolute(0)),
                                                      false,
                                                      Heightmap.Types.WORLD_SURFACE_WG));
    }
}