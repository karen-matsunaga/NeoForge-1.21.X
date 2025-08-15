package net.karen.mccoursemod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.GeckoVariant;
import net.karen.mccoursemod.entity.custom.GeckoEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class GeckoRenderer extends MobRenderer<GeckoEntity, GeckoRenderState, GeckoModel> {
    private static final Map<GeckoVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GeckoVariant.class), map -> {
                      map.put(GeckoVariant.BLUE, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                              "textures/entity/gecko/gecko_blue.png"));
                      map.put(GeckoVariant.GREEN, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                              "textures/entity/gecko/gecko_green.png"));
                      map.put(GeckoVariant.PINK, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                              "textures/entity/gecko/gecko_pink.png"));
                      map.put(GeckoVariant.BROWN, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                              "textures/entity/gecko/gecko_brown.png"));
            });

    public GeckoRenderer(EntityRendererProvider.Context context) {
        super(context, new GeckoModel(context.bakeLayer(GeckoModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(GeckoRenderState entity) {
        return LOCATION_BY_VARIANT.get(entity.variant);
    }

    @Override
    public void render(GeckoRenderState renderState, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (renderState.isBaby) { poseStack.scale(0.45f, 0.45f, 0.45f); }
        else { poseStack.scale(1f, 1f, 1f); }
        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull GeckoRenderState createRenderState() {
        return new GeckoRenderState();
    }

    @Override
    public void extractRenderState(@NotNull GeckoEntity entity,
                                   @NotNull GeckoRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.idleAnimationState.copyFrom(entity.idleAnimationState);
        reusedState.variant = entity.getVariant();
    }
}