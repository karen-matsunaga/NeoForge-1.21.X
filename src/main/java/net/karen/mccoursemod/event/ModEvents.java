package net.karen.mccoursemod.event;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.command.DeleteHomeCommand;
import net.karen.mccoursemod.command.ListHomesCommand;
import net.karen.mccoursemod.command.ReturnHomeCommand;
import net.karen.mccoursemod.command.SetHomeCommand;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.effect.ModEffects;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.entity.custom.GeckoEntity;
import net.karen.mccoursemod.entity.custom.RhinoEntity;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.item.custom.HammerItem;
import net.karen.mccoursemod.item.custom.LevelChargerGenericItem;
import net.karen.mccoursemod.item.custom.LevelChargerSpecifItem;
import net.karen.mccoursemod.item.custom.MccourseModBottleItem;
import net.karen.mccoursemod.network.LevelChargerInventorySlotPacketPayload;
import net.karen.mccoursemod.network.MccourseModBottlePacketPayload;
import net.karen.mccoursemod.network.MccourseModElevatorPacketPayload;
import net.karen.mccoursemod.network.UnlockEnchantmentPacketPayload;
import net.karen.mccoursemod.potion.ModPotions;
import net.karen.mccoursemod.util.*;
import net.karen.mccoursemod.villager.ModVillagers;
import net.karen.mccoursemod.worldgen.dimension.ModDimensions;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Camera;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.server.command.ConfigCommand;
import org.lwjgl.glfw.GLFW;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import static net.karen.mccoursemod.enchantment.custom.AutoSmeltEnchantmentEffect.autoSmeltEnch;
import static net.karen.mccoursemod.enchantment.custom.MoreOresEnchantmentEffect.moreOresEnch;
import static net.karen.mccoursemod.enchantment.custom.RainbowEnchantmentEffect.rainbowEnch;
import static net.karen.mccoursemod.util.ChatUtils.*;
import static net.karen.mccoursemod.util.Utils.*;

