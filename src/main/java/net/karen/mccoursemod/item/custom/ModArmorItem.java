package net.karen.mccoursemod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.karen.mccoursemod.item.ModArmorMaterials;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.Equippable;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModArmorItem extends Item {
    private static final ImmutableMap<ArmorMaterial, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<MobEffectInstance>>())
            .put(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL,
                 List.of(new MobEffectInstance(MobEffects.JUMP_BOOST, 200, 1, true, true),
                         new MobEffectInstance(MobEffects.GLOWING, 200, 1, true, true)))
            .build();

    public ModArmorItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull ServerLevel level,
                              @NotNull Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof Player player && hasFullSuitOfArmorOn(player)) {
            evaluateArmorEffects(player);
        }
    }

    // CUSTOM METHOD - Check if player used same armor material type and received correct effect
    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapEffect = entry.getValue();
            if (hasPlayerCorrectArmorOn(mapArmorMaterial, player)) {
                addEffectToPlayer(player, mapEffect);
            }
        }
    }

    // CUSTOM METHOD - Player received all effect from armor material type
    private void addEffectToPlayer(Player player, List<MobEffectInstance> mapEffect) {
        boolean hasPlayerEffect = mapEffect.stream().allMatch(effect -> player.hasEffect(effect.getEffect()));
        if (!hasPlayerEffect) {
            for (MobEffectInstance effect : mapEffect) {
                 player.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration(),
                                                        effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
            }
        }
    }

    // CUSTOM METHOD - Player is used only one armor material type
    private boolean hasPlayerCorrectArmorOn(ArmorMaterial armorMaterial, Player player) {
        Inventory playerInv = player.getInventory(); // Player's inventory
        DataComponentType<Equippable> dataComp = DataComponents.EQUIPPABLE;
        Equippable boots = playerInv.getItem(EquipmentSlot.FEET.getIndex()).getComponents().get(dataComp),
                leggings = playerInv.getItem(EquipmentSlot.LEGS.getIndex()).getComponents().get(dataComp),
              chestplate = playerInv.getItem(EquipmentSlot.CHEST.getIndex()).getComponents().get(dataComp),
                  helmet = playerInv.getItem(EquipmentSlot.HEAD.getIndex()).getComponents().get(dataComp);
        boolean result = (boots != null && leggings != null && chestplate != null && helmet != null);
        boolean returnValue = false;
        if (result) {
            Optional<ResourceKey<EquipmentAsset>> bootsEquip = boots.assetId(),
                                                  leggingsEquip = leggings.assetId(),
                                                  chestplateEquip = chestplate.assetId(),
                                                  helmetEquip = helmet.assetId();
            boolean resultTwo = (bootsEquip.isPresent() && leggingsEquip.isPresent() &&
                                 chestplateEquip.isPresent() && helmetEquip.isPresent());
            if (resultTwo) {
                ResourceKey<EquipmentAsset> armorAssetId = armorMaterial.assetId();
                returnValue = bootsEquip.get().equals(armorAssetId) && leggingsEquip.get().equals(armorAssetId) &&
                              chestplateEquip.get().equals(armorAssetId) && helmetEquip.get().equals(armorAssetId);
            }
        }
        return returnValue;
    }

    // CUSTOM METHOD - Player is used full armor
    private boolean hasFullSuitOfArmorOn(Player player) {
        Inventory playerInv = player.getInventory(); // Player's inventory
        ItemStack boots = playerInv.getItem(EquipmentSlot.FEET.getIndex());  // Boots
        ItemStack leggings = playerInv.getItem(EquipmentSlot.LEGS.getIndex());  // Leggings
        ItemStack chestplate = playerInv.getItem(EquipmentSlot.CHEST.getIndex()); // Chestplate
        ItemStack helmet = playerInv.getItem(EquipmentSlot.HEAD.getIndex());  // Helmet
        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }
}