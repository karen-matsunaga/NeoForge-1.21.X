package net.karen.mccoursemod.entity.custom;

import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.UUID;

public class BouncyBallsProjectileEntity extends ThrownEnderpearl {
    private long ticketTimer = 0L;

    public BouncyBallsProjectileEntity(EntityType<? extends ThrownEnderpearl> entityType,
                                       Level level) {
        super(entityType, level);
    }

    public BouncyBallsProjectileEntity(Level level, LivingEntity owner, ItemStack item) {
        super(level, owner, item);
    }

    // CUSTOM METHOD - Default item
    protected @NotNull Item getDefaultItem() { return ModItems.BOUNCY_BALLS.get(); }

    protected void setOwner(@Nullable EntityReference<Entity> reference) {
        this.deregisterFromCurrentOwner();
        super.setOwner(reference);
        this.registerToCurrentOwner();
    }

    private void deregisterFromCurrentOwner() {
        Entity owner = this.getOwner();
        if (owner instanceof ServerPlayer serverplayer) {
            serverplayer.deregisterEnderPearl(this);
        }
    }

    private void registerToCurrentOwner() {
        Entity owner = this.getOwner();
        if (owner instanceof ServerPlayer serverplayer) {
            serverplayer.registerEnderPearl(this);
        }
    }

    @Nullable
    public Entity getOwner() {
        Entity entity;
        if (this.owner != null) {
            Level level = this.level();
            if (level instanceof ServerLevel serverlevel) {
                entity = this.owner.getEntity(uuid ->
                                              findOwnerInAnyDimension(serverlevel, uuid), Entity.class);
                return entity;
            }
        }
        entity = super.getOwner();
        return entity;
    }

