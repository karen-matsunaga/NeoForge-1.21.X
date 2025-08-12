package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.item.custom.*;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MccourseMod.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                       ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                       "bismuth")))));

    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                       ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                       "raw_bismuth")))));

    // Auto smelt item
    public static final DeferredItem<Item> AUTO_SMELT = ITEMS.register("auto_smelt",
           () -> new SpecialEffectItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                                   ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                   "auto_smelt"))), ModDataComponentTypes.AUTO_SMELT.get(),
                                                                   10));

    // Level Charger
    public static final DeferredItem<Item> LEVEL_CHARGER_GENERIC_PLUS =
            ITEMS.register("level_charger_generic_plus",
            () -> new LevelChargerGenericItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                                          ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                          "level_charger_generic_plus")))
                                                                   .fireResistant(), 1));

    public static final DeferredItem<Item> LEVEL_CHARGER_GENERIC_MINUS =
            ITEMS.register("level_charger_generic_minus",
            () -> new LevelChargerGenericItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                                          ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                          "level_charger_generic_minus")))
                                                                   .fireResistant(), -1));

    public static final DeferredItem<Item> LEVEL_CHARGER_SPECIF_PLUS_FORTUNE =
            ITEMS.register("level_charger_specif_plus_fortune",
            () -> new LevelChargerSpecifItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                         "level_charger_specif_plus_fortune")))
                                                                  .fireResistant(), 1, Enchantments.FORTUNE));

    public static final DeferredItem<Item> LEVEL_CHARGER_SPECIF_MINUS_FORTUNE =
            ITEMS.register("level_charger_specif_minus_fortune",
            () -> new LevelChargerSpecifItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                         "level_charger_specif_minus_fortune")))
                                                                  .fireResistant(), -1, Enchantments.FORTUNE));

    // Custom tools
    // CUSTOM Sword
    public static final DeferredItem<Item> BISMUTH_SWORD = ITEMS.register("bismuth_sword",
           () -> new Item(new Item.Properties().sword(ModToolMaterials.BISMUTH, 3, -2.4f)
                                  .fireResistant().setId(ResourceKey.create(Registries.ITEM,
                                                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                         "bismuth_sword")))
                                                  .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    // CUSTOM Pickaxe
    public static final DeferredItem<Item> BISMUTH_PICKAXE = ITEMS.register("bismuth_pickaxe",
           () -> new Item(new Item.Properties().pickaxe(ModToolMaterials.BISMUTH, 1, -2.8f)
                                  .fireResistant().setId(ResourceKey.create(Registries.ITEM,
                                                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                         "bismuth_pickaxe")))
                                                  .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    // CUSTOM Shovel
    public static final DeferredItem<Item> BISMUTH_SHOVEL = ITEMS.register("bismuth_shovel",
           () -> new ShovelItem(ModToolMaterials.BISMUTH, 1.5f, -3.0f,
                 new Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM,
                                                             ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                             "bismuth_shovel")))
                                                      .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    // CUSTOM Axe
    public static final DeferredItem<Item> BISMUTH_AXE = ITEMS.register("bismuth_axe",
           () -> new AxeItem(ModToolMaterials.BISMUTH,6, -3.2f,
                    new Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM,
                                                                ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                "bismuth_axe")))
                                                         .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    // CUSTOM Hoe
    public static final DeferredItem<Item> BISMUTH_HOE = ITEMS.register("bismuth_hoe",
           () -> new HoeItem(ModToolMaterials.BISMUTH, 0, -3.0f,
                 new Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM,
                                                             ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                             "bismuth_hoe")))
                                                      .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    // CUSTOM Hammer
    public static final DeferredItem<Item> BISMUTH_HAMMER = ITEMS.register("bismuth_hammer",
           () -> new HammerItem(ModToolMaterials.BISMUTH, 1F, -3.3F,
                 new Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM,
                                                             ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                             "bismuth_hammer")))
                                                      .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    // CUSTOM Paxel
    public static final DeferredItem<Item> BISMUTH_PAXEL = ITEMS.register("bismuth_paxel",
            () -> new PaxelItem(ModToolMaterials.BISMUTH, 1F, -2.8F,
                  new Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM,
                                                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                              "bismuth_paxel")))
                                                       .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    // Custom armors
    public static final DeferredItem<Item> BISMUTH_HELMET = ITEMS.register("bismuth_helmet",
           () -> new ModArmorItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                              "bismuth_helmet")))
                                               .humanoidArmor(ModArmorMaterials.BISMUTH, ArmorType.HELMET)));

    public static final DeferredItem<Item> BISMUTH_CHESTPLATE = ITEMS.register("bismuth_chestplate",
           () -> new ModArmorItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                              "bismuth_chestplate")))
                                               .humanoidArmor(ModArmorMaterials.BISMUTH, ArmorType.CHESTPLATE)));

    public static final DeferredItem<Item> BISMUTH_LEGGINGS = ITEMS.register("bismuth_leggings",
           () -> new ModArmorItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                              "bismuth_leggings")))
                                               .humanoidArmor(ModArmorMaterials.BISMUTH, ArmorType.LEGGINGS)));

    public static final DeferredItem<Item> BISMUTH_BOOTS = ITEMS.register("bismuth_boots",
           () -> new ModArmorItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                              "bismuth_boots")))
                                               .humanoidArmor(ModArmorMaterials.BISMUTH, ArmorType.BOOTS)));

    // Custom foods
    public static final DeferredItem<Item> COFFEE = ITEMS.register("coffee",
           () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                      ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                      "coffee")))
                                               .food(new FoodProperties.Builder().nutrition(3)
                                                                                 .saturationModifier(0.25f).build())
                                               .component(DataComponents.CONSUMABLE,
                                                          Consumable.builder().consumeSeconds(2f)
                                                                              .animation(ItemUseAnimation.EAT)
                                                                              .sound(SoundEvents.GENERIC_EAT)
                                                                              .hasConsumeParticles(false)
                                                                              .onConsume(new ApplyStatusEffectsConsumeEffect(
                                                                                         new MobEffectInstance(
                                                                                         MobEffects.HEALTH_BOOST,
                                                                                         600, 0), 0.35f))
                                                                              .build())));

    // Custom advanced items
    public static final DeferredItem<Item> MCCOURSE_MOD_BOTTLE = ITEMS.register("mccourse_mod_bottle",
           () -> new MccourseModBottleItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                                       ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                       "mccourse_mod_bottle")))
                                                                .fireResistant()
                                                                .stacksTo(1),
                                                                100000, 1));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}