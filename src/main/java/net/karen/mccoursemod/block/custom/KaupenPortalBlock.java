package net.karen.mccoursemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.EndPlatformFeature;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KaupenPortalBlock extends Block implements Portal {
    public static final MapCodec<KaupenPortalBlock> CODEC = simpleCodec(KaupenPortalBlock::new);

    public KaupenPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends Block> codec() { return CODEC; }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level,
                                                        @NotNull BlockPos pos, @NotNull Player player,
                                                        @NotNull BlockHitResult hitResult) {
        if (player.canUsePortal(false)) {
            player.setAsInsidePortal(this, pos);
            return InteractionResult.SUCCESS;
        }
        else { return InteractionResult.CONSUME; }
    }

    @Override
    public int getPortalTransitionTime(@NotNull ServerLevel level, @NotNull Entity entity) { return 0; }

    @Nullable
    public TeleportTransition getPortalDestination(ServerLevel level, @NotNull Entity entity, @NotNull BlockPos pos) {
        ResourceKey<Level> resourcekey = level.dimension() == ModDimensions.KAUPENDIM_LEVEL_KEY
                                         ? Level.OVERWORLD : ModDimensions.KAUPENDIM_LEVEL_KEY;
        ServerLevel serverlevel = level.getServer().getLevel(resourcekey);
        BlockPos spawnPos = ServerLevel.END_SPAWN_POINT;
        if (serverlevel == null) { return null; }
        else {
            Vec3 vec3 = spawnPos.getCenter();
            BlockPos platformPos = BlockPos.containing(vec3).below();
            EndPlatformFeature.createEndPlatform(serverlevel, platformPos, true);
            float yaw = Direction.WEST.toYRot();
            TeleportTransition transition =
                    new TeleportTransition(serverlevel, vec3, entity.getKnownMovement(), yaw, entity.getXRot(),
                                           TeleportTransition.PLAY_PORTAL_SOUND
                                                             .then(TeleportTransition.PLACE_PORTAL_TICKET));
            Player teleportedPlayer = (Player) entity.teleport(transition);
            if (teleportedPlayer != null) {
                teleportedPlayer.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
            }
            BlockPos kaupenPos = spawnPos.relative(entity.getDirection());
            while (serverlevel.isEmptyBlock(kaupenPos.below())) {
                kaupenPos = kaupenPos.below();
            }
            if (serverlevel.isEmptyBlock(kaupenPos)) {
                serverlevel.setBlock(kaupenPos, ModBlocks.KAUPEN_PORTAL.get().defaultBlockState(), 3);
            }
            return transition;
        }
    }

    @Override
    public @NotNull Transition getLocalTransition() { return Portal.Transition.NONE; }
}