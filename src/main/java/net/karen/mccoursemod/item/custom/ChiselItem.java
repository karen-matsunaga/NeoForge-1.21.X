package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.particle.ModParticles;
import net.karen.mccoursemod.sound.ModSounds;
import net.minecraft.client.gui.screens.Screen;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(Blocks.STONE, Blocks.STONE_BRICKS, Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                   Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS, Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK,
                   Blocks.IRON_BLOCK, Blocks.STONE, Blocks.NETHERRACK, ModBlocks.BISMUTH_BLOCK.get(),
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
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        if (CHISEL_MAP.containsKey(clickedBlock)) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(), item -> {
                       Player player = context.getPlayer();
                       if (player != null) { player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND); }
                });
                level.playSound(null, context.getClickedPos(), ModSounds.CHISEL_USE.get(), SoundSource.BLOCKS);
                ((ServerLevel) level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, clickedBlock.defaultBlockState()),
                        context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.0,
                        context.getClickedPos().getZ() + 0.5, 5, 0, 0, 0, 1);
                ((ServerLevel) level).sendParticles(ParticleTypes.DOLPHIN,
                        context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.5,
                        context.getClickedPos().getZ() + 0.5, 5, 0, 0, 0, 3);
                ((ServerLevel) level).sendParticles(ModParticles.BISMUTH_PARTICLES.get(),
                        context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.0,
                        context.getClickedPos().getZ() + 0.5, 5, 0, 0, 0, 3);
                context.getItemInHand().set(ModDataComponentTypes.COORDINATES, context.getClickedPos());
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                @NotNull TooltipDisplay display, @NotNull Consumer<Component> consumer,
                                @NotNull TooltipFlag flag) {
        if (Screen.hasShiftDown()) { tooltipLineT(consumer, "tooltip.mccoursemod.chisel.shift_down", blue); }
        else { tooltipLineT(consumer, "tooltip.mccoursemod.chisel", red); }
        if (stack.get(ModDataComponentTypes.COORDINATES) != null) {
            tooltipLine(consumer, "Last Block changed at " + stack.get(ModDataComponentTypes.COORDINATES), gray);
        }
        super.appendHoverText(stack, context, display, consumer, flag);
    }
}