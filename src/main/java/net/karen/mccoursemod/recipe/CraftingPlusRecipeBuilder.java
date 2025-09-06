package net.karen.mccoursemod.recipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CraftingPlusRecipeBuilder implements RecipeBuilder {
    private final HolderGetter<Item> items;
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final ItemStack resultStack;
    private final List<String> rows;
    private final Map<Character, Ingredient> key;
    private final Map<String, Criterion<?>> criteria;
    @Nullable
    private String group;
    private boolean showNotification;

    private CraftingPlusRecipeBuilder(HolderGetter<Item> items,
                                      RecipeCategory category, ItemLike result, int count) {
        this(items, category, new ItemStack(result, count));
    }

    private CraftingPlusRecipeBuilder(HolderGetter<Item> items,
                                      RecipeCategory category, ItemStack result) {
        this.rows = Lists.newArrayList();
        this.key = Maps.newLinkedHashMap();
        this.criteria = new LinkedHashMap<>();
        this.showNotification = true;
        this.items = items;
        this.category = category;
        this.result = result.getItem();
        this.count = result.getCount();
        this.resultStack = result;
    }

    public static CraftingPlusRecipeBuilder plusShaped(HolderGetter<Item> items,
                                                       RecipeCategory category, ItemLike result) {
        return plusShaped(items, category, result, 1);
    }

    public static CraftingPlusRecipeBuilder plusShaped(HolderGetter<Item> items,
                                                       RecipeCategory category, ItemLike result, int count) {
        return new CraftingPlusRecipeBuilder(items, category, result, count);
    }

    public static CraftingPlusRecipeBuilder plusShaped(HolderGetter<Item> items,
                                                       RecipeCategory category, ItemStack result) {
        return new CraftingPlusRecipeBuilder(items, category, result);
    }

    public CraftingPlusRecipeBuilder plusDefine(Character symbol, TagKey<Item> tag) {
        return this.plusDefine(symbol, Ingredient.of(this.items.getOrThrow(tag)));
    }

    public CraftingPlusRecipeBuilder plusDefine(Character symbol, ItemLike item) {
        return this.plusDefine(symbol, Ingredient.of(item));
    }

    public CraftingPlusRecipeBuilder plusDefine(Character symbol, Ingredient ingredient) {
        if (this.key.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
        }
        else if (symbol == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        }
        else {
            this.key.put(symbol, ingredient);
            return this;
        }
    }

    public CraftingPlusRecipeBuilder pattern(String pattern) {
        if (!this.rows.isEmpty() && pattern.length() != this.rows.getFirst().length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        }
        else {
            this.rows.add(pattern);
            return this;
        }
    }

    public @NotNull CraftingPlusRecipeBuilder unlockedBy(@NotNull String name,
                                                         @NotNull Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public @NotNull CraftingPlusRecipeBuilder group(@Nullable String name) {
        this.group = name;
        return this;
    }

    public CraftingPlusRecipeBuilder showNotification(boolean showNotification) {
        this.showNotification = showNotification;
        return this;
    }

    public @NotNull Item getResult() { return this.result; }

    public void save(RecipeOutput recipeOutput, @NotNull ResourceKey<Recipe<?>> resourceKey) {
        ShapedRecipePattern shapedRecipePattern = this.ensureValid(resourceKey);
        Advancement.Builder advancementBuilder =
            recipeOutput.advancement().addCriterion("has_the_recipe",
                                                    RecipeUnlockedTrigger.unlocked(resourceKey))
                                                                         .rewards(AdvancementRewards.Builder.recipe(resourceKey))
                                                                         .requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(advancementBuilder);
        this.criteria.forEach(advancementBuilder::addCriterion);
        CraftingPlusRecipe craftingPlusRecipe =
                new CraftingPlusRecipe(Objects.requireNonNullElse(this.group, ""),
                                       RecipeBuilder.determineBookCategory(this.category),
                                       shapedRecipePattern, this.resultStack, this.showNotification);
        recipeOutput.accept(resourceKey, craftingPlusRecipe,
                            advancementBuilder.build(resourceKey.location().withPrefix("recipes/" +
                                                     this.category.getFolderName() + "/")));
    }

    private ShapedRecipePattern ensureValid(ResourceKey<Recipe<?>> recipe) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipe.location());
        }
        else { return ShapedRecipePattern.of(this.key, this.rows); }
    }
}