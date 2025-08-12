package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.AlternateTexture;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.item.ModEquipmentAssets;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
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
        blockModels.createTrivialCube(ModBlocks.BISMUTH_END_ORE.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_NETHER_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MAGIC.get());
        blockModels.createTrivialCube(ModBlocks.ENCHANT.get());
        blockModels.createTrivialCube(ModBlocks.DISENCHANT_GROUPED.get());
        blockModels.createTrivialCube(ModBlocks.DISENCHANT_INDIVIDUAL.get());
        blockModels.createTrivialCube(ModBlocks.MCCOURSEMOD_ELEVATOR.get());

        // CUSTOM ITEMS
        itemModels.generateFlatItem(ModItems.BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.AUTO_SMELT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_GENERIC_PLUS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_GENERIC_MINUS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE.get(), ModelTemplates.FLAT_ITEM);

        // CUSTOM ADVANCED ITEMS
        alternateItem(itemModels, ModItems.MCCOURSE_MOD_BOTTLE.get());

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

    // CUSTOM METHOD - Alternate Texture
    public static void alternateItem(ItemModelGenerators itemModels, Item item) {
        // Boolean Texture
        ItemModel.Unbaked bottleOn = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked bottleOff = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_off",
                                                                                               ModelTemplates.FLAT_ITEM));
        itemModels.itemModelOutput.accept(item, new ConditionalItemModel.Unbaked(
           new AlternateTexture(ModDataComponentTypes.STORED_LEVELS.get()), // The property to check
           bottleOn, // When the boolean is true
           bottleOff)); // When the boolean is false
    }
}