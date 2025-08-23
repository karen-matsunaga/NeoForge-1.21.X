package net.karen.mccoursemod.entity.ai;

import net.karen.mccoursemod.entity.custom.RhinoEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RhinoAttackGoal extends MeleeAttackGoal {
    private final RhinoEntity entity;
    private int attackDelay = 40;
    private int ticksUntilNextAttack = 40;
    private boolean shouldCountTillNextAttack = false;

    public RhinoAttackGoal(PathfinderMob pathfinderMob, double speedModifier,
                           boolean followingTargetEvenIfNotSeen) {
        super(pathfinderMob, speedModifier, followingTargetEvenIfNotSeen);
        entity = ((RhinoEntity) pathfinderMob);
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 40;
        ticksUntilNextAttack = 40;
    }

    // Rhino's attack animation ticks
    @Override
    protected void checkAndPerformAttack(@NotNull LivingEntity enemy) {
        double distToEnemySqr = this.mob.distanceToSqr(enemy);
        if (isEnemyWithinAttackDistance(enemy, distToEnemySqr)) {
            shouldCountTillNextAttack = true;
            if (isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }
            if (isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(enemy.getX(), enemy.getEyeY(), enemy.getZ());
                performAttack(enemy);
            }
        }
        else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity entity, double distToEnemySqr) {
        return distToEnemySqr >= this.mob.distanceTo(entity);
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    protected void performAttack(LivingEntity entity) {
        this.resetAttackCooldown();
        Level level = entity.level();
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            this.mob.swing(InteractionHand.MAIN_HAND); this.mob.doHurtTarget(serverLevel, entity);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack -1, 0);
        }
    }

    @Override
    public void stop() { entity.setAttacking(false); super.stop(); } // Entity data save false as default
}