package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.particle.ModParticles;
import net.karen.mccoursemod.sound.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(Blocks.STONE, Blocks.STONE_BRICKS,
                   Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                   Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
                   Blocks.NETHERRACK, Blocks.NETHER_BRICKS,
                   ModBlocks.RUBY_BLOCK.get(), ModBlocks.RUBY_BLOCK_1.get(),
                   ModBlocks.RUBY_BLOCK_2.get(), ModBlocks.RUBY_BLOCK_3.get(),
                   ModBlocks.WAXED_RUBY_BLOCK.get(),  ModBlocks.WAXED_RUBY_BLOCK_1.get(),
                   ModBlocks.WAXED_RUBY_BLOCK_2.get(), ModBlocks.WAXED_RUBY_BLOCK_3.get());

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Block clickedBlock = level.getBlockState(clickedPos).getBlock();
        if (CHISEL_MAP.containsKey(clickedBlock)) {
            if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
                Player player = context.getPlayer();
                ItemStack itemHand = context.getItemInHand();
                level.setBlockAndUpdate(clickedPos, CHISEL_MAP.get(clickedBlock).defaultBlockState());
                itemHand.hurtAndBreak(1, serverLevel, player, item -> {
                                       if (player != null) {
                                           player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                                       }
                                      });
                level.playSound(null, clickedPos, ModSounds.CHISEL_USE.get(), SoundSource.BLOCKS);
                int x = clickedPos.getX();
                int y = clickedPos.getY();
                int z = clickedPos.getZ();
                serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK,
                                                                  clickedBlock.defaultBlockState()),
                                          x + 0.5, y + 1.0,
                                          z + 0.5, 5,
                                          0, 0, 0, 1);
                serverLevel.sendParticles(ParticleTypes.DOLPHIN,
                                          x + 0.5, y + 1.5,
                                          z + 0.5, 5,
                                          0, 0, 0, 3);
                serverLevel.sendParticles(ModParticles.BISMUTH_PARTICLES.get(),
                                          x + 0.5, y + 1.0,
                                          z + 0.5, 5,
                                          0, 0, 0, 3);
                itemHand.set(ModDataComponentTypes.COORDINATES, clickedPos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return componentTranslatable(this.descriptionId, gold);
    }

    // CUSTOM METHOD - CHISEL shift description
    public String chiselShiftDescription() {
        if (Screen.hasShiftDown()) { return "tooltip.mccoursemod.chisel.shift_down"; }
        else { return "tooltip.mccoursemod.chisel"; }
    }

    // CUSTOM METHOD - CHISEL item description
    public String chiselItemDescription(ItemStack stack) {
        BlockPos COORDINATES = stack.get(ModDataComponentTypes.COORDINATES);
        if (COORDINATES != null) {
            return "Last Block changed at [X: " + COORDINATES.getX() +
                    ", Y: " +  COORDINATES.getY() + ", Z: " + COORDINATES.getZ() + "]";
        }
        else { return ""; }
    }
}