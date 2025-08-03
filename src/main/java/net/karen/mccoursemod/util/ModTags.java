package net.karen.mccoursemod.util;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class ModTags {
    // CUSTOM CLASS - Registry all custom block tags
    public static class Blocks {
        // Created tool tags
        // Blocks break
        public static final TagKey<Block> NEEDS_BISMUTH_TOOL = createTag("needs_bismuth_tool");

        // Blocks not break
        public static final TagKey<Block> INCORRECT_FOR_BISMUTH_TOOL = createTag("incorrect_for_bismuth_tool");

        // Created More Ores's drops
        public static final TagKey<Block> MORE_ORES_ALL_DROPS = createTag("more_ores_all_drops");
        public static final TagKey<Block> MORE_ORES_BREAK_BLOCK = createTag("more_ores_break_block");

        // Ores
        public static final TagKey<Block> AUTO_SMELT_ORES = createTag("auto_smelt_ores");

        // Rainbow effect
        public static final TagKey<Block> RAINBOW_DROPS = createTag("rainbow_drops");
        public static final TagKey<Block> RAINBOW_ORES = createTag("rainbow_ores");
        public static final TagKey<Block> RAINBOW_BLOCKS = createTag("rainbow_blocks");

        // CUSTOM METHOD - Registry all custom block tags
        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
        }
    }

    // CUSTOM CLASS - Registry all custom item tags
    public static class Items {
        // Created Fly effect item tag
        public static final TagKey<Item> HELMET_FLY = createTag("helmet_fly");
        public static final TagKey<Item> CHESTPLATE_FLY = createTag("chestplate_fly");
        public static final TagKey<Item> LEGGINGS_FLY = createTag("leggings_fly");
        public static final TagKey<Item> BOOTS_FLY = createTag("boots_fly");

        // Level Charger items
        public static final TagKey<Item> LEVEL_CHARGER_ITEMS = createTag("level_charger_items");
        public static final TagKey<Item> LEVEL_CHARGER_GENERIC = createTag("level_charger_generic");
        public static final TagKey<Item> LEVEL_CHARGER_SPECIF = createTag("level_charger_specif");
        public static final TagKey<Item> LEVEL_CHARGER_GREEN = createTag("level_charger_green");
        public static final TagKey<Item> LEVEL_CHARGER_RED = createTag("level_charger_red");

        // Magic block
        public static final TagKey<Item> MAGIC_BLOCK = createTag("magic_block");

        // Bismuth Tools
        public static final TagKey<Item> BISMUTH_TOOLS = createTag("bismuth_tools");

        // Bismuth Ingredients
        public static final TagKey<Item> BISMUTH_ARMOR = createTag("bismuth_armor");

        // Tools
        public static final TagKey<Item> BISMUTH_TOOL_MATERIALS = createTag("bismuth_tool_materials");

        // Armor ingredient
        public static final TagKey<Item> REPAIRS_BISMUTH_ARMOR = createTag("repairs_bismuth_armor");

        // Data Component tags
        public static final TagKey<Item> SPECIAL_EFFECT_ITEMS = createTag("special_effect_items");

        // CUSTOM METHOD - Registry all custom item tags
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
        }
    }

    // CUSTOM CLASS - Registry all custom enchantment tags
    public static class Enchantments {
        public static final TagKey<Enchantment> LIGHTNING_STRIKER_TAG = createTag("lightning_striker_tag");
        public static final TagKey<Enchantment> MINING_ENCHANTMENTS = createTag("mining_enchantments");
        public static final TagKey<Enchantment> DURABILITY_ENCHANTMENTS = createTag("durability_enchantments");
        public static final TagKey<Enchantment> FISHING_ENCHANTMENTS = createTag("fishing_enchantments");
        public static final TagKey<Enchantment> HELMET_ENCHANTMENTS = createTag("helmet_enchantments");
        public static final TagKey<Enchantment> BOOTS_ENCHANTMENTS = createTag("boots_enchantments");
        public static final TagKey<Enchantment> LEGGINGS_ENCHANTMENTS = createTag("leggings_enchantments");
        public static final TagKey<Enchantment> CHESTPLATE_ENCHANTMENTS = createTag("chestplate_enchantments");
        public static final TagKey<Enchantment> SWORD_ENCHANTMENTS = createTag("sword_enchantments");
        public static final TagKey<Enchantment> BOW_ENCHANTMENTS = createTag("bow_enchantments");
        public static final TagKey<Enchantment> CROSSBOW_ENCHANTMENTS = createTag("crossbow_enchantments");
        public static final TagKey<Enchantment> TRIDENT_ENCHANTMENTS = createTag("trident_enchantments");
        public static final TagKey<Enchantment> MACE_ENCHANTMENTS = createTag("mace_enchantments");
        public static final TagKey<Enchantment> ALL_ENCHANTMENTS = createTag("all_enchantments");

        // CUSTOM METHOD - Registry all custom enchantment tags
        private static TagKey<Enchantment> createTag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
        }
    }
}