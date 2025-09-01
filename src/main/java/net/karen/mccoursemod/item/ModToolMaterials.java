package net.karen.mccoursemod.item;

import net.karen.mccoursemod.util.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public class ModToolMaterials {
    // Registry all custom TOOL MATERIALS
    // BISMUTH
    public static final ToolMaterial BISMUTH =
           toolMaterial(ModTags.Blocks.INCORRECT_FOR_BISMUTH_TOOL, 3000, 15.0F,
                        5.0F, 20, ModTags.Items.BISMUTH_TOOL_MATERIALS);

    // ALEXANDRITE
    public static final ToolMaterial ALEXANDRITE =
           toolMaterial(ModTags.Blocks.INCORRECT_FOR_ALEXANDRITE_TOOL, 1400, 11.0F,
                        3.0F, 26, ModTags.Items.ALEXANDRITE_TOOL_MATERIALS);

    // PINK
    public static final ToolMaterial PINK =
           toolMaterial(ModTags.Blocks.INCORRECT_FOR_PINK_TOOL, 800, 11.0F,
                       3.0F, 30, ModTags.Items.PINK_TOOL_MATERIALS);

    // COPPER
    public static final ToolMaterial COPPER =
           toolMaterial(ModTags.Blocks.INCORRECT_FOR_COPPER_TOOL, 500, 9.0F,
                        6.0F, 30, ModTags.Items.COPPER_TOOL_MATERIALS);

    // LAPIS LAZULI
    public static final ToolMaterial LAPIS_LAZULI =
           toolMaterial(ModTags.Blocks.INCORRECT_FOR_LAPIS_LAZULI_TOOL, 200, 5.0F,
                        2.0F, 20, ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS);

    // REDSTONE
    public static final ToolMaterial REDSTONE =
           toolMaterial(ModTags.Blocks.INCORRECT_FOR_REDSTONE_TOOL, 400, 7.0F,
                        1.0F, 30, ModTags.Items.REDSTONE_TOOL_MATERIALS);

    // CUSTOM METHOD - Register MOD tiers
    public static ToolMaterial toolMaterial(TagKey<Block> block, int uses, float speed,
                                            float attack, int enchant, TagKey<Item> repair) {
        return new ToolMaterial(block, uses, speed, attack, enchant, repair);
    }
}