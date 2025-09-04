package net.karen.mccoursemod.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public record MoreOresEnchantmentEffect(List<TagKey<Block>> blockTagKey,
                                        HolderSet<Block> block, List<Float> chance) {
    // CODEC
    public static final Codec<MoreOresEnchantmentEffect> CODEC =
           RecordCodecBuilder.create(instance ->
                    instance.group(TagKey.codec(Registries.BLOCK).listOf().fieldOf("blockTagKey")
                                         .forGetter(MoreOresEnchantmentEffect::blockTagKey), // Block Tag Key
                                   RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("block")
                                                 .forGetter(MoreOresEnchantmentEffect::block), // Block
                                   Codec.list(Codec.FLOAT).fieldOf("chance")
                                                          .forGetter(MoreOresEnchantmentEffect::chance)) // Block Chance
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
                                    BlockPos pos, Player player, List<ItemStack> finalDrops,
                                    AtomicBoolean cancelVanillaDrop, int hasFortune) {
        EnchantmentHelper.runIterationOnItem(tool, (holder, holderLvl) -> {
            MoreOresEnchantmentEffect effect =
                    holder.value().effects().get(ModDataComponentTypes.MORE_ORES_ENCHANTMENT_EFFECT.get());
            if (effect != null && effect.block() != null && effect.blockTagKey() != null) {
                HolderSet<Block> blockHolderSet = effect.block();
                List<TagKey<Block>> blockTag = effect.blockTagKey();
                List<Float> chances = effect.chance();
                Holder<Block> stoneHolder = blockHolderSet.get(0), netherrackHolder = blockHolderSet.get(1);
                Float stoneChance = chances.getFirst(), netherrackChance = chances.get(1);
                moreOresEffect(state.is(stoneHolder) && holderLvl > 0 || holderLvl <= 5,
                               blockTag, holderLvl - 1, serverLevel, stoneChance, hasFortune, holderLvl, finalDrops);
                moreOresEffect(state.is(netherrackHolder) && holderLvl >= 6, blockTag, 5,
                               serverLevel, netherrackChance, hasFortune, holderLvl, finalDrops);
            }
            // Break block and ore chance drop
            finalDrops.addAll(Block.getDrops(state, serverLevel, pos, null, player, tool));
            cancelVanillaDrop.set(true);
        });
    }

    // CUSTOM METHOD - MORE ORES Enchantment Effect
    public static void moreOresEffect(boolean bool,
                                      List<TagKey<Block>> blockTag, int index,
                                      ServerLevel serverLevel, Float blockChance,
                                      int hasFortune, int holderLvl, List<ItemStack> finalDrops) {
        if (bool && serverLevel.random.nextFloat() < blockChance) {
            Optional<HolderSet.Named<Block>> tagBlock = BuiltInRegistries.BLOCK.get(blockTag.get(index));
            tagBlock.flatMap(block ->
                             block.getRandomElement(RandomSource.create())).ifPresent(holder -> {
                                  // Increase ore drop with FORTUNE and MORE ORES enchantments
                                  ItemStack drop = new ItemStack(holder.value().asItem());
                                  drop.setCount((drop.getCount() * hasFortune) * holderLvl);
                                  finalDrops.add(drop); // Break block and ore chance drop
                             });

        }
    }
}