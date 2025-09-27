package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.loot.ModChestLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import java.util.function.BiConsumer;

public class ModChestLootTableProvider implements LootTableSubProvider {
    public ModChestLootTableProvider(HolderLookup.Provider registries) {}

    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        consumer.accept(ModChestLootTables.KAUPEN_HOUSE_TREASURE, addKaupenHouseChestLootTables());
    }

    // CUSTOM METHOD - KAUPEN HOUSE chest loot table
    public static LootTable.Builder addKaupenHouseChestLootTables() {
        LootTable.Builder builder = LootTable.lootTable();
        return builder.withPool(LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        // RARE chest loot table
                                        .add(LootItem.lootTableItem(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE)
                                                     .setWeight(4)
                                                     .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                        // COMMON chest loot table
                                        .add(LootItem.lootTableItem(ModItems.CHISEL)
                                                     .setWeight(1)
                                                     .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))));
    }
}