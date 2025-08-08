package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput packOutput) {
        super(packOutput, MccourseMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // CUSTOM ITEMS
        addItem(ModItems.BISMUTH, "Bismuth");
        addItem(ModItems.RAW_BISMUTH, "Raw Bismuth");
        addItem(ModItems.MAGNET, "Magnet");
        addItem(ModItems.RAINBOW, "Rainbow");
        addItem(ModItems.AUTO_SMELT, "Auto Smelt");
        addItem(ModItems.MORE_ORES, "More Ores");
        addItem(ModItems.LEVEL_CHARGER_GENERIC_PLUS, "Level Charger Generic Plus");
        addItem(ModItems.LEVEL_CHARGER_GENERIC_MINUS, "Level Charger Generic Minus");
        addItem(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE, "Level Charger Specif Minus Fortune");
        addItem(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE, "Level Charger Specif Plus Fortune");

        // CUSTOM Foods
        addItem(ModItems.COFFEE, "Coffee");

        // CUSTOM Tools
        addItem(ModItems.BISMUTH_HAMMER, "Bismuth Hammer");
        addItem(ModItems.BISMUTH_PAXEL, "Bismuth Paxel");
        addItem(ModItems.BISMUTH_SWORD, "Bismuth Sword");
        addItem(ModItems.BISMUTH_PICKAXE, "Bismuth Pickaxe");
        addItem(ModItems.BISMUTH_SHOVEL, "Bismuth Shovel");
        addItem(ModItems.BISMUTH_AXE, "Bismuth Axe");
        addItem(ModItems.BISMUTH_HOE, "Bismuth Hoe");

        // CUSTOM Armors
        addItem(ModItems.BISMUTH_HELMET, "Bismuth Helmet");
        addItem(ModItems.BISMUTH_CHESTPLATE, "Bismuth Chestplate");
        addItem(ModItems.BISMUTH_LEGGINGS, "Bismuth Leggings");
        addItem(ModItems.BISMUTH_BOOTS, "Bismuth Boots");

        // CUSTOM block item
        add("item.mccoursemod.enchant", "Enchant Block");
        add("item.mccoursemod.bismuth_block", "Bismuth Block");
        add("item.mccoursemod.bismuth_ore", "Bismuth Ore");
        add("item.mccoursemod.bismuth_deepslate_ore", "Bismuth Deepslate Ore");
        add("item.mccoursemod.disenchant_individual", "Disenchant Individual Block");
        add("item.mccoursemod.disenchant_grouped", "Disenchant Grouped Block");
        add("item.mccoursemod.magic", "Magic Block");
        add("item.mccoursemod.mccoursemod_elevator", "Mccourse Mod Elevator Block");

        // CUSTOM BLOCKS
        addBlock(ModBlocks.BISMUTH_BLOCK, "Bismuth Block");
        addBlock(ModBlocks.BISMUTH_ORE, "Bismuth Ore");
        addBlock(ModBlocks.BISMUTH_DEEPSLATE_ORE, "Bismuth Deepslate Ore");
        addBlock(ModBlocks.ENCHANT, "§2Enchant Block");
        addBlock(ModBlocks.DISENCHANT_INDIVIDUAL, "Disenchant Individual Block");
        addBlock(ModBlocks.DISENCHANT_GROUPED, "Disenchant Grouped Block");
        addBlock(ModBlocks.MAGIC, "Magic Block");
        addBlock(ModBlocks.MCCOURSEMOD_ELEVATOR, "Mccourse Mod Elevator Block");

        // CUSTOM CREATIVE TABS
        add("creativetab.mccoursemod.bismuth_items", "Mccourse Items");
        add("creativetab.mccoursemod.bismuth_blocks", "Mccourse Blocks");

        // CUSTOM Enchantment
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

        // MOD
        add("enchantment.mccoursemod.lightning_striker.desc",
            "When applied on sword when player hits on entities appears lightning, but player receive damage if attacked.");

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
            "When applied on helmet armor all entities are detected with X-Ray of mobs. (Press M key to activated or disabled)");

        // MINING
        add("enchantment.minecraft.efficiency.desc", "When applied on axe, pickaxe, shovel or hoe increases mining speed.");
        add("enchantment.minecraft.silk_touch.desc", "When applied on axe, pickaxe, shovel or hoe receives the broken block.");
        add("enchantment.minecraft.fortune.desc", "When applied on axe, pickaxe, shovel or hoe receives bonus loot drops of ores.");

        // DURABILITY
        add("enchantment.minecraft.mending.desc",
                "When applied on tools or armor regenerates tools, equipments, etc. only player received XP.");
        add("enchantment.minecraft.unbreaking.desc", "When applied on tools or armor increases durability of tools, equipments, etc.");

        // ARMOR EXCLUSIVE
        add("enchantment.minecraft.protection.desc", "When applied on armor add bonus damage reduction.");
        add("enchantment.minecraft.fire_protection.desc", "When applied on armor add bonus damage reduction of fire.");
        add("enchantment.minecraft.blast_protection.desc",
                "When applied on armor add bonus damage reduction of explosions and fireworks.");
        add("enchantment.minecraft.projectile_protection.desc", "When applied on armor add bonus damage reduction of projectiles.");

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
        add("enchantment.minecraft.depth_strider.desc", "When applied on boots armor increases underwater movement speed.");
        add("enchantment.minecraft.frost_walker.desc",
                "When applied on boots armor creates frosted ice blocks when walking over water, " +
                        "and causes the wearer to be immune to damage from certain blocks such as campfires and magma blocks " +
                        "when stepped on, but not working with lava.");
        add("enchantment.minecraft.soul_speed.desc",
                "When applied on boots armor walk more quickly on soul sand and soul soil blocks.");

        // SWORD
        add("enchantment.minecraft.sharpness.desc", "When applied on sword or axe increases melee damage attack on entities.");
        add("enchantment.minecraft.smite.desc",
                "When applied on sword or axe increases damage dealt to undead mobs also Skeleton, Zombie, Wither, Phantom, Zoglin, etc.");
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

        // CUSTOM TOOLTIP
        add("tooltip.mccoursemod.magic_block.tooltip", "This Block is quite §9MAGICAL§r");
        add("tooltip.mccoursemod.auto_smelt.tooltip", "When applied on pickaxe transform all items that can be roasted on furnace.");

        // CUSTOM Effects and Potions
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

        // NETWORK message
        add("mccoursemod.networking.failed", "Mccourse Mod network failed!");

        // KEY BINDING
        add("key.category.mccoursemod", "Mccourse Mod");
        add("key.mccoursemod_glowing_blocks", "Glowing Blocks");
        add("key.mccoursemod_glowing_mobs", "Glowing Mobs");
        add("key.mccoursemod_mccoursemod_bottle_stored_ten_levels", "Mccourse Mod Bottle Stored Ten Levels");
        add("key.mccoursemod_mccoursemod_bottle_restored_ten_levels", "Mccourse Mod Bottle Restored Ten Levels");
        add("key.mccoursemod_mccoursemod_bottle_stored_hundred_levels", "Mccourse Mod Bottle Stored Hundred Levels");
        add("key.mccoursemod_mccoursemod_bottle_restored_hundred_levels", "Mccourse Mod Bottle Restored Hundred Levels");
        add("key.mccoursemod_unlock", "Unlock");
        add("key.mccoursemod_light", "Light");
    }
}