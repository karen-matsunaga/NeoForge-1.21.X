package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.SculkShriekerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SculkShriekerBlockEntity.class)
public class SculkShriekerBlockEntityMixin {
    @Inject(method = "tryShriek", at = @At("HEAD"), cancellable = true)
    private void onTryShriek(ServerLevel level, ServerPlayer player, CallbackInfo cir) {
        if (player != null && player.hasEffect(ModEffects.NOTHING_EFFECT)) {
            cir.cancel(); // Cancels Sculk Shrieker block activation
        }
    }
}