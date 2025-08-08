package net.karen.mccoursemod.network;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.custom.MccourseElevatorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {
    public static void handleKeyInput(MccourseModElevatorPacketPayload payload,
                                      IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                BlockPos pos = BlockPos.containing(serverPlayer.getX(), serverPlayer.getY() - 1, serverPlayer.getZ());
                if (serverPlayer.level().getBlockState(pos).getBlock() == ModBlocks.MCCOURSEMOD_ELEVATOR.get()) {
                    if (payload.bool()) { MccourseElevatorBlock.blockUp(serverPlayer); } // Player UP
                    else { MccourseElevatorBlock.blockDown(serverPlayer); } // Player DOWN
                }
            }
        })
        .exceptionally(e -> { // Handle exception
             context.disconnect(Component.translatable("mccoursemod.networking.failed", e.getMessage()));
             return null;
        });
    }
}