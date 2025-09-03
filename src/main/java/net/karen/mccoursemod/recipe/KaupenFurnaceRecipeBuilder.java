package net.karen.mccoursemod.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class KaupenFurnaceRecipeBuilder implements RecipeBuilder {
    private final Ingredient ingredient;
    private final Item result;
    private final float experience;
    private final int cookingTime;
    private final CookingBookCategory category;
    private final String group;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public KaupenFurnaceRecipeBuilder(String group, CookingBookCategory category,
                                      Ingredient ingredient, Item result,
                                      float experience, int cookingTime) {
        this.group = group;
        this.category = category;
        this.ingredient = ingredient;
        this.result = result;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String name, @NotNull Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String name) {
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput recipeOutput, @NotNull ResourceKey<Recipe<?>> resourceKey) {
        Advancement.Builder advancement =
             recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
                         .rewards(AdvancementRewards.Builder.recipe(resourceKey))
                         .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);
        KaupenFurnaceRecipe recipe = new KaupenFurnaceRecipe(this.group, this.category,
                                                             this.ingredient, new ItemStack(this.result),
                                                             this.experience, this.cookingTime);
        recipeOutput.accept(resourceKey, recipe, advancement.build(resourceKey.location().withPrefix("recipes/")));
    }
}