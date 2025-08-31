package net.karen.mccoursemod.worldgen.biome;

import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK =
            makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource ALEXANDRITE =
            makeStateRule(ModBlocks.ALEXANDRITE_BLOCK.get());
    private static final SurfaceRules.RuleSource RAW_ALEXANDRITE =
            makeStateRule(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());

    // CUSTOM METHOD - Custom SURFACE RULES
    public static SurfaceRules.RuleSource makeRules() {
        // WATER
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        // GRASS AND DIRT BLOCKS
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK),
                                                                     DIRT);
        // TEST BIOME
        return SurfaceRules.sequence(SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.TEST_BIOME),
                                                           SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                                                               RAW_ALEXANDRITE)),
                                                           SurfaceRules.ifTrue(SurfaceRules.ON_CEILING,
                                                                               ALEXANDRITE)),
                                     // TEST BIOME 2
                                     SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.TEST_BIOME_2),
                                                           SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                                                               RAW_ALEXANDRITE)),
                                                           SurfaceRules.ifTrue(SurfaceRules.ON_CEILING,
                                                                               ALEXANDRITE)),

                                     // Default to a GRASS and a DIRT surface
                                     SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface));
    }

    // CUSTOM METHOD - Custom block STATE RULE
    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}