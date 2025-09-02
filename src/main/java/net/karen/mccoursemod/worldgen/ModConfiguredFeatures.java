package net.karen.mccoursemod.worldgen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import java.util.List;

public class ModConfiguredFeatures {
    // Registry all custom configured features
    // ** CUSTOM ores **
    // BISMUTH -> STONE, NETHER and END
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_BISMUTH_ORE_KEY =
           registerKey("bismuth_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_BISMUTH_ORE_KEY =
           registerKey("nether_bismuth_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> END_BISMUTH_ORE_KEY =
           registerKey("end_bismuth_ore");

    // ALEXANDRITE -> STONE, NETHER and END
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ALEXANDRITE_ORE_KEY =
           registerKey("alexandrite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_ALEXANDRITE_ORE_KEY =
           registerKey("nether_alexandrite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> END_ALEXANDRITE_ORE_KEY =
           registerKey("end_alexandrite_ore");

    // PINK -> STONE, NETHER and END
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PINK_ORE_KEY =
           registerKey("pink_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_PINK_ORE_KEY =
           registerKey("nether_pink_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> END_PINK_ORE_KEY =
           registerKey("end_pink_ore");

    // ** CUSTOM trees **
    // BLOODWOOD
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODWOOD_KEY =
           registerKey("bloodwood");

    // WALNUT
    public static final ResourceKey<ConfiguredFeature<?, ?>> WALNUT_KEY =
           registerKey("walnut");

    // Goji Berry custom bush
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOJI_BERRY_BUSH_KEY =
           registerKey("goji_berry_bush");

    // ** CUSTOM Geodes **
    // ALEXANDRITE
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALEXANDRITE_GEODE_KEY =
           registerKey("alexandrite_geode");

    // ** CUSTOM flowers **
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNAPDRAGON_KEY =
           registerKey("snapdragon");

    // CUSTOM METHOD - Registry all custom configured features
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        // CUSTOM ores
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        // All custom ores generated on overworld
        // BISMUTH
        List<OreConfiguration.TargetBlockState> overworldBismuthOres =
            List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.BISMUTH_ORE.get().defaultBlockState()),
                    OreConfiguration.target(deepslateReplaceables, ModBlocks.BISMUTH_DEEPSLATE_ORE.get().defaultBlockState()));

        // ALEXANDRITE
        List<OreConfiguration.TargetBlockState> overworldAlexandriteOres =
            List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.ALEXANDRITE_ORE.get().defaultBlockState()),
                    OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get().defaultBlockState()));

        // PINK
        List<OreConfiguration.TargetBlockState> overworldPinkOres =
            List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.PINK_ORE.get().defaultBlockState()),
                    OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_PINK_ORE.get().defaultBlockState()));

        // Register all custom ores and define the amount of ores to generate on overworld
        // BISMUTH
        register(context, OVERWORLD_BISMUTH_ORE_KEY, Feature.ORE, new OreConfiguration(overworldBismuthOres, 9));

        register(context, NETHER_BISMUTH_ORE_KEY, Feature.ORE,
                 new OreConfiguration(netherrackReplaceables, ModBlocks.BISMUTH_NETHER_ORE.get().defaultBlockState(), 9));

        register(context, END_BISMUTH_ORE_KEY, Feature.ORE,
                 new OreConfiguration(endReplaceables, ModBlocks.BISMUTH_END_ORE.get().defaultBlockState(), 9));

        // ALEXANDRITE
        register(context, OVERWORLD_ALEXANDRITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAlexandriteOres, 9));

        register(context, NETHER_ALEXANDRITE_ORE_KEY, Feature.ORE,
                 new OreConfiguration(netherrackReplaceables, ModBlocks.NETHER_ALEXANDRITE_ORE.get().defaultBlockState(), 9));

        register(context, END_ALEXANDRITE_ORE_KEY, Feature.ORE,
                 new OreConfiguration(endReplaceables, ModBlocks.END_STONE_ALEXANDRITE_ORE.get().defaultBlockState(), 9));

        // PINK
        register(context, OVERWORLD_PINK_ORE_KEY, Feature.ORE, new OreConfiguration(overworldPinkOres, 20));

        register(context, NETHER_PINK_ORE_KEY, Feature.ORE,
                 new OreConfiguration(netherrackReplaceables, ModBlocks.NETHER_PINK_ORE.get().defaultBlockState(), 20));

        register(context, END_PINK_ORE_KEY, Feature.ORE,
                 new OreConfiguration(endReplaceables, ModBlocks.END_STONE_PINK_ORE.get().defaultBlockState(), 20));

        // ** CUSTOM trees **
        // BLOODWOOD
        register(context, BLOODWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                 // Bloodwood log
                 BlockStateProvider.simple(ModBlocks.BLOODWOOD_LOG.get()),
                 new ForkingTrunkPlacer(4, 4, 3),
                 // Bloodwood leave
                 BlockStateProvider.simple(ModBlocks.BLOODWOOD_LEAVES.get()),
                 new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                 // Bloodwood log + Bloodwood leave layer
                 new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(Blocks.NETHERRACK)).build());

        // WALNUT
        register(context, WALNUT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                 // Log's position
                 BlockStateProvider.simple(ModBlocks.WALNUT_LOG.get()), new DarkOakTrunkPlacer(5, 4, 3),
                 // Leave's position
                 BlockStateProvider.simple(ModBlocks.WALNUT_LEAVES.get()),
                 new DarkOakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2)),
                 // Dirt block or End Stone block
                 new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(Blocks.END_STONE)).build());

        // CUSTOM bush
        register(context, GOJI_BERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                 FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                 new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GOJI_BERRY_BUSH.get()
                                                                .defaultBlockState()
                                                                .setValue(SweetBerryBushBlock.AGE, 3))),
                                              List.of(Blocks.GRASS_BLOCK)));

        // CUSTOM geodes
        register(context, ALEXANDRITE_GEODE_KEY, Feature.GEODE,
                 new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                                        BlockStateProvider.simple(Blocks.DEEPSLATE),
                                        BlockStateProvider.simple(ModBlocks.ALEXANDRITE_ORE.get()),
                                        BlockStateProvider.simple(Blocks.DIRT),
                                        BlockStateProvider.simple(Blocks.EMERALD_BLOCK),
                                        List.of(ModBlocks.ALEXANDRITE_BLOCK.get().defaultBlockState()),
                                        BlockTags.FEATURES_CANNOT_REPLACE , BlockTags.GEODE_INVALID_BLOCKS),
                                        new GeodeLayerSettings(1.7D, 1.2D,
                                                               2.5D, 3.5D),
                                        new GeodeCrackSettings(0.25D,
                                                               1.5D, 1),
                                        0.5D, 0.1D,
                                        true,
                                        UniformInt.of(3, 8), UniformInt.of(2, 6), UniformInt.of(1, 2),
                                        -18, 18, 0.075D, 1));

        // CUSTOM flowers
        register(context, SNAPDRAGON_KEY, Feature.FLOWER,
                 new RandomPatchConfiguration(32, 6, 2,
                                              PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                              new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SNAPDRAGON.get())))));
    }

    // CUSTOM METHOD - Registry all configured features (JSON file)
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                                  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

    // CUSTOM METHOD - Registry all configured features
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void
                   register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                            ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}