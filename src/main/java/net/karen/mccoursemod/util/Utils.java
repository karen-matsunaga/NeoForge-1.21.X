package net.karen.mccoursemod.util;

import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.network.UnlockEnchantmentPacketPayload;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Map;

public class Utils {
    public static boolean IGNORE_LAPIS = false; // Avoid consumption of LAPIS LAZULI

    private static final ThreadLocal<ItemStack> LAST_BOW_USED = new ThreadLocal<>(); // Store Miner Bow

    public static ItemStack getLastBowUsed() { return LAST_BOW_USED.get(); } // Get Miner Bow

    public static void clear() { LAST_BOW_USED.remove(); } // Clear Miner Bow

    public static InteractionHand mainHand = InteractionHand.MAIN_HAND, offhand = InteractionHand.OFF_HAND;

    // CUSTOM METHOD - Level block sounds
    public static void blockSound(Level level, boolean isPlayer, @Nullable Entity entity,
                                  BlockPos pos, SoundEvent sound) {
        level.playSound(isPlayer ? entity : null, pos, sound, SoundSource.BLOCKS, 1F, 1F);
    }

    // CUSTOM METHOD - Player item sounds
    public static void sound(Player player, SoundEvent sound, float volume, float pitch) {
        player.level().playSound(null, player.blockPosition(), sound, SoundSource.PLAYERS, volume, pitch);
    }

