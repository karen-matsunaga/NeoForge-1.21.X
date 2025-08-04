package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.CustomTooltip;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.util.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.ArrayList;
import java.util.List;
import static net.karen.mccoursemod.item.custom.SpecialEffectItem.getMultiplier;
import static net.karen.mccoursemod.util.ChatUtils.*;

@Mixin(value = ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract String toString();

    @Inject(method = "getTooltipLines", at = @At("RETURN"), cancellable = true)
    private void getTooltipLines(Item.TooltipContext context, Player player,
                                 TooltipFlag flag, CallbackInfoReturnable<List<Component>> cir) {
        ItemStack stack = (ItemStack) (Object) this; // Get all blocks, items, etc.
        List<Component> tooltip = new ArrayList<>(cir.getReturnValue()); // Old tooltip
        if (stack.is(ModBlocks.MAGIC.get().asItem())) { // Item checked is Magic block
            Component original = tooltip.getFirst(), // Original tooltip line 0
                       colored = original.copy().withStyle(style -> style.withColor(0x00ff00));
            tooltip.set(0, colored); // Change only the name (first line of the tooltip) -> Color not appears on screen
            tooltip.add(standardTranslatable("tooltip.mccoursemod.magic_block.tooltip")); // Added more information about block
        }
        // AUTO SMELT, MAGNET, MORE ORES and RAINBOW custom effects
        if (stack.has(ModDataComponentTypes.MAGNET.get())) {
            tooltip.add(componentLiteral("Magnet x" +
                        getMultiplier(stack, ModDataComponentTypes.MAGNET.get()) + "!", darkGray));
        }
        if (stack.has(ModDataComponentTypes.RAINBOW.get())) {
            tooltip.add(componentLiteral("Rainbow x" +
                        getMultiplier(stack, ModDataComponentTypes.RAINBOW.get()) + "!", purple));
        }
        if (stack.has(ModDataComponentTypes.AUTO_SMELT.get())) {
            tooltip.add(componentLiteral("Auto Smelt x" +
                        getMultiplier(stack, ModDataComponentTypes.AUTO_SMELT.get()) + "!", gold));
            tooltip.add(componentTranslatable("tooltip.mccoursemod.auto_smelt.tooltip", gold));
            Component test2 = Component.literal("\u00a1");
            tooltip.add(test2);
            CustomTooltip test = new CustomTooltip(Component.nullToEmpty("[Auto Smelt]"));
            stack.set(ModDataComponentTypes.CUSTOM_TOOLTIP.get(), test);
            stack.get(ModDataComponentTypes.CUSTOM_TOOLTIP.get());
        }
        if (stack.has(ModDataComponentTypes.MORE_ORES.get())) {
            tooltip.add(componentLiteral("More Ores x" +
                        getMultiplier(stack, ModDataComponentTypes.MORE_ORES.get()), darkAqua));
        }
        cir.setReturnValue(tooltip); // New tooltip
    }

    // Lapis Lazuli consumption is blocked
    @Inject(method = "shrink", at = @At("HEAD"), cancellable = true)
    private void preventLapisShrink(int decrement, CallbackInfo ci) {
        ItemStack self = (ItemStack) (Object) this;
        if (self.is(Items.LAPIS_LAZULI) && Utils.IGNORE_LAPIS) { ci.cancel(); }
    }
}