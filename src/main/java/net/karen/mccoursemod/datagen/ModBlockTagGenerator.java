package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.block.ModBlocks;
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
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ENCHANT.get(), ModBlocks.DISENCHANT_INDIVIDUAL.get(),
                                                      ModBlocks.DISENCHANT_GROUPED.get(), ModBlocks.BISMUTH_BLOCK.get(),
                                                      ModBlocks.MAGIC.get(), ModBlocks.MCCOURSEMOD_ELEVATOR.get());

        // CUSTOM Tier Tools - Bismuth as Netherite tier
        tag(ModTags.Blocks.NEEDS_BISMUTH_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_BISMUTH_TOOL);

        // Ores
        this.tag(ModTags.Blocks.AUTO_SMELT_ORES).addTag(Tags.Blocks.ORES).addTag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE);

        // Rainbow
        this.tag(ModTags.Blocks.RAINBOW_DROPS).addTag(Tags.Blocks.STORAGE_BLOCKS_COAL)
                                              .addTag(Tags.Blocks.STORAGE_BLOCKS_COPPER)
                                              .addTag(Tags.Blocks.STORAGE_BLOCKS_DIAMOND)
                                              .addTag(Tags.Blocks.STORAGE_BLOCKS_EMERALD)
                                              .addTag(Tags.Blocks.STORAGE_BLOCKS_GOLD)
                                              .addTag(Tags.Blocks.STORAGE_BLOCKS_IRON)
                                              .addTag(Tags.Blocks.STORAGE_BLOCKS_LAPIS)
                                              .addTag(Tags.Blocks.STORAGE_BLOCKS_NETHERITE)
                                              .addTag(Tags.Blocks.STORAGE_BLOCKS_REDSTONE);

        this.tag(ModTags.Blocks.RAINBOW_ORES).add(Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE,
                                                  Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE,
                                                  Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE,
                                                  Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE,
                                                  Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE,
                                                  Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE,
                                                  Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
                                                  Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE, Blocks.ANCIENT_DEBRIS);

        this.tag(ModTags.Blocks.RAINBOW_BLOCKS).add(Blocks.COAL_BLOCK, Blocks.COPPER_BLOCK, Blocks.DIAMOND_BLOCK,
                                                    Blocks.EMERALD_BLOCK, Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK,
                                                    Blocks.LAPIS_BLOCK, Blocks.NETHERITE_BLOCK, Blocks.REDSTONE_BLOCK);

        // More Ores random drop effect
        this.tag(ModTags.Blocks.MORE_ORES_ALL_DROPS).add(Blocks.COAL_ORE, Blocks.COPPER_ORE, Blocks.IRON_ORE,
                                                         Blocks.LAPIS_ORE, Blocks.REDSTONE_ORE, Blocks.GOLD_ORE,
                                                         Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE, Blocks.ANCIENT_DEBRIS,
                                                         Blocks.NETHER_GOLD_ORE, Blocks.NETHER_QUARTZ_ORE);

        // More Ores break block effect
        this.tag(ModTags.Blocks.MORE_ORES_BREAK_BLOCK).add(Blocks.STONE);

        // Paxel break
        this.tag(ModTags.Blocks.MINEABLE_WITH_PAXEL).addTag(BlockTags.MINEABLE_WITH_AXE)
                                                    .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                                                    .addTag(BlockTags.MINEABLE_WITH_SHOVEL);

        // Block Fly effect
        this.tag(ModTags.Blocks.BLOCK_FLY_BLOCK_SPEED).add(Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND,
                                                           Blocks.ICE, Blocks.PACKED_ICE,
                                                           Blocks.BLUE_ICE, Blocks.GLOWSTONE)
                                                      .addTag(Tags.Blocks.GLASS_BLOCKS);
    }
}