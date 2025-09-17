package net.karen.mccoursemod.screen.custom;

import net.karen.mccoursemod.recipe.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

public class CraftingPlusResultSlot extends Slot {
    private final CraftingContainer craftSlots;
    private final Player player;
    private int removeCount;

    public CraftingPlusResultSlot(Player player, CraftingContainer craftSlots,
                                  ResultContainer resultContainer, int index, int x, int y) {
        super(resultContainer, index, x, y);
        this.player = player;
        this.craftSlots = craftSlots;
    }

    public boolean mayPlace(@NotNull ItemStack stack) { return false; }

    public @NotNull ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(amount, this.getItem().getCount());
        }
        return super.remove(amount);
    }

    protected void onQuickCraft(@NotNull ItemStack stack, int amount) {
        this.removeCount += amount;
        this.checkTakeAchievements(stack);
    }

    protected void onSwapCraft(int numItemsCrafted) {
        this.removeCount += numItemsCrafted;
    }

    protected void checkTakeAchievements(@NotNull ItemStack stack) {
        if (this.removeCount > 0) {
            stack.onCraftedBy(this.player, this.removeCount);
            EventHooks.firePlayerCraftingEvent(this.player, stack, this.craftSlots);
        }
        if (this.container instanceof RecipeCraftingHolder recipecraftingholder) {
            recipecraftingholder.awardUsedRecipes(this.player, this.craftSlots.getItems());
        }
        this.removeCount = 0;
    }

    private static NonNullList<ItemStack> copyAllInputItems(CraftingInput input) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < nonnulllist.size(); ++i) { nonnulllist.set(i, input.getItem(i)); }
        return nonnulllist;
    }

    private NonNullList<ItemStack> getRemainingItems(CraftingInput input, Level level) {
        NonNullList<ItemStack> list;
        if (level instanceof ServerLevel serverlevel) {
            list = serverlevel.recipeAccess().getRecipeFor(ModRecipes.CRAFTING_PLUS_TYPE.get(), input, serverlevel)
                              .map(holder ->
                                   holder.value().getRemainingItems(input)).orElseGet(() -> copyAllInputItems(input));
        }
        else { list = CraftingRecipe.defaultCraftingReminder(input); }
        return list;
    }

    public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        this.checkTakeAchievements(stack);
        CraftingInput.Positioned craftInput = this.craftSlots.asPositionedCraftInput();
        CraftingInput craftinginput = craftInput.input();
        int i = craftInput.left();
        int j = craftInput.top();
        NonNullList<ItemStack> nonnulllist;
        Level level = player.level();
        if (level instanceof ServerLevel) {
            CommonHooks.setCraftingPlayer(player);
            nonnulllist = this.getRemainingItems(craftinginput, level);
            CommonHooks.setCraftingPlayer(player); // DEFAULT VALUE -> null
            for(int k = 0; k < craftinginput.height(); ++k) {
                for(int l = 0; l < craftinginput.width(); ++l) {
                    int i1 = l + i + (k + j) * this.craftSlots.getWidth();
                    ItemStack itemstack = this.craftSlots.getItem(i1);
                    ItemStack itemstack1 = nonnulllist.get(l + k * craftinginput.width());
                    if (!itemstack.isEmpty()) {
                        this.craftSlots.removeItem(i1, 1);
                        itemstack = this.craftSlots.getItem(i1);
                    }
                    if (!itemstack1.isEmpty()) {
                        if (itemstack.isEmpty()) { this.craftSlots.setItem(i1, itemstack1); }
                        else if (ItemStack.isSameItemSameComponents(itemstack, itemstack1)) {
                            itemstack1.grow(itemstack.getCount());
                            this.craftSlots.setItem(i1, itemstack1);
                        }
                        else if (!this.player.getInventory().add(itemstack1)) {
                            this.player.drop(itemstack1, false);
                        }
                    }
                }
            }
        }
    }

    public boolean isFake() { return true; }
}