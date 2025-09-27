package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.loot.ModChestLootTables;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import java.util.function.BiConsumer;

public record ModChestLootTableProvider(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        consumer.accept(ModChestLootTables.KAUPEN_HOUSE_TREASURE, addKaupenHouseChestLootTables());
    }

    // CUSTOM METHOD - KAUPEN HOUSE chest loot table
    public LootTable.Builder addKaupenHouseChestLootTables() {
        LootTable.Builder builder = LootTable.lootTable();
                      // CHEST 1 - RARE chest loot table
        return builder.withPool(LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(1, 10))
                                        .add(LootItem.lootTableItem(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE)
                                                     .setWeight(4)
                                                     .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                                        .add(LootItem.lootTableItem(Items.BOOK)
                                                     .setWeight(5)
                                                     .apply(EnchantRandomlyFunction.randomEnchantment()
                                                                                   .withOneOf(registries.lookupOrThrow(Registries.ENCHANTMENT)
                                                                                                        .getOrThrow(ModTags.Enchantments.ALL_ENCHANTMENTS))))
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
                                                     .setWeight(1)));
    }
}