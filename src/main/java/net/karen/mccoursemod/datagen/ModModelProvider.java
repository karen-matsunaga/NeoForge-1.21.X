package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.custom.BismuthLampBlock;
import net.karen.mccoursemod.block.custom.GojiBerryBushBlock;
import net.karen.mccoursemod.block.custom.RadishCropBlock;
import net.karen.mccoursemod.component.AlternateTexture;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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
        blockModels.createTrivialCube(ModBlocks.BISMUTH_ORE.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_DEEPSLATE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_END_ORE.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_NETHER_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MAGIC.get());
        blockModels.createTrivialCube(ModBlocks.ENCHANT.get());
        blockModels.createTrivialCube(ModBlocks.DISENCHANT_GROUPED.get());
        blockModels.createTrivialCube(ModBlocks.DISENCHANT_INDIVIDUAL.get());
        blockModels.createTrivialCube(ModBlocks.MCCOURSEMOD_ELEVATOR.get());

        // BLOCK FAMILIES
        blockModels.family(ModBlocks.BISMUTH_BLOCK.get())
                   .fence(ModBlocks.BISMUTH_FENCE.get())
                   .fenceGate(ModBlocks.BISMUTH_FENCE_GATE.get())
                   .wall(ModBlocks.BISMUTH_WALL.get())
                   .stairs(ModBlocks.BISMUTH_STAIRS.get())
                   .slab(ModBlocks.BISMUTH_SLAB.get())
                   .button(ModBlocks.BISMUTH_BUTTON.get())
                   .pressurePlate(ModBlocks.BISMUTH_PRESSURE_PLATE.get())
                   .door(ModBlocks.BISMUTH_DOOR.get())
                   .trapdoor(ModBlocks.BISMUTH_TRAPDOOR.get());

        // BLOCKSTATE block
        blockstateTexture(blockModels, ModBlocks.BISMUTH_LAMP.get());

        // CUSTOM crop
        blockModels.createCropBlock(ModBlocks.RADISH_CROP.get(), RadishCropBlock.AGE, 0, 1, 2, 3);

        // CUSTOM bush crop
        blockModels.createCropBlock(ModBlocks.GOJI_BERRY_BUSH.get(), GojiBerryBushBlock.AGE,  0, 1, 2, 3);

        // Bloodwood log
        blockModels.woodProvider(ModBlocks.BLOODWOOD_LOG.get())
                   .logWithHorizontal(ModBlocks.BLOODWOOD_LOG.get())
                   .wood(ModBlocks.BLOODWOOD_WOOD.get());

        blockModels.woodProvider(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                   .logWithHorizontal(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                   .wood(ModBlocks.STRIPPED_BLOODWOOD_WOOD.get());

        blockModels.createTrivialCube(ModBlocks.BLOODWOOD_PLANKS.get());
        blockModels.createTintedLeaves(ModBlocks.BLOODWOOD_LEAVES.get(), TexturedModel.LEAVES, -12012264);
        blockModels.createCrossBlock(ModBlocks.BLOODWOOD_SAPLING.get(), BlockModelGenerators.PlantType.TINTED);

        // CUSTOM ITEMS
        itemModels.generateFlatItem(ModItems.BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.AUTO_SMELT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_GENERIC_PLUS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_GENERIC_MINUS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE.get(), ModelTemplates.FLAT_ITEM);

        // CUSTOM ADVANCED ITEMS
        alternateItemTexture(itemModels, ModItems.MCCOURSE_MOD_BOTTLE.get());
        booleanItemTexture(itemModels, ModItems.CHISEL.get());

        // CUSTOM MUSIC DISC
        itemModels.generateFlatItem(ModItems.BAR_BRAWL_MUSIC_DISC.get(), ModelTemplates.FLAT_ITEM);

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

        // CUSTOM bow
        createBowTexture(itemModels, ModItems.KAUPEN_BOW.get());

        // CUSTOM Horse Armor
        itemModels.generateFlatItem(ModItems.BISMUTH_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);

        // CUSTOM ARMORS
        itemModels.generateTrimmableItem(ModItems.BISMUTH_HELMET.get(), ModEquipmentAssetProvider.BISMUTH,
                                         TRIM_PREFIX_HELMET, false);

        itemModels.generateTrimmableItem(ModItems.BISMUTH_CHESTPLATE.get(), ModEquipmentAssetProvider.BISMUTH,
                                         TRIM_PREFIX_CHESTPLATE, false);

        itemModels.generateTrimmableItem(ModItems.BISMUTH_LEGGINGS.get(), ModEquipmentAssetProvider.BISMUTH,
                                         TRIM_PREFIX_LEGGINGS, false);

        itemModels.generateTrimmableItem(ModItems.BISMUTH_BOOTS.get(), ModEquipmentAssetProvider.BISMUTH,
                                         TRIM_PREFIX_BOOTS, false);

        // Custom Smithing Template
        itemModels.generateFlatItem(ModItems.KAUPEN_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);

        // CUSTOM seeds
        itemModels.generateFlatItem(ModItems.RADISH.get(), ModelTemplates.FLAT_ITEM);

        // CUSTOM fuels
        itemModels.generateFlatItem(ModItems.FROSTFIRE_ICE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.STARLIGHT_ASHES.get(), ModelTemplates.FLAT_ITEM);
    }

    // * CUSTOM BLOCKS *
    // CUSTOM METHOD - Blockstate Texture
    protected static void blockstateTexture(BlockModelGenerators blockModels, Block block) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                   .with(BlockModelGenerators.createBooleanModelDispatch(BismuthLampBlock.CLICKED,
                         // Bismuth Lamp On
                         BlockModelGenerators.plainVariant(blockModels.createSuffixedVariant(block, "_on",
                                                           ModelTemplates.CUBE_ALL, TextureMapping::cube)),
                         // Bismuth Lamp Off
                         BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(block, blockModels.modelOutput)))));
    }

    // * CUSTOM ITEMS *
    // CUSTOM METHOD - Alternate item textures
    protected static void alternateItemTexture(ItemModelGenerators itemModels, Item item) {
        // Boolean Texture
        ItemModel.Unbaked bottleOn = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked bottleOff = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_off",
                                                                                               ModelTemplates.FLAT_ITEM));
        itemModels.itemModelOutput.accept(item, new ConditionalItemModel.Unbaked(
            new AlternateTexture(ModDataComponentTypes.STORED_LEVELS.get()), // The property to check
                                 bottleOn, // When the boolean is true
                                 bottleOff)); // When the boolean is false
    }

    // CUSTOM METHOD - Boolean item textures
    protected static void booleanItemTexture(ItemModelGenerators itemModels, Item item) {
        ItemModel.Unbaked chisel = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked usedChisel = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_used",
                                                                                                ModelTemplates.FLAT_ITEM));
        itemModels.itemModelOutput.register(ModItems.CHISEL.get(),
                  new ClientItem(new ConditionalItemModel.Unbaked(
                                 new HasComponent(ModDataComponentTypes.COORDINATES.get(), false),
                                 usedChisel, chisel), new ClientItem.Properties(false, false)));
    }

    // CUSTOM METHOD - Bow texture
    protected static void createBowTexture(ItemModelGenerators itemModels, Item item) {
        itemModels.createFlatItemModel(item, ModelTemplates.BOW);
        itemModels.generateBow(item);
    }
}