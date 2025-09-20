package net.karen.mccoursemod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.karen.mccoursemod.item.ModArmorMaterials;
import net.karen.mccoursemod.util.ChatUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
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
    private final int textColor;
    private static final ImmutableMap<ArmorMaterial, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<MobEffectInstance>>())
            .put(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL,
                 List.of(new MobEffectInstance(MobEffects.JUMP_BOOST, 200, 1, true, true),
                         new MobEffectInstance(MobEffects.GLOWING, 200, 1, true, true)))
            .put(ModArmorMaterials.PINK_ARMOR_MATERIAL,
                 List.of(new MobEffectInstance(MobEffects.SPEED, 200, 1, true, true)))
            .put(ModArmorMaterials.COPPER_ARMOR_MATERIAL,
                 List.of(new MobEffectInstance(MobEffects.REGENERATION, 200, 1, true, true)))
            .put(ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL,
                 List.of(new MobEffectInstance(MobEffects.SATURATION, 200, 1, true, true)))
            .put(ModArmorMaterials.REDSTONE_ARMOR_MATERIAL,
                 List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 1, true, true),
                         new MobEffectInstance(MobEffects.GLOWING, 200, 1, true, true)))
            .build();

    public ModArmorItem(Properties properties, int textColor) {
        super(properties);
        this.textColor = textColor;
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull ServerLevel level,
                              @NotNull Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof Player player && hasFullSuitOfArmorOn(player)) { evaluateArmorEffects(player); }
    }

    // CUSTOM METHOD - Check if player used same armor material type and received correct effect
    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapEffect = entry.getValue();
            if (hasPlayerCorrectArmorOn(mapArmorMaterial, player)) { addEffectToPlayer(player, mapEffect); }
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
        Equippable boots = player.getItemBySlot(EquipmentSlot.FEET).getComponents().get(DataComponents.EQUIPPABLE);
        Equippable leggings = player.getItemBySlot(EquipmentSlot.LEGS).getComponents().get(DataComponents.EQUIPPABLE);
        Equippable chestplate = player.getItemBySlot(EquipmentSlot.CHEST).getComponents().get(DataComponents.EQUIPPABLE);
        Equippable helmet = player.getItemBySlot(EquipmentSlot.HEAD).getComponents().get(DataComponents.EQUIPPABLE);
        if (boots != null && leggings != null && chestplate != null && helmet != null) {
            Optional<ResourceKey<EquipmentAsset>> bootsEquip = boots.assetId();
            Optional<ResourceKey<EquipmentAsset>> leggingsEquip = leggings.assetId();
            Optional<ResourceKey<EquipmentAsset>> chestplateEquip = chestplate.assetId();
            Optional<ResourceKey<EquipmentAsset>> helmetEquip = helmet.assetId();
            if (bootsEquip.isPresent() && leggingsEquip.isPresent() && chestplateEquip.isPresent() && helmetEquip.isPresent()) {
                ResourceKey<EquipmentAsset> armorAssetId = armorMaterial.assetId();
                return bootsEquip.get().equals(armorAssetId) && leggingsEquip.get().equals(armorAssetId) &&
                       chestplateEquip.get().equals(armorAssetId) && helmetEquip.get().equals(armorAssetId);
            }
        }
        return false;
    }

    // CUSTOM METHOD - Player is used full armor
    private boolean hasFullSuitOfArmorOn(Player player) {
        return player.hasItemInSlot(EquipmentSlot.FEET) && player.hasItemInSlot(EquipmentSlot.LEGS) &&
               player.hasItemInSlot(EquipmentSlot.CHEST) && player.hasItemInSlot(EquipmentSlot.HEAD);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return ChatUtils.componentTranslatableIntColor(this.getDescriptionId(), textColor);
    }
}