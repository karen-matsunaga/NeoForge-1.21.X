package net.karen.mccoursemod.network;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record MccourseModBottlePacketPayload(boolean actionItem,
                                             int amount) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<MccourseModBottlePacketPayload> TYPE =
            new CustomPacketPayload.Type<>
                (ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "mccoursemod_bottle_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, MccourseModBottlePacketPayload> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.BOOL, MccourseModBottlePacketPayload::actionItem,
                                 ByteBufCodecs.INT, MccourseModBottlePacketPayload::amount,
                                 MccourseModBottlePacketPayload::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}