package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModEquipmentAssets;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import static net.minecraft.client.data.models.ItemModelGenerators.*;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MccourseMod.MOD_ID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels,
                                  @NotNull ItemModelGenerators itemModels) {
        // CUSTOM BLOCKS
        blockModels.createTrivialCube(ModBlocks.BISMUTH_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_ORE.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_DEEPSLATE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MAGIC.get());
        blockModels.createTrivialCube(ModBlocks.ENCHANT.get());
        blockModels.createTrivialCube(ModBlocks.DISENCHANT_GROUPED.get());
        blockModels.createTrivialCube(ModBlocks.DISENCHANT_INDIVIDUAL.get());

        // CUSTOM ITEMS
        itemModels.generateFlatItem(ModItems.BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.AUTO_SMELT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MAGNET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MORE_ORES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAINBOW.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_GENERIC_PLUS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_GENERIC_MINUS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE.get(), ModelTemplates.FLAT_ITEM);

        // CUSTOM FOODS
        itemModels.generateFlatItem(ModItems.COFFEE.get(), ModelTemplates.FLAT_ITEM);

        // CUSTOM TOOLS
        itemModels.generateFlatItem(ModItems.BISMUTH_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // CUSTOM ARMORS
        itemModels.generateTrimmableItem(ModItems.BISMUTH_HELMET.get(), ModEquipmentAssets.BISMUTH,
                                         TRIM_PREFIX_HELMET, false);

        itemModels.generateTrimmableItem(ModItems.BISMUTH_CHESTPLATE.get(), ModEquipmentAssets.BISMUTH,
                                         TRIM_PREFIX_CHESTPLATE, false);

        itemModels.generateTrimmableItem(ModItems.BISMUTH_LEGGINGS.get(), ModEquipmentAssets.BISMUTH,
                                         TRIM_PREFIX_LEGGINGS, false);

        itemModels.generateTrimmableItem(ModItems.BISMUTH_BOOTS.get(), ModEquipmentAssets.BISMUTH,
                                         TRIM_PREFIX_BOOTS, false);
    }
}