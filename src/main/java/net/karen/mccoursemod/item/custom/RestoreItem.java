package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.network.chat.Component;
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
        InteractionHand offhand = InteractionHand.OFF_HAND; // MAIN HAND -> RESTORE item
        InteractionHand mainHand = InteractionHand.MAIN_HAND; // OFFHAND -> Target item (Block, item, tools or armors to UNCRAFT)
        ItemStack target = hasItem(player, (hand == mainHand) ? offhand : mainHand);
        ItemStack restore = hasItem(player, mainHand);
        int itemsGiven = 0; // Items restored on Player's inventory
        if (level instanceof ServerLevel serverLevel) {
            if (target.isEmpty() || target.is(ModTags.Items.RESTORE_BLACKLIST_ITEMS)) { // If OFFHAND empty
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
            List<RecipeHolder<?>> matchingRecipes = serverLevel.recipeAccess().getRecipes().stream().toList();
            if (matchingRecipes.isEmpty()) { return screen(player, "No recipes found for this item.", darkRed); }
            for (RecipeHolder<?> recipeHolder : matchingRecipes) {
                Recipe<?> recipes = recipeHolder.value();
                // CRAFTING RECIPE -> Found recipe to restore on Player inventory
                if (recipes.getType() == RecipeType.CRAFTING && recipes instanceof CraftingRecipe craftingRecipe) {
                    if (recipes instanceof ShapedRecipe shapedRecipe) {
                        ItemStack output = craftingRecipe.assemble(CraftingInput.EMPTY, serverLevel.registryAccess());
                        if (target.getItem() == output.getItem()) {
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
            }
            target.shrink(1); // TARGET item and RESTORE item are removed on Player's inventory
            restore.shrink(1);
            return screen(player, "Restore accomplished! Items recovered: " + itemsGiven + " item(s)!", green);
        }
        else { return InteractionResult.PASS; }
    }

    // CUSTOM METHOD - Messages on SCREEN
    private InteractionResult screen(Player player,
                                     String message, ChatFormatting color) {
        player(player, message, color);
        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return componentTranslatableIntColor(this.getDescriptionId(), 0xFFE68B);
    }

    // CUSTOM METHOD - Restore description
    public List<String> restoreItemDescription() {
        return List.of("§3Add your §eTarget§3 on §cOFFHAND§3 and §eRestore§3 on §cMAIN HAND§3 slots to restore all items!",
                       "§cOFFHAND§3 slot: §eTarget§3 item.",
                       "§cMAIN HAND§3 slot: §eRestore§3 item.");
    }
}