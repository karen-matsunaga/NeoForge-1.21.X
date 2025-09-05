package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.fluid.ModFluids;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput packOutput) {
        super(packOutput, MccourseMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // ** CUSTOM ITEMS **
        // ** CUSTOM ore items **
        // BISMUTH
        addItem(ModItems.BISMUTH, "Bismuth");
        addItem(ModItems.RAW_BISMUTH, "Raw Bismuth");
        // ALEXANDRITE
        addItem(ModItems.ALEXANDRITE, "Alexandrite");
        addItem(ModItems.RAW_ALEXANDRITE, "Raw Alexandrite");
        // PINK
        addItem(ModItems.PINK, "Pink");

        // ** CUSTOM ADVANCED ITEMS **
        addItem(ModItems.AUTO_SMELT, "Auto Smelt");
        addItem(ModItems.GROWTH, "Growth");
        addItem(ModItems.LEVEL_CHARGER_GENERIC_PLUS, "Level Charger Generic Plus");
        addItem(ModItems.LEVEL_CHARGER_GENERIC_MINUS, "Level Charger Generic Minus");
        addItem(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE, "Level Charger Specif Minus Fortune");
        addItem(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE, "Level Charger Specif Plus Fortune");
        addItem(ModItems.MCCOURSE_MOD_BOTTLE, "Mccourse Mod Bottle");
        addItem(ModItems.CHISEL, "Chisel");
        addItem(ModItems.DATA_TABLET, "Data Tablet");
        addItem(ModItems.METAL_DETECTOR, "Metal Detector");

        // ** CUSTOM MUSIC DISC **
        addItem(ModItems.BAR_BRAWL_MUSIC_DISC, "Bar Brawl Music Disc");

        // ** CUSTOM Foods **
        addItem(ModItems.COFFEE, "Coffee");

        // ** CUSTOM Tools **
        // BISMUTH
        addItem(ModItems.BISMUTH_HAMMER, "Bismuth Hammer");
        addItem(ModItems.BISMUTH_PAXEL, "Bismuth Paxel");
        addItem(ModItems.BISMUTH_SWORD, "Bismuth Sword");
        addItem(ModItems.BISMUTH_PICKAXE, "Bismuth Pickaxe");
        addItem(ModItems.BISMUTH_SHOVEL, "Bismuth Shovel");
        addItem(ModItems.BISMUTH_AXE, "Bismuth Axe");
        addItem(ModItems.BISMUTH_HOE, "Bismuth Hoe");
        addItem(ModItems.KAUPEN_BOW, "Kaupen Bow");
        // ALEXANDRITE
        addItem(ModItems.ALEXANDRITE_HAMMER, "Alexandrite Hammer");
        addItem(ModItems.ALEXANDRITE_PAXEL, "Alexandrite Paxel");
        addItem(ModItems.ALEXANDRITE_SWORD, "Alexandrite Sword");
        addItem(ModItems.ALEXANDRITE_PICKAXE, "Alexandrite Pickaxe");
        addItem(ModItems.ALEXANDRITE_SHOVEL, "Alexandrite Shovel");
        addItem(ModItems.ALEXANDRITE_AXE, "Alexandrite Axe");
        addItem(ModItems.ALEXANDRITE_HOE, "Alexandrite Hoe");
        addItem(ModItems.ALEXANDRITE_BOW, "Alexandrite Bow");

        // ** CUSTOM Armors **
        // BISMUTH
        addItem(ModItems.BISMUTH_HELMET, "Bismuth Helmet");
        addItem(ModItems.BISMUTH_CHESTPLATE, "Bismuth Chestplate");
        addItem(ModItems.BISMUTH_LEGGINGS, "Bismuth Leggings");
        addItem(ModItems.BISMUTH_BOOTS, "Bismuth Boots");
        // ALEXANDRITE
        addItem(ModItems.ALEXANDRITE_HELMET, "Alexandrite Helmet");
        addItem(ModItems.ALEXANDRITE_CHESTPLATE, "Alexandrite Chestplate");
        addItem(ModItems.ALEXANDRITE_LEGGINGS, "Alexandrite Leggings");
        addItem(ModItems.ALEXANDRITE_BOOTS, "Alexandrite Boots");
        // PINK
        addItem(ModItems.PINK_HELMET, "Pink Helmet");
        addItem(ModItems.PINK_CHESTPLATE, "Pink Chestplate");
        addItem(ModItems.PINK_LEGGINGS, "Pink Leggings");
        addItem(ModItems.PINK_BOOTS, "Pink Boots");
        // COPPER
        addItem(ModItems.COPPER_HELMET, "Copper Helmet");
        addItem(ModItems.COPPER_CHESTPLATE, "Copper Chestplate");
        addItem(ModItems.COPPER_LEGGINGS, "Copper Leggings");
        addItem(ModItems.COPPER_BOOTS, "Copper Boots");
        // LAPIS LAZULI
        addItem(ModItems.LAPIS_LAZULI_HELMET, "Lapis Lazuli Helmet");
        addItem(ModItems.LAPIS_LAZULI_CHESTPLATE, "Lapis Lazuli Chestplate");
        addItem(ModItems.LAPIS_LAZULI_LEGGINGS, "Lapis Lazuli Leggings");
        addItem(ModItems.LAPIS_LAZULI_BOOTS, "Lapis Lazuli Boots");
        // REDSTONE
        addItem(ModItems.REDSTONE_HELMET, "Redstone Helmet");
        addItem(ModItems.REDSTONE_CHESTPLATE, "Redstone Chestplate");
        addItem(ModItems.REDSTONE_LEGGINGS, "Redstone Leggings");
        addItem(ModItems.REDSTONE_BOOTS, "Redstone Boots");

        // ** CUSTOM Paxel **
        addItem(ModItems.PINK_PAXEL, "Pink Paxel");
        addItem(ModItems.COPPER_PAXEL, "Copper Paxel");
        addItem(ModItems.DIAMOND_PAXEL, "Diamond Paxel");
        addItem(ModItems.GOLD_PAXEL, "Gold Paxel");
        addItem(ModItems.IRON_PAXEL, "Iron Paxel");
        addItem(ModItems.STONE_PAXEL, "Stone Paxel");
        addItem(ModItems.WOODEN_PAXEL, "Wooden Paxel");
        addItem(ModItems.NETHERITE_PAXEL, "Netherite Paxel");
        addItem(ModItems.LAPIS_LAZULI_PAXEL, "Lapis Lazuli Paxel");
        addItem(ModItems.REDSTONE_PAXEL, "Redstone Paxel");

        // ** CUSTOM Hammer **
        addItem(ModItems.PINK_HAMMER, "Pink Hammer");
        addItem(ModItems.COPPER_HAMMER, "Copper Hammer");
        addItem(ModItems.DIAMOND_HAMMER, "Diamond Hammer");
        addItem(ModItems.GOLD_HAMMER, "Gold Hammer");
        addItem(ModItems.IRON_HAMMER, "Iron Hammer");
        addItem(ModItems.STONE_HAMMER, "Stone Hammer");
        addItem(ModItems.WOODEN_HAMMER, "Wooden Hammer");
        addItem(ModItems.NETHERITE_HAMMER, "Netherite Hammer");
        addItem(ModItems.LAPIS_LAZULI_HAMMER, "Lapis Lazuli Hammer");
        addItem(ModItems.REDSTONE_HAMMER, "Redstone Hammer");

        // ** CUSTOM Shovel **
        addItem(ModItems.PINK_SHOVEL, "Pink Shovel");
        addItem(ModItems.COPPER_SHOVEL, "Copper Shovel");
        addItem(ModItems.LAPIS_LAZULI_SHOVEL, "Lapis Lazuli Shovel");
        addItem(ModItems.REDSTONE_SHOVEL, "Redstone Shovel");

        // ** CUSTOM Axe **
        addItem(ModItems.PINK_AXE, "Pink Axe");
        addItem(ModItems.COPPER_AXE, "Copper Axe");
        addItem(ModItems.LAPIS_LAZULI_AXE, "Lapis Lazuli Axe");
        addItem(ModItems.REDSTONE_AXE, "Redstone Axe");

        // ** CUSTOM Hoe **
        addItem(ModItems.PINK_HOE, "Pink Hoe");
        addItem(ModItems.COPPER_HOE, "Copper Hoe");
        addItem(ModItems.LAPIS_LAZULI_HOE, "Lapis Lazuli Hoe");
        addItem(ModItems.REDSTONE_HOE, "Redstone Hoe");

        // ** CUSTOM Pickaxe **
        addItem(ModItems.PINK_PICKAXE, "Pink Pickaxe");
        addItem(ModItems.COPPER_PICKAXE, "Copper Pickaxe");
        addItem(ModItems.LAPIS_LAZULI_PICKAXE, "Lapis Lazuli Pickaxe");
        addItem(ModItems.REDSTONE_PICKAXE, "Redstone Pickaxe");

        // ** CUSTOM Pickaxe **
        addItem(ModItems.PINK_SWORD, "Pink Sword");
        addItem(ModItems.COPPER_SWORD, "Copper Sword");
        addItem(ModItems.LAPIS_LAZULI_SWORD, "Lapis Lazuli Sword");
        addItem(ModItems.REDSTONE_SWORD, "Redstone Sword");

        // ** CUSTOM Elytra armor **
        addItem(ModItems.DIAMOND_ELYTRA, "Diamond Elytra");
        add("item.mccoursemod.diamond_elytra_broken", "Broken Diamond Elytra");

        // ** CUSTOM Horse armor **
        // BISMUTH
        addItem(ModItems.BISMUTH_HORSE_ARMOR, "Bismuth Horse Armor");
        // ALEXANDRITE
        addItem(ModItems.ALEXANDRITE_HORSE_ARMOR, "Alexandrite Horse Armor");

        // ** CUSTOM SMITHING TEMPLATE **
        addItem(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE, "Kaupen Armor Trim Smithing Template");

        // ** CUSTOM TRIM MATERIAL **
        add("trim_material.mccoursemod.bismuth", "Bismuth Material");
        add("trim_material.mccoursemod.alexandrite", "Alexandrite Material");
        add("trim_material.mccoursemod.pink", "Pink Material");

        // ** CUSTOM TRIM PATTERN **
        add("trim_pattern.mccoursemod.kaupen", "Kaupen Armor Trim");

        // ** CUSTOM Painting Variant **
        add("painting.mccoursemod.world.title", "World");
        add("painting.mccoursemod.world.author", "NanoAttack");
        add("painting.mccoursemod.shrimp.title", "Shrimp");
        add("painting.mccoursemod.shrimp.author", "NanoAttack");
        add("painting.mccoursemod.saw_them.title", "Saw Them");
        add("painting.mccoursemod.saw_them.author", "NanoAttack");

        // ** CUSTOM seeds **
        addItem(ModItems.RADISH, "Radish");
        addItem(ModItems.RADISH_SEEDS, "Radish Seeds");
        addItem(ModItems.KOHLRABI, "Kohlrabi");
        addItem(ModItems.KOHLRABI_SEEDS, "Kohlrabi Seeds");
        addItem(ModItems.CATTAIL, "Cattail");
        addItem(ModItems.CATTAIL_SEEDS, "Cattail Seeds");

        // ** CUSTOM fuels **
        addItem(ModItems.FROSTFIRE_ICE, "Frostfire Ice");
        addItem(ModItems.STARLIGHT_ASHES, "Starlight Ashes");
        addItem(ModItems.PEAT_BRICK, "Peat Brick");

        // ** CUSTOM Bush **
        addItem(ModItems.GOJI_BERRIES, "Goji Berries");

        // ** CUSTOM Mob **
        // GECKO
        addItem(ModItems.GECKO_SPAWN_EGG, "Gecko Spawn Egg");
        // RHINO
        addItem(ModItems.RHINO_SPAWN_EGG, "Rhino Spawn Egg");

        // ** CUSTOM Throwable Projectiles **
        addItem(ModItems.TOMAHAWK, "Tomahawk");
        addItem(ModItems.TORCH_BALL, "Torch Ball");
        addItem(ModItems.BOUNCY_BALLS, "Bouncy Balls");
        addItem(ModItems.BOUNCY_BALLS_PARTICLES, "Bouncy Balls Particles");
        addItem(ModItems.DICE_ITEM, "Dice Item");

        // ** CUSTOM Animated Textures **
        addItem(ModItems.RADIATION_STAFF, "Radiation Staff");

        // ** CUSTOM Fishing Rod **
        addItem(ModItems.MCCOURSE_MOD_FISHING_ROD, "Mccourse Mod Fishing Rod");

        // ** CUSTOM Shield **
        addItem(ModItems.ALEXANDRITE_SHIELD, "Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.black", "Black Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.white", "White Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.blue", "Blue Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.brown", "Brown Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.cyan", "Cyan Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.gray", "Gray Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.green", "Green Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.light_blue", "Light Blue Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.light_gray", "Light Gray Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.lime", "Light Blue Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.magenta", "Magenta Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.orange", "Orange Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.pink", "Pink Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.purple", "Purple Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.red", "Red Alexandrite Shield");
        add("item.mccoursemod.alexandrite_shield.yellow", "Yellow Alexandrite Shield");

        // ** CUSTOM Fluid **
        addItem(ModFluids.SOAP_WATER_BUCKET, "Soap Water Bucket");
        add("fluid_type.mccoursemod.soap_water_fluid", "Soap Water Fluid");

        // ** CUSTOM Boat **
        addItem(ModItems.WALNUT_BOAT, "Walnut Boat");
        addItem(ModItems.WALNUT_CHEST_BOAT, "Walnut Chest Boat");

        // ** CUSTOM block items **
        add("item.mccoursemod.bismuth_block", "Bismuth Block");
        add("item.mccoursemod.bismuth_ore", "Bismuth Ore");
        add("item.mccoursemod.bismuth_deepslate_ore", "Bismuth Deepslate Ore");
        add("item.mccoursemod.bismuth_end_ore", "Bismuth End Ore");
        add("item.mccoursemod.bismuth_nether_ore", "Bismuth Nether Ore");
        add("item.mccoursemod.alexandrite_block", "Alexandrite Block");
        add("item.mccoursemod.raw_alexandrite_block", "Alexandrite Raw Block");
        add("item.mccoursemod.alexandrite_ore", "Alexandrite Ore");
        add("item.mccoursemod.deepslate_alexandrite_ore", "Alexandrite Deepslate Ore");
        add("item.mccoursemod.end_stone_alexandrite_ore", "Alexandrite End Ore");
        add("item.mccoursemod.nether_alexandrite_ore", "Alexandrite Nether Ore");
        add("item.mccoursemod.pink_block", "Pink Block");
        add("item.mccoursemod.pink_ore", "Pink Ore");
        add("item.mccoursemod.deepslate_pink_ore", "Pink Deepslate Ore");
        add("item.mccoursemod.end_stone_pink_ore", "Pink End Ore");
        add("item.mccoursemod.nether_pink_ore", "Pink Nether Ore");
        add("item.mccoursemod.enchant", "Enchant Block");
        add("item.mccoursemod.disenchant_individual", "Disenchant Individual Block");
        add("item.mccoursemod.disenchant_grouped", "Disenchant Grouped Block");
        add("item.mccoursemod.magic", "Magic Block");
        add("item.mccoursemod.mccoursemod_elevator", "Mccourse Mod Elevator Block");
        add("item.mccoursemod.mccoursemod_generator", "Mccourse Mod Generator Block");
        add("item.mccoursemod.bismuth_button", "Bismuth Button Block");
        add("item.mccoursemod.bismuth_door", "Bismuth Door Block");
        add("item.mccoursemod.bismuth_fence", "Bismuth Fence Block");
        add("item.mccoursemod.bismuth_fence_gate", "Bismuth Fence Gate Block");
        add("item.mccoursemod.bismuth_pressure_plate", "Bismuth Pressure Plate Block");
        add("item.mccoursemod.bismuth_slab", "Bismuth Slab Block");
        add("item.mccoursemod.bismuth_stairs", "Bismuth Stairs Block");
        add("item.mccoursemod.bismuth_trapdoor", "Bismuth Trapdoor Block");
        add("item.mccoursemod.bismuth_wall", "Bismuth Wall Block");
        add("item.mccoursemod.bismuth_lamp", "Bismuth Lamp Block");
        add("item.mccoursemod.alexandrite_button", "Alexandrite Button Block");
        add("item.mccoursemod.alexandrite_door", "Alexandrite Door Block");
        add("item.mccoursemod.alexandrite_fence", "Alexandrite Fence Block");
        add("item.mccoursemod.alexandrite_fence_gate", "Alexandrite Fence Gate Block");
        add("item.mccoursemod.alexandrite_pressure_plate", "Alexandrite Pressure Plate Block");
        add("item.mccoursemod.alexandrite_slabs", "Alexandrite Slab Block");
        add("item.mccoursemod.alexandrite_stairs", "Alexandrite Stairs Block");
        add("item.mccoursemod.alexandrite_trapdoor", "Alexandrite Trapdoor Block");
        add("item.mccoursemod.alexandrite_wall", "Alexandrite Wall Block");
        add("item.mccoursemod.alexandrite_lamp", "Alexandrite Lamp Block");
        add("item.mccoursemod.radish_crop", "Radish Crop Block");
        add("item.mccoursemod.kohlrabi_crop", "Kohlrabi Crop Block");
        add("item.mccoursemod.cattail_crop", "Cattail Crop Block");
        add("item.mccoursemod.goji_berry_bush", "Goji Berry Bush Block");
        add("item.mccoursemod.bloodwood_log", "Bloodwood Log");
        add("item.mccoursemod.bloodwood_planks", "Bloodwood Planks");
        add("item.mccoursemod.bloodwood_wood", "Bloodwood Wood");
        add("item.mccoursemod.stripped_bloodwood_log", "Stripped Bloodwood Log");
        add("item.mccoursemod.stripped_bloodwood_wood", "Stripped Bloodwood Wood");
        add("item.mccoursemod.bloodwood_leaves", "Bloodwood Leaves");
        add("item.mccoursemod.bloodwood_sapling", "Bloodwood Sapling");
        add("item.mccoursemod.walnut_log", "Walnut Log");
        add("item.mccoursemod.walnut_planks", "Walnut Planks");
        add("item.mccoursemod.walnut_wood", "Walnut Wood");
        add("item.mccoursemod.stripped_walnut_log", "Stripped Walnut Log");
        add("item.mccoursemod.stripped_walnut_wood", "Stripped Walnut Wood");
        add("item.mccoursemod.walnut_leaves", "Walnut Leaves");
        add("item.mccoursemod.walnut_sapling", "Walnut Sapling");
        add("item.mccoursemod.colored_leaves", "Colored Leaves");
        add("item.mccoursemod.chair", "Chair Block");
        add("item.mccoursemod.pedestal", "Pedestal Block");
        add("item.mccoursemod.growth_chamber", "Growth Chamber Block");
        add("item.mccoursemod.forced_stained_glass", "Force Stained Glass Block");
        add("item.mccoursemod.forced_stained_glass_pane", "Force Stained Glass Pane Block");
        add("item.mccoursemod.sound", "Sound Block");
        add("item.mccoursemod.soap_water_block", "Soap Water Block");
        add("item.mccoursemod.walnut_sign", "Walnut Sign");
        add("item.mccoursemod.walnut_hanging_sign", "Walnut Hanging Sign");
        add("item.mccoursemod.walnut_wall_sign", "Walnut Wall Sign");
        add("item.mccoursemod.walnut_wall_hanging_sign", "Walnut Wall Hanging Sign");
        add("item.mccoursemod.ender_pearl_block", "Ender Pearl Block");
        add("item.mccoursemod.green_ender_pearl_block", "Green Ender Pearl Block");
        add("item.mccoursemod.black_ender_pearl_block", "Black Ender Pearl Block");
        add("item.mccoursemod.magenta_ender_pearl_block", "Magenta Ender Pearl Block");
        add("item.mccoursemod.purple_ender_pearl_block", "Purple Ender Pearl Block");
        add("item.mccoursemod.orange_ender_pearl_block", "Orange Ender Pearl Block");
        add("item.mccoursemod.pink_ender_pearl_block", "Pink Ender Pearl Block");
        add("item.mccoursemod.cyan_ender_pearl_block", "Cyan Ender Pearl Block");
        add("item.mccoursemod.brown_ender_pearl_block", "Brown Ender Pearl Block");
        add("item.mccoursemod.gray_ender_pearl_block", "Gray Ender Pearl Block");
        add("item.mccoursemod.red_ender_pearl_block", "Red Ender Pearl Block");
        add("item.mccoursemod.lime_green_ender_pearl_block", "Lime Green Ender Pearl Block");
        add("item.mccoursemod.yellow_ender_pearl_block", "Yellow Ender Pearl Block");
        add("item.mccoursemod.blue_ender_pearl_block", "Blue Ender Pearl Block");
        add("item.mccoursemod.white_ender_pearl_block", "White Ender Pearl Block");
        add("item.mccoursemod.nether_star_block", "Nether Star Block");
        add("item.mccoursemod.gunpowder_block", "Gunpowder Block");
        add("item.mccoursemod.rotten_flesh_block", "Rotten Flesh Block");
        add("item.mccoursemod.blaze_rod_block", "Blaze Rod Block");
        add("item.mccoursemod.phantom_membrane_block", "Phantom Membrane Block");
        add("item.mccoursemod.string_block", "String Block");
        add("item.mccoursemod.spider_eye_block", "Spider Eye Block");
        add("item.mccoursemod.fermented_spider_eye_block", "Fermented Spider Eye Block");
        add("item.mccoursemod.sugar_block", "Sugar Block");
        add("item.mccoursemod.sugar_cane_block", "Sugar Cane Block");
        add("item.mccoursemod.ruby_block", "Block of Ruby");
        add("item.mccoursemod.ruby_block_1", "Block of Ruby 1");
        add("item.mccoursemod.ruby_block_2", "Block of Ruby 2");
        add("item.mccoursemod.ruby_block_3", "Block of Ruby 3");
        add("item.mccoursemod.waxed_ruby_block", "Waxed Block of Ruby");
        add("item.mccoursemod.waxed_ruby_block_1", "Waxed Block of Ruby 1");
        add("item.mccoursemod.waxed_ruby_block_2", "Waxed Block of Ruby 2");
        add("item.mccoursemod.waxed_ruby_block_3", "Waxed Block of Ruby 3");
        add("item.mccoursemod.snapdragon", "Snapdragon");
        add("item.mccoursemod.potted_snapdragon", "Snapdragon Potted");
        add("item.mccoursemod.kaupen_portal", "Kaupen Portal");
        add("item.mccoursemod.kaupen_furnace", "Kaupen Furnace");
        add("item.mccoursemod.dice", "Dice");
        add("item.mccoursemod.crafting_plus", "Crafting Plus");

        // ** CUSTOM BLOCKS **
        // ** CUSTOM ores **
        // BISMUTH
        addBlock(ModBlocks.BISMUTH_BLOCK, "Bismuth Block");
        addBlock(ModBlocks.BISMUTH_ORE, "Bismuth Ore");
        addBlock(ModBlocks.BISMUTH_DEEPSLATE_ORE, "Bismuth Deepslate Ore");
        addBlock(ModBlocks.BISMUTH_END_ORE, "Bismuth End Ore");
        addBlock(ModBlocks.BISMUTH_NETHER_ORE, "Bismuth Nether Ore");
        // ALEXANDRITE
        addBlock(ModBlocks.ALEXANDRITE_BLOCK, "Alexandrite Block");
        addBlock(ModBlocks.RAW_ALEXANDRITE_BLOCK, "Alexandrite Raw Block");
        addBlock(ModBlocks.ALEXANDRITE_ORE, "Alexandrite Ore");
        addBlock(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE, "Alexandrite Deepslate Ore");
        addBlock(ModBlocks.END_STONE_ALEXANDRITE_ORE, "Alexandrite End Ore");
        addBlock(ModBlocks.NETHER_ALEXANDRITE_ORE, "Alexandrite Nether Ore");

        // PINK
        addBlock(ModBlocks.PINK_BLOCK, "Pink Block");
        addBlock(ModBlocks.PINK_ORE, "Pink Ore");
        addBlock(ModBlocks.DEEPSLATE_PINK_ORE, "Pink Deepslate Ore");
        addBlock(ModBlocks.END_STONE_PINK_ORE, "Pink End Ore");
        addBlock(ModBlocks.NETHER_PINK_ORE, "Pink Nether Ore");

        // ** CUSTOM Advanced blocks **
        addBlock(ModBlocks.ENCHANT, "§2Enchant Block");
        addBlock(ModBlocks.DISENCHANT_INDIVIDUAL, "Disenchant Individual Block");
        addBlock(ModBlocks.DISENCHANT_GROUPED, "Disenchant Grouped Block");
        addBlock(ModBlocks.MAGIC, "Magic Block");
        addBlock(ModBlocks.MCCOURSEMOD_ELEVATOR, "Mccourse Mod Elevator Block");
        addBlock(ModBlocks.MCCOURSEMOD_GENERATOR, "Mccourse Mod Generator Block");
        addBlock(ModBlocks.CRAFTING_PLUS, "Crafting Plus Block");
        addBlock(ModBlocks.SOUND, "Sound Block");

        // ** CUSTOM Block Families **
        // BISMUTH
        addBlock(ModBlocks.BISMUTH_BUTTON, "Bismuth Button Block");
        addBlock(ModBlocks.BISMUTH_DOOR, "Bismuth Door Block");
        addBlock(ModBlocks.BISMUTH_FENCE, "Bismuth Fence Block");
        addBlock(ModBlocks.BISMUTH_FENCE_GATE, "Bismuth Fence Gate Block");
        addBlock(ModBlocks.BISMUTH_PRESSURE_PLATE, "Bismuth Pressure Plate Block");
        addBlock(ModBlocks.BISMUTH_SLAB, "Bismuth Slab Block");
        addBlock(ModBlocks.BISMUTH_STAIRS, "Bismuth Stairs Block");
        addBlock(ModBlocks.BISMUTH_TRAPDOOR, "Bismuth Trapdoor Block");
        addBlock(ModBlocks.BISMUTH_WALL, "Bismuth Wall Block");
        // ALEXANDRITE
        addBlock(ModBlocks.ALEXANDRITE_BUTTON, "Alexandrite Button Block");
        addBlock(ModBlocks.ALEXANDRITE_DOOR, "Alexandrite Door Block");
        addBlock(ModBlocks.ALEXANDRITE_FENCE, "Alexandrite Fence Block");
        addBlock(ModBlocks.ALEXANDRITE_FENCE_GATE, "Alexandrite Fence Gate Block");
        addBlock(ModBlocks.ALEXANDRITE_PREASSURE_PLATE, "Alexandrite Pressure Plate Block");
        addBlock(ModBlocks.ALEXANDRITE_SLABS, "Alexandrite Slab Block");
        addBlock(ModBlocks.ALEXANDRITE_STAIRS, "Alexandrite Stairs Block");
        addBlock(ModBlocks.ALEXANDRITE_TRAPDOOR, "Alexandrite Trapdoor Block");
        addBlock(ModBlocks.ALEXANDRITE_WALL, "Alexandrite Wall Block");

        // ** CUSTOM glass block **
        addBlock(ModBlocks.FORCED_STAINED_GLASS, "Forced Stained Glass Block");
        addBlock(ModBlocks.FORCED_STAINED_GLASS_PANE, "Forced Stained Glass Pane Block");

        // ** CUSTOM Blockstate block **
        addBlock(ModBlocks.BISMUTH_LAMP, "Bismuth Lamp Block");
        addBlock(ModBlocks.ALEXANDRITE_LAMP, "Alexandrite Lamp Block");

        // ** CUSTOM Crop block **
        addBlock(ModBlocks.RADISH_CROP, "Radish Crop Block");
        addBlock(ModBlocks.KOHLRABI_CROP, "Kohlrabi Crop Block");

        // ** CUSTOM Crop block with two height **
        addBlock(ModBlocks.CATTAIL_CROP, "Cattail Crop Block");

        // ** CUSTOM Bush block **
        addBlock(ModBlocks.GOJI_BERRY_BUSH, "Goji Berry Bush Block");

        // ** CUSTOM Log block **
        // BLOODWOOD
        addBlock(ModBlocks.BLOODWOOD_LOG, "Bloodwood Log");
        addBlock(ModBlocks.BLOODWOOD_PLANKS, "Bloodwood Planks");
        addBlock(ModBlocks.BLOODWOOD_WOOD, "Bloodwood Wood");
        addBlock(ModBlocks.STRIPPED_BLOODWOOD_LOG, "Stripped Bloodwood Log");
        addBlock(ModBlocks.STRIPPED_BLOODWOOD_WOOD, "Stripped Bloodwood Wood");
        addBlock(ModBlocks.BLOODWOOD_LEAVES, "Bloodwood Leaves");
        addBlock(ModBlocks.BLOODWOOD_SAPLING, "Bloodwood Sapling");

        // WALNUT
        addBlock(ModBlocks.WALNUT_LOG, "Walnut Log");
        addBlock(ModBlocks.WALNUT_PLANKS, "Walnut Planks");
        addBlock(ModBlocks.WALNUT_WOOD, "Walnut Wood");
        addBlock(ModBlocks.STRIPPED_WALNUT_LOG, "Stripped Walnut Log");
        addBlock(ModBlocks.STRIPPED_WALNUT_WOOD, "Stripped Walnut Wood");
        addBlock(ModBlocks.WALNUT_LEAVES, "Walnut Leaves");
        addBlock(ModBlocks.WALNUT_SAPLING, "Walnut Sapling");

        // ** CUSTOM colored blocks **
        addBlock(ModBlocks.COLORED_LEAVES, "Colored Leaves");

        // ** CUSTOM Sign and Hanging sign **
        addBlock(ModBlocks.WALNUT_SIGN, "Walnut Sign");
        addBlock(ModBlocks.WALNUT_HANGING_SIGN, "Walnut Hanging Sign");
        addBlock(ModBlocks.WALNUT_WALL_SIGN, "Walnut Wall Sign");
        addBlock(ModBlocks.WALNUT_WALL_HANGING_SIGN, "Walnut Wall Hanging Sign");

        // ** CUSTOM sittable block model **
        addBlock(ModBlocks.CHAIR, "Chair Block");

        // ** CUSTOM block entity **
        addBlock(ModBlocks.PEDESTAL, "Pedestal Block");
        addBlock(ModBlocks.GROWTH_CHAMBER, "Growth Chamber Block");

        // ** CUSTOM Fluid block **
        addBlock(ModFluids.SOAP_WATER_BLOCK, "Soap Water Block");

        // ** CUSTOM Ender Pearl blocks **
        addBlock(ModBlocks.ENDER_PEARL_BLOCK, "Ender Pearl Block");
        addBlock(ModBlocks.GREEN_ENDER_PEARL_BLOCK, "Green Ender Pearl Block");
        addBlock(ModBlocks.BLACK_ENDER_PEARL_BLOCK, "Black Ender Pearl Block");
        addBlock(ModBlocks.MAGENTA_ENDER_PEARL_BLOCK, "Magenta Ender Pearl Block");
        addBlock(ModBlocks.PURPLE_ENDER_PEARL_BLOCK, "Purple Ender Pearl Block");
        addBlock(ModBlocks.ORANGE_ENDER_PEARL_BLOCK, "Orange Ender Pearl Block");
        addBlock(ModBlocks.PINK_ENDER_PEARL_BLOCK, "Pink Ender Pearl Block");
        addBlock(ModBlocks.CYAN_ENDER_PEARL_BLOCK, "Cyan Ender Pearl Block");
        addBlock(ModBlocks.BROWN_ENDER_PEARL_BLOCK, "Brown Ender Pearl Block");
        addBlock(ModBlocks.GRAY_ENDER_PEARL_BLOCK, "Gray Ender Pearl Block");
        addBlock(ModBlocks.RED_ENDER_PEARL_BLOCK, "Red Ender Pearl Block");
        addBlock(ModBlocks.LIME_GREEN_ENDER_PEARL_BLOCK, "Lime Green Ender Pearl Block");
        addBlock(ModBlocks.YELLOW_ENDER_PEARL_BLOCK, "Yellow Ender Pearl Block");
        addBlock(ModBlocks.BLUE_ENDER_PEARL_BLOCK, "Blue Ender Pearl Block");
        addBlock(ModBlocks.WHITE_ENDER_PEARL_BLOCK, "White Ender Pearl Block");

        // ** CUSTOM mob blocks **
        addBlock(ModBlocks.NETHER_STAR_BLOCK, "Nether Star Block");
        addBlock(ModBlocks.GUNPOWDER_BLOCK, "Gunpowder Block");
        addBlock(ModBlocks.ROTTEN_FLESH_BLOCK, "Rotten Flesh Block");
        addBlock(ModBlocks.BLAZE_ROD_BLOCK, "Blaze Rod Block");
        addBlock(ModBlocks.PHANTOM_MEMBRANE_BLOCK, "Phantom Membrane Block");
        addBlock(ModBlocks.STRING_BLOCK, "String Block");
        addBlock(ModBlocks.SPIDER_EYE_BLOCK, "Spider Eye Block");
        addBlock(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK, "Fermented Spider Eye Block");
        addBlock(ModBlocks.SUGAR_BLOCK, "Sugar Block");
        addBlock(ModBlocks.SUGAR_CANE_BLOCK, "Sugar Cane Block");

        // ** CUSTOM oxidizable blocks **
        addBlock(ModBlocks.RUBY_BLOCK, "Block of Ruby");
        addBlock(ModBlocks.RUBY_BLOCK_1, "Block of Ruby 1");
        addBlock(ModBlocks.RUBY_BLOCK_2, "Block of Ruby 2");
        addBlock(ModBlocks.RUBY_BLOCK_3, "Block of Ruby 3");
        addBlock(ModBlocks.WAXED_RUBY_BLOCK, "Waxed Block of Ruby");
        addBlock(ModBlocks.WAXED_RUBY_BLOCK_1, "Waxed Block of Ruby 1");
        addBlock(ModBlocks.WAXED_RUBY_BLOCK_2, "Waxed Block of Ruby 2");
        addBlock(ModBlocks.WAXED_RUBY_BLOCK_3, "Waxed Block of Ruby 3");

        // ** CUSTOM flowers and pot flowers **
        addBlock(ModBlocks.SNAPDRAGON, "Snapdragon");
        addBlock(ModBlocks.POTTED_SNAPDRAGON, "Snapdragon Potted");

        // ** CUSTOM portal **
        addBlock(ModBlocks.KAUPEN_PORTAL, "Kaupen Portal");

        // ** CUSTOM furnace **
        addBlock(ModBlocks.KAUPEN_FURNACE_BLOCK, "Kaupen Furnace");

        // ** CUSTOM block projectile **
        addBlock(ModBlocks.DICE, "Dice");

        // ** CUSTOM CREATIVE TABS **
        add("creativetab.mccoursemod.bismuth_items", "Mccourse Items");
        add("creativetab.mccoursemod.bismuth_blocks", "Mccourse Blocks");

        // ** CUSTOM Mccourse Mod Enchantment names **
        add("enchantment.mccoursemod.lightning_striker", "Lightning Striker");
        add("enchantment.mccoursemod.auto_smelt", "Auto Smelt");
        add("enchantment.mccoursemod.more_ores", "More Ores");
        add("enchantment.mccoursemod.block_fly", "Block Fly");
        add("enchantment.mccoursemod.magnet", "Magnet");
        add("enchantment.mccoursemod.rainbow", "Rainbow");
        add("enchantment.mccoursemod.immortal", "Immortal");
        add("enchantment.mccoursemod.peaceful_mobs", "Peaceful Mobs");
        add("enchantment.mccoursemod.lightstring", "Lightstring");
        add("enchantment.mccoursemod.glowing_mobs", "Glowing Mobs");
        add("enchantment.mccoursemod.magnetism", "Magnetism");
        add("enchantment.mccoursemod.xp_boost", "Xp Boost");

        // ** CUSTOM DESCRIPTIONS **
        // ** CUSTOM MUSIC DISC **
        add("item.mccoursemod.bar_brawl_music_disc.desc", "Bryan Tech - Bar Brawl (CC0)");

        // ** CUSTOM MCCOURSE MOD ENCHANTMENTS **
        add("enchantment.mccoursemod.lightning_striker.desc",
            "When applied on sword when player hits on entities appears lightning, " +
            "but player receive damage if attacked.");

        add("enchantment.mccoursemod.auto_smelt.desc",
            "When applied on pickaxe transform all items that can be roasted on furnace.");

        add("enchantment.mccoursemod.more_ores.desc",
            "When applied on pickaxe if a stone block is break has a percentage to receive ores.");

        add("enchantment.mccoursemod.block_fly.desc",
            "When applied on pickaxe if player has flying speed mining equals if player has on ground.");

        add("enchantment.mccoursemod.magnet.desc",
            "When applied on pickaxe all mined blocks automatically store on Player's inventory.");

        add("enchantment.mccoursemod.rainbow.desc",
            "When applied on pickaxe the broken ore is replaced and turned on an ore block.");

        add("enchantment.mccoursemod.immortal.desc",
            "When applied to tools, armor, etc., it prevents the loss of the item in cactus, TNT, lava, void, etc.");

        add("enchantment.mccoursemod.peaceful_mobs.desc",
            "When applied on leggings armor all entities not attack Player.");

        add("enchantment.mccoursemod.lightstring.desc", "When applied on bow increases bow loading speed.");

        add("enchantment.mccoursemod.glowing_mobs.desc",
            "When applied on helmet armor all entities are detected with X-Ray of mobs. " +
            "(Press M key to activated or disabled)");

        add("enchantment.mccoursemod.magnetism.desc",
            "When applied on leggings armor searches for items and Experience Orbs on the ground within " +
            "a radius and returns them to the Player's inventory.");

        // ** VANILLA ENCHANTMENTS **
        // MINING
        add("enchantment.minecraft.efficiency.desc",
            "When applied on axe, pickaxe, shovel or hoe increases mining speed.");
        add("enchantment.minecraft.silk_touch.desc",
            "When applied on axe, pickaxe, shovel or hoe receives the broken block.");
        add("enchantment.minecraft.fortune.desc",
            "When applied on axe, pickaxe, shovel or hoe receives bonus loot drops of ores.");

        // DURABILITY
        add("enchantment.minecraft.mending.desc",
            "When applied on tools or armor regenerates tools, equipments, etc. only player received XP.");
        add("enchantment.minecraft.unbreaking.desc",
            "When applied on tools or armor increases durability of tools, equipments, etc.");
        add("enchantment.mccoursemod.xp_boost.desc",
            "When applied to tools, armor, etc. gain more experience orb when killing mobs, mining blocks, " +
            "cooking items, walking, mining, attacking, etc.");

        // ARMOR EXCLUSIVE
        add("enchantment.minecraft.protection.desc", "When applied on armor add bonus damage reduction.");
        add("enchantment.minecraft.fire_protection.desc", "When applied on armor add bonus damage reduction of fire.");
        add("enchantment.minecraft.blast_protection.desc",
            "When applied on armor add bonus damage reduction of explosions and fireworks.");
        add("enchantment.minecraft.projectile_protection.desc",
            "When applied on armor add bonus damage reduction of projectiles.");

        // CHESTPLATE
        add("enchantment.minecraft.thorns.desc", "When applied on armor if entities attacked player receives damage.");

        // HELMET
        add("enchantment.minecraft.respiration.desc", "When applied on helmet armor extending breathing time underwater.");
        add("enchantment.minecraft.aqua_affinity.desc", "When applied on helmet armor increases underwater mining speed.");

        // LEGGINGS
        add("enchantment.minecraft.swift_sneak.desc", "When applied on leggings armor walk more quickly while sneaking.");

        // BOOTS
        add("enchantment.minecraft.feather_falling.desc",
            "When applied on boots armor reduces fall damage the player takes, but it does not affect falling speed.");
        add("enchantment.minecraft.depth_strider.desc",
            "When applied on boots armor increases underwater movement speed.");
        add("enchantment.minecraft.frost_walker.desc",
            "When applied on boots armor creates frosted ice blocks when walking over water, " +
            "and causes the wearer to be immune to damage from certain blocks such as campfires and magma blocks " +
            "when stepped on, but not working with lava.");
        add("enchantment.minecraft.soul_speed.desc",
            "When applied on boots armor walk more quickly on soul sand and soul soil blocks.");

        // SWORD
        add("enchantment.minecraft.sharpness.desc", "When applied on sword or axe increases melee damage attack on entities.");

        add("enchantment.minecraft.smite.desc",
            "When applied on sword or axe increases damage dealt to undead mobs " +
            "also Skeleton, Zombie, Wither, Phantom, Zoglin, etc.");

        add("enchantment.minecraft.bane_of_arthropods.desc",
            "When applied on sword or axe increases damage to arthropod mobs also Spiders, Bees, Silverfish, Endermites, etc.");

        add("enchantment.minecraft.knockback.desc", "When applied on sword or axe increases knockback distance of entities.");

        add("enchantment.minecraft.fire_aspect.desc", "When applied on sword or axe an entity received fire attack when hit.");

        add("enchantment.minecraft.looting.desc", "When applied on sword or axe increases amount of drop loot of entities.");

        // SWEEPING EDGE ENCHANTMENT
        add("enchantment.minecraft.sweeping.desc", "When applied on sword or axe increases sweep attack damage on entities.");

        add("enchantment.minecraft.sweeping_edge.desc", "When applied on sword or axe increases sweep attack damage on entities.");

        // BOW
        add("enchantment.minecraft.power.desc", "When applied on bow increases arrow damage.");

        add("enchantment.minecraft.punch.desc",
            "When applied on bow increases an arrow's knockback, but it not affect damage dealt of arrows.");

        add("enchantment.minecraft.flame.desc", "When applied on bow shoots flaming arrows.");

        add("enchantment.minecraft.infinity.desc",
            "When applied on bow one arrow is needed to used the enchantment that prevents regular arrows " +
            "from being consumed when slot.");

        // FISHING ROD
        add("enchantment.minecraft.luck_of_the_sea.desc",
            "When applied on fishing rod increases luck while fishing to received enchantments, armors, tools, etc.");

        add("enchantment.minecraft.lure.desc", "When applied on fishing rod decreases the wait time for a bite on the hook.");

        // TRIDENT
        add("enchantment.minecraft.loyalty.desc", "When applied on trident causing it to return to the owner once thrown.");

        add("enchantment.minecraft.impaling.desc",
            "When applied on trident deal extra damage on each hit against aquatic mobs also axolotls, " +
            "dolphins, guardians, squid, turtles, all variants of fish, etc. Except drowned is an undead mob.");

        add("enchantment.minecraft.riptide.desc",
            "When applied on trident hurls the user in the direction the user is facing, but only when they are wet.");

        add("enchantment.minecraft.channeling.desc",
            "When applied on trident produces lightning when thrown at a mob or lightning rod while a thunderstorm is occurring.");

        // CROSSBOW
        add("enchantment.minecraft.quick_charge.desc", "When applied on crossbow quickly reloading a crossbow.");

        add("enchantment.minecraft.multishot.desc",
            "When applied on crossbow shoot three arrows or firework rockets at the cost of one.");

        add("enchantment.minecraft.piercing.desc", "When applied on crossbow causes arrows to pierce through entities.");

        // CURSE
        add("enchantment.minecraft.binding_curse.desc",
            "When applied on armor the player not remove the item of inventory.");

        add("enchantment.minecraft.vanishing_curse.desc",
            "When applied on tool or armor if player killed the item disappears of inventory.");

        // MACE
        add("enchantment.minecraft.breach.desc",
            "When applied on mace ignores 15% of armor damage reduction per level.");

        add("enchantment.minecraft.density.desc",
            "When applied on mace increases 0.5 additional damage per level for each block fallen with tool.");

        add("enchantment.minecraft.wind_burst.desc",
            "When applied on mace the player into the air seven blocks per level after performing a smash attack.");

        // ** CUSTOM TOOLTIP **
        add("tooltip.mccoursemod.magic_block", "This Block is quite §9MAGICAL§r");
        add("tooltip.mccoursemod.auto_smelt.tooltip",
            "When applied on pickaxe transform all items that can be roasted on furnace.");
        add("tooltip.mccoursemod.more_ores.tooltip",
            "When applied on pickaxe if a stone block is break has a percentage to receive ores.");
        add("tooltip.mccoursemod.chisel.shift_down", "This Item can chisel Blocks into Bricks");
        add("tooltip.mccoursemod.chisel", "Press §eShift§r for more Information");
        add("tooltip.mccoursemod.radish", "Tastes really great!");
        add("tooltip.mccoursemod.metal_detector.tooltip.shift", "§eRight Click on Blocks to find Valuables!");
        add("tooltip.mccoursemod.metal_detector.tooltip", "Press §eSHIFT§r for more Information");
        add("tooltip.mccoursemod.sound", "Plays nice sounds when walking or right-clicking.");

        // ** CUSTOM player display screen messages **
        add("item.mccoursemod.metal_detector.no_valuable_values", "§4No Valuables Found!");

        // ** CUSTOM Effects and Potions **
        // FLY effect + potion
        add("effect.mccoursemod.fly", "Fly");
        add("item.minecraft.potion.effect.fly_potion", "Fly Potion");
        add("item.minecraft.splash_potion.effect.fly_potion", "Fly Splash Potion");
        add("item.minecraft.lingering_potion.effect.fly_potion", "Fly Lingering Potion");
        add("item.minecraft.tipped_arrow.effect.fly_potion", "Arrow of Fly");

        // NOTHING effect + potion
        add("effect.mccoursemod.nothing", "Nothing");
        add("item.minecraft.potion.effect.nothing_potion", "Nothing Potion");
        add("item.minecraft.splash_potion.effect.nothing_potion", "Nothing Splash Potion");
        add("item.minecraft.lingering_potion.effect.nothing_potion", "Nothing Lingering Potion");
        add("item.minecraft.tipped_arrow.effect.nothing_potion", "Arrow of Nothing");

        // HASTE potion
        add("item.minecraft.potion.effect.haste_potion", "Haste Potion");
        add("item.minecraft.splash_potion.effect.haste_potion", "Haste Splash Potion");
        add("item.minecraft.lingering_potion.effect.haste_potion", "Haste Lingering Potion");
        add("item.minecraft.tipped_arrow.effect.haste_potion", "Arrow of Haste");

        // SLIMEY potion
        add("effect.mccoursemod.slimey", "Slimey");
        add("item.minecraft.potion.effect.slimey_potion", "Slimey Potion");
        add("item.minecraft.splash_potion.effect.slimey_potion", "Slimey Splash Potion");
        add("item.minecraft.lingering_potion.effect.slimey_potion", "Slimey Lingering Potion");
        add("item.minecraft.tipped_arrow.effect.slimey_potion", "Arrow of Slimey");

        // ** CUSTOM NETWORK message **
        add("mccoursemod.networking.failed", "Mccourse Mod network failed!");

        // ** CUSTOM KEY BINDING **
        add("key.category.mccoursemod", "Mccourse Mod");
        add("key.mccoursemod_glowing_blocks", "Glowing Blocks");
        add("key.mccoursemod_glowing_mobs", "Glowing Mobs");
        add("key.mccoursemod_mccoursemod_bottle_stored", "Mccourse Mod Bottle Stored");
        add("key.mccoursemod_mccoursemod_bottle_restored", "Mccourse Mod Bottle Restored");
        add("key.mccoursemod_unlock", "Unlock");

        // ** CUSTOM mob **
        // GECKO
        add("entity.mccoursemod.gecko", "Gecko");
        add("entity.mccoursemod.blue_gecko", "Blue Gecko");
        add("entity.mccoursemod.green_gecko", "Green Gecko");
        add("entity.mccoursemod.pink_gecko", "Pink Gecko");
        add("entity.mccoursemod.brown_gecko", "Brown Gecko");
        add("entity.minecraft.gecko", "Gecko");
        add("entity.minecraft.blue_gecko", "Blue Gecko");
        add("entity.minecraft.green_gecko", "Green Gecko");
        add("entity.minecraft.pink_gecko", "Pink Gecko");
        add("entity.minecraft.brown_gecko", "Brown Gecko");
        // RHINO
        add("entity.mccoursemod.rhino", "Rhino");
        add("entity.mccoursemod.white_rhino", "White Rhino");
        add("entity.minecraft.rhino", "Rhino");
        add("entity.minecraft.white_rhino", "White Rhino");

        // ** CUSTOM Throwable Projectiles **
        add("entity.mccoursemod.tomahawk", "Tomahawk");
        add("entity.minecraft.tomahawk", "Tomahawk");

        // ** CUSTOM villager **
        add("entity.minecraft.villager.mccoursemod.kaupenger", "Kaupenger");
        add("entity.minecraft.villager.mccoursemod.soundmaster", "Soundmaster");

        // ** CUSTOM boat and chest boat**
        add("entity.minecraft.mod_chest_boat", "Chest Boat");

        // ** CUSTOM sounds **
        add("sounds.mccoursemod.chisel_use", "Chisel Use");
        add("sounds.mccoursemod.metal_detector_found_ore", "Metal Detector Found Ore");
        add("sounds.mccoursemod.magic_block_break", "Magic Block Break");
        add("sounds.mccoursemod.magic_block_step", "Magic Block Step");
        add("sounds.mccoursemod.magic_block_place", "Magic Block Place");
        add("sounds.mccoursemod.magic_block_hit", "Magic Block Hit");
        add("sounds.mccoursemod.magic_block_fall", "Magic Block Fall");
        add("sounds.mccoursemod.alexandrite_lamp_break", "Alexandrite Block Break");
        add("sounds.mccoursemod.alexandrite_lamp_step", "Alexandrite Block Step");
        add("sounds.mccoursemod.alexandrite_lamp_place", "Alexandrite Block Place");
        add("sounds.mccoursemod.alexandrite_lamp_hit", "Alexandrite Block Hit");
        add("sounds.mccoursemod.alexandrite_lamp_fall", "Alexandrite Block Fall");

        // ** CUSTOM block entity container **
        add("mccoursemod.container.crafting", "");
        add("container.inventory", "");
    }
}