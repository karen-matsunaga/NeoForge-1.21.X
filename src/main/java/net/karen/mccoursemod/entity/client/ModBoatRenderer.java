package net.karen.mccoursemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ModBoatRenderer extends BoatRenderer {
    private final Model waterPatchModel;
    private final ResourceLocation texture;
    private final EntityModel<BoatRenderState> model;

    public ModBoatRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayer) {
        super(context, modelLayer);
        this.texture = modelLayer.model().withPath((string) -> "textures/entity/" + string + ".png");
        this.waterPatchModel = new Model.Simple(context.bakeLayer(ModelLayers.BOAT_WATER_PATCH),
                                                                  location -> RenderType.waterMask());
        this.model = new BoatModel(context.bakeLayer(modelLayer));
    }

    protected @NotNull EntityModel<BoatRenderState> model() {
        return this.model;
    }

    protected @NotNull RenderType renderType() {
        return this.model.renderType(this.texture);
    }

    protected void renderTypeAdditions(BoatRenderState state, @NotNull PoseStack stack,
                                       @NotNull MultiBufferSource source, int i) {
        if (!state.isUnderWater) {
            this.waterPatchModel.renderToBuffer(stack, source.getBuffer(this.waterPatchModel.renderType(this.texture)),
                                                i, OverlayTexture.NO_OVERLAY);
        }
    }
}