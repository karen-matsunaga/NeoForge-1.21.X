package net.karen.mccoursemod.enchantment;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.karen.mccoursemod.enchantment.custom.MoreOresEnchantmentEffect;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;

public class ModEnchantments {
    // Registry all custom enchantments -> enchantment name on JSON file
    public static final ResourceKey<Enchantment> LIGHTNING_STRIKER = createTag("lightning_striker");
    public static final ResourceKey<Enchantment> AUTO_SMELT = createTag("auto_smelt");
    public static final ResourceKey<Enchantment> MORE_ORES = createTag("more_ores");
    public static final ResourceKey<Enchantment> BLOCK_FLY = createTag("block_fly");
    public static final ResourceKey<Enchantment> MAGNET = createTag("magnet");
    public static final ResourceKey<Enchantment> RAINBOW = createTag("rainbow");
    public static final ResourceKey<Enchantment> IMMORTAL = createTag("immortal");
    public static final ResourceKey<Enchantment> PEACEFUL_MOBS = createTag("peaceful_mobs");
    public static final ResourceKey<Enchantment> LIGHTSTRING = createTag("lightstring");
    public static final ResourceKey<Enchantment> GLOWING_MOBS = createTag("glowing_mobs");
    public static final ResourceKey<Enchantment> MAGNETISM = createTag("magnetism");
    public static final ResourceKey<Enchantment> MORE_ORES_ENCHANTMENT_EFFECT = createTag("more_ores_enchantment_effect");

    // CUSTOM METHOD - Registry all custom enchantments (JSON file)
    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);
        var blocks = context.lookup(Registries.BLOCK);

        // Lightning Striker - Sword tool
        register(context, LIGHTNING_STRIKER,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM,
                                        new LightningStrikerEnchantmentEffect(
                                        LevelBasedValue.perLevel(0.5F, 0.15F))));

        // Auto Smelt - Pickaxe tool
        register(context, AUTO_SMELT,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND)));

        // More Ores - Pickaxe tool
        register(context, MORE_ORES,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND)));

        // Block Fly - Pickaxe tool
        register(context, BLOCK_FLY,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND)));

        // Magnet - Pickaxe tool
        register(context, MAGNET,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND)));

        // Rainbow - Pickaxe tool
        register(context, RAINBOW,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND)));

        // Immortal - All tools
        register(context, IMMORTAL,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.ANY)));

        // Peaceful Mobs - Legging armor
        register(context, PEACEFUL_MOBS,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.LEGS)));

        // Magnetism - Legging armor
        register(context, MAGNETISM,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.LEGS)));

        // Lightning - Bow and Crossbow tools
        register(context, LIGHTSTRING,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND)));

        // Glowing Mobs - Head armor
        register(context, GLOWING_MOBS,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.HEAD)));

        // More Ores Enchantment Effect
        register(context, MORE_ORES_ENCHANTMENT_EFFECT,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .withSpecialEffect(ModDataComponentTypes.MORE_ORES_ENCHANTMENT_EFFECT.get(),
                                               new MoreOresEnchantmentEffect(ModTags.Blocks.MORE_ORES_ALL_DROPS,
                                                                             blocks.getOrThrow(ModTags.Blocks.MORE_ORES_BREAK_BLOCK),
                                                                             0.1F)));
    }

    // CUSTOM METHOD - Registry all custom enchantments -> DATA GEN
    private static void register(BootstrapContext<Enchantment> registry,
                                 ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }

    // CUSTOM METHOD - Registry all custom enchantment resource keys
    private static ResourceKey<Enchantment> createTag(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }
}