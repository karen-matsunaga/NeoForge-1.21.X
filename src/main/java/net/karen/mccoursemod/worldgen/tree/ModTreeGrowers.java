package net.karen.mccoursemod.worldgen.tree;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;
import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower BLOODWOOD = new TreeGrower(MccourseMod.MOD_ID + ":bloodwood",
           Optional.empty(), Optional.of(ModConfiguredFeatures.BLOODWOOD_KEY), Optional.empty());

    public static final TreeGrower WALNUT = new TreeGrower(MccourseMod.MOD_ID + ":walnut",
           Optional.empty(), Optional.of(ModConfiguredFeatures.WALNUT_KEY), Optional.empty());
}