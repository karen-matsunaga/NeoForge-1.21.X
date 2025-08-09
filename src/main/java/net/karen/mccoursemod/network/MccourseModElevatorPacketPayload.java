package net.karen.mccoursemod.network;

import io.netty.buffer.ByteBuf;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.custom.MccourseElevatorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record MccourseModElevatorPacketPayload(boolean bool) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<MccourseModElevatorPacketPayload> TYPE =
           new CustomPacketPayload.Type<>
               (ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "mccoursemod_elevator_data"));

    // Each pair of elements defines the stream codec of the element to encode/decode and the getter for the element to encode
    // 'bool' will be encoded and decoded as a boolean
    // The final parameter takes in the previous parameters in the order they are provided to construct the payload object
    public static final StreamCodec<ByteBuf, MccourseModElevatorPacketPayload> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.BOOL, MccourseModElevatorPacketPayload::bool,
                                 MccourseModElevatorPacketPayload::new);

    @Override
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
       return TYPE;
    }

    // SERVER NETWORK -> Mccourse Mod Elevator block
    public static void onMccourseModElevatorServerPayloadHandler(MccourseModElevatorPacketPayload payload,
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