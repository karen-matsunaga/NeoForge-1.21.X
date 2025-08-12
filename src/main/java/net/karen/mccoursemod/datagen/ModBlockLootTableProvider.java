package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        // Custom BLOCKS
        dropSelf(ModBlocks.ENCHANT.get());
        dropSelf(ModBlocks.DISENCHANT_INDIVIDUAL.get());
        dropSelf(ModBlocks.DISENCHANT_GROUPED.get());
        dropSelf(ModBlocks.BISMUTH_BLOCK.get());
        dropSelf(ModBlocks.MAGIC.get());
        dropSelf(ModBlocks.MCCOURSEMOD_ELEVATOR.get());

        // Mccourse Mod Ores
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
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item,
                                                       float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}