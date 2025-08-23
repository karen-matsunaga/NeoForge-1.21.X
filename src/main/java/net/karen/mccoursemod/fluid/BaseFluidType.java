package net.karen.mccoursemod.fluid;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class BaseFluidType extends FluidType {
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;
    private final ResourceLocation overlayTexture;
    private final int tintColor;
    private final Vector3f fogColor;

    public BaseFluidType(final ResourceLocation stillTexture, final ResourceLocation flowingTexture,
                         final ResourceLocation overlayTexture, final int tintColor, final Vector3f fogColor,
                         final Properties properties) {
        super(properties);
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
        this.overlayTexture = overlayTexture;
        this.tintColor = tintColor;
        this.fogColor = fogColor;
    }

    public IClientFluidTypeExtensions getClientFluidTypeExtensions() {
        return new IClientFluidTypeExtensions() {
            @Override
            public int getTintColor() {
                return tintColor;
            }

            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return stillTexture;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return flowingTexture;
            }

            @Override
            public @Nullable ResourceLocation getOverlayTexture() {
                return overlayTexture;
            }

            @Override
            public @NotNull Vector4f modifyFogColor(@NotNull Camera camera, float partialTick,
                                                    @NotNull ClientLevel level, int renderDistance,
                                                    float darkenWorldAmount, @NotNull Vector4f fluidFogColor) {
                return new Vector4f(fogColor.x, fogColor.y, fogColor.z, 1f);
            }

            @Override
            public void modifyFogRender(@NotNull Camera camera, @Nullable FogEnvironment environment,
                                        float renderDistance, float partialTick, @NotNull FogData fogData) {
                var old = RenderSystem.getShaderFog();
                if (old != null) { RenderSystem.setShaderFog(old); }
            }
        };
    }
}