package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.item.custom.LevelChargerGenericItem;
import net.karen.mccoursemod.item.custom.LevelChargerSpecifItem;
import net.karen.mccoursemod.item.custom.SpecialEffectItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
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

    // Magnet item
    public static final DeferredItem<Item> MAGNET = ITEMS.register("magnet",
           () -> new SpecialEffectItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                                   ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                   "magnet"))), ModDataComponentTypes.MAGNET.get(), 10));

    // Rainbow item
    public static final DeferredItem<Item> RAINBOW = ITEMS.register("rainbow",
           () -> new SpecialEffectItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                                   ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                   "rainbow"))), ModDataComponentTypes.RAINBOW.get(), 10));

    // Auto smelt item
    public static final DeferredItem<Item> AUTO_SMELT = ITEMS.register("auto_smelt",
           () -> new SpecialEffectItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                                   ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                   "auto_smelt"))), ModDataComponentTypes.AUTO_SMELT.get(),
                                                                   10));

    // More Ores item
    public static final DeferredItem<Item> MORE_ORES = ITEMS.register("more_ores",
           () -> new SpecialEffectItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                                                                   ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                   "more_ores"))), ModDataComponentTypes.MORE_ORES.get(),
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}