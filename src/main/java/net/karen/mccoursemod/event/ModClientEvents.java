package net.karen.mccoursemod.event;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.entity.ModBlockEntities;
import net.karen.mccoursemod.block.entity.renderer.PedestalBlockEntityRenderer;
import net.karen.mccoursemod.component.AlternateTexture;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.entity.client.*;
import net.karen.mccoursemod.fluid.BaseFluidType;
import net.karen.mccoursemod.fluid.ModFluidTypes;
import net.karen.mccoursemod.fluid.ModFluids;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.network.MccourseModBottlePacketPayload;
import net.karen.mccoursemod.network.MccourseModElevatorPacketPayload;
import net.karen.mccoursemod.particle.BismuthParticles;
import net.karen.mccoursemod.particle.BouncyBallsParticles;
import net.karen.mccoursemod.particle.ModParticles;
import net.karen.mccoursemod.screen.ModMenuTypes;
import net.karen.mccoursemod.screen.custom.GrowthChamberScreen;
import net.karen.mccoursemod.screen.custom.PedestalScreen;
import net.karen.mccoursemod.util.ImageTooltipComponent;
import net.karen.mccoursemod.util.KeyBinding;
import net.karen.mccoursemod.util.MultiImageTooltipComponent;
import net.karen.mccoursemod.worldgen.tree.ModWoodTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import java.util.function.Function;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = MccourseMod.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = MccourseMod.MOD_ID, value = Dist.CLIENT)
public class ModClientEvents {
    public ModClientEvents(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        MccourseMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        MccourseMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        // ** CUSTOM entity mob **
        // GECKO
        EntityRenderers.register(ModEntities.GECKO.get(), GeckoRenderer::new);
        // RHINO
        EntityRenderers.register(ModEntities.RHINO.get(), RhinoRenderer::new);
        // ** CUSTOM Throwable Projectiles **
        EntityRenderers.register(ModEntities.TOMAHAWK.get(), TomahawkProjectileRenderer::new);
        EntityRenderers.register(ModEntities.TORCH_BALL.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.BOUNCY_BALLS.get(), ThrownItemRenderer::new);
        // ** CUSTOM Sittable blocks **
        EntityRenderers.register(ModEntities.CHAIR_ENTITY.get(), ChairRenderer::new);
        event.enqueueWork(() -> {
            // ** CUSTOM Wood types -> Sign and Hanging Sign **
            Sheets.addWoodType(ModWoodTypes.WALNUT);
            // ** CUSTOM Fluid **
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(), ChunkSectionLayer.TRANSLUCENT);
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(), ChunkSectionLayer.TRANSLUCENT);
        });
    }

    // CUSTOM EVENT - Register all custom particles
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.BISMUTH_PARTICLES.get(), BismuthParticles.Provider::new);
        event.registerSpriteSet(ModParticles.BOUNCY_BALLS_PARTICLES.get(), BouncyBallsParticles.Provider::new);
    }

    // CUSTOM EVENT - Register all custom image tooltip
    @SubscribeEvent
    public static void registerTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ImageTooltipComponent.class, Function.identity());
        event.register(MultiImageTooltipComponent.class, Function.identity());
    }

    // CUSTOM EVENT - Register all custom network (CLIENT -> SERVER)
    @SubscribeEvent
    public static void registerNetwork(RegisterPayloadHandlersEvent event) {
        // Network version
        final PayloadRegistrar registrar = event.registrar("1");
        // Network -> Mccourse Mod Elevator block
        registrar.playToServer(MccourseModElevatorPacketPayload.TYPE,
                               // STREAM CODEC
                               MccourseModElevatorPacketPayload.STREAM_CODEC,
                               // Server Payload Handler
                               MccourseModElevatorPacketPayload::onMccourseModElevatorServerPayloadHandler);

        // Network -> Mccourse Mod Bottle item
        registrar.playToServer(MccourseModBottlePacketPayload.TYPE,
                               // STREAM CODEC
                               MccourseModBottlePacketPayload.STREAM_CODEC,
                               // Server Payload Handler
                               MccourseModBottlePacketPayload::onMccourseModBottleServerPayloadHandler);
    }

    // CUSTOM EVENT - Register all custom Key Inputs
    @SubscribeEvent
    public static void registerKeyInput(RegisterKeyMappingsEvent event) {
        event.register(KeyBinding.GLOWING_MOBS_KEY.get()); // Glowing Mobs custom enchantment
        event.register(KeyBinding.GLOWING_BLOCKS_KEY.get()); // Glowing Blocks custom enchantment
        event.register(KeyBinding.MCCOURSE_BOTTLE_STORED_KEY.get()); // Mccourse Bottle stored 10 levels
        event.register(KeyBinding.MCCOURSE_BOTTLE_RESTORED_KEY.get()); // Mccourse Bottle restored 10 levels
        event.register(KeyBinding.UNLOCK_KEY.get()); // Unlock custom enchantment
    }

    // CUSTOM EVENT - Register all custom item model property conditionals
    @SubscribeEvent
    public static void registerConditionalProperties(RegisterConditionalItemModelPropertyEvent event) {
        // The name to reference as the type
        event.register(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "has_data_info"),
                       AlternateTexture.MAP_CODEC); // The map codec
    }

    // CUSTOM EVENT - Register all custom bows zoom
    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        Player player = event.getPlayer();
        if (player.isUsingItem() && player.getUseItem().getItem() == ModItems.KAUPEN_BOW.get()) {
            float fovModifier = 1f;
            int ticksUsingItem = player.getTicksUsingItem();
            float deltaTicks = (float) ticksUsingItem / 20f;
            if (deltaTicks > 1f) { deltaTicks = 1f; }
            else { deltaTicks *= deltaTicks; }
            fovModifier *= 1f - deltaTicks * 0.15f;
            event.setNewFovModifier(fovModifier);
        }
    }

    // CUSTOM EVENT - Registry all custom entity renderer layers
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // CUSTOM Gecko mob
        // GECKO
        event.registerLayerDefinition(GeckoModel.LAYER_LOCATION, GeckoModel::createBodyLayer);
        // RHINO
        event.registerLayerDefinition(RhinoModel.LAYER_LOCATION, RhinoModel::createBodyLayer);
        // CUSTOM Throwable Projectiles
        event.registerLayerDefinition(TomahawkProjectileModel.LAYER_LOCATION, TomahawkProjectileModel::createBodyLayer);
    }

    // CUSTOM EVENT - Registry all custom block entity renderers
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        // ** CUSTOM Block entity renderers **
        event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);
        // ** CUSTOM Sign and Hanging Sing renderers **
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    // CUSTOM EVENT - Registry all custom screens
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.PEDESTAL_MENU.get(), PedestalScreen::new);
        event.register(ModMenuTypes.GROWTH_CHAMBER_MENU.get(), GrowthChamberScreen::new);
    }

    // CUSTOM EVENT - Registry all custom fluid types
    @SubscribeEvent
    public static void registerFluids(RegisterClientExtensionsEvent event) {
        event.registerFluidType(((BaseFluidType) ModFluidTypes.SOAP_WATER_FLUID_TYPE.get())
                                .getClientFluidTypeExtensions(),
                                ModFluidTypes.SOAP_WATER_FLUID_TYPE.get());
    }
}