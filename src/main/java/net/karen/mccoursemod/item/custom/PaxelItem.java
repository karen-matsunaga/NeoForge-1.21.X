package net.karen.mccoursemod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

public class PaxelItem extends Item {
    public PaxelItem(ToolMaterial material, float attackDamage,
                     float attackSpeed, Properties properties) {
        super(material.applyToolProperties(properties, ModTags.Blocks.MINEABLE_WITH_PAXEL,
                                           attackDamage, attackSpeed, 0F));
    }

    // AXE
    protected static final Map<Block, Block> STRIPPABLES =
         new ImmutableMap.Builder<Block, Block>()
                         .put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD)
                         .put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
                         .put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
                         .put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG)
                         .put(Blocks.PALE_OAK_WOOD, Blocks.STRIPPED_PALE_OAK_WOOD)
                         .put(Blocks.PALE_OAK_LOG, Blocks.STRIPPED_PALE_OAK_LOG)
                         .put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
                         .put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG)
                         .put(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD)
                         .put(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG)
                         .put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
                         .put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG)
                         .put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
                         .put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG)
                         .put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
                         .put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG)
                         .put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM)
                         .put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE)
                         .put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM)
                         .put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE)
                         .put(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD)
                         .put(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG)
                         .put(Blocks.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK)
                         .build();

    // SHOVEL
    protected static final Map<Block, BlockState> FLATTENABLES =
         new ImmutableMap.Builder<Block, BlockState>()
                         .put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.defaultBlockState())
                         .put(Blocks.DIRT, Blocks.DIRT_PATH.defaultBlockState())
                         .put(Blocks.PODZOL, Blocks.DIRT_PATH.defaultBlockState())
                         .put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH.defaultBlockState())
                         .put(Blocks.MYCELIUM, Blocks.DIRT_PATH.defaultBlockState())
                         .put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.defaultBlockState())
                         .build();

    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();
        if (playerHasShieldUseIntent(context)) { return InteractionResult.PASS; }
        else {
            Optional<BlockState> optional =
                    this.evaluateNewBlockState(level, blockpos, player, level.getBlockState(blockpos), context);
            if (optional.isEmpty()) {
                BlockState blockstate = level.getBlockState(blockpos);
                if (context.getClickedFace() == Direction.DOWN) { return InteractionResult.PASS; }
                else {
                    BlockState blockstate1 =
                         blockstate.getToolModifiedState(context, ItemAbilities.SHOVEL_FLATTEN, false);
                    BlockState blockstate2 = null;
                    if (blockstate1 != null && level.getBlockState(blockpos.above()).isAir()) {
                        level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                        blockstate2 = blockstate1;
                    }
                    else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
                        if (!level.isClientSide()) { level.levelEvent(null, 1009, blockpos, 0); }
                        CampfireBlock.dowse(context.getPlayer(), level, blockpos, blockstate);
                        blockstate2 = blockstate.setValue(CampfireBlock.LIT, Boolean.FALSE);
                    }
                    if (blockstate2 != null) {
                        if (!level.isClientSide()) {
                            level.setBlock(blockpos, blockstate2, 11);
                            level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate2));
                            if (player != null) {
                                context.getItemInHand()
                                       .hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                            }
                        }
                        return InteractionResult.SUCCESS;
                    }
                    else { return InteractionResult.PASS; }
                }
            }
            else {
                ItemStack itemstack = context.getItemInHand();
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
                }
                level.setBlock(blockpos, optional.get(), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, optional.get()));
                if (player != null) {
                    itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }
                return InteractionResult.SUCCESS;
            }
        }
    }

    private Optional<BlockState> evaluateNewBlockState(Level level, BlockPos pos,
                                                       @Nullable Player player,
                                                       BlockState state, UseOnContext context) {
        Optional<BlockState> optional =
                Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_STRIP, false));
        if (optional.isPresent()) {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return optional;
        }
        else {
            Optional<BlockState> optional1 =
                    Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_SCRAPE, false));
            if (optional1.isPresent()) {
                level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3005, pos, 0);
                return optional1;
            }
            else {
                Optional<BlockState> optional2 =
                        Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_WAX_OFF, false));
                if (optional2.isPresent()) {
                    level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.levelEvent(player, 3004, pos, 0);
                    return optional2;
                }
                else { return Optional.empty(); }
            }
        }
    }

    private static boolean playerHasShieldUseIntent(UseOnContext context) {
        Player player = context.getPlayer();
        return context.getHand().equals(InteractionHand.MAIN_HAND) &&
               (player != null && player.getOffhandItem().is(Tags.Items.TOOLS_SHIELD) &&
                !player.isSecondaryUseActive());
    }

    // DEFAULT METHOD - AXE STRIPPABLES CHANGE STATE
    private Optional<BlockState> getStripped(BlockState unstrippedState) {
        return Optional.ofNullable(STRIPPABLES.get(unstrippedState.getBlock()))
                       .map(block -> block.defaultBlockState()
                                                .setValue(RotatedPillarBlock.AXIS,
                                                          unstrippedState.getValue(RotatedPillarBlock.AXIS)));
    }

    // DEFAULT METHOD - SHOVEL FLATTENABLES CHANGE STATE
    @Nullable
    public static BlockState getShovelPathingState(BlockState originalState) {
        return FLATTENABLES.get(originalState.getBlock());
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack,
                                    @NotNull ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility) ||
               ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);
    }
}