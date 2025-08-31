package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.util.ModTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.Optional;

public class PaxelItem extends Item {
    public PaxelItem(ToolMaterial material, float attackDamage,
                     float attackSpeed, Properties properties) {
        super(material.applyToolProperties(properties, ModTags.Blocks.MINEABLE_WITH_PAXEL,
                                           attackDamage, attackSpeed, 0F));
    }

    // DEFAULT METHOD - AXE and SHOVEL functions
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();
        if (playerHasBlockingItemUseIntent(context)) { return InteractionResult.PASS; }
        else {
            Optional<BlockState> axeOptional =
                    this.evaluateNewBlockState(level, blockpos, player, level.getBlockState(blockpos), context);
            if (axeOptional.isEmpty()) {
                BlockState blockstate = level.getBlockState(blockpos);
                if (context.getClickedFace() == Direction.DOWN) { return InteractionResult.PASS; }
                else {
                    BlockState shovelFlatten =
                         blockstate.getToolModifiedState(context, ItemAbilities.SHOVEL_FLATTEN, false);
                    BlockState block = null;
                    if (shovelFlatten != null && level.getBlockState(blockpos.above()).isAir()) {
                        level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                        block = shovelFlatten;
                    }
                    else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
                        if (!level.isClientSide()) { level.levelEvent(null, 1009, blockpos, 0); }
                        CampfireBlock.dowse(context.getPlayer(), level, blockpos, blockstate);
                        block = blockstate.setValue(CampfireBlock.LIT, Boolean.FALSE);
                    }
                    if (block != null) {
                        if (!level.isClientSide()) {
                            level.setBlock(blockpos, block, 11);
                            level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, block));
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
                level.setBlock(blockpos, axeOptional.get(), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, axeOptional.get()));
                if (player != null) {
                    itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }
                return InteractionResult.SUCCESS;
            }
        }
    }

    // CUSTOM METHOD - AXE block state
    private Optional<BlockState> evaluateNewBlockState(Level level, BlockPos pos,
                                                       @Nullable Player player,
                                                       BlockState state, UseOnContext context) {
        Optional<BlockState> axeStrip =
                Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_STRIP, false));
        if (axeStrip.isPresent()) {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return axeStrip;
        }
        else {
            Optional<BlockState> axeScrape =
                    Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_SCRAPE, false));
            if (axeScrape.isPresent()) {
                level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3005, pos, 0);
                return axeScrape;
            }
            else {
                Optional<BlockState> axeWaxOff =
                        Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_WAX_OFF, false));
                if (axeWaxOff.isPresent()) {
                    level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.levelEvent(player, 3004, pos, 0);
                    return axeWaxOff;
                }
                else { return Optional.empty(); }
            }
        }
    }

    // CUSTOM METHOD - Used AXE (MAIN HAND) with SHIELD (OFFHAND) items
    private static boolean playerHasBlockingItemUseIntent(UseOnContext context) {
        Player player = context.getPlayer();
        return context.getHand().equals(InteractionHand.MAIN_HAND) &&
               player != null && player.getOffhandItem().has(DataComponents.BLOCKS_ATTACKS) &&
               !player.isSecondaryUseActive();
    }

    // DEFAULT METHOD - AXE and SHOVEL vanilla actions
    @Override
    public boolean canPerformAction(@NotNull ItemStack stack,
                                    @NotNull ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility) ||
               ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);
    }
}