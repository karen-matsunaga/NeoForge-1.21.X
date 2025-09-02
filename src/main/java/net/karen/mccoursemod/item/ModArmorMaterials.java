package net.karen.mccoursemod.item;

import com.google.common.collect.Maps;
import net.karen.mccoursemod.datagen.ModEquipmentAssetProvider;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import java.util.Map;

public class ModArmorMaterials {
    // Registry all custom ARMOR MATERIALS
    // BISMUTH
    public static final ArmorMaterial BISMUTH_ARMOR_MATERIAL =
           armorMaterial(50, 20, SoundEvents.ARMOR_EQUIP_NETHERITE,
                         0.0F, 0.0F, ModTags.Items.REPAIRS_BISMUTH_ARMOR,
                         ModEquipmentAssetProvider.BISMUTH);

    // ALEXANDRITE
    public static final ArmorMaterial ALEXANDRITE_ARMOR_MATERIAL =
           armorMaterial(25, 15, SoundEvents.ARMOR_EQUIP_IRON,
                         3.0F, 0.1F, ModTags.Items.REPAIRS_ALEXANDRITE_ARMOR,
                         ModEquipmentAssetProvider.ALEXANDRITE);

    // PINK
    public static final ArmorMaterial PINK_ARMOR_MATERIAL =
           armorMaterial(30, 25, SoundEvents.ARMOR_EQUIP_DIAMOND,
                         4.0F, 0.5F, ModTags.Items.REPAIRS_PINK_ARMOR,
                         ModEquipmentAssetProvider.PINK);

    // COPPER
    public static final ArmorMaterial COPPER_ARMOR_MATERIAL =
           armorMaterial(40, 35, SoundEvents.ARMOR_EQUIP_GENERIC,
                         7.0F, 0.6F, ModTags.Items.REPAIRS_COPPER_ARMOR,
                         ModEquipmentAssetProvider.COPPER);

    // REDSTONE
    public static final ArmorMaterial REDSTONE_ARMOR_MATERIAL =
           armorMaterial(20, 25, SoundEvents.ARMOR_EQUIP_IRON,
                         6.0F, 0.8F, ModTags.Items.REPAIRS_REDSTONE_ARMOR,
                         ModEquipmentAssetProvider.REDSTONE);

    // LAPIS LAZULI
    public static final ArmorMaterial LAPIS_LAZULI_ARMOR_MATERIAL =
           armorMaterial(15, 45, SoundEvents.ARMOR_EQUIP_IRON,
                         7.0F, 0.9F, ModTags.Items.REPAIRS_LAPIS_LAZULI_ARMOR,
                         ModEquipmentAssetProvider.LAPIS_LAZULI);

    // CUSTOM METHOD - Armor Type Defense
    private static Map<ArmorType, Integer> makeArmorTypeDefense() {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, 5, ArmorType.LEGGINGS, 7,
                                      ArmorType.CHESTPLATE, 9, ArmorType.HELMET, 5, ArmorType.BODY, 11));
    }

    // CUSTOM METHOD - Register MOD armor materials
    private static ArmorMaterial armorMaterial(int durability, int enchantment,
                                               Holder<SoundEvent> armorSound, float toughness,
                                               float knockbackResistance, TagKey<Item> repairIngredient,
                                               ResourceKey<EquipmentAsset> assetId) {
        return new ArmorMaterial(durability, makeArmorTypeDefense(), enchantment, armorSound,
                                 toughness, knockbackResistance, repairIngredient, assetId);
    }
}