@EventBusSubscriber(modid = MccourseMod.MOD_ID)
public class ModEvents {
    // CUSTOM EVENT - Hammer Item
    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();
        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) { return; }
            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(hammer.getRadius(), initialBlockPos, serverPlayer)) {
                if (pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }
                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    // Credits by nanite (Just Hammers) - https://github.com/nanite/JustHammers/blob/main/LICENSE.md
    @SubscribeEvent
    public static void onWorldRenderLast(RenderLevelStageEvent.AfterTranslucentBlocks event) {
        Minecraft instance = Minecraft.getInstance();
        ClientLevel level = instance.level;
        Camera camera = event.getCamera();
        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource.BufferSource bufferSource = instance.renderBuffers().bufferSource();
        BlockBoxRender.render(level, camera, poseStack, bufferSource);
    }

    // CUSTOM EVENT - Home's commands -> Register all custom commands
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new SetHomeCommand(event.getDispatcher()); // SET HOME command
        new ReturnHomeCommand(event.getDispatcher()); // RETURN HOME command
        new DeleteHomeCommand(event.getDispatcher()); // DELETE HOME command
        new ListHomesCommand(event.getDispatcher()); // LIST ALL HOMES command
        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) { // If player dies is respawned where saved the SET HOME
        Player oldPlayer = event.getEntity(); // OLD player
        Player newPlayer = event.getOriginal(); // NEW player
        Optional<int[]> newPlayerData = newPlayer.getPersistentData().getIntArray("mccoursemod.homepos");
        newPlayerData.ifPresent(pos -> oldPlayer.getPersistentData().putIntArray("mccoursemod.homepos", pos));
    }

    // CUSTOM EVENT - Living Damage Event -> Sheep's poison effect
    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Sheep sheep && event.getSource().getDirectEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() == Items.END_ROD) {
                player.displayClientMessage(standardLiteral(player.getName().getString() +
                                            " just hit a sheep with an END ROD? YOU SICK FRICK!"), true);
                sheep.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 6));
                player.getMainHandItem().shrink(1);
            }
        }
    }

    // CUSTOM EVENT - Registry all custom potion recipes
    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        // SLIMEY POTION
        builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION);
        // FLY POTION
        builder.addMix(Potions.AWKWARD, Items.EMERALD, ModPotions.FLY_POTION);
        // FLY II POTION
        builder.addMix(ModPotions.FLY_POTION, Items.EMERALD_BLOCK, ModPotions.FLY_II_POTION);
        // NOTHING POTION
        builder.addMix(Potions.AWKWARD, Items.GLOWSTONE, ModPotions.NOTHING_POTION);
        // HASTE POTION
        builder.addMix(Potions.AWKWARD, Items.CARROT, ModPotions.HASTE_POTION);
    }

    // CUSTOM EVENT - Registry all custom entities attributes
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        // GECKO ATTRIBUTES
        event.put(ModEntities.GECKO.get(), GeckoEntity.createAttributes().build());
        // RHINO ATTRIBUTES
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
    }

    // CUSTOM EVENT - Registry all custom entities spawn placements
    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        // SPAWN GECKO
        event.register(ModEntities.GECKO.get(), SpawnPlacementTypes.ON_GROUND,
                       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules,
                       RegisterSpawnPlacementsEvent.Operation.REPLACE);
        // SPAWN RHINO
        event.register(ModEntities.RHINO.get(), SpawnPlacementTypes.ON_GROUND,
                       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules,
                       RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    // CUSTOM EVENT - Villager trades
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        // Vanilla trade -> FARMER trades
        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            // Trade level one
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                               new ItemCost(Items.EMERALD, 3),
                               new ItemStack(ModItems.GOJI_BERRIES.get(), 18),
                               6, 3, 0.05F));
            // Trade level one
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                               new ItemCost(Items.DIAMOND, 12),
                               new ItemStack(ModItems.RADISH.get(), 1),
                               6, 3, 0.05F));
            // Trade level two
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                               new ItemCost(Items.ENDER_PEARL, 1),
                               new ItemStack(ModItems.RADISH_SEEDS.get(), 1),
                               2, 8, 0.05F));
        }
        // Mccourse Mod trade -> KAUPENGER trades
        if (event.getType() == ModVillagers.KAUPENGER.getKey()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            // Trade level one
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                               new ItemCost(Items.EMERALD, 2),
                               new ItemStack(ModItems.RAW_BISMUTH.get(), 18),
                               6, 3, 0.05F));
            // Trade level one
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                               new ItemCost(Items.DIAMOND, 16),
                               new ItemStack(ModItems.RADIATION_STAFF.get(), 1),
                               6, 3, 0.05F));
            // Trade level two
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                               new ItemCost(Items.ENDER_PEARL, 2),
                               new ItemStack(ModItems.BISMUTH_SWORD.get(), 1),
                               2, 8, 0.05F));
        }
        // Mccourse Mod trade -> SOUND MASTER trades
        if (event.getType() == ModVillagers.SOUND_MASTER.getKey()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            HolderLookup.RegistryLookup<Enchantment> ench = event.getRegistries().lookupOrThrow(Registries.ENCHANTMENT);
            // Trade level one
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                              new ItemCost(Items.EMERALD, 25),
                              new ItemStack(ModBlocks.SOUND.get().asItem(), 1),
                              2, 5, 0.06F));
            // Trade level two - Received Iron pickaxe with EFFICIENCY 4
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                              new ItemCost(ModItems.MCCOURSE_MOD_BOTTLE, 1)
                                          .withComponents(value ->
                                                          value.expect(ModDataComponentTypes.STORED_LEVELS.get(), 200)),
                              Optional.of(new ItemCost(Items.IRON_PICKAXE)),
                              villagerEnchantedItem(Items.IRON_PICKAXE,
                                                    Map.of(ench.getOrThrow(Enchantments.EFFICIENCY), 4)),
                              20, 100, 0.08F));
            // Trade level three - Received Enchanted Book with FORTUNE 4 + GLOWING MOBS 1
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                              // Item Cost
                              new ItemCost(ModItems.MCCOURSE_MOD_BOTTLE.get(), 1)
                                          .withComponents(value ->
                                                          value.expect(ModDataComponentTypes.STORED_LEVELS.get(), 500)),
                              // Item Cost
                              Optional.of(new ItemCost(Items.BOOK)),
                              // Item Result
                              villagerEnchantedItem(Items.ENCHANTED_BOOK,
                                                    Map.of(ench.getOrThrow(Enchantments.FORTUNE), 4,
                                                           ench.getOrThrow(ModEnchantments.GLOWING_MOBS), 1)),
                              30, 50, 1F));
        }
    }

    // CUSTOM EVENT - Wandering trades
    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();
        // Generic trades
        genericTrades.add((entity, randomSource) -> new MerchantOffer(
                           new ItemCost(Items.EMERALD, 16),
                           new ItemStack(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE.get(), 1),
                           1, 10, 0.2F));
        // Rare trades
        rareTrades.add((entity, randomSource) -> new MerchantOffer(
                        new ItemCost(Items.NETHERITE_INGOT, 1),
                        new ItemStack(ModItems.BAR_BRAWL_MUSIC_DISC.get(), 1),
                        1, 10, 0.2F));
    }

    // CUSTOM EVENT -> AUTO SMELT, MAGNET, MORE ORES, MULTIPLIER and RAINBOW custom effects
    @SubscribeEvent
    public static void onAutoSmeltMagnetMoreOresMultiplierRainbowActivated(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        LevelAccessor world = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();
        ItemStack tool = player.getMainHandItem();
        Level level = (Level) event.getLevel();
        if (tool.isEmpty()) { return; } // * PROBLEMS *
        HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        int fortune = toolEnchant(ench, Enchantments.FORTUNE, tool);
        if (!level.isClientSide() && world instanceof ServerLevel serverLevel) {
            // Adapt the drop according to the enchantment being true
            AtomicBoolean cancelVanillaDrop = new AtomicBoolean(false);
            List<ItemStack> finalDrops = new ArrayList<>(); // Items caused by enchantments are stored in the list
            int hasFortune = (fortune > 0) ? (1 + serverLevel.random.nextInt(fortune + 1)) : 1;
             // * RAINBOW ENCHANTMENT EFFECT *
            rainbowEnch(tool, state, hasFortune, finalDrops, cancelVanillaDrop);
            // * MORE ORES ENCHANTMENT EFFECT *
            moreOresEnch(tool, state, serverLevel, pos, player, finalDrops, cancelVanillaDrop, hasFortune);
            // * AUTO SMELT EFFECT *
            autoSmeltEnch(tool, state, serverLevel, pos, player, finalDrops, cancelVanillaDrop, hasFortune);
            // * MAGNETIC EFFECT *
            if (toolEnchant(ench, ModEnchantments.MAGNET, tool) > 0 && !state.isAir()) {
                if (finalDrops.isEmpty()) { // FinalDrops empty list added all items on it is
                    finalDrops.addAll(Block.getDrops(state, serverLevel, pos, null, player, tool));
                }
                finalDrops.forEach(drop -> { // FinalDrops list added on Player's inventory
                    if (!player.getInventory().add(drop)) { player.drop(drop, false); }});
                block(serverLevel, pos, Blocks.AIR, event);
                return;
            }
            if (cancelVanillaDrop.get()) { // FinalDrops list accumulate drop on world
                block(serverLevel, pos, Blocks.AIR, event);
                finalDrops.forEach(drop -> dropItem(serverLevel, pos, drop));
            }
        }
    }

    // CUSTOM EVENT - Icon tooltip
    @SubscribeEvent
    public static void onRenderTooltipGatherComponent(RenderTooltipEvent.GatherComponents event) {
        ItemStack item = event.getItemStack();
        boolean hasMoreOres = item.has(ModDataComponentTypes.MORE_ORES_ENCHANTMENT_EFFECT.get());
        List<Either<FormattedText, TooltipComponent>> elements = event.getTooltipElements(); // Item TOOLTIP
        // IMAGE TOOLTIP COMPONENT
        ChatUtils.image(elements, Items.DIAMOND_ORE, 16, 16, "More Ores Effect!", hasMoreOres);
        ChatUtils.image(elements, Items.REDSTONE_ORE, 16, 16, "More Ores Effect!", hasMoreOres);
        ChatUtils.image(elements, Items.GOLD_ORE, 16, 16, "More Ores Effect!", hasMoreOres);
        ChatUtils.image(elements, Items.IRON_ORE, 16, 16, "More Ores Effect!", hasMoreOres);
        // MULTI IMAGE TOOLTIP COMPONENT
        ChatUtils.text(elements, List.of(Items.IRON_ORE, Items.GOLD_ORE), 16, "More Ores Effect!", hasMoreOres);
    }

    // CUSTOM EVENT - RENDER on SCREEN
    @SubscribeEvent
    public static void onRenderGuiScreenOverlay(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        GuiGraphics guiGraphics = event.getGuiGraphics();
        LocalPlayer player = mc.player;
        int a = 10, b = 10;
        ItemStack itemStack = new ItemStack(Items.DIAMOND);
        guiGraphics.renderItem(itemStack, a, b);
        guiGraphics.renderItemDecorations(mc.font, itemStack, a + 20, b + 10, "§bDiamond");
        if (mc.screen == null) {
            if (player != null && mc.level != null) { // Render only when the player is in the game and not in the menu
                double x = player.getX(), y = player.getY(), z = player.getZ(); // Player x, y, z coordinates
                BlockPos pos = player.blockPosition();
                int blockLight = Utils.light(mc, LightLayer.BLOCK, pos);
                int skyLight = Utils.light(mc, LightLayer.SKY, pos);
                int totalLight = Math.max(blockLight, skyLight);
                // Text to be displayed on screen - LIGHT, SKY, BLOCK
                Component coordinate = ChatUtils.literal(x, y, z);
                Component light = ChatUtils.numbers(totalLight, skyLight, blockLight);
                guiGraphics.renderItemDecorations(mc.font, itemStack, a + 165, b + 20, coordinate.getString());
                guiGraphics.renderItemDecorations(mc.font, itemStack, a + 165, b + 30, light.getString());
            }
        }
    }

    // CUSTOM EVENT - Double Jump
    private static boolean wasJumping = false;

    @SubscribeEvent
    public static void onDoubleJumpActivated(PlayerTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || mc.level == null) { return; }
        AttributeInstance fly = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
        boolean hasEffect = player.hasEffect(ModEffects.FLY_EFFECT); // Fly effect
        MobEffectInstance level = player.getEffect(ModEffects.FLY_EFFECT); // Has fly effect
        if (player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.BISMUTH_BOOTS.get())) {
            disableFlight(player, fly);
            double x = player.getDeltaMovement().x, z = player.getDeltaMovement().z;
            boolean isJumping = mc.options.keyJump.isDown();
            if (isJumping && !wasJumping) { // Check if the pulse key is being pressed
                if (!player.onGround()) { player.setDeltaMovement(x, 0.42, z); } // Applies the boost only if not on the ground
                else { player.jumpFromGround(); } // Default jump if on the ground
            }
            wasJumping = isJumping;
        }
        else { if (level != null && hasEffect) { enableFlight(player, fly, level.getAmplifier()); } }
    }

    // CUSTOM EVENT - Fly effect
    @SubscribeEvent
    public static void onFlyEffectActivated(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide()) {
            AttributeInstance fly = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT); // Attribute
            boolean hasEffect = player.hasEffect(ModEffects.FLY_EFFECT); // Fly effect
            MobEffectInstance level = player.getEffect(ModEffects.FLY_EFFECT); // Has fly effect
            if (level != null && hasEffect) {
               int flyLevel = level.getAmplifier(); // Fly effect level
               enableFlight(player, fly, flyLevel);
            }
            else { disableFlight(player, fly); }
        }
    }

    // CUSTOM EVENT - XP BOOST custom enchantment effect -> Block + Item experience orb
    @SubscribeEvent
    public static void onBedExperiencePlayerSleep(PlayerWakeUpEvent event) { // Gain experience orb when sleep
        Player player = event.getEntity();
        Level level = player.level();
        HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        int xpBoost = Utils.hasEnchant(ench.getOrThrow(ModEnchantments.XP_BOOST).getDelegate(), player);
        if (!level.isClientSide() && xpBoost > 0) { setPlayerXP(player, level, xpBoost); }
    }

    @SubscribeEvent
    public static void onExperienceItemFished(ItemFishedEvent event) { // Gain experience orb when fished
        Player player = event.getEntity();
        Level level = player.level();
        HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        int xpBoost = Utils.hasEnchant(ench.getOrThrow(ModEnchantments.XP_BOOST).getDelegate(), player);
        if (!level.isClientSide() && xpBoost > 0) {
            if (!event.getDrops().isEmpty()) { setPlayerXP(player, level, xpBoost); }
        }
    }

    @SubscribeEvent
    public static void onExperienceBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.level();
        HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        int xpBoost = Utils.hasEnchant(ench.getOrThrow(ModEnchantments.XP_BOOST).getDelegate(), player);
        if (!level.isClientSide() && xpBoost > 0) { // Block drop xp
            BuiltInRegistries.BLOCK.forEach(block -> setPlayerXP(player, level, xpBoost)); // All blocks
        }
    }

    @SubscribeEvent
    public static void onExperienceHitMob(LivingExperienceDropEvent event) {
        if (event.getEntity() instanceof Player player) {
            Level level = player.level();
            if (event.getAttackingPlayer() != null && !level.isClientSide()) { // Attacked entities
            HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            int xpBoost = Utils.hasEnchant(ench.getOrThrow(ModEnchantments.XP_BOOST).getDelegate(), player);
                if (!level.isClientSide() && xpBoost > 0) {
                    int bonus = Math.round(event.getOriginalExperience() * (1.0F * xpBoost));
                    event.setDroppedExperience(event.getDroppedExperience() + bonus);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onExperienceMineBlockPickupFurnace(PlayerXpEvent.PickupXp event) {
        Player player = event.getEntity();
        Level level = player.level();
        HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        int xpBoost = Utils.hasEnchant(ench.getOrThrow(ModEnchantments.XP_BOOST).getDelegate(), player);
        if (!level.isClientSide() && xpBoost > 0) {
            int original = event.getOrb().getValue();
            original += Math.round(original * (1.0F * xpBoost)); // Mined blocks or Picked furnace items
            player.giveExperiencePoints(original);
        }
    }

    // CUSTOM EVENT - Block Fly custom enchantment
    @SubscribeEvent
    public static void onBlockFlyBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity(); // Entity is a player
        Level level = player.level();
        HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        int efficiency = hasEnchant(ench.getOrThrow(Enchantments.EFFICIENCY).getDelegate(), player),
              blockFly = hasEnchant(ench.getOrThrow(ModEnchantments.BLOCK_FLY).getDelegate(), player);
        newSpeed(event, blockFly > 0, player, 5); // There is Block Fly enchantment -> OLD speed * NEW speed (5)
        // There is Block Fly and Efficiency enchantments -> OLD speed * (NEW speed (5) * efficiency level)
        newSpeed(event, blockFly > 0 && efficiency > 0, player, (5 + efficiency));
    }

    // CUSTOM EVENT - IMMORTAL custom enchantment
    @SubscribeEvent
    public static void onImmortalItemToss(ItemTossEvent event) {
        ItemEntity entity = event.getEntity();
        HolderLookup.RegistryLookup<Enchantment> ench = entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> immortalEnch = ench.getOrThrow(ModEnchantments.IMMORTAL);
        if (!entity.level().isClientSide()) {
            activatedImmortalEnchantment(entity, entity.getItem(), immortalEnch);
        }
    }

    @SubscribeEvent
    public static void onImmortalItemSpawn(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        HolderLookup.RegistryLookup<Enchantment> ench = entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> immortalEnch = ench.getOrThrow(ModEnchantments.IMMORTAL);
        if (!entity.level().isClientSide()) {
            if (entity instanceof ItemEntity itemEntity) {
                activatedImmortalEnchantment(itemEntity, itemEntity.getItem(), immortalEnch);
            }
        }
    }

    @SubscribeEvent
    public static void onImmortalExplosion(ExplosionEvent.Detonate event) {
        HolderLookup.RegistryLookup<Enchantment> ench = event.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> immortalEnch = ench.getOrThrow(ModEnchantments.IMMORTAL);
        event.getAffectedEntities().removeIf(entity -> entity instanceof ItemEntity itemEntity &&
                                                             itemEntity.getItem().getEnchantmentLevel(immortalEnch) > 0);
    }

    @SubscribeEvent
    public static void onImmortalItemExpire(ItemExpireEvent event) { // Never disappears
        ItemEntity itemEntity = event.getEntity();
        HolderLookup.RegistryLookup<Enchantment> ench = itemEntity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> immortalEnch = ench.getOrThrow(ModEnchantments.IMMORTAL);
        if (itemEntity.getItem().getEnchantmentLevel(immortalEnch) > 0) {
            activatedImmortalEnchantment(itemEntity, itemEntity.getItem(), immortalEnch);
        }
    }

    @SubscribeEvent
    public static void onImmortalEntityTick(LevelTickEvent.Post event) {
        if (!event.getLevel().isClientSide()) {
            for (Entity entity : getRadiusItem(event)) {
                if (entity instanceof ItemEntity item) {
                    HolderLookup.RegistryLookup<Enchantment> ench =
                            item.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                    Holder<Enchantment> immortalEnch = ench.getOrThrow(ModEnchantments.IMMORTAL);
                    if (item.getItem().getEnchantmentLevel(immortalEnch) > 0) {
                        if (item.getY() < -64) {
                            Player nearestPlayer = event.getLevel().getNearestPlayer(item, 64); // Find the nearest player
                            if (nearestPlayer != null) { // Teleports the item to the player
                                item.teleportTo(nearestPlayer.getX(), nearestPlayer.getY() + 1, nearestPlayer.getZ());
                                // Sets the speed for "flying to player"
                                Vec3 motion = nearestPlayer.position().subtract(item.position()).normalize().scale(0.5);
                                item.setDeltaMovement(motion);
                            }
                            // If no player nearby, pick up the item as before
                            else { item.teleportTo(item.getX(), 100, item.getZ()); }
                        }
                    }
                }
            }
        }
    }

    // CUSTOM EVENT - LIGHTSTRING enchantment
    @SubscribeEvent
    public static void onLightstringStopBowUsing(LivingEntityUseItemEvent.Stop event) {
        Utils.clear(); // Clean after use
    }

    @SubscribeEvent
    public static void onLightstringFinishBowUsing(LivingEntityUseItemEvent.Finish event) {
        Utils.clear(); // Clean up if usage is finished
    }

    @SubscribeEvent
    public static void onLightstringCancelBowUsing(LivingEntityUseItemEvent.Tick event) {
        if (!(event.getEntity() instanceof Player)) { return; }
        ItemStack stack = event.getItem();
        if (!(stack.getItem() instanceof BowItem)) { return; }
        ClientLevel minecraftLevel = Minecraft.getInstance().level;
        if (minecraftLevel != null) {
            HolderLookup.RegistryLookup<Enchantment> ench =
                         minecraftLevel.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            int level = stack.getEnchantmentLevel(ench.getOrThrow(ModEnchantments.LIGHTSTRING));
            if (level <= 0) { return; }
            int newDuration = event.getDuration() - level; // Decreases usage time (ex: 20 → 15)
            event.setDuration(newDuration);
            if (event.getDuration() <= 0) { Utils.clear(); } // Ensures you don't get stuck
        }
    }

    // CUSTOM EVENT - NOTHING custom effect
    @SubscribeEvent
    public static void onNothingEffectActivated(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Warden warden) { // Checks if there is a player with the effect active nearby
            List<Player> players = warden.level().getEntitiesOfClass(Player.class, warden.getBoundingBox().inflate(32));
            for (Player player : players) {
                if (player.hasEffect(ModEffects.NOTHING_EFFECT)) {
                    event.setCanceled(true); // Prevents the Warden from spawning
                    break;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onNothingEffect(EnderManAngerEvent event) {
        Player player = event.getPlayer();
        if (player.hasEffect(ModEffects.NOTHING_EFFECT)) {
            event.setCanceled(true);
        }
    }

    // CUSTOM EVENT - Mccourse Mod Elevator advanced block
    @SubscribeEvent
    public static void onMccourseModElevatorKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player != null) { // Checks if the player is over the elevator
            BlockPos pos = BlockPos.containing(player.getX(), player.getY() - 1, player.getZ());
            if (player.level().getBlockState(pos).getBlock() == ModBlocks.MCCOURSEMOD_ELEVATOR.get()) { // Detects JUMP or SHIFT
                if (InputConstants.isKeyDown(mc.getWindow().getWindow(), GLFW.GLFW_KEY_SPACE)) {
                    ClientPacketDistributor.sendToServer(new MccourseModElevatorPacketPayload(true));
                }
                if (player.isShiftKeyDown()) {
                    ClientPacketDistributor.sendToServer(new MccourseModElevatorPacketPayload(false));
                }
            }
        }
    }

    // CUSTOM EVENT - GLOWING MOBS enchantment
    private static final Map<UUID, Boolean> glowingState = new HashMap<>(); // GLOWING MOBS state (ON/OFF)

    @SubscribeEvent
    public static void activatedGlowingMobsEnchantment(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        ItemStack helmet = has(player, EquipmentSlot.HEAD); // Player has an item on HELMET slot
        UUID playerUUID = player.getUUID(); // Player UUID -> Detected GLOWING MOBS stage
        HolderLookup.RegistryLookup<Enchantment> ench = player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        boolean isEnchanted = helmet.isEnchanted() && helmet.getEnchantmentLevel(ench.getOrThrow(ModEnchantments.GLOWING_MOBS)) > 0;
        if (!isEnchanted) { // Player hasn't GLOWING MOBS is disabled
            glowingState.remove(playerUUID);
        }
        boolean current = glowingState.getOrDefault(playerUUID, false); // GLOWING MOBS default stage is FALSE
        if (KeyBinding.GLOWING_MOBS_KEY.get().consumeClick() && KeyBinding.GLOWING_MOBS_KEY.get().isDown()) { // Press [M] key input
            if (isEnchanted) { // Player has a HELMET inputted on slot and GLOWING MOBS enchantment level
                boolean newState = !current; // Default stage is FALSE
                glowingState.put(playerUUID, newState); // Adapted "newState" of "current" stage
                glow(player, newState, "Mobs: ON!", "Mobs: OFF!"); // Toggle ON/OFF
            }
            else { // Hasn't item
                player(player, "Mobs: Enchanted helmet!", darkRed);
            }
        }
        if (current && isEnchanted) {
            // Key (Color) -> Each group represent with some color. Value (Group tag name) -> Represent as Tag.
            Map<ChatFormatting, TagKey<EntityType<?>>> entitiesTag = Map.ofEntries(
            // Monsters, Animal | Flying entities, Water animals and Villagers
            Map.entry(red, ModTags.Entities.MONSTERS), Map.entry(blue, ModTags.Entities.ANIMALS),
            Map.entry(yellow, ModTags.Entities.WATER_ANIMALS), Map.entry(darkPurple, ModTags.Entities.VILLAGER));
            entitiesTag.forEach((color, tag) -> { // Added GLOWING effect for each GROUP
                String teamName = tag.location().getPath();
                List<LivingEntity> entities = getPlayer(player, tag); // Range of GLOWING effect on mobs
                if (!entities.isEmpty()) { // Groups not empty
                    Scoreboard score = player.getScoreboard();
                    PlayerTeam team = score.getPlayerTeam(teamName);
                    // Added each entity on group with specif tag and color on entitiesTag
                    if (team == null) { team = score.addPlayerTeam(teamName); team.setColor(color); }
                    PlayerTeam finalTeam = team; // Each entity received GLOWING effect with specif color on entitiesTag
                    entities.forEach(entity -> {
                        entity.addEffect(effect(MobEffects.GLOWING, 20, 1));
                        score.addPlayerToTeam(entity.getScoreboardName(), finalTeam);
                    });
                }
            });
        }
    }

    // CUSTOM EVENT - MAGNETISM enchantment
    @SubscribeEvent
    public static void activatedMagnetismEnchantment(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (!level.isClientSide()) {
            ItemStack legging = player.getItemBySlot(EquipmentSlot.LEGS);
            HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            int enchLevel = legging.getEnchantmentLevel(ench.getOrThrow(ModEnchantments.MAGNETISM));
            // To not run all the time, only every TICK_INTERVAL = 20 ticks
            if (enchLevel <= 0 || player.tickCount % 20 != 0) { return; } // 1 second (20 ticks)
            double range = 5.0 + enchLevel * 2; // Range increases with level
            // Search for items near the player
            List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, player.getBoundingBox().inflate(range));
            for (ItemEntity itemEntity : items) {
                if (itemEntity.isRemoved() || !itemEntity.isAlive()) { continue; }
                ItemStack stack = itemEntity.getItem().copy();
                if (player.getInventory().add(stack)) {
                    itemEntity.remove(Entity.RemovalReason.DISCARDED);
                    sound(player, SoundEvents.ITEM_PICKUP, 0.2F, ((player.getRandom().nextFloat() -
                          player.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F); // Play sound
                }
            }
            // Search for experience orbs near the player
            List<ExperienceOrb> orbs = level.getEntitiesOfClass(ExperienceOrb.class, player.getBoundingBox().inflate(range));
            for (ExperienceOrb orb : orbs) {
                if (!orb.isAlive() || orb.isRemoved()) { continue; }
                int xpValue = orb.getValue();
                player.giveExperiencePoints(xpValue); // Adds XP directly to the player
                orb.discard(); // Remove the orb from the world
                // Play sound
                sound(player, SoundEvents.EXPERIENCE_ORB_PICKUP, 0.1F, 0.5F + player.getRandom().nextFloat());
            }
        }
    }

    // CUSTOM EVENT - MCCOURSE MOD BOTTLE item
    @SubscribeEvent
    public static void onMccourseModBottleMouseButton(InputEvent.MouseButton.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        ClientLevel clientLevel = mc.level;
        boolean shift = Screen.hasShiftDown();
        if (player != null && clientLevel != null) { // Pressed LEFT click + SHIFT
            Item item = player.getMainHandItem().getItem();
            if (mc.options.keyAttack.isDown() && item instanceof MccourseModBottleItem self) {
                ClientPacketDistributor.sendToServer(new MccourseModBottlePacketPayload(
                                                     MccourseModBottlePacketPayload.MccourseModBottleEnum.STORED,
                                                     shift ? self.storeXp : 1));
            }
        }
    }

    @SubscribeEvent
    public static void onMccourseModBottleKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        ClientLevel clientLevel = mc.level;
        boolean shift = Screen.hasShiftDown();
        if (player != null && clientLevel != null) {
            if (KeyBinding.MCCOURSE_BOTTLE_STORED_KEY.get().consumeClick()) { // Pressed SHIFT + N
                ClientPacketDistributor.sendToServer(new MccourseModBottlePacketPayload(
                                                     MccourseModBottlePacketPayload.MccourseModBottleEnum.STORED,
                                                     shift ? 100 : 10));
            }
            if (KeyBinding.MCCOURSE_BOTTLE_RESTORED_KEY.get().consumeClick()) { // Pressed SHIFT + B
                ClientPacketDistributor.sendToServer(new MccourseModBottlePacketPayload(
                                                     MccourseModBottlePacketPayload.MccourseModBottleEnum.RESTORED,
                                                     shift ? 100 : 10));
            }
        }
    }

    // CUSTOM EVENT - Player inventory PRESERVED items
    // Map of MAIN INVENTORY, ARMOR, OFFHAND and EXPERIENCE items
    private static final Map<UUID, Map<Integer, ItemStack>> savedInventory = new HashMap<>();
    private static final Map<UUID, int[]> savedExperience = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerDeathStoreItems(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) { // Entity is player
            UUID uuid = player.getUUID(); // Player UUID
            // PLAYER INVENTORY items - Added all INVENTORY, ARMOR and OFFHAND slots
            Inventory inv = player.getInventory();
            Map<Integer, ItemStack> indexedItems = new HashMap<>();
            // Get all items on MAIN INVENTORY, ARMOR and OFFHAND slots
            for (int i = 0; i < inv.getContainerSize(); i++) {
                ItemStack stack = inv.getItem(i);
                if (!stack.isEmpty()) {
                    indexedItems.put(i, stack.copy()); // Saves item with same index
                    inv.setItem(i, ItemStack.EMPTY);   // After clean item slot
                }
            }
            // Save player INVENTORY, ARMOR and OFFHAND slots
            savedInventory.put(uuid, indexedItems);
            // PLAYER EXPERIENCE orbs - Added player experience
            int[] xp = new int[] { player.experienceLevel,
                                   Float.floatToIntBits(player.experienceProgress),
                                   player.totalExperience };
            // Save player EXPERIENCE
            savedExperience.put(uuid, xp);
            // Reset EXPERIENCE to prevent drop
            player.experienceLevel = 0;
            player.experienceProgress = 0;
            player.totalExperience = 0;
        }
    }

    @SubscribeEvent
    public static void onPlayerCloneRestoreItems(PlayerEvent.Clone event) {
        if (event.isWasDeath()) { // Ensures that it only runs AFTER death
            Player oldPlayer = event.getOriginal();
            UUID uuid = oldPlayer.getUUID(); // Get Old player UUID -> BEFORE death
            Player newPlayer = event.getEntity(); // Entity is New player -> AFTER death
            BlockPos blockPos = oldPlayer.blockPosition(); // Player position BEFORE death
            // MESSAGE when player death
            playDisplayLiteralFalse(newPlayer, chatMessage(newPlayer, blockPos, green));
            // Restore EXPERIENCE and removed all EXPERIENCE saved
            int[] xp = savedExperience.remove(uuid);
            if (xp != null) {
                newPlayer.experienceLevel = xp[0]; // Restored experience level
                newPlayer.experienceProgress = Float.intBitsToFloat(xp[1]); // Restored experience progress
                newPlayer.totalExperience = xp[2]; // Restored total experience
            }
            // Restore INVENTORY items in the same indexes
            Inventory inv = newPlayer.getInventory();
            // Restore all INVENTORY, ARMOR and OFFHAND saved slots
            Map<Integer, ItemStack> indexedItems = savedInventory.remove(uuid);
            // Added ITEMS on MAIN INVENTORY, ARMOR and OFFHAND slots
            if (indexedItems != null) {
                for (Map.Entry<Integer, ItemStack> entry : indexedItems.entrySet()) {
                    int index = entry.getKey(); // Index saved
                    ItemStack stack = entry.getValue(); // Item saved
                    inv.setItem(index, stack);
                }
            }
        }
    }

    // CUSTOM EVENT - LEVEL CHARGER items -> Used item with mouse click on slot
    @SubscribeEvent
    public static void onLevelChargerItemsMouseClick(ScreenEvent.MouseButtonPressed.Pre event) {
        if (!(event.getScreen() instanceof AbstractContainerScreen<?> screen)) { return; }
        Player player = Minecraft.getInstance().player;
        if (player == null) { return; }
        ItemStack carried = player.containerMenu.getCarried();
        Item item = carried.getItem();
        boolean generic = item instanceof LevelChargerGenericItem; // Level Charger Generic
        boolean specif = item instanceof LevelChargerSpecifItem; // Level Charger Specif
        if (!(generic || specif)) { return; } // Hasn't LEVEL CHARGER items
        double mouseX = event.getMouseX();
        double mouseY = event.getMouseY();
        if (event.getButton() != 0) { return; }
        for (Slot slot : screen.getMenu().slots) {
            int x = screen.getGuiLeft() + slot.x, y = screen.getGuiTop() + slot.y;
            if (mouseX >= x && mouseX < x + 16 && mouseY >= y && mouseY < y + 16) {
                ItemStack target = slot.getItem();
                if (target.isEmpty() || target == carried) { return; }
                // Send to the server -> LEVEL CHARGER items
                ClientPacketDistributor.sendToServer(new LevelChargerInventorySlotPacketPayload(slot.index));
                Utils.consumeInfinite(player, carried); // Consume item on client (immediate visual effect)
                event.setCanceled(true);
                return;
            }
        }
    }

    // CUSTOM EVENT - UNLOCK custom enchantment
    @SubscribeEvent
    public static void activatedUnlockOnKeyPress(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        KeyMapping map = KeyBinding.UNLOCK_KEY.get();
        if (player == null || !map.isDown() || !map.consumeClick()) { return; }
        if (mc.screen == null) {
            Inventory inv = player.getInventory();
            for (int i = 0; i < inv.getContainerSize(); i++) { // MAIN, ARMOR and OFFHAND slots
                ItemStack main = inv.getItem(i);
                Utils.unlockOnKeyPress(main, i);
            }
        }
    }

    @SubscribeEvent
    public static void activatedUnlockOnGuiKeyPress(ScreenEvent.KeyPressed.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (!(event.getScreen() instanceof AbstractContainerScreen<?> screen)) { return; }
        if (player == null) { return; }
        if (event.getKeyCode() != KeyBinding.UNLOCK_KEY.get().getKey().getValue()) { return; }
        Slot hovered = screen.getSlotUnderMouse();
        if (hovered == null || !hovered.hasItem()) {
            ChatUtils.player(player, "No item under mouse!", red);
            return;
        }
        ItemStack hoveredStack = hovered.getItem();
        int index = -1;
        Inventory inv = player.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            if (ItemStack.isSameItemSameComponents(inv.getItem(i), hoveredStack)) {
                index = i;
                break;
            }
        }
        boolean locked = false;
        Boolean effect = hoveredStack.get(ModDataComponentTypes.UNLOCK);
        if (effect != null) { locked = effect; }
        ClientPacketDistributor.sendToServer(new UnlockEnchantmentPacketPayload(!locked, index));
        event.setCanceled(true); // Prevents other mods or the game from consuming the key
    }

    @SubscribeEvent
    public static void activatedUnlockOnDropKey(ScreenEvent.KeyPressed.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (!(event.getScreen() instanceof AbstractContainerScreen<?> screen)) { return; }
        if (player == null) { return; }
        if (event.getKeyCode() != mc.options.keyDrop.getKey().getValue()) { return; } // Check if the PRESSED key is DROP
        Slot hoveredSlot = screen.getSlotUnderMouse();
        if (hoveredSlot == null) { return; }
        ItemStack stack = hoveredSlot.getItem();
        if (stack.isEmpty()) { return; }
        Boolean unlockValue = stack.get(ModDataComponentTypes.UNLOCK);
        if (unlockValue != null && unlockValue) { // Check if the item is LOCKED
            event.setCanceled(true); // Prevents item movement
            player(player, "\uD83D\uDD12 This item is locked!", red);
        }
    }

    @SubscribeEvent
    public static void activatedUnlockItemToss(ItemTossEvent event) {
        ItemStack stack = event.getEntity().getItem();
        ItemStack safeCopy = stack.copy(); // Original item and make a safe copy first
        Player player = event.getPlayer();
        Boolean unlockValue = stack.get(ModDataComponentTypes.UNLOCK);
        if (unlockValue != null && unlockValue) {
            event.setCanceled(true);
            // If the item is LOCKED, and you try to play the item a warning is shown on the screen
            player(player, "\uD83D\uDD12 This item is locked!", red);
            boolean added = player.getInventory().add(safeCopy);
            if (!added) {
                // Try to put in slots
                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    player.getInventory().setItem(i, safeCopy.copy());
                    safeCopy.setCount(0); // Empties after moving
                }
                // If there is any left, throw it on the floor
                MinecraftServer server = player.level().getServer();
                if (!safeCopy.isEmpty() && server != null) {
                    ServerLevel kaupen = server.getLevel(ModDimensions.KAUPENDIM_LEVEL_KEY);
                    ServerLevel over = server.getLevel(Level.OVERWORLD);
                    ServerLevel nether = server.getLevel(Level.NETHER);
                    ServerLevel end = server.getLevel(Level.END);
                    if (kaupen != null) { player.spawnAtLocation(kaupen, safeCopy); }
                    if (over != null) { player.spawnAtLocation(over, safeCopy); }
                    if (nether != null) { player.spawnAtLocation(nether, safeCopy); }
                    if (end != null) { player.spawnAtLocation(end, safeCopy); }
                }
            }
        }
    }

    // CUSTOM EVENT - TELEPORT effect
    @SubscribeEvent
    public static void teleportEffectOnItemRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        Level level = event.getLevel();
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            if (player instanceof ServerPlayer serverPlayer) { // Teleport when using item (like tools, etc.)
                // Check if holding Alexandrite Sword item
                if (!stack.isEmpty() && stack.is(ModItems.ALEXANDRITE_SWORD)) {
                    /* 1. Get the direction the player is looking;
                       2. It starts from the eyes;
                       3. Block render distance. How many blocks ahead to ray trace (reach distance). */
                    Vec3 look = serverPlayer.getLookAngle();
                    Vec3 start = serverPlayer.getEyePosition();
                    Vec3 end = start.add(look.scale(5.0));
                    // Ray trace until it hits a block
                    BlockHitResult hitResult = hitBlock(serverLevel, start, end, serverPlayer);
                    if (hitResult.getType() == HitResult.Type.BLOCK) {
                        BlockPos blockPos = hitResult.getBlockPos(); // Teleports to the top of the block hit (+1 height)
                        double x = blockPos.getX() + 0.5;
                        double y = blockPos.getY() + 1.0;
                        double z = blockPos.getZ() + 0.5;
                        float xRot = serverPlayer.getXRot();
                        float yRot = serverPlayer.getYRot();
                        serverPlayer.teleportTo(serverLevel, x, y, z, Set.of(), yRot, xRot, true);
                    }
                }
            }
        }
    }
}