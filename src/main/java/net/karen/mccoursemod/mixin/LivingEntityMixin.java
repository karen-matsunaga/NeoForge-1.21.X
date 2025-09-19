package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.effect.ModEffects;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
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
public abstract class LivingEntityMixin extends Entity {
    @Shadow private Optional<BlockPos> lastClimbablePos;
    @Shadow public abstract boolean hasEffect(Holder<MobEffect> effect);

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

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
        if (!this.isSpectator() && this.hasEffect(ModEffects.SLIMEY_EFFECT)) {
            AABB bb = this.getBoundingBox();
            Iterable<BlockPos> blockPosCoordinates =
                    BlockPos.betweenClosed(Mth.floor(bb.minX), Mth.floor(bb.minY), Mth.floor(bb.minZ),
                                           Mth.ceil(bb.maxX), Mth.ceil(bb.maxY), Mth.ceil(bb.maxZ));
            for (BlockPos pos : blockPosCoordinates) {
                BuiltInRegistries.BLOCK.forEach(block -> {
                    for (Direction direction : Direction.values()) {
                        BlockPos blockPos = pos.relative(direction);
                        BlockState state = this.level().getBlockState(blockPos);
                        if (state.is(BlockTags.CLIMBABLE)) { return; }
                        if (!state.isAir() && state.is(block)) {
                            this.lastClimbablePos = Optional.of(blockPos);
                            cir.setReturnValue(true);
                            return;
                        }
                    }
                });
            }
        }
    }
}