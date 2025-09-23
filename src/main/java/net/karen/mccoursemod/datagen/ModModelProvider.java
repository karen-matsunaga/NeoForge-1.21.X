package net.karen.mccoursemod.datagen;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.custom.*;
import net.karen.mccoursemod.component.custom.AlternateTexture;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.entity.client.ShieldSpecialModelRenderer;
import net.karen.mccoursemod.fluid.ModFluids;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.trim.ModTrimMaterials;
import net.minecraft.client.color.item.Dye;
import net.minecraft.client.data.models.*;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
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
        // ** CUSTOM ores **
        // BISMUTH
        blockModels.createTrivialCube(ModBlocks.BISMUTH_ORE.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_DEEPSLATE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_END_ORE.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_NETHER_ORE.get());

        // ALEXANDRITE
        blockModels.createTrivialCube(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.ALEXANDRITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.END_STONE_ALEXANDRITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.NETHER_ALEXANDRITE_ORE.get());

        // PINK
        blockModels.createTrivialCube(ModBlocks.PINK_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.PINK_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_PINK_ORE.get());
        blockModels.createTrivialCube(ModBlocks.END_STONE_PINK_ORE.get());
        blockModels.createTrivialCube(ModBlocks.NETHER_PINK_ORE.get());

        // ** CUSTOM Advanced blocks **
        blockModels.createTrivialCube(ModBlocks.MAGIC.get());
        blockModels.createTrivialCube(ModBlocks.ENCHANT.get());
        blockModels.createTrivialCube(ModBlocks.DISENCHANT_GROUPED.get());
        blockModels.createTrivialCube(ModBlocks.DISENCHANT_INDIVIDUAL.get());
        blockModels.createTrivialCube(ModBlocks.MCCOURSEMOD_ELEVATOR.get());
        blockModels.createTrivialCube(ModBlocks.MCCOURSEMOD_GENERATOR.get());
        blockModels.createTrivialCube(ModBlocks.SOUND.get());
        blockModels.createCraftingTableLike(ModBlocks.CRAFTING_PLUS.get(),
                                            Blocks.OAK_LOG, TextureMapping::craftingTable);

        // ** BLOCK FAMILIES **
        // BISMUTH
        blockModels.family(ModBlocks.BISMUTH_BLOCK.get())
                   .fence(ModBlocks.BISMUTH_FENCE.get())
                   .fenceGate(ModBlocks.BISMUTH_FENCE_GATE.get())
                   .wall(ModBlocks.BISMUTH_WALL.get())
                   .stairs(ModBlocks.BISMUTH_STAIRS.get())
                   .slab(ModBlocks.BISMUTH_SLAB.get())
                   .button(ModBlocks.BISMUTH_BUTTON.get())
                   .pressurePlate(ModBlocks.BISMUTH_PRESSURE_PLATE.get());

        // DOOR
        createDoorTexture(blockModels, ModBlocks.BISMUTH_DOOR.get());

        // TRAPDOOR
        createTrapdoorTexture(blockModels, ModBlocks.BISMUTH_TRAPDOOR.get());

        // ALEXANDRITE
        blockModels.family(ModBlocks.ALEXANDRITE_BLOCK.get())
                   .fence(ModBlocks.ALEXANDRITE_FENCE.get())
                   .fenceGate(ModBlocks.ALEXANDRITE_FENCE_GATE.get())
                   .wall(ModBlocks.ALEXANDRITE_WALL.get())
                   .stairs(ModBlocks.ALEXANDRITE_STAIRS.get())
                   .slab(ModBlocks.ALEXANDRITE_SLABS.get())
                   .button(ModBlocks.ALEXANDRITE_BUTTON.get())
                   .pressurePlate(ModBlocks.ALEXANDRITE_PREASSURE_PLATE.get());

        // DOOR
        createDoorTexture(blockModels, ModBlocks.ALEXANDRITE_DOOR.get());

        // TRAPDOOR
        createTrapdoorTexture(blockModels, ModBlocks.ALEXANDRITE_TRAPDOOR.get());

        // ** CUSTOM BLOCKSTATE block **
        // BISMUTH
        blockstateTexture(blockModels, ModBlocks.BISMUTH_LAMP.get());
        // ALEXANDRITE
        blockstateTexture(blockModels, ModBlocks.ALEXANDRITE_LAMP.get());

        // ** CUSTOM crop **
        // RADISH
        createCropBlock(blockModels, ModBlocks.RADISH_CROP.get(),
                        RadishCropBlock.AGE, 0, 1, 2, 3);

        // KOHLRABI
        createCropBlock(blockModels, ModBlocks.KOHLRABI_CROP.get(),
                        KohlrabiCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6);

        // ** CUSTOM Crop block with two height **
        // CATTAIL
        createCropBlock(blockModels, ModBlocks.CATTAIL_CROP.get(),
                        CattailCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7, 8);

        // ** CUSTOM bush crop **
        createCropBlock(blockModels, ModBlocks.GOJI_BERRY_BUSH.get(),
                        GojiBerryBushBlock.AGE,  0, 1, 2, 3);

        // ** CUSTOM log block **
        // BLOODWOOD
        blockModels.woodProvider(ModBlocks.BLOODWOOD_LOG.get())
                   .logWithHorizontal(ModBlocks.BLOODWOOD_LOG.get())
                   .wood(ModBlocks.BLOODWOOD_WOOD.get());

        blockModels.woodProvider(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                   .logWithHorizontal(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                   .wood(ModBlocks.STRIPPED_BLOODWOOD_WOOD.get());

        blockModels.createTrivialCube(ModBlocks.BLOODWOOD_PLANKS.get());

        blockModels.createTintedLeaves(ModBlocks.BLOODWOOD_LEAVES.get(),
                                       TexturedModel.LEAVES.updateTemplate(template ->
                                                                           template.extend()
                                                                                   .renderType("cutout_mipped").build()),
                                                                           -12012264);

        createSaplingTexture(blockModels, ModBlocks.BLOODWOOD_SAPLING.get()); // BLOODWOOD SAPLING -> Used Netherrack block

        // WALNUT
        blockModels.woodProvider(ModBlocks.WALNUT_LOG.get())
                   .logWithHorizontal(ModBlocks.WALNUT_LOG.get())
                   .wood(ModBlocks.WALNUT_WOOD.get());

        blockModels.woodProvider(ModBlocks.STRIPPED_WALNUT_LOG.get())
                   .logWithHorizontal(ModBlocks.STRIPPED_WALNUT_LOG.get())
                   .wood(ModBlocks.STRIPPED_WALNUT_WOOD.get());

        blockModels.createTrivialCube(ModBlocks.WALNUT_PLANKS.get());

        blockModels.createTintedLeaves(ModBlocks.WALNUT_LEAVES.get(),
                                       TexturedModel.LEAVES.updateTemplate(template ->
                                                                           template.extend().renderType("cutout_mipped").build()),
                                                                           -12012264);

        createSaplingTexture(blockModels, ModBlocks.WALNUT_SAPLING.get()); // WALNUT SAPLING -> Used End block

        // ** CUSTOM colored blocks **
        blockModels.createTintedLeaves(ModBlocks.COLORED_LEAVES.get(),
                                       TexturedModel.LEAVES.updateTemplate(template ->
                                                                           template.extend().renderType("cutout_mipped").build()),
                                                                           -12012264);

        // ** CUSTOM sittable block model **
        createChairTexture(blockModels, itemModels, ModBlocks.CHAIR.get());

        // ** CUSTOM block entity **
        createPedestalTexture(blockModels, itemModels, ModBlocks.PEDESTAL.get());

        // ** CUSTOM crafting block entity **
        blockModels.createTrivialCube(ModBlocks.GROWTH_CHAMBER.get());

        // ** CUSTOM glass block **
        createGlassBlocksTransparent(blockModels, ModBlocks.FORCED_STAINED_GLASS.get(),
                                     ModBlocks.FORCED_STAINED_GLASS_PANE.get());

        // ** CUSTOM fluid block **
        waterTexture(blockModels, ModFluids.SOAP_WATER_BLOCK.get());

        // ** CUSTOM Sign and Hanging sign **
        blockModels.createHangingSign(ModBlocks.STRIPPED_WALNUT_LOG.get(),
                                      ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get());

        blockModels.createHangingSign(ModBlocks.WALNUT_PLANKS.get(),
                                      ModBlocks.WALNUT_WALL_SIGN.get(), ModBlocks.WALNUT_SIGN.get());

        // ** CUSTOM Ender Pearl block **
        blockModels.createTrivialCube(ModBlocks.ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.GREEN_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.BLACK_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.MAGENTA_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.PURPLE_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.ORANGE_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.PINK_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CYAN_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.BROWN_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.GRAY_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RED_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LIME_GREEN_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.YELLOW_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.BLUE_ENDER_PEARL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.WHITE_ENDER_PEARL_BLOCK.get());

        // ** CUSTOM mob blocks **
        blockModels.createTrivialCube(ModBlocks.NETHER_STAR_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.GUNPOWDER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.ROTTEN_FLESH_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.BLAZE_ROD_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.PHANTOM_MEMBRANE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.STRING_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.SPIDER_EYE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.SUGAR_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.SUGAR_CANE_BLOCK.get());

        // ** CUSTOM oxidizable block **
        blockModels.createTrivialCube(ModBlocks.RUBY_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RUBY_BLOCK_1.get());
        blockModels.createTrivialCube(ModBlocks.RUBY_BLOCK_2.get());
        blockModels.createTrivialCube(ModBlocks.RUBY_BLOCK_3.get());

        blockModels.createTrivialCube(ModBlocks.WAXED_RUBY_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.WAXED_RUBY_BLOCK_1.get());
        blockModels.createTrivialCube(ModBlocks.WAXED_RUBY_BLOCK_2.get());
        blockModels.createTrivialCube(ModBlocks.WAXED_RUBY_BLOCK_3.get());

        // ** CUSTOM flowers and pot flowers **
        flowerTexture(blockModels, ModBlocks.SNAPDRAGON.get(),
                      ModBlocks.POTTED_SNAPDRAGON.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // ** CUSTOM portal **
        blockModels.createTrivialCube(ModBlocks.KAUPEN_PORTAL.get());

        // ** CUSTOM furnace **
        blockModels.createFurnace(ModBlocks.KAUPEN_FURNACE_BLOCK.get(), TexturedModel.ORIENTABLE_ONLY_TOP);

        // ** CUSTOM block projectile **
        dynamicProjectile(blockModels, ModBlocks.DICE.get());

        // ** CUSTOM ITEMS **
        // ** CUSTOM ore items **
        // BISMUTH
        itemModels.generateFlatItem(ModItems.BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        // ALEXANDRITE
        itemModels.generateFlatItem(ModItems.ALEXANDRITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_ALEXANDRITE.get(), ModelTemplates.FLAT_ITEM);
        // PINK
        itemModels.generateFlatItem(ModItems.PINK.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM ADVANCED ITEMS **
        itemModels.generateFlatItem(ModItems.GROWTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_GENERIC_PLUS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_GENERIC_MINUS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE.get(), ModelTemplates.FLAT_ITEM);
        alternateItemTexture(itemModels, ModItems.MCCOURSE_MOD_BOTTLE.get());
        booleanItemTexture(itemModels, ModItems.CHISEL.get(), ModDataComponentTypes.COORDINATES.get());
        booleanItemTexture(itemModels, ModItems.DATA_TABLET.get(), ModDataComponentTypes.FOUND_BLOCK.get());
        itemModels.generateFlatItem(ModItems.METAL_DETECTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ULTRA_COMPACTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PINK_ULTRA_COMPACTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RESTORE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.FARMER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUCK_GENERAL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUCK_PICKAXE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUCK_WEAPON.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM MUSIC DISC **
        itemModels.generateFlatItem(ModItems.BAR_BRAWL_MUSIC_DISC.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM FOODS **
        itemModels.generateFlatItem(ModItems.COFFEE.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM TOOLS **
        // BISMUTH
        itemModels.generateFlatItem(ModItems.BISMUTH_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        // ALEXANDRITE
        itemModels.generateFlatItem(ModItems.ALEXANDRITE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ALEXANDRITE_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ALEXANDRITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ALEXANDRITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ALEXANDRITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ALEXANDRITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ALEXANDRITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // ** CUSTOM bow **
        // BISMUTH
        createBowTexture(itemModels, ModItems.KAUPEN_BOW.get());
        // ALEXANDRITE
        createBowTexture(itemModels, ModItems.ALEXANDRITE_BOW.get());
        // MINER
        createBowTexture(itemModels, ModItems.MINER_BOW.get());

        // ** CUSTOM paxel **
        itemModels.generateFlatItem(ModItems.PINK_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.DIAMOND_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.GOLD_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.IRON_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.STONE_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.WOODEN_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.NETHERITE_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LAPIS_LAZULI_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.REDSTONE_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // ** CUSTOM hammer **
        itemModels.generateFlatItem(ModItems.PINK_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.DIAMOND_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.GOLD_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.IRON_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.STONE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.WOODEN_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.NETHERITE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LAPIS_LAZULI_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.REDSTONE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // ** CUSTOM shovel **
        itemModels.generateFlatItem(ModItems.PINK_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LAPIS_LAZULI_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.REDSTONE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // ** CUSTOM axe **
        itemModels.generateFlatItem(ModItems.PINK_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LAPIS_LAZULI_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.REDSTONE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // ** CUSTOM hoe **
        itemModels.generateFlatItem(ModItems.PINK_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LAPIS_LAZULI_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.REDSTONE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // ** CUSTOM pickaxe **
        itemModels.generateFlatItem(ModItems.PINK_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LAPIS_LAZULI_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.REDSTONE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // ** CUSTOM sword **
        itemModels.generateFlatItem(ModItems.PINK_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LAPIS_LAZULI_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.REDSTONE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // ** CUSTOM Horse Armor **
        // BISMUTH
        itemModels.generateFlatItem(ModItems.BISMUTH_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        // ALEXANDRITE
        itemModels.generateFlatItem(ModItems.ALEXANDRITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM ARMORS **
        // BISMUTH
        createArmorTrim(itemModels, ModEquipmentAssetProvider.BISMUTH,
                        List.of(ModItems.BISMUTH_HELMET.get(), ModItems.BISMUTH_CHESTPLATE.get(),
                                ModItems.BISMUTH_LEGGINGS.get(), ModItems.BISMUTH_BOOTS.get()));
        // ALEXANDRITE
        createArmorTrim(itemModels, ModEquipmentAssetProvider.ALEXANDRITE,
                        List.of(ModItems.ALEXANDRITE_HELMET.get(), ModItems.ALEXANDRITE_CHESTPLATE.get(),
                                ModItems.ALEXANDRITE_LEGGINGS.get(), ModItems.ALEXANDRITE_BOOTS.get()));

        // PINK
        createArmorTrim(itemModels, ModEquipmentAssetProvider.PINK,
                        List.of(ModItems.PINK_HELMET.get(), ModItems.PINK_CHESTPLATE.get(),
                                ModItems.PINK_LEGGINGS.get(), ModItems.PINK_BOOTS.get()));

        // COPPER
        createArmorTrim(itemModels, ModEquipmentAssetProvider.COPPER,
                        List.of(ModItems.COPPER_HELMET.get(), ModItems.COPPER_CHESTPLATE.get(),
                                ModItems.COPPER_LEGGINGS.get(), ModItems.COPPER_BOOTS.get()));

        // LAPIS LAZULI
        createArmorTrim(itemModels, ModEquipmentAssetProvider.LAPIS_LAZULI,
                        List.of(ModItems.LAPIS_LAZULI_HELMET.get(), ModItems.LAPIS_LAZULI_CHESTPLATE.get(),
                                ModItems.LAPIS_LAZULI_LEGGINGS.get(), ModItems.LAPIS_LAZULI_BOOTS.get()));

        // REDSTONE
        createArmorTrim(itemModels, ModEquipmentAssetProvider.REDSTONE,
                        List.of(ModItems.REDSTONE_HELMET.get(), ModItems.REDSTONE_CHESTPLATE.get(),
                                ModItems.REDSTONE_LEGGINGS.get(), ModItems.REDSTONE_BOOTS.get()));

        // ** VANILLA ARMORS **
        createArmorTrim(itemModels, EquipmentAssets.CHAINMAIL,
                        List.of(Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE,
                                Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS));

        createArmorTrim(itemModels, EquipmentAssets.DIAMOND,
                        List.of(Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE,
                                Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS));

        createArmorTrim(itemModels, EquipmentAssets.GOLD,
                        List.of(Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE,
                                Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS));

        createArmorTrim(itemModels, EquipmentAssets.IRON,
                        List.of(Items.IRON_HELMET, Items.IRON_CHESTPLATE,
                                Items.IRON_LEGGINGS, Items.IRON_BOOTS));

        createArmorTrim(itemModels, EquipmentAssets.LEATHER,
                        List.of(Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE,
                                Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS));

        createArmorTrim(itemModels, EquipmentAssets.NETHERITE,
                        List.of(Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE,
                                Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS));

        pieceArmorTrim(itemModels, Items.TURTLE_HELMET, EquipmentAssets.TURTLE_SCUTE, TRIM_PREFIX_HELMET, false);

        // ** CUSTOM Smithing Template **
        itemModels.generateFlatItem(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM seeds **
        itemModels.generateFlatItem(ModItems.RADISH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.KOHLRABI.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CATTAIL.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM fuels **
        itemModels.generateFlatItem(ModItems.FROSTFIRE_ICE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.STARLIGHT_ASHES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PEAT_BRICK.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM mob **
        // GECKO
        itemModels.generateFlatItem(ModItems.GECKO_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        // RHINO
        itemModels.generateFlatItem(ModItems.RHINO_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM Throwable Projectiles **
        itemModels.declareCustomModelItem(ModItems.TOMAHAWK.get());
        itemModels.generateFlatItem(ModItems.TORCH_BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BOUNCY_BALLS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BOUNCY_BALLS_PARTICLES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.DICE_ITEM.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM Animated Textures **
        itemModels.generateFlatItem(ModItems.RADIATION_STAFF.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM Fishing Rod **
        itemModels.generateFishingRod(ModItems.MCCOURSE_MOD_FISHING_ROD.get());

        // ** CUSTOM Shield **
        shieldTexture(itemModels, ModItems.ALEXANDRITE_SHIELD.get());

        // ** CUSTOM Elytra **
        itemModels.generateElytra(ModItems.DIAMOND_ELYTRA.get());

        // ** CUSTOM Fluid **
        itemModels.generateFlatItem(ModFluids.SOAP_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);

        // ** CUSTOM Boat **
        itemModels.generateFlatItem(ModItems.WALNUT_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.WALNUT_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
    }

    // * CUSTOM BLOCKS *
    // CUSTOM METHOD - Flower and Pot Flower block textures
    protected static void flowerTexture(BlockModelGenerators blockModels,
                                        Block flower, Block pottedFlower,
                                        PlantType plantType) {
        // FLOWER
        // assets\mccoursemod\items + assets\mccoursemod\models\item
        blockModels.registerSimpleItemModel(flower.asItem(),
                                            blockModels.createFlatItemModelWithBlockTexture(flower.asItem(), flower));

        TextureMapping textureMapping = plantType.getTextureMapping(flower);

        MultiVariant flowerMultivariant =
             plainVariant(plantType.getCross().extend().renderType("cutout").build()
                                   .create(flower, textureMapping, blockModels.modelOutput));

        // assets\mccoursemod\blockstates + assets\mccoursemod\models\block
        blockModels.blockStateOutput.accept(createSimpleBlock(flower, flowerMultivariant));

        // POT FLOWER
        // assets\mccoursemod\items
        ResourceLocation modelLoc = getBlockTexture(pottedFlower);
        ItemModel.Unbaked pottedFlowerModel = ItemModelUtils.plainModel(modelLoc);
        blockModels.itemModelOutput.accept(pottedFlower.asItem(), pottedFlowerModel);

        TextureMapping flowerTextureMapping = plantType.getPlantTextureMapping(flower);
        MultiVariant pottedMultivariant =
             plainVariant(plantType.getCrossPot().extend().renderType("cutout").build()
                                   .create(pottedFlower, flowerTextureMapping, blockModels.modelOutput));

        // assets\mccoursemod\blockstates
        blockModels.blockStateOutput.accept(createSimpleBlock(pottedFlower, pottedMultivariant));
    }

    // CUSTOM METHOD - Water block texture
    protected static void waterTexture(BlockModelGenerators blockModels, Block block) {
        blockModels.createNonTemplateModelBlock(Blocks.BUBBLE_COLUMN, block);
        blockModels.createNonTemplateModelBlock(block);
    }

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
        ItemModel.Unbaked chairModel = ItemModelUtils.plainModel(modelLoc);
        itemModels.itemModelOutput.accept(block.asItem(), chairModel);
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

    // CUSTOM METHOD - Door block texture
    protected static void createDoorTexture(BlockModelGenerators blockModels, Block block) {
        TextureMapping texturemapping = TextureMapping.door(block);
        MultiVariant doorBottomLeft =
             plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT.extend().renderType("cutout").build()
                                                         .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant doorBottomLeftOpen =
             plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.extend().renderType("cutout").build()
                                                              .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant doorBottomRight =
             plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT.extend().renderType("cutout").build()
                                                          .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant doorBottomRightOpen =
             plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.extend().renderType("cutout").build()
                                                               .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant doorTopLeft =
             plainVariant(ModelTemplates.DOOR_TOP_LEFT.extend().renderType("cutout").build()
                                                      .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant doorTopLeftOpen =
             plainVariant(ModelTemplates.DOOR_TOP_LEFT_OPEN.extend().renderType("cutout").build()
                                                           .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant doorTopRight =
             plainVariant(ModelTemplates.DOOR_TOP_RIGHT.extend().renderType("cutout").build()
                                                       .create(block, texturemapping, blockModels.modelOutput));
        MultiVariant doorTopRightOpen =
             plainVariant(ModelTemplates.DOOR_TOP_RIGHT_OPEN.extend().renderType("cutout").build()
                                                            .create(block, texturemapping, blockModels.modelOutput));
        blockModels.registerSimpleFlatItemModel(block.asItem());
        blockModels.blockStateOutput.accept(createDoor(block, doorBottomLeft, doorBottomLeftOpen, doorBottomRight,
                                                       doorBottomRightOpen, doorTopLeft, doorTopLeftOpen,
                                                       doorTopRight, doorTopRightOpen));
    }

    // CUSTOM METHOD - Trapdoor block texture
    protected static void createTrapdoorTexture(BlockModelGenerators blockModels, Block block) {
        if (BlockModelGenerators.NON_ORIENTABLE_TRAPDOOR.contains(block)) {
            blockModels.createTrapdoor(block);
            TextureMapping textureMapping = TextureMapping.defaultTexture(block);
            MultiVariant trapdoorTop =
                 plainVariant(ModelTemplates.TRAPDOOR_TOP.extend().renderType("cutout").build()
                                                         .create(block, textureMapping, blockModels.modelOutput));
            ResourceLocation location = ModelTemplates.TRAPDOOR_BOTTOM.extend().renderType("cutout").build()
                                                                      .create(block, textureMapping, blockModels.modelOutput);
            MultiVariant trapdoorOpen =
                 plainVariant(ModelTemplates.TRAPDOOR_OPEN.extend().renderType("cutout").build()
                                                          .create(block, textureMapping, blockModels.modelOutput));
            blockModels.blockStateOutput.accept(createTrapdoor(block, trapdoorTop, plainVariant(location), trapdoorOpen));
            blockModels.registerSimpleItemModel(block, location);
        }
        else {
            TextureMapping textureMapping = TextureMapping.defaultTexture(block);
            MultiVariant orientableTrapdoorTop =
                 plainVariant(ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.extend().renderType("cutout").build()
                                                                    .create(block, textureMapping, blockModels.modelOutput));
            ResourceLocation location =
                    ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.extend().renderType("cutout").build()
                                                             .create(block, textureMapping, blockModels.modelOutput);
            MultiVariant orientableTrapdoorOpen =
                 plainVariant(ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.extend().renderType("cutout").build()
                                                                     .create(block, textureMapping, blockModels.modelOutput));
            blockModels.blockStateOutput.accept(createOrientableTrapdoor(block, orientableTrapdoorTop,
                                                                         plainVariant(location), orientableTrapdoorOpen));
            blockModels.registerSimpleItemModel(block, location);
        }
    }

    // CUSTOM METHOD - Dynamic projectiles
    protected static void dynamicProjectile(BlockModelGenerators blockModels,
                                            Block block) {

        // Model names -> Example: dice_1, dice_2, dice_3, dice_4, dice_5, dice_6.
        String[] modelNames = {"_1", "_2", "_3", "_4", "_5", "_6"};

        // List of textures per model, in order: DOWN, EAST, NORTH, SOUTH, UP, WEST
        List<String[]> diceTextures =
            List.of(new String[]{"_1", "_6", "_2", "_3", "_5", "_4"},
                    new String[]{"_2", "_5", "_6", "_3", "_1", "_4"},
                    new String[]{"_3", "_4", "_6", "_2", "_1", "_5"},
                    new String[]{"_4", "_3", "_1", "_5", "_6", "_2"},
                    new String[]{"_5", "_2", "_1", "_4", "_6", "_3"},
                    new String[]{"_6", "_1", "_2", "_4", "_5", "_3"});

        List<MultiVariant> variants = new ArrayList<>();

        for (int i = 0; i < modelNames.length; i++) {
            String[] textures = diceTextures.get(i);

            TextureMapping projectile =
                   new TextureMapping().put(TextureSlot.DOWN, getBlockTexture(block, textures[0]))
                                       .put(TextureSlot.EAST, getBlockTexture(block, textures[1]))
                                       .put(TextureSlot.NORTH, getBlockTexture(block, textures[2]))
                                       .put(TextureSlot.PARTICLE, getBlockTexture(block, textures[3]))
                                       .put(TextureSlot.SOUTH, getBlockTexture(block, textures[4]))
                                       .put(TextureSlot.UP, getBlockTexture(block, textures[5]))
                                       .put(TextureSlot.WEST, getBlockTexture(block, textures[0]));

            ModelTemplates.CUBE.createWithSuffix(block, "_" + (i + 1), projectile, blockModels.modelOutput);

            // assets\mccoursemod\blockstates
            // dice_1, dice_2, dice_3, dice_4, dice_5, dice_6
            variants.add(plainVariant(getBlockTexture(block, modelNames[i])));
            if (variants.size() == 6) {
                blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                                            .with(PropertyDispatch.initial(BlockStateProperties.FACING)
                                            .select(Direction.DOWN, variants.get(5))     // dice_6
                                            .select(Direction.EAST, variants.get(2))     // dice_3
                                            .select(Direction.NORTH, variants.get(1))    // dice_2
                                            .select(Direction.SOUTH, variants.get(4))    // dice_5
                                            .select(Direction.UP, variants.get(0))       // dice_1
                                            .select(Direction.WEST, variants.get(3))));  // dice_4
            }
        }
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
    protected static void booleanItemTexture(ItemModelGenerators itemModels,
                                             Item item, DataComponentType<?> data) {
        ItemModel.Unbaked offItem = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked usedItem = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_used",
                                                                                              ModelTemplates.FLAT_ITEM));
        itemModels.itemModelOutput
                  .register(item, new ClientItem(
                                  new ConditionalItemModel.Unbaked(new HasComponent(data, false), usedItem, offItem),
                                  new ClientItem.Properties(false, false)));
    }

    // CUSTOM METHOD - Bow texture
    protected static void createBowTexture(ItemModelGenerators itemModels, Item item) {
        itemModels.createFlatItemModel(item, ModelTemplates.BOW);
        itemModels.generateBow(item);
    }

    // CUSTOM METHOD - Armor Trim model
    public static void createArmorTrim(ItemModelGenerators itemModels,
                                       ResourceKey<EquipmentAsset> equipmentAsset, List<Item> item) {
        pieceArmorTrim(itemModels, item.getFirst(), equipmentAsset, TRIM_PREFIX_HELMET, false);
        pieceArmorTrim(itemModels, item.get(1), equipmentAsset, TRIM_PREFIX_CHESTPLATE, false);
        pieceArmorTrim(itemModels, item.get(2), equipmentAsset, TRIM_PREFIX_LEGGINGS, false);
        pieceArmorTrim(itemModels, item.get(3), equipmentAsset, TRIM_PREFIX_BOOTS, false);
    }

    // TRIM MATERIAL MODELS
    public static final List<ItemModelGenerators.TrimMaterialData> TRIM_MATERIAL_MODELS =
           List.of(new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.QUARTZ, TrimMaterials.QUARTZ),
                   new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.IRON, TrimMaterials.IRON),
                   new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.NETHERITE, TrimMaterials.NETHERITE),
                   new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.REDSTONE, TrimMaterials.REDSTONE),
                   new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.COPPER, TrimMaterials.COPPER),
                   new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.GOLD, TrimMaterials.GOLD),
                   new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.EMERALD, TrimMaterials.EMERALD),
                   new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.DIAMOND, TrimMaterials.DIAMOND),
                   new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.LAPIS, TrimMaterials.LAPIS),
                   new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.AMETHYST, TrimMaterials.AMETHYST),
                   new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.RESIN, TrimMaterials.RESIN),
                   new ItemModelGenerators.TrimMaterialData(ModTrimMaterials.BISMUTH_MATERIAL, ModTrimMaterials.BISMUTH),
                   new ItemModelGenerators.TrimMaterialData(ModTrimMaterials.ALEXANDRITE_MATERIAL, ModTrimMaterials.ALEXANDRITE),
                   new ItemModelGenerators.TrimMaterialData(ModTrimMaterials.PINK_MATERIAL, ModTrimMaterials.PINK));

    // ARMOR TRIM MATERIAL MODELS
    public static void pieceArmorTrim(ItemModelGenerators itemModels, Item item,
                                      ResourceKey<EquipmentAsset> equipmentAsset,
                                      ResourceLocation modelId, boolean usesSecondLayer) {
        ResourceLocation itemName = ModelLocationUtils.getModelLocation(item);
        ResourceLocation armorItem = TextureMapping.getItemTexture(item);
        ResourceLocation armorRender = TextureMapping.getItemTexture(item, "_overlay");
        List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> list = new ArrayList<>(TRIM_MATERIAL_MODELS.size());
        for (TrimMaterialData armorTrimMaterials : TRIM_MATERIAL_MODELS) {
            ResourceLocation ingredientItem = itemName.withSuffix("_" + armorTrimMaterials.assets().base().suffix() + "_trim");
            String equipmentAssetItem = armorTrimMaterials.assets().assetId(equipmentAsset).suffix();
            ResourceLocation armorTrimType = modelId.withSuffix("_" + equipmentAssetItem);
            ItemModel.Unbaked itemModelUnbaked;
            if (usesSecondLayer) {
                itemModels.generateLayeredItem(ingredientItem, armorItem, armorRender, armorTrimType);
                itemModelUnbaked = ItemModelUtils.tintedModel(ingredientItem, new Dye(-6265536));
            }
            else {
                itemModels.generateLayeredItem(ingredientItem, armorItem, armorTrimType);
                itemModelUnbaked = ItemModelUtils.plainModel(ingredientItem);
            }
            list.add(ItemModelUtils.when(armorTrimMaterials.materialKey(), itemModelUnbaked));
        }
        ItemModel.Unbaked itemModelUnbakedTwo;
        if (usesSecondLayer) { // LAYER 1
            ModelTemplates.TWO_LAYERED_ITEM.create(itemName, TextureMapping.layered(armorItem, armorRender), itemModels.modelOutput);
            itemModelUnbakedTwo = ItemModelUtils.tintedModel(itemName, new Dye(-6265536));
        }
        else { // LAYER 0
            ModelTemplates.FLAT_ITEM.create(itemName, TextureMapping.layer0(armorItem), itemModels.modelOutput);
            itemModelUnbakedTwo = ItemModelUtils.plainModel(itemName);
        }
        itemModels.itemModelOutput.accept(item, ItemModelUtils.select(new TrimMaterialProperty(), itemModelUnbakedTwo, list));
    }

    // CUSTOM METHOD - Shield model
    protected static void shieldTexture(ItemModelGenerators itemModels, Item item) {
        ItemModel.Unbaked shield =
            ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(item),
                                        new ShieldSpecialModelRenderer.Unbaked());
        ItemModel.Unbaked shieldBlocking =
            ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(item, "_blocking"),
                                        new ShieldSpecialModelRenderer.Unbaked());
        itemModels.generateBooleanDispatch(item, ItemModelUtils.isUsingItem(), shieldBlocking, shield);
    }

    // CUSTOM METHOD - Block models -> Ignore JSON files
    @Override
    protected @NotNull Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                               .stream().filter(x ->
                                                !(x.get() == ModBlocks.PEDESTAL.get()) &&
                                                !(x.get() == ModBlocks.CHAIR.get()));
    }

    // CUSTOM METHOD - Item models -> Ignore JSON files
    @Override
    protected @NotNull Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries()
                             .stream().filter(x ->
                                              x.get() != ModBlocks.PEDESTAL.asItem() &&
                                              x.get() != ModBlocks.CHAIR.asItem() &&
                                              !(x.get() == ModItems.TOMAHAWK.get()) &&
                                              !(x.get() == ModItems.ALEXANDRITE_SHIELD.get()) &&
                                              !(x.get() == ModItems.DICE_ITEM.get()));
    }

    @Override
    public @NotNull String getName() {
        return "Mccourse Mod Models";
    }
}