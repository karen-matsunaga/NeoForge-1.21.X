package net.karen.mccoursemod.fluid;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModFluids {
    // Registry all custom fluids
    public static final DeferredRegister<Fluid> FLUIDS =
           DeferredRegister.create(BuiltInRegistries.FLUID, MccourseMod.MOD_ID);

    // Registry all fluids source and fluids flowing
    public static final Supplier<FlowingFluid> SOURCE_SOAP_WATER =
           FLUIDS.register("soap_water_fluid",
           () -> new BaseFlowingFluid.Source(ModFluids.SOAP_WATER_FLUID_PROPERTIES));

    public static final Supplier<FlowingFluid> FLOWING_SOAP_WATER =
           FLUIDS.register("flowing_soap_water_fluid",
           () -> new BaseFlowingFluid.Flowing(ModFluids.SOAP_WATER_FLUID_PROPERTIES));

    // ** CUSTOM FLUID block **
    // Soap Water Block custom fluid
    public static final DeferredBlock<LiquidBlock> SOAP_WATER_BLOCK =
           ModBlocks.BLOCKS.register("soap_water_block",
           () -> new LiquidBlock(ModFluids.SOURCE_SOAP_WATER.get(), BlockBehaviour.Properties
                                                                                  .ofFullCopy(Blocks.WATER)
                                                                                  .noLootTable()
                                                                                  .setId(ResourceKey.create(Registries.BLOCK,
                                                                                         ResourceLocation.fromNamespaceAndPath(
                                                                                         MccourseMod.MOD_ID,
                                                                                         "soap_water_block")))));

    // ** CUSTOM bucket fluid **
    // Soap Water Bucket custom fluid
    public static final DeferredItem<Item> SOAP_WATER_BUCKET =
           ModItems.ITEMS.registerItem("soap_water_bucket",
           (properties) -> new BucketItem(ModFluids.SOURCE_SOAP_WATER.get(),
                                                    properties.craftRemainder(Items.BUCKET).stacksTo(1)
                                                              .setId(ResourceKey.create(Registries.ITEM,
                                                                     ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                     "soap_water_bucket")))));

    // Registry all custom fluids when to inside on custom fluid
    public static final BaseFlowingFluid.Properties SOAP_WATER_FLUID_PROPERTIES =
           new BaseFlowingFluid.Properties(ModFluidTypes.SOAP_WATER_FLUID_TYPE, SOURCE_SOAP_WATER, FLOWING_SOAP_WATER)
                               .slopeFindDistance(2).levelDecreasePerBlock(1)
                               .block(ModFluids.SOAP_WATER_BLOCK).bucket(ModFluids.SOAP_WATER_BUCKET);

    // CUSTOM METHOD - Registry all custom fluids on event
    public static void register(IEventBus eventBus) { FLUIDS.register(eventBus); }
}