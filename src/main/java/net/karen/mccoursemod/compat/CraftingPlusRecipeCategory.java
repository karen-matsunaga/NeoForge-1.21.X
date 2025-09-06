package net.karen.mccoursemod.compat;

import com.mojang.serialization.Codec;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.ICodecHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.recipe.CraftingPlusRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class CraftingPlusRecipeCategory implements IRecipeCategory<CraftingPlusRecipe> {
    public static final ResourceLocation UID =
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "crafting_plus");

    public static final ResourceLocation TEXTURE =
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                 "textures/gui/crafting_plus/crafting_plus_gui.png");

    public static final IRecipeType<CraftingPlusRecipe> CRAFTING_PLUS_TYPE =
           IRecipeType.create(UID, CraftingPlusRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CraftingPlusRecipeCategory(IGuiHelper helper) {
        // Background and Icon screen
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 133);
        this.icon = helper.createDrawableItemLike(ModBlocks.CRAFTING_PLUS.get());
    }

    @Override
    public @NotNull IRecipeType<CraftingPlusRecipe> getRecipeType() {
        return CRAFTING_PLUS_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.mccoursemod.crafting_plus");
    }

    @Override
    public @Nullable IDrawable getIcon() { return icon; }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder,
                          @NotNull CraftingPlusRecipe recipe, @NotNull IFocusGroup focuses) {
        List<Optional<Ingredient>> ingredients = recipe.getIngredients();
        int slotIndex = 0;

        // INPUT
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                if (slotIndex >= ingredients.size()) { break; }
                int x = 8 + col * 18;
                int y = 6 + row * 18;
                Optional<Ingredient> ingredient = ingredients.get(slotIndex);
                if (ingredient.isPresent()) {
                    builder.addSlot(RecipeIngredientRole.INPUT, x, y).add(ingredient.get()).setStandardSlotBackground();
                    slotIndex++;
                }
            }
        }

        // OUTPUT
        builder.addSlot(RecipeIngredientRole.OUTPUT, 148, 35).add(recipe.getResult()).setOutputSlotBackground();
    }

    @Override
    public int getHeight() { return background.getHeight(); }

    @Override
    public int getWidth() { return background.getWidth(); }

    @Override
    public @Nullable ResourceLocation getRegistryName(@NotNull CraftingPlusRecipe recipe) {
        return getRecipeType().getUid();
    }

    @Override
    public @NotNull Codec<CraftingPlusRecipe> getCodec(@NotNull ICodecHelper codecHelper,
                                                       @NotNull IRecipeManager recipeManager) {
        return CraftingPlusRecipe.Serializer.CODEC.codec();
    }
}