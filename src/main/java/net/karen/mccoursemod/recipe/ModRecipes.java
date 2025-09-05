package net.karen.mccoursemod.recipe;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    // Registry all custom recipe serializers
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
           DeferredRegister.create(Registries.RECIPE_SERIALIZER, MccourseMod.MOD_ID);

    // Registry all custom recipe types
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
           DeferredRegister.create(Registries.RECIPE_TYPE, MccourseMod.MOD_ID);

    // Registry all custom recipes serializers
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<GrowthChamberRecipe>>
           GROWTH_CHAMBER_SERIALIZER = SERIALIZERS.register("growth_chamber", GrowthChamberRecipe.Serializer::new);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<KaupenFurnaceRecipe>>
           KAUPEN_FURNACE_SERIALIZER =
           SERIALIZERS.register("kaupen_furnace",
           () -> new KaupenFurnaceRecipe.Serializer(200));

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CraftingPlusRecipe>>
           CRAFTING_PLUS_SERIALIZER = SERIALIZERS.register("crafting_plus", CraftingPlusRecipe.Serializer::new);

    // Registry all custom recipes types
    public static final DeferredHolder<RecipeType<?>, RecipeType<GrowthChamberRecipe>> GROWTH_CHAMBER_TYPE =
           RECIPE_TYPES.register("growth_chamber", () -> new RecipeType<>() {
               @Override
               public String toString() {
                   return "growth_chamber";
               }
           });

    public static final DeferredHolder<RecipeType<?>, RecipeType<KaupenFurnaceRecipe>> KAUPEN_FURNACE_TYPE =
           RECIPE_TYPES.register("kaupen_furnace", () -> new RecipeType<>() {
               @Override
               public String toString() {
                   return "kaupen_furnace";
               }
           });

    public static final DeferredHolder<RecipeType<?>, RecipeType<CraftingPlusRecipe>> CRAFTING_PLUS_TYPE =
           RECIPE_TYPES.register("crafting_plus", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "crafting_plus";
                }
           });

    // CUSTOM METHOD - Registry all custom recipes on event bus
    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}