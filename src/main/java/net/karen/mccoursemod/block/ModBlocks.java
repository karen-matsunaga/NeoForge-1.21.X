package net.karen.mccoursemod.block;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.custom.*;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
           DeferredRegister.createBlocks(MccourseMod.MOD_ID);

    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
            () -> new Block(BlockBehaviour.Properties.of()
                                          .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)
                                          .setId(ResourceKey.create(Registries.BLOCK,
                                                 ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "bismuth_block")))));

    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock("bismuth_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                      BlockBehaviour.Properties.of().strength(3f)
                                    .requiresCorrectToolForDrops().sound(SoundType.STONE)
                                    .setId(ResourceKey.create(Registries.BLOCK,
                                           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "bismuth_ore")))));

    public static final DeferredBlock<Block> BISMUTH_DEEPSLATE_ORE = registerBlock("bismuth_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6),
                      BlockBehaviour.Properties.of().strength(4f)
                                    .requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)
                                    .setId(ResourceKey.create(Registries.BLOCK,
                                                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                              "bismuth_deepslate_ore")))));

    public static final DeferredBlock<Block> BISMUTH_END_ORE = registerBlock("bismuth_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(5, 9),
                      BlockBehaviour.Properties.of().strength(7f)
                                    .requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM)
                                    .setId(ResourceKey.create(Registries.BLOCK,
                                                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                              "bismuth_end_ore")))));

    public static final DeferredBlock<Block> BISMUTH_NETHER_ORE = registerBlock("bismuth_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 5),
                    BlockBehaviour.Properties.of().strength(3f)
                                             .requiresCorrectToolForDrops().sound(SoundType.NETHERRACK)
                                             .setId(ResourceKey.create(Registries.BLOCK,
                                                                       ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                       "bismuth_nether_ore")))));

    public static final DeferredBlock<Block> MAGIC = registerBlock("magic",
           () -> new MagicBlock(BlockBehaviour.Properties.of().strength(2f).sound(ModSounds.MAGIC_BLOCK_SOUNDS)
                                              .setId(ResourceKey.create(Registries.BLOCK,
                                                     ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "magic")))));

    // CUSTOM Enchant block
    public static final DeferredBlock<Block> ENCHANT = registerBlock("enchant",
            () -> new EnchantBlock(BlockBehaviour.Properties.of().strength(5.0F, 3600000.0F)
                  .requiresCorrectToolForDrops()
                  .setId(ResourceKey.create(Registries.BLOCK,
                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "enchant")))));

    // CUSTOM Disenchant individual block
    public static final DeferredBlock<Block> DISENCHANT_INDIVIDUAL = registerBlock("disenchant_individual",
            () -> new DisenchantBlock(BlockBehaviour.Properties.of().strength(5.0F, 3600000.0F)
                  .requiresCorrectToolForDrops()
                  .setId(ResourceKey.create(Registries.BLOCK,
                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "disenchant_individual"))), 1));

    // CUSTOM Disenchant grouped block
    public static final DeferredBlock<Block> DISENCHANT_GROUPED = registerBlock("disenchant_grouped",
            () -> new DisenchantBlock(BlockBehaviour.Properties.of().strength(5.0F, 3600000.0F)
                  .requiresCorrectToolForDrops()
                  .setId(ResourceKey.create(Registries.BLOCK,
                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "disenchant_grouped"))), 2));

    // Mccourse Mod Elevator block
    public static final DeferredBlock<Block> MCCOURSEMOD_ELEVATOR =
            registerBlock("mccoursemod_elevator",
            () -> new MccourseElevatorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL)
                                                                          .sound(SoundType.WOOL)
                                                                          .strength(5F, 1200.F)
                                                                          .requiresCorrectToolForDrops()
                                                                          .setId(ResourceKey.create(Registries.BLOCK,
                                                                                 ResourceLocation.fromNamespaceAndPath(
                                                                                 MccourseMod.MOD_ID,
                                                                                 "mccoursemod_elevator")))));

    // Block Family
    public static final DeferredBlock<StairBlock> BISMUTH_STAIRS =
           registerBlock("bismuth_stairs",
           () -> new StairBlock(ModBlocks.BISMUTH_BLOCK.get().defaultBlockState(),
                 BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()
                                                            .setId(ResourceKey.create(Registries.BLOCK,
                                                                   ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                   "bismuth_stairs")))));

    public static final DeferredBlock<SlabBlock> BISMUTH_SLAB =
           registerBlock("bismuth_slab",
           () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()
                                                                          .setId(ResourceKey.create(Registries.BLOCK,
                                                                                 ResourceLocation.fromNamespaceAndPath(
                                                                                 MccourseMod.MOD_ID, "bismuth_slab")))));

    public static final DeferredBlock<PressurePlateBlock> BISMUTH_PRESSURE_PLATE =
           registerBlock("bismuth_pressure_plate",
           () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(2f)
                                                                         .requiresCorrectToolForDrops()
                                                                         .setId(ResourceKey.create(Registries.BLOCK,
                                                                                ResourceLocation.fromNamespaceAndPath(
                                                                                MccourseMod.MOD_ID,
                                                                                "bismuth_pressure_plate")))));

    public static final DeferredBlock<ButtonBlock> BISMUTH_BUTTON = registerBlock("bismuth_button",
           () -> new ButtonBlock(BlockSetType.IRON, 20,
                 BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()
                                                            .noCollission()
                                                            .setId(ResourceKey.create(Registries.BLOCK,
                                                                   ResourceLocation.fromNamespaceAndPath(
                                                                   MccourseMod.MOD_ID, "bismuth_button")))));

    public static final DeferredBlock<FenceBlock> BISMUTH_FENCE = registerBlock("bismuth_fence",
           () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2f)
                                                              .requiresCorrectToolForDrops()
                                                              .setId(ResourceKey.create(Registries.BLOCK,
                                                                     ResourceLocation.fromNamespaceAndPath(
                                                                     MccourseMod.MOD_ID, "bismuth_fence")))));

    public static final DeferredBlock<FenceGateBlock> BISMUTH_FENCE_GATE = registerBlock("bismuth_fence_gate",
           () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of().strength(2f)
                                                                              .requiresCorrectToolForDrops()
                                                                              .setId(ResourceKey.create(Registries.BLOCK,
                                                                                     ResourceLocation.fromNamespaceAndPath(
                                                                                     MccourseMod.MOD_ID,
                                                                                     "bismuth_fence_gate")))));

    public static final DeferredBlock<WallBlock> BISMUTH_WALL = registerBlock("bismuth_wall",
           () -> new WallBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()
                                                                          .setId(ResourceKey.create(Registries.BLOCK,
                                                                                 ResourceLocation.fromNamespaceAndPath(
                                                                                 MccourseMod.MOD_ID, "bismuth_wall")))));

    public static final DeferredBlock<DoorBlock> BISMUTH_DOOR = registerBlock("bismuth_door",
           () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(2f)
                                                                           .requiresCorrectToolForDrops()
                                                                           .noOcclusion()
                                                                           .setId(ResourceKey.create(Registries.BLOCK,
                                                                                  ResourceLocation.fromNamespaceAndPath(
                                                                                  MccourseMod.MOD_ID, "bismuth_door")))));

    public static final DeferredBlock<TrapDoorBlock> BISMUTH_TRAPDOOR = registerBlock("bismuth_trapdoor",
           () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(2f)
                                                                    .requiresCorrectToolForDrops()
                                                                    .noOcclusion()
                                                                    .setId(ResourceKey.create(Registries.BLOCK,
                                                                           ResourceLocation.fromNamespaceAndPath(
                                                                           MccourseMod.MOD_ID, "bismuth_trapdoor")))));

    // CUSTOM blockstate block
    public static final DeferredBlock<Block> BISMUTH_LAMP = registerBlock("bismuth_lamp",
           () -> new BismuthLampBlock(BlockBehaviour.Properties.of().strength(2f)
                                                    .requiresCorrectToolForDrops()
                                                    .lightLevel(state ->
                                                                state.getValue(BismuthLampBlock.CLICKED) ? 15 : 0)
                                                    .setId(ResourceKey.create(Registries.BLOCK,
                                                           ResourceLocation.fromNamespaceAndPath(
                                                           MccourseMod.MOD_ID, "bismuth_lamp")))));

    // CUSTOM crop block
    public static final DeferredBlock<Block> RADISH_CROP = BLOCKS.registerBlock("radish_crop",
            (properties) -> new RadishCropBlock(properties.mapColor(MapColor.PLANT)
                                                                    .strength(0.2F)
                                                                    .randomTicks()
                                                                    .instabreak()
                                                                    .sound(SoundType.CROP)
                                                                    .noOcclusion()
                                                                    .pushReaction(PushReaction.DESTROY)));

    // CUSTOM bush crop block
    public static final DeferredBlock<Block> GOJI_BERRY_BUSH = BLOCKS.registerBlock("goji_berry_bush",
            (properties) -> new GojiBerryBushBlock(properties.mapColor(MapColor.PLANT)
                                                                       .strength(0.2F)
                                                                       .randomTicks()
                                                                       .sound(SoundType.CROP)
                                                                       .noOcclusion()
                                                                       .pushReaction(PushReaction.DESTROY)));

    // CUSTOM log
    public static final DeferredBlock<Block> BLOODWOOD_LOG = registerBlock("bloodwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.of()
                                                                   .instrument(NoteBlockInstrument.BASS)
                                                                   .strength(2.0F)
                                                                   .sound(SoundType.CHERRY_WOOD)
                                                                   .ignitedByLava()
                                                                   .setId(ResourceKey.create(Registries.BLOCK,
                                                                          ResourceLocation.fromNamespaceAndPath(
                                                                          MccourseMod.MOD_ID, "bloodwood_log")))));

    // CUSTOM wood
    public static final DeferredBlock<Block> BLOODWOOD_WOOD = registerBlock("bloodwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.of()
                                                                   .instrument(NoteBlockInstrument.BASS)
                                                                   .strength(2.0F)
                                                                   .sound(SoundType.CHERRY_WOOD)
                                                                   .ignitedByLava()
                                                                   .setId(ResourceKey.create(Registries.BLOCK,
                                                                          ResourceLocation.fromNamespaceAndPath(
                                                                          MccourseMod.MOD_ID, "bloodwood_wood")))));

    // CUSTOM stripped log
    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_LOG = registerBlock("stripped_bloodwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.of()
                                                                   .instrument(NoteBlockInstrument.BASS)
                                                                   .strength(2.0F)
                                                                   .sound(SoundType.CHERRY_WOOD)
                                                                   .ignitedByLava()
                                                                   .setId(ResourceKey.create(Registries.BLOCK,
                                                                          ResourceLocation.fromNamespaceAndPath(
                                                                          MccourseMod.MOD_ID, "stripped_bloodwood_log")))));

    // CUSTOM stripped wood
    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_WOOD = registerBlock("stripped_bloodwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.of()
                                                                   .instrument(NoteBlockInstrument.BASS)
                                                                   .strength(2.0F)
                                                                   .sound(SoundType.CHERRY_WOOD)
                                                                   .ignitedByLava()
                                                                   .setId(ResourceKey.create(Registries.BLOCK,
                                                                          ResourceLocation.fromNamespaceAndPath(
                                                                          MccourseMod.MOD_ID, "stripped_bloodwood_wood")))));

    // CUSTOM planks
    public static final DeferredBlock<Block> BLOODWOOD_PLANKS = registerBlock("bloodwood_planks",
            () -> new Block(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK,
                                                                 ResourceLocation.fromNamespaceAndPath(
                                                                 MccourseMod.MOD_ID, "bloodwood_planks")))) {
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
            () -> new UntintedParticleLeavesBlock(0.01f, ParticleTypes.CHERRY_LEAVES,
                  BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
                                                .strength(0.2F)
                                                .randomTicks()
                                                .sound(SoundType.CHERRY_LEAVES)
                                                .noOcclusion()
                                                .isValidSpawn(Blocks::ocelotOrParrot)
                                                .ignitedByLava()
                                                .pushReaction(PushReaction.DESTROY)
                                                .setId(ResourceKey.create(Registries.BLOCK,
                                                       ResourceLocation.fromNamespaceAndPath(
                                                       MccourseMod.MOD_ID, "bloodwood_leaves")))) {
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
            () -> new ModSaplingBlock(ModTreeGrowers.BLOODWOOD, BlockBehaviour.Properties.of()
                                                                              .mapColor(MapColor.PLANT)
                                                                              .noCollission()
                                                                              .randomTicks()
                                                                              .instabreak()
                                                                              .sound(SoundType.GRASS)
                                                                              .pushReaction(PushReaction.DESTROY)
                                                                              .setId(ResourceKey.create(Registries.BLOCK,
                                                                                     ResourceLocation.fromNamespaceAndPath(
                                                                                     MccourseMod.MOD_ID, "bloodwood_sapling"))),
                                      () -> Blocks.NETHERRACK));

    // CUSTOM sittable block model
    public static final DeferredBlock<Block> CHAIR = registerBlock("chair",
           () -> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion()
                                                              .setId(ResourceKey.create(Registries.BLOCK,
                                                                     ResourceLocation.fromNamespaceAndPath(
                                                                     MccourseMod.MOD_ID, "chair")))));

    // CUSTOM block entity
    public static final DeferredBlock<Block> PEDESTAL = registerBlock("pedestal",
           () -> new PedestalBlock(BlockBehaviour.Properties.of().noOcclusion().setId(ResourceKey.create(Registries.BLOCK,
                                                                                      ResourceLocation.fromNamespaceAndPath(
                                                                                      MccourseMod.MOD_ID, "pedestal")))));

    // CUSTOM METHOD - Registry all custom blocks
    private static <T extends Block> DeferredBlock<T> registerBlock(String name,
                                                                    Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // CUSTOM METHOD - Registry all custom block items
    private static <T extends Block> void registerBlockItem(String name,
                                                            DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM,
                               ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name)))));
    }

    // CUSTOM METHOD - Registry all custom blocks on event bus
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}