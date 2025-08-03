package net.karen.mccoursemod.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import static net.neoforged.neoforge.common.NeoForgeMod.CREATIVE_FLIGHT;

public class FlyEffect extends MobEffect {
    protected FlyEffect(MobEffectCategory category, int color) { super(category, color); }

    @Override
    public boolean applyEffectTick(@NotNull ServerLevel level, @NotNull LivingEntity entity, int amplifier) { // Fly effect APPLIED
        if (entity instanceof Player player && !player.level().isClientSide()) {
            Abilities abilities = player.getAbilities();
            AttributeMap attributes = player.getAttributes();
            boolean flyAbilities = abilities.pack().mayFly();
            boolean fly = attributes.hasAttribute(CREATIVE_FLIGHT);
            boolean effect = player.hasEffect(ModEffects.FLY_EFFECT);
            if (!fly && !flyAbilities && effect) {
                abilities.setFlyingSpeed(0.05f + (0.02f * amplifier));
                player.onUpdateAbilities();
            }
            if (fly && flyAbilities && !effect && !player.isCreative()) {
                abilities.flying = false;
                abilities.setFlyingSpeed(0.05F);
                player.onUpdateAbilities();
            }
        }
        return super.applyEffectTick(level, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) { return true; }
}