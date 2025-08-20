package net.karen.mccoursemod.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InventoryUtil {
    // CUSTOM METHOD - Stack item of Player's inventory
    public static boolean hasPlayerStackInInventory(Player player, Item item) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack currentStack = player.getInventory().getItem(i);
            if (!currentStack.isEmpty() && currentStack.is(item)) { return true; }
        }
        return false;
    }

    // CUSTOM METHOD - Index/Slot of Player's inventory
    public static int getFirstInventoryIndex(Player player, Item item) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack currentStack = player.getInventory().getItem(i);
            if (!currentStack.isEmpty() && currentStack.is(item)) { return i; }
        }
        return -1;
    }
}