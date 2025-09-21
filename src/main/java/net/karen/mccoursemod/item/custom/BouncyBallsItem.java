package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.entity.custom.BouncyBallsProjectileEntity;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.util.ChatUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import static net.karen.mccoursemod.util.Utils.neutralSoundValue;

public class BouncyBallsItem extends Item {
    public BouncyBallsItem(Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResult use(@NotNull Level level, Player player,
                                          @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand); // Player has Bouncy Balls item on hand
        neutralSoundValue(level, player, SoundEvents.ENDER_PEARL_THROW, 0.0F); // Sound when used item
        player.getCooldowns().addCooldown(new ItemStack(this.asItem()), 0); // Nothing cooldown
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            Projectile.spawnProjectileFromRotation(BouncyBallsProjectileEntity::new, serverLevel,
                                                   new ItemStack(ModItems.BOUNCY_BALLS_PARTICLES.get()), player,
                                                   0.0F, 1.5F, 1.0F);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.hurtAndBreak(1, player, player.getUsedItemHand());
        }
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return ChatUtils.componentTranslatableIntColor(this.getDescriptionId(), 0xEFBB2D);
    }
}