package net.karen.mccoursemod.event;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.datafixers.util.Either;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.effect.ModEffects;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.enchantment.custom.MoreOresEnchantmentEffect;
import net.karen.mccoursemod.enchantment.custom.RainbowEnchantmentEffect;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.item.custom.HammerItem;
import net.karen.mccoursemod.item.custom.MccourseModBottleItem;
import net.karen.mccoursemod.item.custom.SpecialEffectItem;
import net.karen.mccoursemod.network.MccourseModBottlePacketPayload;
import net.karen.mccoursemod.network.MccourseModElevatorPacketPayload;
import net.karen.mccoursemod.potion.ModPotions;
import net.karen.mccoursemod.util.ChatUtils;
import net.karen.mccoursemod.util.KeyBinding;
import net.karen.mccoursemod.util.ModTags;
import net.karen.mccoursemod.util.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.living.EnderManAngerEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.lwjgl.glfw.GLFW;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import static net.karen.mccoursemod.item.custom.SpecialEffectItem.getEffectMultiplier;
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
            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if (pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }
                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
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
        int fortune = toolEnchant(ench, Enchantments.FORTUNE, tool),
            autoSmelt = toolEnchant(ench, ModEnchantments.AUTO_SMELT, tool),
            magnet = toolEnchant(ench, ModEnchantments.MAGNET, tool),
            moreOres = toolEnchant(ench, ModEnchantments.MORE_ORES_ENCHANTMENT_EFFECT, tool),
            rainbow = toolEnchant(ench, ModEnchantments.RAINBOW_ENCHANTMENT_EFFECT, tool);
        boolean hasAutoSmelt = SpecialEffectItem.getMultiplierBool(tool, ModDataComponentTypes.AUTO_SMELT.get()),
                hasMagnet = SpecialEffectItem.getMultiplierBool(tool, ModDataComponentTypes.MAGNET.get());
        if (!level.isClientSide() && world instanceof ServerLevel serverLevel) {
            // Adapt the drop according to the enchantment being true
            AtomicBoolean cancelVanillaDrop = new AtomicBoolean(false);
            List<ItemStack> finalDrops = new ArrayList<>(); // Items caused by enchantments are stored in the list
            int hasFortune = (fortune > 0) ? (1 + serverLevel.random.nextInt(fortune + 1)) : 1;
            if (rainbow > 0) { // * RAINBOW ENCHANTMENT EFFECT *
                EnchantmentHelper.runIterationOnItem(tool, (holder, holderLvl) -> {
                    RainbowEnchantmentEffect rainbowEnchEffect = // Get all values of Rainbow Enchantment Data Component
                           holder.value().effects().get(ModDataComponentTypes.RAINBOW_ENCHANTMENT_EFFECT.get());
                    if (rainbowEnchEffect != null && rainbowEnchEffect.map() != null) {
                        Map<Block, TagKey<Block>> rainbowMap = rainbowEnchEffect.map();
                        for (Map.Entry<Block, TagKey<Block>> entry : rainbowMap.entrySet()) {
                            Block entryKey = entry.getKey(); // Block Ores
                            TagKey<Block> entryValue = entry.getValue(); // Block Ore Blocks
                            if (state.is(entryValue)) {
                                block(world, pos, entryKey, event);
                                break;
                            }
                        }
                        if (state.is(ModTags.Blocks.RAINBOW_DROPS)) {
                            ItemStack rainbowDrop = new ItemStack(state.getBlock());
                            rainbowDrop.setCount((rainbowDrop.getCount() * hasFortune) * holderLvl);
                            finalDrops.add(rainbowDrop);
                            cancelVanillaDrop.set(true);
                        }
                    }
                });
            }
            if (moreOres > 0) { // * MORE ORES ENCHANTMENT EFFECT *
                EnchantmentHelper.runIterationOnItem(tool, (holder, holderLvl) -> {
                    MoreOresEnchantmentEffect moreOresEnchEffect =
                            holder.value().effects().get(ModDataComponentTypes.MORE_ORES_ENCHANTMENT_EFFECT.get());
                    if (moreOresEnchEffect != null && moreOresEnchEffect.block() != null &&
                        moreOresEnchEffect.blockTagKey() != null) {
                        if (state.is(moreOresEnchEffect.block())) {
                            Iterable<Holder<Block>> tagBlock =
                                    BuiltInRegistries.BLOCK.getTagOrEmpty(moreOresEnchEffect.blockTagKey());
                            tagBlock.forEach((block -> {
                                if (serverLevel.random.nextFloat() < moreOresEnchEffect.chance()) {
                                    // Increase ore drop with Fortune and More Ores enchantments
                                    ItemStack drop = new ItemStack(block.value().asItem());
                                    drop.setCount((drop.getCount() * hasFortune) * holderLvl);
                                    finalDrops.add(drop); // Break block and ore chance drop
                                }
                            }));
                        }
                    }
                });
                // Break block and ore chance drop
                finalDrops.addAll(Block.getDrops(state, serverLevel, pos, null, player, tool));
                cancelVanillaDrop.set(true);
            }
            if (hasAutoSmelt || autoSmelt > 0) { // * AUTO SMELT EFFECT *
                SingleRecipeInput singleRecipe = new SingleRecipeInput(new ItemStack(state.getBlock()));
                ServerLevel worldServer = serverLevel.getLevel();
                Optional<RecipeHolder<SmeltingRecipe>> recipe =
                        serverLevel.getServer().getRecipeManager().getRecipeFor(RecipeType.SMELTING, singleRecipe, worldServer);
                recipe.ifPresentOrElse(result -> {
                    ItemStack recipeValue = result.value().assemble(singleRecipe, worldServer.registryAccess()),
                                     drop = recipeValue.copy();
                    if (state.is(ModTags.Blocks.AUTO_SMELT_ORES)) {
                        drop.setCount((drop.getCount() * hasFortune) *
                                      (getEffectMultiplier(tool, ModDataComponentTypes.AUTO_SMELT.get(), 1)) *
                                      autoSmelt);
                    }
                    finalDrops.add(drop);
                },
                () -> finalDrops.addAll(Block.getDrops(state, serverLevel, pos, null, player, tool)));
                cancelVanillaDrop.set(true);
            }
            if ((hasMagnet || magnet > 0) && !state.isAir()) { // * MAGNETIC EFFECT *
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
        boolean hasMoreOres = item.has(ModDataComponentTypes.MORE_ORES.get());
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
        int x = 10, y = 10;
        ItemStack itemStack = new ItemStack(Items.DIAMOND);
        guiGraphics.renderItem(itemStack, x, y);
        guiGraphics.renderItemDecorations(mc.font, itemStack, x + 20, y + 10, "§bDiamond");
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

    // CUSTOM EVENT - Block + Item experience orb
    @SubscribeEvent
    public static void onBedExperiencePlayerWakeUp(PlayerWakeUpEvent event) { // Gain experience orb when sleep
        Player player = event.getEntity();
        Level level = player.level();
        if (!level.isClientSide()) { setPlayerXP(player, level, 2); }
    }

    @SubscribeEvent
    public static void onExperienceItemFished(ItemFishedEvent event) { // Gain experience orb when fished
        Player player = event.getEntity();
        Level level = player.level();
        if (!level.isClientSide()) { if (!event.getDrops().isEmpty()) { setPlayerXP(player, level, 2); } }
    }

    @SubscribeEvent
    public static void onExperienceBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.level();
        if (!level.isClientSide()) { // Block drop xp
            BuiltInRegistries.BLOCK.forEach(block -> setPlayerXP(player, level, 2)); // All blocks
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
}