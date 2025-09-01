package net.karen.mccoursemod.block.custom;

import net.karen.mccoursemod.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Set;

public class KaupenPortalBlock extends Block implements Portal {
    public static BlockPos thisPos;
    public static boolean insideDimension;

    public KaupenPortalBlock(BlockPos pos, boolean insideDim, Properties properties) {
        super(properties);
        thisPos = pos;
        insideDimension = insideDim;
    }

    @Override
    protected void entityInside(@NotNull BlockState state, @NotNull Level level,
                                @NotNull BlockPos pos, @NotNull Entity entity,
                                @NotNull InsideBlockEffectApplier blockEffectApplier) {
        if (!level.isClientSide() && entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, pos);
        }
    }

    @Override
    public int getPortalTransitionTime(@NotNull ServerLevel level, @NotNull Entity entity) {
        return 0;
    }

    @Override
    public @Nullable TeleportTransition getPortalDestination(@NotNull ServerLevel level,
                                                             @NotNull Entity entity,
                                                             @NotNull BlockPos pos) {
        ResourceKey<Level> resourcekey = level.dimension() == ModDimensions.KAUPENDIM_LEVEL_KEY
                                         ? Level.OVERWORLD : ModDimensions.KAUPENDIM_LEVEL_KEY;
        ServerLevel serverLevel = level.getServer().getLevel(resourcekey);
        if (serverLevel == null) {
            return null;
        }
        else {
            boolean flag = resourcekey == ModDimensions.KAUPENDIM_LEVEL_KEY;
            Vec3 vec3 = pos.getBottomCenter();
            float f;
            Set<Relative> set;
            if (flag) {
                if (serverLevel.getBlockState(pos.below()).isAir()) {
                    BlockPos.MutableBlockPos blockPos = pos.mutable();
                    for (int x = -2; x <= 2; x++) {
                        for (int z = -2; z <= 2; z++) {
                            if (level.getBlockState(blockPos.setWithOffset(pos, x, -2, z)).isAir())
                                level.setBlock(blockPos, Blocks.STONE.defaultBlockState(), 3);
                        }
                    }
                }
                if (!serverLevel.getBlockState(pos.above()).isAir()) {
                    vec3 = findFreePos(serverLevel, pos.above()).getBottomCenter();
                }
                f = Direction.WEST.toYRot();
                set = Relative.union(Relative.DELTA, Set.of(Relative.X_ROT));
                if (entity instanceof ServerPlayer) {
                    vec3 = vec3.subtract(0.0, 1.0, 0.0);
                }
            }
            else {
                f = serverLevel.getSharedSpawnAngle();
                set = Relative.union(Relative.DELTA, Relative.ROTATION);
                if (entity instanceof ServerPlayer serverPlayer) {
                    return serverPlayer.findRespawnPositionAndUseSpawnBlock(false,
                                                                            TeleportTransition.DO_NOTHING);
                }
                vec3 = entity.adjustSpawnLocation(serverLevel, pos).getBottomCenter();
            }
            return new TeleportTransition(serverLevel, vec3, Vec3.ZERO, f,
                                          0.0F, set,
                                          TeleportTransition.PLAY_PORTAL_SOUND
                                                            .then(TeleportTransition.PLACE_PORTAL_TICKET));
        }
    }

    @Override
    public @NotNull Transition getLocalTransition() {
        return Portal.Transition.NONE;
    }

    // CUSTOM METHOD - Player free position
    public static BlockPos findFreePos(ServerLevelAccessor level, BlockPos pos) {
        BlockPos.MutableBlockPos blockPos = pos.mutable();
        int i = 0;
        do {
            i++;
            blockPos.setWithOffset(pos, 0, i, 0);
        }
        while (!(level.getBlockState(blockPos).isAir() && level.getBlockState(blockPos.below()).isAir()));
        return blockPos;
    }
}