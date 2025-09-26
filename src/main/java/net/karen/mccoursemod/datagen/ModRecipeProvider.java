package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.recipe.CraftingPlusRecipeBuilder;
import net.karen.mccoursemod.recipe.GrowthChamberRecipeBuilder;
import net.karen.mccoursemod.recipe.KaupenFurnaceRecipeBuilder;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
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
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    private final HolderGetter<Item> items;

    protected ModRecipeProvider(HolderLookup.Provider registries,
                                RecipeOutput output) {
        super(registries, output);
        this.items = registries.lookupOrThrow(Registries.ITEM);
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
        public @NotNull String getName() { return "Mccourse Mod Recipes"; }
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

        List<ItemLike> PINK_SMELTABLES = List.of(ModBlocks.PINK_ORE, ModBlocks.DEEPSLATE_PINK_ORE,
                                                 ModBlocks.END_STONE_PINK_ORE, ModBlocks.NETHER_PINK_ORE);

        // ** CUSTOM Smelting + Blasting blocks, items, etc. **
        oreSmelting(BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(),
                    0.25F, 200, "bismuth");
        oreBlasting(BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(),
                    0.25F, 100, "bismuth");

        oreSmelting(ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                    0.25F, 200, "alexandrite");
        oreBlasting(ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                    0.25F, 100, "alexandrite");

        oreSmelting(PINK_SMELTABLES, RecipeCategory.MISC, ModItems.PINK.get(),
                    0.5F, 400, "pink");
        oreBlasting(PINK_SMELTABLES, RecipeCategory.MISC, ModItems.PINK.get(),
                    0.5F, 200, "pink");

        // ** CUSTOM block **
        this.nineBlockStorageRecipes(RecipeCategory.MISC, Items.ANVIL,
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENCHANT.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.BISMUTH.get(),
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE.get(),
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_ALEXANDRITE_BLOCK.get());

        this.nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.PINK.get(),
                                     RecipeCategory.BUILDING_BLOCKS, ModBlocks.PINK_BLOCK.get());

        // ** CUSTOM advanced block **
        this.shaped(RecipeCategory.TOOLS, ModBlocks.CRAFTING_PLUS.get())
            .pattern("WWW")
            .pattern("WSW")
            .pattern("WWW")
            .define('W', Items.CRAFTING_TABLE) // Main ingredient
            .define('S', Items.ANVIL) // Center ingredient
            .unlockedBy(getHasName(Items.CRAFTING_TABLE), this.has(Items.CRAFTING_TABLE))
            .save(this.output);

        // ** CUSTOM tools **
        this.allTools(List.of(ModItems.BISMUTH_SWORD.get(), ModItems.BISMUTH_PICKAXE.get(),
                              ModItems.BISMUTH_SHOVEL.get(), ModItems.BISMUTH_AXE.get(),
                              ModItems.BISMUTH_HOE.get(), ModItems.BISMUTH.get()));

        this.allTools(List.of(ModItems.ALEXANDRITE_SWORD.get(), ModItems.ALEXANDRITE_PICKAXE.get(),
                              ModItems.ALEXANDRITE_SHOVEL.get(), ModItems.ALEXANDRITE_AXE.get(),
                              ModItems.ALEXANDRITE_HOE.get(), ModItems.ALEXANDRITE.get()));

        this.allTools(List.of(ModItems.PINK_SWORD.get(), ModItems.PINK_PICKAXE.get(),
                              ModItems.PINK_SHOVEL.get(), ModItems.PINK_AXE.get(),
                              ModItems.PINK_HOE.get(), ModItems.PINK.get()));

        this.allTools(List.of(ModItems.COPPER_SWORD.get(), ModItems.COPPER_PICKAXE.get(),
                              ModItems.COPPER_SHOVEL.get(), ModItems.COPPER_AXE.get(),
                              ModItems.COPPER_HOE.get(), Items.COPPER_INGOT));

        this.allTools(List.of(ModItems.LAPIS_LAZULI_SWORD.get(), ModItems.LAPIS_LAZULI_PICKAXE.get(),
                              ModItems.LAPIS_LAZULI_SHOVEL.get(), ModItems.LAPIS_LAZULI_AXE.get(),
                              ModItems.LAPIS_LAZULI_HOE.get(), Items.LAPIS_LAZULI));

        this.allTools(List.of(ModItems.REDSTONE_SWORD.get(), ModItems.REDSTONE_PICKAXE.get(),
                              ModItems.REDSTONE_SHOVEL.get(), ModItems.REDSTONE_AXE.get(),
                              ModItems.REDSTONE_HOE.get(), Items.REDSTONE));

        // ** CUSTOM hammer **
        this.hammerTool(List.of(ModItems.BISMUTH_HAMMER.get(), ModBlocks.BISMUTH_BLOCK.get()));
        this.hammerTool(List.of(ModItems.ALEXANDRITE_HAMMER.get(), ModBlocks.ALEXANDRITE_BLOCK.get()));
        this.hammerTool(List.of(ModItems.PINK_HAMMER.get(), ModBlocks.PINK_BLOCK.get()));
        this.hammerTool(List.of(ModItems.COPPER_HAMMER.get(), Blocks.COPPER_BLOCK));
        this.hammerTool(List.of(ModItems.DIAMOND_HAMMER.get(), Blocks.DIAMOND_BLOCK));
        this.hammerTool(List.of(ModItems.GOLD_HAMMER.get(), Blocks.GOLD_BLOCK));
        this.hammerTool(List.of(ModItems.IRON_HAMMER.get(), Blocks.IRON_BLOCK));
        this.hammerTool(List.of(ModItems.STONE_HAMMER.get(), Blocks.STONE));
        this.hammerTool(List.of(ModItems.WOODEN_HAMMER.get(), ModBlocks.WALNUT_LOG));
        this.hammerTool(List.of(ModItems.LAPIS_LAZULI_HAMMER.get(), Blocks.LAPIS_BLOCK));
        this.hammerTool(List.of(ModItems.REDSTONE_HAMMER.get(), Blocks.REDSTONE_BLOCK));
        this.netheriteSmithing(ModItems.DIAMOND_HAMMER.get(), RecipeCategory.TOOLS, ModItems.NETHERITE_HAMMER.get());

        // ** CUSTOM paxel **
        this.paxelTool(List.of(ModItems.BISMUTH_PAXEL.get(), ModItems.BISMUTH_PICKAXE.get(),
                               ModItems.BISMUTH_AXE.get(), ModItems.BISMUTH_SHOVEL.get()));

        this.paxelTool(List.of(ModItems.ALEXANDRITE_PAXEL.get(), ModItems.ALEXANDRITE_PICKAXE.get(),
                               ModItems.ALEXANDRITE_AXE.get(), ModItems.ALEXANDRITE_SHOVEL.get()));

        this.paxelTool(List.of(ModItems.PINK_PAXEL.get(), ModItems.PINK_PICKAXE.get(),
                               ModItems.PINK_AXE.get(), ModItems.PINK_SHOVEL.get()));

        this.paxelTool(List.of(ModItems.COPPER_PAXEL.get(), ModItems.COPPER_PICKAXE.get(),
                               ModItems.COPPER_AXE.get(), ModItems.COPPER_SHOVEL.get()));

        this.paxelTool(List.of(ModItems.LAPIS_LAZULI_PAXEL.get(), ModItems.LAPIS_LAZULI_PICKAXE.get(),
                               ModItems.LAPIS_LAZULI_AXE.get(), ModItems.LAPIS_LAZULI_SHOVEL.get()));

        this.paxelTool(List.of(ModItems.REDSTONE_PAXEL.get(), ModItems.REDSTONE_PICKAXE.get(),
                               ModItems.REDSTONE_AXE.get(), ModItems.REDSTONE_SHOVEL.get()));

        this.paxelTool(List.of(ModItems.WOODEN_PAXEL.get(), Items.WOODEN_PICKAXE,
                               Items.WOODEN_AXE, Items.WOODEN_SHOVEL));

        this.paxelTool(List.of(ModItems.STONE_PAXEL.get(), Items.STONE_PICKAXE,
                               Items.STONE_AXE, Items.STONE_SHOVEL));

        this.paxelTool(List.of(ModItems.GOLD_PAXEL.get(), Items.GOLDEN_PICKAXE,
                               Items.GOLDEN_AXE, Items.GOLDEN_SHOVEL));

        this.paxelTool(List.of(ModItems.IRON_PAXEL.get(), Items.IRON_PICKAXE,
                               Items.IRON_AXE, Items.IRON_SHOVEL));

        this.paxelTool(List.of(ModItems.DIAMOND_PAXEL.get(), Items.DIAMOND_PICKAXE,
                               Items.DIAMOND_AXE, Items.DIAMOND_SHOVEL));

        this.netheriteSmithing(ModItems.DIAMOND_PAXEL.get(), RecipeCategory.TOOLS, ModItems.NETHERITE_PAXEL.get());

        // ** CUSTOM bow **
        this.bow(List.of(ModItems.KAUPEN_BOW.get(), ModItems.BISMUTH.get()));
        this.bow(List.of(ModItems.ALEXANDRITE_BOW.get(), ModItems.ALEXANDRITE.get()));
        this.bow(List.of(ModItems.MINER_BOW.get(), ModItems.BISMUTH.get()));

        // ** CUSTOM armors **
        this.fullArmor(List.of(ModItems.BISMUTH_HELMET.get(), ModItems.BISMUTH_CHESTPLATE.get(),
                               ModItems.BISMUTH_LEGGINGS.get(), ModItems.BISMUTH_BOOTS.get(),
                               ModItems.BISMUTH.get()));

        this.fullArmor(List.of(ModItems.ALEXANDRITE_HELMET.get(), ModItems.ALEXANDRITE_CHESTPLATE.get(),
                               ModItems.ALEXANDRITE_LEGGINGS.get(), ModItems.ALEXANDRITE_BOOTS.get(),
                               ModItems.ALEXANDRITE.get()));

        this.fullArmor(List.of(ModItems.PINK_HELMET.get(), ModItems.PINK_CHESTPLATE.get(),
                               ModItems.PINK_LEGGINGS.get(), ModItems.PINK_BOOTS.get(),
                               ModItems.PINK.get()));

        this.fullArmor(List.of(ModItems.COPPER_HELMET.get(), ModItems.COPPER_CHESTPLATE.get(),
                               ModItems.COPPER_LEGGINGS.get(), ModItems.COPPER_BOOTS.get(),
                               Items.COPPER_INGOT));

        this.fullArmor(List.of(ModItems.LAPIS_LAZULI_HELMET.get(), ModItems.LAPIS_LAZULI_CHESTPLATE.get(),
                               ModItems.LAPIS_LAZULI_LEGGINGS.get(), ModItems.LAPIS_LAZULI_BOOTS.get(),
                               Items.LAPIS_LAZULI));

        this.fullArmor(List.of(ModItems.REDSTONE_HELMET.get(), ModItems.REDSTONE_CHESTPLATE.get(),
                               ModItems.REDSTONE_LEGGINGS.get(), ModItems.REDSTONE_BOOTS.get(),
                               Items.REDSTONE));

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
                           ModItems.ALEXANDRITE.get(), "alexandrite", this.output);

        // ** CUSTOM Trim Smithing **
        // CRAFTING TABLE
        this.copySmithingTemplate(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModItems.BISMUTH.get());

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

        // ** KAUPEN FURNACE recipes **
        kaupenFurnace("ores", CookingBookCategory.BLOCKS,
                      Ingredient.of(Items.IRON_INGOT), Items.RAW_IRON, 0.5F, 50);

        kaupenFurnace("ores", CookingBookCategory.MISC,
                      Ingredient.of(Items.COAL), Items.DIAMOND, 0.5F, 50);

        kaupenFurnace("misc", CookingBookCategory.MISC,
                      Ingredient.of(Items.BONE_MEAL), Items.PHANTOM_MEMBRANE, 10.0F, 100);

        // ** CRAFTING PLUS recipes **
        // Crafting Plus 7x7 - (One item)
        craftSeven(List.of(ModBlocks.KAUPEN_FURNACE_BLOCK.get(), Items.FURNACE));
        craftSeven(List.of(ModBlocks.MCCOURSEMOD_ELEVATOR.get(), Items.WHITE_WOOL));
        // ** ENCHANTMENT recipes **
        craftSeven(List.of(ModBlocks.ENCHANT.get(), Items.ENCHANTING_TABLE));
        craftSeven(List.of(ModBlocks.DISENCHANT_GROUPED.get(), ModBlocks.DISENCHANT_INDIVIDUAL.get()));
        craftSeven(List.of(ModBlocks.DISENCHANT_INDIVIDUAL.get(), Items.ANVIL));
        // ** Advanced items recipes **
        craftSeven(List.of(ModItems.FARMER.get(), Items.BONE_MEAL));
        // ** COMPACTOR recipes **
        craftSeven(List.of(ModItems.ULTRA_COMPACTOR.get(), Items.NETHERITE_PICKAXE));
        craftSeven(List.of(ModItems.PINK_ULTRA_COMPACTOR.get(), ModItems.PINK_PICKAXE));
        // Crafting Plus 7x7 - (Three items)
        craftSevenItems(List.of(ModBlocks.MCCOURSEMOD_GENERATOR.get(), ModBlocks.CRAFTING_PLUS.get(),
                                Items.NETHER_STAR, Items.ENCHANTED_GOLDEN_APPLE));
    }

    // ** CUSTOM METHOD - CRAFTING PLUS custom recipes **
    protected CraftingPlusRecipeBuilder plusShaped(RecipeCategory category, ItemLike result) {
        return CraftingPlusRecipeBuilder.plusShaped(this.items, category, result);
    }

    protected CraftingPlusRecipeBuilder plusShaped(RecipeCategory category,
                                                   ItemLike result, int count) {
        return CraftingPlusRecipeBuilder.plusShaped(this.items, category, result, count);
    }

    // CUSTOM METHOD - Crafting Plus 7x7 (One item/recipe)
    protected void craftSeven(List<ItemLike> item) {
        // 0 -> RESULT; 1 -> Main recipe;
        ItemLike ingredient = item.get(1);
        this.plusShaped(RecipeCategory.MISC, item.get(0))
            .pattern("AAAAAAA").pattern("AAAAAAA").pattern("AAAAAAA")
            .pattern("AAAAAAA").pattern("AAAAAAA").pattern("AAAAAAA")
            .pattern("AAAAAAA")
            .plusDefine('A', ingredient)
            .unlockedBy(getHasName(ingredient), has(ingredient))
            .group("crafting_plus")
            .save(this.output);
    }

    // CUSTOM METHOD - Crafting Plus 7x7 (Three items/recipes)
    protected void craftSevenItems(List<ItemLike> item) {
        // 0 -> RESULT; 1 -> Main recipe; 2 -> Addition recipe; 3 -> Addition recipe;
        ItemLike ingredient = item.get(1);
        this.plusShaped(RecipeCategory.MISC, item.get(0))
            .pattern("AAAAAAA").pattern("ABBBBBA").pattern("ABBBBBA")
            .pattern("ABBCBBA").pattern("ABBBBBA").pattern("ABBBBBA")
            .pattern("AAAAAAA")
            .plusDefine('A', ingredient)
            .plusDefine('B', item.get(2))
            .plusDefine('C', item.get(3)) // Center ingredient
            .unlockedBy(getHasName(ingredient), has(ingredient))
            .group("crafting_plus")
            .save(this.output);
    }

    // CUSTOM METHOD - KAUPEN FURNACE custom recipes
    protected void kaupenFurnace(@NotNull String group, @NotNull CookingBookCategory category,
                                 Ingredient ingredient, Item result, float experience, int cookingTime) {
        new KaupenFurnaceRecipeBuilder(group, category, ingredient,
                                       result, experience, cookingTime)
                                      .unlockedBy(getHasName(result), has(result))
                                      .save(this.output, MccourseMod.MOD_ID + ":" +
                                            getItemName(result) + "_from_kaupen_furnace");
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

    // CUSTOM METHOD - BOW
    protected void bow(List<ItemLike> items) {
        // 0 -> BOW; 1 -> TOOL MATERIAL;
        Item string = Items.STRING;
        this.shaped(RecipeCategory.COMBAT, items.getFirst())
            .define('#', items.get(1))
            .define('X', string)
            .pattern(" #X")
            .pattern("# X")
            .pattern(" #X")
            .unlockedBy(getHasName(string), this.has(string))
            .save(this.output);
    }

    // CUSTOM METHOD - BOAT
    protected void boat(List<ItemLike> items) {
        // 0 -> BOAT; 1 -> CHEST BOAT; 2 -> PLANKS;
        this.woodenBoat(items.getFirst(), items.get(2)); // Boat block
        this.chestBoat(items.get(1), items.getFirst()); // Chest boat block
    }
}