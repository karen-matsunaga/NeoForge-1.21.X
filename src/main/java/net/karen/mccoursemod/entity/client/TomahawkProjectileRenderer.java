package net.karen.mccoursemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.custom.TomahawkProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TomahawkProjectileRenderer extends EntityRenderer<TomahawkProjectileEntity, EntityRenderState> {
    private final TomahawkProjectileModel model;

    public TomahawkProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new TomahawkProjectileModel(context.bakeLayer(TomahawkProjectileModel.LAYER_LOCATION));
    }

    @Override
    public @NotNull EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    @Override
    public void render(@NotNull EntityRenderState state, PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        VertexConsumer vertexconsumer =
              ItemRenderer.getFoilBuffer(buffer, this.model.renderType(this.getTextureLocation()),false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(state, poseStack, buffer, packedLight);
    }

    public ResourceLocation getTextureLocation() {
        return ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "textures/entity/tomahawk/tomahawk.png");
    }
}