    @Nullable
    private static Entity findOwnerInAnyDimension(ServerLevel level, UUID uuid) {
        Entity entity = level.getEntity(uuid);
        if (entity != null) { return entity; }
        else {
            for (ServerLevel serverlevel : level.getServer().getAllLevels()) {
                if (serverlevel != level) {
                    entity = serverlevel.getEntity(uuid);
                    if (entity != null) { return entity; }
                }
            }
            return null;
        }
    }

    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurtServer((ServerLevel) result.getEntity().level(),
                                      this.damageSources().thrown(this,
                                      this.getOwner()), 0.0F);
    }

    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);
        for (int i = 0; i < 32; ++i) {
            this.level().addParticle(ParticleTypes.PORTAL, this.getX(),
                                     this.getY() + this.random.nextDouble() * (double) 2.0F,
                                     this.getZ(), this.random.nextGaussian(), 0.0F, this.random.nextGaussian());
        }
        Level level = this.level();
        if (level instanceof ServerLevel serverlevel) {
            if (!this.isRemoved()) {
                Entity entity = this.getOwner();
                if (entity != null && isAllowedToTeleportOwner(entity, serverlevel)) {
                    Vec3 vec3 = this.oldPosition();
                    if (entity instanceof ServerPlayer serverplayer) {
                        if (serverplayer.connection.isAcceptingMessages()) {
                            EntityTeleportEvent.EnderPearl event =
                                  EventHooks.onEnderPearlLand(serverplayer, this.getX(), this.getY(), this.getZ(),
                                                              this, 5.0F, result);
                            if (!event.isCanceled()) {
                                if (this.random.nextFloat() < 0.05F &&
                                    serverlevel.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
                                    Endermite endermite = EntityType.ENDERMITE.create(serverlevel, EntitySpawnReason.TRIGGERED);
                                    if (endermite != null) {
                                        endermite.snapTo(entity.getX(), entity.getY(), entity.getZ(),
                                                         entity.getYRot(), entity.getXRot());
                                        serverlevel.addFreshEntity(endermite);
                                    }
                                }
                                if (this.isOnPortalCooldown()) { entity.setPortalCooldown(); }
                                ServerPlayer serverPlayerNew =
                                      serverplayer.teleport(new TeleportTransition(serverlevel, event.getTarget(),
                                                            entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(),
                                                            TeleportTransition.DO_NOTHING));
                                if (serverPlayerNew != null) {
                                    serverPlayerNew.resetFallDistance();
                                    serverPlayerNew.resetCurrentImpulseContext();
                                    serverPlayerNew.hurtServer(serverplayer.level(), this.damageSources().fall(),
                                                               event.getAttackDamage());
                                }
                                this.playSound(serverlevel, vec3);
                            }
                        }
                    }
                    else {
                        Entity entity1 = entity.teleport(new TeleportTransition(serverlevel, vec3, entity.getDeltaMovement(),
                                                         entity.getYRot(), entity.getXRot(), TeleportTransition.DO_NOTHING));
                        if (entity1 != null) { entity1.resetFallDistance(); }
                        this.playSound(serverlevel, vec3);
                    }
                    this.discard();
                }
                else { this.discard(); }
            }
        }
    }

    private static boolean isAllowedToTeleportOwner(Entity entity, Level level) {
        if (entity.level().dimension() != level.dimension()) {
            return entity.canUsePortal(true);
        }
        else {
            boolean block;
            if (entity instanceof LivingEntity livingentity) {
                block = livingentity.isAlive() && !livingentity.isSleeping();
            }
            else { block = entity.isAlive(); }
            return block;
        }
    }

    public void tick() {
        int i;
        int j;
        Entity entity;
        label: {
            i = SectionPos.blockToSectionCoord(this.position().x());
            j = SectionPos.blockToSectionCoord(this.position().z());
            entity = this.getOwner();
            if (entity instanceof ServerPlayer serverplayer) {
                if (!entity.isAlive() && serverplayer.level()
                                                     .getGameRules().getBoolean(GameRules.RULE_ENDER_PEARLS_VANISH_ON_DEATH)) {
                    this.discard();
                    break label;
                }
            }
            super.tick();
        }
        if (this.isAlive()) {
            BlockPos blockpos = BlockPos.containing(this.position());
            if ((--this.ticketTimer <= 0L || i != SectionPos.blockToSectionCoord(blockpos.getX()) ||
                j != SectionPos.blockToSectionCoord(blockpos.getZ())) && entity instanceof ServerPlayer serverplayer) {
                this.ticketTimer = serverplayer.registerAndUpdateEnderPearlTicket(this);
            }
        }
    }

    private void playSound(Level level, Vec3 pos) {
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
    }

    @Nullable
    public Entity teleport(@NotNull TeleportTransition transition) {
        Entity entity = super.teleport(transition);
        if (entity != null) { entity.placePortalTicket(BlockPos.containing(entity.position())); }
        return entity;
    }

    public boolean canTeleport(Level level, @NotNull Level dimension) {
        boolean block;
        if (level.dimension() == Level.END && dimension.dimension() == Level.OVERWORLD) {
            Entity owner = this.getOwner();
            if (owner instanceof ServerPlayer serverplayer) {
                block = super.canTeleport(level, dimension) && serverplayer.seenCredits;
                return block;
            }
        }
        block = super.canTeleport(level, dimension);
        return block;
    }

    protected void onInsideBlock(@NotNull BlockState state) {
        super.onInsideBlock(state);
        if (state.is(Blocks.END_GATEWAY)) {
            Entity owner = this.getOwner();
            if (owner instanceof ServerPlayer serverplayer) { serverplayer.onInsideBlock(state); }
        }
    }

    public void onRemoval(Entity.@NotNull RemovalReason reason) {
        if (reason != RemovalReason.UNLOADED_WITH_PLAYER) { this.deregisterFromCurrentOwner(); }
        super.onRemoval(reason);
    }

    public void onAboveBubbleColumn(boolean bubble, @NotNull BlockPos pos) {
        Entity.handleOnAboveBubbleColumn(this, bubble, pos);
    }

    public void onInsideBubbleColumn(boolean bubble) {
        Entity.handleOnInsideBubbleColumn(this, bubble);
    }
}