package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.entity.custom.MagicProjectileEntity;
import net.karen.mccoursemod.sound.ModSounds;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import static net.karen.mccoursemod.util.Utils.neutralSound;

public class RadiationStaffItem extends Item {
    public RadiationStaffItem(Properties properties) { super(properties); }

    // DEFAULT METHOD - Magic Projectile only right click on mouse button
    @Override
    public @NotNull InteractionResult use(@NotNull Level level,
                                          @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand); // Player has RADIATION STAFF item
        neutralSound(level, player, ModSounds.METAL_DETECTOR_FOUND_ORE.get(), 1.5F, 1F); // Sound of Magic Projectile
        player.getCooldowns().addCooldown(new ItemStack(this), 20);
        if (!level.isClientSide()) { // CLIENT and SERVER created Magic Projectile
            MagicProjectileEntity magicProjectile = new MagicProjectileEntity(level, player); // Added velocity and inaccuracy
            magicProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.25F);
            level.addFreshEntity(magicProjectile); // Added Magic Projectile on world
        }
        player.awardStat(Stats.ITEM_USED.get(this)); // Item hurt
        if (!player.getAbilities().instabuild) {
            itemstack.hurtAndBreak(1, player, player.getUsedItemHand());
        }
        return InteractionResult.SUCCESS;
    }
}