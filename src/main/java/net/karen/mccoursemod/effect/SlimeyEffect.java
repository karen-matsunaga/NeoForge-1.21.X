package net.karen.mccoursemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SlimeyEffect extends MobEffect {
    public SlimeyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) { return true; }
}