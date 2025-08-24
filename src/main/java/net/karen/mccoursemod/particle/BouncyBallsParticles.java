package net.karen.mccoursemod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

public class BouncyBallsParticles extends TextureSheetParticle {
    protected BouncyBallsParticles(ClientLevel level, double x, double y, double z,
                                   SpriteSet spriteSet, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

        // Position of particle
        this.friction = 0.8f;
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;

        // Size of particle
        this.quadSize *= 0.75f; // 75% of size
        this.lifetime = 20; // 20 ticks
        this.setSpriteFromAge(spriteSet);

        // RGB colors of particle
        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    // Return of particle
    @Override
    public @NotNull ParticleRenderType getRenderType() { return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT; }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        // Created constructor to set spriteSet
        public Provider(SpriteSet spriteSet) { this.spriteSet = spriteSet; }

        // Created method to create custom particles
        public Particle createParticle(@NotNull SimpleParticleType particleType,
                                       @NotNull ClientLevel level, double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new BouncyBallsParticles(level, x, y, z, this.spriteSet, xSpeed, ySpeed, zSpeed);
        }
    }
}