package net.karen.mccoursemod.network;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.util.ChatUtils;
import net.karen.mccoursemod.util.Utils;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record UnlockEnchantmentPacketPayload(boolean locked,
                                             int index) implements CustomPacketPayload {
    // TYPE
    public static final CustomPacketPayload.Type<UnlockEnchantmentPacketPayload> TYPE =
           new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "unlock_data"));

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() { return TYPE; }

    // STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, UnlockEnchantmentPacketPayload> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.BOOL, UnlockEnchantmentPacketPayload::locked,
                                 ByteBufCodecs.INT, UnlockEnchantmentPacketPayload::index,
                                 UnlockEnchantmentPacketPayload::new);

    // SERVER PAYLOAD HANDLER
    public static void onUnlockEnchantmentServerPayloadHandler(UnlockEnchantmentPacketPayload payload,
                                                               IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                HolderLookup.RegistryLookup<Enchantment> ench =
                      serverPlayer.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                ItemStack target = serverPlayer.getInventory().getItem(payload.index);
                // Item with UNLOCK enchantment
                if (!target.isEmpty() && Utils.toolEnchant(ench, ModEnchantments.UNLOCK, target) > 0) {
                    target.set(ModDataComponentTypes.UNLOCK, payload.locked);
                    ChatUtils.playerDefault(serverPlayer, payload.locked
                                            ? "§c\uD83D\uDD12 Item locked!" : "§a\uD83D\uDD13 Item unlocked!");
                }
            }
        })
        .exceptionally(e -> { // Handle exception
            context.disconnect(Component.translatable("mccoursemod.networking.failed", e.getMessage()));
            return null;
        });
    }
}