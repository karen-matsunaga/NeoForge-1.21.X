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
    public static final ResourceKey<TrimMaterial> BISMUTH =
           ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "bismuth"));

    // Registry all custom MATERIAL ASSET GROUP ingredients
    public static final MaterialAssetGroup BISMUTH_MATERIAL = MaterialAssetGroup.create("bismuth");

    // CUSTOM METHOD - Registry all custom TRIM MATERIALS -> Data Generation with JSON file
    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, BISMUTH, Style.EMPTY.withColor(TextColor.parseColor("#031cfc").getOrThrow()));
    }

    // CUSTOM METHOD - Registry all custom TRIM MATERIALS parameters
    private static void register(BootstrapContext<TrimMaterial> context,
                                 ResourceKey<TrimMaterial> trimKey, Style style) {
        TrimMaterial trimmaterial = new TrimMaterial(BISMUTH_MATERIAL,
                                                     Component.translatable(Util.makeDescriptionId("trim_material",
                                                                                                   trimKey.location()))
                                                              .withStyle(style));
        context.register(trimKey, trimmaterial);
    }
}