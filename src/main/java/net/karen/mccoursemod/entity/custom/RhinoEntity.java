package net.karen.mccoursemod.entity.custom;

import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.entity.ai.RhinoAttackGoal;
import net.karen.mccoursemod.entity.variant.RhinoVariant;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static net.neoforged.neoforge.event.EventHooks.onAnimalTame;

public class RhinoEntity extends TamableAnimal implements PlayerRideable {
    // RHINO entity data access - CLIENT / SERVER
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(RhinoEntity.class, EntityDataSerializers.BOOLEAN);

    // RHINO id custom entity variants
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(RhinoEntity.class, EntityDataSerializers.INT);

    // RHINO custom entity animations
    // Rhino custom WALK animation
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    // Rhino custom ATTACK animation
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    // Rhino custom SIT animation
    public final AnimationState sitAnimationState = new AnimationState();

    /* BOSS BAR */
    private final ServerBossEvent bossEvent =
            new ServerBossEvent(Component.literal("Our Cool Rhino"),
                                BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.NOTCHED_12);

    public RhinoEntity(EntityType<? extends TamableAnimal> entityType,
                       Level level) {
        super(entityType, level);
    }

    // Rhino custom entity IA
    @Override
    protected void registerGoals() {
        // WALK animation
        this.goalSelector.addGoal(0, new FloatGoal(this));
        // ATTACK animation
        this.goalSelector.addGoal(1, new RhinoAttackGoal(this, 1.0D,
                                                                true));
        // BREED animation
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D,
                                                          Ingredient.of(Items.COOKED_BEEF), false));
        // TAMABLE animation
        this.goalSelector.addGoal(0, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.25D,
                                                                18F, 7F));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 4F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        // ATTACK animation
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    // Custom attributes of Rhino custom entity
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 35D)
                                              .add(Attributes.MOVEMENT_SPEED, 0.15D)
                                              .add(Attributes.FOLLOW_RANGE, 24D)
                                              .add(Attributes.ARMOR_TOUGHNESS, 0.1F)
                                              .add(Attributes.ATTACK_KNOCKBACK, 0.5F)
                                              .add(Attributes.ATTACK_DAMAGE, 2F)
                                              .add(Attributes.TEMPT_RANGE, 16D);
    }

    // Walk custom entity animation
    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel,
                                                  @NotNull AgeableMob ageableMob) {
        return ModEntities.RHINO.get().create(serverLevel, EntitySpawnReason.BREEDING);
    }

    private void setupAnimationStates() {
        // WALK animation
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        }
        else { --this.idleAnimationTimeout; }
        // ATTACK animation
        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            // 20 (ticks) * 4 (seconds) = 80 ticks / 4 seconds - Length in ticks of your animation
            attackAnimationTimeout = 80;
            attackAnimationState.start(this.tickCount);
        }
        else { --this.attackAnimationTimeout; }
        if (!this.isAttacking()) { attackAnimationState.stop(); } // NONE attack
        // SIT animation
        if (this.isInSittingPose()) { sitAnimationState.startIfStopped(this.tickCount); }
        else { sitAnimationState.stop(); }
    }

    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) { f = Math.min(v * 6.0F, 1.0F); }
        else { f = 0.0F; }
        this.walkAnimation.update(f, 0.2F, 1f);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) { this.setupAnimationStates(); }
    }

    // CUSTOM METHOD - RHINO attack animation -> DEFAULT (False)
    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking); // It is attacked
    }

    // CUSTOM METHOD - RHINO is attacking
    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false); // Default it is not attack
        builder.define(DATA_ID_TYPE_VARIANT, 0); // Default it is 0
    }

    /* VARIANT */
    public RhinoVariant getVariant() {
        return RhinoVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(RhinoVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    // Chance of spawn a Rhino variants
    @Override
    public @NotNull SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor accessor,
                                                 @NotNull DifficultyInstance instance,
                                                 @NotNull EntitySpawnReason spawnReason,
                                                 @Nullable SpawnGroupData data) {
        RhinoVariant variant = Util.getRandom(RhinoVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(accessor, instance, spawnReason, data);
    }

    @Override
    protected void readAdditionalSaveData(@NotNull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.entityData.set(DATA_ID_TYPE_VARIANT, input.getIntOr("Variant", 0));
    }

    @Override
    protected void addAdditionalSaveData(@NotNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("Variant", this.getTypeVariant());
    }

    /* SOUNDS */
    @Override
    protected @Nullable SoundEvent getAmbientSound() { return SoundEvents.HOGLIN_AMBIENT; }

    @Override
    protected @Nullable SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() { return SoundEvents.DOLPHIN_DEATH; }

    /* TAMABLE */
    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand); // Player has an APPLE on main hand
        Item item = itemstack.getItem(); // Player has an APPLE item
        Item itemForTaming = Items.APPLE; // When the player used APPLE to tamable RHINO entity
        if (item == itemForTaming && !isTame()) {
            if (this.level().isClientSide()) { return InteractionResult.CONSUME; }
            else {
                if (!player.getAbilities().instabuild) { itemstack.shrink(1); }
                if (!onAnimalTame(this, player)) {
                    super.tame(player);
                    this.navigation.recomputePath();
                    this.setTarget(null);
                    this.level().broadcastEntityEvent(this, (byte)7);
                    setOrderedToSit(true);
                    this.setInSittingPose(true);
                }
                return InteractionResult.SUCCESS;
            }
        }
        if (isTame() && hand == InteractionHand.MAIN_HAND && !isFood(itemstack)) {
            if (!player.isCrouching()) { setRiding(player); }
            // TOGGLES SITTING FOR OUR ENTITY
            else {
                setOrderedToSit(!isOrderedToSit());
                setInSittingPose(!isOrderedToSit());
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    /* RIDEABLE */
    private void setRiding(Player player) {
        this.setInSittingPose(false);
        player.setYRot(this.getYRot());
        player.setXRot(this.getXRot());
        player.startRiding(this);
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() { return ((LivingEntity) this.getFirstPassenger()); }

    @Override
    public void travel(@NotNull Vec3 vec3) {
        if (this.isVehicle() && getControllingPassenger() instanceof Player) {
            LivingEntity livingentity = this.getControllingPassenger();
            this.setYRot(livingentity.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(livingentity.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.yBodyRot;
            float f = livingentity.xxa * 0.5F;
            float f1 = livingentity.zza;
            if (f1 <= 0.0F) { f1 *= 0.25F; }
            // Inside this if statement, we are on the client!
            if (this.isLocalInstanceAuthoritative()) {
                float newSpeed = (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
                // Increasing speed by 100% if the spring key is held down (number for testing purposes)
                if (Minecraft.getInstance().options.keySprint.isDown()) { newSpeed *= 2f; }
                this.setSpeed(newSpeed);
                super.travel(new Vec3(f, vec3.y, f1));
            }
        }
        else { super.travel(vec3); }
    }

    @Override
    public @NotNull Vec3 getDismountLocationForPassenger(@NotNull LivingEntity entity) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() != Direction.Axis.Y) {
            int[][] offsets = DismountHelper.offsetsForDirection(direction);
            BlockPos blockpos = this.blockPosition();
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            for (Pose pose : entity.getDismountPoses()) {
                AABB aabb = entity.getLocalBoundsForPose(pose);
                for (int[] offset : offsets) {
                    mutableBlockPos.set(blockpos.getX() + offset[0], blockpos.getY(), blockpos.getZ() + offset[1]);
                    double d0 = this.level().getBlockFloorHeight(mutableBlockPos);
                    if (DismountHelper.isBlockFloorValid(d0)) {
                        Vec3 vec3 = Vec3.upFromBottomCenterOf(mutableBlockPos, d0);
                        if (DismountHelper.canDismountTo(this.level(), entity, aabb.move(vec3))) {
                            entity.setPose(pose);
                            return vec3;
                        }
                    }
                }
            }
        }
        return super.getDismountLocationForPassenger(entity);
    }

    /* BREEDING -> COOKED BEEF breedable Rhino entities */
    @Override
    public boolean isFood(ItemStack stack) { return stack.is(Items.COOKED_BEEF); }

    /* BOSS BAR */
    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossEvent.removePlayer(serverPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}