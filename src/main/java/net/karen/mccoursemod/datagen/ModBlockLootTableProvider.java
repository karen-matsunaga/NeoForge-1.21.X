package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.custom.CattailCropBlock;
import net.karen.mccoursemod.block.custom.KohlrabiCropBlock;
import net.karen.mccoursemod.block.custom.RadishCropBlock;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        // ** Custom BLOCKS **
        // ** CUSTOM Advanced blocks **
        dropSelf(ModBlocks.ENCHANT.get());
        dropSelf(ModBlocks.DISENCHANT_INDIVIDUAL.get());
        dropSelf(ModBlocks.DISENCHANT_GROUPED.get());
        dropSelf(ModBlocks.MAGIC.get());
        dropSelf(ModBlocks.MCCOURSEMOD_ELEVATOR.get());
        dropSelf(ModBlocks.MCCOURSEMOD_GENERATOR.get());
        dropSelf(ModBlocks.CRAFTING_PLUS.get());
        dropSelf(ModBlocks.SOUND.get());

        // ** CUSTOM ORES ** -> Mccourse Mod Ores
        // BISMUTH
        dropSelf(ModBlocks.BISMUTH_BLOCK.get());
        // ALEXANDRITE
        dropSelf(ModBlocks.ALEXANDRITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
        // PINK
        dropSelf(ModBlocks.PINK_BLOCK.get());

        // BISMUTH ores
        add(ModBlocks.BISMUTH_ORE.get(), block -> createOreDrop(ModBlocks.BISMUTH_ORE.get(), ModItems.RAW_BISMUTH.get()));

        add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get(), block -> createMultipleOreDrops(ModBlocks.BISMUTH_DEEPSLATE_ORE.get(),
                                                                                         ModItems.RAW_BISMUTH.get(),
                                                                                         2, 5));

        add(ModBlocks.BISMUTH_END_ORE.get(), block -> createMultipleOreDrops(ModBlocks.BISMUTH_END_ORE.get(),
                                                                                   ModItems.RAW_BISMUTH.get(),
                                                                                   3, 6));

        add(ModBlocks.BISMUTH_NETHER_ORE.get(), block -> createMultipleOreDrops(ModBlocks.BISMUTH_NETHER_ORE.get(),
                                                                                      ModItems.RAW_BISMUTH.get(),
                                                                                      4, 8));

        // ALEXANDRITE ores
        add(ModBlocks.ALEXANDRITE_ORE.get(), block -> createOreDrop(ModBlocks.ALEXANDRITE_ORE.get(),
                                                                          ModItems.RAW_ALEXANDRITE.get()));

        add(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
            block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
                                                  ModItems.RAW_ALEXANDRITE.get(), 2, 5));

        add(ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
            block -> createMultipleOreDrops(ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
                                                  ModItems.RAW_ALEXANDRITE.get(), 3, 6));

        add(ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
            block -> createMultipleOreDrops(ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
                                                  ModItems.RAW_ALEXANDRITE.get(), 4, 8));

        // PINK ores
        add(ModBlocks.PINK_ORE.get(), block -> createOreDrop(ModBlocks.PINK_ORE.get(),
                                                                   ModItems.PINK.get()));

        add(ModBlocks.DEEPSLATE_PINK_ORE.get(),
            block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_PINK_ORE.get(),
                                                  ModItems.PINK.get(), 2, 5));

        add(ModBlocks.END_STONE_PINK_ORE.get(),
            block -> createMultipleOreDrops(ModBlocks.END_STONE_PINK_ORE.get(),
                                                  ModItems.PINK.get(), 3, 6));

        add(ModBlocks.NETHER_PINK_ORE.get(),
            block -> createMultipleOreDrops(ModBlocks.NETHER_PINK_ORE.get(),
                                                  ModItems.PINK.get(), 4, 8));

        // Block families
        // BISMUTH
        dropSelf(ModBlocks.BISMUTH_STAIRS.get());
        add(ModBlocks.BISMUTH_SLAB.get(), block -> createSlabItemTable(ModBlocks.BISMUTH_SLAB.get()));
        dropSelf(ModBlocks.BISMUTH_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.BISMUTH_BUTTON.get());
        dropSelf(ModBlocks.BISMUTH_FENCE.get());
        dropSelf(ModBlocks.BISMUTH_FENCE_GATE.get());
        dropSelf(ModBlocks.BISMUTH_WALL.get());
        dropSelf(ModBlocks.BISMUTH_TRAPDOOR.get());
        add(ModBlocks.BISMUTH_DOOR.get(), block -> createDoorTable(ModBlocks.BISMUTH_DOOR.get()));

        // ALEXANDRITE
        dropSelf(ModBlocks.ALEXANDRITE_STAIRS.get());
        // Slabs - Drops are different because to put twice blocks
        add(ModBlocks.ALEXANDRITE_SLABS.get(), block -> createSlabItemTable(ModBlocks.ALEXANDRITE_SLABS.get()));
        dropSelf(ModBlocks.ALEXANDRITE_PREASSURE_PLATE.get());
        dropSelf(ModBlocks.ALEXANDRITE_BUTTON.get());
        dropSelf(ModBlocks.ALEXANDRITE_FENCE.get());
        dropSelf(ModBlocks.ALEXANDRITE_FENCE_GATE.get());
        dropSelf(ModBlocks.ALEXANDRITE_WALL.get());
        dropSelf(ModBlocks.ALEXANDRITE_TRAPDOOR.get());
        add(ModBlocks.ALEXANDRITE_DOOR.get(), block -> createDoorTable(ModBlocks.ALEXANDRITE_DOOR.get()));

        // ** CUSTOM Blockstate blocks **
        // BISMUTH
        dropSelf(ModBlocks.BISMUTH_LAMP.get());
        // ALEXANDRITE
        dropSelf(ModBlocks.ALEXANDRITE_LAMP.get());

        // ** CUSTOM Crop block **
        // RADISH Crop loot item drop
        LootItemCondition.Builder radishLootItemConditionBuilder =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RADISH_CROP.get())
                                                   .setProperties(StatePropertiesPredicate.Builder.properties()
                                                   .hasProperty(RadishCropBlock.AGE, 3));
        // RADISH Crop drop
        this.add(ModBlocks.RADISH_CROP.get(),
                 this.createCropDrops(ModBlocks.RADISH_CROP.get(), ModItems.RADISH.get(),
                                      ModItems.RADISH_SEEDS.get(), radishLootItemConditionBuilder));

        // KOHLRABI Crop loot item drop
        LootItemCondition.Builder kohlrabiLootItemConditionBuilder =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KOHLRABI_CROP.get())
                                                   .setProperties(StatePropertiesPredicate.Builder.properties()
                                                   .hasProperty(KohlrabiCropBlock.AGE, 6));

        // KOHLRABI Crop drop
        this.add(ModBlocks.KOHLRABI_CROP.get(),
                 this.createCropDrops(ModBlocks.KOHLRABI_CROP.get(), ModItems.KOHLRABI.get(),
                                      ModItems.KOHLRABI_SEEDS.get(), kohlrabiLootItemConditionBuilder));

        // ** CUSTOM Crop block with two height **
        // CATTAIL Crop loot item drop
        LootItemCondition.Builder cattailLootItemConditionBuilder =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CATTAIL_CROP.get())
                                                   .setProperties(StatePropertiesPredicate.Builder.properties()
                                                   .hasProperty(CattailCropBlock.AGE, 7))
                                                   .or(LootItemBlockStatePropertyCondition
                                                   .hasBlockStateProperties(ModBlocks.CATTAIL_CROP.get())
                                                   .setProperties(StatePropertiesPredicate.Builder.properties()
                                                   .hasProperty(CattailCropBlock.AGE, 8)));

        // CATTAIL Crop drop
        this.add(ModBlocks.CATTAIL_CROP.get(),
                 this.createCropDrops(ModBlocks.CATTAIL_CROP.get(), ModItems.CATTAIL.get(),
                                      ModItems.CATTAIL_SEEDS.get(), cattailLootItemConditionBuilder));

        // Bush crop
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.GOJI_BERRY_BUSH.get(), block -> this.applyExplosionDecay(block,
                 LootTable.lootTable().withPool(
                 LootPool.lootPool()
                         .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GOJI_BERRY_BUSH.get())
                         .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3)))
                         .add(LootItem.lootTableItem(ModItems.GOJI_BERRIES.get()))
                         .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                         .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))
                         .withPool(LootPool.lootPool()
                         .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GOJI_BERRY_BUSH.get())
                         .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2)))
                         .add(LootItem.lootTableItem(ModItems.GOJI_BERRIES.get()))
                         .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                         .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        // ** CUSTOM log **
        // Bloodwood log + Bloodwood wood
        this.dropSelf(ModBlocks.BLOODWOOD_LOG.get());
        this.dropSelf(ModBlocks.BLOODWOOD_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_BLOODWOOD_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_BLOODWOOD_WOOD.get());
        this.dropSelf(ModBlocks.BLOODWOOD_PLANKS.get());
        this.dropSelf(ModBlocks.BLOODWOOD_SAPLING.get());
        this.add(ModBlocks.BLOODWOOD_LEAVES.get(),
                 block -> createLeavesDrops(block, ModBlocks.BLOODWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Walnut log + Walnut wood
        this.dropSelf(ModBlocks.WALNUT_LOG.get());
        this.dropSelf(ModBlocks.WALNUT_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_WALNUT_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_WALNUT_WOOD.get());
        this.dropSelf(ModBlocks.WALNUT_PLANKS.get());
        this.dropSelf(ModBlocks.WALNUT_SAPLING.get());
        this.add(ModBlocks.WALNUT_LEAVES.get(),
                 block -> createLeavesDrops(block, ModBlocks.WALNUT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // ** CUSTOM sign and hanging sign **
        this.add(ModBlocks.WALNUT_SIGN.get(), block -> createSingleItemTable(ModItems.WALNUT_SIGN.get()));
        this.add(ModBlocks.WALNUT_WALL_SIGN.get(), block -> createSingleItemTable(ModItems.WALNUT_SIGN.get()));
        this.add(ModBlocks.WALNUT_HANGING_SIGN.get(), block -> createSingleItemTable(ModItems.WALNUT_HANGING_SIGN.get()));
        this.add(ModBlocks.WALNUT_WALL_HANGING_SIGN.get(), block -> createSingleItemTable(ModItems.WALNUT_HANGING_SIGN.get()));

        // CUSTOM sittable block model
        this.dropSelf(ModBlocks.CHAIR.get());

        // CUSTOM block entity
        this.dropSelf(ModBlocks.PEDESTAL.get());
        this.dropSelf(ModBlocks.GROWTH_CHAMBER.get());

        // CUSTOM glass block
        this.dropSelf(ModBlocks.FORCED_STAINED_GLASS.get());
        this.dropSelf(ModBlocks.FORCED_STAINED_GLASS_PANE.get());

        // CUSTOM ender pearl block
        this.dropSelf(ModBlocks.ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.GREEN_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.BLACK_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.MAGENTA_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.PURPLE_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.ORANGE_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.PINK_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.CYAN_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.BROWN_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.GRAY_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.RED_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.LIME_GREEN_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.YELLOW_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.BLUE_ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.WHITE_ENDER_PEARL_BLOCK.get());

        // CUSTOM mob blocks
        this.dropSelf(ModBlocks.NETHER_STAR_BLOCK.get());
        this.dropSelf(ModBlocks.GUNPOWDER_BLOCK.get());
        this.dropSelf(ModBlocks.ROTTEN_FLESH_BLOCK.get());
        this.dropSelf(ModBlocks.BLAZE_ROD_BLOCK.get());
        this.dropSelf(ModBlocks.PHANTOM_MEMBRANE_BLOCK.get());
        this.dropSelf(ModBlocks.STRING_BLOCK.get());
        this.dropSelf(ModBlocks.SPIDER_EYE_BLOCK.get());
        this.dropSelf(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK.get());
        this.dropSelf(ModBlocks.SUGAR_BLOCK.get());
        this.dropSelf(ModBlocks.SUGAR_CANE_BLOCK.get());

        // ** CUSTOM oxidizable block **
        this.dropSelf(ModBlocks.RUBY_BLOCK.get());
        this.dropSelf(ModBlocks.RUBY_BLOCK_1.get());
        this.dropSelf(ModBlocks.RUBY_BLOCK_2.get());
        this.dropSelf(ModBlocks.RUBY_BLOCK_3.get());

        this.dropSelf(ModBlocks.WAXED_RUBY_BLOCK.get());
        this.dropSelf(ModBlocks.WAXED_RUBY_BLOCK_1.get());
        this.dropSelf(ModBlocks.WAXED_RUBY_BLOCK_2.get());
        this.dropSelf(ModBlocks.WAXED_RUBY_BLOCK_3.get());

        // ** CUSTOM flowers and pot flowers **
        this.dropSelf(ModBlocks.SNAPDRAGON.get());
        this.dropPottedContents(ModBlocks.POTTED_SNAPDRAGON.get());

        // ** CUSTOM colored block **
        this.dropSelf(ModBlocks.COLORED_LEAVES.get());

        // ** CUSTOM furnace block **
        this.dropSelf(ModBlocks.KAUPEN_FURNACE_BLOCK.get());
    }

    // CUSTOM METHOD - Custom ore loot table drops
    protected LootTable.Builder createMultipleOreDrops(Block block, Item item,
                                                       float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block,
               this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                   .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                   .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}