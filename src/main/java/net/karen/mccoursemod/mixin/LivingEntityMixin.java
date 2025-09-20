package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.effect.ModEffects;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Optional;
import static net.karen.mccoursemod.util.Utils.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow private Optional<BlockPos> lastClimbablePos;

    // DEFAULT METHOD - MONSTERS not attack Player
    @Inject(method = "canAttack*", at = @At("HEAD"), cancellable = true)
    private void livingPlayerAttack(LivingEntity living, CallbackInfoReturnable<Boolean> cir) {
        if (living instanceof Player player) { // Monsters etc. not attack Player
            HolderLookup.RegistryLookup<Enchantment> ench =
                  player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            if (player.hasEffect(ModEffects.NOTHING_EFFECT) ||
                hasEnchant(ench.getOrThrow(ModEnchantments.PEACEFUL_MOBS), player) > 0) {
                cir.setReturnValue(false);
            }
        }
    }

    // Climbable Logs by unilock: https://github.com/unilock/ClimbableLogs/blob/xplat/1.21/LICENSE
    // Distributed under MIT
    // DEFAULT METHOD - Block Climbable
    @Inject(method = "onClimbable", at = @At("RETURN"), cancellable = true)
    public void slimeyEffectClimb(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!entity.isSpectator() && entity.hasEffect(ModEffects.SLIMEY_EFFECT)) {
            // Block Bounding Box
            AABB bb = entity.getBoundingBox();
            Iterable<BlockPos> blockPosCoordinates =
                    BlockPos.betweenClosed(Mth.floor(bb.minX), Mth.floor(bb.minY), Mth.floor(bb.minZ),
                                           Mth.floor(bb.maxX), Mth.floor(bb.maxY), Mth.floor(bb.maxZ));
            // Found Block climb
            for (BlockPos pos : blockPosCoordinates) {
                for (Direction dir : Direction.Plane.HORIZONTAL) { // Direction X and Z
                    BlockPos adjacent = pos.relative(dir);
                    BlockState state = entity.level().getBlockState(adjacent);
                    if (state.is(ModTags.Blocks.SLIMEY_EFFECT_BLOCKS)) { // Compatible blocks
                        this.lastClimbablePos = Optional.of(adjacent);
                        cir.setReturnValue(true);
                        return;
                    }
                }
            }
        }
    }
}