package net.karen.mccoursemod.block;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.custom.*;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.block.custom.GrowthChamberBlock;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
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

    // ** CUSTOM blockstate block **
    public static final DeferredBlock<Block> BISMUTH_LAMP = registerBlock("bismuth_lamp",
           (properties) -> new BismuthLampBlock(properties.strength(2F).requiresCorrectToolForDrops()
                                                                    .lightLevel(state ->
                                                                                state.getValue(BismuthLampBlock.CLICKED)
                                                                                               ? 15 : 0)));

    // ** CUSTOM crop block **
    public static final DeferredBlock<Block> RADISH_CROP = BLOCKS.registerBlock("radish_crop",
           (properties) -> new RadishCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F)
                                                                   .randomTicks().instabreak()
                                                                   .sound(SoundType.CROP).noOcclusion()
                                                                   .pushReaction(PushReaction.DESTROY)));

    // ** CUSTOM bush crop block **
    public static final DeferredBlock<Block> GOJI_BERRY_BUSH = BLOCKS.registerBlock("goji_berry_bush",
           (properties) -> new GojiBerryBushBlock(properties.mapColor(MapColor.PLANT).strength(0.2F)
                                                                      .randomTicks().instabreak()
                                                                      .sound(SoundType.CROP).noOcclusion()
                                                                      .pushReaction(PushReaction.DESTROY)));

    // ** CUSTOM log **
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
           (properties) -> new UntintedParticleLeavesBlock(0.01f,
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

    // ** CUSTOM sittable block model **
    public static final DeferredBlock<Block> CHAIR = registerBlock("chair",
           (properties) -> new ChairBlock(properties.noOcclusion()));

    // ** CUSTOM block entity **
    public static final DeferredBlock<Block> PEDESTAL = registerBlock("pedestal",
           (properties) -> new PedestalBlock(properties.noOcclusion()));

    // ** CUSTOM crafting block entity **
    public static final DeferredBlock<Block> GROWTH_CHAMBER =
           registerBlock("growth_chamber", GrowthChamberBlock::new);

    // CUSTOM METHOD - Registry all custom blocks
    private static <T extends Block> DeferredBlock<T> registerBlock(String name,
                                                                    Function<BlockBehaviour.Properties, T> block) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // CUSTOM METHOD - Registry all custom block items
    private static <T extends Block> void registerBlockItem(String name,
                                                            DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name,
                                    (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    // CUSTOM METHOD - Registry all custom blocks on event bus
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}