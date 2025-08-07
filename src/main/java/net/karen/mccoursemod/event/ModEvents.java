package net.karen.mccoursemod.event;

import com.mojang.datafixers.util.Either;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.effect.ModEffects;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.item.custom.HammerItem;
import net.karen.mccoursemod.item.custom.SpecialEffectItem;
import net.karen.mccoursemod.potion.ModPotions;
import net.karen.mccoursemod.util.ChatUtils;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import java.util.*;
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
    public static void onBlockBreakWithCustomEnchantments(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        LevelAccessor world = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();
        ItemStack tool = player.getMainHandItem();
        Level level = (Level) event.getLevel();
        if (tool.isEmpty()) { return; } // * PROBLEMS *
        HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        int fortune = EnchantmentHelper.getTagEnchantmentLevel(ench.getOrThrow(Enchantments.FORTUNE).getDelegate(), tool),
            autoSmelt = EnchantmentHelper.getTagEnchantmentLevel(ench.getOrThrow(ModEnchantments.AUTO_SMELT).getDelegate(), tool),
            moreOres = EnchantmentHelper.getTagEnchantmentLevel(ench.getOrThrow(ModEnchantments.MORE_ORES).getDelegate(), tool),
            rainbow = EnchantmentHelper.getTagEnchantmentLevel(ench.getOrThrow(ModEnchantments.RAINBOW).getDelegate(), tool),
            magnet = EnchantmentHelper.getTagEnchantmentLevel(ench.getOrThrow(ModEnchantments.MAGNET).getDelegate(), tool);
        boolean hasRainbow = SpecialEffectItem.getMultiplierBool(tool, ModDataComponentTypes.RAINBOW.get()),
                hasMoreOres = SpecialEffectItem.getMultiplierBool(tool, ModDataComponentTypes.MORE_ORES.get()),
                hasAutoSmelt = SpecialEffectItem.getMultiplierBool(tool, ModDataComponentTypes.AUTO_SMELT.get()),
                hasMagnet = SpecialEffectItem.getMultiplierBool(tool, ModDataComponentTypes.MAGNET.get());
        if (!level.isClientSide() && world instanceof ServerLevel serverLevel) {
            boolean cancelVanillaDrop = false; // Adapt the drop according to the enchantment being true
            List<ItemStack> finalDrops = new ArrayList<>(); // Items caused by enchantments are stored in the list
            int oresFortune = serverLevel.random.nextInt(fortune + 1),
                    hasFortune = (fortune > 0) ? (1 + oresFortune) : 1;
            if (hasRainbow || rainbow > 0) { // * RAINBOW EFFECT *
                Map<Block, TagKey<Block>> rainbowMap = Map.ofEntries(Map.entry(Blocks.COAL_BLOCK, Tags.Blocks.ORES_COAL),
                Map.entry(Blocks.COPPER_BLOCK, Tags.Blocks.ORES_COPPER), Map.entry(Blocks.DIAMOND_BLOCK, Tags.Blocks.ORES_DIAMOND),
                Map.entry(Blocks.EMERALD_BLOCK, Tags.Blocks.ORES_EMERALD), Map.entry(Blocks.GOLD_BLOCK, Tags.Blocks.ORES_GOLD),
                Map.entry(Blocks.IRON_BLOCK, Tags.Blocks.ORES_IRON), Map.entry(Blocks.LAPIS_BLOCK, Tags.Blocks.ORES_LAPIS),
                Map.entry(Blocks.REDSTONE_BLOCK, Tags.Blocks.ORES_REDSTONE),
                Map.entry(Blocks.NETHERITE_BLOCK, Tags.Blocks.ORES_NETHERITE_SCRAP));
                for (Map.Entry<Block, TagKey<Block>> entry : rainbowMap.entrySet()) {
                    if (state.is(entry.getValue())) {
                        block(world, pos, entry.getKey(), event);
                        return;
                    }
                }
                if (state.is(ModTags.Blocks.RAINBOW_DROPS)) {
                    ItemStack rainbowDrop = new ItemStack(state.getBlock());
                    rainbowDrop.setCount((rainbowDrop.getCount() * hasFortune) *
                                         (getEffectMultiplier(tool, ModDataComponentTypes.RAINBOW.get(), 1)) *
                                         rainbow);
                    finalDrops.add(rainbowDrop);
                    cancelVanillaDrop = true;
                }
            }
            if (hasMoreOres || moreOres > 0) { // * MORE ORES EFFECT *
                if (state.is(ModTags.Blocks.MORE_ORES_BREAK_BLOCK)) {
                    Iterable<Holder<Block>> tagBlock = BuiltInRegistries.BLOCK.getTagOrEmpty(ModTags.Blocks.MORE_ORES_ALL_DROPS);
                    tagBlock.forEach((block -> {
                        if (serverLevel.random.nextFloat() < 0.01F) {
                            ItemStack drop = new ItemStack(block.value().asItem()); // Increase ore drop with Multiplier enchantment
                            drop.setCount((drop.getCount() * hasFortune) *
                                          (getEffectMultiplier(tool, ModDataComponentTypes.MORE_ORES.get(), 1)) *
                                          moreOres);
                            finalDrops.add(drop); // Break block and ore chance drop
                        }
                    }));
                    // Break block and ore chance drop
                    finalDrops.addAll(Block.getDrops(state, serverLevel, pos, null, player, tool));
                    cancelVanillaDrop = true;
                }
            }
            if (hasAutoSmelt || autoSmelt > 0) { // * AUTO SMELT EFFECT *
                SingleRecipeInput singleRecipe = new SingleRecipeInput(new ItemStack(state.getBlock()));
                ServerLevel worldServer = serverLevel.getLevel();
                Optional<RecipeHolder<SmeltingRecipe>> recipe =
                        serverLevel.getServer().getRecipeManager().getRecipeFor(RecipeType.SMELTING, singleRecipe, worldServer);
                recipe.ifPresentOrElse(result -> {
                    ItemStack recipeValue = result.value().assemble(singleRecipe, worldServer.registryAccess()),
                            drop = new ItemStack(recipeValue.getItem().asItem());
                    if (state.is(ModTags.Blocks.AUTO_SMELT_ORES)) {
                        drop.setCount((drop.getCount() * hasFortune) *
                                      (getEffectMultiplier(tool, ModDataComponentTypes.AUTO_SMELT.get(), 1)) *
                                      autoSmelt);
                    }
                    finalDrops.add(drop);
                },
                () -> finalDrops.addAll(Block.getDrops(state, serverLevel, pos, null, player, tool)));
                cancelVanillaDrop = true;
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
            if (cancelVanillaDrop) { // FinalDrops list accumulate drop on world
                block(serverLevel, pos, Blocks.AIR, event);
                finalDrops.forEach(drop -> dropItem(serverLevel, pos, drop));
            }
        }
    }

    // CUSTOM EVENT - Icon tooltip
    @SubscribeEvent
    public static void onGatherTooltip(RenderTooltipEvent.GatherComponents event) {
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

    // CUSTOM EVENT - Double Jump
    private static boolean wasJumping = false;

    @SubscribeEvent
    public static void doubleJump(PlayerTickEvent.Post event) {
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

    @SubscribeEvent
    public static void flyEffect(PlayerTickEvent.Post event) {
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

    @SubscribeEvent
    public static void onFlyEffectRemoved(MobEffectEvent.Remove event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide()) {
            if (event.getEffect() == ModEffects.FLY_EFFECT) {
                AttributeInstance fly = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
                disableFlight(player, fly);
            }
        }
    }

    // CUSTOM EVENT - Block + Item experience orb
    @SubscribeEvent
    public static void onPlayerWakeUp(PlayerWakeUpEvent event) { // Gain experience orb when sleep
        Player player = event.getEntity();
        Level level = player.level();
        if (!level.isClientSide()) { setPlayerXP(player, level, 2); }
    }

    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) { // Gain experience orb when fished
        Player player = event.getEntity();
        Level level = player.level();
        if (!level.isClientSide()) { if (!event.getDrops().isEmpty()) { setPlayerXP(player, level, 2); } }
    }

    @SubscribeEvent
    public static void activatedAccumulatorOnBreakAnyBlocks(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.level();
        if (!level.isClientSide()) { // ACCUMULATOR block drop xp
            BuiltInRegistries.BLOCK.forEach(block -> setPlayerXP(player, level, 2)); // All blocks
        }
    }

    // CUSTOM EVENT - Block Fly custom enchantment
    @SubscribeEvent
    public static void activatedBlockFlyEnchantment(PlayerEvent.BreakSpeed event) {
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
    public static void onItemToss(ItemTossEvent event) {
        ItemEntity entity = event.getEntity();
        HolderLookup.RegistryLookup<Enchantment> ench = entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> immortalEnch = ench.getOrThrow(ModEnchantments.IMMORTAL);
        if (!entity.level().isClientSide()) {
            activatedImmortalEnchantment(entity, entity.getItem(), immortalEnch);
        }
    }

    @SubscribeEvent
    public static void onItemSpawn(EntityJoinLevelEvent event) {
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
    public static void onExplosion(ExplosionEvent.Detonate event) {
        HolderLookup.RegistryLookup<Enchantment> ench = event.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> immortalEnch = ench.getOrThrow(ModEnchantments.IMMORTAL);
        event.getAffectedEntities().removeIf(entity -> entity instanceof ItemEntity itemEntity &&
                                                             itemEntity.getItem().getEnchantmentLevel(immortalEnch) > 0);
    }

    @SubscribeEvent
    public static void onItemExpire(ItemExpireEvent event) { // Never disappears
        ItemEntity itemEntity = event.getEntity();
        HolderLookup.RegistryLookup<Enchantment> ench = itemEntity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> immortalEnch = ench.getOrThrow(ModEnchantments.IMMORTAL);
        if (itemEntity.getItem().getEnchantmentLevel(immortalEnch) > 0) {
            activatedImmortalEnchantment(itemEntity, itemEntity.getItem(), immortalEnch);
        }
    }

    @SubscribeEvent
    public static void onEntityTick(LevelTickEvent.Post event) {
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

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        GuiGraphics guiGraphics = event.getGuiGraphics();
        int x = 10, y = 10;
        ItemStack itemStack = new ItemStack(Items.DIAMOND);
        guiGraphics.renderItem(itemStack, x, y);
        guiGraphics.renderItemDecorations(mc.font, itemStack, x + 20, y + 5, "§bDiamond");
    }
}