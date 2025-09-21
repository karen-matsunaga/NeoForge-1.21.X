package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.util.ChatUtils;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.Optional;

public class FarmerItem extends Item {
    public FarmerItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        if (!level.isClientSide() && player != null) {
            if (isBlockTag(ModTags.Blocks.FARMER_TREE_GROWABLES, block)) {
                if (block instanceof BonemealableBlock growable) { // Standard Bonemealable
                    if (growable.isValidBonemealTarget(level, pos, state)) {
                        if (block instanceof SaplingBlock sapling) { // Vanilla sapling -> Oak, Acacia, etc.
                            sapling.advanceTree((ServerLevel) level, pos, state, level.random);
                        }
                        else if (block instanceof MangrovePropaguleBlock mangrove) { // Dark and Jungle saplings
                            mangrove.advanceTree((ServerLevel) level, pos, state, level.random);
                        }
                        else { growable.performBonemeal((ServerLevel) level, level.random, pos, state); }
                        return consumeAndSucceed(stack, player); // Used Farmer on TREES
                    }
                    else { return InteractionResult.SUCCESS; }
                }
            }
            if (isBlockTag(ModTags.Blocks.FARMER_CROPS_GROWABLES, block)) { // Potato, Carrot, Beetroot, etc.
                for (Property<?> property : state.getProperties()) {
                    if (property.getName().equals("age") && property instanceof IntegerProperty age) {
                        int currentAge = state.getValue(age), maxAge = Collections.max(age.getPossibleValues());
                        if (currentAge < maxAge) {
                            grow(level, pos, state.setValue(age, maxAge), 2);
                            return consumeAndSucceed(stack, player); // Used Farmer on CROPS
                        }
                        else { return InteractionResult.SUCCESS; } // Crops are max age level
                    }
                }
            }
            if (isBlockTag(ModTags.Blocks.FARMER_VERTICAL_GROWABLES, block)) { // Bamboo, Sugar cane and Cactus
                int height = 0;
                BlockState contains = block.defaultBlockState();
                BlockPos.MutableBlockPos current = pos.mutable();
                while (level.getBlockState(current.above()).is(block) && height++ < 16) {
                    current.move(Direction.UP);
                }
                boolean grew = false; // Vertical grow not max level
                for (int i = 0; i < 3; i++) {
                    BlockPos above = current.above();
                    if (level.isEmptyBlock(above) && contains.canSurvive(level, above)) {
                        grow(level, above, contains, 3);
                        current = above.mutable();
                        grew = true; // Vertical grow max age level
                    }
                    else { break; }
                }
                // Vertical growth (if tagged) - Used Farmer on GROW VERTICALLY
                return grew ? consumeAndSucceed(stack, player) : InteractionResult.SUCCESS;
            }
            if (isBlockTag(ModTags.Blocks.FARMER_AGE_GROWABLES, block) && state.hasProperty(BlockStateProperties.AGE_3)) {
                if (state.getValue(BlockStateProperties.AGE_3) < 3) {
                    grow(level, pos, state.setValue(BlockStateProperties.AGE_3, 3), 2);
                    return consumeAndSucceed(stack, player); // Used Farmer on NETHER WART
                }
                else { return InteractionResult.SUCCESS; } // Nether Wart is max age level
            }
        }
        return InteractionResult.PASS;
    }

    // CUSTOM METHOD - Consumed Farmer item
    private InteractionResult consumeAndSucceed(ItemStack stack, Player player) {
        if (!player.getAbilities().instabuild) { stack.shrink(1); } // Every time it is used it is consumed
        return InteractionResult.SUCCESS;
    }

    // CUSTOM METHOD - Check if the BLOCK is in the BLOCK TAG
    public static boolean isBlockTag(TagKey<Block> blocks, Block block) {
        Optional<HolderSet.Named<Block>> blockTag = BuiltInRegistries.BLOCK.get(blocks);
        if (blockTag.isPresent()) {
            HolderSet.Named<Block> blockList = blockTag.get();
            for (Holder<Block> blockHolder : blockList) {
                Block blockTest = blockHolder.value();
                if (blockTest == block) { return blockTag.get().contains(blockHolder); }
            }
        }
        return false;
    }

    // CUSTOM METHOD - Grow block
    public static void grow(Level level, BlockPos pos, BlockState state, int flag) {
        level.setBlock(pos, state, flag);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return ChatUtils.componentTranslatableIntColor(this.getDescriptionId(), 0x4094D3);
    }
}