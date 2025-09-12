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

public class MagicProjectileModel extends EntityModel<EntityRenderState> {
    private final ModelPart bb_main;

    public static final ModelLayerLocation MAGIC_PROJECTILE_LAYER = new ModelLayerLocation(
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "magic_projectile_layer"), "main");

    public MagicProjectileModel(ModelPart root) {
        super(root);
        this.bb_main = root.getChild("bb_main"); }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main =
            partdefinition.addOrReplaceChild("bb_main",
                                             CubeListBuilder.create()
                                                            .texOffs(0, 0)
                                                            .addBox(-1.0F, -4.0F, -1.0F,
                                                                    2.0F, 2.0F, 2.0F,
                                                                    new CubeDeformation(0.0F))
                                                            .texOffs(8, 0)
                                                            .addBox(-1.0F, -5.0F, 0.0F,
                                                                    2.0F, 4.0F, 0.0F,
                                                                    new CubeDeformation(0.0F))
                                                            .texOffs(0, 6)
                                                            .addBox(-2.0F, -4.0F, 0.0F,
                                                                    4.0F, 2.0F, 0.0F,
                                                                    new CubeDeformation(0.0F)),
                                             PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 =
            bb_main.addOrReplaceChild("cube_r1",
                                      CubeListBuilder.create()
                                                     .texOffs(0, 4)
                                                     .addBox(-2.0F, -1.0F, 0.0F,
                                                             4.0F, 2.0F, 0.0F,
                                                             new CubeDeformation(0.0F))
                                                     .texOffs(0, 8)
                                                     .addBox(-1.0F, -2.0F, 0.0F,
                                                             2.0F, 4.0F, 0.0F,
                                                             new CubeDeformation(0.0F)),
                                      PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F,
                                                                 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(@NotNull EntityRenderState renderState) {}
}