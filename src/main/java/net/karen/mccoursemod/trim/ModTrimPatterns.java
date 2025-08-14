package net.karen.mccoursemod.trim;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModTrimPatterns {
    // Registry all custom TRIM PATTERN textures
    public static final ResourceKey<TrimPattern> KAUPEN = ResourceKey.create(Registries.TRIM_PATTERN,
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "kaupen"));

    // CUSTOM METHOD - Registry all custom TRIM PATTERNS -> Data Generation with JSON file
    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        register(context, ModItems.KAUPEN_SMITHING_TEMPLATE, KAUPEN);
    }

    // CUSTOM METHOD - Registry all custom TRIM PATTERNS parameters
    private static void register(BootstrapContext<TrimPattern> context,
                                 DeferredItem<Item> item, ResourceKey<TrimPattern> key) {
        TrimPattern trimPattern =
            new TrimPattern(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "kaupen"),
                            Component.translatable(Util.makeDescriptionId("trim_pattern", key.location())), false);
        context.register(key, trimPattern);
    }
}