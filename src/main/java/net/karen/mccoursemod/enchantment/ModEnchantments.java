package net.karen.mccoursemod.enchantment;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.enchantment.custom.*;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.neoforged.neoforge.common.Tags;
import java.util.List;
import java.util.Map;

public class ModEnchantments {
    // Registry all custom enchantments -> enchantment name on JSON file
    public static final ResourceKey<Enchantment> LIGHTNING_STRIKER = createTag("lightning_striker");
    public static final ResourceKey<Enchantment> AUTO_SMELT = createTag("auto_smelt");
    public static final ResourceKey<Enchantment> BLOCK_FLY = createTag("block_fly");
    public static final ResourceKey<Enchantment> MAGNET = createTag("magnet");
    public static final ResourceKey<Enchantment> IMMORTAL = createTag("immortal");
    public static final ResourceKey<Enchantment> PEACEFUL_MOBS = createTag("peaceful_mobs");
    public static final ResourceKey<Enchantment> LIGHTSTRING = createTag("lightstring");
    public static final ResourceKey<Enchantment> GLOWING_MOBS = createTag("glowing_mobs");
    public static final ResourceKey<Enchantment> MAGNETISM = createTag("magnetism");
    public static final ResourceKey<Enchantment> MORE_ORES = createTag("more_ores");
    public static final ResourceKey<Enchantment> RAINBOW = createTag("rainbow");
    public static final ResourceKey<Enchantment> XP_BOOST = createTag("xp_boost");
    public static final ResourceKey<Enchantment> UNLOCK = createTag("unlock");
    public static final ResourceKey<Enchantment> GLOWING_BLOCKS = createTag("glowing_blocks");
    public static final ResourceKey<Enchantment> MOBS_CRITICAL = createTag("mobs_critical");

    // CUSTOM METHOD - Registry all custom enchantments (JSON file)
    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<EntityType<?>> entities = context.lookup(Registries.ENTITY_TYPE);

        // Lightning Striker - Sword
        register(context, LIGHTNING_STRIKER,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                                        EnchantmentTarget.VICTIM,
                                        new LightningStrikerEnchantmentEffect(
                                        LevelBasedValue.perLevel(0.5F, 0.15F))));

        // Auto Smelt - Pickaxe
        register(context, AUTO_SMELT,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .withSpecialEffect(ModDataComponentTypes.AUTO_SMELT_ENCHANTMENT_EFFECT.get(),
                                               new AutoSmeltEnchantmentEffect(ModTags.Blocks.AUTO_SMELT_ORES)));

        // Block Fly - Pickaxe
        register(context, BLOCK_FLY,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND)));

        // Magnet - Pickaxe
        register(context, MAGNET,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                5, 1,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND)));

        // Immortal - Armor and Tools
        register(context, IMMORTAL,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                                                                5, 1,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.ANY)));

        // Peaceful Mobs - Leggings
        register(context, PEACEFUL_MOBS,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                                                                5, 1,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.LEGS)));

        // Magnetism - Leggings
        register(context, MAGNETISM,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.LEGS)));

        // Lightning - Bow and Crossbow
        register(context, LIGHTSTRING,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND)));

        // Glowing Mobs - Head
        register(context, GLOWING_MOBS,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                                                                5, 1,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.HEAD)));

        // More Ores - Pickaxe
        register(context, MORE_ORES,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .withSpecialEffect(ModDataComponentTypes.MORE_ORES_ENCHANTMENT_EFFECT.get(),
                                               new MoreOresEnchantmentEffect(
                                               List.of(ModTags.Blocks.MORE_ORES_ONE_DROPS,
                                                       ModTags.Blocks.MORE_ORES_TWO_DROPS,
                                                       ModTags.Blocks.MORE_ORES_THREE_DROPS,
                                                       ModTags.Blocks.MORE_ORES_FOUR_DROPS,
                                                       ModTags.Blocks.MORE_ORES_FIVE_DROPS,
                                                       ModTags.Blocks.MORE_ORES_SIX_DROPS,
                                                       ModTags.Blocks.MORE_ORES_ALL_DROPS),
                                               blocks.getOrThrow(ModTags.Blocks.MORE_ORES_BREAK_BLOCK),
                                               List.of(0.1F, 0.05F))));

        // Rainbow - Pickaxe
        register(context, RAINBOW,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .withSpecialEffect(ModDataComponentTypes.RAINBOW_ENCHANTMENT_EFFECT.get(),
                                               new RainbowEnchantmentEffect(
                                               Map.ofEntries(Map.entry(Blocks.COAL_BLOCK, Tags.Blocks.ORES_COAL),
                                               Map.entry(Blocks.COPPER_BLOCK, Tags.Blocks.ORES_COPPER),
                                               Map.entry(Blocks.DIAMOND_BLOCK, Tags.Blocks.ORES_DIAMOND),
                                               Map.entry(Blocks.EMERALD_BLOCK, Tags.Blocks.ORES_EMERALD),
                                               Map.entry(Blocks.GOLD_BLOCK, Tags.Blocks.ORES_GOLD),
                                               Map.entry(Blocks.IRON_BLOCK, Tags.Blocks.ORES_IRON),
                                               Map.entry(Blocks.LAPIS_BLOCK, Tags.Blocks.ORES_LAPIS),
                                               Map.entry(Blocks.REDSTONE_BLOCK, Tags.Blocks.ORES_REDSTONE),
                                               Map.entry(Blocks.NETHERITE_BLOCK, Tags.Blocks.ORES_NETHERITE_SCRAP)))));

        // Xp Boost - Armor and Tools
        register(context, XP_BOOST,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                                                                5, 2,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.ANY)));

        // Unlock - Armor and Tools
        register(context, UNLOCK,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                                                                5, 1,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.ANY)));

        // Glowing Blocks - Helmet
        register(context, GLOWING_BLOCKS,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                                                                5, 1,
                                                                Enchantment.dynamicCost(5, 7),
                                                                Enchantment.dynamicCost(25, 7),
                                                                2, EquipmentSlotGroup.HEAD)));

        // Mobs Critical - Sword
        register(context, MOBS_CRITICAL,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                                                5, 5,
                                                                Enchantment.dynamicCost(5, 8),
                                                                Enchantment.dynamicCost(25, 8),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .withEffect(EnchantmentEffectComponents.DAMAGE,
                                        new AddValue(LevelBasedValue.perLevel(10F)),
                                        LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.entity()
                                                       .entityType(EntityTypePredicate.of(entities,
                                                                                          ModTags.Entities.ALL_ENTITIES)))));
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