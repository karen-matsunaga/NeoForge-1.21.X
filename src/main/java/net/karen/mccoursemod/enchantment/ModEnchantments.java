package net.karen.mccoursemod.enchantment;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.minecraft.world.item.enchantment.effects.MultiplyValue;

public class ModEnchantments {
    // Registry all custom enchantments -> enchantment name on JSON file
    public static final ResourceKey<Enchantment> LIGHTNING_STRIKER = createTag("lightning_striker");
    public static final ResourceKey<Enchantment> AUTO_SMELT = createTag("auto_smelt");
    public static final ResourceKey<Enchantment> MORE_ORES = createTag("more_ores");
    public static final ResourceKey<Enchantment> BLOCK_FLY = createTag("block_fly");

    // CUSTOM METHOD - Registry all custom enchantments (JSON file)
    public static void bootstrap(BootstrapContext<Enchantment> context) {
        // var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

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

        register(context, AUTO_SMELT,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .withEffect(EnchantmentEffectComponents.BLOCK_EXPERIENCE,
                                        new AddValue(LevelBasedValue.perLevel(1.0F, 2.0F))));

        register(context, MORE_ORES,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .withEffect(EnchantmentEffectComponents.BLOCK_EXPERIENCE,
                                        new MultiplyValue(LevelBasedValue.perLevel(1.0F, 2.0F))));

        register(context, BLOCK_FLY,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .withEffect(EnchantmentEffectComponents.ATTRIBUTES,
                                        new EnchantmentAttributeEffect(
                                        ResourceLocation.withDefaultNamespace("enchantment.efficiency"),
                                        Attributes.MINING_EFFICIENCY, new LevelBasedValue.LevelsSquared(1.0F),
                                        AttributeModifier.Operation.ADD_VALUE)));
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