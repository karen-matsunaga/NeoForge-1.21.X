package net.karen.mccoursemod.worldgen.biome;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.worldgen.ModPlacedFeatures;
import net.karen.mccoursemod.worldgen.biome.region.OverworldRegion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import terrablender.api.Regions;

public class ModBiomes {
    // Register all custom BIOMES
    public static final ResourceKey<Biome> TEST_BIOME = register("test_biome");
    public static final ResourceKey<Biome> TEST_BIOME_2 = register("test_biome_2");

    // CUSTOM METHOD - Register all custom biomes on CLIENT EVENT
    public static void registerBiomes() {
        Regions.register(new OverworldRegion(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                                   "mccoursemod_overworld"),
                         5));
    }

    // CUSTOM METHOD - Register all custom biomes (DATA GENERATION) -> JSON file
    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(TEST_BIOME, testBiome(context));
        context.register(TEST_BIOME_2, testBiome2(context));
    }

    // CUSTOM METHOD - Register biome default features of OVERWORLD generation
    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    // CUSTOM METHOD - Register TEST BIOME
    public static Biome testBiome(BootstrapContext<Biome> context) {
        // ANIMALS and MONSTERS spawned
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        // RHINO
        spawnBuilder.addSpawn(MobCategory.CREATURE, 2,
                              new MobSpawnSettings.SpawnerData(ModEntities.RHINO.get(), 3, 5));
        // WOLF
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5,
                              new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4));

        // SHEEP, PIG, CHICKEN and COW
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // CAVE and MONSTERS
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // All DEFAULT FEATURES and CUSTOM FEATURES added
        BiomeGenerationSettings.Builder biomeBuilder =
             new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                                                 context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                VegetationPlacements.TREES_PLAINS);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, true);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                                ModPlacedFeatures.ALEXANDRITE_GEODE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                ModPlacedFeatures.SNAPDRAGON_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                ModPlacedFeatures.WALNUT_PLACED_KEY);

        // CUSTOMIZE rain, sky, special effects, etc.
        return new Biome.BiomeBuilder()
                        .hasPrecipitation(true)
                        .downfall(0.8f)
                        .temperature(0.7f)
                        .generationSettings(biomeBuilder.build())
                        .mobSpawnSettings(spawnBuilder.build())
                        .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColorOverride(0x7f03fc)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.MUSIC_DISC_BAR_BRAWL)).build()).build();
    }

    // CUSTOM METHOD - Register TEST BIOME 2
    public static Biome testBiome2(BootstrapContext<Biome> context) {
        // ANIMALS and MONSTERS spawned
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        // RHINO
        spawnBuilder.addSpawn(MobCategory.CREATURE, 2,
                              new MobSpawnSettings.SpawnerData(ModEntities.RHINO.get(), 3, 5));

        // WOLF
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5,
                              new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4));

        // SHEEP, PIG, CHICKEN and COW
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // CAVE and MONSTERS
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // All DEFAULT FEATURES and CUSTOM FEATURES added
        BiomeGenerationSettings.Builder biomeBuilder =
             new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                                                 context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                VegetationPlacements.TREES_PLAINS);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, true);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                                ModPlacedFeatures.ALEXANDRITE_GEODE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                ModPlacedFeatures.SNAPDRAGON_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                ModPlacedFeatures.WALNUT_PLACED_KEY);

        // CUSTOMIZE rain, sky, special effects, etc.
        return new Biome.BiomeBuilder()
                        .hasPrecipitation(true)
                        .downfall(0.8f)
                        .temperature(0.7f)
                        .generationSettings(biomeBuilder.build())
                        .mobSpawnSettings(spawnBuilder.build())
                        .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColorOverride(0x7f03fc)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.MUSIC_DISC_BAR_BRAWL)).build()).build();
    }

    // CUSTOM METHOD - Register all custom biomes on REGISTRIES BIOME
    public static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME,
                                  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }
}