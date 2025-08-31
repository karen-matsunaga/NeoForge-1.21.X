package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.loot.AddItemModifier;
import net.karen.mccoursemod.loot.AddSusSandItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output,
                                         CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, MccourseMod.MOD_ID);
    }

    @Override
    protected void start() {
        // ** CUSTOM LOOT TABLE items **
        this.add("radish_seeds_to_short_grass",
                 new AddItemModifier(new LootItemCondition[] {
                                     LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                                     LootItemRandomChanceCondition.randomChance(0.25f).build() }, ModItems.RADISH_SEEDS.get()));
        this.add("radish_seeds_to_tall_grass",
                 new AddItemModifier(new LootItemCondition[] {
                                     LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                                     LootItemRandomChanceCondition.randomChance(0.25f).build() }, ModItems.RADISH_SEEDS.get()));

        this.add("chisel_from_jungle_temple",
                 new AddItemModifier(new LootItemCondition[] {
                                     new LootTableIdCondition.Builder(
                                     ResourceLocation.withDefaultNamespace("chests/jungle_temple"))
                                     .build()}, ModItems.CHISEL.get()));

        this.add("berry_from_creeper",
                 new AddItemModifier(new LootItemCondition[] {
                                     new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/creeper"))
                                     .build()}, ModItems.GOJI_BERRIES.get()));

        // ** CUSTOM SUSPICIOUS SAND items **
        this.add("metal_detector_from_suspicious_sand",
                 new AddSusSandItemModifier(new LootItemCondition[] {
                                            new LootTableIdCondition.Builder(
                                            ResourceLocation.withDefaultNamespace("archaeology/desert_pyramid"))
                                            .build() }, ModItems.METAL_DETECTOR.get()));
    }
}