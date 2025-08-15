package net.karen.mccoursemod;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.AlternateTexture;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.entity.client.GeckoModel;
import net.karen.mccoursemod.entity.client.GeckoRenderer;
import net.karen.mccoursemod.entity.client.TomahawkProjectileModel;
import net.karen.mccoursemod.entity.client.TomahawkProjectileRenderer;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.network.MccourseModBottlePacketPayload;
import net.karen.mccoursemod.network.MccourseModElevatorPacketPayload;
import net.karen.mccoursemod.particle.BismuthParticles;
import net.karen.mccoursemod.particle.ModParticles;
import net.karen.mccoursemod.util.ImageTooltipComponent;
import net.karen.mccoursemod.util.KeyBinding;
import net.karen.mccoursemod.util.MultiImageTooltipComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import java.util.function.Function;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = MccourseMod.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = MccourseMod.MOD_ID, value = Dist.CLIENT)
public class MccourseModClient {
    public MccourseModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        MccourseMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        MccourseMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        // Custom mob
        EntityRenderers.register(ModEntities.GECKO.get(), GeckoRenderer::new);
        // CUSTOM Throwable Projectiles
        EntityRenderers.register(ModEntities.TOMAHAWK.get(), TomahawkProjectileRenderer::new);
        // Custom block render type
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GOJI_BERRY_BUSH.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RADISH_CROP.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLOODWOOD_SAPLING.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BISMUTH_DOOR.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BISMUTH_TRAPDOOR.get(), ChunkSectionLayer.CUTOUT);
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.BISMUTH_PARTICLES.get(), BismuthParticles.Provider::new);
    }

    // CUSTOM EVENT - Register custom image tooltip
    @SubscribeEvent
    public static void registerTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ImageTooltipComponent.class, Function.identity());
        event.register(MultiImageTooltipComponent.class, Function.identity());
    }

    // CUSTOM EVENT - Register network (CLIENT -> SERVER)
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

    // CUSTOM EVENT - Register custom Key Input
    @SubscribeEvent
    public static void registerKeyInput(RegisterKeyMappingsEvent event) {
        event.register(KeyBinding.GLOWING_MOBS_KEY.get()); // Glowing Mobs custom enchantment
        event.register(KeyBinding.GLOWING_BLOCKS_KEY.get()); // Glowing Blocks custom enchantment
        event.register(KeyBinding.MCCOURSE_BOTTLE_STORED_KEY.get()); // Mccourse Bottle stored 10 levels
        event.register(KeyBinding.MCCOURSE_BOTTLE_RESTORED_KEY.get()); // Mccourse Bottle restored 10 levels
        event.register(KeyBinding.UNLOCK_KEY.get()); // Unlock custom enchantment
    }

    // CUSTOM EVENT - On the MOD EVENT BUS only on the physical client
    @SubscribeEvent
    public static void registerConditionalProperties(RegisterConditionalItemModelPropertyEvent event) {
        // The name to reference as the type
        event.register(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "has_data_info"),
                       // The map codec
                       AlternateTexture.MAP_CODEC);
    }

    // CUSTOM EVENT - Kaupen Bow zoom
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
        event.registerLayerDefinition(GeckoModel.LAYER_LOCATION, GeckoModel::createBodyLayer);
        // CUSTOM Throwable Projectiles
        event.registerLayerDefinition(TomahawkProjectileModel.LAYER_LOCATION, TomahawkProjectileModel::createBodyLayer);
    }
}