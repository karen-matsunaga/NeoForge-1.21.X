package net.karen.mccoursemod.util;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    // CUSTOM CLASS - Registry all custom block tags
    public static class Blocks {
        // ** CUSTOM ores -> Mccourse Mod ores **
        public static final TagKey<Block> MCCOURSE_MOD_ORES = createTag("mccourse_mod_ores");

        // ** CUSTOM TOOLS **
        // PAXEL tool
        public static final TagKey<Block> MINEABLE_WITH_PAXEL = createTag("mineable_with_paxel");

        // TOOL MATERIALS -> Blocks break
        public static final TagKey<Block> NEEDS_BISMUTH_TOOL = createTag("needs_bismuth_tool");
        public static final TagKey<Block> NEEDS_ALEXANDRITE_TOOL =
               createTag("needs_alexandrite_tool");

        // TOOL MATERIALS -> Blocks not break
        public static final TagKey<Block> INCORRECT_FOR_BISMUTH_TOOL = createTag("incorrect_for_bismuth_tool");
        public static final TagKey<Block> INCORRECT_FOR_ALEXANDRITE_TOOL =
               createTag("incorrect_for_alexandrite_tool");

        // Created More Ores's drops - More Ores I, II, III, IV, V, Max Level
        public static final TagKey<Block> MORE_ORES_ONE_DROPS = createTag("more_ores_one_drops");
        public static final TagKey<Block> MORE_ORES_TWO_DROPS = createTag("more_ores_two_drops");
        public static final TagKey<Block> MORE_ORES_THREE_DROPS = createTag("more_ores_three_drops");
        public static final TagKey<Block> MORE_ORES_FOUR_DROPS = createTag("more_ores_four_drops");
        public static final TagKey<Block> MORE_ORES_FIVE_DROPS = createTag("more_ores_five_drops");
        public static final TagKey<Block> MORE_ORES_ALL_DROPS = createTag("more_ores_all_drops");
        public static final TagKey<Block> MORE_ORES_BREAK_BLOCK = createTag("more_ores_break_block");

        // Ores
        public static final TagKey<Block> AUTO_SMELT_ORES = createTag("auto_smelt_ores");

        // Block Fly effect
        public static final TagKey<Block> BLOCK_FLY_BLOCK_SPEED = createTag("block_fly_block_speed");

        // BISMUTH blocks
        public static final TagKey<Block> BISMUTH_BLOCKS = createTag("bismuth_blocks");

        // ALEXANDRITE blocks
        public static final TagKey<Block> ALEXANDRITE_BLOCKS = createTag("alexandrite_blocks");

        // METAL DETECTOR
        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = createTag("metal_detector_valuables");
        public static final TagKey<Block> METAL_DETECTOR_COLORS = createTag("metal_detector_colors");

        // ** CUSTOM log **
        public static final TagKey<Block> BLOODWOOD_LOGS = createTag("bloodwood_logs");
        public static final TagKey<Block> WALNUT_LOGS = createTag("walnut_logs");

        // ** CUSTOM Ender pearl block **
        public static final TagKey<Block> ENDER_PEARL_BLOCKS = createTag("ender_pearl_blocks");

        // ** CUSTOM mob blocks **
        public static final TagKey<Block> MOBS_BLOCKS_DROPS = createTag("mobs_blocks_drops");

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

        // TOOL MATERIALS items - TOOLS
        public static final TagKey<Item> BISMUTH_TOOLS = createTag("bismuth_tools");
        public static final TagKey<Item> ALEXANDRITE_TOOLS = createTag("alexandrite_tools");

        // ARMOR MATERIALS items - ARMORS
        public static final TagKey<Item> BISMUTH_ARMOR = createTag("bismuth_armor");
        public static final TagKey<Item> ALEXANDRITE_ARMOR = createTag("alexandrite_armor");

        // TOOL MATERIALS items - REPAIR TOOL ingredients
        public static final TagKey<Item> BISMUTH_TOOL_MATERIALS = createTag("bismuth_tool_materials");
        public static final TagKey<Item> ALEXANDRITE_TOOL_MATERIALS =
               createTag("alexandrite_tool_materials");

        // ARMOR MATERIALS items - REPAIR ARMOR ingredients
        public static final TagKey<Item> REPAIRS_BISMUTH_ARMOR = createTag("repairs_bismuth_armor");
        public static final TagKey<Item> REPAIRS_ALEXANDRITE_ARMOR = createTag("repairs_alexandrite_armor");

        // Data Component tags
        public static final TagKey<Item> SPECIAL_EFFECT_ITEMS = createTag("special_effect_items");

        // ** CUSTOM log **
        public static final TagKey<Item> BLOODWOOD_LOGS = createTag("bloodwood_logs");
        public static final TagKey<Item> WALNUT_LOGS = createTag("walnut_logs");

        // CUSTOM METHOD - Registry all custom item tags
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
        }
    }

    // CUSTOM CLASS - Registry all custom enchantment tags
    public static class Enchantments {
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

    // Entities
    public static class Entities {
        // Created Entities's tags HERE
        public static final TagKey<EntityType<?>> BOSSES = createTag("bosses");
        public static final TagKey<EntityType<?>> NETHER = createTag("nether");
        public static final TagKey<EntityType<?>> END = createTag("end");
        public static final TagKey<EntityType<?>> OVERWORLD = createTag("overworld");
        public static final TagKey<EntityType<?>> MONSTERS = createTag("monsters");
        public static final TagKey<EntityType<?>> ANIMALS = createTag("animals");
        public static final TagKey<EntityType<?>> VILLAGER = createTag("villager");
        public static final TagKey<EntityType<?>> WATER_ANIMALS = createTag("water_animals");

        // CUSTOM METHOD - Registry all custom entities tags
        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
        }
    }

    // Biomes
    public static class Biomes {
        // Created Biome's tags HERE
        // CUSTOM STRUCTURES
        public static final TagKey<Biome> HAS_KAUPEN_HOUSE = create("has_structure/kaupen_house");
        // CUSTOM JIGSAW STRUCTURES
        public static final TagKey<Biome> HAS_STORAGE_PLATFORM = create("has_structure/storage_platform");

        // CUSTOM METHOD - Registry all custom biome tags
        private static TagKey<Biome> create(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
        }
    }
}