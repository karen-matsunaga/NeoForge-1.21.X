package net.karen.mccoursemod.entity.client;

import net.karen.mccoursemod.entity.variant.RhinoVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class RhinoRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState;
    public final AnimationState sitAnimationState;
    public final AnimationState walkAnimationState;
    public final AnimationState attackAnimationState;
    public RhinoVariant variant;

    public RhinoRenderState() {
        this.sitAnimationState = new AnimationState();
        this.idleAnimationState = new AnimationState();
        this.walkAnimationState = new AnimationState();
        this.attackAnimationState = new AnimationState();
    }
}