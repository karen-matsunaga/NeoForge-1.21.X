package net.karen.mccoursemod.item.custom;

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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
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

    protected Item input(Level level) {
        HolderLookup.RegistryLookup<Item> itemHolder = level.registryAccess().lookupOrThrow(Registries.ITEM);
        Optional<HolderSet.Named<Item>> items = itemHolder.get(INPUT);
        if (items.isPresent()) {
            HolderSet.Named<Item> item = items.get();
            for (Holder<Item> getItem: item) { return getItem.value().asItem(); }
        }
        return ItemStack.EMPTY.getItem();
    }

    // CUSTOM METHOD - Craft items
    private void craftItem(Level level, Player player) {
        MinecraftServer mc = level.getServer();
        if (mc == null) { return; }
        int width = 3;
        int height = 3;
        CraftingInput input =
              CraftingInput.of(width, height, NonNullList.withSize(width * height,
                                                                   new ItemStack(input(level))));
        Stream<RecipeHolder<CraftingRecipe>> matchingRecipes =
              mc.getRecipeManager().recipeMap().getRecipesFor(RecipeType.CRAFTING, input, level);
        AtomicInteger totalCrafted = new AtomicInteger();
        for (RecipeHolder<CraftingRecipe> recipeHolder : matchingRecipes.toList()) {
            if (!(recipeHolder.value() instanceof ShapedRecipe shaped)) { continue; }
            ItemStack output = shaped.assemble(input, level.registryAccess());
            if (output.isEmpty() || !output.is(OUTPUT)) { continue; }
            List<Optional<Ingredient>> ingredients = shaped.getIngredients();
            boolean allInputs = ingredients.stream().allMatch(ing ->
                                                              ing.isPresent() &&
                                                              ing.get().test(new ItemStack(input(level))));
            if (!allInputs) { continue; }
            int countRequired = ingredients.size();
            int available = countItem(player, new ItemStack(input(level)));
            if (available >= countRequired) {
                int maxCrafts = available / countRequired;
                removeItems(player, new ItemStack(input(level)), countRequired * maxCrafts);
                ItemStack result = output.copy();
                result.setCount(maxCrafts);
                player.getInventory().add(result);
                totalCrafted.addAndGet(maxCrafts);
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
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                @NotNull TooltipDisplay display, @NotNull Consumer<Component> consumer,
                                @NotNull TooltipFlag flag) {
        tooltipLine(consumer, "Transform all vanilla gems, raw's, ingots and mobs drops on blocks!", gold);
        tooltipLine(consumer, "Compact type: 3x3 crafting recipes.", darkAqua);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull ServerLevel level,
                              @NotNull Entity entity, @Nullable EquipmentSlot slot) {
        Player player = (Player) entity;
        if (this.AUTOMATED) { // CRAFT is automated
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) { craftItem(level, player); }
        }
    }
}