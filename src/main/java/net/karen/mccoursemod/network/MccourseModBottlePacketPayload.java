package net.karen.mccoursemod.network;

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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import static net.karen.mccoursemod.util.ChatUtils.*;
import static net.karen.mccoursemod.util.ChatUtils.green;
import static net.karen.mccoursemod.util.ChatUtils.player;
import static net.karen.mccoursemod.util.ChatUtils.red;

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

    // SERVER NETWORK -> Mccourse Mod Bottle item
    public static void onMccourseModBottleServerPayloadHandler(MccourseModBottlePacketPayload payload,
                                                               IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                ItemStack stack = serverPlayer.getMainHandItem(); // Has Mccourse Mod Bottle item on main hand
                if (!(stack.getItem() instanceof MccourseModBottleItem)) { return; }
                // Data Component to store and to restore XP, Store Levels on Mccourse Mod Bottle, Player XP
                Integer storedLevels = stack.get(ModDataComponentTypes.STORED_LEVELS);
                if (storedLevels != null && storedLevels >= 0) {
                    int maxLevels = MccourseModBottleItem.storeXp,
                        availableLevels = serverPlayer.experienceLevel;
                    if (serverPlayer.getCooldowns().isOnCooldown(stack)) { // Check if it is already on cooldown
                        player(serverPlayer, "Wait before using again!", yellow);
                        return;
                    }
                    if (payload.actionItem()) {
                        if (availableLevels <= 0) { // Player store XP only has 1+ levels
                            player(serverPlayer, "You have no XP to store!", red);
                            return;
                        }
                        int canStore = Math.min(payload.amount(), Math.min(maxLevels - storedLevels, availableLevels));
                        if (canStore >= 0) {
                            stack.set(ModDataComponentTypes.STORED_LEVELS, storedLevels + canStore);
                            serverPlayer.giveExperienceLevels(-canStore);
                            player(serverPlayer, "Stored " + canStore + " levels!", green);
                        }
                        else { player(serverPlayer, "XP full or insufficient!", red); }
                    }
                    if (!payload.actionItem()) {
                        int toRestore = Math.min(payload.amount(), storedLevels);
                        stack.set(ModDataComponentTypes.STORED_LEVELS, storedLevels - toRestore);
                        serverPlayer.giveExperienceLevels(toRestore);
                        player(serverPlayer, "Restored " + toRestore + " levels!", green);
                    }
                    serverPlayer.getCooldowns().addCooldown(stack, 20); // Applies 1 second cooldown (20 ticks)
                }
                else { // Player store XP only has 1+ levels
                    player(serverPlayer, "No XP to restore!", red);
                }
            }
        })
        .exceptionally(e -> { // Handle exception
            context.disconnect(Component.translatable("mccoursemod.networking.failed", e.getMessage()));
            return null;
        });
    }
}