package net.karen.mccoursemod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.recipe.GrowthChamberRecipe;
import net.karen.mccoursemod.recipe.ModRecipes;
import net.karen.mccoursemod.screen.custom.GrowthChamberScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import java.util.List;

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
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            server.getRecipeManager().recipeMap().byType(ModRecipes.GROWTH_CHAMBER_TYPE.get())
                  .forEach(holder -> {
                           var recipe = holder.value();
                           registration.addRecipes(GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_TYPE,
                                                   List.of(new GrowthChamberRecipe(recipe.inputItem(),
                                                                                   recipe.output())));
                  });
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