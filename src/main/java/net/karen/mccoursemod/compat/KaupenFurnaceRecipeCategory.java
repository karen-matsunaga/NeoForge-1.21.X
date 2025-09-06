package net.karen.mccoursemod.compat;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.entity.KaupenFurnaceBlockEntity;
import net.karen.mccoursemod.recipe.KaupenFurnaceRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class KaupenFurnaceRecipeCategory implements IRecipeCategory<KaupenFurnaceRecipe> {
    public static final ResourceLocation UID =
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "kaupen_furnace");

    public static final ResourceLocation TEXTURE =
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "textures/gui/container/furnace.png");

    public static final IRecipeType<KaupenFurnaceRecipe> KAUPEN_FURNACE_TYPE =
           IRecipeType.create(UID, KaupenFurnaceRecipe.class);

    private final IDrawable background, icon;

    public KaupenFurnaceRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 83);
        this.icon = helper.createDrawableItemLike(ModBlocks.KAUPEN_FURNACE_BLOCK.get());
    }

    @Override
    public @NotNull IRecipeType<KaupenFurnaceRecipe> getRecipeType() { return KAUPEN_FURNACE_TYPE; }

    @Override
    public @NotNull Component getTitle() { return Component.translatable("block.mccoursemod.kaupen_furnace"); }

    @Override
    public IDrawable getIcon() { return icon; }

    @Override
    public int getHeight() { return background.getHeight(); }

    @Override
    public int getWidth() { return background.getWidth(); }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder,
                          KaupenFurnaceRecipe recipe, @NotNull IFocusGroup focuses) {
        RecipeDisplay display = recipe.display().getFirst();
        if (display instanceof FurnaceRecipeDisplay furnace) {
            // INPUT 0 -> BLOCK / ITEM
            builder.addInputSlot(56, 17).add(recipe.input()).setStandardSlotBackground();

            // INPUT 1 -> FUEL BLOCK / FUEL ITEM
            builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 56, 53)
                   .addItemStacks(KaupenFurnaceBlockEntity.getValidFuels())
                   .setStandardSlotBackground();

            // OUTPUT -> RESULT BLOCK / ITEM
            builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35)
                   .add(furnace.result()).setOutputSlotBackground();
        }
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder,
                                   @NotNull KaupenFurnaceRecipe recipe,
                                   @NotNull IFocusGroup focuses) {
        builder.addAnimatedRecipeArrow(200).setPosition(79, 34); // Arrow animation
        builder.addAnimatedRecipeFlame(300).setPosition(56, 36); // Flame animation
    }

    @Override
    public void draw(@NotNull KaupenFurnaceRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView,
                     @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        float seconds = recipe.cookingTime(); // Cooking time
        float experience = recipe.experience(); // Experience
        if (seconds > 0 || experience > 0) {
            showInfo(fontRenderer, guiGraphics, "gui.jei.category.smelting.time.seconds", seconds, 60); // Time
            showInfo(fontRenderer, guiGraphics, "gui.jei.category.smelting.experience", experience, 20); // Experience
        }
    }

    // CUSTOM METHOD - Show info of cooking time, experience, etc. for each Kaupen Furnace recipe
    private void showInfo(Font font, GuiGraphics gui, String key, float value, int y) {
        Component info = Component.translatable(key, value);
        gui.drawString(font, info, 110, y, 0xFF808080, false);
    }

    @Override
    public @Nullable ResourceLocation getRegistryName(@NotNull KaupenFurnaceRecipe recipe) {
        return getRecipeType().getUid();
    }
}