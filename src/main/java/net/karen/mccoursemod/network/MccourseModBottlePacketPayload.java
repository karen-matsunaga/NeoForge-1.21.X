package net.karen.mccoursemod.network;

import io.netty.buffer.ByteBuf;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.item.custom.MccourseModBottleItem;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import java.util.function.IntFunction;
import static net.karen.mccoursemod.util.ChatUtils.*;

public record MccourseModBottlePacketPayload(MccourseModBottleEnum actionItem,
                                             int amount) implements CustomPacketPayload {

    public enum MccourseModBottleEnum implements StringRepresentable {
        STORED("Stored"),
        RESTORED("Restored");

        // Mccourse Mod Bottle Enum -> ID
        public static final IntFunction<MccourseModBottleEnum> BY_ID =
               ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        // Mccourse Mod Bottle Enum -> STREAM CODEC
        public static final StreamCodec<ByteBuf, MccourseModBottleEnum> STREAM_CODEC =
               ByteBufCodecs.idMapper(BY_ID, Enum::ordinal);

        private final String name;

        MccourseModBottleEnum(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() { return this.name; }
    }

    // Mccourse Mod Bottle PACKET PAYLOAD -> TYPE
    public static final CustomPacketPayload.Type<MccourseModBottlePacketPayload> TYPE =
           new CustomPacketPayload.Type<>
               (ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "mccoursemod_bottle_data"));

    // Mccourse Mod Bottle PACKET PAYLOAD -> STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, MccourseModBottlePacketPayload> STREAM_CODEC =
           StreamCodec.composite(MccourseModBottleEnum.STREAM_CODEC, MccourseModBottlePacketPayload::actionItem,
                                 ByteBufCodecs.INT, MccourseModBottlePacketPayload::amount,
                                 MccourseModBottlePacketPayload::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() { return TYPE; }

    // SERVER NETWORK -> Mccourse Mod Bottle item
    public static void onMccourseModBottleServerPayloadHandler(MccourseModBottlePacketPayload payload,
                                                               IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                ItemStack stack = serverPlayer.getMainHandItem(); // Has Mccourse Mod Bottle item on main hand
                if (!(stack.getItem() instanceof MccourseModBottleItem self)) { return; }
                // Data Component to store and to restore XP, Store Levels on Mccourse Mod Bottle, Player XP
                Integer storedLevels = stack.get(ModDataComponentTypes.STORED_LEVELS);
                if (storedLevels != null && storedLevels >= 0) { // STORED
                    int maxLevels = self.storeXp,
                        availableLevels = serverPlayer.experienceLevel;
                    if (serverPlayer.getCooldowns().isOnCooldown(stack)) { // Check if it is already on cooldown
                        player(serverPlayer, "Wait before using again!", yellow);
                        return;
                    }
                    // Mccourse Mod Bottle Enum -> STORED
                    if (payload.actionItem == MccourseModBottleEnum.STORED) {
                        if (availableLevels <= 0) { // Player store XP only has 1+ levels
                            player(serverPlayer, "You have no XP to store!", red);
                            return;
                        }
                        int canStore = Math.min(payload.amount(), Math.min(maxLevels - storedLevels, availableLevels));
                        if (canStore > 0) {
                            stack.set(ModDataComponentTypes.STORED_LEVELS, storedLevels + canStore);
                            serverPlayer.giveExperienceLevels(-canStore);
                            player(serverPlayer, "Stored " + canStore + " levels!", green);
                        }
                        else { player(serverPlayer, "XP full or insufficient!", red); }
                    }
                    // Mccourse Mod Bottle Enum -> RESTORED
                    if (payload.actionItem == MccourseModBottleEnum.RESTORED) {
                        int canRestore = Math.min(payload.amount(), storedLevels);
                        if (canRestore > 0) {
                            stack.set(ModDataComponentTypes.STORED_LEVELS, storedLevels - canRestore);
                            serverPlayer.giveExperienceLevels(canRestore);
                            player(serverPlayer, "Restored " + canRestore + " levels!", green);
                        }
                        else { player(serverPlayer, "XP restored!", red); }
                    }
                    serverPlayer.getCooldowns().addCooldown(stack, 20); // Applies 1 second cooldown (20 ticks)
                }
            }
        })
        .exceptionally(e -> { // Handle exception
            context.disconnect(Component.translatable("mccoursemod.networking.failed", e.getMessage()));
            return null;
        });
    }
}