package net.karen.mccoursemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import java.util.ArrayList;
import java.util.List;

public class HammerItem extends Item {
    private final int radius;

    public HammerItem(ToolMaterial material, float attackDamage,
                      float attackSpeed, Properties settings, int radius) {
        super(material.applyToolProperties(settings, BlockTags.MINEABLE_WITH_PICKAXE,
                                           attackDamage, attackSpeed, 0F));
        this.radius = radius;
    }

    // DEFAULT METHOD - RADIUS value
    public int getRadius() {
        return radius;
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos,
                                                        ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();
        BlockHitResult traceResult =
             player.level().clip(new ClipContext(player.getEyePosition(1f),
                                                (player.getEyePosition(1f)
                                                       .add(player.getViewVector(1f)
                                                       .scale(6f))),
                                 ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

        if (traceResult.getType() == HitResult.Type.MISS) {
            return positions;
        }

        if (traceResult.getDirection() == Direction.DOWN || traceResult.getDirection() == Direction.UP) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x,
                                               initalBlockPos.getY(),
                                               initalBlockPos.getZ() + y));
                }
            }
        }

        if (traceResult.getDirection() == Direction.NORTH || traceResult.getDirection() == Direction.SOUTH) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x,
                                               initalBlockPos.getY() + y,
                                               initalBlockPos.getZ()));
                }
            }
        }

        if (traceResult.getDirection() == Direction.EAST || traceResult.getDirection() == Direction.WEST) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX(),
                                               initalBlockPos.getY() + y,
                                               initalBlockPos.getZ() + x));
                }
            }
        }
        return positions;
    }

    // CUSTOM METHOD - Hammer "Highlighter" BLOCK BOUNDING BOX area
    public static BoundingBox getAreaOfEffect(BlockPos blockPos, Direction direction, int radius) {
        int size = (radius / 2);
        int offset = size - 1;
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        return switch (direction) {
            // DOWN and UP directions
            case DOWN, UP -> new BoundingBox(x - size, y, z - size,
                                             x + size, y, z + size);
            // NORTH and SOUTH directions
            case NORTH, SOUTH -> new BoundingBox(x - size, y - size + offset, z,
                                                 x + size, y + size + offset, z);
            // WEST and EAST directions
            case WEST, EAST -> new BoundingBox(x, y - size + offset, z - size,
                                               x, y + size + offset, z + size);
        };
    }
}