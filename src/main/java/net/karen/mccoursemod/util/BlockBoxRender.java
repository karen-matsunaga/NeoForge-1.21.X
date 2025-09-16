package net.karen.mccoursemod.util;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.PoseStack;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.custom.HammerItem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalDouble;

/* Credits by nanite (Just Hammers) GNU General Public License v3.0 - https://github.com/nanite/JustHammers/blob/main/LICENSE.md
   https://github.com/nanite/JustHammers/blob/main/common/src/main/java/pro/mikey/justhammers/client/SelectionOutlineRender.java */
public class BlockBoxRender {
    public static void render(ClientLevel world, Camera camera,
                              PoseStack poseStack, MultiBufferSource consumers) {
        Player player = Minecraft.getInstance().player;
        if (world == null || player == null || player.isCrouching()) { return; }
        ItemStack heldItem = player.getMainHandItem();
        ItemStack offHandItem = player.getOffhandItem();
        if (heldItem.isEmpty() && offHandItem.isEmpty()) { return; }
        Item heldHand = heldItem.getItem();
        Item offHand = offHandItem.getItem();
        if (!(heldHand instanceof HammerItem) && !(offHand instanceof HammerItem)) { return; }
        HitResult blockHitResult = Minecraft.getInstance().hitResult;
        if (blockHitResult == null || blockHitResult.getType() != HitResult.Type.BLOCK) { return; }
        Item item = heldHand instanceof HammerItem ? heldHand : offHand;
        ItemStack itemStack = heldHand instanceof HammerItem ? heldItem : offHandItem;
        HammerItem hammer = (HammerItem) item;
        BlockPos blockPos = ((BlockHitResult) blockHitResult).getBlockPos();
        Direction direction = ((BlockHitResult) blockHitResult).getDirection();
        BlockState block = world.getBlockState(blockPos);
        Tool toolComponent = itemStack.get(DataComponents.TOOL);
        if (toolComponent == null) { return; }
        if (!toolComponent.isCorrectForDrops(block)) { return; }
        BoundingBox boundingBox = HammerItem.getAreaOfEffect(blockPos, direction, hammer.getRadius() * 2 + 1);
        poseStack.pushPose();
        double camX = -camera.getPosition().x();
        double camY = -camera.getPosition().y();
        double camZ = -camera.getPosition().z();
        poseStack.translate(camX, camY, camZ);
        Iterator<BlockPos> blockPosStream = BlockPos.betweenClosedStream(boundingBox).iterator();
        while (blockPosStream.hasNext()) {
            BlockPos pos = blockPosStream.next();
            if (pos.equals(blockPos)) { continue; }
            BlockState blockState = world.getBlockState(pos);
            FluidState fluidState = blockState.getFluidState();
            if (blockState.isAir() || (!fluidState.isEmpty())) { continue; }
            VoxelShape renderShape = blockState.getVisualShape(world, pos, CollisionContext.empty());
            poseStack.pushPose();
            poseStack.translate(pos.getX(), pos.getY(), pos.getZ());
            ShapeRenderer.renderShape(poseStack, consumers.getBuffer(RenderType.lines()),
                                      renderShape, 0, 0, 0, hammer.getArgbColors());
            poseStack.popPose();
        }
        poseStack.popPose();
    }

    // CUSTOM Render Pipeline
    public static final RenderPipeline LINES_NO_DEPTH_RENDER_PIPELINE =
           RenderPipeline.builder(RenderPipelines.LINES_SNIPPET)
                         .withLocation(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "pipeline/lines_no_depth"))
                         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
                         .build();

    // CUSTOM Render Type
    public static final RenderType LINES_NO_DEPTH_RENDER_TYPE =
           RenderType.create(MccourseMod.MOD_ID + "lines_no_depth", 3 * 512,
                             LINES_NO_DEPTH_RENDER_PIPELINE,
                             RenderType.CompositeState.builder()
                                       .setLayeringState(RenderType.VIEW_OFFSET_Z_LAYERING)
                                       .setLineState(new RenderType.LineStateShard(OptionalDouble.of(2)))
                                       .createCompositeState(false));

    // CUSTOM XRAY block bounding boxes
    public static final Map<TagKey<Block>, Integer> renderColors =
           Map.ofEntries(Map.entry(Tags.Blocks.ORES_COAL, color(169, 169,169)),
                         Map.entry(Tags.Blocks.ORES_COPPER, color(255, 140, 0)),
                         Map.entry(Tags.Blocks.ORES_DIAMOND, color(0, 254, 255)),
                         Map.entry(Tags.Blocks.ORES_EMERALD, color(49, 200, 49)),
                         Map.entry(Tags.Blocks.ORES_GOLD, color(255, 215, 0)),
                         Map.entry(Tags.Blocks.ORES_IRON, color(211, 211, 211)),
                         Map.entry(Tags.Blocks.ORES_LAPIS, color(0, 0, 255)),
                         Map.entry(Tags.Blocks.ORES_REDSTONE, color(179, 0, 0)),
                         Map.entry(Tags.Blocks.ORES_NETHERITE_SCRAP, color(210, 44, 248)),
                         Map.entry(ModTags.Blocks.MCCOURSE_MOD_ORES, color(255, 192, 235)),
                         Map.entry(ModTags.Blocks.SPECIAL_METAL_DETECTOR_VALUABLES, color(204, 0, 0)));

    // CUSTOM METHOD - Block bounding box colors
    public static int color(int red, int green, int blue) {
        return ARGB.color(255, red, green, blue);
    }
}