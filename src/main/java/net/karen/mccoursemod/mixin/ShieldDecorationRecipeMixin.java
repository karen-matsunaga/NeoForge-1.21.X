package net.karen.mccoursemod.mixin;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.neoforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShieldDecorationRecipe.class)
public class ShieldDecorationRecipeMixin {
    // DEFAULT METHOD - Shield recipe matches
    @Inject(method = "matches*", at = @At("HEAD"), cancellable = true)
    private void shieldRecipeMatches(CraftingInput input, Level level, CallbackInfoReturnable<Boolean> cir) {
        if (input.ingredientCount() != 2) {
            cir.setReturnValue(false);
            return;
        }
        boolean hasBanner = false;
        boolean hasShield = false;
        for (int i = 0; i < input.size(); ++i) {
            ItemStack itemstack = input.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof BannerItem) {
                    if (hasBanner) {
                        cir.setReturnValue(false);
                        return;
                    }
                    hasBanner = true;
                }
                else {
                    if (!itemstack.is(Tags.Items.TOOLS_SHIELD)) {
                        cir.setReturnValue(false);
                        return;
                    }
                    if (hasShield) {
                        cir.setReturnValue(false);
                        return;
                    }
                    BannerPatternLayers layers = itemstack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
                    if (!layers.layers().isEmpty()) {
                        cir.setReturnValue(false);
                        return;
                    }
                    hasShield = true;
                }
            }
        }
        cir.setReturnValue(hasBanner && hasShield);
    }

    // DEFAULT METHOD - Shield recipe assemble
    @Inject(method = "assemble(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;",
            at = @At("HEAD"), cancellable = true)
    private void assemble(CraftingInput input, HolderLookup.Provider provider, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack banner = ItemStack.EMPTY;
        ItemStack shield = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof BannerItem) { banner = stack; }
                else if (stack.is(Tags.Items.TOOLS_SHIELD)) { shield = stack.copy(); }
            }
        }
        if (!shield.isEmpty() && banner.getItem() instanceof BannerItem bannerItem) {
            shield.set(DataComponents.BANNER_PATTERNS, banner.get(DataComponents.BANNER_PATTERNS));
            shield.set(DataComponents.BASE_COLOR, bannerItem.getColor());
            cir.setReturnValue(shield);
        }
    }
}