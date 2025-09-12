package net.karen.mccoursemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.custom.MagicProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class MagicProjectileRenderer extends EntityRenderer<MagicProjectileEntity, EntityRenderState> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                 "textures/entity/magic_projectile/magic_projectile.png");
    private final MagicProjectileModel model;
    private MagicProjectileEntity entity;
    private float partial;

    public MagicProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new MagicProjectileModel(context.bakeLayer(MagicProjectileModel.MAGIC_PROJECTILE_LAYER));
        this.shadowRadius = 0.5f;
    }

    @Override
    public @NotNull EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    @Override
    public void extractRenderState(@NotNull MagicProjectileEntity entity,
                                   @NotNull EntityRenderState reusedState, float partialTick) {
        this.entity = entity;
        this.partial = partialTick;
        super.extractRenderState(entity, reusedState, partialTick);
    }

    @Override
    public void render(@NotNull EntityRenderState state, PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partial, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partial, entity.xRotO, entity.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer =
              ItemRenderer.getFoilBuffer(buffer, this.model.renderType(this.getTextureLocation()),
                                         false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(state, poseStack, buffer, packedLight);
    }

    // CUSTOM METHOD - Magic texture
    public ResourceLocation getTextureLocation() { return TEXTURE; }
}