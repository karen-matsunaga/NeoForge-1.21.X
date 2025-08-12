package net.karen.mccoursemod.worldgen;

import net.minecraft.world.level.levelgen.placement.*;
import java.util.List;

public class ModOrePlacement {
    // CUSTOM METHOD - Ore Placement
    public static List<PlacementModifier> orePlacement(PlacementModifier countPlacement,
                                                       PlacementModifier heightRange) {
        return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    // CUSTOM METHOD - Common Ore Placement
    public static List<PlacementModifier> commonOrePlacement(int count,
                                                             PlacementModifier heightRange) {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    // CUSTOM METHOD - Rare Ore Placement
    public static List<PlacementModifier> rareOrePlacement(int chance,
                                                           PlacementModifier heightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }
}