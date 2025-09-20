package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.custom.HammerTooltip;
import net.karen.mccoursemod.component.custom.ShiftTooltip;
import net.karen.mccoursemod.component.custom.ItemTooltip;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.datagen.ModEquipmentAssetProvider;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.item.custom.*;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.trim.ModTrimMaterials;
import net.karen.mccoursemod.util.ChatUtils;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ARGB;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.*;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Optional;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class ModItems {
    // Registry all custom ITEMS
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MccourseMod.MOD_ID);

    // ** CUSTOM items **
    // ** CUSTOM ore items **
    // BISMUTH
    public static final DeferredItem<Item> BISMUTH = trimMaterialItem("bismuth", ModTrimMaterials.BISMUTH);

    public static final DeferredItem<Item> RAW_BISMUTH =
           ITEMS.registerItem("raw_bismuth", Item::new, new Item.Properties());

    // ALEXANDRITE
    public static final DeferredItem<Item> ALEXANDRITE = trimMaterialItem("alexandrite", ModTrimMaterials.ALEXANDRITE);

    public static final DeferredItem<Item> RAW_ALEXANDRITE =
           ITEMS.registerItem("raw_alexandrite", Item::new, new Item.Properties());

    // PINK
    public static final DeferredItem<Item> PINK = trimMaterialItem("pink", ModTrimMaterials.PINK);

    // ** CUSTOM advanced items **
    public static final DeferredItem<Item> CHISEL =
           ITEMS.registerItem("chisel", ChiselItem::new, new Item.Properties().durability(32));

    public static final DeferredItem<Item> RESTORE =
           ITEMS.registerItem("restore", RestoreItem::new, new Item.Properties().fireResistant());

    public static final DeferredItem<Item> FARMER =
           ITEMS.registerItem("farmer", FarmerItem::new, new Item.Properties().fireResistant());

    public static final DeferredItem<Item> CATTAIL =
           ITEMS.registerItem("cattail", ModWaxingItem::new, new Item.Properties());

    // ** CUSTOM Foods **
    public static final DeferredItem<Item> RADISH =
           foodItem("radish", 3, 0.25F, MobEffects.HEALTH_BOOST, 400, 0.35F, green);

    public static final DeferredItem<Item> KOHLRABI =
           foodItem("kohlrabi", 3, 0.25F, MobEffects.SPEED, 200, 0.1F, darkGreen);

    public static final DeferredItem<Item> COFFEE =
           foodItem("coffee", 5, 0.1F, MobEffects.NIGHT_VISION, 600, 0.5F, white);

    // ** CUSTOM fuels (Custom FURNACE) **
    public static final DeferredItem<Item> FROSTFIRE_ICE = fuelItem("frostfire_ice", 800);

    public static final DeferredItem<Item> STARLIGHT_ASHES = fuelItem("starlight_ashes", 1200);

    public static final DeferredItem<Item> PEAT_BRICK = fuelItem("peat_brick", 200);

    // ** CUSTOM armors (Helmet, Chestplate, Leggings and Boots) **
    // BISMUTH
    public static final DeferredItem<Item> BISMUTH_HELMET =
           helmetArmor("bismuth_helmet", ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, 0xE85480);

    public static final DeferredItem<Item> BISMUTH_CHESTPLATE =
           chestplateArmor("bismuth_chestplate", ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, 0xE85480);

    public static final DeferredItem<Item> BISMUTH_LEGGINGS =
           leggingsArmor("bismuth_leggings", ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, 0xE85480);

    public static final DeferredItem<Item> BISMUTH_BOOTS =
           bootsArmor("bismuth_boots", ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, 0xE85480);

    // ALEXANDRITE
    public static final DeferredItem<Item> ALEXANDRITE_HELMET =
           helmetArmor("alexandrite_helmet", ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, 0x30D5C8);

    public static final DeferredItem<Item> ALEXANDRITE_CHESTPLATE =
           chestplateArmor("alexandrite_chestplate", ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, 0x30D5C8);

    public static final DeferredItem<Item> ALEXANDRITE_LEGGINGS =
           leggingsArmor("alexandrite_leggings", ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, 0x30D5C8);

    public static final DeferredItem<Item> ALEXANDRITE_BOOTS =
           bootsArmor("alexandrite_boots", ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, 0x30D5C8);

    // PINK
    public static final DeferredItem<Item> PINK_HELMET =
           helmetArmor("pink_helmet", ModArmorMaterials.PINK_ARMOR_MATERIAL, 0xF433C1);

    public static final DeferredItem<Item> PINK_CHESTPLATE =
           chestplateArmor("pink_chestplate", ModArmorMaterials.PINK_ARMOR_MATERIAL, 0xF433C1);

    public static final DeferredItem<Item> PINK_LEGGINGS =
           leggingsArmor("pink_leggings", ModArmorMaterials.PINK_ARMOR_MATERIAL, 0xF433C1);

    public static final DeferredItem<Item> PINK_BOOTS =
           bootsArmor("pink_boots", ModArmorMaterials.PINK_ARMOR_MATERIAL, 0xF433C1);

    // COPPER
    public static final DeferredItem<Item> COPPER_HELMET =
           helmetArmor("copper_helmet", ModArmorMaterials.COPPER_ARMOR_MATERIAL, 0x9C4529);

    public static final DeferredItem<Item> COPPER_CHESTPLATE =
           chestplateArmor("copper_chestplate", ModArmorMaterials.COPPER_ARMOR_MATERIAL, 0x9C4529);

    public static final DeferredItem<Item> COPPER_LEGGINGS =
           leggingsArmor("copper_leggings", ModArmorMaterials.COPPER_ARMOR_MATERIAL, 0x9C4529);

    public static final DeferredItem<Item> COPPER_BOOTS =
           bootsArmor("copper_boots", ModArmorMaterials.COPPER_ARMOR_MATERIAL, 0x9C4529);

    // LAPIS LAZULI
    public static final DeferredItem<Item> LAPIS_LAZULI_HELMET =
           helmetArmor("lapis_lazuli_helmet", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, 0x273FB2);

    public static final DeferredItem<Item> LAPIS_LAZULI_CHESTPLATE =
           chestplateArmor("lapis_lazuli_chestplate", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, 0x273FB2);

    public static final DeferredItem<Item> LAPIS_LAZULI_LEGGINGS =
           leggingsArmor("lapis_lazuli_leggings", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, 0x273FB2);

    public static final DeferredItem<Item> LAPIS_LAZULI_BOOTS =
           bootsArmor("lapis_lazuli_boots", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, 0x273FB2);

    // REDSTONE
    public static final DeferredItem<Item> REDSTONE_HELMET =
           helmetArmor("redstone_helmet", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, 0xDA304B);

    public static final DeferredItem<Item> REDSTONE_CHESTPLATE =
           chestplateArmor("redstone_chestplate", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, 0xDA304B);

    public static final DeferredItem<Item> REDSTONE_LEGGINGS =
           leggingsArmor("redstone_leggings", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, 0xDA304B);

    public static final DeferredItem<Item> REDSTONE_BOOTS =
           bootsArmor("redstone_boots", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, 0xDA304B);

    // ** CUSTOM Horse armor **
    public static final DeferredItem<Item> BISMUTH_HORSE_ARMOR =
           horseArmorItem("bismuth_horse_armor", ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, 0xE85480);

    public static final DeferredItem<Item> ALEXANDRITE_HORSE_ARMOR =
           horseArmorItem("alexandrite_horse_armor", ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, 0x30D5C8);

    // ** CUSTOM tools (SWORD, PICKAXE, SHOVEL, AXE, HOE, HAMMER, PAXEL, BOW, etc.) **
    // ** CUSTOM Bow tools **
    public static final DeferredItem<Item> KAUPEN_BOW =
           bowItem("kaupen_bow", ModTags.Items.BISMUTH_TOOL_MATERIALS, 0xE85480);

    public static final DeferredItem<Item> MINER_BOW =
           hammerBowItem("miner_bow", ModTags.Items.BISMUTH_TOOL_MATERIALS);

    public static final DeferredItem<Item> ALEXANDRITE_BOW =
           bowItem("alexandrite_bow", ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, 0x30D5C8);

    // ** CUSTOM Fishing Rod tools **
    public static final DeferredItem<Item> MCCOURSE_MOD_FISHING_ROD =
           ITEMS.registerItem("mccourse_mod_fishing_rod", properties ->
                              new FishingRodItem(properties.fireResistant()) {
                                   // DEFAULT METHOD - Appears on name item
                                   @Override
                                   public @NotNull Component getName(@NotNull ItemStack stack) {
                                       return componentTranslatable(this.getDescriptionId(), purple);
                                   }
                              });

    // ** CUSTOM Shield tools **
    public static final DeferredItem<Item> ALEXANDRITE_SHIELD =
           shieldItem("alexandrite_shield", ModTags.Items.ALEXANDRITE_TOOL_MATERIALS);

    // ** CUSTOM Paxel tools **
    public static final DeferredItem<Item> BISMUTH_PAXEL =
           paxelItem("bismuth_paxel", ModToolMaterials.BISMUTH, 1F, -2.8F,
                     ModTags.Items.BISMUTH_TOOL_MATERIALS, 0xE85480);

    public static final DeferredItem<Item> ALEXANDRITE_PAXEL =
           paxelItem("alexandrite_paxel", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                     ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, 0x30D5C8);

    public static final DeferredItem<Item> PINK_PAXEL =
           paxelItem("pink_paxel", ModToolMaterials.PINK, 1.0F, 2.0F,
                     ModTags.Items.PINK_TOOL_MATERIALS, 0xF433C1);

    public static final DeferredItem<Item> COPPER_PAXEL =
           paxelItem("copper_paxel", ModToolMaterials.COPPER, 1.0F, 2.5F,
                     ModTags.Items.COPPER_TOOL_MATERIALS, 0x9C4529);

    public static final DeferredItem<Item> DIAMOND_PAXEL =
           paxelItem("diamond_paxel", ToolMaterial.DIAMOND, 1.0F, 4.5F,
                     ItemTags.DIAMOND_TOOL_MATERIALS, 0x27B29A);

    public static final DeferredItem<Item> GOLD_PAXEL =
           paxelItem("gold_paxel", ToolMaterial.GOLD, 1.0F, 4.0F,
                     ItemTags.GOLD_TOOL_MATERIALS, 0xFFFF23);

    public static final DeferredItem<Item> IRON_PAXEL =
           paxelItem("iron_paxel", ToolMaterial.IRON, 1.0F, 3.0F,
                     ItemTags.IRON_TOOL_MATERIALS, 0x828282);

    public static final DeferredItem<Item> STONE_PAXEL =
           paxelItem("stone_paxel", ToolMaterial.STONE, 1.0F, 1.5F,
                     ItemTags.STONE_TOOL_MATERIALS, 0x636363);

    public static final DeferredItem<Item> WOODEN_PAXEL =
           paxelItem("wooden_paxel", ToolMaterial.WOOD, 1.0F, 1.0F,
                     ItemTags.WOODEN_TOOL_MATERIALS, 0x886626);

    public static final DeferredItem<Item> NETHERITE_PAXEL =
           paxelItem("netherite_paxel", ToolMaterial.NETHERITE, 1.0F, 5.0F,
                     ItemTags.NETHERITE_TOOL_MATERIALS, 0x4A2940);

    public static final DeferredItem<Item> LAPIS_LAZULI_PAXEL =
           paxelItem("lapis_lazuli_paxel", ModToolMaterials.LAPIS_LAZULI, 1.0F, 3.5F,
                     ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, 0x273FB2);

    public static final DeferredItem<Item> REDSTONE_PAXEL =
           paxelItem("redstone_paxel", ModToolMaterials.REDSTONE, 1.0F, 4.5F,
                     ModTags.Items.REDSTONE_TOOL_MATERIALS, 0xDA304B);

    // ** CUSTOM Hammer tools **
    public static final DeferredItem<Item> BISMUTH_HAMMER =
           hammerItem("bismuth_hammer", ModToolMaterials.BISMUTH, 7F, -3.5F,
                      ModTags.Items.BISMUTH_TOOL_MATERIALS, 2, ARGB.color(255, 232, 84, 128),
                      0xE85480);

    public static final DeferredItem<Item> ALEXANDRITE_HAMMER =
           hammerItem("alexandrite_hammer", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                      ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, 2, ARGB.color(255, 48, 213, 200),
                      0x30D5C8);

    public static final DeferredItem<Item> PINK_HAMMER =
           hammerItem("pink_hammer", ModToolMaterials.PINK, 2.0F, 2.0F,
                      ModTags.Items.PINK_TOOL_MATERIALS, 3, ARGB.color(255, 244, 51, 193),
                      0xF433C1);

    public static final DeferredItem<Item> COPPER_HAMMER =
           hammerItem("copper_hammer", ModToolMaterials.COPPER, 2.0F, 2.5F,
                      ModTags.Items.COPPER_TOOL_MATERIALS, 2, ARGB.color(255, 156, 69, 41),
                      0x9C4529);

    public static final DeferredItem<Item> DIAMOND_HAMMER =
           hammerItem("diamond_hammer", ToolMaterial.DIAMOND, 2.0F, 4.5F,
                      ItemTags.DIAMOND_TOOL_MATERIALS, 3, ARGB.color(255, 39, 178, 154),
                      0x27B29A);

    public static final DeferredItem<Item> GOLD_HAMMER =
           hammerItem("gold_hammer", ToolMaterial.GOLD, 2.0F, 4.0F,
                      ItemTags.GOLD_TOOL_MATERIALS, 2, ARGB.color(255, 255, 255, 35),
                      0xFFFF23);

    public static final DeferredItem<Item> IRON_HAMMER =
           hammerItem("iron_hammer", ToolMaterial.IRON, 2.0F, 3.0F,
                      ItemTags.IRON_TOOL_MATERIALS, 2, ARGB.color(255, 130, 130, 130),
                      0x828282);

    public static final DeferredItem<Item> STONE_HAMMER =
           hammerItem("stone_hammer", ToolMaterial.STONE, 2.0F, 1.5F,
                      ItemTags.STONE_TOOL_MATERIALS, 1, ARGB.color(255, 99, 99, 99),
                      0x636363);

    public static final DeferredItem<Item> WOODEN_HAMMER =
           hammerItem("wooden_hammer", ToolMaterial.WOOD, 2.0F, 1.0F,
                      ItemTags.WOODEN_TOOL_MATERIALS, 1, ARGB.color(255, 136, 102, 38),
                      0x886626);

    public static final DeferredItem<Item> NETHERITE_HAMMER =
           hammerItem("netherite_hammer", ToolMaterial.NETHERITE, 2.0F, 5.0F,
                      ItemTags.NETHERITE_TOOL_MATERIALS, 5, ARGB.color(255, 74, 41, 64),
                      0x4A2940);

    public static final DeferredItem<Item> LAPIS_LAZULI_HAMMER =
           hammerItem("lapis_lazuli_hammer", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                      ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, 4, ARGB.color(255, 39, 63, 178),
                      0x273FB2);

    public static final DeferredItem<Item> REDSTONE_HAMMER =
           hammerItem("redstone_hammer", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                      ModTags.Items.REDSTONE_TOOL_MATERIALS, 4, ARGB.color(255, 218, 48, 75),
                      0xDA304B);

    // ** CUSTOM Shovel tools **
    public static final DeferredItem<Item> BISMUTH_SHOVEL =
           shovelItem("bismuth_shovel", ModToolMaterials.BISMUTH, 1.5F, -3.0F,
                      ModTags.Items.BISMUTH_TOOL_MATERIALS, 0xE85480);

    public static final DeferredItem<Item> ALEXANDRITE_SHOVEL =
           shovelItem("alexandrite_shovel", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                      ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, 0x30D5C8);

    public static final DeferredItem<Item> PINK_SHOVEL =
           shovelItem("pink_shovel", ModToolMaterials.PINK, 2.0F, 2.0F,
                      ModTags.Items.PINK_TOOL_MATERIALS, 0xF433C1);

    public static final DeferredItem<Item> COPPER_SHOVEL =
           shovelItem("copper_shovel", ModToolMaterials.COPPER, 2.0F, 2.5F,
                      ModTags.Items.COPPER_TOOL_MATERIALS, 0x9C4529);

    public static final DeferredItem<Item> LAPIS_LAZULI_SHOVEL =
           shovelItem("lapis_lazuli_shovel", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                      ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, 0x273FB2);

    public static final DeferredItem<Item> REDSTONE_SHOVEL =
           shovelItem("redstone_shovel", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                      ModTags.Items.REDSTONE_TOOL_MATERIALS, 0xDA304B);

    // ** CUSTOM Axe tools **
    public static final DeferredItem<Item> BISMUTH_AXE =
           axeItem("bismuth_axe", ModToolMaterials.BISMUTH, 6.0F, -3.2F,
                   ModTags.Items.BISMUTH_TOOL_MATERIALS, 0xE85480);

    public static final DeferredItem<Item> ALEXANDRITE_AXE =
           axeItem("alexandrite_axe", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                   ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, 0x30D5C8);

    public static final DeferredItem<Item> PINK_AXE =
           axeItem("pink_axe", ModToolMaterials.PINK, 2.0F, 2.0F,
                   ModTags.Items.PINK_TOOL_MATERIALS, 0xF433C1);

    public static final DeferredItem<Item> COPPER_AXE =
           axeItem("copper_axe", ModToolMaterials.COPPER, 2.0F, 2.5F,
                   ModTags.Items.COPPER_TOOL_MATERIALS, 0x9C4529);

    public static final DeferredItem<Item> LAPIS_LAZULI_AXE =
           axeItem("lapis_lazuli_axe", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                   ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, 0x273FB2);

    public static final DeferredItem<Item> REDSTONE_AXE =
           axeItem("redstone_axe", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                   ModTags.Items.REDSTONE_TOOL_MATERIALS, 0xDA304B);

    // ** CUSTOM hoe **
    public static final DeferredItem<Item> BISMUTH_HOE =
           hoeItem("bismuth_hoe", ModToolMaterials.BISMUTH, 0.0F, -3.0F,
                   ModTags.Items.BISMUTH_TOOL_MATERIALS, 0xE85480);

    public static final DeferredItem<Item> ALEXANDRITE_HOE =
           hoeItem("alexandrite_hoe", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                   ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, 0x30D5C8);

    public static final DeferredItem<Item> PINK_HOE =
           hoeItem("pink_hoe", ModToolMaterials.PINK, 2.0F, 2.0F,
                   ModTags.Items.PINK_TOOL_MATERIALS, 0xF433C1);

    public static final DeferredItem<Item> COPPER_HOE =
           hoeItem("copper_hoe", ModToolMaterials.COPPER, 2.0F, 2.5F,
                   ModTags.Items.COPPER_TOOL_MATERIALS, 0x9C4529);

    public static final DeferredItem<Item> LAPIS_LAZULI_HOE =
           hoeItem("lapis_lazuli_hoe", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                   ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, 0x273FB2);

    public static final DeferredItem<Item> REDSTONE_HOE =
           hoeItem("redstone_hoe", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                   ModTags.Items.REDSTONE_TOOL_MATERIALS, 0xDA304B);

    // ** CUSTOM pickaxe **
    public static final DeferredItem<Item> BISMUTH_PICKAXE =
           pickaxeItem("bismuth_pickaxe", ModToolMaterials.BISMUTH, 1.0F, -2.8F,
                       ModTags.Items.BISMUTH_TOOL_MATERIALS, 0xE85480);

    public static final DeferredItem<Item> ALEXANDRITE_PICKAXE =
           pickaxeItem("alexandrite_pickaxe", ModToolMaterials.ALEXANDRITE, 1.0F, 2.0F,
                       ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, 0x30D5C8);

    public static final DeferredItem<Item> PINK_PICKAXE =
           pickaxeItem("pink_pickaxe", ModToolMaterials.PINK, 2.0F, 2.0F,
                       ModTags.Items.PINK_TOOL_MATERIALS, 0xF433C1);

    public static final DeferredItem<Item> COPPER_PICKAXE =
           pickaxeItem("copper_pickaxe", ModToolMaterials.COPPER, 2.0F, 2.5F,
                       ModTags.Items.COPPER_TOOL_MATERIALS, 0x9C4529);

    public static final DeferredItem<Item> LAPIS_LAZULI_PICKAXE =
           pickaxeItem("lapis_lazuli_pickaxe", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                       ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, 0x273FB2);

    public static final DeferredItem<Item> REDSTONE_PICKAXE =
           pickaxeItem("redstone_pickaxe", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                       ModTags.Items.REDSTONE_TOOL_MATERIALS, 0xDA304B);

    // ** CUSTOM sword **
    public static final DeferredItem<Item> BISMUTH_SWORD =
           swordItem("bismuth_sword", ModToolMaterials.BISMUTH, 5.0F, -2.4F,
                     ModTags.Items.BISMUTH_TOOL_MATERIALS, 0xE85480);

    public static final DeferredItem<Item> ALEXANDRITE_SWORD =
           swordItem("alexandrite_sword", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                     ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, 0x30D5C8);

    public static final DeferredItem<Item> PINK_SWORD =
           swordItem("pink_sword", ModToolMaterials.PINK, 2.0F, 2.0F,
                     ModTags.Items.PINK_TOOL_MATERIALS, 0xF433C1);

    public static final DeferredItem<Item> COPPER_SWORD =
           swordItem("copper_sword", ModToolMaterials.COPPER, 2.0F, 2.5F,
                     ModTags.Items.COPPER_TOOL_MATERIALS, 0x9C4529);

    public static final DeferredItem<Item> LAPIS_LAZULI_SWORD =
           swordItem("lapis_lazuli_sword", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                     ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, 0x273FB2);

    public static final DeferredItem<Item> REDSTONE_SWORD =
           swordItem("redstone_sword", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                     ModTags.Items.REDSTONE_TOOL_MATERIALS, 0xDA304B);

    // ** CUSTOM Elytra armor **
    public static final DeferredItem<Item> DIAMOND_ELYTRA =
           elytraArmor("diamond_elytra", 10000, ModEquipmentAssetProvider.DIAMOND_ELYTRA,
                       ItemTags.DIAMOND_TOOL_MATERIALS, MobEffects.REGENERATION, 4);

    // ** CUSTOM Smithing Template **
    public static final DeferredItem<Item> KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE =
           ITEMS.registerItem("kaupen_armor_trim_smithing_template",
                              SmithingTemplateItem::createArmorTrimTemplate,
                              new Item.Properties().rarity(Rarity.COMMON));

    // ** CUSTOM Music Disc **
    public static final DeferredItem<Item> BAR_BRAWL_MUSIC_DISC =
           ITEMS.registerItem("bar_brawl_music_disc",
                              properties -> new Item(properties.jukeboxPlayable(ModSounds.BAR_BRAWL_KEY)
                                                                         .stacksTo(1)));

    // ** CUSTOM Seeds **
    public static final DeferredItem<Item> RADISH_SEEDS =
           ITEMS.registerItem("radish_seeds", properties -> new BlockItem(ModBlocks.RADISH_CROP.get(),
                                                                                          properties));

    public static final DeferredItem<Item> KOHLRABI_SEEDS =
           ITEMS.registerItem("kohlrabi_seeds", properties -> new BlockItem(ModBlocks.KOHLRABI_CROP.get(),
                                                                                            properties));

    public static final DeferredItem<Item> CATTAIL_SEEDS =
           ITEMS.registerItem("cattail_seeds", properties -> new BlockItem(ModBlocks.CATTAIL_CROP.get(),
                                                                                           properties));

    // ** CUSTOM Bush Crop **
    public static final DeferredItem<Item> GOJI_BERRIES =
           ITEMS.registerItem("goji_berries", properties ->
                              new BlockItem(ModBlocks.GOJI_BERRY_BUSH.get(),
                                            properties.food(new FoodProperties.Builder().nutrition(2)
                                                                                        .saturationModifier(0.15F)
                                                                                        .build())));

    // ** CUSTOM Mob **
    // GECKO
    public static final DeferredItem<Item> GECKO_SPAWN_EGG =
           ITEMS.registerItem("gecko_spawn_egg", properties ->
                              new SpawnEggItem(ModEntities.GECKO.get(), properties));

    // RHINO
    public static final DeferredItem<Item> RHINO_SPAWN_EGG =
           ITEMS.registerItem("rhino_spawn_egg", properties ->
                              new SpawnEggItem(ModEntities.RHINO.get(), properties));

    // ** CUSTOM Throwable Projectiles **
    public static final DeferredItem<Item> TOMAHAWK =
           ITEMS.registerItem("tomahawk", properties ->
                              new TomahawkItem(properties.stacksTo(16)));

    public static final DeferredItem<Item> DICE_ITEM =
           ITEMS.registerItem("dice_item", DiceItem::new, new Item.Properties());

    // ** CUSTOM Animated Textures **
    public static final DeferredItem<Item> RADIATION_STAFF =
           ITEMS.registerItem("radiation_staff", RadiationStaffItem::new,
                              new Item.Properties().stacksTo(1).durability(1024));

    // ** CUSTOM Advanced Items **
    // Level Charger items
    public static final DeferredItem<Item> LEVEL_CHARGER_GENERIC_PLUS =
           levelChargerItem("level_charger_generic_plus", 1, null);

    public static final DeferredItem<Item> LEVEL_CHARGER_GENERIC_MINUS =
           levelChargerItem("level_charger_generic_minus", -1, null);

    public static final DeferredItem<Item> LEVEL_CHARGER_SPECIF_PLUS_FORTUNE =
           levelChargerItem("level_charger_specif_plus_fortune", 1, Enchantments.FORTUNE);

    public static final DeferredItem<Item> LEVEL_CHARGER_SPECIF_MINUS_FORTUNE =
           levelChargerItem("level_charger_specif_minus_fortune", -1, Enchantments.FORTUNE);

    // ** CUSTOM Compactor items **
    // Ultra Compactor
    public static final DeferredItem<Item> ULTRA_COMPACTOR =
           ITEMS.registerItem("ultra_compactor",
                              properties -> new CompactorItem(properties.stacksTo(1)
                                                                                  .fireResistant(),
                                                                        true,
                                                                        ModTags.Items.ULTRA_COMPACTOR_ITEMS,
                                                                        ModTags.Items.ULTRA_COMPACTOR_RESULT));

    // Pink Ultra Compactor
    public static final DeferredItem<Item> PINK_ULTRA_COMPACTOR =
           ITEMS.registerItem("pink_ultra_compactor",
                              properties -> new CompactorItem(properties.stacksTo(1)
                                                                                  .fireResistant(),
                                                                        false,
                                                                        ModTags.Items.PINK_ULTRA_COMPACTOR_ITEMS,
                                                                        ModTags.Items.PINK_ULTRA_COMPACTOR_RESULT));

    // Mccourse Mod Bottle item
    public static final DeferredItem<Item> MCCOURSE_MOD_BOTTLE =
           ITEMS.registerItem("mccourse_mod_bottle",
                              properties -> new MccourseModBottleItem(properties.fireResistant()
                                                                                          .stacksTo(1),
                                                                                100000, 1));

    // METAL DETECTOR item
    public static final DeferredItem<Item> METAL_DETECTOR =
           ITEMS.registerItem("metal_detector", properties ->
                              new MetalDetectorItem(properties.fireResistant()
                                                              .stacksTo(1)
                                                              .component(ModDataComponentTypes.SHIFT_TOOLTIP,
                                                                         new ShiftTooltip(
                                                                         List.of(
                                                                         "tooltip.mccoursemod.metal_detector.tooltip.shift",
                                                                         "tooltip.mccoursemod.metal_detector.tooltip"),
                                                                         List.of(ChatUtils.yellow, ChatUtils.white),
                                                                         true)),
                                                    ModTags.Blocks.METAL_DETECTOR_VALUABLES));

    // DATA TABLET item
    public static final DeferredItem<Item> DATA_TABLET =
           ITEMS.registerItem("data_tablet", DataTabletItem::new, new Item.Properties().stacksTo(1));

    // GROWTH item
    public static final DeferredItem<Item> GROWTH =
           ITEMS.registerItem("growth", GrowthItem::new, new Item.Properties().stacksTo(64)
                                                                                    .fireResistant());

    // ** CUSTOM sign and Hanging sign **
    public static final DeferredItem<Item> WALNUT_SIGN =
           ITEMS.register("walnut_sign", properties ->
                          new SignItem(ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get(),
                                       new Item.Properties().stacksTo(16)
                                                            .setId(ResourceKey.create(Registries.ITEM,
                                                                   ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                   "walnut_sign")))));

    public static final DeferredItem<Item> WALNUT_HANGING_SIGN =
           ITEMS.register("walnut_hanging_sign", properties ->
                          new HangingSignItem(ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get(),
                                              new Item.Properties().stacksTo(16)
                                                                   .setId(ResourceKey.create(Registries.ITEM,
                                                                          ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                          "walnut_hanging_sign")))));

    // TORCH BALL item
    public static final DeferredItem<Item> TORCH_BALL =
           ITEMS.registerItem("torch_ball", TorchBallItem::new,
                              new Item.Properties().fireResistant().stacksTo(1));

    public static final DeferredItem<Item> BOUNCY_BALLS =
           ITEMS.registerItem("bouncy_balls", BouncyBallsItem::new,
                              new Item.Properties().fireResistant().stacksTo(1));

    public static final DeferredItem<Item> BOUNCY_BALLS_PARTICLES =
           ITEMS.registerItem("bouncy_balls_particles", Item::new,
                              new Item.Properties().stacksTo(64).fireResistant());

    // ** CUSTOM boats **
    public static final DeferredItem<Item> WALNUT_BOAT =
           ITEMS.registerItem("walnut_boat",
                              properties -> new ModBoatItem(ModEntities.MOD_BOAT.get(),
                                                                      properties.stacksTo(1)));

    public static final DeferredItem<Item> WALNUT_CHEST_BOAT =
           ITEMS.registerItem("walnut_chest_boat",
                              properties -> new ModBoatItem(ModEntities.MOD_CHEST_BOAT.get(),
                                                                      properties.stacksTo(1)));

    // ** CUSTOM METHOD - Level Charger items **
    public static DeferredItem<Item> levelChargerItem(String name, int amount,
                                                      @Nullable ResourceKey<Enchantment> enchantment) {
        return ITEMS.registerItem(name, properties ->
                                  new LevelChargerItem(properties.fireResistant(), amount, enchantment));
    }

    // ** CUSTOM METHOD - Fuel **
    public static DeferredItem<Item> fuelItem(String name, int burnTime) {
        return ITEMS.registerItem(name, properties ->
                                  new FuelItem(properties.component(ModDataComponentTypes.ITEM_TOOLTIP,
                                                                    new ItemTooltip("§6Burn: §r" + burnTime,
                                                                                    yellow, true)),
                                               burnTime));
    }

    // ** CUSTOM METHOD - Food **
    public static DeferredItem<Item> foodItem(String name, int nutrition,
                                              float saturation, Holder<MobEffect> effect,
                                              int duration, float chance, ChatFormatting color) {
        return ITEMS.registerItem(name, properties ->
                                  new Item(properties.food(new FoodProperties.Builder()
                                                                             .nutrition(nutrition)
                                                                             .saturationModifier(saturation).build(),
                                                           Consumables.defaultFood()
                                                                      .onConsume(new ApplyStatusEffectsConsumeEffect(
                                                                                 new MobEffectInstance(effect, duration),
                                                                                 chance)).build())
                                                     .component(ModDataComponentTypes.ITEM_TOOLTIP,
                                                                new ItemTooltip("tooltip.mccoursemod." + name,
                                                                                color, true))));
    }

    // ** CUSTOM METHOD - Trim Material **
    public static DeferredItem<Item> trimMaterialItem(String name, ResourceKey<TrimMaterial> trim) {
        return ITEMS.registerItem(name, Item::new, new Item.Properties().trimMaterial(trim));
    }

    // ** CUSTOM METHOD - Paxel tool **
    public static DeferredItem<Item> paxelItem(String name, ToolMaterial material,
                                               float attackDamage, float attackSpeed,
                                               TagKey<Item> repair, int color) {
        return ITEMS.registerItem(name, properties ->
                                  new PaxelItem(material, attackDamage, attackSpeed,
                                                properties.fireResistant().repairable(repair), color));
    }

    // ** CUSTOM METHOD - Hammer tool **
    public static DeferredItem<Item> hammerItem(String name, ToolMaterial material,
                                               float attackDamage, float attackSpeed, TagKey<Item> repair,
                                               int radius, int argbColors, int textColor) {
        return ITEMS.registerItem(name, properties ->
                                  new HammerItem(material, attackDamage, attackSpeed,
                                                 properties.fireResistant().repairable(repair)
                                                                           .component(ModDataComponentTypes.HAMMER_TOOLTIP,
                                                                                      new HammerTooltip(radius, textColor)),
                                                 radius, argbColors, textColor));
    }

    // ** CUSTOM METHOD - Shovel tool **
    public static DeferredItem<Item> shovelItem(String name, ToolMaterial material,
                                                float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        return ITEMS.registerItem(name, properties ->
                                  new ShovelItem(material, attackDamage, attackSpeed,
                                                 properties.fireResistant().repairable(repair)) {
                                                      @Override
                                                      public @NotNull Component getName(@NotNull ItemStack stack) {
                                                          return componentTranslatableIntColor(this.getDescriptionId(), color);
                                                      }
                                                 });
    }

    // ** CUSTOM METHOD - Axe tool **
    public static DeferredItem<Item> axeItem(String name, ToolMaterial material,
                                             float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        return ITEMS.registerItem(name, properties ->
                                  new AxeItem(material, attackDamage, attackSpeed,
                                              properties.fireResistant().repairable(repair)) {
                                                   @Override
                                                   public @NotNull Component getName(@NotNull ItemStack stack) {
                                                       return componentTranslatableIntColor(this.getDescriptionId(), color);
                                                   }
                                              });
    }

    // ** CUSTOM METHOD - Hoe tool **
    public static DeferredItem<Item> hoeItem(String name, ToolMaterial material,
                                             float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        return ITEMS.registerItem(name, properties ->
                                  new HoeItem(material, attackDamage, attackSpeed,
                                              properties.fireResistant().repairable(repair)) {
                                                   @Override
                                                   public @NotNull Component getName(@NotNull ItemStack stack) {
                                                       return componentTranslatableIntColor(this.getDescriptionId(), color);
                                                   }
                                              });
    }

    // ** CUSTOM METHOD - Pickaxe tool **
    public static DeferredItem<Item> pickaxeItem(String name, ToolMaterial material,
                                                 float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        return ITEMS.registerItem(name, properties ->
                                  new Item(properties.pickaxe(material, attackDamage, attackSpeed)
                                                     .fireResistant().repairable(repair)) {
                                                @Override
                                                public @NotNull Component getName(@NotNull ItemStack stack) {
                                                    return componentTranslatableIntColor(this.getDescriptionId(), color);
                                                }
                                           });
    }

    // ** CUSTOM METHOD - Sword tool **
    public static DeferredItem<Item> swordItem(String name, ToolMaterial material,
                                               float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        return ITEMS.registerItem(name, properties ->
                                  new Item(properties.sword(material, attackDamage, attackSpeed)
                                                     .fireResistant().repairable(repair)) {
                                                @Override
                                                public @NotNull Component getName(@NotNull ItemStack stack) {
                                                    return componentTranslatableIntColor(this.getDescriptionId(), color);
                                                }
                                           });
    }

    // ** CUSTOM METHOD - Helmet armor **
    public static DeferredItem<Item> helmetArmor(String name,
                                                 ArmorMaterial material, int color) {
        return ITEMS.registerItem(name, properties -> new ModArmorItem(properties.humanoidArmor(material,
                                                                                 ArmorType.HELMET).fireResistant(), color));
    }

    // ** CUSTOM METHOD - Chestplate armor **
    public static DeferredItem<Item> chestplateArmor(String name,
                                                     ArmorMaterial material, int color) {
        return ITEMS.registerItem(name, properties -> new ModArmorItem(properties.humanoidArmor(material,
                                                                                 ArmorType.CHESTPLATE).fireResistant(), color));
    }

    // ** CUSTOM METHOD - Leggings armor **
    public static DeferredItem<Item> leggingsArmor(String name,
                                                   ArmorMaterial material, int color) {
        return ITEMS.registerItem(name, properties -> new ModArmorItem(properties.humanoidArmor(material,
                                                                                 ArmorType.LEGGINGS).fireResistant(), color));
    }

    // ** CUSTOM METHOD - Boots armor **
    public static DeferredItem<Item> bootsArmor(String name, ArmorMaterial material, int color) {
        return ITEMS.registerItem(name, properties -> new ModArmorItem(properties.humanoidArmor(material,
                                                                                 ArmorType.BOOTS).fireResistant(), color));
    }

    // ** CUSTOM METHOD - Elytra armor **
    public static DeferredItem<Item> elytraArmor(String name,
                                                 int durability, ResourceKey<EquipmentAsset> equipAsset,
                                                 TagKey<Item> repair, Holder<MobEffect> effectHolder, int effectAmplifier) {
        return ITEMS.registerItem(name, properties ->
                                  new ElytraPlusItem(effectHolder, effectAmplifier,
                                                     properties.fireResistant()
                                                               .durability(durability)
                                                               .rarity(Rarity.EPIC)
                                                               .component(DataComponents.GLIDER, Unit.INSTANCE)
                                                               .component(DataComponents.EQUIPPABLE,
                                                                          Equippable.builder(EquipmentSlot.CHEST)
                                                                                    .setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA)
                                                                                    .setAsset(equipAsset)
                                                                                    .setDamageOnHurt(false)
                                                                                    .build())
                                                               .repairable(repair)));
    }

    // ** CUSTOM METHOD - Bow tool **
    public static DeferredItem<Item> bowItem(String name,
                                             TagKey<Item> repair, int color) {
        return ITEMS.registerItem(name, properties ->
                                  new BowItem(properties.repairable(repair)) {
                                                   @Override
                                                   public @NotNull Component getName(@NotNull ItemStack stack) {
                                                       return componentTranslatableIntColor(this.getDescriptionId(), color);
                                                   }
                                              });
    }

    // ** CUSTOM METHOD - Hammer Bow tool **
    public static DeferredItem<Item> hammerBowItem(String name, TagKey<Item> repair) {
        return ITEMS.registerItem(name, properties ->
                                  new MinerBowItem(properties.repairable(repair)));
    }

    // ** CUSTOM METHOD - Horse armor **
    public static DeferredItem<Item> horseArmorItem(String name,
                                                    ArmorMaterial armorMaterial, int color) {
        return ITEMS.registerItem(name, properties ->
                                  new Item(properties.stacksTo(1).horseArmor(armorMaterial)) {
                                              @Override
                                              public @NotNull Component getName(@NotNull ItemStack stack) {
                                                  return componentTranslatableIntColor(this.getDescriptionId(), color);
                                              }
                                          });
    }

    // ** CUSTOM METHOD - Shield tool **
    public static DeferredItem<Item> shieldItem(String name, TagKey<Item> repair) {
        return ITEMS.registerItem(name, properties ->
                                  new ShieldItem(properties.component(DataComponents.BANNER_PATTERNS,
                                                                      BannerPatternLayers.EMPTY)
                                                           .repairable(repair)
                                                           .equippableUnswappable(EquipmentSlot.OFFHAND)
                                                           .component(DataComponents.BLOCKS_ATTACKS,
                                                                      new BlocksAttacks(0.25F,
                                                                                        1.0F,
                                                                                        List.of(new BlocksAttacks.DamageReduction(
                                                                                                90.0F,
                                                                                                Optional.empty(),
                                                                                                0.0F, 1.0F)),
                                                                      new BlocksAttacks.ItemDamageFunction(3.0F,
                                                                                                           1.0F,
                                                                                                           1.0F),
                                                                      Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                                                                      Optional.of(SoundEvents.SHIELD_BLOCK),
                                                                      Optional.of(SoundEvents.SHIELD_BREAK)))
                                                           .component(DataComponents.BREAK_SOUND,
                                                                      SoundEvents.SHIELD_BREAK)));
    }

    // CUSTOM METHOD - Registry all items on event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}