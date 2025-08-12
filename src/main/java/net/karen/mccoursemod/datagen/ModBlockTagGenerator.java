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

        // Auto Smelt enchantment effect
        this.tag(ModTags.Blocks.AUTO_SMELT_ORES).addTag(Tags.Blocks.ORES).addTag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE);

        // More Ores break block and random drop enchantment effect
        this.tag(ModTags.Blocks.MORE_ORES_BREAK_BLOCK).add(Blocks.STONE, Blocks.NETHERRACK);
        this.tag(ModTags.Blocks.MORE_ORES_ONE_DROPS).add(Blocks.COAL_ORE, Blocks.COPPER_ORE);
        this.tag(ModTags.Blocks.MORE_ORES_TWO_DROPS).add(Blocks.IRON_ORE, Blocks.LAPIS_ORE);
        this.tag(ModTags.Blocks.MORE_ORES_THREE_DROPS).add(Blocks.REDSTONE_ORE, Blocks.GOLD_ORE);
        this.tag(ModTags.Blocks.MORE_ORES_FOUR_DROPS).add(Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE);
        this.tag(ModTags.Blocks.MORE_ORES_FIVE_DROPS).add(Blocks.ANCIENT_DEBRIS, Blocks.NETHER_GOLD_ORE, Blocks.NETHER_QUARTZ_ORE);
        this.tag(ModTags.Blocks.MORE_ORES_ALL_DROPS).addTag(ModTags.Blocks.MORE_ORES_ONE_DROPS)
                                                    .addTag(ModTags.Blocks.MORE_ORES_TWO_DROPS)
                                                    .addTag(ModTags.Blocks.MORE_ORES_THREE_DROPS)
                                                    .addTag(ModTags.Blocks.MORE_ORES_FOUR_DROPS)
                                                    .addTag(ModTags.Blocks.MORE_ORES_FIVE_DROPS);

        // Paxel break
        this.tag(ModTags.Blocks.MINEABLE_WITH_PAXEL).addTag(BlockTags.MINEABLE_WITH_AXE)
                                                    .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                                                    .addTag(BlockTags.MINEABLE_WITH_SHOVEL);

        // Block Fly enchantment effect
        this.tag(ModTags.Blocks.BLOCK_FLY_BLOCK_SPEED).add(Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND,
                                                           Blocks.ICE, Blocks.PACKED_ICE,
                                                           Blocks.BLUE_ICE, Blocks.GLOWSTONE)
                                                      .addTag(Tags.Blocks.GLASS_BLOCKS);
    }
}