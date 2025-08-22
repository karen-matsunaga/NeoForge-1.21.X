package net.karen.mccoursemod.entity.client;

import net.karen.mccoursemod.entity.variant.GeckoVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class GeckoRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public GeckoVariant variant;
}