package net.karen.mccoursemod.datagen;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
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
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import org.jetbrains.annotations.NotNull;
import java.util.stream.Stream;
import static net.minecraft.client.data.models.BlockModelGenerators.*;
import static net.minecraft.client.data.models.ItemModelGenerators.*;
import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MccourseMod.MOD_ID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels,
                                  @NotNull ItemModelGenerators itemModels) {
        // ** CUSTOM BLOCKS **
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
                   .trapdoor(ModBlocks.BISMUTH_TRAPDOOR.get());

        // DOOR
        createDoorTexture(blockModels, ModBlocks.BISMUTH_DOOR.get());

        // BLOCKSTATE block
        blockstateTexture(blockModels, ModBlocks.BISMUTH_LAMP.get());

        // CUSTOM crop
        createCropBlock(blockModels, ModBlocks.RADISH_CROP.get(), RadishCropBlock.AGE, 0, 1, 2, 3);

        // CUSTOM bush crop
        createCropBlock(blockModels, ModBlocks.GOJI_BERRY_BUSH.get(), GojiBerryBushBlock.AGE,  0, 1, 2, 3);

        // Bloodwood log
        blockModels.woodProvider(ModBlocks.BLOODWOOD_LOG.get())
                   .logWithHorizontal(ModBlocks.BLOODWOOD_LOG.get())
                   .wood(ModBlocks.BLOODWOOD_WOOD.get());

        blockModels.woodProvider(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                   .logWithHorizontal(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                   .wood(ModBlocks.STRIPPED_BLOODWOOD_WOOD.get());

        blockModels.createTrivialCube(ModBlocks.BLOODWOOD_PLANKS.get());
        blockModels.createTintedLeaves(ModBlocks.BLOODWOOD_LEAVES.get(), TexturedModel.LEAVES, -12012264);
        // BLOODWOOD SAPLING -> Used Netherrack block
        createSaplingTexture(blockModels, ModBlocks.BLOODWOOD_SAPLING.get());

        // CUSTOM sittable block model
        createChairTexture(blockModels, itemModels, ModBlocks.CHAIR.get());

        // CUSTOM block entity
        createPedestalTexture(blockModels, itemModels, ModBlocks.PEDESTAL.get());

        // CUSTOM crafting block entity
        blockModels.createTrivialCube(ModBlocks.GROWTH_CHAMBER.get());

        // CUSTOM glass block
        createGlassBlocksTransparent(blockModels, ModBlocks.FORCED_STAINED_GLASS.get(), ModBlocks.FORCED_STAINED_GLASS_PANE.get());

        // ** CUSTOM ITEMS **
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

        // CUSTOM mob
        itemModels.generateFlatItem(ModItems.GECKO_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);

        // CUSTOM Throwable Projectiles
        itemModels.declareCustomModelItem(ModItems.TOMAHAWK.get());

        // CUSTOM Animated Textures
        itemModels.generateFlatItem(ModItems.RADIATION_STAFF.get(), ModelTemplates.FLAT_ITEM);
    }

    // * CUSTOM BLOCKS *
    // CUSTOM METHOD - Blockstate Texture
    protected static void blockstateTexture(BlockModelGenerators blockModels, Block block) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                   .with(createBooleanModelDispatch(BismuthLampBlock.CLICKED,
                         // Bismuth Lamp On
                         plainVariant(blockModels.createSuffixedVariant(block, "_on",
                                                           ModelTemplates.CUBE_ALL, TextureMapping::cube)),
                         // Bismuth Lamp Off
                         plainVariant(TexturedModel.CUBE.create(block, blockModels.modelOutput)))));
    }

    // CUSTOM METHOD - Chair texture
    protected static void createChairTexture(BlockModelGenerators blockModels,
                                             ItemModelGenerators itemModels, Block block) {
        ResourceLocation modelLoc = getBlockTexture(block);
        MultiVariant multiVariant = plainVariant(modelLoc);
        // assets\mccoursemod\blockstates
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                   .with(PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING)
                   .select(Direction.NORTH, multiVariant)
                   .select(Direction.SOUTH, multiVariant.with(Y_ROT_180))
                   .select(Direction.EAST, multiVariant.with(Y_ROT_90))
                   .select(Direction.WEST, multiVariant.with(Y_ROT_270))));
        // assets\mccoursemod\items
        ItemModel.Unbaked pedestalModel = ItemModelUtils.plainModel(modelLoc);
        itemModels.itemModelOutput.accept(block.asItem(), pedestalModel);
    }

    // CUSTOM METHOD - Pedestal texture
    protected static void createPedestalTexture(BlockModelGenerators blockModels,
                                                ItemModelGenerators itemModels, Block block) {
        ResourceLocation modelLoc = getBlockTexture(block);
        MultiVariant multiVariant = plainVariant(modelLoc);
        // assets\mccoursemod\blockstates
        blockModels.blockStateOutput.accept(createSimpleBlock(ModBlocks.PEDESTAL.value(), multiVariant));
        // assets\mccoursemod\items
        ItemModel.Unbaked pedestalModel = ItemModelUtils.plainModel(modelLoc);
        itemModels.itemModelOutput.accept(block.asItem(), pedestalModel);
    }

    // CUSTOM METHOD - Sapling Texture
    protected static void createSaplingTexture(BlockModelGenerators blockModels,
                                               Block block) {
        // assets\mccoursemod\blockstates
        ResourceLocation modelLoc = getBlockTexture(block);
        MultiVariant multiVariant = plainVariant(modelLoc);
        blockModels.blockStateOutput.accept(createSimpleBlock(block, multiVariant));

        // CROSS -> assets\mccoursemod\models\block + assets\mccoursemod\items
        ExtendedModelTemplate crossType = ModelTemplates.TINTED_CROSS.extend().renderType("cutout").build();
        ResourceLocation crossLocation = crossType.create(block, TextureMapping.cross(modelLoc), blockModels.modelOutput);
        blockModels.registerSimpleItemModel(block, crossLocation);
    }

    // CUSTOM METHOD - Glass Translucent texture (TRANSPARENT)
    protected static void createGlassBlocksTransparent(BlockModelGenerators blockModels,
                                                       Block glassBlock, Block paneBlock) {
        // ** STAINED GLASS model **
        TexturedModel.Provider cubeTransparent =
                TexturedModel.CUBE.updateTemplate(template -> template.extend().renderType("translucent").build());
        // assets\mccoursemod\items + assets\mccoursemod\blockstates
        blockModels.createTrivialBlock(glassBlock, cubeTransparent);
        // ** STAINED GLASS PANE model **
        TextureMapping texturemapping = TextureMapping.pane(glassBlock, paneBlock);
        // Render type
        ExtendedModelTemplate stainedGlassPost =
                ModelTemplates.STAINED_GLASS_PANE_POST.extend().renderType("translucent").build();
        ExtendedModelTemplate stainedGlassSide =
                ModelTemplates.STAINED_GLASS_PANE_SIDE.extend().renderType("translucent").build();
        ExtendedModelTemplate stainedGlassSideAlt =
                ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.extend().renderType("translucent").build();
        ExtendedModelTemplate stainedGlassSideNoside =
                ModelTemplates.STAINED_GLASS_PANE_NOSIDE.extend().renderType("translucent").build();
        ExtendedModelTemplate stainedGlassSideNosideAlt =
                ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.extend().renderType("translucent").build();
        // Multivariant
        MultiVariant multivariant =
             plainVariant(stainedGlassPost.create(paneBlock, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant1 =
             plainVariant(stainedGlassSide.create(paneBlock, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant2 =
             plainVariant(stainedGlassSideAlt.create(paneBlock, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant3 =
             plainVariant(stainedGlassSideNoside.create(paneBlock, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant4 =
             plainVariant(stainedGlassSideNosideAlt.create(paneBlock, texturemapping, blockModels.modelOutput));
        Item item = paneBlock.asItem();
        // assets\mccoursemod\items
        ExtendedModelTemplate paneTransparent = ModelTemplates.FLAT_ITEM.extend().renderType("translucent").build();
        blockModels.registerSimpleItemModel(item, paneTransparent.create(ModelLocationUtils.getModelLocation(item),
                                            TextureMapping.layer0(glassBlock), blockModels.modelOutput));
        // assets\mccoursemod\blockstates
        blockModels.blockStateOutput.accept(MultiPartGenerator.multiPart(paneBlock)
                   .with(multivariant).with(condition().term(BlockStateProperties.NORTH, true), multivariant1)
                   .with(condition().term(BlockStateProperties.EAST, true), multivariant1.with(Y_ROT_90))
                   .with(condition().term(BlockStateProperties.SOUTH, true), multivariant2)
                   .with(condition().term(BlockStateProperties.WEST, true), multivariant2.with(Y_ROT_90))
                   .with(condition().term(BlockStateProperties.NORTH, false), multivariant3)
                   .with(condition().term(BlockStateProperties.EAST, false), multivariant4)
                   .with(condition().term(BlockStateProperties.SOUTH, false), multivariant4.with(Y_ROT_90))
                   .with(condition().term(BlockStateProperties.WEST, false), multivariant3.with(Y_ROT_270)));
    }

    // CUSTOM METHOD - Crop block texture
    protected static void createCropBlock(BlockModelGenerators blockModels,
                                          Block cropBlock, Property<Integer> ageProperty,
                                          int... ageToVisualStageMapping) {
        // assets\mccoursemod\items + assets\mccoursemod\blockstates
        blockModels.registerSimpleFlatItemModel(cropBlock.asItem());
        if (ageProperty.getPossibleValues().size() != ageToVisualStageMapping.length) {
            throw new IllegalArgumentException();
        }
        else {
            Int2ObjectMap<ResourceLocation> ageState = new Int2ObjectOpenHashMap<>();
            // assets\mccoursemod\models\block
            blockModels.blockStateOutput
                       .accept(MultiVariantGenerator.dispatch(cropBlock)
                                                    .with(PropertyDispatch.initial(ageProperty)
                                                    .generate((integer) ->
                       plainVariant(ageState.computeIfAbsent(ageToVisualStageMapping[integer], (j) ->
                                                              blockModels.createSuffixedVariant(cropBlock, "_stage" + j,
                                                              ModelTemplates.CROP.extend().renderType("cutout").build(),
                                                              TextureMapping::crop))))));
        }
    }

    // CUSTOM METHOD - Door texture
    protected static void createDoorTexture(BlockModelGenerators blockModels, Block block) {
        TextureMapping texturemapping = TextureMapping.door(block);
        MultiVariant multivariant =
             plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT.extend().renderType("cutout").build()
                                                         .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant1 =
             plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.extend().renderType("cutout").build()
                                                              .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant2 =
             plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT.extend().renderType("cutout").build()
                                                          .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant3 =
             plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.extend().renderType("cutout").build()
                                                               .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant4 =
             plainVariant(ModelTemplates.DOOR_TOP_LEFT.extend().renderType("cutout").build()
                                                      .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant5 =
             plainVariant(ModelTemplates.DOOR_TOP_LEFT_OPEN.extend().renderType("cutout").build()
                                                           .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant6 =
             plainVariant(ModelTemplates.DOOR_TOP_RIGHT.extend().renderType("cutout").build()
                                                       .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant7 =
             plainVariant(ModelTemplates.DOOR_TOP_RIGHT_OPEN.extend().renderType("cutout").build()
                                                            .create(block, texturemapping, blockModels.modelOutput));
        blockModels.registerSimpleFlatItemModel(block.asItem());
        blockModels.blockStateOutput.accept(createDoor(block, multivariant, multivariant1, multivariant2,
                                                       multivariant3, multivariant4, multivariant5,
                                                       multivariant6, multivariant7));
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

    // CUSTOM METHOD - Block models -> Ignore JSON files
    @Override
    protected @NotNull Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                               .stream().filter(x ->
                                                !(x.get() == ModBlocks.PEDESTAL.get()) && !(x.get() == ModBlocks.CHAIR.get()));
    }

    // CUSTOM METHOD - Item models -> Ignore JSON files
    @Override
    protected @NotNull Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries()
                             .stream().filter(x -> x.get() != ModBlocks.PEDESTAL.asItem() &&
                                              x.get() != ModBlocks.CHAIR.asItem() && !(x.get() == ModItems.TOMAHAWK.get()));
    }
}