package net.karen.mccoursemod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.recipe.GrowthChamberRecipe;
import net.karen.mccoursemod.recipe.ModRecipes;
import net.karen.mccoursemod.screen.custom.GrowthChamberScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new GrowthChamberRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        Level level = Minecraft.getInstance().level;
        if (level != null) {
            MinecraftServer server = level.getServer();
            if (server != null) {
                RecipeManager recipeManager = server.getRecipeManager();
                List<GrowthChamberRecipe> growthChamberRecipes =
                    recipeManager.getRecipes().stream()
                                 .filter(recipe ->
                                         recipe.value().getType() == ModRecipes.GROWTH_CHAMBER_TYPE.get())
                                 .map(recipe ->
                                      (GrowthChamberRecipe) recipe.value())
                                 .collect(Collectors.toList());
                registration.addRecipes(GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_TYPE, growthChamberRecipes);
            }
        }
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(GrowthChamberScreen.class, 70, 30, 25, 20,
                                        GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_TYPE,
                                        ModBlocks.GROWTH_CHAMBER.get());
    }
}