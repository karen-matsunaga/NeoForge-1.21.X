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

    // CUSTOM METHOD - Registry all items on event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}