package net.karen.mccoursemod.worldgen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import java.util.List;

public class ModPlacedFeatures {
    // Registry all custom placed features
    // ** CUSTOM ores **
    // BISMUTH
    public static final ResourceKey<PlacedFeature> BISMUTH_ORE_PLACED_KEY =
           registerKey("bismuth_ore_placed");

    public static final ResourceKey<PlacedFeature> NETHER_BISMUTH_ORE_PLACED_KEY =
           registerKey("nether_bismuth_ore_placed");

    public static final ResourceKey<PlacedFeature> END_BISMUTH_ORE_PLACED_KEY =
           registerKey("end_bismuth_ore_placed");

    // ALEXANDRITE
    public static final ResourceKey<PlacedFeature> ALEXANDRITE_ORE_PLACED_KEY =
           registerKey("alexandrite_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_ALEXANDRITE_ORE_PLACED_KEY =
           registerKey("nether_alexandrite_ore_placed");
    public static final ResourceKey<PlacedFeature> END_ALEXANDRITE_ORE_PLACED_KEY =
           registerKey("end_alexandrite_ore_placed");

    // PINK
    public static final ResourceKey<PlacedFeature> PINK_ORE_PLACED_KEY =
           registerKey("pink_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_PINK_ORE_PLACED_KEY =
           registerKey("nether_pink_ore_placed");
    public static final ResourceKey<PlacedFeature> END_PINK_ORE_PLACED_KEY =
           registerKey("end_pink_ore_placed");

    // ** CUSTOM trees **
    // BLOODWOOD
    public static final ResourceKey<PlacedFeature> BLOODWOOD_PLACED_KEY =
           registerKey("bloodwood_placed");

    // WALNUT
    public static final ResourceKey<PlacedFeature> WALNUT_PLACED_KEY =
           registerKey("walnut_placed");

    // Goji Berry custom bush
    public static final ResourceKey<PlacedFeature> GOJI_BERRY_BUSH_PLACED_KEY =
           registerKey("goji_berry_bush_placed");

    // ** CUSTOM Geodes **
    // ALEXANDRITE
    public static final ResourceKey<PlacedFeature> ALEXANDRITE_GEODE_PLACED_KEY =
           registerKey("alexandrite_geode_placed");

    // ** CUSTOM flowers **
    public static final ResourceKey<PlacedFeature> SNAPDRAGON_PLACED_KEY =
           registerKey("snapdragon_placed");

    // CUSTOM METHOD - Registry all custom placed features
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // CUSTOM ores -> Position block of custom ores to go to generate on overworld, nether, etc.
        // BISMUTH
        register(context, BISMUTH_ORE_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_BISMUTH_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(12,
                 HeightRangePlacement.triangle(VerticalAnchor.absolute(-64),
                 VerticalAnchor.absolute(80))));

        register(context, NETHER_BISMUTH_ORE_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_BISMUTH_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(9,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                 VerticalAnchor.absolute(80))));

        register(context, END_BISMUTH_ORE_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.END_BISMUTH_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(12,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                 VerticalAnchor.absolute(80))));

        // ALEXANDRITE
        register(context, ALEXANDRITE_ORE_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_ALEXANDRITE_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(12,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                 VerticalAnchor.absolute(80))));

        register(context, NETHER_ALEXANDRITE_ORE_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_ALEXANDRITE_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(9,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                 VerticalAnchor.absolute(80))));

        register(context, END_ALEXANDRITE_ORE_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.END_ALEXANDRITE_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(7,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                 VerticalAnchor.absolute(80))));

        // PINK
        register(context, PINK_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_PINK_ORE_KEY),
                 ModOrePlacement.rareOrePlacement(20,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                 VerticalAnchor.absolute(80))));

        register(context, NETHER_PINK_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_PINK_ORE_KEY),
                 ModOrePlacement.rareOrePlacement(15,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                 VerticalAnchor.absolute(80))));

        register(context, END_PINK_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_PINK_ORE_KEY),
                 ModOrePlacement.rareOrePlacement(10,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                 VerticalAnchor.absolute(80))));

        // Position block of custom tree to go to generate on overworld
        // CUSTOM trees
        // BLOODWOOD
        register(context, BLOODWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOODWOOD_KEY),
                 VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                 ModBlocks.BLOODWOOD_SAPLING.get()));

        // WALNUT
        register(context, WALNUT_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WALNUT_KEY),
                 VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                 ModBlocks.WALNUT_SAPLING.get()));

        // CUSTOM bush
        register(context, GOJI_BERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOJI_BERRY_BUSH_KEY),
                 List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(),
                         PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

        // CUSTOM geodes
        register(context, ALEXANDRITE_GEODE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALEXANDRITE_GEODE_KEY),
                 List.of(RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                         HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                         BiomeFilter.biome()));

        // CUSTOM flowers
        register(context, SNAPDRAGON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SNAPDRAGON_KEY),
                 List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),
                         PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    }

    // CUSTOM METHOD - Registry all custom placed features (JSON file)
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                                  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

    // CUSTOM METHOD - Registry all custom placed features
    private static void register(BootstrapContext<PlacedFeature> context,
                                 ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}