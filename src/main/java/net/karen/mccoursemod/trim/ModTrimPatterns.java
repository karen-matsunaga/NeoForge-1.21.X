package net.karen.mccoursemod.trim;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.trim.TrimPattern;

public class ModTrimPatterns {
    // Registry all custom TRIM PATTERN textures
    public static final ResourceKey<TrimPattern> KAUPEN =
           ResourceKey.create(Registries.TRIM_PATTERN,
                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "kaupen"));

    // CUSTOM METHOD - Registry all custom TRIM PATTERNS -> Data Generation with JSON file
    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        register(context, KAUPEN);
    }

    // CUSTOM METHOD - Registry all custom TRIM PATTERNS parameters
    private static void register(BootstrapContext<TrimPattern> context,
                                 ResourceKey<TrimPattern> key) {
        ResourceLocation location = key.location();
        TrimPattern trimPattern = new TrimPattern(location,
                                                  Component.translatable(Util.makeDescriptionId("trim_pattern", location)),
                                                  false);
        context.register(key, trimPattern);
    }
}