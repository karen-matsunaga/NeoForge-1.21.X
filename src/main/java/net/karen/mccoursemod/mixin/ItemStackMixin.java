package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.item.custom.TorchBallItem;
import net.karen.mccoursemod.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.*;

@Mixin(value = ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract String toString();

    @Shadow public abstract <T extends TooltipProvider>
                            void addToTooltip(DataComponentType<T> component,
                                              Item.TooltipContext context,
                                              TooltipDisplay display,
                                              Consumer<Component> consumer,
                                              TooltipFlag flag);

    @Inject(method = "getTooltipLines", at = @At("RETURN"), cancellable = true)
    private void getTooltipLines(Item.TooltipContext context, Player player,
                                 TooltipFlag flag, CallbackInfoReturnable<List<Component>> cir) {
        ItemStack stack = (ItemStack) (Object) this; // Get all blocks, items, etc.
        List<Component> tooltip = new ArrayList<>(cir.getReturnValue()); // Old tooltip
        Level level = Minecraft.getInstance().level;
        // ** CUSTOM BLOCKS **
        // Item checked is MAGIC block + Added more information about MAGIC block
        if (stack.is(ModBlocks.MAGIC.get().asItem())) {
            Component original = tooltip.getFirst(), // Original tooltip line 0
                       colored = original.copy().withStyle(style -> style.withColor(0x00ff00));
            tooltip.set(0, colored); // Change only the name (first line of the tooltip) -> Color not appears on screen
            tooltip.add(standardTranslatable("tooltip.mccoursemod.magic_block"));
        }
        // Item checked is SOUND block + Added more information about SOUND block
        if (stack.is(ModBlocks.SOUND.get().asItem())) {
            tooltip.add(standardTranslatable("tooltip.mccoursemod.sound"));
        }
        // ** CUSTOM ITEMS **
        // Added more information about GROWTH item
        if (stack.is(ModItems.GROWTH.get().asItem())) {
            tooltip.add(componentTranslatable("tooltip.mccoursemod.growth", aqua));
        }
        // TORCH BALL item
        if (stack.is(ModItems.TORCH_BALL.get().asItem())) {
            if (stack.getItem() instanceof TorchBallItem torchBall) {
                tooltip.add(componentLiteral("§6 [" + torchBall.getItemName() + "]§r" +
                                             " when hit added torch!", yellow));
            }
        }
        // ** CUSTOM ENCHANTMENTS **
        if (level != null) {
            HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            // Item checked is UNLOCK enchantment
            if (Utils.toolEnchant(ench, ModEnchantments.UNLOCK, stack) > 0) {
                if (stack.has(ModDataComponentTypes.UNLOCK)) {
                    Boolean values = stack.get(ModDataComponentTypes.UNLOCK);
                    if (values != null) {
                        boolean locked = values; // Locked Data Component change stage
                        tooltip.add(standardLiteral(locked ? "§c\uD83D\uDD12 Item locked! " +
                                                              "§7- Press §eV§7 §cto unlock " + locked
                                                           : "§a\uD83D\uDD13 Item unlocked! " +
                                                             "§7- Press §eV§7 §ato lock " + !locked));
                    }
                }
                else { stack.set(ModDataComponentTypes.UNLOCK, false); }
            }
        }
        cir.setReturnValue(tooltip); // New tooltip
    }

    // DEFAULT METHOD - Added custom tooltip
    @Inject(method="addDetailsToTooltip", at = @At("HEAD"))
    private void addDetailsToTooltip$mccoursemod(Item.TooltipContext context, TooltipDisplay display,
                                                 Player player, TooltipFlag flag,
                                                 Consumer<Component> consumer, CallbackInfo ci) {
       this.addToTooltip(ModDataComponentTypes.SHIFT_TOOLTIP.get(), context, display, consumer, flag);
       this.addToTooltip(ModDataComponentTypes.ITEM_TOOLTIP.get(), context, display, consumer, flag);
       this.addToTooltip(ModDataComponentTypes.HAMMER_TOOLTIP.get(), context, display, consumer, flag);
    }

    // DEFAULT METHOD - LAPIS LAZULI consumption is blocked
    @Inject(method = "shrink", at = @At("HEAD"), cancellable = true)
    private void preventLapisShrink(int decrement, CallbackInfo ci) {
        ItemStack self = (ItemStack) (Object) this;
        if (self.is(Items.LAPIS_LAZULI) && Utils.IGNORE_LAPIS) { ci.cancel(); } // Ignore original method
    }
}