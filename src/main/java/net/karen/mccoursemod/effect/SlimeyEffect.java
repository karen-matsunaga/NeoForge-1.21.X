package net.karen.mccoursemod.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

// Climbing Effect by SameDifferent: https://github.com/samedifferent/TrickOrTreat/blob/master/LICENSE
// Distributed under MIT
public class SlimeyEffect extends MobEffect {
    public SlimeyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(@NotNull ServerLevel level, @NotNull LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof Player player) {
            if (player.horizontalCollision) {
                Vec3 initialVec = player.getDeltaMovement();
                Vec3 climbVec = new Vec3(initialVec.x, 0.2D, initialVec.z);
                player.setDeltaMovement(climbVec.scale(0.96D));
                return true;
            }
        }
        return super.applyEffectTick(level, livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}