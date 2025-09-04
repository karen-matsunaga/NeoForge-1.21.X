package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.util.ChatUtils;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class ElytraPlusItem extends Item {
    private final Holder<MobEffect> effectHolder; // EFFECT
    private final int effectAmplifier; // EFFECT LEVEL

    private static final int[] COLORS = { 0xff5555, 0xffaa00, 0xffff55, 0x55ff55,
                                          0x55ffff, 0x5555ff, 0xff55ff };

    public ElytraPlusItem(Holder<MobEffect> effectHolder, int effectAmplifier,
                          Item.Properties properties) {
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
        return componentTranslatable(this.descriptionId, ChatUtils.aqua);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @NotNull TooltipContext context, @NotNull TooltipDisplay display,
                                @NotNull Consumer<Component> consumer, @NotNull TooltipFlag flag) {
        String message = " with more durability and receive an effect! \nEffect: REGENERATION V.";
        tooltipLineLiteralRGB(consumer, COLORS, stack, message);
        super.appendHoverText(stack, context, display, consumer, flag);
    }
}