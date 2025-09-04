package net.karen.mccoursemod.block.custom;

import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;
import java.util.Optional;

public class MccourseModGeneratorBlock extends Block {
    private final String type; // TYPE of drop (RANDOM or ALL)
    private final TagKey<Block> blocks; // BLOCK TAG to generate drops

    public MccourseModGeneratorBlock(Properties properties, String type, TagKey<Block> blocks) {
        super(properties);
        this.type = type;
        this.blocks = blocks;
    }

    @Override
    public boolean onDestroyedByPlayer(@NotNull BlockState state,
                                       Level level, @NotNull BlockPos pos, Player player,
                                       boolean willHarvest, @NotNull FluidState fluid) {
        boolean destroy = player.isCreative() || player.getMainHandItem().is(ModItems.PINK_PICKAXE.get());
        // ENABLES destruction only CREATIVE mode or uses PINK PICKAXE, but not received DROP items
        if (!level.isClientSide()) {
            if (destroy) { return level.destroyBlock(pos, false); }
            else {
                if (state.getBlock().equals(this)) { // Is MCCOURSE GENERATOR custom block
                    Optional<HolderSet.Named<Block>> blockTag = BuiltInRegistries.BLOCK.get(blocks);
                    if (blockTag.isPresent()) {
                        HolderSet.Named<Block> block = blockTag.get();
                        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
                        switch (type) {
                            // 1. RANDOM TYPE -> DROP RANDOM BLOCK;
                            case "RANDOM" -> block.getRandomElement(RandomSource.create())
                                                  .ifPresent(drop ->
                                                             Utils.dropItems(level, x, y, z,
                                                                            new ItemStack(drop.value().asItem())));

                            // 2. ALL TYPE -> DROP ALL BLOCK CONTAINED ON BLOCK TAG
                            case "ALL" ->
                                 block.forEach(drop ->
                                               Utils.dropItems(level, x, y, z, new ItemStack(drop.value().asItem())));
                        }
                    }
                }
                level.sendBlockUpdated(pos, state, state, 3); // PREVENTS destruction of block
                return false;
            }
        }
        return destroy; // On the SERVER side, it allows only if you are creative
    }

    @Override
    public boolean canHarvestBlock(@NotNull BlockState state,
                                   @NotNull BlockGetter level, @NotNull BlockPos pos, Player player) {
        // Checks if the player is holding the item is PINK PICKAXE
        return player.getMainHandItem().is(ModItems.PINK_PICKAXE.get());
    }
}