package net.karen.mccoursemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class CompactorItem extends Item {
    private final boolean AUTOMATED; // Automated CRAFT items
    private final TagKey<Item> INPUT, OUTPUT; // INPUT and OUTPUT Crafting Recipe using ITEM TAGS

    public CompactorItem(Properties properties, boolean automated,
                         TagKey<Item> input, TagKey<Item> output) {
        super(properties);
        this.AUTOMATED = automated;
        this.INPUT = input;
        this.OUTPUT = output;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide() && !this.AUTOMATED) {
            craftItem(level, player);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    // CUSTOM METHOD - Craft items
    private void craftItem(Level level, Player player) {
        MinecraftServer mc = level.getServer();
        if (mc == null) { return; }
        int width = 3;
        int height = 3;
        AtomicInteger totalCrafted = new AtomicInteger();
        HolderLookup.RegistryLookup<Item> itemHolder = level.registryAccess().lookupOrThrow(Registries.ITEM);
        Optional<HolderSet.Named<Item>> items = itemHolder.get(INPUT);
        if (items.isPresent()) {
            HolderSet.Named<Item> item = items.get();
            for (Holder<Item> getItem : item) {
                Item inputItem = getItem.value().asItem();
                RecipeManager recipeManager = mc.getRecipeManager();
                CraftingInput input =
                        CraftingInput.of(width, height, NonNullList.withSize(width * height, new ItemStack(inputItem)));
                Stream<RecipeHolder<CraftingRecipe>> matchingRecipes =
                      recipeManager.recipeMap().getRecipesFor(RecipeType.CRAFTING, input, level);
                for (RecipeHolder<CraftingRecipe> recipeHolder : matchingRecipes.toList()) {
                    CraftingRecipe craftingRecipe = recipeHolder.value();
                    if (!(craftingRecipe instanceof ShapedRecipe shaped)) { continue; }
                    ItemStack output = shaped.assemble(input, level.registryAccess());
                    if (output.isEmpty() || !output.is(OUTPUT)) { continue; }
                    List<Optional<Ingredient>> ingredients = shaped.getIngredients();
                    boolean allInputs = ingredients.stream().allMatch(ing -> ing.isPresent() &&
                                                                      ing.get().test(new ItemStack(inputItem)));
                    if (!allInputs) { continue; }
                    int countRequired = ingredients.size();
                    int available = countItem(player, new ItemStack(inputItem));
                    if (available >= countRequired) {
                        int maxCrafts = available / countRequired;
                        removeItems(player, new ItemStack(inputItem), countRequired * maxCrafts);
                        ItemStack result = output.copy();
                        result.setCount(maxCrafts);
                        player.getInventory().add(result);
                        totalCrafted.addAndGet(maxCrafts);
                    }
                }
            }
        }
        if (totalCrafted.get() > 0) { player(player, "§aCompacted " + totalCrafted + " blocks!", green); }
        else { player(player, "Nothing to compress.", red); }
    }

    // CUSTOM METHOD - Counts how many of the same items there are in the inventory
    private int countItem(Player player, ItemStack target) {
        int count = 0;
        for (ItemStack stack : player.getInventory()) {
            if (ItemStack.isSameItemSameComponents(stack, target)) { count += stack.getCount(); }
        }
        return count;
    }

    // CUSTOM METHOD - Removes a certain amount of an item from inventory
    private void removeItems(Player player, ItemStack target, int amountToRemove) {
        Inventory inv = player.getInventory();
        for (ItemStack stack : inv) { // Checks Player's inventory
            if (ItemStack.isSameItemSameComponents(stack, target)) {
                int removed = Math.min(stack.getCount(), amountToRemove); // Is same item
                stack.shrink(removed);
                amountToRemove -= removed;
                if (amountToRemove <= 0) { return; }
            }
        }
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return Component.translatable(this.descriptionId).withStyle(purple);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull ServerLevel level,
                              @NotNull Entity entity, @Nullable EquipmentSlot slot) {
        Player player = (Player) entity;
        if (this.AUTOMATED) { // CRAFT is automated
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) { craftItem(level, player); }
        }
    }

    // CUSTOM METHOD - COMPACTOR item description
    public Map<String, ChatFormatting> compactorItemName() {
        boolean isAutomated = this.AUTOMATED;
        Map<String, ChatFormatting> lines = new LinkedHashMap<>();
        // Map<String, ChatFormatting> -> String [Message line]; ChatFormatting [Message color line].
        lines.put("Craft Automated: " + isAutomated, isAutomated ? green : red);
        lines.put("Transform on blocks all vanilla gems, raw's, ingots, mobs drops, etc.!", gold);
        lines.put("Compact type: 3x3 crafting recipes.", aqua);
        return lines;
    }
}