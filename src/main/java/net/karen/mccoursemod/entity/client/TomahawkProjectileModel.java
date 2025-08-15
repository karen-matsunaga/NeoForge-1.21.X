package net.karen.mccoursemod.entity.client;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TomahawkProjectileModel extends EntityModel<EntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION =
           new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "tomahawk"), "main");
    private final ModelPart tomahawk;

    public TomahawkProjectileModel(ModelPart root) {
        super(root);
        this.tomahawk = root.getChild("tomahawk");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition tomahawk = partdefinition.addOrReplaceChild("tomahawk",
            CubeListBuilder.create(), PartPose.offset(0.0F, 16.5F, 0.0F));

        PartDefinition cube_r1 = tomahawk.addOrReplaceChild("cube_r1",
            CubeListBuilder.create().texOffs(8, 7)
                           .addBox(1.5F, 2.5F, -0.5F, 1.0F, 1.0F, 1.0F,
                                   new CubeDeformation(0.0F)),
                                   PartPose.offsetAndRotation(0.0F, -7.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r2 = tomahawk.addOrReplaceChild("cube_r2",
            CubeListBuilder.create().texOffs(7, 9)
                           .addBox(0.5F, -1.5F, -0.5F, 2.0F, 5.0F, 1.0F,
                                   new CubeDeformation(0.0F)),
                                   PartPose.offsetAndRotation(0.0F, -7.0F, -5.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r3 = tomahawk.addOrReplaceChild("cube_r3",
            CubeListBuilder.create().texOffs(3, 10)
                           .addBox(-2.5F, -1.5F, -0.5F, 1.0F, 4.0F, 1.0F,
                                   new CubeDeformation(0.0F)),
                                   PartPose.offsetAndRotation(0.0F, -7.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r4 = tomahawk.addOrReplaceChild("cube_r4",
            CubeListBuilder.create().texOffs(1, 4)
                           .addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F,
                                   new CubeDeformation(0.0F)),
                                   PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r5 = tomahawk.addOrReplaceChild("cube_r5",
            CubeListBuilder.create().texOffs(18, 1)
                           .addBox(-0.5F, -9.0F, -0.5F, 1.0F, 18.0F, 1.0F,
                                   new CubeDeformation(0.0F)),
                                   PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.7854F, 0.0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(@NotNull EntityRenderState state) {}
}