package net.karen.mccoursemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.*;
import static net.karen.mccoursemod.util.ChatUtils.*;
import static net.karen.mccoursemod.util.Utils.hasItem;

public class RestoreItem extends Item {
    public RestoreItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (level.isClientSide()) { return InteractionResult.PASS; }
        // MAIN HAND -> Restore item || OFFHAND -> Target item (Block, item, tools or armors to UNCRAFT)
        InteractionHand offhand = InteractionHand.OFF_HAND;
        InteractionHand mainHand = InteractionHand.MAIN_HAND;
        ItemStack target = hasItem(player, (hand == mainHand) ? offhand : mainHand);
        ItemStack restore = hasItem(player, mainHand);
        int itemsGiven = 0; // Items restored on Player's inventory
        if (level instanceof ServerLevel serverLevel) {
            if (target.isEmpty()) { // If OFFHAND empty
                return screen(player, "Hold the item you wish to uncraft in your other hand.", black);
            }
            if (target.isEnchanted()) { // If the item is enchanted
                ItemEnchantments itemEnchantment = EnchantmentHelper.getEnchantmentsForCrafting(target);
                ItemStack itemBook = new ItemStack(Items.ENCHANTED_BOOK); // Converts the enchantments to enchanted books
                Map<Holder<Enchantment>, Integer> newValue = new HashMap<>();
                itemEnchantment.entrySet().forEach((ench) ->
                                                   newValue.put(ench.getKey(), Math.max(1, ench.getIntValue())));
                // Apply the updated enchantments to the original item
                ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(itemEnchantment);
                newValue.forEach(enchantments::set);
                EnchantmentHelper.setEnchantments(itemBook, enchantments.toImmutable()); // New enchantment level
                // Returns the base item WITHOUT enchantment and an enchanted book WITH enchantment(s)
                if (player.getInventory().add(itemBook)) { itemsGiven++; }
            }
            // Filters recipes that create the same base item -> Crafting recipe
            HolderLookup.RegistryLookup<Item> itemHolder = level.registryAccess().lookupOrThrow(Registries.ITEM);
            List<Holder.Reference<Item>> list = itemHolder.listElements().toList();
            for (Holder.Reference<Item> value : list) {
                Holder<Item> items = value.getDelegate();
                Item item = items.value();
                List<RecipeHolder<?>> matchingRecipes =
                    serverLevel.recipeAccess().getRecipes().stream()
                               .filter(recipeHolder -> recipeHolder.value().getType() == RecipeType.CRAFTING)
                               .filter(recipeHolder -> recipeHolder.value() instanceof CraftingRecipe)
                               .filter(recipeHolder -> {
                                         Recipe<?> values = recipeHolder.value();
                                         if (values instanceof ShapedRecipe recipe) {
                                             int width = recipe.getWidth();
                                             int height = recipe.getHeight();
                                             int gridSize = width * height;
                                             List<ItemStack> inputItems = new ArrayList<>(gridSize);
                                             for (int i = 0; i < gridSize; i++) {
                                                 inputItems.add(new ItemStack(item));
                                             }
                                             CraftingInput input = CraftingInput.of(width, height, inputItems);
                                             ItemStack output = recipe.assemble(input, serverLevel.registryAccess());
                                             return target.getItem() == output.getItem();
                                         }
                                         return false;
                                      }).toList();

                if (matchingRecipes.isEmpty()) { return screen(player, "No recipes found for this item.", darkRed); }
                for (RecipeHolder<?> recipeHolder : matchingRecipes) {
                    Recipe<?> recipes = recipeHolder.value();
                    if (recipes instanceof ShapedRecipe shapedRecipe) {
                        List<Optional<Ingredient>> ingredients = shapedRecipe.getIngredients();
                        for (Optional<Ingredient> ingredient : ingredients) {
                            if (ingredient.isEmpty()) { continue; }
                            HolderSet<Item> possibleItems = ingredient.get().getValues();
                            if (possibleItems.size() > 0) {
                                ItemStack stackToGive = new ItemStack(possibleItems.get(0).value()).copy();
                                stackToGive.setCount(1);
                                if (player.getInventory().add(stackToGive)) { itemsGiven++; }
                            }
                        }
                    }
                }
            }
            target.shrink(1); // Target item and Restore item are removed on Player's inventory
            restore.shrink(1);
            return screen(player, "Restore accomplished! Items recovered: " + itemsGiven + " item(s)!", green);
        }
        else {
            return InteractionResult.PASS;
        }
    }

    // CUSTOM METHOD - Messages on SCREEN
    private InteractionResult screen(Player player,
                                     String message, ChatFormatting color) {
        player(player, message, color);
        return InteractionResult.SUCCESS;
    }
}