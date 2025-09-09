package net.karen.mccoursemod.network;

import io.netty.buffer.ByteBuf;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.custom.LevelChargerGenericItem;
import net.karen.mccoursemod.item.custom.LevelChargerSpecifItem;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record LevelChargerInventorySlotPacketPayload(int slotIndex) implements CustomPacketPayload {
    public static final Type<LevelChargerInventorySlotPacketPayload> TYPE =
           new Type<>
                     (ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "level_charger_data"));

    // TYPE
    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // STREAM CODEC
    public static final StreamCodec<ByteBuf, LevelChargerInventorySlotPacketPayload> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.VAR_INT, LevelChargerInventorySlotPacketPayload::slotIndex,
                                 LevelChargerInventorySlotPacketPayload::new);

    // SERVER NETWORK -> Level Charger items
    public static void onLevelChargerServerPayloadHandler(LevelChargerInventorySlotPacketPayload payload,
                                                          IPayloadContext context) {
        context.enqueueWork(() -> {
                    Player player = context.player();
                    if (player instanceof ServerPlayer serverPlayer) {
                        int slotIndex = payload.slotIndex();
                        if (slotIndex < 0 || slotIndex >= serverPlayer.containerMenu.slots.size()) { return; }
                        Slot slot = serverPlayer.containerMenu.getSlot(slotIndex);
                        if (!slot.hasItem()) { return; }
                        ItemStack targetStack = slot.getItem();
                        ItemStack changerStack = serverPlayer.containerMenu.getCarried();
                        if (changerStack.isEmpty() || !(changerStack.is(ModTags.Items.LEVEL_CHARGER_ITEMS))) { return; }
                        if (changerStack.getItem() instanceof LevelChargerGenericItem) {
                            boolean applied = LevelChargerGenericItem.applyTo(player, targetStack, changerStack);
                            if (applied && !serverPlayer.getAbilities().instabuild) {
                                changerStack.shrink(1);
                                serverPlayer.containerMenu.broadcastChanges();
                            }
                        }
                        if (changerStack.getItem() instanceof LevelChargerSpecifItem specif) {
                            boolean applied = specif.applyTo(serverPlayer.level(), player, targetStack, changerStack);
                            if (applied && !serverPlayer.getAbilities().instabuild) {
                                changerStack.shrink(1);
                                serverPlayer.containerMenu.broadcastChanges();
                            }
                        }
                    }
                })
                .exceptionally(e -> { // Handle exception
                    context.disconnect(Component.translatable("mccoursemod.networking.failed", e.getMessage()));
                    return null;
                });
    }
}