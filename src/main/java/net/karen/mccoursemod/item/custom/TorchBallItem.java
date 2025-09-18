package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.entity.custom.TorchBallProjectileEntity;
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
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import static net.karen.mccoursemod.util.ChatUtils.*;
import static net.karen.mccoursemod.util.Utils.neutralSoundValue;

public class TorchBallItem extends Item {
    public TorchBallItem(Properties properties) { super(properties); }

    // DEFAULT METHOD - Torch Ball item name
    public String getItemName() {
        String upper = itemLine(this.descriptionId, "item.mccoursemod.", "", "_", " ");
        return itemLines(upper);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand); // When used Torch Ball item pressed right mouse button
        neutralSoundValue(level, player, SoundEvents.REDSTONE_TORCH_BURNOUT, 0.0F);
        player.getCooldowns().addCooldown(new ItemStack(this.asItem()), 0); // Nothing cooldown
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            Projectile.spawnProjectileFromRotation(TorchBallProjectileEntity::new, serverLevel,
                                                   new ItemStack(Blocks.TORCH.asItem()), player,
                                                   0.0F, 1.5F, 0.0F);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            item.hurtAndBreak(1, player, player.getUsedItemHand());
        }
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return componentTranslatable(this.descriptionId, ChatUtils.gold);
    }
}