package net.karen.mccoursemod.recipe;

import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

public class KaupenFurnaceRecipe extends AbstractCookingRecipe {
    public KaupenFurnaceRecipe(String group, CookingBookCategory category,
                               Ingredient ingredient, ItemStack result,
                               float experience, int cookingTime) {
        super(group, category, ingredient, result, experience, cookingTime);
    }

    @Override
    public @NotNull RecipeSerializer<? extends AbstractCookingRecipe> getSerializer() {
        return ModRecipes.KAUPEN_FURNACE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<? extends AbstractCookingRecipe> getType() {
        return ModRecipes.KAUPEN_FURNACE_TYPE.get();
    }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() {
        return switch (this.category()) {
            case BLOCKS -> RecipeBookCategories.FURNACE_BLOCKS;
            case FOOD -> RecipeBookCategories.FURNACE_FOOD;
            case MISC -> RecipeBookCategories.FURNACE_MISC;
        };
    }

    @Override
    protected @NotNull Item furnaceIcon() {
        return ModBlocks.KAUPEN_FURNACE_BLOCK.asItem();
    }

    public static class Serializer extends AbstractCookingRecipe.Serializer<KaupenFurnaceRecipe> {
        public Serializer(int defaultCookingTime) {
            super(KaupenFurnaceRecipe::new, defaultCookingTime);
        }
    }
}