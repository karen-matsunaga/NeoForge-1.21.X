package net.karen.mccoursemod.trim;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class ModTrimMaterials {
    // Registry all custom TRIM MATERIAL textures
    // BISMUTH
    public static final ResourceKey<TrimMaterial> BISMUTH =
           ResourceKey.create(Registries.TRIM_MATERIAL,
                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "bismuth"));

    // ALEXANDRITE
    public static final ResourceKey<TrimMaterial> ALEXANDRITE =
           ResourceKey.create(Registries.TRIM_MATERIAL,
                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "alexandrite"));

    // PINK
    public static final ResourceKey<TrimMaterial> PINK =
           ResourceKey.create(Registries.TRIM_MATERIAL,
                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "pink"));

    // Registry all custom MATERIAL ASSET GROUP ingredients
    public static final MaterialAssetGroup BISMUTH_MATERIAL = MaterialAssetGroup.create("bismuth");
    public static final MaterialAssetGroup ALEXANDRITE_MATERIAL = MaterialAssetGroup.create("alexandrite");
    public static final MaterialAssetGroup PINK_MATERIAL = MaterialAssetGroup.create("pink");

    // CUSTOM METHOD - Registry all custom TRIM MATERIALS -> Data Generation with JSON file
    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, BISMUTH, Style.EMPTY.withColor(TextColor.parseColor("#031cfc").getOrThrow()), BISMUTH_MATERIAL);
        register(context, ALEXANDRITE, Style.EMPTY.withColor(TextColor.parseColor("#7AE2FF").getOrThrow()), ALEXANDRITE_MATERIAL);
        register(context, PINK, Style.EMPTY.withColor(TextColor.parseColor("#F433C1").getOrThrow()), PINK_MATERIAL);
    }

    // CUSTOM METHOD - Registry all custom TRIM MATERIALS parameters
    private static void register(BootstrapContext<TrimMaterial> context,
                                 ResourceKey<TrimMaterial> key, Style style, MaterialAssetGroup material) {
        Component trimMaterial = Component.translatable(Util.makeDescriptionId("trim_material", key.location()))
                                          .withStyle(style);
        TrimMaterial trimmaterial = new TrimMaterial(material, trimMaterial);
        context.register(key, trimmaterial);
    }
}