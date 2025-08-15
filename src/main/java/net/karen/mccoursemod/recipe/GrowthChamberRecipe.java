package net.karen.mccoursemod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record GrowthChamberRecipe(Ingredient inputItem, ItemStack output)
                                  implements Recipe<GrowthChamberRecipeInput> {
    // inputItem & output ==> Read From JSON File!
    // GrowthChamberRecipeInput --> INVENTORY of the Block Entity
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(@NotNull GrowthChamberRecipeInput growthChamberRecipeInput,
                           Level level) {
        if (level.isClientSide()) { return false; }
        return inputItem.test(growthChamberRecipeInput.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull GrowthChamberRecipeInput growthChamberRecipeInput,
                                       HolderLookup.@NotNull Provider provider) {
        return output.copy();
    }

    @Override
    public @NotNull RecipeSerializer<? extends Recipe<GrowthChamberRecipeInput>> getSerializer() {
        return ModRecipes.GROWTH_CHAMBER_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<? extends Recipe<GrowthChamberRecipeInput>> getType() {
        return ModRecipes.GROWTH_CHAMBER_TYPE.get();
    }

    @Override
    public @NotNull PlacementInfo placementInfo() {
        return PlacementInfo.create(inputItem);
    }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<GrowthChamberRecipe> {
        public static final MapCodec<GrowthChamberRecipe> CODEC =
               RecordCodecBuilder.mapCodec(inst -> inst.group(
                                           Ingredient.CODEC.fieldOf("ingredient")
                                                           .forGetter(GrowthChamberRecipe::inputItem),
                                           ItemStack.CODEC.fieldOf("result")
                                                          .forGetter(GrowthChamberRecipe::output))
                                 .apply(inst, GrowthChamberRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, GrowthChamberRecipe> STREAM_CODEC =
               StreamCodec.composite(Ingredient.CONTENTS_STREAM_CODEC, GrowthChamberRecipe::inputItem,
                                     ItemStack.STREAM_CODEC, GrowthChamberRecipe::output,
                                     GrowthChamberRecipe::new);

        @Override
        public @NotNull MapCodec<GrowthChamberRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, GrowthChamberRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}