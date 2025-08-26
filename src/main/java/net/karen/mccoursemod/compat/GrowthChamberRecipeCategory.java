package net.karen.mccoursemod.compat;

import com.mojang.serialization.Codec;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.ICodecHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.recipe.GrowthChamberRecipe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class GrowthChamberRecipeCategory implements IRecipeCategory<GrowthChamberRecipe> {
    public static final ResourceLocation UID =
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "growth_chamber");

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
           "textures/gui/growth_chamber/growth_chamber_gui.png");

    public static final IRecipeType<GrowthChamberRecipe> GROWTH_CHAMBER_RECIPE_TYPE =
           IRecipeType.create(UID, GrowthChamberRecipe.class);

    private final IDrawableStatic background;
    private final IDrawable icon;

    public GrowthChamberRecipeCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE,0 ,0, 176, 85).build();
        this.icon = helper.createDrawableItemLike(ModBlocks.GROWTH_CHAMBER);
    }

    @Override
    public @NotNull IRecipeType<GrowthChamberRecipe> getRecipeType() {
        return GROWTH_CHAMBER_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.mccoursemod.growth_chamber");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth() {
        return background.getWidth();
    }

    @Override
    public int getHeight() {
        return background.getHeight();
    }

    @Override
    public void draw(@NotNull GrowthChamberRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView,
                     @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics, 0, 0);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder,
                          @NotNull GrowthChamberRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addInputSlot(54, 34).add(recipe.inputItem()).setStandardSlotBackground();
        builder.addOutputSlot(104, 34).add(recipe.output().getItem()).setOutputSlotBackground();
    }

    @Override
    public @NotNull Codec<GrowthChamberRecipe> getCodec(@NotNull ICodecHelper codecHelper,
                                                        @NotNull IRecipeManager recipeManager) {
        return GrowthChamberRecipe.Serializer.CODEC.codec();
    }

    @Override
    public @Nullable ResourceLocation getRegistryName(@NotNull GrowthChamberRecipe recipe) {
        return getRecipeType().getUid();
    }
}