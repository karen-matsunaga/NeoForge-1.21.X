package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.loot.ModLootTables;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import java.util.function.BiConsumer;

public record ModLootTableProvider(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        // CHEST loot tables
        consumer.accept(ModLootTables.KAUPEN_HOUSE_TREASURE, addKaupenHouseChestLootTables());
        // FISH loot tables
        consumer.accept(ModLootTables.MCCOURSE_MOD_FISHING_ROD_TREASURE, addMccourseModFishingRodFishLootTable());
    }

    // CUSTOM METHOD - KAUPEN HOUSE chest loot table
    public LootTable.Builder addKaupenHouseChestLootTables() {
        HolderLookup.RegistryLookup<Enchantment> ench = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        LootTable.Builder builder = LootTable.lootTable();
        // COMMON chest loot table
        return builder.withPool(LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(1, 10))
                                        .add(LootItem.lootTableItem(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE)
                                                     .setWeight(4)
                                                     .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                                        .add(LootItem.lootTableItem(Items.BOOK)
                                                     .setWeight(5)
                                                     .apply(EnchantRandomlyFunction
                                                            .randomEnchantment()
                                                            .withOneOf(ench.getOrThrow(ModTags.Enchantments.ALL_ENCHANTMENTS))))
                                        .add(LootItem.lootTableItem(ModItems.CHISEL)
                                                     .setWeight(1)
                                                     .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                                        .add(LootItem.lootTableItem(Items.SPLASH_POTION)
                                                     .apply(SetPotionFunction.setPotion(Potions.NIGHT_VISION))
                                                     .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5)))
                                                     .setWeight(2))
                                        .add(LootItem.lootTableItem(ModItems.TOMAHAWK)
                                                     .setWeight(1)
                                                     .apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 16))))
                                        .add(LootItem.lootTableItem(ModItems.MCCOURSE_MOD_FISHING_ROD)
                                                     .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                     .apply(new EnchantRandomlyFunction
                                                            .Builder()
                                                            .withEnchantment(ench.getOrThrow(Enchantments.LUCK_OF_THE_SEA)))
                                                     .setWeight(1))
        );
    }

    // CUSTOM METHOD - MCCOURSE MOD FISHING ROD loot table
    public LootTable.Builder addMccourseModFishingRodFishLootTable() {
        HolderLookup.Provider provider = this.registries;
        HolderLookup.RegistryLookup<Enchantment> ench = provider.lookupOrThrow(Registries.ENCHANTMENT);
        LootTable.Builder builder = LootTable.lootTable();
        return builder.withPool(LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(ModItems.MINER_BOW)
                                                     .apply(EnchantRandomlyFunction
                                                            .randomEnchantment()
                                                            .withOneOf(ench.getOrThrow(ModTags.Enchantments.ALL_ENCHANTMENTS)))
                                                     .setWeight(1))
                                        .add(LootItem.lootTableItem(Items.BOOK)
                                                     .apply(EnchantWithLevelsFunction
                                                            .enchantWithLevels(provider,
                                                                               ConstantValue.exactly(300.0F))))
        );
    }
}