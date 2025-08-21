package net.karen.mccoursemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.custom.TomahawkProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class TomahawkProjectileRenderer extends EntityRenderer<TomahawkProjectileEntity,
                                                TomahawkProjectileRenderState> {
    private final TomahawkProjectileModel model;
    private TomahawkProjectileEntity entity;
    private float partial;

    public TomahawkProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new TomahawkProjectileModel(context.bakeLayer(TomahawkProjectileModel.LAYER_LOCATION));
    }

    @Override
    public @NotNull TomahawkProjectileRenderState createRenderState() {
        return new TomahawkProjectileRenderState();
    }

    @Override
    public void extractRenderState(@NotNull TomahawkProjectileEntity entity,
                                   @NotNull TomahawkProjectileRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        this.entity = entity;
        this.partial = partialTick;
        state.xRot = entity.getXRot(partialTick);
        state.yRot = entity.getYRot(partialTick);
    }

    @Override
    public void render(@NotNull TomahawkProjectileRenderState state, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        if (!entity.onGround()) {
            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partial, entity.yRotO, entity.getYRot())));
            poseStack.mulPose(Axis.XP.rotationDegrees(entity.getRenderingRotation() * 5f));
            poseStack.translate(0, -1.0f, 0);
        }
        else {
            poseStack.mulPose(Axis.YP.rotationDegrees(entity.groundedOffset.y));
            poseStack.mulPose(Axis.XP.rotationDegrees(entity.groundedOffset.x));
            poseStack.translate(0, -1.0f, 0);
        }
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