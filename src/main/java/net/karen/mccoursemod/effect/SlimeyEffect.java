package net.karen.mccoursemod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

// Climbing Effect by SameDifferent: https://github.com/samedifferent/TrickOrTreat/blob/master/LICENSE
// Distributed under MIT
public class SlimeyEffect extends MobEffect {
    public SlimeyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(@NotNull ServerLevel serverLevel, @NotNull LivingEntity livingEntity, int amplifier) {
        Level level = livingEntity.level();
        BlockPos pos = livingEntity.blockPosition();
        BlockState state = level.getBlockState(pos);
        if (state.isCollisionShapeFullBlock(level, pos)) {
            boolean isOnGround = livingEntity.onGround(),
                    isHorizontal = livingEntity.horizontalCollision,
                    isClimbable = livingEntity.onClimbable();
            if (isHorizontal || isClimbable) {
                Vec3 initialVec = livingEntity.getDeltaMovement(),
                       climbVec = new Vec3(initialVec.x, 0.2D, initialVec.z);
                livingEntity.setDeltaMovement(climbVec.scale(0.96D));
                livingEntity.setOnGroundWithMovement(isOnGround, isHorizontal, climbVec.scale(0.96D));
                return true;
            }
        }
        return super.applyEffectTick(serverLevel, livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) { return true; }
}