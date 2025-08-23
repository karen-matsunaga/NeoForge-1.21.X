package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.fluid.ModFluids;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaBlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends VanillaBlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider lookupProvider) {
        // ** CUSTOM pickaxe mineable blocks **
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ENCHANT.get(),
                                                      ModBlocks.DISENCHANT_INDIVIDUAL.get(),
                                                      ModBlocks.DISENCHANT_GROUPED.get(),
                                                      ModBlocks.BISMUTH_BLOCK.get(),
                                                      ModBlocks.MAGIC.get(),
                                                      ModBlocks.MCCOURSEMOD_ELEVATOR.get(),
                                                      ModBlocks.BISMUTH_LAMP.get(),
                                                      ModBlocks.FORCED_STAINED_GLASS.get(),
                                                      ModBlocks.FORCED_STAINED_GLASS_PANE.get(),
                                                      ModBlocks.ALEXANDRITE_LAMP.get(),
                                                      ModBlocks.ALEXANDRITE_BLOCK.get(),
                                                      ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
                                                      ModBlocks.SOUND.get())
                                                 .addTag(ModTags.Blocks.MCCOURSE_MOD_ORES)
                                                 .addTag(ModTags.Blocks.BISMUTH_BLOCKS)
                                                 .addTag(ModTags.Blocks.ALEXANDRITE_BLOCKS);

        // Paxel break
        this.tag(ModTags.Blocks.MINEABLE_WITH_PAXEL).addTag(BlockTags.MINEABLE_WITH_AXE)
                                                    .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                                                    .addTag(BlockTags.MINEABLE_WITH_SHOVEL);

        // ** CUSTOM ores **
        // Mccourse Mod Ores
        this.tag(ModTags.Blocks.MCCOURSE_MOD_ORES).add(ModBlocks.BISMUTH_ORE.get(),
                                                       ModBlocks.BISMUTH_DEEPSLATE_ORE.get(),
                                                       ModBlocks.BISMUTH_END_ORE.get(),
                                                       ModBlocks.BISMUTH_NETHER_ORE.get(),
                                                       ModBlocks.ALEXANDRITE_ORE.get(),
                                                       ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
                                                       ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
                                                       ModBlocks.NETHER_ALEXANDRITE_ORE.get() );

        // ** CUSTOM TIER TOOLS **
        // BISMUTH as Netherite tier
        tag(ModTags.Blocks.NEEDS_BISMUTH_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_BISMUTH_TOOL);
        // ALEXANDRITE as Diamond tier
        tag(ModTags.Blocks.NEEDS_ALEXANDRITE_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_ALEXANDRITE_TOOL);

        // ** CUSTOM enchantments **
        // Auto Smelt enchantment effect
        this.tag(ModTags.Blocks.AUTO_SMELT_ORES).addTag(Tags.Blocks.ORES).addTag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE);

        // More Ores break block and random drop enchantment effect
        this.tag(ModTags.Blocks.MORE_ORES_BREAK_BLOCK).add(Blocks.STONE, Blocks.NETHERRACK);
        this.tag(ModTags.Blocks.MORE_ORES_ONE_DROPS).add(Blocks.COAL_ORE, Blocks.COPPER_ORE);
        this.tag(ModTags.Blocks.MORE_ORES_TWO_DROPS).add(Blocks.IRON_ORE, Blocks.LAPIS_ORE);
        this.tag(ModTags.Blocks.MORE_ORES_THREE_DROPS).add(Blocks.REDSTONE_ORE, Blocks.GOLD_ORE);
        this.tag(ModTags.Blocks.MORE_ORES_FOUR_DROPS).add(Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE);
        this.tag(ModTags.Blocks.MORE_ORES_FIVE_DROPS).add(Blocks.ANCIENT_DEBRIS, Blocks.NETHER_GOLD_ORE,
                                                          Blocks.NETHER_QUARTZ_ORE);
        this.tag(ModTags.Blocks.MORE_ORES_ALL_DROPS).addTag(ModTags.Blocks.MORE_ORES_ONE_DROPS)
                                                    .addTag(ModTags.Blocks.MORE_ORES_TWO_DROPS)
                                                    .addTag(ModTags.Blocks.MORE_ORES_THREE_DROPS)
                                                    .addTag(ModTags.Blocks.MORE_ORES_FOUR_DROPS)
                                                    .addTag(ModTags.Blocks.MORE_ORES_FIVE_DROPS);

        // Block Fly enchantment effect
        this.tag(ModTags.Blocks.BLOCK_FLY_BLOCK_SPEED).add(Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND,
                                                           Blocks.ICE, Blocks.PACKED_ICE,
                                                           Blocks.BLUE_ICE, Blocks.GLOWSTONE)
                                                      .addTag(Tags.Blocks.GLASS_BLOCKS);

        // ** CUSTOM Block families **
        tag(BlockTags.BUTTONS).add(ModBlocks.BISMUTH_BUTTON.get(), ModBlocks.ALEXANDRITE_BUTTON.get());
        tag(BlockTags.DOORS).add(ModBlocks.BISMUTH_DOOR.get(), ModBlocks.ALEXANDRITE_DOOR.get());
        tag(BlockTags.FENCES).add(ModBlocks.BISMUTH_FENCE.get(), ModBlocks.ALEXANDRITE_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.BISMUTH_FENCE_GATE.get(), ModBlocks.ALEXANDRITE_FENCE_GATE.get());
        tag(BlockTags.PRESSURE_PLATES).add(ModBlocks.BISMUTH_PRESSURE_PLATE.get(), ModBlocks.BISMUTH_PRESSURE_PLATE.get());
        tag(BlockTags.STAIRS).add(ModBlocks.BISMUTH_STAIRS.get(), ModBlocks.ALEXANDRITE_STAIRS.get());
        tag(BlockTags.SLABS).add(ModBlocks.BISMUTH_SLAB.get(), ModBlocks.ALEXANDRITE_SLABS.get());
        tag(BlockTags.TRAPDOORS).add(ModBlocks.BISMUTH_TRAPDOOR.get(), ModBlocks.ALEXANDRITE_TRAPDOOR.get());
        tag(BlockTags.WALLS).add(ModBlocks.BISMUTH_WALL.get(), ModBlocks.ALEXANDRITE_WALL.get());
        // BISMUTH
        this.tag(ModTags.Blocks.BISMUTH_BLOCKS).add(ModBlocks.BISMUTH_BUTTON.get(),
                                                    ModBlocks.BISMUTH_DOOR.get(),
                                                    ModBlocks.BISMUTH_FENCE.get(),
                                                    ModBlocks.BISMUTH_FENCE_GATE.get(),
                                                    ModBlocks.BISMUTH_PRESSURE_PLATE.get(),
                                                    ModBlocks.BISMUTH_STAIRS.get(),
                                                    ModBlocks.BISMUTH_SLAB.get(),
                                                    ModBlocks.BISMUTH_TRAPDOOR.get(),
                                                    ModBlocks.BISMUTH_WALL.get());
        // ALEXANDRITE
        this.tag(ModTags.Blocks.ALEXANDRITE_BLOCKS).add(ModBlocks.ALEXANDRITE_BUTTON.get(),
                                                        ModBlocks.ALEXANDRITE_DOOR.get(),
                                                        ModBlocks.ALEXANDRITE_FENCE.get(),
                                                        ModBlocks.ALEXANDRITE_FENCE_GATE.get(),
                                                        ModBlocks.ALEXANDRITE_PREASSURE_PLATE.get(),
                                                        ModBlocks.ALEXANDRITE_STAIRS.get(),
                                                        ModBlocks.ALEXANDRITE_SLABS.get(),
                                                        ModBlocks.ALEXANDRITE_TRAPDOOR.get(),
                                                        ModBlocks.ALEXANDRITE_WALL.get());

        // ** CUSTOM Crop blocks **
        tag(BlockTags.CROPS).add(ModBlocks.RADISH_CROP.get(), ModBlocks.KOHLRABI_CROP.get());

        // ** CUSTOM log **
        // Bloodwood and Walnut logs
        this.tag(BlockTags.LOGS_THAT_BURN).add(ModBlocks.BLOODWOOD_LOG.get(), ModBlocks.WALNUT_LOG.get())
                                          .add(ModBlocks.BLOODWOOD_WOOD.get(), ModBlocks.WALNUT_WOOD.get())
                                          .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.get(), ModBlocks.STRIPPED_WALNUT_LOG.get())
                                          .add(ModBlocks.STRIPPED_BLOODWOOD_WOOD.get(), ModBlocks.STRIPPED_WALNUT_WOOD.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.BLOODWOOD_SAPLING.get(), ModBlocks.WALNUT_SAPLING.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.BLOODWOOD_LEAVES.get(), ModBlocks.WALNUT_LEAVES.get());
        this.tag(BlockTags.PLANKS).add(ModBlocks.BLOODWOOD_PLANKS.get(), ModBlocks.WALNUT_PLANKS.get());

        this.tag(ModTags.Blocks.BLOODWOOD_LOGS).add(ModBlocks.BLOODWOOD_LOG.get(), ModBlocks.BLOODWOOD_WOOD.get(),
                                                    ModBlocks.STRIPPED_BLOODWOOD_LOG.get(), ModBlocks.STRIPPED_BLOODWOOD_WOOD.get());

        this.tag(ModTags.Blocks.WALNUT_LOGS).add(ModBlocks.WALNUT_LOG.get(), ModBlocks.WALNUT_WOOD.get(),
                                                 ModBlocks.STRIPPED_WALNUT_LOG.get(), ModBlocks.STRIPPED_WALNUT_WOOD.get());

        // ** CUSTOM Glass blocks **
        this.tag(BlockTags.IMPERMEABLE).add(ModBlocks.FORCED_STAINED_GLASS.get());

        // ** CUSTOM Advanced items **
        // METAL DETECTOR
        tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES).addTag(Tags.Blocks.ORES)
                                                    .addTag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE);
        tag(ModTags.Blocks.METAL_DETECTOR_COLORS).addTag(Tags.Blocks.ORES_GOLD).addTag(Tags.Blocks.ORES_COPPER);

        // ** CUSTOM Fluid **
        tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES).add(ModFluids.SOAP_WATER_BLOCK.get());
        tag(BlockTags.GEODE_INVALID_BLOCKS).add(ModFluids.SOAP_WATER_BLOCK.get());
        tag(BlockTags.REPLACEABLE_BY_TREES).add(ModFluids.SOAP_WATER_BLOCK.get());
        tag(BlockTags.REPLACEABLE_BY_MUSHROOMS).add(ModFluids.SOAP_WATER_BLOCK.get());
    }
}