package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.recipe.GrowthChamberRecipeBuilder;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output,
                      CompletableFuture<HolderLookup.Provider> lookupProvider) {
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
        List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_BISMUTH, ModBlocks.BISMUTH_ORE,
                                                    ModBlocks.BISMUTH_DEEPSLATE_ORE, ModBlocks.BISMUTH_END_ORE,
                                                    ModBlocks.BISMUTH_NETHER_ORE);

        List<ItemLike> ALEXANDRITE_SMELTABLES = List.of(ModItems.RAW_ALEXANDRITE, ModBlocks.ALEXANDRITE_ORE,
                                                        ModBlocks.DEEPSLATE_ALEXANDRITE_ORE,
                                                        ModBlocks.END_STONE_ALEXANDRITE_ORE,
                                                        ModBlocks.NETHER_ALEXANDRITE_ORE);

        // ** CUSTOM Smelting + Blasting blocks, items, etc. **
        oreSmelting(BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(),
                    0.25F, 200, "bismuth");
        oreBlasting(BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(),
                    0.25F, 100, "bismuth");

        oreSmelting(ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                    0.25F, 200, "alexandrite");
        oreBlasting(ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                    0.25F, 100, "alexandrite");

        // ** CUSTOM block **
        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.ANVIL,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENCHANT.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.BISMUTH.get(),
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_BLOCK.get());

        // ** CUSTOM tools **
        this.allTools(List.of(ModItems.BISMUTH_SWORD.get(), ModItems.BISMUTH_PICKAXE.get(),
                              ModItems.BISMUTH_SHOVEL.get(), ModItems.BISMUTH_AXE.get(),
                              ModItems.BISMUTH_HOE.get(), ModItems.BISMUTH.get()));

        this.allTools(List.of(ModItems.ALEXANDRITE_SWORD.get(), ModItems.ALEXANDRITE_PICKAXE.get(),
                              ModItems.ALEXANDRITE_SHOVEL.get(), ModItems.ALEXANDRITE_AXE.get(),
                              ModItems.ALEXANDRITE_HOE.get(), ModItems.ALEXANDRITE.get()));

        this.hammerTool(List.of(ModItems.BISMUTH_HAMMER.get(), ModBlocks.BISMUTH_BLOCK.get()));
        this.hammerTool(List.of(ModItems.ALEXANDRITE_HAMMER.get(), ModBlocks.ALEXANDRITE_BLOCK.get()));

        this.paxelTool(List.of(ModItems.BISMUTH_PAXEL.get(), ModItems.BISMUTH_PICKAXE.get(),
                               ModItems.BISMUTH_AXE.get(), ModItems.BISMUTH_SHOVEL.get()));

        this.paxelTool(List.of(ModItems.ALEXANDRITE_PAXEL.get(), ModItems.ALEXANDRITE_PICKAXE.get(),
                               ModItems.ALEXANDRITE_AXE.get(), ModItems.ALEXANDRITE_SHOVEL.get()));

        // ** CUSTOM armors **
        this.fullArmor(List.of(ModItems.BISMUTH_HELMET.get(), ModItems.BISMUTH_CHESTPLATE.get(),
                               ModItems.BISMUTH_LEGGINGS.get(), ModItems.BISMUTH_BOOTS.get(),
                               ModItems.BISMUTH.get()));

        this.fullArmor(List.of(ModItems.ALEXANDRITE_HELMET.get(), ModItems.ALEXANDRITE_CHESTPLATE.get(),
                               ModItems.ALEXANDRITE_LEGGINGS.get(), ModItems.ALEXANDRITE_BOOTS.get(),
                               ModItems.ALEXANDRITE.get()));

        // ** CUSTOM Block Families **
        this.blockFamilies(List.of(ModBlocks.BISMUTH_STAIRS.get(), ModBlocks.BISMUTH_SLAB.get(),
                                   ModBlocks.BISMUTH_BUTTON.get(), ModBlocks.BISMUTH_PRESSURE_PLATE.get(),
                                   ModBlocks.BISMUTH_FENCE.get(), ModBlocks.BISMUTH_FENCE_GATE.get(),
                                   ModBlocks.BISMUTH_WALL.get(), ModBlocks.BISMUTH_DOOR.get(),
                                   ModBlocks.BISMUTH_TRAPDOOR.get()),
                           ModItems.BISMUTH.get(), "bismuth", this.output);

        this.blockFamilies(List.of(ModBlocks.ALEXANDRITE_STAIRS.get(), ModBlocks.ALEXANDRITE_SLABS.get(),
                                   ModBlocks.ALEXANDRITE_BUTTON.get(), ModBlocks.ALEXANDRITE_PREASSURE_PLATE.get(),
                                   ModBlocks.ALEXANDRITE_FENCE.get(), ModBlocks.ALEXANDRITE_FENCE_GATE.get(),
                                   ModBlocks.ALEXANDRITE_WALL.get(), ModBlocks.ALEXANDRITE_DOOR.get(),
                                   ModBlocks.ALEXANDRITE_TRAPDOOR.get()),
                           ModItems.BISMUTH.get(), "alexandrite", this.output);

        // ** CUSTOM Trim Smithing **
        // CRAFTING TABLE
        this.copySmithingTemplate(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModItems.BISMUTH.get());
        // SMITHING TABLE -> ERROR
        //this.trimSmithing(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModTrimPatterns.KAUPEN,
        //                  ResourceKey.create(Registries.RECIPE, ModTrimPatterns.KAUPEN.location()));

        // ** CUSTOM glass block **
        this.glassBlocks(List.of(ModBlocks.FORCED_STAINED_GLASS.get(),
                                 ModBlocks.FORCED_STAINED_GLASS_PANE.get(), Items.GREEN_DYE));

        // ** CUSTOM advanced items **
        // CARROT -> REQUIRED || GROWTH -> RESULT
        this.nineBlockStorageRecipes(RecipeCategory.FOOD, Items.CARROT, RecipeCategory.MISC, ModItems.GROWTH.get());

        // ** CUSTOM log **
        log(List.of(ModBlocks.BLOODWOOD_PLANKS, ModBlocks.BLOODWOOD_WOOD, ModBlocks.BLOODWOOD_LOG), ModTags.Items.BLOODWOOD_LOGS);
        log(List.of(ModBlocks.WALNUT_PLANKS, ModBlocks.WALNUT_WOOD, ModBlocks.WALNUT_LOG), ModTags.Items.WALNUT_LOGS);

        // ** CUSTOM shield **
        shield(List.of(ModItems.ALEXANDRITE_SHIELD, ModItems.ALEXANDRITE));

        // ** CUSTOM sign and hanging sign **
        sign(List.of(ModItems.WALNUT_SIGN, ModBlocks.WALNUT_PLANKS,
                     ModItems.WALNUT_HANGING_SIGN, ModBlocks.STRIPPED_WALNUT_LOG));

        // ** CUSTOM Ender Pearl blocks **
        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.ENDER_PEARL,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDER_PEARL_BLOCK.get());
        enderPearlBlock(List.of(ModBlocks.GREEN_ENDER_PEARL_BLOCK.get(), Items.GREEN_DYE));
        enderPearlBlock(List.of(ModBlocks.LIME_GREEN_ENDER_PEARL_BLOCK.get(), Items.LIME_DYE));
        enderPearlBlock(List.of(ModBlocks.MAGENTA_ENDER_PEARL_BLOCK.get(), Items.MAGENTA_DYE));
        enderPearlBlock(List.of(ModBlocks.PINK_ENDER_PEARL_BLOCK.get(), Items.PINK_DYE));
        enderPearlBlock(List.of(ModBlocks.PURPLE_ENDER_PEARL_BLOCK.get(), Items.PURPLE_DYE));
        enderPearlBlock(List.of(ModBlocks.BLACK_ENDER_PEARL_BLOCK.get(), Items.BLACK_DYE));
        enderPearlBlock(List.of(ModBlocks.BLUE_ENDER_PEARL_BLOCK.get(), Items.BLUE_DYE));
        enderPearlBlock(List.of(ModBlocks.CYAN_ENDER_PEARL_BLOCK.get(), Items.CYAN_DYE));
        enderPearlBlock(List.of(ModBlocks.GRAY_ENDER_PEARL_BLOCK.get(), Items.GRAY_DYE));
        enderPearlBlock(List.of(ModBlocks.BROWN_ENDER_PEARL_BLOCK.get(), Items.BROWN_DYE));
        enderPearlBlock(List.of(ModBlocks.YELLOW_ENDER_PEARL_BLOCK.get(), Items.YELLOW_DYE));
        enderPearlBlock(List.of(ModBlocks.WHITE_ENDER_PEARL_BLOCK.get(), Items.WHITE_DYE));
        enderPearlBlock(List.of(ModBlocks.ORANGE_ENDER_PEARL_BLOCK.get(), Items.ORANGE_DYE));
        enderPearlBlock(List.of(ModBlocks.RED_ENDER_PEARL_BLOCK.get(), Items.RED_DYE));

        // ** CUSTOM mob blocks **
        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.NETHER_STAR,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHER_STAR_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.ROTTEN_FLESH,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROTTEN_FLESH_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.GUNPOWDER,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.GUNPOWDER_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.BLAZE_ROD,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLAZE_ROD_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.PHANTOM_MEMBRANE,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.PHANTOM_MEMBRANE_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.STRING,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRING_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.SPIDER_EYE,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPIDER_EYE_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.FERMENTED_SPIDER_EYE,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.FERMENTED_SPIDER_EYE_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.SUGAR,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.SUGAR_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.SUGAR_CANE,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.SUGAR_CANE_BLOCK.get());

        // ** CUSTOM boat **
        boat(List.of(ModItems.WALNUT_BOAT, ModItems.WALNUT_CHEST_BOAT, ModBlocks.WALNUT_PLANKS));

        // ** GROWTH CHAMBER recipes **
        growthChamberRecipe(List.of(Items.DIAMOND, Items.DIAMOND_BLOCK));
        growthChamberRecipe(List.of(ModItems.RAW_BISMUTH.get(), ModItems.BISMUTH.get()));
        growthChamberRecipe(List.of(Items.STICK, Items.END_ROD));
    }

    // CUSTOM METHOD - GROWTH CHAMBER custom recipes
    protected void growthChamberRecipe(List<ItemLike> items) {
        // 0 -> INPUT; 1 -> OUTPUT
        ItemLike input = items.getFirst();
        ItemLike output = items.get(1);
        new GrowthChamberRecipeBuilder(Ingredient.of(input), new ItemStack(output))
                                                 .unlockedBy(getHasName(input), has(input))
                                                 .save(this.output,
                                                       MccourseMod.MOD_ID + ":" + getItemName(output) +
                                                       "_from_growth_chamber");
    }

    // CUSTOM METHOD - Block Families
    protected void blockFamilies(List<Block> blocks, Item item,
                                 String group, RecipeOutput output) {
        stairBuilder(blocks.getFirst(), Ingredient.of(item)).group(group)
                    .unlockedBy(getHasName(item), has(item)).save(output);

        slab(RecipeCategory.BUILDING_BLOCKS, blocks.get(1), item);

        buttonBuilder(blocks.get(2), Ingredient.of(item)).group(group)
                     .unlockedBy(getHasName(item), has(item)).save(output);

        pressurePlate(blocks.get(3), item);

        fenceBuilder(blocks.get(4), Ingredient.of(item)).group(group)
                    .unlockedBy(getHasName(item), has(item)).save(output);

        fenceGateBuilder(blocks.get(5), Ingredient.of(item)).group(group)
                        .unlockedBy(getHasName(item), has(item)).save(output);

        wall(RecipeCategory.BUILDING_BLOCKS, blocks.get(6), item);

        doorBuilder(blocks.get(7), Ingredient.of(item)).group(group)
                   .unlockedBy(getHasName(item), has(item)).save(output);

        trapdoorBuilder(blocks.get(8), Ingredient.of(item)).group(group)
                       .unlockedBy(getHasName(item), has(item)).save(output);
    }

    // CUSTOM METHOD - Smelting
    protected void oreSmelting(@NotNull List<ItemLike> itemLikes,
                               @NotNull RecipeCategory category, @NotNull ItemLike result,
                               float experience, int cookingTime, @NotNull String group) {
        this.oreCooking(RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, itemLikes, category, result,
                        experience, cookingTime, group, "_from_smelting");
    }

    // CUSTOM METHOD - Blasting
    protected void oreBlasting(@NotNull List<ItemLike> itemLikes,
                               @NotNull RecipeCategory category, @NotNull ItemLike result,
                               float experience, int cookingTime, @NotNull String group) {
        this.oreCooking(RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, itemLikes, category, result,
                        experience, cookingTime, group, "_from_blasting");
    }

    // CUSTOM METHOD - Smelting and Blasting
    protected <T extends AbstractCookingRecipe>
              void oreCooking(@NotNull RecipeSerializer<T> serializer,
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

    // CUSTOM METHOD - Glass blocks custom recipes
    protected void glassBlocks(List<ItemLike> items) {
        // 0 -> GLASS; 1 -> GLASS PANE; 2 -> DYE COLOR.
        this.stainedGlassFromGlassAndDye(items.get(0), items.get(2));
        this.stainedGlassPaneFromGlassPaneAndDye(items.get(1), items.get(2));
        this.stainedGlassPaneFromStainedGlass(items.get(1), items.get(0));
    }

    // CUSTOM METHOD - Logs
    protected void log(List<ItemLike> items, TagKey<Item> itemTag) {
        this.planksFromLogs(items.getFirst(), itemTag, 4);
        this.woodFromLogs(items.get(1), items.get(2));
    }

    // CUSTOM METHOD - SIGN
    protected void sign(List<ItemLike> items) {
        // 0 -> SIGN; 1 -> PLANKS; 2 -> HANGING SING; 3 -> STRIPPED LOG.
        this.signBuilder(items.getFirst(), Ingredient.of(items.get(1)))
            .unlockedBy("has_item", has(items.get(1))).save(this.output);
        this.hangingSign(items.get(2), items.get(3));
    }

    // CUSTOM METHOD - ENDER PEARL blocks
    protected void enderPearlBlock(List<ItemLike> item) {
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, item.get(0), 1)
                                     .requires(item.get(1)).requires(ModBlocks.ENDER_PEARL_BLOCK.get())
                                     .unlockedBy("has_item", has(item.get(1))).save(this.output);
    }

    // CUSTOM METHOD - SHIELD
    protected void shield(List<ItemLike> items) {
        // 0 -> SHIELD; 1 -> TOOL MATERIAL;
        this.shaped(RecipeCategory.COMBAT, items.getFirst())
            .define('W', ItemTags.WOODEN_TOOL_MATERIALS)
            .define('o', items.get(1))
            .pattern("WoW")
            .pattern("WWW")
            .pattern(" W ")
            .unlockedBy(getHasName(items.get(1)), this.has(items.get(1))).save(this.output);
    }

    // CUSTOM METHOD - BOAT
    protected void boat(List<ItemLike> items) {
        // 0 -> BOAT; 1 -> CHEST BOAT; 2 -> PLANKS;
        this.woodenBoat(items.getFirst(), items.get(2)); // Boat block
        this.chestBoat(items.get(1), items.getFirst()); // Chest boat block
    }
}