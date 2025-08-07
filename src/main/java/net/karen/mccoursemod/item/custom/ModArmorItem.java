package net.karen.mccoursemod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.karen.mccoursemod.item.ModArmorMaterials;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ModArmorItem extends Item implements ArmorMaterials {
    private static final ImmutableMap<ArmorMaterial, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<MobEffectInstance>>())
            .put(ModArmorMaterials.BISMUTH,
                 List.of(new MobEffectInstance(MobEffects.JUMP_BOOST, 200, 1, false, false),
                         new MobEffectInstance(MobEffects.GLOWING, 200, 1, false, false)))
            .build();

    public ModArmorItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull ServerLevel level,
                              @NotNull Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof Player player && !level.isClientSide() && hasFullSuitOfArmorOn(player)) {
            evaluateArmorEffects(player);
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapEffect = entry.getValue();
            if (hasPlayerCorrectArmorOn(mapArmorMaterial, player)) {
                addEffectToPlayer(player, mapEffect);
            }
        }
    }

    private void addEffectToPlayer(Player player, List<MobEffectInstance> mapEffect) {
        boolean hasPlayerEffect = mapEffect.stream().allMatch(effect -> player.hasEffect(effect.getEffect()));
        if (!hasPlayerEffect) {
            for (MobEffectInstance effect : mapEffect) {
                 player.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration(),
                                                        effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
            }
        }
    }

    private boolean hasPlayerCorrectArmorOn(ArmorMaterial armorMaterial, Player player) {
        boolean boots = player.hasItemInSlot(EquipmentSlot.FEET);
        boolean leggings = player.hasItemInSlot(EquipmentSlot.LEGS);
        boolean chestplate = player.hasItemInSlot(EquipmentSlot.CHEST);
        boolean helmet = player.hasItemInSlot(EquipmentSlot.HEAD);
        return boots && leggings && chestplate && helmet;
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }
}