    // CUSTOM METHOD - Level neutral sounds
    public static void neutralSound(Level level, Player player,
                                    SoundEvent sound, float volume, float pitch) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(), sound, SoundSource.NEUTRAL, volume, pitch);
    }

    // CUSTOM METHOD - Level neutral sounds with PITCH value
    public static void neutralSoundValue(Level level, Player player, SoundEvent sound, float volume) {
        double x = player.getX(), y = player.getY(), z = player.getZ();
        float pitch = 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F);
        level.playSound(null, x, y, z, sound, SoundSource.NEUTRAL, 0.5F + volume, pitch);
    }

    // CUSTOM METHOD - Item used has on slot
    public static ItemStack has(Player player, EquipmentSlot slot) {
        return player.getItemBySlot(slot); // Xray items - Enchanted Helmet or Metal Detector
    }

    // CUSTOM METHOD - Item used on MAIN HAND
    public static boolean item(Player player, Item item) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == item; // Used item
    }

    // CUSTOM METHOD - Drop enchanted book and base item on ground [world]
    public static void dropItem(ServerLevel world, BlockPos pos, ItemStack stack) {
        ItemEntity item = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
        item.setDeltaMovement(Vec3.ZERO);
        world.addFreshEntity(item);
    }

    // CUSTOM METHOD - Drop ENCHANTED BOOK and BASE ITEM on ground
    public static void dropEnchanted(Level world, BlockPos pos, ItemStack item) {
        ItemEntity drop = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, item);
        drop.setDeltaMovement(Vec3.ZERO);
        world.addFreshEntity(drop);
    }

    // CUSTOM METHOD - Drop items
    public static void dropItems(Level level,
                                 double x, double y, double z, ItemStack item) {
        ItemEntity drop = new ItemEntity(level, x, y, z, item);
        drop.setDeltaMovement(Vec3.ZERO);
        level.addFreshEntity(drop);
    }

    // CUSTOM METHOD - Cancel vanilla drop
    public static void block(LevelAccessor world, BlockPos pos, Block block,
                             BlockEvent.BreakEvent event) {
        event.setCanceled(true);
        if (world instanceof ServerLevel serverLevel) { serverLevel.setBlockAndUpdate(pos, block.defaultBlockState()); }
        else { world.setBlock(pos, block.defaultBlockState(), 3); }
    }

    // CUSTOM METHOD - Manually drops XP from blocks and items that normally have no XP
    public static void setPlayerXP(Player player, Level level, int xp) {
        Vec3 position = new Vec3(player.getBlockX(), player.getBlockY(), player.getBlockZ());
        ExperienceOrb.award((ServerLevel) level, position, xp);
    }

    // CUSTOM METHOD - Enchanted items (Villager) -> Item / Book
    public static ItemStack villagerEnchantedItem(Item item,
                                                  Map<Holder<Enchantment>, Integer> enchantment) {
        ItemStack stack = new ItemStack(item);
        enchantment.forEach((ench, integer) -> {
            if (integer > 0) { stack.enchant(ench, integer); }
        });
        return stack;
    }

    // CUSTOM METHOD - Grouped Enchantment
    public static void groupedEnch(Map<Holder<Enchantment>, Integer> enchantments,
                                   Level level, BlockPos pos) {
        ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
        enchantments.forEach((ench, lvl) -> {
            if (lvl > 0) { book.enchant(ench, lvl); }
        });
        dropEnchanted(level, pos, book); // Drop grouped enchanted book WITH enchantment
    }

    // CUSTOM METHOD - INDIVIDUAL enchanted book
    public static void individualEnch(Map<Holder<Enchantment>, Integer> enchantments,
                                      Level level, BlockPos pos) {
        enchantments.forEach((enc, lvl) -> {
            if (lvl > 0) {
                ItemStack book = EnchantmentHelper.createBook(new EnchantmentInstance(enc, lvl));
                dropEnchanted(level, pos, book);
            }
        });
    }

    // CUSTOM METHOD - Player has enchantment on slots
    public static int hasEnchant(Holder<Enchantment> enchantment, Player player) {
        return EnchantmentHelper.getEnchantmentLevel(enchantment, player);
    }

    // CUSTOM METHOD - Enchantment tool
    public static int toolEnchant(HolderLookup.RegistryLookup<Enchantment> ench,
                                  ResourceKey<Enchantment> name, ItemStack tool) {
        return EnchantmentHelper.getTagEnchantmentLevel(ench.getOrThrow(name).getDelegate(), tool);
    }

    // CUSTOM METHOD - Consume Infinite item
    public static void consumeInfinite(Player player, ItemStack usedStack) {
        if (!player.getAbilities().instabuild) {
            usedStack.shrink(1); // Consume Infinite item
            player.containerMenu.broadcastChanges(); // Update the interface
        }
    }

    // FLY EFFECT
    private static final float BASE_FLY_SPEED = 0.05f;

    public static void enableFlight(Player player, AttributeInstance flyAttribute, int amplifier) {
        if (flyAttribute != null && flyAttribute.getValue() == 0) {
            flyAttribute.setBaseValue(1); // Set FLY -> TRUE
            player.getAbilities().setFlyingSpeed(BASE_FLY_SPEED + (0.02f * amplifier)); // Set FLY SPEED
            player.onUpdateAbilities(); // Updated FLY ability
        }
    }

    public static void disableFlight(Player player, AttributeInstance flyAttribute) {
        if (flyAttribute != null && flyAttribute.getValue() > 0)  {
            flyAttribute.setBaseValue(0); // Set FLY -> FALSE
            player.getAbilities().flying = false; // Set FLYING -> FALSE
            player.getAbilities().setFlyingSpeed(BASE_FLY_SPEED); // Set FLY SPEED
            player.onUpdateAbilities(); // Updated FLY ability
        }
    }

    // CUSTOM METHOD - Set newSpeed adapt with Efficiency enchantment -> Fixed speed mining
    public static void newSpeed(PlayerEvent.BreakSpeed event, boolean hasEnchant,
                                Player player, int value) {
        if (hasEnchant) {
            BlockState state = event.getState();
            if ((!player.onGround() && !player.isUnderWater()) || player.isUnderWater()) {
                event.setNewSpeed(event.getOriginalSpeed() * ((float) Math.sqrt(value) + 1));
            }
            if (state.is(ModTags.Blocks.BLOCK_FLY_BLOCK_SPEED)) {
                event.setNewSpeed(event.getOriginalSpeed() * 2.5F + ((float) Math.sqrt(value) + 1));
            }
        }
    }

    // CUSTOM METHOD - Immortal enchantment radius item
    public static List<Entity> getRadiusItem(LevelTickEvent event) {
        double x = 0, y = -100, z = 0, xSize = 10000, ySize = 500, zSize = 10000;
        return event.getLevel().getEntities(null, AABB.ofSize(new Vec3(x, y, z), xSize, ySize, zSize));
    }

    // CUSTOM METHOD - Activated IMMORTAL enchantment
    public static void activatedImmortalEnchantment(ItemEntity entity, ItemStack item,
                                                    Holder<Enchantment> ench) {
        if (item.getEnchantmentLevel(ench) > 0) {
            entity.setInvulnerable(true);
            entity.setUnlimitedLifetime(); // Does not disappear over time
            entity.setPickUpDelay(10); // It can be collected after 0.5s
        }
    }

    // CUSTOM METHOD - GLOWING MOBS -> Added Effect
    public static MobEffectInstance effect(Holder<MobEffect> effect, int duration, int amplifier) {
        return new MobEffectInstance(effect, duration, amplifier, true, false, false);
    }

    // CUSTOM METHOD - GLOWING MOBS -> GET player item
    public static List<LivingEntity> getPlayer(Player player, TagKey<EntityType<?>> tag) {
        return player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(10),
                                                 entity -> entity.getType().is(tag) && entity != player);
    }

    // CUSTOM METHOD - Render Overlay LIGHT, SKY, BLOCK numbers
    public static int light(Level level, LightLayer type, BlockPos pos) {
        return level != null ? level.getLightEngine().getLayerListener(type).getLightValue(pos) : 0;
    }

    // CUSTOM METHOD - UNLOCK enchantment on key press
    public static void unlockOnKeyPress(ItemStack main, int index) {
        if (!main.isEmpty() && main.has(ModDataComponentTypes.UNLOCK)) { // MAIN HAND, ARMOR and OFFHAND
            Boolean unlockData = main.get(ModDataComponentTypes.UNLOCK);
            if (unlockData != null) {
                boolean locked = unlockData;
                ClientPacketDistributor.sendToServer(new UnlockEnchantmentPacketPayload(!locked, index));
            }
        }
    }

    // CUSTOM METHOD - TELEPORT effect when click on a block
    public static BlockHitResult hitBlock(ServerLevel level, Vec3 eye, Vec3 reach, Player player) {
        return level.clip(new ClipContext(eye, reach, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
    }

    // CUSTOM METHOD - RESTORE -> Item used is on offhand or main hand
    public static ItemStack hasItem(Player player, InteractionHand hand) {
        return player.getItemInHand(hand);
    }
}