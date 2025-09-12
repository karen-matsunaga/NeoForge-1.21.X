package net.karen.mccoursemod.particle;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
           DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, MccourseMod.MOD_ID);

    // Registry all custom particles
    public static final Supplier<SimpleParticleType> BISMUTH_PARTICLES =
           PARTICLE_TYPES.register("bismuth_particles", () -> new SimpleParticleType(true));

    public static final Supplier<SimpleParticleType> ALEXANDRITE_PARTICLES =
           PARTICLE_TYPES.register("alexandrite_particles", () -> new SimpleParticleType(true));

    public static final Supplier<SimpleParticleType> BOUNCY_BALLS_PARTICLES =
           PARTICLE_TYPES.register("bouncy_balls_particles", () -> new SimpleParticleType(true));

    // CUSTOM METHOD - Registry all custom particles types on event
    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}