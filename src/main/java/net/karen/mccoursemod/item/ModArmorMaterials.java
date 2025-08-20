package net.karen.mccoursemod.item;

import com.google.common.collect.Maps;
import net.karen.mccoursemod.datagen.ModEquipmentAssetProvider;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.Map;

public class ModArmorMaterials {
    // Registry all custom ARMOR MATERIALS
    // BISMUTH
    public static final ArmorMaterial BISMUTH_ARMOR_MATERIAL =
           new ArmorMaterial(50, makeArmorTypeDefense(), 20, SoundEvents.ARMOR_EQUIP_NETHERITE,
                             0.0F, 0.0F, ModTags.Items.REPAIRS_BISMUTH_ARMOR,
                             ModEquipmentAssetProvider.BISMUTH);

    // ALEXANDRITE
    public static final ArmorMaterial ALEXANDRITE_ARMOR_MATERIAL =
           new ArmorMaterial(24, makeArmorTypeDefense(), 15, SoundEvents.ARMOR_EQUIP_IRON,
                             3.0F, 0.1F, ModTags.Items.REPAIRS_ALEXANDRITE_ARMOR,
                             ModEquipmentAssetProvider.ALEXANDRITE);


    // CUSTOM METHOD - Armor Type Defense
    private static Map<ArmorType, Integer> makeArmorTypeDefense() {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, 5, ArmorType.LEGGINGS, 7,
                                      ArmorType.CHESTPLATE, 9, ArmorType.HELMET, 5, ArmorType.BODY, 11));
    }
}