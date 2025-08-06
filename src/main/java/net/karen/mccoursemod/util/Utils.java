package net.karen.mccoursemod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.level.BlockEvent;
import java.util.Map;

public class Utils {
    public static boolean IGNORE_LAPIS = false; // Avoid consumption of LAPIS LAZULI

    private static final ThreadLocal<ItemStack> LAST_BOW_USED = new ThreadLocal<>(); // Store Miner Bow

    public static void setLastBowUsed(ItemStack stack) { LAST_BOW_USED.set(stack); } // Set Miner Bow

    public static ItemStack getLastBowUsed() { return LAST_BOW_USED.get(); } // Get Miner Bow

    public static void clear() { LAST_BOW_USED.remove(); } // Clear Miner Bow

    public static ClipContext.Block collider = ClipContext.Block.COLLIDER;
    public static ClipContext.Fluid none = ClipContext.Fluid.NONE, any = ClipContext.Fluid.ANY;

    public static HitResult.Type hitMiss = HitResult.Type.MISS, hitBlock = HitResult.Type.BLOCK;

    public static ItemStack empty = ItemStack.EMPTY;

    public static InteractionHand mainHand = InteractionHand.MAIN_HAND, offhand = InteractionHand.OFF_HAND;

    // CUSTOM METHOD - Player item sounds
    public static void sound(Player player, SoundEvent sound, float volume, float pitch) {
        player.level().playSound(null, player.blockPosition(), sound, SoundSource.PLAYERS, volume, pitch);
    }

    // CUSTOM METHOD - Player block sounds
    public static void soundBlock(Player player, SoundEvent sound, float volume, float pitch) {
        player.level().playSound(null, player.blockPosition(), sound, SoundSource.BLOCKS, volume, pitch);
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

    // CUSTOM METHOD - Level
    public static void playerSound(Level level, Player player,
                                   SoundEvent sound, float volume, float pitch) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(), sound, SoundSource.PLAYERS, volume, pitch);
    }

    public static void particle(Player player, Entity entity) {
        ((ServerLevel) player.level()).sendParticles(ParticleTypes.CRIT, entity.getX(), entity.getY(0.5),
                entity.getZ(), 5, 0.2, 0.2, 0.2, 0.1);
    }

    // CUSTOM METHOD - Item used has on slot
    public static ItemStack has(Player player, EquipmentSlot slot) {
        return player.getItemBySlot(slot); // Xray items - Enchanted Helmet or Metal Detector
    }

    // CUSTOM METHOD - Item used has on slot
    public static boolean slot(Player player, EquipmentSlot slot, TagKey<Item> item) {
        return player.getItemBySlot(slot).is(item); // Active Fly with Item
    }

    // CUSTOM METHOD - Item used on MAIN HAND
    public static boolean item(Player player, Item item) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == item; // Used item
    }

    // CUSTOM METHOD - Item used is on offhand or main hand
    public static ItemStack hasItem(Player player, InteractionHand hand) {
        return player.getItemInHand(hand);
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

    // CUSTOM METHOD - Grouped Enchanted book
    public static void createGroupedEnchantedBook(Map<Holder<Enchantment>, Integer> enchant,
                                                  Level level, BlockPos pos) {
        ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
        enchant.forEach((ench, integer) -> {
            if (integer > 0) { book.enchant(ench, integer); } });
        dropEnchanted(level, pos, book);
    }

    // CUSTOM METHOD - Enchanted item
    public static void createEnchantedItem(Item item,
                                           Map<Holder<Enchantment>, Integer> enchantment,
                                           Level level, BlockPos pos) {
        ItemStack stack = new ItemStack(item);
        enchantment.forEach((ench, integer) -> {
            if (integer > 0) { stack.enchant(ench, integer); }});
        dropEnchanted(level, pos, stack);
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
            flyAttribute.setBaseValue(1);
//            player.getAbilities().flying = true;
            player.getAbilities().setFlyingSpeed(BASE_FLY_SPEED + (0.02f * amplifier));
//            player.fallDistance = 0;
            player.onUpdateAbilities();
        }
    }

    public static void disableFlight(Player player, AttributeInstance flyAttribute) {
        if (flyAttribute != null && flyAttribute.getValue() > 0)  {
            flyAttribute.setBaseValue(0);
            player.getAbilities().flying = false;
            player.getAbilities().setFlyingSpeed(BASE_FLY_SPEED);
            player.onUpdateAbilities();
        }
    }
}