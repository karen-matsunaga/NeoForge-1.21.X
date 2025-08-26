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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class GrowthChamberRecipeBuilder implements RecipeBuilder {
    private final Ingredient ingredient;
    private final ItemStack output;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public GrowthChamberRecipeBuilder(Ingredient ingredient, ItemStack output) {
        this.ingredient = ingredient;
        this.output = output;
    }

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String name, @NotNull Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.output.getItem();
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceKey<Recipe<?>> resourceKey) {
        Advancement.Builder advancement =
                   recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
                               .rewards(AdvancementRewards.Builder.recipe(resourceKey))
                               .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);
        GrowthChamberRecipe recipe = new GrowthChamberRecipe(ingredient, output);
        recipeOutput.accept(resourceKey, recipe, advancement.build(resourceKey.location().withPrefix("recipes/")));
    }
}