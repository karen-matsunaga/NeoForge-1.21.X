package net.karen.mccoursemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.MapCodec;
import net.karen.mccoursemod.MccourseMod;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Set;

public class ShieldSpecialModelRenderer implements SpecialModelRenderer<DataComponentMap> {
    private final ShieldModel model;
    public static final Material ALEXANDRITE_SHIELD_BASE =
           new Material(Sheets.SHIELD_SHEET,
                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                        "entity/alexandrite_shield_base"));
    public static final Material ALEXANDRITE_SHIELD_BASE_NO_PATTERN =
           new Material(Sheets.SHIELD_SHEET,
                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                        "entity/alexandrite_shield_base_nopattern"));

    public ShieldSpecialModelRenderer(ShieldModel model) {
        this.model = model;
    }

    @Nullable
    public DataComponentMap extractArgument(ItemStack stack) {
        return stack.immutableComponents();
    }

    public void render(@Nullable DataComponentMap components,
                       @NotNull ItemDisplayContext displayContext,
                       PoseStack poseStack, @NotNull MultiBufferSource source,
                       int i, int i1, boolean b) {
        BannerPatternLayers bannerpatternlayers =
              components != null ? components.getOrDefault(DataComponents.BANNER_PATTERNS,
                                                           BannerPatternLayers.EMPTY)
                                 : BannerPatternLayers.EMPTY;
        DyeColor dyecolor = components != null ? components.get(DataComponents.BASE_COLOR) : null;
        boolean flag = !bannerpatternlayers.layers().isEmpty() || dyecolor != null;
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = flag ? ALEXANDRITE_SHIELD_BASE : ALEXANDRITE_SHIELD_BASE_NO_PATTERN;
        VertexConsumer vertexconsumer =
              material.sprite().wrap(ItemRenderer.getFoilBuffer(source, this.model.renderType(material.atlasLocation()),
                                                                displayContext == ItemDisplayContext.GUI, b));
        this.model.handle().render(poseStack, vertexconsumer, i, i1);
        if (flag) {
            BannerRenderer.renderPatterns(poseStack, source, i, i1, this.model.plate(), material, false,
                                          Objects.requireNonNullElse(dyecolor, DyeColor.WHITE),
                                          bannerpatternlayers, b, false);
        }
        else { this.model.plate().render(poseStack, vertexconsumer, i, i1); }
        poseStack.popPose();
    }

    public void getExtents(@NotNull Set<Vector3f> vector3fs) {
        PoseStack posestack = new PoseStack();
        posestack.scale(1.0F, -1.0F, -1.0F);
        this.model.root().getExtentsForGui(posestack, vector3fs);
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final ShieldSpecialModelRenderer.Unbaked INSTANCE = new ShieldSpecialModelRenderer.Unbaked();
        public static final MapCodec<ShieldSpecialModelRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        public @NotNull MapCodec<ShieldSpecialModelRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public SpecialModelRenderer<?> bake(EntityModelSet entityModelSet) {
            return new ShieldSpecialModelRenderer(new ShieldModel(entityModelSet.bakeLayer(ModelLayers.SHIELD)));
        }
    }
}