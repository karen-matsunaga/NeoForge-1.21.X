package net.karen.mccoursemod.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class ElytraPlusItem extends Item {
    private final Holder<MobEffect> effectHolder; // EFFECT
    private final int effectAmplifier; // EFFECT LEVEL

    public ElytraPlusItem(Holder<MobEffect> effectHolder,
                          int effectAmplifier, Item.Properties properties) {
        super(properties);
        this.effectHolder = effectHolder;
        this.effectAmplifier = effectAmplifier;
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull ServerLevel level,
                              @NotNull Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof Player player && !level.isClientSide()) {
            MobEffectInstance effect = new MobEffectInstance(effectHolder, 200, effectAmplifier,
                                                             true, true, true);
            boolean isElytra = player.getInventory().player.getItemBySlot(EquipmentSlot.CHEST).is(this);
            if (isElytra) { player.addEffect(effect); } // EFFECT Activated
            if (!isElytra) { player.removeEffect(effect.getEffect()); } // EFFECT Disabled
        }
        super.inventoryTick(stack, level, entity, slot);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return rgbItemName(stack);
    }

    // CUSTOM METHOD - Elytra Plus item name
    public String elytraItemName() {
        String effectHolder = this.effectHolder.getRegisteredName().replace("minecraft:", "");
        String upperLetter = effectHolder.substring(0, 1).toUpperCase();
        String lowerLetters = effectHolder.substring(1);
        String effectName = upperLetter + lowerLetters;
        int effectLevel = effectAmplifier + 1;
        return " with more durability and receive an effect! \nEffect: " + effectName + " " + effectLevel;
    }
}