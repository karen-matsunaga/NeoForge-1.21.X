package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.*;
import static net.karen.mccoursemod.util.ChatUtils.componentTranslatable;

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
        if (player.level().isClientSide() || stack.isEmpty()) { return InteractionResult.FAIL; }
        if (!(stack.getItem() instanceof MccourseModBottleItem self)) { return InteractionResult.FAIL; }
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

    @Override
    public void appendHoverText(ItemStack stack,
                                @NotNull TooltipContext context,
                                @NotNull TooltipDisplay tooltipDisplay,
                                @NotNull Consumer<Component> consumer,
                                @NotNull TooltipFlag flag) {
        Integer xp = stack.get(ModDataComponentTypes.STORED_LEVELS);
        if (xp != null && xp >= 0) {
            tooltipLine(consumer, "Stored XP: " + xp + " / " + storeXp, yellow);
            tooltipLine(consumer, "Stored XP: Left click: 1 level; N: 10 levels;", red);
            tooltipLine(consumer, "Shift + N: 100 levels; Shift + Left click: All levels.", red);
            tooltipLine(consumer, "Restored XP: Right click: 1 level; B: 10 levels;", green);
            tooltipLine(consumer, "Shift + B: 100 levels; Shift + Right click: All levels.", green);
        }
        super.appendHoverText(stack, context, tooltipDisplay, consumer, flag);
    }

    // CUSTOM METHOD - Mccourse Bottle with XP
    public static ItemStack createMccourseBottleWithXP(int levels) {
        ItemStack stack = new ItemStack(ModItems.MCCOURSE_MOD_BOTTLE.get());
        stack.set(ModDataComponentTypes.STORED_LEVELS, levels); // Added levels on Mccourse Bottle item
        return stack;
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