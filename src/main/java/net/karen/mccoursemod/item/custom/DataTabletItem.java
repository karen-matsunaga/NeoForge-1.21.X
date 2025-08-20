package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.component.FoundBlock;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.standardLiteral;

public class DataTabletItem extends Item {
    public DataTabletItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack playerItem = player.getItemInHand(hand);
        FoundBlock foundBlockData = playerItem.get(ModDataComponentTypes.FOUND_BLOCK.get());
        if (foundBlockData != null) { playerItem.set(ModDataComponentTypes.FOUND_BLOCK.get(), null); }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        FoundBlock foundBlockData = stack.get(ModDataComponentTypes.FOUND_BLOCK.get());
        return foundBlockData != null;
    }

    @Override
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext context,
                                @NotNull TooltipDisplay display, @NotNull Consumer<Component> consumer,
                                @NotNull TooltipFlag flag) {
        FoundBlock foundBlockData = stack.get(ModDataComponentTypes.FOUND_BLOCK.get());
        if (foundBlockData != null ) { consumer.accept(standardLiteral(foundBlockData.getOutputString())); }
        super.appendHoverText(stack, context, display, consumer, flag);
    }
}