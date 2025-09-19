package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.entity.custom.MinerBowArrowEntity;
import net.karen.mccoursemod.util.ChatUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MinerBowItem extends BowItem {
    public MinerBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity, int i) {
        if (!level.isClientSide() && entity instanceof Player player) {
            MinerBowArrowEntity arrow = new MinerBowArrowEntity(player, level);
            arrow.setPos(player.getX(), player.getEyeY(), player.getZ());
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
            level.addFreshEntity(arrow);
            stack.hurtAndBreak(1, player, player.getUsedItemHand());
        }
        return false;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return ChatUtils.rgbItemName(stack);
    }
}