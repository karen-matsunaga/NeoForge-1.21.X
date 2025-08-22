package net.karen.mccoursemod.entity.custom;

import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.NotNull;

public class TomahawkProjectileEntity extends AbstractArrow {
    private float rotation;
    public Vec2 groundedOffset;

    public TomahawkProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public TomahawkProjectileEntity(LivingEntity shooter, Level level) {
        super(ModEntities.TOMAHAWK.get(), shooter, level, new ItemStack(ModItems.TOMAHAWK.get()), null);
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.TOMAHAWK.get());
    }

    @Override
    protected boolean isInGround() {
        return super.isInGround();
    }

    // CUSTOM METHOD - Tomahawk not is in ground
    public boolean neverIsInGround() {
        return isInGround();
    }

    // CUSTOM METHOD - Tomahawk rotate when not is in ground
    public float getRenderingRotation() {
        rotation += 0.5f;
        if (rotation >= 360) { rotation = 0; }
        return rotation;
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        Level level = this.level();
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            entity.hurtServer(serverLevel, this.damageSources().thrown(this, this.getOwner()), 4);
            level.broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);
        if (result.getDirection() == Direction.SOUTH) { groundedOffset = new Vec2(215f,180f); }
        if (result.getDirection() == Direction.NORTH) { groundedOffset = new Vec2(215f, 0f); }
        if (result.getDirection() == Direction.EAST) { groundedOffset = new Vec2(215f,-90f); }
        if (result.getDirection() == Direction.WEST) { groundedOffset = new Vec2(215f,90f); }
        if (result.getDirection() == Direction.DOWN) { groundedOffset = new Vec2(115f,180f); }
        if (result.getDirection() == Direction.UP) { groundedOffset = new Vec2(285f,180f); }
    }
}