package net.karen.mccoursemod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.recipe.GrowthChamberRecipe;
import net.karen.mccoursemod.recipe.KaupenFurnaceRecipe;
import net.karen.mccoursemod.recipe.ModRecipes;
import net.karen.mccoursemod.screen.ModMenuTypes;
import net.karen.mccoursemod.screen.custom.GrowthChamberScreen;
import net.karen.mccoursemod.screen.custom.KaupenFurnaceMenu;
import net.karen.mccoursemod.screen.custom.KaupenFurnaceScreen;
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
        registration.addRecipeCategories(new KaupenFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            // GROWTH CHAMBER
            server.getRecipeManager().recipeMap().byType(ModRecipes.GROWTH_CHAMBER_TYPE.get())
                  .forEach(holder -> {
                           var recipe = holder.value();
                           registration.addRecipes(GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_TYPE,
                                                   List.of(new GrowthChamberRecipe(recipe.inputItem(),
                                                                                   recipe.output())));
                  });
            // KAUPEN FURNACE
            server.getRecipeManager().recipeMap().byType(ModRecipes.KAUPEN_FURNACE_TYPE.get())
                  .forEach(holder -> {
                           var recipe = holder.value();
                           registration.addRecipes(KaupenFurnaceRecipeCategory.KAUPEN_FURNACE_TYPE,
                                                   List.of(new KaupenFurnaceRecipe(recipe.group(), recipe.category(), recipe.input(),
                                                                                   null,
                                                                                   recipe.experience(), recipe.cookingTime())));
                  });
        }
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        // GROWTH CHAMBER
        registration.addRecipeClickArea(GrowthChamberScreen.class, 70, 30, 25, 20,
                                        GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_TYPE);

        // KAUPEN FURNACE
        registration.addRecipeClickArea(KaupenFurnaceScreen.class, 60, 30, 20, 30,
                                        KaupenFurnaceRecipeCategory.KAUPEN_FURNACE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        // GROWTH CHAMBER
        registration.addCraftingStation(GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_TYPE,
                                        ModBlocks.GROWTH_CHAMBER.get());

        // KAUPEN FURNACE
        registration.addCraftingStation(KaupenFurnaceRecipeCategory.KAUPEN_FURNACE_TYPE,
                                        ModBlocks.KAUPEN_FURNACE_BLOCK.get());
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        // Register all custom recipe transfer handlers
        // [CUSTOM ITEM] Input Start [i] - Input End [i1]
        // Player's Inventory Start [i2] (9 hotbar + 27 inventory slots) - Inventory End [i3]
        // KAUPEN FURNACE - Menu Class, Menu Type and Recipe Type
        registration.addRecipeTransferHandler(KaupenFurnaceMenu.class,
                                              ModMenuTypes.KAUPEN_FURNACE_MENU.get(),
                                              KaupenFurnaceRecipeCategory.KAUPEN_FURNACE_TYPE,
                                              0, 1, 3, 36);
    }
}