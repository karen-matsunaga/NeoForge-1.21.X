package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public abstract class BowItemMixin {
    @Inject(method = "getPowerForTime", at = @At("HEAD"), cancellable = true)
    private static void injectPower(int charge, CallbackInfoReturnable<Float> cir) {
        ItemStack stack = Utils.getLastBowUsed();
        if (stack != null) {
            ClientLevel minecraftLevel = Minecraft.getInstance().level;
            if (minecraftLevel != null) {
                HolderLookup.RegistryLookup<Enchantment> ench =
                             minecraftLevel.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                int level = stack.getEnchantmentLevel(ench.getOrThrow(ModEnchantments.LIGHTSTRING));
                if (level > 0) {
                    float speedMultiplier = 1.0f + (0.25f * level); // 25% per level
                    float power = charge / (20.0f / speedMultiplier); // Simulates faster loading
                    cir.setReturnValue(Math.min(power, 1.0f));
                }
            }
        }
    }
}