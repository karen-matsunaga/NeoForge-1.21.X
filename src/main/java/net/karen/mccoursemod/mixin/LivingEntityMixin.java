package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.effect.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "canAttack*", at = @At("HEAD"), cancellable = true)
    private void livingPlayerAttack(LivingEntity living, CallbackInfoReturnable<Boolean> cir) {
        if (living instanceof Player player) { // Monsters etc. not attack Player
            if (player.hasEffect(ModEffects.NOTHING_EFFECT)) {
                cir.setReturnValue(false);
            }
        }
    }
}