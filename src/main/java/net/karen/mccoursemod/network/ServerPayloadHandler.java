package net.karen.mccoursemod.network;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.custom.MccourseElevatorBlock;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.item.custom.MccourseModBottleItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class ServerPayloadHandler {
    // SERVER NETWORK -> Mccourse Mod Elevator block
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

    // SERVER NETWORK -> Mccourse Mod Bottle item
    public static void handleKeyInputs(MccourseModBottlePacketPayload payload,
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
                        if (canStore > 0) {
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