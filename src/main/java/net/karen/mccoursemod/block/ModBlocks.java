package net.karen.mccoursemod.block;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.custom.*;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.block.custom.GrowthChamberBlock;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.worldgen.tree.ModTreeGrowers;
import net.karen.mccoursemod.worldgen.tree.ModWoodTypes;
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

public class ModBlocks {
    // Registry all custom BLOCKS
    public static final DeferredRegister.Blocks BLOCKS =
           DeferredRegister.createBlocks(MccourseMod.MOD_ID);

    // ** CUSTOM ores **
    // BISMUTH
    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
           (properties) -> new Block(properties.strength(4F).requiresCorrectToolForDrops()
                                                         .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock("bismuth_ore",
           (properties) -> new DropExperienceBlock(UniformInt.of(2, 4),
                                                             properties.strength(3F).requiresCorrectToolForDrops()
                                                                       .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BISMUTH_DEEPSLATE_ORE = registerBlock("bismuth_deepslate_ore",
           (properties) -> new DropExperienceBlock(UniformInt.of(3, 6),
                                                             properties.strength(4F).requiresCorrectToolForDrops()
                                                                       .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> BISMUTH_END_ORE = registerBlock("bismuth_end_ore",
           (properties) -> new DropExperienceBlock(UniformInt.of(5, 9),
                                                             properties.strength(7F).requiresCorrectToolForDrops()
                                                                       .instrument(NoteBlockInstrument.BASEDRUM)));

    public static final DeferredBlock<Block> BISMUTH_NETHER_ORE = registerBlock("bismuth_nether_ore",
           (properties) -> new DropExperienceBlock(UniformInt.of(1, 5),
                                                             properties.strength(3F).requiresCorrectToolForDrops()
                                                                       .sound(SoundType.NETHERRACK)));

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

    public static final DeferredBlock<Block> ALEXANDRITE_ORE = registerBlock("alexandrite_ore",
           (properties) -> new DropExperienceBlock(UniformInt.of(2, 5),
                                                             properties.strength(5F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> DEEPSLATE_ALEXANDRITE_ORE =
           registerBlock("deepslate_alexandrite_ore",
           (properties) -> new DropExperienceBlock(UniformInt.of(3, 7),
                                                             properties.strength(5F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> END_STONE_ALEXANDRITE_ORE =
           registerBlock("end_stone_alexandrite_ore",
           (properties) -> new DropExperienceBlock(UniformInt.of(5, 8),
                                                            properties.strength(5F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NETHER_ALEXANDRITE_ORE = registerBlock("nether_alexandrite_ore",
           (properties) -> new DropExperienceBlock(UniformInt.of(3, 6),
                                                             properties.strength(5F).requiresCorrectToolForDrops()));

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

    // ** CUSTOM Block Family -> (Button, Door, Fence, Fence Gate, Pressure Plate, Slab, Stairs, Trapdoor and Wall) **
    // BISMUTH
    public static final DeferredBlock<StairBlock> BISMUTH_STAIRS = registerBlock("bismuth_stairs",
           (properties) -> new StairBlock(ModBlocks.BISMUTH_BLOCK.get().defaultBlockState(),
                                                    properties.strength(2F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<SlabBlock> BISMUTH_SLAB = registerBlock("bismuth_slab",
           (properties) -> new SlabBlock(properties.strength(2F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<PressurePlateBlock> BISMUTH_PRESSURE_PLATE =
           registerBlock("bismuth_pressure_plate",
           (properties) -> new PressurePlateBlock(BlockSetType.IRON,
                                                            properties.strength(2F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<ButtonBlock> BISMUTH_BUTTON = registerBlock("bismuth_button",
           (properties) -> new ButtonBlock(BlockSetType.IRON, 20,
                                                    properties.strength(2F).requiresCorrectToolForDrops()
                                                              .noCollission()));

    public static final DeferredBlock<FenceBlock> BISMUTH_FENCE = registerBlock("bismuth_fence",
           (properties) -> new FenceBlock(properties.strength(2F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<FenceGateBlock> BISMUTH_FENCE_GATE = registerBlock("bismuth_fence_gate",
           (properties) -> new FenceGateBlock(WoodType.ACACIA,
                                                        properties.strength(2F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> BISMUTH_WALL = registerBlock("bismuth_wall",
           (properties) -> new WallBlock(properties.strength(2F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<DoorBlock> BISMUTH_DOOR = registerBlock("bismuth_door",
           (properties) -> new DoorBlock(BlockSetType.IRON,
                                                  properties.strength(2F).requiresCorrectToolForDrops()
                                                                         .noOcclusion()));

    public static final DeferredBlock<TrapDoorBlock> BISMUTH_TRAPDOOR = registerBlock("bismuth_trapdoor",
           (properties) -> new TrapDoorBlock(BlockSetType.IRON,
                                                       properties.strength(2F).requiresCorrectToolForDrops()
                                                                              .noOcclusion()));

    // ALEXANDRITE
    public static final DeferredBlock<Block> ALEXANDRITE_STAIRS = registerBlock("alexandrite_stairs",
           (properties) -> new StairBlock(ModBlocks.ALEXANDRITE_BLOCK.get().defaultBlockState(),
                                                    properties.strength(2F).requiresCorrectToolForDrops()
                                                                           .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> ALEXANDRITE_SLABS = registerBlock("alexandrite_slabs",
           (properties) -> new SlabBlock(properties.strength(2F).requiresCorrectToolForDrops()
                                                                          .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> ALEXANDRITE_PREASSURE_PLATE =
           registerBlock("alexandrite_pressure_plate",
           (properties) -> new PressurePlateBlock(BlockSetType.IRON,
                                                            properties.strength(2F).requiresCorrectToolForDrops()
                                                                                   .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> ALEXANDRITE_BUTTON = registerBlock("alexandrite_button",
           (properties) -> new ButtonBlock(BlockSetType.IRON, 10,
                                                     properties.strength(2F).requiresCorrectToolForDrops()
                                                                            .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> ALEXANDRITE_FENCE = registerBlock("alexandrite_fence",
           (properties) -> new FenceBlock(properties.strength(2F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ALEXANDRITE_FENCE_GATE = registerBlock("alexandrite_fence_gate",
           (properties) -> new FenceGateBlock(WoodType.ACACIA,
                                                        properties.strength(2F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ALEXANDRITE_WALL = registerBlock("alexandrite_wall",
           (properties) -> new WallBlock(properties.strength(2F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ALEXANDRITE_DOOR = registerBlock("alexandrite_door",
           (properties) -> new DoorBlock(BlockSetType.IRON,
                                                   properties.strength(2F).requiresCorrectToolForDrops().noOcclusion()));

    public static final DeferredBlock<Block> ALEXANDRITE_TRAPDOOR = registerBlock("alexandrite_trapdoor",
           (properties) -> new TrapDoorBlock(BlockSetType.IRON,
                                                       properties.strength(2F).requiresCorrectToolForDrops().noOcclusion()));

    // ** CUSTOM blockstate block **
    public static final DeferredBlock<Block> BISMUTH_LAMP = registerBlock("bismuth_lamp",
           (properties) -> new BismuthLampBlock(properties.strength(2F).requiresCorrectToolForDrops()
                                                                    .lightLevel(state ->
                                                                                state.getValue(BismuthLampBlock.CLICKED)
                                                                                               ? 15 : 0)));

    public static final DeferredBlock<Block> ALEXANDRITE_LAMP = registerBlock("alexandrite_lamp",
           (properties) -> new BismuthLampBlock(properties.mapColor(MapColor.COLOR_BLUE)
                                                                    .sound(ModSounds.ALEXANDRITE_LAMP_SOUNDS)
                                                                    .strength(1f)
                                                                    .lightLevel(state ->
                                                                                state.getValue(BismuthLampBlock.CLICKED)
                                                                                               ? 15 : 0)));

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
    public static final DeferredBlock<Block> BLOODWOOD_LOG = registerBlock("bloodwood_log",
           (properties) -> new ModFlammableRotatedPillarBlock(properties.instrument(NoteBlockInstrument.BASS)
                                                                                  .strength(2.0F)
                                                                                  .sound(SoundType.CHERRY_WOOD)
                                                                                  .ignitedByLava()));

    // CUSTOM wood
    public static final DeferredBlock<Block> BLOODWOOD_WOOD = registerBlock("bloodwood_wood",
           (properties) -> new ModFlammableRotatedPillarBlock(properties.instrument(NoteBlockInstrument.BASS)
                                                                                  .strength(2.0F)
                                                                                  .sound(SoundType.CHERRY_WOOD)
                                                                                  .ignitedByLava()));

    // CUSTOM stripped log
    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_LOG = registerBlock("stripped_bloodwood_log",
           (properties) -> new ModFlammableRotatedPillarBlock(properties.instrument(NoteBlockInstrument.BASS)
                                                                                  .strength(2.0F)
                                                                                  .sound(SoundType.CHERRY_WOOD)
                                                                                  .ignitedByLava()));

    // CUSTOM stripped wood
    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_WOOD = registerBlock("stripped_bloodwood_wood",
           (properties) -> new ModFlammableRotatedPillarBlock(properties.instrument(NoteBlockInstrument.BASS)
                                                                                  .strength(2.0F)
                                                                                  .sound(SoundType.CHERRY_WOOD)
                                                                                  .ignitedByLava()));

    // CUSTOM planks
    public static final DeferredBlock<Block> BLOODWOOD_PLANKS = registerBlock("bloodwood_planks",
           (properties) -> new Block(properties) {
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

    // CUSTOM leaves
    public static final DeferredBlock<Block> BLOODWOOD_LEAVES = registerBlock("bloodwood_leaves",
           (properties) -> new UntintedParticleLeavesBlock(0.01F,
                                                                     ParticleTypes.CHERRY_LEAVES,
                                                                     properties.mapColor(MapColor.PLANT)
                                                                               .strength(0.2F)
                                                                               .randomTicks()
                                                                               .sound(SoundType.CHERRY_LEAVES)
                                                                               .noOcclusion()
                                                                               .isValidSpawn(Blocks::ocelotOrParrot)
                                                                               .ignitedByLava()
                                                                               .pushReaction(PushReaction.DESTROY)) {
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

    // CUSTOM sapling
    public static final DeferredBlock<Block> BLOODWOOD_SAPLING = registerBlock("bloodwood_sapling",
           (properties) -> new ModSaplingBlock(ModTreeGrowers.BLOODWOOD,
                                                         properties.mapColor(MapColor.PLANT)
                                                                   .noCollission().randomTicks()
                                                                   .instabreak().sound(SoundType.GRASS)
                                                                   .pushReaction(PushReaction.DESTROY),
                                                         () -> Blocks.NETHERRACK));

    // WALNUT
    public static final DeferredBlock<Block> WALNUT_LOG = registerBlock("walnut_log",
           (properties) -> new ModFlammableRotatedPillarBlock(properties.instrument(NoteBlockInstrument.BASS)
                                                                                  .strength(2.0F)
                                                                                  .sound(SoundType.WOOD)
                                                                                  .ignitedByLava()));

    public static final DeferredBlock<Block> WALNUT_WOOD = registerBlock("walnut_wood",
           (properties) -> new ModFlammableRotatedPillarBlock(properties.instrument(NoteBlockInstrument.BASS)
                                                                                  .strength(2.0F)
                                                                                  .sound(SoundType.WOOD)
                                                                                  .ignitedByLava()));

    public static final DeferredBlock<Block> STRIPPED_WALNUT_LOG = registerBlock("stripped_walnut_log",
           (properties) -> new ModFlammableRotatedPillarBlock(properties.instrument(NoteBlockInstrument.BASS)
                                                                                  .strength(2.0F)
                                                                                  .sound(SoundType.WOOD)
                                                                                  .ignitedByLava()));

    public static final DeferredBlock<Block> STRIPPED_WALNUT_WOOD = registerBlock("stripped_walnut_wood",
           (properties) -> new ModFlammableRotatedPillarBlock(properties.instrument(NoteBlockInstrument.BASS)
                                                                                  .strength(2.0F)
                                                                                  .sound(SoundType.WOOD)
                                                                                  .ignitedByLava()));

    public static final DeferredBlock<Block> WALNUT_PLANKS = registerBlock("walnut_planks",
           (properties) -> new Block(properties) {
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

    public static final DeferredBlock<Block> WALNUT_LEAVES = registerBlock("walnut_leaves",
           (properties) -> new UntintedParticleLeavesBlock(0.01F,
                                                                     ParticleTypes.CHERRY_LEAVES,
                                                                     properties.mapColor(MapColor.PLANT)
                                                                               .strength(0.2F)
                                                                               .randomTicks()
                                                                               .sound(SoundType.CHERRY_LEAVES)
                                                                               .noOcclusion()
                                                                               .isValidSpawn(Blocks::ocelotOrParrot)
                                                                               .ignitedByLava()
                                                                               .pushReaction(PushReaction.DESTROY)) {
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

    public static final DeferredBlock<Block> WALNUT_SAPLING = registerBlock("walnut_sapling",
           (properties) -> new ModSaplingBlock(ModTreeGrowers.WALNUT,
                                                         properties.mapColor(MapColor.PLANT)
                                                                   .noCollission().randomTicks()
                                                                   .instabreak().sound(SoundType.GRASS)
                                                                   .pushReaction(PushReaction.DESTROY),
                                                         () -> Blocks.END_STONE));

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

    // Kaupen custom portal block
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

    // ** CUSTOM METHOD - ENDER PEARL blocks **
    protected static DeferredBlock<Block> enderPearlBlock(String name, MapColor color) {
        return registerBlock(name, (properties) ->
                             new Block(properties.mapColor(color)
                                                 .instrument(NoteBlockInstrument.BELL)
                                                 .requiresCorrectToolForDrops()
                                                 .strength(5.0F, 6.0F)
                                                 .sound(SoundType.METAL)
                                                 .lightLevel(state -> 50)));
    }

    // ** CUSTOM METHOD - Mob nine blocks **
    protected static DeferredBlock<Block> mobBlock(String name, MapColor color,
                                                   NoteBlockInstrument noteBlock, SoundType soundType) {
       return registerBlock(name, (properties) ->
                            new Block(properties.mapColor(color)
                                                .instrument(noteBlock)
                                                .requiresCorrectToolForDrops()
                                                .strength(5.0F, 6.0F)
                                                .sound(soundType)));
    }

    // CUSTOM METHOD - Registry all custom BLOCKS
    private static <T extends Block> DeferredBlock<T> registerBlock(String name,
                                                                    Function<BlockBehaviour.Properties, T> block) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // CUSTOM METHOD - Registry all custom BLOCK ITEMS
    private static <T extends Block> void registerBlockItem(String name,
                                                            DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, (properties) ->
                                    new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    // CUSTOM METHOD - Registry all custom BLOCKS on EVENT
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}