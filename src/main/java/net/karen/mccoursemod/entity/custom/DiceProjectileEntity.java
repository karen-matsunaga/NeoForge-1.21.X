package net.karen.mccoursemod.entity.custom;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.custom.DiceBlock;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class DiceProjectileEntity extends ThrowableItemProjectile {
    public DiceProjectileEntity(EntityType<? extends ThrowableItemProjectile> entityType,
                                Level level) {
        super(entityType, level);
    }

    /* LEVEL, LIVING ENTITY and ITEMSTACK this order because
       PROJECTILE abstract class DOESN'T work in other order! */
    public DiceProjectileEntity(Level level, LivingEntity owner, ItemStack item) {
        super(ModEntities.DICE_PROJECTILE.get(), owner, level, item);
    }

    @Override
    protected @NotNull Item getDefaultItem() { return ModItems.DICE_ITEM.get(); }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult hitResult) {
        Level level = this.level();
        if (!level.isClientSide()) { // Not is CLIENT side
            level.broadcastEntityEvent(this, ((byte) 3));
            level.setBlock(blockPosition(), ((DiceBlock) ModBlocks.DICE.get()).getRandomBlockState(), 3);
        }
        this.discard();
        super.onHitBlock(hitResult);
    }
}