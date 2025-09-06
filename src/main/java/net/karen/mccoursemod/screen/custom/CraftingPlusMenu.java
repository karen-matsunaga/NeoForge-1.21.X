package net.karen.mccoursemod.screen.custom;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.recipe.CraftingPlusRecipe;
import net.karen.mccoursemod.recipe.ModRecipes;
import net.karen.mccoursemod.screen.ModMenuTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class CraftingPlusMenu extends AbstractCraftingMenu {
    protected final CraftingContainer craftSlots =
              new TransientCraftingContainer(this, 7, 7);
    protected final ResultContainer resultSlots =
              new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;
    private boolean placingRecipe;

    public CraftingPlusMenu(int containerId, Inventory inventory,
                            RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public CraftingPlusMenu(int containerId,
                            Inventory playerInventory, ContainerLevelAccess access) {
        super(ModMenuTypes.CRAFTING_PLUS_MENU.get(), containerId, 7, 7);
        this.access = access;
        this.player = playerInventory.player;
        this.addSlot(new CraftingPlusResultSlot(playerInventory.player, this.craftSlots, this.resultSlots,
                                                0, 148, 35));

        // Crafting Plus 7x7 size
        for (int i = 0; i < craftSlots.getHeight(); ++i) {
            for (int j = 0; j < craftSlots.getWidth(); ++j) {
                this.addSlot(new Slot(this.craftSlots, j + i * 7, 8 + j * 18, 6 + i * 18));
            }
        }

        // Player inventory
        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 135 + k * 18));
            }
        }

        for (int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 193));
        }
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu menu,
                                                  ServerLevel level,
                                                  Player player,
                                                  CraftingContainer craftSlots,
                                                  ResultContainer resultSlots,
                                                  @Nullable RecipeHolder<CraftingRecipe> recipe) {
        ServerPlayer serverplayer = (ServerPlayer) player;
        RecipeManager recipes = level.getServer().getRecipeManager();
        ItemStack itemstack = ItemStack.EMPTY;

        CraftingInput customInput = CraftingInput.of(7, 7, craftSlots.getItems());

        // CRAFTING PLUS
        Optional<RecipeHolder<CraftingPlusRecipe>> optional =
                recipes.getRecipeFor(ModRecipes.CRAFTING_PLUS_TYPE.get(), customInput, level);

        // CRAFTING PLUS RECIPE
        if (optional.isPresent()) {
            RecipeHolder<CraftingPlusRecipe> recipeholder = optional.get();
            if (resultSlots.setRecipeUsed(serverplayer, recipeholder)) {
                ItemStack itemStack2 = recipeholder.value().assemble(customInput, level.registryAccess());
                if (itemStack2.isItemEnabled(level.enabledFeatures())) {
                    itemstack = itemStack2;
                }
            }
        }
        resultSlots.setItem(0, itemstack);
        menu.setRemoteSlot(0, itemstack);
        serverplayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(),
                                                                           0, itemstack));
    }

    public void slotsChanged(@NotNull Container inventory) {
        if (!this.placingRecipe) {
            this.access.execute((level, pos) -> {
                if (level instanceof ServerLevel serverlevel) {
                    slotChangedCraftingGrid(this, serverlevel, this.player,
                                            this.craftSlots, this.resultSlots, null);
                }
            });
        }
    }

    public void beginPlacingRecipe() { this.placingRecipe = true; }

    public void finishPlacingRecipe(@NotNull ServerLevel serverLevel,
                                    @NotNull RecipeHolder<CraftingRecipe> recipe) {
        this.placingRecipe = false;
        slotChangedCraftingGrid(this, serverLevel, this.player, this.craftSlots, this.resultSlots, recipe);
    }

    public void removed(@NotNull Player player) {
        super.removed(player);
        this.access.execute((level, pos) -> this.clearContainer(player, this.craftSlots));
    }

    @Override
    public @NotNull Slot getResultSlot() { return this.slots.getFirst(); }

    @Override
    public @NotNull List<Slot> getInputGridSlots() { return this.slots.subList(1, 50); }

    @Override
    protected @NotNull Player owner() { return this.player; }

    @Override
    public @NotNull RecipeBookType getRecipeBookType() { return RecipeBookType.CRAFTING; }

    private static final int RESULT_SLOT = 0;
    private static final int CRAFTING_FIRST_SLOT = 1;
    private static final int CRAFTING_LAST_SLOT = 49; // inclusive
    private static final int PLAYER_INV_FIRST_SLOT = 50;
    private static final int PLAYER_INV_LAST_SLOT = 85; // inclusive

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == RESULT_SLOT) {
                this.access.execute((level, pos) -> itemstack1.getItem().onCraftedBy(itemstack1, player));
                // RESULT -> Player Inventory
                if (!this.moveItemStackTo(itemstack1, PLAYER_INV_FIRST_SLOT,
                                          PLAYER_INV_LAST_SLOT + 1, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            }
            else if (index >= PLAYER_INV_FIRST_SLOT && index <= PLAYER_INV_LAST_SLOT) {
                // PLAYER INVENTORY -> crafting grid
                if (!this.moveItemStackTo(itemstack1, CRAFTING_FIRST_SLOT,
                                          CRAFTING_LAST_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (index >= CRAFTING_FIRST_SLOT && index <= CRAFTING_LAST_SLOT) {
                // CRAFTING GRID -> Player's Inventory
                if (!this.moveItemStackTo(itemstack1, PLAYER_INV_FIRST_SLOT,
                                          PLAYER_INV_LAST_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else { return ItemStack.EMPTY; } // INVALID index
            if (itemstack1.isEmpty()) { slot.setByPlayer(ItemStack.EMPTY); }
            else { slot.setChanged(); }
            if (itemstack1.getCount() == itemstack.getCount()) { return ItemStack.EMPTY; }
            slot.onTake(player, itemstack1);
            if (index == RESULT_SLOT) { player.drop(itemstack1, false); }
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.access, player, ModBlocks.CRAFTING_PLUS.get());
    }

    @Override
    public int getGridHeight() { return this.craftSlots.getHeight(); }

    @Override
    public int getGridWidth() { return this.craftSlots.getWidth(); }

    public boolean canTakeItemForPickAll(@NotNull ItemStack stack, 
                                         Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }
}