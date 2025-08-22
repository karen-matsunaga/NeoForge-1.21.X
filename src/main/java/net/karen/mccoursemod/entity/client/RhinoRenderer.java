package net.karen.mccoursemod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.custom.RhinoEntity;
import net.karen.mccoursemod.entity.variant.RhinoVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class RhinoRenderer extends MobRenderer<RhinoEntity, RhinoRenderState, RhinoModel> {
    private static final Map<RhinoVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RhinoVariant.class), map -> {
                map.put(RhinoVariant.DEFAULT,
                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "textures/entity/rhino/rhino.png"));
                map.put(RhinoVariant.WHITE,
                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "textures/entity/rhino/white_rhino.png"));
            });

    public RhinoRenderer(EntityRendererProvider.Context context) {
        super(context, new RhinoModel(context.bakeLayer(RhinoModel.LAYER_LOCATION)), 2f);
    }

    @Override
    public @NotNull RhinoRenderState createRenderState() {
        return new RhinoRenderState();
    }

    @Override
    public void render(RhinoRenderState entity, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource source, int i) {
        if (entity.isBaby) { poseStack.scale(0.45f, 0.45f, 0.45f); } // Rhino's baby
        super.render(entity, poseStack, source, i);
    }

    @Override
    public void extractRenderState(@NotNull RhinoEntity entity,
                                   @NotNull RhinoRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.variant = entity.getVariant();
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.attackAnimationState.copyFrom(entity.attackAnimationState);
        state.sitAnimationState.copyFrom(entity.sitAnimationState);
    }

    // DEFAULT METHOD - RHINO renderer texture on game
    @Override
    public @NotNull ResourceLocation getTextureLocation(RhinoRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }
}