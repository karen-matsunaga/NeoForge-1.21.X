package net.karen.mccoursemod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class GrowthItem extends Item {
    public GrowthItem(Properties properties) { super(properties); }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player player,
                                                           @NotNull LivingEntity entity, @NotNull InteractionHand hand) {
        if (!player.level().isClientSide() && entity instanceof AgeableMob ageable && ageable.isBaby()) {
            ageable.setAge(0); // 0 = Adult -> Checks if the target is a Baby Animal
            ageable.level().broadcastEntityEvent(ageable, (byte) 7); // Heart particles
            if (!player.isCreative()) { stack.shrink(1); } // Consumes item
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return componentTranslatable(this.getDescriptionId(), darkAqua);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                @NotNull TooltipDisplay display, @NotNull Consumer<Component> consumer,
                                @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, context, display, consumer, flag);
        tooltipLine(consumer, "Makes a baby animal an adult animal!", aqua);
    }
}