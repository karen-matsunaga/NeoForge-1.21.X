package net.karen.mccoursemod.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.karen.mccoursemod.item.custom.HammerItem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.util.Iterator;

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
}