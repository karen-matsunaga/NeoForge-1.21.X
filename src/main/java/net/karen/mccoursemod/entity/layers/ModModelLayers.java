package net.karen.mccoursemod.entity.layers;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    // Registry all custom boats and chest boats
    public static final ModelLayerLocation WALNUT_BOAT_LAYER =
           new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "boat/walnut"),
                                  "main"); // Custom boat

    public static final ModelLayerLocation WALNUT_CHEST_BOAT_LAYER =
           new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "chest_boat/walnut"),
                                  "main"); // Custom chest boat
}