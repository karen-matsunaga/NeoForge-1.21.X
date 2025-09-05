package net.karen.mccoursemod.recipe;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class CraftingPlusRecipe implements Recipe<CraftingInput> {
    final String group;
    final CraftingBookCategory category;
    final ShapedRecipePattern pattern;
    final ItemStack result;
    final boolean showNotification;
    @Nullable private PlacementInfo placementInfo;

    public CraftingPlusRecipe(String group, CraftingBookCategory category,
                              ShapedRecipePattern pattern, ItemStack result,
                              boolean showNotification) {
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.result = result;
        this.showNotification = showNotification;
    }

    @VisibleForTesting
    public List<Optional<Ingredient>> getIngredients() { return this.pattern.ingredients(); }

    @Override
    public @NotNull String group() { return this.group; }

    public ItemStack getResult() { return this.result; }

    public ShapedRecipePattern getPattern() { return this.pattern; }

    @Override
    public boolean showNotification() { return this.showNotification; }

    @Override
    public boolean matches(@NotNull CraftingInput input, @NotNull Level level) { return this.pattern.matches(input); }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput input,
                                       HolderLookup.@NotNull Provider provider) {
        return this.result.copy();
    }

    @Override
    public @NotNull RecipeType<? extends Recipe<CraftingInput>> getType() {
        return ModRecipes.CRAFTING_PLUS_TYPE.get();
    }

    @Override
    public @NotNull PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.createFromOptionals(this.pattern.ingredients());
        }
        return this.placementInfo;
    }

    @Override
    public @NotNull RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
        return ModRecipes.CRAFTING_PLUS_SERIALIZER.get();
    }

    public CraftingBookCategory getCategory() { return this.category; }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() {
        RecipeBookCategory recipeBookCategory;
        switch (this.category) {
            case BUILDING -> recipeBookCategory = RecipeBookCategories.CRAFTING_BUILDING_BLOCKS;
            case EQUIPMENT -> recipeBookCategory = RecipeBookCategories.CRAFTING_EQUIPMENT;
            case REDSTONE -> recipeBookCategory = RecipeBookCategories.CRAFTING_REDSTONE;
            case MISC -> recipeBookCategory = RecipeBookCategories.CRAFTING_MISC;
            default -> throw new MatchException(null, null);
        }
        return recipeBookCategory;
    }

    public static class Serializer implements RecipeSerializer<CraftingPlusRecipe> {
        public static final MapCodec<CraftingPlusRecipe> CODEC =
               RecordCodecBuilder.mapCodec(instance ->
                         instance.group(Codec.STRING.optionalFieldOf("group", "") // GROUP
                                                    .forGetter(CraftingPlusRecipe::group),
                                        CraftingBookCategory.CODEC.fieldOf("category") // CATEGORY
                                                            .orElse(CraftingBookCategory.MISC)
                                                            .forGetter(CraftingPlusRecipe::getCategory),
                                                                       ShapedRecipePattern.MAP_CODEC
                                                                                          .forGetter(recipe ->
                                                                                                     recipe.pattern),
                                        ItemStack.STRICT_CODEC
                                                 .fieldOf("result") // RESULT
                                                 .forGetter(CraftingPlusRecipe::getResult),
                                        Codec.BOOL.optionalFieldOf("show_notification", true) // NOTIFICATION
                                                  .forGetter(CraftingPlusRecipe::showNotification))
                                                  .apply(instance, CraftingPlusRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, CraftingPlusRecipe> STREAM_CODEC =
               StreamCodec.of(CraftingPlusRecipe.Serializer::toNetwork, CraftingPlusRecipe.Serializer::fromNetwork);

        public @NotNull MapCodec<CraftingPlusRecipe> codec() { return CODEC; }

        public @NotNull StreamCodec<RegistryFriendlyByteBuf, CraftingPlusRecipe> streamCodec() { return STREAM_CODEC; }

        // CUSTOM METHOD - Read NETWORK
        private static CraftingPlusRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String string = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            ShapedRecipePattern shapedrecipepattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            boolean flag = buffer.readBoolean();
            return new CraftingPlusRecipe(string, craftingbookcategory, shapedrecipepattern, itemstack, flag);
        }

        // CUSTOM METHOD - Write NETWORK
        private static void toNetwork(RegistryFriendlyByteBuf buffer, CraftingPlusRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeBoolean(recipe.showNotification);
        }
    }
}