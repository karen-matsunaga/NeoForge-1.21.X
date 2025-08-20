package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.block.ModBlocks;
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

        // ** CUSTOM ORES ** -> Mccourse Mod Ores
        // BISMUTH
        dropSelf(ModBlocks.BISMUTH_BLOCK.get());
        // ALEXANDRITE
        dropSelf(ModBlocks.ALEXANDRITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());

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

        // Crop block
        // Crop loot item drop
        LootItemCondition.Builder lootItemConditionBuilder =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RADISH_CROP.get())
                                                   .setProperties(StatePropertiesPredicate.Builder.properties()
                                                   .hasProperty(RadishCropBlock.AGE, 3));
        // Crop drop
        this.add(ModBlocks.RADISH_CROP.get(),
                 this.createCropDrops(ModBlocks.RADISH_CROP.get(), ModItems.RADISH.get(),
                                      ModItems.RADISH_SEEDS.get(), lootItemConditionBuilder));

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

        // Bloodwood log + Bloodwood wood
        this.dropSelf(ModBlocks.BLOODWOOD_LOG.get());
        this.dropSelf(ModBlocks.BLOODWOOD_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_BLOODWOOD_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_BLOODWOOD_WOOD.get());
        this.dropSelf(ModBlocks.BLOODWOOD_PLANKS.get());
        this.dropSelf(ModBlocks.BLOODWOOD_SAPLING.get());
        this.add(ModBlocks.BLOODWOOD_LEAVES.get(),
                 block -> createLeavesDrops(block, ModBlocks.BLOODWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // CUSTOM sittable block model
        this.dropSelf(ModBlocks.CHAIR.get());

        // CUSTOM block entity
        this.dropSelf(ModBlocks.PEDESTAL.get());
        this.dropSelf(ModBlocks.GROWTH_CHAMBER.get());

        // CUSTOM glass block
        this.dropSelf(ModBlocks.FORCED_STAINED_GLASS.get());
        this.dropSelf(ModBlocks.FORCED_STAINED_GLASS_PANE.get());
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