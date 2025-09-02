package net.karen.mccoursemod.worldgen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    // Registry all custom biome modifiers
    // ** CUSTOM ores **
    // BISMUTH
    public static final ResourceKey<BiomeModifier> ADD_BISMUTH_ORE =
           registerKey("add_bismuth_ore");

    public static final ResourceKey<BiomeModifier> ADD_NETHER_BISMUTH_ORE =
           registerKey("add_nether_bismuth_ore");

    public static final ResourceKey<BiomeModifier> ADD_END_BISMUTH_ORE =
           registerKey("add_end_bismuth_ore");

    // ALEXANDRITE
    public static final ResourceKey<BiomeModifier> ADD_ALEXANDRITE_ORE =
           registerKey("add_alexandrite_ore");

    public static final ResourceKey<BiomeModifier> ADD_NETHER_ALEXANDRITE_ORE =
           registerKey("add_nether_alexandrite_ore");

    public static final ResourceKey<BiomeModifier> ADD_END_ALEXANDRITE_ORE =
           registerKey("add_end_alexandrite_ore");

    // PINK
    public static final ResourceKey<BiomeModifier> ADD_PINK_ORE =
           registerKey("add_pink_ore");

    public static final ResourceKey<BiomeModifier> ADD_NETHER_PINK_ORE =
           registerKey("add_nether_pink_ore");

    public static final ResourceKey<BiomeModifier> ADD_END_PINK_ORE =
           registerKey("add_end_pink_ore");

    // ** CUSTOM trees **
    // BLOODWOOD
    public static final ResourceKey<BiomeModifier> ADD_TREE_BLOODWOOD =
           registerKey("add_tree_bloodwood");

    // WALNUT
    public static final ResourceKey<BiomeModifier> ADD_TREE_WALNUT =
           registerKey("add_tree_walnut");

    // Goji Berry custom bush
    public static final ResourceKey<BiomeModifier> ADD_GOJI_BERRY_BUSH =
           registerKey("add_goji_berry_bush");

    // Gecko custom mob
    public static final ResourceKey<BiomeModifier> SPAWN_GECKO = registerKey("spawn_gecko");

    // ** CUSTOM Geodes **
    // ALEXANDRITE
    public static final ResourceKey<BiomeModifier> ADD_ALEXANDRITE_GEODE =
           registerKey("add_alexandrite_geode");

    // ** CUSTOM flowers **
    public static final ResourceKey<BiomeModifier> ADD_SNAPDRAGON = registerKey("add_snapdragon");

    // CUSTOM METHOD - Registry all custom biome modifiers
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // CF -> PF -> BM
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        // Registry all custom placed feature (BIOME)
        // World to generate all custom ores on biomes selected
        // ** CUSTOM ores **
        // BISMUTH
        context.register(ADD_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BISMUTH_ORE_PLACED_KEY)),
                         GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_NETHER_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(BiomeTags.IS_NETHER),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NETHER_BISMUTH_ORE_PLACED_KEY)),
                         GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_END_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(BiomeTags.IS_END),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.END_BISMUTH_ORE_PLACED_KEY)),
                         GenerationStep.Decoration.UNDERGROUND_ORES));

        // Example for individual Biomes!
        // context.register(ADD_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
        //                  HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.SAVANNA)),
        //                  HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BISMUTH_ORE_PLACED_KEY)),
        //                  GenerationStep.Decoration.UNDERGROUND_ORES));

        // ALEXANDRITE
        context.register(ADD_ALEXANDRITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ALEXANDRITE_ORE_PLACED_KEY)),
                         GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_NETHER_ALEXANDRITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(BiomeTags.IS_NETHER),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NETHER_ALEXANDRITE_ORE_PLACED_KEY)),
                         GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_END_ALEXANDRITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(BiomeTags.IS_END),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.END_ALEXANDRITE_ORE_PLACED_KEY)),
                         GenerationStep.Decoration.UNDERGROUND_ORES));

        // PINK
        context.register(ADD_PINK_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PINK_ORE_PLACED_KEY)),
                         GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_NETHER_PINK_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(BiomeTags.IS_NETHER),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NETHER_PINK_ORE_PLACED_KEY)),
                         GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_END_PINK_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(BiomeTags.IS_END),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PINK_ORE_PLACED_KEY)),
                         GenerationStep.Decoration.UNDERGROUND_ORES));

        // World to generate all custom tree on biomes selected
        // CUSTOM trees
        // BLOODWOOD
        context.register(ADD_TREE_BLOODWOOD, new BiomeModifiers.AddFeaturesBiomeModifier(
                         HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.SAVANNA)),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BLOODWOOD_PLACED_KEY)),
                         GenerationStep.Decoration.VEGETAL_DECORATION));

        // WALNUT
        context.register(ADD_TREE_WALNUT, new BiomeModifiers.AddFeaturesBiomeModifier(
                         HolderSet.direct(biomes.getOrThrow(Biomes.BEACH), biomes.getOrThrow(Biomes.ICE_SPIKES)),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.WALNUT_PLACED_KEY)),
                         GenerationStep.Decoration.VEGETAL_DECORATION));

        // CUSTOM bush
        context.register(ADD_GOJI_BERRY_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                         HolderSet.direct(biomes.getOrThrow(Biomes.FOREST)),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GOJI_BERRY_BUSH_PLACED_KEY)),
                         GenerationStep.Decoration.VEGETAL_DECORATION));

        // CUSTOM mob
        context.register(SPAWN_GECKO, new BiomeModifiers.AddSpawnsBiomeModifier(
                         HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.PLAINS)),
                         WeightedList.of(new MobSpawnSettings.SpawnerData(ModEntities.GECKO.get(), 2, 4))));

        // CUSTOM geodes
        context.register(ADD_ALEXANDRITE_GEODE, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ALEXANDRITE_GEODE_PLACED_KEY)),
                         GenerationStep.Decoration.LOCAL_MODIFICATIONS));

        // CUSTOM flowers
        context.register(ADD_SNAPDRAGON, new BiomeModifiers.AddFeaturesBiomeModifier(
                         biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SNAPDRAGON_PLACED_KEY)),
                         GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    // CUSTOM METHOD - Registry all custom biome modifiers (JSON file)
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                                  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }
}