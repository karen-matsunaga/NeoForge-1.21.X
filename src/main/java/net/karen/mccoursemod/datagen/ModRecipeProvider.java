package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        // Get the parameters from the `GatherDataEvent`s.
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider,
                                                               @NotNull RecipeOutput output) {
            return new ModRecipeProvider(provider, output);
        }

        @Override
        public @NotNull String getName() { return "Mccourse Recipes"; }
    }

    @Override
    protected void buildRecipes() {
        // CUSTOM block
        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.ANVIL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENCHANT.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.BISMUTH.get(),
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.NETHER_STAR, RecipeCategory.BUILDING_BLOCKS, Blocks.BONE_BLOCK);

        // CUSTOM tools
        this.allTools(List.of(ModItems.BISMUTH_SWORD.get(), ModItems.BISMUTH_PICKAXE.get(),
                              ModItems.BISMUTH_SHOVEL.get(), ModItems.BISMUTH_AXE.get(),
                              ModItems.BISMUTH_HOE.get(), ModItems.BISMUTH.get()));

        this.hammerTool(List.of(ModItems.BISMUTH_HAMMER.get(), ModBlocks.BISMUTH_BLOCK.get()));

        this.paxelTool(List.of(ModItems.BISMUTH_PAXEL.get(), ModItems.BISMUTH_PICKAXE.get(),
                               ModItems.BISMUTH_AXE.get(), ModItems.BISMUTH_SHOVEL.get()));

        // CUSTOM armors
        this.fullArmor(List.of(ModItems.BISMUTH_HELMET.get(), ModItems.BISMUTH_CHESTPLATE.get(),
                               ModItems.BISMUTH_LEGGINGS.get(), ModItems.BISMUTH_BOOTS.get(),
                               ModItems.BISMUTH.get()));
    }

    // CUSTOM METHOD - Smelting
    protected void oreSmelting(@NotNull List<ItemLike> itemLikes, @NotNull RecipeCategory category,
                               @NotNull ItemLike result, float experience, int cookingTime, @NotNull String group) {
        this.oreCooking(RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, itemLikes, category, result,
                        experience, cookingTime, group, "_from_smelting");
    }

    // CUSTOM METHOD - Blasting
    protected void oreBlasting(@NotNull List<ItemLike> itemLikes, @NotNull RecipeCategory category,
                               @NotNull ItemLike result, float experience, int cookingTime, @NotNull String group) {
        this.oreCooking(RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, itemLikes, category, result,
                        experience, cookingTime, group, "_from_blasting");
    }

    // CUSTOM METHOD - Smelting and Blasting
    protected <T extends AbstractCookingRecipe> void oreCooking(@NotNull RecipeSerializer<T> serializer,
                                                                AbstractCookingRecipe.@NotNull Factory<T> recipeFactory,
                                                                List<ItemLike> itemLikes, @NotNull RecipeCategory category,
                                                                @NotNull ItemLike result, float experience, int cookingTime,
                                                                @NotNull String group, @NotNull String suffix) {
        for (ItemLike itemlike : itemLikes) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime,
                                               serializer, recipeFactory)
                                      .group(group)
                                      .unlockedBy(getHasName(itemlike), this.has(itemlike))
                                      .save(this.output, MccourseMod.MOD_ID + ":" + getItemName(result) +
                                            suffix + "_" + getItemName(itemlike));
        }
    }

    // CUSTOM Tools
    // CUSTOM METHOD - All tools
    protected void allTools(List<ItemLike> itemLikes) {
        // 0 index -> Result (SWORD) || 1 index -> Result (PICKAXE) || 2 index -> Result (SHOVEL)
        // 3 index -> Result (AXE)   || 4 index -> Result (HOE)     || 5 index -> Result (ITEM)
        swordTool(List.of(itemLikes.getFirst(), itemLikes.get(5)));
        pickaxeTool(List.of(itemLikes.get(1), itemLikes.get(5)));
        shovelTool(List.of(itemLikes.get(2), itemLikes.get(5)));
        axeTool(List.of(itemLikes.get(3), itemLikes.get(5)));
        hoeTool(List.of(itemLikes.get(4), itemLikes.get(5)));
    }

    // CUSTOM METHOD - Paxel custom recipes
    protected void paxelTool(List<ItemLike> itemLikes) {
        // 0 index -> Result (PAXEL) || 1 index -> Ingredient (PICKAXE)
        // 2 index -> Ingredient (AXE) || 3 index -> Ingredient (SHOVEL)
        this.shapeless(RecipeCategory.TOOLS, itemLikes.getFirst())
            .requires(itemLikes.get(1))
            .requires(itemLikes.get(2))
            .requires(itemLikes.get(3))
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }

    // CUSTOM METHOD - Hammer Method
    protected void hammerTool(List<ItemLike> itemLikes) {
        // 0 index -> Result (HAMMER) || 1 index -> Ingredient (ITEM)
        this.shaped(RecipeCategory.TOOLS, itemLikes.getFirst())
            .pattern("WWW")
            .pattern(" S ")
            .pattern(" S ")
            .define('W', itemLikes.get(1)) // Ingredient
            .define('S', Items.STICK)
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }

    // CUSTOM METHOD - Sword tool
    protected void swordTool(List<ItemLike> itemLikes) {
        // 0 index -> Result (SWORD) || 1 index -> Ingredient (ITEM)
        this.shaped(RecipeCategory.COMBAT, itemLikes.getFirst())
            .pattern("X")
            .pattern("X")
            .pattern("#")
            .define('X', itemLikes.get(1)) // Ingredient
            .define('#', Items.STICK)
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }

    // CUSTOM METHOD - Pickaxe tool
    protected void pickaxeTool(List<ItemLike> itemLikes) {
        // 0 index -> Result (PICKAXE) || 1 index -> Ingredient (ITEM)
        this.shaped(RecipeCategory.TOOLS, itemLikes.getFirst())
            .pattern("XXX")
            .pattern(" # ")
            .pattern(" # ")
            .define('X', itemLikes.get(1)) // Ingredient
            .define('#', Items.STICK)
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }

    // CUSTOM METHOD - Shovel tool
    protected void shovelTool(List<ItemLike> itemLikes) {
        // 0 index -> Result (SHOVEL) || 1 index -> Ingredient (ITEM)
        this.shaped(RecipeCategory.TOOLS, itemLikes.getFirst())
            .pattern("X")
            .pattern("#")
            .pattern("#")
            .define('X', itemLikes.get(1)) // Ingredient
            .define('#', Items.STICK)
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }

    // CUSTOM METHOD - Axe tool
    protected void axeTool(List<ItemLike> itemLikes) {
        // 0 index -> Result (AXE) || 1 index -> Ingredient (ITEM)
        this.shaped(RecipeCategory.TOOLS, itemLikes.getFirst())
            .pattern("XX")
            .pattern("X#")
            .pattern(" #")
            .define('X', itemLikes.get(1)) // Ingredient
            .define('#', Items.STICK)
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }

    // CUSTOM METHOD - Hoe tool
    protected void hoeTool(List<ItemLike> itemLikes) {
        // 0 index -> Result (HOE) || 1 index -> Ingredient (ITEM)
        this.shaped(RecipeCategory.TOOLS, itemLikes.getFirst())
            .pattern("XX")
            .pattern(" #")
            .pattern(" #")
            .define('X', itemLikes.get(1)) // Ingredient
            .define('#', Items.STICK)
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }

    // CUSTOM Armors
    // CUSTOM METHOD - Full armor
    protected void fullArmor(List<ItemLike> itemLikes) {
        // 0 index -> Result (HELMET)   || 1 index -> Result (CHESTPLATE)
        // 2 index -> Result (LEGGINGS) || 3 index -> Result (BOOTS)
        // 4 index -> Ingredient (ITEM)
        this.helmetArmor(List.of(itemLikes.getFirst(), itemLikes.get(4)));
        this.chestplateArmor(List.of(itemLikes.get(1), itemLikes.get(4)));
        this.leggingsArmor(List.of(itemLikes.get(2), itemLikes.get(4)));
        this.bootsArmor(List.of(itemLikes.get(3), itemLikes.get(4)));
    }

    // CUSTOM METHOD - Helmet armor
    protected void helmetArmor(List<ItemLike> itemLikes) {
        // 0 index -> Result (HELMET) || 1 index -> Ingredient (ITEM)
        this.shaped(RecipeCategory.COMBAT, itemLikes.getFirst())
            .pattern("XXX")
            .pattern("X X")
            .define('X', itemLikes.get(1)) // Ingredient
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }

    // CUSTOM METHOD - Chestplate armor
    protected void chestplateArmor(List<ItemLike> itemLikes) {
        // 0 index -> Result (CHESTPLATE) || 1 index -> Ingredient (ITEM)
        this.shaped(RecipeCategory.COMBAT, itemLikes.getFirst())
            .pattern("X X")
            .pattern("XXX")
            .pattern("XXX")
            .define('X', itemLikes.get(1)) // Ingredient
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }

    // CUSTOM METHOD - Leggings armor
    protected void leggingsArmor(List<ItemLike> itemLikes) {
        // 0 index -> Result (LEGGINGS) || 1 index -> Ingredient (ITEM)
        this.shaped(RecipeCategory.COMBAT, itemLikes.getFirst())
            .pattern("XXX")
            .pattern("X X")
            .pattern("X X")
            .define('X', itemLikes.get(1)) // Ingredient
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }

    // CUSTOM METHOD - Boots armor
    protected void bootsArmor(List<ItemLike> itemLikes) {
        // 0 index -> Result (BOOTS) || 1 index -> Ingredient (ITEM)
        this.shaped(RecipeCategory.COMBAT, itemLikes.getFirst())
            .pattern("X X")
            .pattern("X X")
            .define('X', itemLikes.get(1)) // Ingredient
            .unlockedBy(getHasName(itemLikes.get(1)), this.has(itemLikes.get(1)))
            .save(this.output);
    }
}