package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.component.custom.FoundBlock;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.util.ChatUtils;
import net.karen.mccoursemod.util.InventoryUtil;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class MetalDetectorItem extends Item {
    TagKey<Block> type;
    public MetalDetectorItem(Properties properties,
                             TagKey<Block> type) {
        super(properties);
        this.type = type;
    }

    // DEFAULT METHOD - METAL DETECTOR BLOCK TAG -> Founded ores
    public TagKey<Block> getType() { return type; }

    // DEFAULT METHOD - METAL DETECTOR item function
    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer(); // Detected position of block
        Level level = context.getLevel();
        // Client and Server sides
        if (player != null) {
            if (!level.isClientSide()) {
                BlockPos positionClicked = context.getClickedPos(); // Block that player clicked
                boolean foundBlock = false; // Starting always false when not found an ore
                // Block of current layer up to layer -64
                for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                    // Checked if found some ore
                    BlockState blockState = level.getBlockState(positionClicked.below(i));
                    // Custom method if found ore
                    if (isValuableBlock(blockState)) {
                        // Detected coordinates of block clicked
                        outputValuableCoordinates(positionClicked.below(i), player, blockState.getBlock());
                        foundBlock = true;
                        // If found ore the information is recorded in data tablet item
                        if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET.get())) {
                            addDataToDataTablet(player, positionClicked.below(i), blockState.getBlock());
                        }
                        // If found ore it is sounded
                        level.playSeededSound(null, player.getX(), player.getY(), player.getZ(),
                                              ModSounds.METAL_DETECTOR_FOUND_ORE.get(), SoundSource.BLOCKS,
                                              1f, 1f, 0);
                        // If found ore it is particles
                        spawnFoundParticles(context, positionClicked, blockState);
                        break; // Finished loop
                    }
                }
                // Output message if not found ore
                if (!foundBlock) { outputNoValuableFound(player); }
                // Durability of Metal Detector item hurt
                context.getItemInHand().hurtAndBreak(1, player, player.getUsedItemHand());
            }
        }
        return InteractionResult.SUCCESS;
    }

    // CUSTOM METHOD - CREATE custom particles if found an ORE
    private void spawnFoundParticles(UseOnContext context, BlockPos positionClicked,
                                     BlockState blockState) {
        for (int i = 0; i < 20; i++) {
            ServerLevel level = (ServerLevel) context.getLevel();
            // Position of block and spawn particle
            level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState), positionClicked.getX() + 0.5d,
                                positionClicked.getY() + 1, positionClicked.getZ() + 0.5d, 1,
                                Math.cos(i * 18) * 0.15d, 0.15d, Math.sin(i * 18) * 0.15d, 0.1);
        }
    }

    // CUSTOM METHOD - DATA TABLET item function
    private void addDataToDataTablet(Player player, BlockPos below, Block block) {
        ItemStack dataTablet =
            player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET.get()));
        FoundBlock foundBlockData = new FoundBlock(block.defaultBlockState(), below, player.level().dimension());
        // Added x, y, z coordinates on Data Tablet
        dataTablet.set(ModDataComponentTypes.FOUND_BLOCK.get(), foundBlockData);
    }

    // CUSTOM METHOD - Output message if not found ore -> Screen
    private void outputNoValuableFound(Player player) {
        playerDisplayTranslatable(player, "item.mccoursemod.metal_detector.no_valuable_values");
    }

    // CUSTOM METHOD - Output message if found ore -> Screen
    private void outputValuableCoordinates(BlockPos pos, Player player, Block block) {
        ChatFormatting color = white; // DEFAULT COLOR
        Map<TagKey<Block>, ChatFormatting> oreColors =
           Map.ofEntries(Map.entry(Tags.Blocks.ORES_DIAMOND, aqua), Map.entry(ModTags.Blocks.METAL_DETECTOR_COLORS, gold),
                         Map.entry(Tags.Blocks.ORES_IRON, gray), Map.entry(Tags.Blocks.ORES_EMERALD, darkGreen),
                         Map.entry(Tags.Blocks.ORES_REDSTONE, darkRed), Map.entry(Tags.Blocks.ORES_LAPIS, darkBlue),
                         Map.entry(Tags.Blocks.ORES_COAL, black), Map.entry(ModTags.Blocks.MCCOURSE_MOD_ORES, purple),
                         Map.entry(ModTags.Blocks.SPECIAL_METAL_DETECTOR_VALUABLES, red));

        // KEY -> Ores || VALUE -> Ore colors message
        for (Map.Entry<TagKey<Block>, ChatFormatting> entry : oreColors.entrySet()) {
            TagKey<Block> ores = entry.getKey(); // Ore blocks
            ChatFormatting oreColor = entry.getValue(); // Ore colors message
            if (block.defaultBlockState().is(ores)) {
                color = oreColor;
                break;
            }
        }

        // OUTPUT message on screen
        playDisplayLiteral(player, Component.literal("Ore found: ").withStyle(white).withStyle(bold)
                                            .append(componentTranslatableBold(block.getDescriptionId(), color))
                                            .append(componentLiteralBold(" at [X: ", white))
                                            .append(componentLiteralBold(String.valueOf(pos.getX()), color))
                                            .append(componentLiteralBold(", Y: ", white))
                                            .append(componentLiteralBold(String.valueOf(pos.getY()), color))
                                            .append(componentLiteralBold(", Z: ", white))
                                            .append(componentLiteralBold(String.valueOf(pos.getZ()), color))
                                            .append(componentLiteralBold("]", white)));
    }

    // CUSTOM METHOD - Identifies ALL BLOCKS added in metal_detector_valuables.json on CUSTOM BLOCK TAGS
    private boolean isValuableBlock(BlockState blockState) {
        return blockState.is(getType()) || blockState.is(ModTags.Blocks.SPECIAL_METAL_DETECTOR_VALUABLES);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return ChatUtils.componentTranslatable(this.getDescriptionId(), gray);
    }
}