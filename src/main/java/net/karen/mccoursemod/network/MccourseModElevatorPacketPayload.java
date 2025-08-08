package net.karen.mccoursemod.network;

import io.netty.buffer.ByteBuf;
import net.karen.mccoursemod.MccourseMod;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record MccourseModElevatorPacketPayload(boolean bool) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<MccourseModElevatorPacketPayload> TYPE =
           new CustomPacketPayload.Type<>
               (ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "mccoursemod_elevator_data"));

    // Each pair of elements defines the stream codec of the element to encode/decode and the getter for the element to encode
    // 'bool' will be encoded and decoded as a boolean
    // The final parameter takes in the previous parameters in the order they are provided to construct the payload object
    public static final StreamCodec<ByteBuf, MccourseModElevatorPacketPayload> STREAM_CODEC = StreamCodec.composite(
           ByteBufCodecs.BOOL, MccourseModElevatorPacketPayload::bool, MccourseModElevatorPacketPayload::new);

    @Override
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
       return TYPE;
    }
}