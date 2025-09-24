package net.karen.mccoursemod.block;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.custom.*;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.block.custom.GrowthChamberBlock;
import net.karen.mccoursemod.block.custom.MccourseModGeneratorBlock;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.util.ModTags;
import net.karen.mccoursemod.worldgen.tree.ModTreeGrowers;
import net.karen.mccoursemod.util.ModWoodTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    // Registry all custom BLOCKS
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MccourseMod.MOD_ID);

    // ** CUSTOM ores **
    // BISMUTH
    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
           (properties) -> new Block(properties.strength(4F).requiresCorrectToolForDrops()
                                                         .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> BISMUTH_ORE =
           oreBlock("bismuth_ore", 2, 4, 3F, SoundType.STONE);

    public static final DeferredBlock<Block> BISMUTH_DEEPSLATE_ORE =
           oreBlock("bismuth_deepslate_ore", 3, 6, 4F, SoundType.DEEPSLATE);

    public static final DeferredBlock<Block> BISMUTH_END_ORE =
           oreBlock("bismuth_end_ore", 5, 9, 7F, SoundType.GLASS);

    public static final DeferredBlock<Block> BISMUTH_NETHER_ORE =
           oreBlock("bismuth_nether_ore", 1, 5, 3F, SoundType.NETHERRACK);

    // ALEXANDRITE
    public static final DeferredBlock<Block> ALEXANDRITE_BLOCK = registerBlock("alexandrite_block",
           (properties) -> new Block(properties.mapColor(MapColor.METAL)
                                                         .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                                                         .requiresCorrectToolForDrops()
                                                         .strength(5.0F, 6.0F)
                                                         .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> RAW_ALEXANDRITE_BLOCK = registerBlock("raw_alexandrite_block",
           (properties) -> new Block(properties.mapColor(MapColor.METAL)
                                                        .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                                                        .requiresCorrectToolForDrops()
                                                        .strength(5.0F, 6.0F)
                                                        .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> ALEXANDRITE_ORE =
           oreBlock("alexandrite_ore", 2, 5, 5F, SoundType.STONE);

    public static final DeferredBlock<Block> DEEPSLATE_ALEXANDRITE_ORE =
           oreBlock("deepslate_alexandrite_ore", 3, 7, 5F, SoundType.DEEPSLATE);

    public static final DeferredBlock<Block> END_STONE_ALEXANDRITE_ORE =
           oreBlock("end_stone_alexandrite_ore", 5, 8, 5F, SoundType.GLASS);

    public static final DeferredBlock<Block> NETHER_ALEXANDRITE_ORE =
           oreBlock("nether_alexandrite_ore", 3, 6, 5F, SoundType.NETHERRACK);

    // PINK
    public static final DeferredBlock<Block> PINK_BLOCK = registerBlock("pink_block",
           (properties) -> new Block(properties.mapColor(MapColor.METAL)
                                                         .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                                                         .requiresCorrectToolForDrops()
                                                         .strength(5.0F, 6.0F)
                                                         .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> PINK_ORE =
           oreBlock("pink_ore", 2, 5, 5F, SoundType.STONE);

    public static final DeferredBlock<Block> DEEPSLATE_PINK_ORE =
           oreBlock("deepslate_pink_ore", 3, 7, 5F, SoundType.DEEPSLATE);

    public static final DeferredBlock<Block> END_STONE_PINK_ORE =
           oreBlock("end_stone_pink_ore", 5, 8, 5F, SoundType.GLASS);

    public static final DeferredBlock<Block> NETHER_PINK_ORE =
           oreBlock("nether_pink_ore", 3, 6, 5F, SoundType.NETHERRACK);

    // ** CUSTOM advanced block **
    public static final DeferredBlock<Block> MAGIC = registerBlock("magic",
           (properties) -> new MagicBlock(properties.strength(2F).sound(ModSounds.MAGIC_BLOCK_SOUNDS)));

    // CUSTOM Enchant block
    public static final DeferredBlock<Block> ENCHANT = registerBlock("enchant",
           (properties) -> new EnchantBlock(properties.strength(5.0F, 3600000.0F)
                                                                .requiresCorrectToolForDrops()));

    // CUSTOM Disenchant individual block
    public static final DeferredBlock<Block> DISENCHANT_INDIVIDUAL = registerBlock("disenchant_individual",
           (properties) -> new DisenchantBlock(properties.strength(5.0F, 3600000.0F)
                                                                   .requiresCorrectToolForDrops(), 1));

    // CUSTOM Disenchant grouped block
    public static final DeferredBlock<Block> DISENCHANT_GROUPED = registerBlock("disenchant_grouped",
           (properties) -> new DisenchantBlock(properties.strength(5.0F, 3600000.0F)
                                                                   .requiresCorrectToolForDrops(), 2));

    // CUSTOM Mccourse Mod Elevator block
    public static final DeferredBlock<Block> MCCOURSEMOD_ELEVATOR = registerBlock("mccoursemod_elevator",
           (properties) -> new MccourseElevatorBlock(properties.mapColor(MapColor.WOOL).sound(SoundType.WOOL)
                                                                         .strength(5F, 1200.F)
                                                                         .requiresCorrectToolForDrops()));

    // CUSTOM Mccourse Mod Generator block
    public static final DeferredBlock<Block> MCCOURSEMOD_GENERATOR = registerBlock("mccoursemod_generator",
           (properties) -> new MccourseModGeneratorBlock(properties.mapColor(MapColor.STONE)
                                                                             .instrument(NoteBlockInstrument.BASEDRUM)
                                                                             .strength(5F, 3600000.0F),
                                                                                       "ALL", ModTags.Blocks.ALL_ORES));

    // CUSTOM Crafting Plus custom Crafting Table
    public static final DeferredBlock<Block> CRAFTING_PLUS = registerBlock("crafting_plus",
           (properties) ->
           new CraftingPlusBlock(BlockBehaviour.Properties
                                               .ofFullCopy(Blocks.CRAFTING_TABLE)
                                               .setId(ResourceKey.create(Registries.BLOCK,
                                                      ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                                            "crafting_plus")))));

    // ** CUSTOM Block Family -> (Button, Door, Fence, Fence Gate, Pressure Plate, Slab, Stairs, Trapdoor and Wall) **
    // BISMUTH
    public static final DeferredBlock<StairBlock> BISMUTH_STAIRS =
           registerBlock("bismuth_stairs", properties ->
                         new StairBlock(ModBlocks.BISMUTH_BLOCK.get().defaultBlockState(),
                                        properties.strength(2F).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<SlabBlock> BISMUTH_SLAB = slabBlock("bismuth_slab");

    public static final DeferredBlock<PressurePlateBlock> BISMUTH_PRESSURE_PLATE =
           pressurePlateBlock("bismuth_pressure_plate");

    public static final DeferredBlock<ButtonBlock> BISMUTH_BUTTON = buttonBlock("bismuth_button", 20);

    public static final DeferredBlock<FenceBlock> BISMUTH_FENCE = fenceBlock("bismuth_fence");

    public static final DeferredBlock<FenceGateBlock> BISMUTH_FENCE_GATE = fenceGateBlock("bismuth_fence_gate");

    public static final DeferredBlock<WallBlock> BISMUTH_WALL = wallBlock("bismuth_wall");

    public static final DeferredBlock<DoorBlock> BISMUTH_DOOR = doorBlock("bismuth_door");

    public static final DeferredBlock<TrapDoorBlock> BISMUTH_TRAPDOOR = trapDoorBlock("bismuth_trapdoor");

    // ALEXANDRITE
    public static final DeferredBlock<StairBlock> ALEXANDRITE_STAIRS =
           registerBlock("alexandrite_stairs", properties ->
                         new StairBlock(ModBlocks.ALEXANDRITE_BLOCK.get().defaultBlockState(),
                                        properties.strength(2F).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<SlabBlock> ALEXANDRITE_SLABS = slabBlock("alexandrite_slabs");

    public static final DeferredBlock<PressurePlateBlock> ALEXANDRITE_PREASSURE_PLATE =
           pressurePlateBlock("alexandrite_pressure_plate");

    public static final DeferredBlock<ButtonBlock> ALEXANDRITE_BUTTON =
           buttonBlock("alexandrite_button", 10);

    public static final DeferredBlock<FenceBlock> ALEXANDRITE_FENCE = fenceBlock("alexandrite_fence");

    public static final DeferredBlock<FenceGateBlock> ALEXANDRITE_FENCE_GATE =
           fenceGateBlock("alexandrite_fence_gate");

    public static final DeferredBlock<WallBlock> ALEXANDRITE_WALL = wallBlock("alexandrite_wall");

    public static final DeferredBlock<DoorBlock> ALEXANDRITE_DOOR = doorBlock("alexandrite_door");

    public static final DeferredBlock<TrapDoorBlock> ALEXANDRITE_TRAPDOOR =
           trapDoorBlock("alexandrite_trapdoor");

    // ** CUSTOM blockstate block **
    public static final DeferredBlock<Block> BISMUTH_LAMP =
           lampBlock("bismuth_lamp", MapColor.COLOR_ORANGE, ModSounds.MAGIC_BLOCK_SOUNDS);

    public static final DeferredBlock<Block> ALEXANDRITE_LAMP =
           lampBlock("alexandrite_lamp", MapColor.COLOR_BLUE, ModSounds.ALEXANDRITE_LAMP_SOUNDS);

    // ** CUSTOM crop block **
    // RADISH
    public static final DeferredBlock<Block> RADISH_CROP = BLOCKS.registerBlock("radish_crop",
           (properties) -> new RadishCropBlock(properties.mapColor(MapColor.PLANT).noCollission()
                                                                   .randomTicks().instabreak().sound(SoundType.CROP)
                                                                   .pushReaction(PushReaction.DESTROY)));
    // KOHLRABI
    public static final DeferredBlock<Block> KOHLRABI_CROP = BLOCKS.registerBlock("kohlrabi_crop",
           (properties) -> new KohlrabiCropBlock(properties.mapColor(MapColor.PLANT).noCollission()
                                                                     .randomTicks().instabreak().sound(SoundType.CROP)
                                                                     .pushReaction(PushReaction.DESTROY)));

    // ** CUSTOM Crop block with two height **
    // CATTAIL
    public static final DeferredBlock<Block> CATTAIL_CROP = BLOCKS.registerBlock("cattail_crop",
           (properties) -> new CattailCropBlock(properties.mapColor(MapColor.PLANT).noCollission()
                                                                    .randomTicks().instabreak().sound(SoundType.CROP)
                                                                    .pushReaction(PushReaction.DESTROY)));

    // ** CUSTOM bush crop block **
    public static final DeferredBlock<Block> GOJI_BERRY_BUSH = BLOCKS.registerBlock("goji_berry_bush",
           (properties) -> new GojiBerryBushBlock(properties.mapColor(MapColor.PLANT).noCollission()
                                                                      .randomTicks().instabreak().sound(SoundType.CROP)
                                                                      .pushReaction(PushReaction.DESTROY)));

    // ** CUSTOM log **
    // BLOODWOOD
    public static final DeferredBlock<Block> BLOODWOOD_LOG =
           logWoodBlocks("bloodwood_log", SoundType.CHERRY_WOOD);

    // CUSTOM wood
    public static final DeferredBlock<Block> BLOODWOOD_WOOD =
           logWoodBlocks("bloodwood_wood", SoundType.CHERRY_WOOD);

    // CUSTOM stripped log
    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_LOG =
           logWoodBlocks("stripped_bloodwood_log", SoundType.CHERRY_WOOD);

    // CUSTOM stripped wood
    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_WOOD =
           logWoodBlocks("stripped_bloodwood_wood", SoundType.CHERRY_WOOD);

    // CUSTOM planks
    public static final DeferredBlock<Block> BLOODWOOD_PLANKS =
           plankBlock("bloodwood_planks");

    // CUSTOM leaves
    public static final DeferredBlock<Block> BLOODWOOD_LEAVES =
           leaveBlock("bloodwood_leaves");

    // CUSTOM sapling
    public static final DeferredBlock<Block> BLOODWOOD_SAPLING =
           saplingBlock("bloodwood_sapling", ModTreeGrowers.BLOODWOOD, () -> Blocks.NETHERRACK);

    // WALNUT
    public static final DeferredBlock<Block> WALNUT_LOG =
           logWoodBlocks("walnut_log", SoundType.WOOD);

    public static final DeferredBlock<Block> WALNUT_WOOD =
           logWoodBlocks("walnut_wood", SoundType.WOOD);

    public static final DeferredBlock<Block> STRIPPED_WALNUT_LOG =
           logWoodBlocks("stripped_walnut_log", SoundType.WOOD);

    public static final DeferredBlock<Block> STRIPPED_WALNUT_WOOD =
           logWoodBlocks("stripped_walnut_wood", SoundType.WOOD);

    public static final DeferredBlock<Block> WALNUT_PLANKS =
           plankBlock("walnut_planks");

    public static final DeferredBlock<Block> WALNUT_LEAVES =
           leaveBlock("walnut_leaves");

    public static final DeferredBlock<Block> WALNUT_SAPLING =
           saplingBlock("walnut_sapling", ModTreeGrowers.WALNUT, () -> Blocks.END_STONE);

    // ** CUSTOM sign and hanging sign **
    public static final DeferredBlock<Block> WALNUT_SIGN =
           BLOCKS.register("walnut_sign",
           () -> new ModStandingSignBlock(ModWoodTypes.WALNUT,
                                          BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)
                                                                   .setId(ResourceKey.create(Registries.BLOCK,
                                                                          ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                          "walnut_sign")))));

    public static final DeferredBlock<Block> WALNUT_WALL_SIGN =
           BLOCKS.register("walnut_wall_sign",
           () -> new ModWallSignBlock(ModWoodTypes.WALNUT,
                                      BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)
                                                               .setId(ResourceKey.create(Registries.BLOCK,
                                                                      ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                      "walnut_wall_sign")))));

    public static final DeferredBlock<Block> WALNUT_HANGING_SIGN =
           BLOCKS.register("walnut_hanging_sign",
           () -> new ModHangingSignBlock(ModWoodTypes.WALNUT,
                                         BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)
                                                                  .setId(ResourceKey.create(Registries.BLOCK,
                                                                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                         "walnut_hanging_sign")))));

    public static final DeferredBlock<Block> WALNUT_WALL_HANGING_SIGN =
           BLOCKS.register("walnut_wall_hanging_sign",
           () -> new ModWallHangingSignBlock(ModWoodTypes.WALNUT,
                                             BlockBehaviour.Properties
                                                           .ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)
                                                           .setId(ResourceKey.create(Registries.BLOCK,
                                                                  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                  "walnut_wall_hanging_sign")))));

    // ** CUSTOM sittable block model **
    public static final DeferredBlock<Block> CHAIR = registerBlock("chair",
           (properties) -> new ChairBlock(properties.noOcclusion()));

    // ** CUSTOM block entity **
    public static final DeferredBlock<Block> PEDESTAL = registerBlock("pedestal",
           (properties) -> new PedestalBlock(properties.noOcclusion()));

    // ** CUSTOM crafting block entity **
    public static final DeferredBlock<Block> GROWTH_CHAMBER =
           registerBlock("growth_chamber", GrowthChamberBlock::new);

    public static final DeferredBlock<Block> KAUPEN_FURNACE_BLOCK =
           registerBlock("kaupen_furnace", KaupenFurnaceBlock::new);

    // ** CUSTOM glass block **
    public static final DeferredBlock<Block> FORCED_STAINED_GLASS =
           registerBlock("forced_stained_glass",
           (properties) -> new StainedGlassBlock(DyeColor.GREEN,
                                                           properties.strength(3.0F, 10.0F)
                                                                     .mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.HAT)
                                                                     .sound(SoundType.GLASS).noOcclusion()
                                                                     .isValidSpawn(Blocks::never)
                                                                     .isRedstoneConductor((s, g, p)
                                                                                           -> false)
                                                                     .isSuffocating((s, g, p)
                                                                                      -> false)
                                                                     .isViewBlocking((s, g, p)
                                                                                      -> false)));

    public static final DeferredBlock<Block> FORCED_STAINED_GLASS_PANE =
           registerBlock("forced_stained_glass_pane",
           (properties) -> new IronBarsBlock(properties.strength(3.0F, 10.0F)
                                                                 .instrument(NoteBlockInstrument.HAT)
                                                                 .sound(SoundType.GLASS).noOcclusion()));

    // SOUND block
    public static final DeferredBlock<Block> SOUND = registerBlock("sound",
           (properties) -> new SoundBlock(properties.strength(3.0F, 20.F)
                                                              .requiresCorrectToolForDrops()
                                                              .sound(SoundType.AMETHYST)));

    // END PEARL blocks
    public static final DeferredBlock<Block> ENDER_PEARL_BLOCK =
           enderPearlBlock("ender_pearl_block", MapColor.EMERALD);

    public static final DeferredBlock<Block> GREEN_ENDER_PEARL_BLOCK =
           enderPearlBlock("green_ender_pearl_block", MapColor.COLOR_GREEN);

    public static final DeferredBlock<Block> LIME_GREEN_ENDER_PEARL_BLOCK =
           enderPearlBlock("lime_green_ender_pearl_block", MapColor.COLOR_LIGHT_GREEN);

    public static final DeferredBlock<Block> BLACK_ENDER_PEARL_BLOCK =
           enderPearlBlock("black_ender_pearl_block", MapColor.COLOR_BLACK);

    public static final DeferredBlock<Block> MAGENTA_ENDER_PEARL_BLOCK =
           enderPearlBlock("magenta_ender_pearl_block", MapColor.COLOR_MAGENTA);

    public static final DeferredBlock<Block> PURPLE_ENDER_PEARL_BLOCK =
           enderPearlBlock("purple_ender_pearl_block", MapColor.COLOR_PURPLE);

    public static final DeferredBlock<Block> ORANGE_ENDER_PEARL_BLOCK =
           enderPearlBlock("orange_ender_pearl_block", MapColor.COLOR_ORANGE);

    public static final DeferredBlock<Block> PINK_ENDER_PEARL_BLOCK =
           enderPearlBlock("pink_ender_pearl_block", MapColor.COLOR_PINK);

    public static final DeferredBlock<Block> CYAN_ENDER_PEARL_BLOCK =
           enderPearlBlock("cyan_ender_pearl_block", MapColor.COLOR_CYAN);

    public static final DeferredBlock<Block> BROWN_ENDER_PEARL_BLOCK =
           enderPearlBlock("brown_ender_pearl_block", MapColor.COLOR_BROWN);

    public static final DeferredBlock<Block> GRAY_ENDER_PEARL_BLOCK =
           enderPearlBlock("gray_ender_pearl_block", MapColor.COLOR_GRAY);

    public static final DeferredBlock<Block> RED_ENDER_PEARL_BLOCK =
           enderPearlBlock("red_ender_pearl_block", MapColor.COLOR_RED);

    public static final DeferredBlock<Block> YELLOW_ENDER_PEARL_BLOCK =
           enderPearlBlock("yellow_ender_pearl_block", MapColor.COLOR_YELLOW);

    public static final DeferredBlock<Block> BLUE_ENDER_PEARL_BLOCK =
           enderPearlBlock("blue_ender_pearl_block", MapColor.COLOR_BLUE);

    public static final DeferredBlock<Block> WHITE_ENDER_PEARL_BLOCK =
           enderPearlBlock("white_ender_pearl_block", MapColor.COLOR_LIGHT_GRAY);

    // ** CUSTOM mob blocks **
    public static final DeferredBlock<Block> NETHER_STAR_BLOCK =
           mobBlock("nether_star_block", MapColor.METAL, NoteBlockInstrument.BELL, SoundType.METAL);

    public static final DeferredBlock<Block> GUNPOWDER_BLOCK =
           mobBlock("gunpowder_block", MapColor.METAL, NoteBlockInstrument.CREEPER, SoundType.METAL);

    public static final DeferredBlock<Block> ROTTEN_FLESH_BLOCK =
           mobBlock("rotten_flesh_block", MapColor.METAL, NoteBlockInstrument.ZOMBIE, SoundType.METAL);

    public static final DeferredBlock<Block> BLAZE_ROD_BLOCK =
           mobBlock("blaze_rod_block", MapColor.METAL, NoteBlockInstrument.BIT, SoundType.METAL);

    public static final DeferredBlock<Block> PHANTOM_MEMBRANE_BLOCK =
           mobBlock("phantom_membrane_block", MapColor.METAL, NoteBlockInstrument.BIT, SoundType.METAL);

    public static final DeferredBlock<Block> STRING_BLOCK =
           mobBlock("string_block", MapColor.WOOL, NoteBlockInstrument.DIDGERIDOO, SoundType.WOOL);

    public static final DeferredBlock<Block> SPIDER_EYE_BLOCK =
           mobBlock("spider_eye_block", MapColor.COLOR_RED, NoteBlockInstrument.GUITAR, SoundType.SLIME_BLOCK);

    public static final DeferredBlock<Block> FERMENTED_SPIDER_EYE_BLOCK =
           mobBlock("fermented_spider_eye_block", MapColor.COLOR_RED, NoteBlockInstrument.GUITAR, SoundType.SLIME_BLOCK);

    public static final DeferredBlock<Block> SUGAR_BLOCK =
           mobBlock("sugar_block", MapColor.COLOR_LIGHT_GRAY, NoteBlockInstrument.BELL, SoundType.SAND);

    public static final DeferredBlock<Block> SUGAR_CANE_BLOCK =
           mobBlock("sugar_cane_block", MapColor.METAL, NoteBlockInstrument.BELL, SoundType.SAND);

    // ** CUSTOM oxidizable block **
    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block",
           (properties) ->
           new DegradableRubyBlock(GemDegradable.GemDegradationLevel.UNAFFECTED,
                                   BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                                                            .setId(ResourceKey.create(Registries.BLOCK,
                                                                   ResourceLocation.fromNamespaceAndPath(
                                                                   MccourseMod.MOD_ID, "ruby_block")))));

    public static final DeferredBlock<Block> RUBY_BLOCK_1 = registerBlock("ruby_block_1",
           (properties) ->
           new DegradableRubyBlock(GemDegradable.GemDegradationLevel.EXPOSED,
                                   BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                                                            .setId(ResourceKey.create(Registries.BLOCK,
                                                                   ResourceLocation.fromNamespaceAndPath(
                                                                   MccourseMod.MOD_ID, "ruby_block_1")))));

    public static final DeferredBlock<Block> RUBY_BLOCK_2 = registerBlock("ruby_block_2",
           (properties) ->
           new DegradableRubyBlock(GemDegradable.GemDegradationLevel.WEATHERED,
                                   BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                                                            .setId(ResourceKey.create(Registries.BLOCK,
                                                                   ResourceLocation.fromNamespaceAndPath(
                                                                   MccourseMod.MOD_ID, "ruby_block_2")))));

    public static final DeferredBlock<Block> RUBY_BLOCK_3 = registerBlock("ruby_block_3",
           (properties) ->
           new DegradableRubyBlock(GemDegradable.GemDegradationLevel.DEGRADED,
                                   BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                                                 .setId(ResourceKey.create(Registries.BLOCK,
                                                        ResourceLocation.fromNamespaceAndPath(
                                                        MccourseMod.MOD_ID, "ruby_block_3")))));

    public static final DeferredBlock<Block> WAXED_RUBY_BLOCK = registerBlock("waxed_ruby_block",
           (properties) ->
           new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                                              .setId(ResourceKey.create(Registries.BLOCK,
                                                     ResourceLocation.fromNamespaceAndPath(
                                                     MccourseMod.MOD_ID, "waxed_ruby_block")))));

    public static final DeferredBlock<Block> WAXED_RUBY_BLOCK_1 = registerBlock("waxed_ruby_block_1",
           (properties) ->
           new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                                              .setId(ResourceKey.create(Registries.BLOCK,
                                                     ResourceLocation.fromNamespaceAndPath(
                                                     MccourseMod.MOD_ID, "waxed_ruby_block_1")))));

    public static final DeferredBlock<Block> WAXED_RUBY_BLOCK_2 = registerBlock("waxed_ruby_block_2",
           (properties) ->
           new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                                              .setId(ResourceKey.create(Registries.BLOCK,
                                                     ResourceLocation.fromNamespaceAndPath(
                                                     MccourseMod.MOD_ID, "waxed_ruby_block_2")))));

    public static final DeferredBlock<Block> WAXED_RUBY_BLOCK_3 = registerBlock("waxed_ruby_block_3",
           (properties) ->
           new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                                              .setId(ResourceKey.create(Registries.BLOCK,
                                                                        ResourceLocation.fromNamespaceAndPath(
                                                                        MccourseMod.MOD_ID, "waxed_ruby_block_3")))));

    // ** CUSTOM flowers and pot flowers **
    // SNAPDRAGON
    public static final DeferredBlock<Block> SNAPDRAGON = registerBlock("snapdragon",
           (properties) ->
           new FlowerBlock(MobEffects.BLINDNESS, 6.0F,
                           BlockBehaviour.Properties
                                         .ofFullCopy(Blocks.ALLIUM)
                                         .setId(ResourceKey.create(Registries.BLOCK,
                                                ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                                      "snapdragon")))));

    public static final DeferredBlock<Block> POTTED_SNAPDRAGON = registerBlock("potted_snapdragon",
           (properties) ->
           new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SNAPDRAGON,
                              BlockBehaviour.Properties
                                            .ofFullCopy(Blocks.POTTED_ALLIUM)
                                            .setId(ResourceKey.create(Registries.BLOCK,
                                                   ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                                         "potted_snapdragon")))));

    // ** CUSTOM colored block **
    public static final DeferredBlock<Block> COLORED_LEAVES = registerBlock("colored_leaves",
           (properties) ->
           new UntintedParticleLeavesBlock(0.01F, ParticleTypes.PALE_OAK_LEAVES,
                                           BlockBehaviour.Properties
                                                         .ofFullCopy(Blocks.OAK_LEAVES)
                                                         .setId(ResourceKey.create(Registries.BLOCK,
                                                                ResourceLocation.fromNamespaceAndPath(
                                                                MccourseMod.MOD_ID, "colored_leaves")))) {
               @Override
               public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level,
                                          @NotNull BlockPos pos, @NotNull Direction direction) {
                   return true;
               }

               @Override
               public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level,
                                          @NotNull BlockPos pos, @NotNull Direction direction) {
                   return 60;
               }

               @Override
               public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level,
                                             @NotNull BlockPos pos, @NotNull Direction direction) {
                   return 30;
               }
           });

    // ** CUSTOM portal block **
    public static final DeferredBlock<Block> KAUPEN_PORTAL = registerBlock("kaupen_portal",
           (properties) ->
           new KaupenPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL)
                                                          .liquid()
                                                          .forceSolidOn()
                                                          .noLootTable()
                                                          .noOcclusion()
                                                          .noCollission()
                                                          .setId(ResourceKey.create(Registries.BLOCK,
                                                                 ResourceLocation.fromNamespaceAndPath(
                                                                 MccourseMod.MOD_ID, "kaupen_portal")))));

    // ** CUSTOM block projectile **
    public static final DeferredBlock<Block> DICE =
           BLOCKS.register("dice", (properties) ->
                           new DiceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                                                                  .noLootTable()
                                                                  .setId(ResourceKey.create(Registries.BLOCK,
                                                                         ResourceLocation.fromNamespaceAndPath(
                                                                         MccourseMod.MOD_ID, "dice")))));

    // ** CUSTOM METHOD - SLAB blocks **
    private static DeferredBlock<SlabBlock> slabBlock(String name) {
        return registerBlock(name, properties ->
                             new SlabBlock(properties.strength(2F).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    }

    // ** CUSTOM METHOD - PRESSURE PLATE blocks **
    private static DeferredBlock<PressurePlateBlock> pressurePlateBlock(String name) {
        return registerBlock(name, properties ->
                             new PressurePlateBlock(BlockSetType.IRON, properties.strength(2F).requiresCorrectToolForDrops()
                                                                                              .sound(SoundType.METAL)));
    }

    // ** CUSTOM METHOD - BUTTON blocks **
    private static DeferredBlock<ButtonBlock> buttonBlock(String name, int tick) {
        return registerBlock(name, properties ->
                             new ButtonBlock(BlockSetType.IRON, tick, properties.strength(2F).requiresCorrectToolForDrops()
                                                                                .noCollission().sound(SoundType.METAL)));
    }

    // ** CUSTOM METHOD - FENCE blocks **
    private static DeferredBlock<FenceBlock> fenceBlock(String name) {
        return registerBlock(name, properties -> new FenceBlock(properties.strength(2F).requiresCorrectToolForDrops()));
    }

    // ** CUSTOM METHOD - FENCE GATE blocks **
    private static DeferredBlock<FenceGateBlock> fenceGateBlock(String name) {
        return registerBlock(name, properties ->
                             new FenceGateBlock(WoodType.ACACIA, properties.strength(2F).requiresCorrectToolForDrops()));
    }

    // ** CUSTOM METHOD - WALL blocks **
    private static DeferredBlock<WallBlock> wallBlock(String name) {
        return registerBlock(name, properties -> new WallBlock(properties.strength(2F).requiresCorrectToolForDrops()));
    }

    // ** CUSTOM METHOD - DOOR blocks **
    private static DeferredBlock<DoorBlock> doorBlock(String name) {
        return registerBlock(name, properties ->
                             new DoorBlock(BlockSetType.IRON, properties.strength(2F).requiresCorrectToolForDrops()
                                                                                     .noOcclusion()));
    }

    // ** CUSTOM METHOD - TRAPDOOR blocks **
    private static DeferredBlock<TrapDoorBlock> trapDoorBlock(String name) {
        return registerBlock(name, properties ->
                             new TrapDoorBlock(BlockSetType.IRON, properties.strength(2F).requiresCorrectToolForDrops()
                                                                                         .noOcclusion()));
    }

    // ** CUSTOM METHOD - LAMP blocks **
    private static DeferredBlock<Block> lampBlock(String name,
                                                  MapColor mapColor, SoundType soundType) {
        return registerBlock(name, properties ->
                             new BismuthLampBlock(properties.strength(2F).mapColor(mapColor).sound(soundType)
                                                                         .requiresCorrectToolForDrops()
                                                                         .lightLevel(state ->
                                                                                     state.getValue(BismuthLampBlock.CLICKED)
                                                                                     ? 15 : 0)));
    }

    // ** CUSTOM METHOD - LEAVE blocks **
    private static DeferredBlock<Block> leaveBlock(String name) {
        return registerBlock(name, properties ->
                             new UntintedParticleLeavesBlock(0.01F,
                                                             ParticleTypes.CHERRY_LEAVES,
                                                             properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks()
                                                                       .sound(SoundType.CHERRY_LEAVES).noOcclusion()
                                                                       .isValidSpawn(Blocks::ocelotOrParrot)
                                                                       .ignitedByLava().pushReaction(PushReaction.DESTROY)) {
                             @Override
                             public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level,
                                                        @NotNull BlockPos pos, @NotNull Direction direction) {
                                 return true;
                             }

                             @Override
                             public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level,
                                                        @NotNull BlockPos pos, @NotNull Direction direction) {
                                 return 60;
                             }

                             @Override
                             public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level,
                                                           @NotNull BlockPos pos, @NotNull Direction direction) {
                                 return 30;
                             }
        });
    }

    // ** CUSTOM METHOD - LOG, WOOD, STRIPPED LOG, STRIPPED WOOD blocks **
    private static DeferredBlock<Block> logWoodBlocks(String name, SoundType soundType) {
        return  registerBlock(name, properties ->
                              new ModFlammableRotatedPillarBlock(properties.instrument(NoteBlockInstrument.BASS).strength(2.0F)
                                                                           .sound(soundType).ignitedByLava()));
    }

    // ** CUSTOM METHOD - PLANK blocks **
    private static DeferredBlock<Block> plankBlock(String name) {
        return registerBlock(name, properties -> new Block(properties) {
                             @Override
                             public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level,
                                                        @NotNull BlockPos pos, @NotNull Direction direction) {
                                 return true;
                             }

                             @Override
                             public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level,
                                                        @NotNull BlockPos pos, @NotNull Direction direction) {
                                 return 20;
                             }

                             @Override
                             public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level,
                                                           @NotNull BlockPos pos, @NotNull Direction direction) {
                                 return 5;
                             }
        });
    }

    // ** CUSTOM METHOD - SAPLING blocks **
    private static DeferredBlock<Block> saplingBlock(String name,
                                                     TreeGrower tree, Supplier<Block> block) {
        return registerBlock(name, properties ->
                             new ModSaplingBlock(tree, properties.mapColor(MapColor.PLANT)
                                                                 .noCollission().randomTicks()
                                                                 .instabreak().sound(SoundType.GRASS)
                                                                 .pushReaction(PushReaction.DESTROY),
                                                 block));
    }

    // ** CUSTOM METHOD - ORES blocks **
    private static DeferredBlock<Block> oreBlock(String name, int min, int max,
                                                 float strength, SoundType soundType) {
        return registerBlock(name, properties ->
                             new DropExperienceBlock(UniformInt.of(min, max), properties.strength(strength)
                                                                                        .requiresCorrectToolForDrops()
                                                                                        .sound(soundType)));
    }

    // ** CUSTOM METHOD - ENDER PEARL blocks **
    private static DeferredBlock<Block> enderPearlBlock(String name, MapColor color) {
        return registerBlock(name, properties ->
                             new Block(properties.mapColor(color)
                                                 .instrument(NoteBlockInstrument.BELL)
                                                 .requiresCorrectToolForDrops()
                                                 .strength(5.0F, 6.0F)
                                                 .sound(SoundType.METAL)
                                                 .lightLevel(state -> 50)));
    }

    // ** CUSTOM METHOD - MOB blocks **
    private static DeferredBlock<Block> mobBlock(String name, MapColor color,
                                                   NoteBlockInstrument noteBlock, SoundType soundType) {
       return registerBlock(name, properties ->
                            new Block(properties.mapColor(color)
                                                .instrument(noteBlock)
                                                .requiresCorrectToolForDrops()
                                                .strength(5.0F, 6.0F)
                                                .sound(soundType)));
    }

    // ** CUSTOM METHOD - Registry all custom BLOCKS **
    private static <T extends Block> DeferredBlock<T> registerBlock(String name,
                                                                    Function<BlockBehaviour.Properties, T> block) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // ** CUSTOM METHOD - Registry all custom BLOCK ITEMS **
    private static <T extends Block> void registerBlockItem(String name,
                                                            DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties ->
                                    new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    // ** CUSTOM METHOD - Registry all custom BLOCKS on EVENT **
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}