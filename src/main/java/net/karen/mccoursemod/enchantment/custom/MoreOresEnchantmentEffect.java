package net.karen.mccoursemod.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import static net.karen.mccoursemod.util.Utils.toolEnchant;

public record MoreOresEnchantmentEffect(List<TagKey<Block>> blockTagKey,
                                        HolderSet<Block> block, List<Float> chance) {
    // CODEC
    public static final Codec<MoreOresEnchantmentEffect> CODEC =
           RecordCodecBuilder.create(instance ->
                                    // Block Tag Key
                     instance.group(TagKey.codec(Registries.BLOCK).listOf().fieldOf("blockTagKey")
                                          .forGetter(MoreOresEnchantmentEffect::blockTagKey),
                                    // Block
                                    RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("block")
                                                  .forGetter(MoreOresEnchantmentEffect::block),
                                    // Block Chance
                                    Codec.list(Codec.FLOAT).fieldOf("chance")
                                                           .forGetter(MoreOresEnchantmentEffect::chance))
                             .apply(instance, MoreOresEnchantmentEffect::new));

    // STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, MoreOresEnchantmentEffect> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.fromCodec(TagKey.codec(Registries.BLOCK).listOf()),
                                 MoreOresEnchantmentEffect::blockTagKey,
                                 ByteBufCodecs.holderSet(Registries.BLOCK), MoreOresEnchantmentEffect::block,
                                 ByteBufCodecs.<ByteBuf, Float>list().apply(ByteBufCodecs.FLOAT),
                                 MoreOresEnchantmentEffect::chance, MoreOresEnchantmentEffect::new);

    // CUSTOM METHOD - MORE ORES Enchantment Effect EVENT
    public static void moreOresEnch(ItemStack tool,
                                    BlockState state, ServerLevel serverLevel,
                                    List<ItemStack> finalDrops, AtomicBoolean cancelVanillaDrop, int hasFortune) {
        EnchantmentHelper.runIterationOnItem(tool, (holder, holderLvl) -> {
            MoreOresEnchantmentEffect effect =
                    holder.value().effects().get(ModDataComponentTypes.MORE_ORES_ENCHANTMENT_EFFECT.get());
            if (effect != null && effect.block() != null && effect.blockTagKey() != null) {
                HolderSet<Block> blockHolderSet = effect.block();
                List<TagKey<Block>> blockTag = effect.blockTagKey();
                List<Float> chances = effect.chance();
                Holder<Block> stoneHolder = blockHolderSet.get(0), netherrackHolder = blockHolderSet.get(1);
                Float stoneChance = chances.getFirst(), netherrackChance = chances.get(1);
                Block stoneBlock = stoneHolder.value();
                Block netherrackBlock = netherrackHolder.value();
                if (is(state, stoneBlock, serverLevel, stoneChance, tool, 1) ||
                    is(state, netherrackBlock, serverLevel, netherrackChance, tool, 2)) {
                    moreOresEffect(blockTag, holderLvl - 1, hasFortune, holderLvl, finalDrops);
                    cancelVanillaDrop.set(true);
                }
                else if (is(state, stoneBlock, serverLevel, stoneChance, tool, 3)) {
                    moreOresEffect(blockTag, 6, hasFortune, holderLvl, finalDrops);
                    cancelVanillaDrop.set(true);
                }
            }
        });
    }

    // CUSTOM METHOD - MORE ORES Enchantment Effect
    public static void moreOresEffect(List<TagKey<Block>> blockTag, int index,
                                      int hasFortune, int holderLvl, List<ItemStack> finalDrops) {
        Optional<HolderSet.Named<Block>> tagBlock = BuiltInRegistries.BLOCK.get(blockTag.get(index));
        tagBlock.flatMap(block ->
                         block.getRandomElement(RandomSource.create())).ifPresent(holder -> {
                              // Increase ore drop with FORTUNE and MORE ORES enchantments
                              ItemStack drop = new ItemStack(holder.value().asItem());
                              drop.setCount((drop.getCount() * hasFortune) * holderLvl);
                              finalDrops.add(drop); // Break block and ore chance drop
                         });
    }

    // CUSTOM METHOD - MORE ORES Enchantment Effect -> Block, chance ore drop, MORE ORES level and required level enchantment
    public static boolean is(BlockState state, Block block,
                             ServerLevel serverLevel, float chance, ItemStack item, int type) {
        ClientLevel mc = Minecraft.getInstance().level;
        if (mc != null) {
            HolderLookup.RegistryLookup<Enchantment> ench = mc.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            int moreOres = toolEnchant(ench, ModEnchantments.MORE_ORES, item);
            float randomChance = serverLevel.random.nextFloat();
            boolean isBlock = state.is(block);
            boolean hasEnchant = isBlock && (randomChance < chance);
            switch (type) {
                case 1 -> hasEnchant = isBlock && (randomChance < chance) && (moreOres < 6);
                case 2 -> hasEnchant = isBlock && (randomChance < chance) && (moreOres == 6);
                case 3 -> hasEnchant = isBlock && (randomChance < chance) && (moreOres >= 7);
            }
            return hasEnchant;
        }
        else { return false; }
    }
}