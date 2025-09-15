package net.karen.mccoursemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class HammerItem extends Item {
    private final int radius;
    private final int argbColors;
    private final int textColor;

    public HammerItem(ToolMaterial material, float attackDamage,
                      float attackSpeed, Properties settings, int radius,
                      int argbColors, int textColor) {
        super(material.applyToolProperties(settings, BlockTags.MINEABLE_WITH_PICKAXE,
                                           attackDamage, attackSpeed, 0F));
        this.radius = radius;
        this.argbColors = argbColors;
        this.textColor = textColor;
    }

    // DEFAULT METHOD - RADIUS value
    public int getRadius() {
        return radius;
    }

    // DEFAULT METHOD - BLOCK Bounding Box ARGB value
    public int getArgbColors() {
        return argbColors;
    }

    // DEFAULT METHOD - TEXT COLOR value
    public int getTextColor() {
        return textColor;
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos,
                                                        ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();
        BlockHitResult traceResult =
             player.level().clip(new ClipContext(player.getEyePosition(1F),
                                                (player.getEyePosition(1F)
                                                       .add(player.getViewVector(1F)
                                                       .scale(6F))),
                                 ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

        if (traceResult.getType() == HitResult.Type.MISS) {
            return positions;
        }

        Direction direction = traceResult.getDirection();
        int blockPosX = initalBlockPos.getX();
        int blockPosY = initalBlockPos.getY();
        int blockPosZ = initalBlockPos.getZ();

        if (direction == Direction.DOWN || direction == Direction.UP) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(blockPosX + x, blockPosY, blockPosZ + y));
                }
            }
        }

        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(blockPosX + x, blockPosY + y, blockPosZ));
                }
            }
        }

        if (direction == Direction.EAST || direction == Direction.WEST) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(blockPosX, blockPosY + y, blockPosZ + x));
                }
            }
        }
        return positions;
    }

    // CUSTOM METHOD - Hammer BLOCK BOUNDING BOX area
    /* Credits by nanite (Just Hammers) GNU General Public License v3.0 - https://github.com/nanite/JustHammers/blob/main/LICENSE.md
       https://github.com/nanite/JustHammers/blob/main/common/src/main/java/pro/mikey/justhammers/HammerItem.java
    */
    public static BoundingBox getAreaOfEffect(BlockPos blockPos,
                                              Direction direction, int radius) {
        int size = radius / 2;
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        return switch (direction) {
            // DOWN and UP directions
            case DOWN, UP -> new BoundingBox(x - size, y, z - size,
                                             x + size, y, z + size);

            // NORTH and SOUTH directions
            case NORTH, SOUTH -> new BoundingBox(x - size, y - size, z,
                                                 x + size, y + size, z);

            // WEST and EAST directions
            case WEST, EAST -> new BoundingBox(x, y - size, z - size,
                                               x, y + size, z + size);
        };
    }

    // DEFAULT METHOD - Hammer item color name
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return componentTranslatableIntColor(this.getDescriptionId(), getTextColor());
    }
}