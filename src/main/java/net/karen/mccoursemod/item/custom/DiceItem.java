package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.entity.custom.DiceProjectileEntity;
import net.karen.mccoursemod.item.ModItems;
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

public class DiceItem extends Item {
    public DiceItem(Properties properties) { super(properties); }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player,
                                          @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand); // When used Dice item pressed right mouse button
        neutralSoundValue(level, player, SoundEvents.GRAVEL_HIT, 0.0F);
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            Projectile.spawnProjectileFromRotation(DiceProjectileEntity::new, serverLevel,
                                                   new ItemStack(ModItems.DICE_ITEM.get()), player,
                                                   0.0F, 1.5F, 0.0F);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) { itemstack.shrink(1); }
        return InteractionResult.SUCCESS_SERVER;
    }
}