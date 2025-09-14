package net.karen.mccoursemod.entity;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.custom.*;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
           DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MccourseMod.MOD_ID);

    // Registry all custom Entities -> Resource Key
    public static ResourceKey<EntityType<?>> GECKO_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("gecko"));

    public static ResourceKey<EntityType<?>> RHINO_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("rhino"));

    // Registry all custom Throwable Projectiles -> Resource Key
    public static ResourceKey<EntityType<?>> TOMAHAWK_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("tomahawk"));

    public static ResourceKey<EntityType<?>> TORCH_BALL_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE,
                              ResourceLocation.withDefaultNamespace("torch_ball"));

    public static ResourceKey<EntityType<?>> BOUNCY_BALLS_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE,
                              ResourceLocation.withDefaultNamespace("bouncy_balls"));

    public static ResourceKey<EntityType<?>> DICE_PROJECTILE_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE,
                              ResourceLocation.withDefaultNamespace("dice_projectile"));

    public static ResourceKey<EntityType<?>> MAGIC_PROJECTILE_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE,
                              ResourceLocation.withDefaultNamespace("magic_projectile"));

    public static ResourceKey<EntityType<?>> MINER_BOW_ARROW_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE,
                              ResourceLocation.withDefaultNamespace("miner_bow_arrow_entity"));

    public static ResourceKey<EntityType<?>> MOD_BOAT_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE,
                              ResourceLocation.withDefaultNamespace("mod_boat"));

    public static ResourceKey<EntityType<?>> MOD_CHEST_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE,
                              ResourceLocation.withDefaultNamespace("mod_chest_boat"));

    // Registry all custom sittable blocks -> Resource Key
    public static ResourceKey<EntityType<?>> CHAIR_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("chair_entity"));

    // Registry all custom Entities -> Entity Type
    public static final Supplier<EntityType<GeckoEntity>> GECKO =
           ENTITY_TYPES.register("gecko", () -> EntityType.Builder.of(GeckoEntity::new, MobCategory.CREATURE)
                                                                .sized(0.75f, 0.35f).build(GECKO_KEY));

    public static final Supplier<EntityType<RhinoEntity>> RHINO =
           ENTITY_TYPES.register("rhino", () -> EntityType.Builder.of(RhinoEntity::new, MobCategory.CREATURE)
                                                                .sized(2.5f, 2.5f).build(RHINO_KEY));

    // Registry all custom Throwable Projectiles -> Entity Type
    public static final Supplier<EntityType<TomahawkProjectileEntity>> TOMAHAWK =
           ENTITY_TYPES.register("tomahawk",
           () -> EntityType.Builder.<TomahawkProjectileEntity>of(TomahawkProjectileEntity::new, MobCategory.MISC)
                                   .sized(0.5f, 1.15f).build(TOMAHAWK_KEY));

    public static final Supplier<EntityType<TorchBallProjectileEntity>> TORCH_BALL =
           ENTITY_TYPES.register("torch_ball",
           () -> EntityType.Builder.<TorchBallProjectileEntity>of(TorchBallProjectileEntity::new, MobCategory.MISC)
                           .sized(0.5f, 0.5f)
                           .clientTrackingRange(4)
                           .updateInterval(20)
                           .build(TORCH_BALL_KEY));

    public static final Supplier<EntityType<BouncyBallsProjectileEntity>> BOUNCY_BALLS =
           ENTITY_TYPES.register("bouncy_balls",
           () -> EntityType.Builder.<BouncyBallsProjectileEntity>of(BouncyBallsProjectileEntity::new, MobCategory.MISC)
                           .sized(0.5f, 0.5f)
                           .clientTrackingRange(4)
                           .updateInterval(20)
                           .build(BOUNCY_BALLS_KEY));

    public static final Supplier<EntityType<DiceProjectileEntity>> DICE_PROJECTILE =
           ENTITY_TYPES.register("dice_projectile",
           () -> EntityType.Builder.<DiceProjectileEntity>of(DiceProjectileEntity::new, MobCategory.MISC)
                           .sized(0.5f, 0.5f)
                           .clientTrackingRange(4)
                           .updateInterval(20)
                           .build(DICE_PROJECTILE_KEY));

    public static final Supplier<EntityType<MagicProjectileEntity>> MAGIC_PROJECTILE =
           ENTITY_TYPES.register("magic_projectile",
           () -> EntityType.Builder.<MagicProjectileEntity>of(MagicProjectileEntity::new, MobCategory.MISC)
                                   .sized(0.5f, 0.5f)
                                   .clientTrackingRange(4)
                                   .updateInterval(20)
                                   .build(MAGIC_PROJECTILE_KEY));

    public static final Supplier<EntityType<MinerBowArrowEntity>> MINER_BOW_ARROW_ENTITY =
           ENTITY_TYPES.register("miner_bow_arrow_entity",
           () -> EntityType.Builder.<MinerBowArrowEntity>of(MinerBowArrowEntity::new, MobCategory.MISC)
                                   .sized(0.5f, 0.5f)
                                   .clientTrackingRange(4)
                                   .updateInterval(20)
                                   .build(MINER_BOW_ARROW_KEY));

    // Registry all custom sittable blocks -> Entity Type
    public static final Supplier<EntityType<ChairEntity>> CHAIR_ENTITY =
           ENTITY_TYPES.register("chair_entity",
           () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC)
                           .sized(0.5f, 0.5f).build(CHAIR_KEY));

    // Register all custom boats
    public static final Supplier<EntityType<ModBoatEntity>> MOD_BOAT =
           ENTITY_TYPES.register("mod_boat",
           () -> EntityType.Builder.of(boatFactory(), MobCategory.MISC)
                           .sized(1.375f, 0.5625f)
                           .eyeHeight(0.5625F)
                           .clientTrackingRange(10)
                           .build(MOD_BOAT_KEY));

    public static final Supplier<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
           ENTITY_TYPES.register("mod_chest_boat",
           () -> EntityType.Builder.of(chestBoatFactory(), MobCategory.MISC)
                           .sized(1.375f, 0.5625f)
                           .eyeHeight(0.5625F)
                           .clientTrackingRange(10)
                           .build(MOD_CHEST_KEY));


    // CUSTOM METHOD - Register all Boat entity types
    private static EntityType.EntityFactory<ModBoatEntity> boatFactory() {
        return (boatEntityType, level) ->
                new ModBoatEntity(boatEntityType, level, ModItems.WALNUT_BOAT);
    }

    // CUSTOM METHOD - Register all Chest Boat entity types
    private static EntityType.EntityFactory<ModChestBoatEntity> chestBoatFactory() {
        return (chestBoatEntityType, level) ->
                new ModChestBoatEntity(chestBoatEntityType, level, ModItems.WALNUT_CHEST_BOAT);
    }

    // CUSTOM METHOD - Registry all entity types on event
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}