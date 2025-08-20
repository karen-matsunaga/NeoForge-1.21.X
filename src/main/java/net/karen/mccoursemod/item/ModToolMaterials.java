package net.karen.mccoursemod.item;

import net.karen.mccoursemod.util.ModTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterials {
    // Registry all custom TOOL MATERIALS
    // BISMUTH
    public static final ToolMaterial BISMUTH =
           new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_BISMUTH_TOOL, 3000, 15.0F,
                            5.0F, 20, ModTags.Items.BISMUTH_TOOL_MATERIALS);

    // ALEXANDRITE
    public static final ToolMaterial ALEXANDRITE =
           new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_ALEXANDRITE_TOOL, 1400, 11.0F,
                            3.0F, 26, ModTags.Items.ALEXANDRITE_TOOL_MATERIALS);
}