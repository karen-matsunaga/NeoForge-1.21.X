package net.karen.mccoursemod.potion;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, MccourseMod.MOD_ID);

    // Slimey's potion register
    public static final Holder<Potion> SLIMEY_POTION = POTIONS.register("slimey_potion",
            () -> new Potion("slimey_potion", new MobEffectInstance(ModEffects.SLIMEY_EFFECT, 1200, 0)));

    // Fly's potion register
    public static final Holder<Potion> FLY_POTION = POTIONS.register("fly_potion",
            () -> new Potion("fly_potion", new MobEffectInstance(ModEffects.FLY_EFFECT, 2000, 0)));

    public static final Holder<Potion> FLY_II_POTION = POTIONS.register("fly_ii_potion",
            () -> new Potion("fly_potion", new MobEffectInstance(ModEffects.FLY_EFFECT, 4000, 1)));

    // Haste's potion register
    public static final Holder<Potion> HASTE_POTION = POTIONS.register("haste_potion",
            () -> new Potion("haste_potion", new MobEffectInstance(MobEffects.HASTE, 2000, 0)));

    // Nothing's potion register
    public static final Holder<Potion> NOTHING_POTION = POTIONS.register("nothing_potion",
            () -> new Potion("nothing_potion", new MobEffectInstance(ModEffects.NOTHING_EFFECT, 2000, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}