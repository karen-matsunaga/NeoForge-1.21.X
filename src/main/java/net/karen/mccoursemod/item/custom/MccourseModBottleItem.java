package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class MccourseModBottleItem extends Item {
    public final int storeXp;
    public final int amountXp;

    public MccourseModBottleItem(Properties properties, int storeXp, int amountXp) {
        super(properties);
        this.storeXp = storeXp;
        this.amountXp = amountXp;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player,
                                          @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand); // Player has Mccourse Bottle on main hand
        if (level.isClientSide() || stack.isEmpty() || !(stack.getItem() instanceof MccourseModBottleItem self)) {
            return InteractionResult.FAIL;
        }
        if (!stack.has(ModDataComponentTypes.STORED_LEVELS)) {
            stack.set(ModDataComponentTypes.STORED_LEVELS, self.amountXp);
        }
        // StoredLevels Data Component to save and to store XP
        Integer storedLevels = stack.get(ModDataComponentTypes.STORED_LEVELS);
        if (storedLevels != null && storedLevels >= 0) {
            if (player instanceof ServerPlayer serverPlayer) {
                if (player.isShiftKeyDown()) { // SHIFT + RIGHT click
                    mccourseXp(serverPlayer, player, storedLevels, storedLevels, 0, storedLevels + " levels!", stack);
                }
                else { // RIGHT click
                    mccourseXp(serverPlayer, player, storedLevels, 1, storedLevels - 1, "1 level!", stack);
                }
            }
        }
        return InteractionResult.SUCCESS_SERVER.heldItemTransformedTo(stack);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return componentTranslatable(this.descriptionId, darkGreen);
    }

    // CUSTOM METHOD - Mccourse Bottle tooltip
    public Map<String, ChatFormatting> mccourseBottleItemDescription(ItemStack stack) {
        Integer xp = stack.get(ModDataComponentTypes.STORED_LEVELS);
        Map<String, ChatFormatting> map = new LinkedHashMap<>();
        if (xp != null && amountXp > 0 && storeXp > 0) {
           map.put("Stored XP: " + xp + " / " + storeXp, yellow);
           map.put("Stored XP: Left click: 1 level; N: 10 levels;", red);
           map.put("Shift + N: 100 levels; Shift + Left click: All levels.", red);
           map.put("Restored XP: Right click: 1 level; B: 10 levels;", green);
           map.put("Shift + B: 100 levels; Shift + Right click: All levels.", green);
           return map;
        }
        return map;
    }

    // CUSTOM METHOD - Mccourse Bottle RESTORE system
    private void mccourseXp(ServerPlayer serverPlayer, Player player,
                            int storedLevels, int amount, int store, String message, ItemStack heldItem) {
        if (player.getCooldowns().isOnCooldown(heldItem)) { // Check if it is already on cooldown
            player(player, "Wait before using again!", yellow);
            return;
        }
        if (storedLevels > 0) { // Restore levels
            serverPlayer.giveExperienceLevels(amount);
            heldItem.set(ModDataComponentTypes.STORED_LEVELS, store);
            player(player, "Restored " + message, green);
            player.getCooldowns().addCooldown(heldItem, 20); // Applies 1 second cooldown (20 ticks)
        }
        else { player(player, "Bottle is empty.", red); }
    }
}