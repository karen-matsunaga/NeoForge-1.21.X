package net.karen.mccoursemod.fluid;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;
import java.util.function.Supplier;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = ResourceLocation.parse("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.parse("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = ResourceLocation.parse("block/water_overlay");

    // Registry all fluid types
    public static final DeferredRegister<FluidType> FLUID_TYPES =
           DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, MccourseMod.MOD_ID);

    // Registry all custom fluid colors types
    public static final Supplier<FluidType> SOAP_WATER_FLUID_TYPE =
           registerFluidType("soap_water_fluid",
                             new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL, 0xA1E038D0,
                                               new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                                               FluidType.Properties.create().lightLevel(2).viscosity(5).density(15)));

    // CUSTOM METHOD - Register all custom fluid type
    private static Supplier<FluidType> registerFluidType(String name, FluidType fluidType) {
        return FLUID_TYPES.register(name, () -> fluidType);
    }

    // CUSTOM METHOD - Registry all custom fluid types on event
    public static void register(IEventBus eventBus) { FLUID_TYPES.register(eventBus); }
}