package net.karen.mccoursemod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.recipe.CraftingPlusRecipe;
import net.karen.mccoursemod.recipe.GrowthChamberRecipe;
import net.karen.mccoursemod.recipe.KaupenFurnaceRecipe;
import net.karen.mccoursemod.recipe.ModRecipes;
import net.karen.mccoursemod.screen.ModMenuTypes;
import net.karen.mccoursemod.screen.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import java.util.*;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new GrowthChamberRecipeCategory(guiHelper));
        registration.addRecipeCategories(new KaupenFurnaceRecipeCategory(guiHelper));
        registration.addRecipeCategories(new CraftingPlusRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            // GROWTH CHAMBER
            server.getRecipeManager().recipeMap().byType(ModRecipes.GROWTH_CHAMBER_TYPE.get())
                  .forEach(holder -> {
                           GrowthChamberRecipe recipe = holder.value();
                           registration.addRecipes(GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_TYPE,
                                                   List.of(new GrowthChamberRecipe(recipe.inputItem(), recipe.output())));
                  });

            // KAUPEN FURNACE
            server.getRecipeManager().recipeMap().byType(ModRecipes.KAUPEN_FURNACE_TYPE.get())
                  .forEach(holder -> {
                           KaupenFurnaceRecipe recipe = holder.value();
                           registration.addRecipes(KaupenFurnaceRecipeCategory.KAUPEN_FURNACE_TYPE,
                                                   List.of(new KaupenFurnaceRecipe(recipe.group(), recipe.category(),
                                                                                   recipe.input(), new ItemStack(
                                                                                   recipe.assemble(new SingleRecipeInput(
                                                                                   new ItemStack(recipe.input().getValues()
                                                                                                       .get(0).value())),
                                                                                   server.registryAccess()).getItem()),
                                                                                   recipe.experience(), recipe.cookingTime())));
                  });

            // CRAFTING PLUS
            server.getRecipeManager().recipeMap().byType(ModRecipes.CRAFTING_PLUS_TYPE.get())
                  .forEach(holder -> {
                           CraftingPlusRecipe recipe = holder.value();
                           List<Optional<Ingredient>> craftingPlusRecipe = recipe.getIngredients();
                           Optional<Ingredient> item = craftingPlusRecipe.getFirst();
                           if (!craftingPlusRecipe.isEmpty() && item.isPresent()) {
                               registration.addRecipes(CraftingPlusRecipeCategory.CRAFTING_PLUS_TYPE,
                                                       List.of(new CraftingPlusRecipe(recipe.group(), recipe.getCategory(),
                                                               new ShapedRecipePattern(7, 7, craftingPlusRecipe,
                                                                                       Optional.empty()),
                                                               new ItemStack(recipe.assemble(CraftingInput.of(0, 0,
                                                                                                              List.of()),
                                                               server.registryAccess()).getItem()), true)));
                           }
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

        // CRAFTING PLUS
        registration.addRecipeClickArea(CraftingPlusScreen.class, 140, 18, 18, 18,
                                        CraftingPlusRecipeCategory.CRAFTING_PLUS_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        // GROWTH CHAMBER
        registration.addCraftingStation(GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_TYPE,
                                        ModBlocks.GROWTH_CHAMBER.get());

        // KAUPEN FURNACE
        registration.addCraftingStation(KaupenFurnaceRecipeCategory.KAUPEN_FURNACE_TYPE,
                                        ModBlocks.KAUPEN_FURNACE_BLOCK.get());

        // CRAFTING PLUS
        registration.addCraftingStation(CraftingPlusRecipeCategory.CRAFTING_PLUS_TYPE,
                                        ModBlocks.CRAFTING_PLUS.get());
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

        // CRAFTING PLUS - Menu Class, Menu Type and Recipe Type
        registration.addRecipeTransferHandler(CraftingPlusMenu.class,
                                              ModMenuTypes.CRAFTING_PLUS_MENU.get(),
                                              CraftingPlusRecipeCategory.CRAFTING_PLUS_TYPE,
                                              1, 49, 50, 36);

        // GROWTH CHAMBER - Menu Class, Menu Type and Recipe Type
        registration.addRecipeTransferHandler(GrowthChamberMenu.class,
                                              ModMenuTypes.GROWTH_CHAMBER_MENU.get(),
                                              GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_TYPE,
                                              36, 2, 0, 36);
    }
}