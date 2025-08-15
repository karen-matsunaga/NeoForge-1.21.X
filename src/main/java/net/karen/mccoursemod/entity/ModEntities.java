package net.karen.mccoursemod.entity;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.custom.GeckoEntity;
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

    // Registry all custom Entities
    public static ResourceKey<EntityType<?>> GECKO_KEY =
           ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("gecko"));

    public static final Supplier<EntityType<GeckoEntity>> GECKO =
           ENTITY_TYPES.register("gecko", () -> EntityType.Builder.of(GeckoEntity::new, MobCategory.CREATURE)
                                                                .sized(0.75f, 0.35f).build(GECKO_KEY));

    // CUSTOM METHOD - Registry all entity types on event
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}