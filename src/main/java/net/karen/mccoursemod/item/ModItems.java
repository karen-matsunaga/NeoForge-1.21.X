package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.item.custom.*;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.trim.ModTrimMaterials;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.purple;

public class ModItems {
    // Registry all custom ITEMS
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MccourseMod.MOD_ID);

    // ** CUSTOM items **
    // ** CUSTOM ore items **
    // BISMUTH
    public static final DeferredItem<Item> BISMUTH =
           ITEMS.registerItem("bismuth", Item::new, new Item.Properties().trimMaterial(ModTrimMaterials.BISMUTH));

    public static final DeferredItem<Item> RAW_BISMUTH =
           ITEMS.registerItem("raw_bismuth", Item::new, new Item.Properties());

    // ALEXANDRITE
    public static final DeferredItem<Item> ALEXANDRITE =
           ITEMS.registerItem("alexandrite", Item::new, new Item.Properties().trimMaterial(ModTrimMaterials.ALEXANDRITE));

    public static final DeferredItem<Item> RAW_ALEXANDRITE =
           ITEMS.registerItem("raw_alexandrite", Item::new, new Item.Properties());

    // PINK
    public static final DeferredItem<Item> PINK =
           ITEMS.registerItem("pink", Item::new, new Item.Properties().trimMaterial(ModTrimMaterials.PINK));

    // ** CUSTOM advanced items **
    public static final DeferredItem<Item> CHISEL =
           ITEMS.registerItem("chisel", ChiselItem::new, new Item.Properties().durability(32));

    // ** CUSTOM foods **
    public static final DeferredItem<Item> RADISH = ITEMS.registerItem("radish",
           (properties) -> new Item(properties.food(new FoodProperties.Builder()
                                                                                .nutrition(3).saturationModifier(0.25F).build(),
                                                              Consumables.defaultFood().onConsume(
                                                              new ApplyStatusEffectsConsumeEffect(
                                                              new MobEffectInstance(MobEffects.HEALTH_BOOST, 400),
                                                              0.35F)).build())) {
               @Override
               public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                           @NotNull TooltipDisplay display, @NotNull Consumer<Component> consumer,
                                           @NotNull TooltipFlag flag) {
                   consumer.accept(Component.translatable("tooltip.mccoursemod.radish"));
                   super.appendHoverText(stack, context, display, consumer, flag);
               }
           });

    public static final DeferredItem<Item> KOHLRABI = ITEMS.registerItem("kohlrabi",
           (properties) -> new Item(properties.food(new FoodProperties.Builder().nutrition(3)
                                                                                          .saturationModifier(0.25F).build(),
                                                              Consumables.defaultFood().onConsume(
                                                              new ApplyStatusEffectsConsumeEffect(
                                                              new MobEffectInstance(MobEffects.SPEED, 200),
                                                              0.1F)).build())));

    public static final DeferredItem<Item> CATTAIL =
           ITEMS.registerItem("cattail", ModWaxingItem::new, new Item.Properties());

    // ** CUSTOM fuels **
    public static final DeferredItem<Item> FROSTFIRE_ICE = ITEMS.registerItem("frostfire_ice",
           (properties) -> new FuelItem(properties, 800));

    public static final DeferredItem<Item> STARLIGHT_ASHES = ITEMS.registerItem("starlight_ashes", Item::new);

    public static final DeferredItem<Item> PEAT_BRICK =
           ITEMS.registerItem("peat_brick", (properties) -> new FuelItem(properties, 200));

    // ** CUSTOM tools (Sword, Pickaxe, Shovel, Axe, Hoe, Hammer, Paxel, Bow, etc.) **
    // BISMUTH
    public static final DeferredItem<Item> BISMUTH_SWORD = ITEMS.registerItem("bismuth_sword",
           (properties) -> new Item(properties.sword(ModToolMaterials.BISMUTH, 5.0F, -2.4F)
                                                        .fireResistant()
                                                        .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> BISMUTH_PICKAXE = ITEMS.registerItem("bismuth_pickaxe",
           (properties) -> new Item(properties.pickaxe(ModToolMaterials.BISMUTH, 1.0F, -2.8F)
                                                        .fireResistant()
                                                        .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> BISMUTH_SHOVEL = ITEMS.registerItem("bismuth_shovel",
           (properties) -> new ShovelItem(ModToolMaterials.BISMUTH, 1.5F, -3.0F,
                                                    properties.fireResistant()
                                                              .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> BISMUTH_AXE = ITEMS.registerItem("bismuth_axe",
           (properties) -> new AxeItem(ModToolMaterials.BISMUTH,6.0F, -3.2F,
                                                 properties.fireResistant()
                                                           .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> BISMUTH_HOE = ITEMS.registerItem("bismuth_hoe",
           (properties) -> new HoeItem(ModToolMaterials.BISMUTH, 0.0F, -3.0F,
                                                 properties.fireResistant()
                                                           .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> BISMUTH_HAMMER = ITEMS.registerItem("bismuth_hammer",
           (properties) -> new HammerItem(ModToolMaterials.BISMUTH, 7F, -3.5F,
                                                    properties.fireResistant()
                                                              .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> BISMUTH_PAXEL = ITEMS.registerItem("bismuth_paxel",
           (properties) -> new PaxelItem(ModToolMaterials.BISMUTH, 1F, -2.8F,
                                                   properties.fireResistant()
                                                             .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> KAUPEN_BOW = ITEMS.registerItem("kaupen_bow",
           (properties) -> new BowItem(properties.durability(500)
                                                           .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> BISMUTH_HORSE_ARMOR = ITEMS.registerItem("bismuth_horse_armor",
           (properties) -> new Item(properties.stacksTo(1)
                                                        .horseArmor(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL)));

    // ** CUSTOM Fishing Rod **
    public static final DeferredItem<Item> MCCOURSE_MOD_FISHING_ROD =
           ITEMS.registerItem("mccourse_mod_fishing_rod",
                   (properties) -> new FishingRodItem(properties.fireResistant()) {
                       @Override
                       public @NotNull Component getName(@NotNull ItemStack stack) { // Appears on name item
                           return super.getName(stack).copy().withStyle(purple);
                       }
                   });

    // ALEXANDRITE
    public static final DeferredItem<Item> ALEXANDRITE_SWORD = ITEMS.registerItem("alexandrite_sword",
           (properties) -> new Item(properties.sword(ModToolMaterials.ALEXANDRITE,
                                                               2.0F, 3.0F)
                                                        .durability(2304).fireResistant()
                                                        .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> ALEXANDRITE_PICKAXE = ITEMS.registerItem("alexandrite_pickaxe",
           (properties) -> new Item(properties.pickaxe(ModToolMaterials.ALEXANDRITE,
                                                                 1.0F, 2.0F)
                                                        .durability(2304).fireResistant()
                                                        .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> ALEXANDRITE_SHOVEL = ITEMS.registerItem("alexandrite_shovel",
           (properties) -> new ShovelItem(ModToolMaterials.ALEXANDRITE, 2, 3,
                                                    properties.durability(2304).fireResistant()
                                                              .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> ALEXANDRITE_AXE = ITEMS.registerItem("alexandrite_axe",
           (properties) -> new AxeItem(ModToolMaterials.ALEXANDRITE, 2, 3,
                                                 properties.durability(2304).fireResistant()
                                                           .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> ALEXANDRITE_HOE = ITEMS.registerItem("alexandrite_hoe",
           (properties) -> new HoeItem(ModToolMaterials.ALEXANDRITE, 2, 3,
                                                 properties.durability(2304).fireResistant()
                                                           .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> ALEXANDRITE_HAMMER = ITEMS.registerItem("alexandrite_hammer",
           (properties) -> new HammerItem(ModToolMaterials.ALEXANDRITE, 2, 3,
                                                    properties.durability(2304).fireResistant()
                                                              .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    public static final DeferredItem<Item> ALEXANDRITE_PAXEL = ITEMS.registerItem("alexandrite_paxel",
           (properties) -> new PaxelItem(ModToolMaterials.ALEXANDRITE, 2, 3,
                                                   properties.durability(2304).fireResistant()
                                                             .repairable(ModTags.Items.BISMUTH_TOOL_MATERIALS)));

    // ** CUSTOM armors (Helmet, Chestplate, Leggings and Boots) **
    // BISMUTH
    public static final DeferredItem<Item> BISMUTH_HELMET = ITEMS.registerItem("bismuth_helmet",
           (properties) -> new ModArmorItem(properties.humanoidArmor(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL,
                                                                               ArmorType.HELMET)));

    public static final DeferredItem<Item> BISMUTH_CHESTPLATE = ITEMS.registerItem("bismuth_chestplate",
           (properties) -> new ModArmorItem(properties.humanoidArmor(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL,
                                                                               ArmorType.CHESTPLATE)));

    public static final DeferredItem<Item> BISMUTH_LEGGINGS = ITEMS.registerItem("bismuth_leggings",
           (properties) -> new ModArmorItem(properties.humanoidArmor(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL,
                                                                               ArmorType.LEGGINGS)));

    public static final DeferredItem<Item> BISMUTH_BOOTS = ITEMS.registerItem("bismuth_boots",
           (properties) -> new ModArmorItem(properties.humanoidArmor(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL,
                                                                               ArmorType.BOOTS)));

    // ALEXANDRITE
    public static final DeferredItem<Item> ALEXANDRITE_HELMET = ITEMS.registerItem("alexandrite_helmet",
           (properties) -> new ModArmorItem(properties.humanoidArmor(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL,
                                                                               ArmorType.HELMET)));

    public static final DeferredItem<Item> ALEXANDRITE_CHESTPLATE = ITEMS.registerItem("alexandrite_chestplate",
           (properties) -> new ModArmorItem(properties.humanoidArmor(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL,
                                                                               ArmorType.CHESTPLATE)));

    public static final DeferredItem<Item> ALEXANDRITE_LEGGINGS = ITEMS.registerItem("alexandrite_leggings",
           (properties) -> new ModArmorItem(properties.humanoidArmor(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL,
                                                                               ArmorType.LEGGINGS)));

    public static final DeferredItem<Item> ALEXANDRITE_BOOTS = ITEMS.registerItem("alexandrite_boots",
           (properties) -> new ModArmorItem(properties.humanoidArmor(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL,
                                                                               ArmorType.BOOTS)));

    // PINK
    public static final DeferredItem<Item> PINK_HELMET =
           helmetArmor("pink_helmet", ModArmorMaterials.PINK_ARMOR_MATERIAL);

    public static final DeferredItem<Item> PINK_CHESTPLATE =
           chestplateArmor("pink_chestplate", ModArmorMaterials.PINK_ARMOR_MATERIAL);

    public static final DeferredItem<Item> PINK_LEGGINGS =
           leggingsArmor("pink_leggings", ModArmorMaterials.PINK_ARMOR_MATERIAL);

    public static final DeferredItem<Item> PINK_BOOTS =
           bootsArmor("pink_boots", ModArmorMaterials.PINK_ARMOR_MATERIAL);

    // COPPER
    public static final DeferredItem<Item> COPPER_HELMET =
           helmetArmor("copper_helmet", ModArmorMaterials.COPPER_ARMOR_MATERIAL);

    public static final DeferredItem<Item> COPPER_CHESTPLATE =
           chestplateArmor("copper_chestplate", ModArmorMaterials.COPPER_ARMOR_MATERIAL);

    public static final DeferredItem<Item> COPPER_LEGGINGS =
           leggingsArmor("copper_leggings", ModArmorMaterials.COPPER_ARMOR_MATERIAL);

    public static final DeferredItem<Item> COPPER_BOOTS =
           bootsArmor("copper_boots", ModArmorMaterials.COPPER_ARMOR_MATERIAL);

    // LAPIS LAZULI
    public static final DeferredItem<Item> LAPIS_LAZULI_HELMET =
           helmetArmor("lapis_lazuli_helmet", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL);

    public static final DeferredItem<Item> LAPIS_LAZULI_CHESTPLATE =
           chestplateArmor("lapis_lazuli_chestplate", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL);

    public static final DeferredItem<Item> LAPIS_LAZULI_LEGGINGS =
           leggingsArmor("lapis_lazuli_leggings", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL);

    public static final DeferredItem<Item> LAPIS_LAZULI_BOOTS =
           bootsArmor("lapis_lazuli_boots", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL);

    // REDSTONE
    public static final DeferredItem<Item> REDSTONE_HELMET =
           helmetArmor("redstone_helmet", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL);

    public static final DeferredItem<Item> REDSTONE_CHESTPLATE =
           chestplateArmor("redstone_chestplate", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL);

    public static final DeferredItem<Item> REDSTONE_LEGGINGS =
           leggingsArmor("redstone_leggings", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL);

    public static final DeferredItem<Item> REDSTONE_BOOTS =
           bootsArmor("redstone_boots", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL);

    public static final DeferredItem<Item> ALEXANDRITE_BOW = ITEMS.registerItem("alexandrite_bow",
           (properties) -> new BowItem(properties.durability(2304)
                                                           .repairable(ModTags.Items.ALEXANDRITE_TOOL_MATERIALS)));

    public static final DeferredItem<Item> ALEXANDRITE_HORSE_ARMOR =
           ITEMS.registerItem("alexandrite_horse_armor",
           (properties) -> new Item(properties.stacksTo(1)
                                                        .horseArmor(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL)));

    public static final DeferredItem<Item> ALEXANDRITE_SHIELD =
           ITEMS.registerItem("alexandrite_shield", (properties) ->
           new ShieldItem(properties.durability(2304)
                                    .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                                    .repairable(ItemTags.WOODEN_TOOL_MATERIALS)
                                    .equippableUnswappable(EquipmentSlot.OFFHAND)
                                    .component(DataComponents.BLOCKS_ATTACKS,
                                               new BlocksAttacks(0.25F, 1.0F,
                                               List.of(new BlocksAttacks.DamageReduction(90.0F,
                                                       Optional.empty(), 0.0F, 1.0F)),
                                                       new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                                       Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                                                       Optional.of(SoundEvents.SHIELD_BLOCK),
                                                       Optional.of(SoundEvents.SHIELD_BREAK)))
                                    .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)));

    // ** CUSTOM Paxel tools **
    public static final DeferredItem<Item> PINK_PAXEL =
           paxelItem("pink_paxel", ModToolMaterials.PINK,
                     1.0F, 2.0F, ModTags.Items.PINK_TOOL_MATERIALS);

    public static final DeferredItem<Item> COPPER_PAXEL =
           paxelItem("copper_paxel", ModToolMaterials.COPPER,
                     1.0F, 2.5F, ModTags.Items.COPPER_TOOL_MATERIALS);

    public static final DeferredItem<Item> DIAMOND_PAXEL =
           paxelItem("diamond_paxel", ToolMaterial.DIAMOND,
                     1.0F, 4.5F, ItemTags.DIAMOND_TOOL_MATERIALS);

    public static final DeferredItem<Item> GOLD_PAXEL =
           paxelItem("gold_paxel", ToolMaterial.GOLD,
                     1.0F, 4.0F, ItemTags.GOLD_TOOL_MATERIALS);

    public static final DeferredItem<Item> IRON_PAXEL =
           paxelItem("iron_paxel", ToolMaterial.IRON, 1.0F,
                     3.0F, ItemTags.IRON_TOOL_MATERIALS);

    public static final DeferredItem<Item> STONE_PAXEL =
           paxelItem("stone_paxel", ToolMaterial.STONE,
                     1.0F, 1.5F, ItemTags.STONE_TOOL_MATERIALS);

    public static final DeferredItem<Item> WOODEN_PAXEL =
           paxelItem("wooden_paxel", ToolMaterial.WOOD,
                     1.0F, 1.0F, ItemTags.WOODEN_TOOL_MATERIALS);

    public static final DeferredItem<Item> NETHERITE_PAXEL =
           paxelItem("netherite_paxel", ToolMaterial.NETHERITE,
                     1.0F, 5.0F, ItemTags.NETHERITE_TOOL_MATERIALS);

    public static final DeferredItem<Item> LAPIS_LAZULI_PAXEL =
           paxelItem("lapis_lazuli_paxel", ModToolMaterials.LAPIS_LAZULI,
                     1.0F, 3.5F, ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS);

    public static final DeferredItem<Item> REDSTONE_PAXEL =
           paxelItem("redstone_paxel", ModToolMaterials.REDSTONE,
                     1.0F, 4.5F, ModTags.Items.REDSTONE_TOOL_MATERIALS);

    // ** CUSTOM Hammer tools **
    public static final DeferredItem<Item> PINK_HAMMER =
           hammerItem("pink_hammer", ModToolMaterials.PINK,
                      2.0F, 2.0F, ModTags.Items.PINK_TOOL_MATERIALS);

    public static final DeferredItem<Item> COPPER_HAMMER =
           hammerItem("copper_hammer", ModToolMaterials.COPPER,
                      2.0F, 2.5F, ModTags.Items.COPPER_TOOL_MATERIALS);

    public static final DeferredItem<Item> DIAMOND_HAMMER =
           hammerItem("diamond_hammer", ToolMaterial.DIAMOND,
                      2.0F, 4.5F, ItemTags.DIAMOND_TOOL_MATERIALS);

    public static final DeferredItem<Item> GOLD_HAMMER =
           hammerItem("gold_hammer", ToolMaterial.GOLD,
                      2.0F, 4.0F, ItemTags.GOLD_TOOL_MATERIALS);

    public static final DeferredItem<Item> IRON_HAMMER =
           hammerItem("iron_hammer", ToolMaterial.IRON,
                      2.0F, 3.0F, ItemTags.IRON_TOOL_MATERIALS);

    public static final DeferredItem<Item> STONE_HAMMER =
           hammerItem("stone_hammer", ToolMaterial.STONE,
                      2.0F, 1.5F, ItemTags.STONE_TOOL_MATERIALS);

    public static final DeferredItem<Item> WOODEN_HAMMER =
           hammerItem("wooden_hammer", ToolMaterial.WOOD,
                      2.0F, 1.0F, ItemTags.WOODEN_TOOL_MATERIALS);

    public static final DeferredItem<Item> NETHERITE_HAMMER =
           hammerItem("netherite_hammer", ToolMaterial.NETHERITE,
                      2.0F, 5.0F, ItemTags.NETHERITE_TOOL_MATERIALS);

    public static final DeferredItem<Item> LAPIS_LAZULI_HAMMER =
           hammerItem("lapis_lazuli_hammer", ModToolMaterials.LAPIS_LAZULI,
                      2.0F, 3.5F, ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS);

    public static final DeferredItem<Item> REDSTONE_HAMMER =
           hammerItem("redstone_hammer", ModToolMaterials.REDSTONE,
                      2.0F, 4.5F, ModTags.Items.REDSTONE_TOOL_MATERIALS);

    // ** CUSTOM Shovel tools **
    public static final DeferredItem<Item> PINK_SHOVEL =
           shovelItem("pink_shovel", ModToolMaterials.PINK,
                      2.0F, 2.0F, ModTags.Items.PINK_TOOL_MATERIALS);

    public static final DeferredItem<Item> COPPER_SHOVEL =
           shovelItem("copper_shovel", ModToolMaterials.COPPER,
                      2.0F, 2.5F, ModTags.Items.COPPER_TOOL_MATERIALS);

    public static final DeferredItem<Item> LAPIS_LAZULI_SHOVEL =
           shovelItem("lapis_lazuli_shovel", ModToolMaterials.LAPIS_LAZULI,
                      2.0F, 3.5F, ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS);

    public static final DeferredItem<Item> REDSTONE_SHOVEL =
           shovelItem("redstone_shovel", ModToolMaterials.REDSTONE,
                      2.0F, 4.5F, ModTags.Items.REDSTONE_TOOL_MATERIALS);

    // ** CUSTOM Axe tools **
    public static final DeferredItem<Item> PINK_AXE =
           axeItem("pink_axe", ModToolMaterials.PINK,
                   2.0F, 2.0F, ModTags.Items.PINK_TOOL_MATERIALS);

    public static final DeferredItem<Item> COPPER_AXE =
           axeItem("copper_axe", ModToolMaterials.COPPER,
                   2.0F, 2.5F, ModTags.Items.COPPER_TOOL_MATERIALS);

    public static final DeferredItem<Item> LAPIS_LAZULI_AXE =
           axeItem("lapis_lazuli_axe", ModToolMaterials.LAPIS_LAZULI,
                   2.0F, 3.5F, ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS);

    public static final DeferredItem<Item> REDSTONE_AXE =
           axeItem("redstone_axe", ModToolMaterials.REDSTONE,
                   2.0F, 4.5F, ModTags.Items.REDSTONE_TOOL_MATERIALS);

    // ** CUSTOM hoe **
    public static final DeferredItem<Item> PINK_HOE =
           hoeItem("pink_hoe", ModToolMaterials.PINK,
                   2.0F, 2.0F, ModTags.Items.PINK_TOOL_MATERIALS);

    public static final DeferredItem<Item> COPPER_HOE =
           hoeItem("copper_hoe", ModToolMaterials.COPPER,
                   2.0F, 2.5F, ModTags.Items.COPPER_TOOL_MATERIALS);

    public static final DeferredItem<Item> LAPIS_LAZULI_HOE =
           hoeItem("lapis_lazuli_hoe", ModToolMaterials.LAPIS_LAZULI,
                   2.0F, 3.5F, ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS);

    public static final DeferredItem<Item> REDSTONE_HOE =
           hoeItem("redstone_hoe", ModToolMaterials.REDSTONE,
                   2.0F, 4.5F, ModTags.Items.REDSTONE_TOOL_MATERIALS);

    // ** CUSTOM pickaxe **
    public static final DeferredItem<Item> PINK_PICKAXE =
           pickaxeItem("pink_pickaxe", ModToolMaterials.PINK,
                       2.0F, 2.0F, ModTags.Items.PINK_TOOL_MATERIALS);

    public static final DeferredItem<Item> COPPER_PICKAXE =
           pickaxeItem("copper_pickaxe", ModToolMaterials.COPPER,
                       2.0F, 2.5F, ModTags.Items.COPPER_TOOL_MATERIALS);

    public static final DeferredItem<Item> LAPIS_LAZULI_PICKAXE =
           pickaxeItem("lapis_lazuli_pickaxe", ModToolMaterials.LAPIS_LAZULI,
                       2.0F, 3.5F, ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS);

    public static final DeferredItem<Item> REDSTONE_PICKAXE =
           pickaxeItem("redstone_pickaxe", ModToolMaterials.REDSTONE,
                       2.0F, 4.5F, ModTags.Items.REDSTONE_TOOL_MATERIALS);

    // ** CUSTOM sword **
    public static final DeferredItem<Item> PINK_SWORD =
           swordItem("pink_sword", ModToolMaterials.PINK,
                     2.0F, 2.0F, ModTags.Items.PINK_TOOL_MATERIALS);

    public static final DeferredItem<Item> COPPER_SWORD =
           swordItem("copper_sword", ModToolMaterials.COPPER,
                     2.0F, 2.5F, ModTags.Items.COPPER_TOOL_MATERIALS);

    public static final DeferredItem<Item> LAPIS_LAZULI_SWORD =
           swordItem("lapis_lazuli_sword", ModToolMaterials.LAPIS_LAZULI,
                     2.0F, 3.5F, ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS);

    public static final DeferredItem<Item> REDSTONE_SWORD =
           swordItem("redstone_sword", ModToolMaterials.REDSTONE,
                     2.0F, 4.5F, ModTags.Items.REDSTONE_TOOL_MATERIALS);

    // ** CUSTOM Smithing Template **
    public static final DeferredItem<Item> KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE =
           ITEMS.registerItem("kaupen_armor_trim_smithing_template",
                              SmithingTemplateItem::createArmorTrimTemplate, (new Item.Properties()).rarity(Rarity.COMMON));

    // ** CUSTOM Music Disc **
    public static final DeferredItem<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.registerItem("bar_brawl_music_disc",
           (properties) -> new Item(properties.jukeboxPlayable(ModSounds.BAR_BRAWL_KEY).stacksTo(1)));

    // ** CUSTOM Seeds **
    public static final DeferredItem<Item> RADISH_SEEDS = ITEMS.registerItem("radish_seeds",
           (properties) -> new BlockItem(ModBlocks.RADISH_CROP.get(), properties));

    public static final DeferredItem<Item> KOHLRABI_SEEDS = ITEMS.registerItem("kohlrabi_seeds",
           (properties) -> new BlockItem(ModBlocks.KOHLRABI_CROP.get(), properties));

    public static final DeferredItem<Item> CATTAIL_SEEDS = ITEMS.registerItem("cattail_seeds",
           (properties) -> new BlockItem(ModBlocks.CATTAIL_CROP.get(), properties));

    // ** CUSTOM Bush Crop **
    public static final DeferredItem<Item> GOJI_BERRIES = ITEMS.registerItem("goji_berries",
           (properties) -> new BlockItem(ModBlocks.GOJI_BERRY_BUSH.get(),
                                                   properties.food(new FoodProperties.Builder().nutrition(2)
                                                                                               .saturationModifier(0.15F).build())));

    // ** CUSTOM Mob **
    // GECKO
    public static final DeferredItem<Item> GECKO_SPAWN_EGG = ITEMS.registerItem("gecko_spawn_egg",
           (properties) -> new SpawnEggItem(ModEntities.GECKO.get(), properties));

    // RHINO
    public static final DeferredItem<Item> RHINO_SPAWN_EGG = ITEMS.registerItem("rhino_spawn_egg",
           (properties) -> new SpawnEggItem(ModEntities.RHINO.get(), properties));

    // ** CUSTOM Throwable Projectiles **
    public static final DeferredItem<Item> TOMAHAWK = ITEMS.registerItem("tomahawk",
           (properties) -> new TomahawkItem(properties.stacksTo(16)));

    // ** CUSTOM Animated Textures **
    public static final DeferredItem<Item> RADIATION_STAFF = ITEMS.registerItem("radiation_staff",
           (properties) -> new Item(properties.stacksTo(1)));

    // ** CUSTOM Advanced Items **
    // Auto smelt item
    public static final DeferredItem<Item> AUTO_SMELT = ITEMS.registerItem("auto_smelt",
           (properties) -> new SpecialEffectItem(properties, ModDataComponentTypes.AUTO_SMELT.get(), 10));

    // Level Charger items
    public static final DeferredItem<Item> LEVEL_CHARGER_GENERIC_PLUS =
           ITEMS.registerItem("level_charger_generic_plus",
           (properties) -> new LevelChargerGenericItem(properties.fireResistant(), 1));

    public static final DeferredItem<Item> LEVEL_CHARGER_GENERIC_MINUS =
           ITEMS.registerItem("level_charger_generic_minus",
           (properties) -> new LevelChargerGenericItem(properties.fireResistant(), -1));

    public static final DeferredItem<Item> LEVEL_CHARGER_SPECIF_PLUS_FORTUNE =
           ITEMS.registerItem("level_charger_specif_plus_fortune",
           (properties) -> new LevelChargerSpecifItem(properties.fireResistant(), 1, Enchantments.FORTUNE));

    public static final DeferredItem<Item> LEVEL_CHARGER_SPECIF_MINUS_FORTUNE =
           ITEMS.registerItem("level_charger_specif_minus_fortune",
           (properties) -> new LevelChargerSpecifItem(properties.fireResistant(), -1, Enchantments.FORTUNE));

    // ** CUSTOM Foods **
    // Coffee item food
    public static final DeferredItem<Item> COFFEE = ITEMS.registerItem("coffee",
           (properties) -> new Item(properties.food(new FoodProperties.Builder().nutrition(3)
                                                                                          .saturationModifier(0.25F).build())
                                                        .component(DataComponents.CONSUMABLE,
                                                                   Consumable.builder().consumeSeconds(2F)
                                                                             .animation(ItemUseAnimation.EAT)
                                                                             .sound(SoundEvents.GENERIC_EAT)
                                                                             .hasConsumeParticles(false)
                                                                             .onConsume(new ApplyStatusEffectsConsumeEffect(
                                                                                        new MobEffectInstance(
                                                                                        MobEffects.HEALTH_BOOST,
                                                                                        600, 0), 0.35F))
                                                                             .build())));

    // Mccourse Mod Bottle item
    public static final DeferredItem<Item> MCCOURSE_MOD_BOTTLE = ITEMS.registerItem("mccourse_mod_bottle",
           (properties) -> new MccourseModBottleItem(properties.fireResistant().stacksTo(1),
                                                               100000, 1));

    // METAL DETECTOR item
    public static final DeferredItem<Item> METAL_DETECTOR =
           ITEMS.registerItem("metal_detector",
           (properties) -> new MetalDetectorItem(properties.fireResistant().stacksTo(1),
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
           ITEMS.register("walnut_sign",
           () -> new SignItem(ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get(),
                              new Item.Properties().stacksTo(16)
                                                   .setId(ResourceKey.create(Registries.ITEM,
                                                          ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                          "walnut_sign")))));

    public static final DeferredItem<Item> WALNUT_HANGING_SIGN =
           ITEMS.register("walnut_hanging_sign",
           () -> new HangingSignItem(ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get(),
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
           (properties) -> new ModBoatItem(ModEntities.MOD_BOAT.get(), properties.stacksTo(1)));

    public static final DeferredItem<Item> WALNUT_CHEST_BOAT =
           ITEMS.registerItem("walnut_chest_boat",
           (properties) -> new ModBoatItem(ModEntities.MOD_CHEST_BOAT.get(), properties.stacksTo(1)));


    // ** CUSTOM METHOD - Paxel tool **
    public static DeferredItem<Item> paxelItem(String name, ToolMaterial material,
                                               float attackDamage, float attackSpeed,
                                               TagKey<Item> repair) {
        return ITEMS.registerItem(name, (properties) ->
               new PaxelItem(material, attackDamage, attackSpeed, properties.fireResistant().repairable(repair)));
    }

    // ** CUSTOM METHOD - Hammer tool **
    public static DeferredItem<Item> hammerItem(String name, ToolMaterial material,
                                               float attackDamage, float attackSpeed,
                                               TagKey<Item> repair) {
        return ITEMS.registerItem(name, (properties) ->
               new HammerItem(material, attackDamage, attackSpeed, properties.fireResistant().repairable(repair)));
    }

    // ** CUSTOM METHOD - Shovel tool **
    public static DeferredItem<Item> shovelItem(String name, ToolMaterial material,
                                                float attackDamage, float attackSpeed,
                                                TagKey<Item> repair) {
        return ITEMS.registerItem(name, (properties) ->
               new ShovelItem(material, attackDamage, attackSpeed, properties.fireResistant().repairable(repair)));
    }

    // ** CUSTOM METHOD - Axe tool **
    public static DeferredItem<Item> axeItem(String name, ToolMaterial material,
                                             float attackDamage, float attackSpeed,
                                             TagKey<Item> repair) {
        return ITEMS.registerItem(name, (properties) ->
               new AxeItem(material, attackDamage, attackSpeed, properties.fireResistant().repairable(repair)));
    }

    // ** CUSTOM METHOD - Hoe tool **
    public static DeferredItem<Item> hoeItem(String name, ToolMaterial material,
                                             float attackDamage, float attackSpeed,
                                             TagKey<Item> repair) {
        return ITEMS.registerItem(name, (properties) ->
               new HoeItem(material, attackDamage, attackSpeed, properties.fireResistant().repairable(repair)));
    }

    // ** CUSTOM METHOD - Pickaxe tool **
    public static DeferredItem<Item> pickaxeItem(String name, ToolMaterial material,
                                                 float attackDamage, float attackSpeed,
                                                 TagKey<Item> repair) {
        return ITEMS.registerItem(name, (properties) ->
               new Item(properties.pickaxe(material, attackDamage, attackSpeed).fireResistant().repairable(repair)));
    }

    // ** CUSTOM METHOD - Sword tool **
    public static DeferredItem<Item> swordItem(String name, ToolMaterial material,
                                               float attackDamage, float attackSpeed,
                                               TagKey<Item> repair) {
        return ITEMS.registerItem(name, (properties) ->
               new Item(properties.sword(material, attackDamage, attackSpeed).fireResistant().repairable(repair)));
    }

    // ** CUSTOM METHOD - Helmet armor **
    public static DeferredItem<Item> helmetArmor(String name,
                                                 ArmorMaterial material) {
        return ITEMS.registerItem(name, (properties) -> new ModArmorItem(properties.humanoidArmor(material,
                                                                                   ArmorType.HELMET).fireResistant()));
    }

    // ** CUSTOM METHOD - Chestplate armor **
    public static DeferredItem<Item> chestplateArmor(String name,
                                                     ArmorMaterial material) {
        return ITEMS.registerItem(name, (properties) -> new ModArmorItem(properties.humanoidArmor(material,
                                                                                   ArmorType.CHESTPLATE).fireResistant()));
    }

    // ** CUSTOM METHOD - Leggings armor **
    public static DeferredItem<Item> leggingsArmor(String name,
                                                   ArmorMaterial material) {
        return ITEMS.registerItem(name, (properties) -> new ModArmorItem(properties.humanoidArmor(material,
                                                                                   ArmorType.LEGGINGS).fireResistant()));
    }

    // ** CUSTOM METHOD - Boots armor **
    public static DeferredItem<Item> bootsArmor(String name,
                                                ArmorMaterial material) {
        return ITEMS.registerItem(name, (properties) -> new ModArmorItem(properties.humanoidArmor(material,
                                                                                   ArmorType.BOOTS).fireResistant()));
    }

    // CUSTOM METHOD - Registry all items on event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}