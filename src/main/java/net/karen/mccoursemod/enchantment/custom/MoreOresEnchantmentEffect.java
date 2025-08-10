package net.karen.mccoursemod.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public record MoreOresEnchantmentEffect(TagKey<Block> blockTagKey,
                                        HolderSet<Block> block,
                                        float chance) {
    // CODEC
    public static final Codec<MoreOresEnchantmentEffect> CODEC =
           RecordCodecBuilder.create(instance ->
                    instance.group(TagKey.codec(Registries.BLOCK).fieldOf("blockTagKey")
                                         .forGetter(MoreOresEnchantmentEffect::blockTagKey), // Block Tag Key
                                   RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("block")
                                                 .forGetter(MoreOresEnchantmentEffect::block), // Block
                                   Codec.FLOAT.fieldOf("chance").forGetter(MoreOresEnchantmentEffect::chance)) // Block Chance
                            .apply(instance, MoreOresEnchantmentEffect::new));

    // STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, MoreOresEnchantmentEffect> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.fromCodec(TagKey.codec(Registries.BLOCK)), MoreOresEnchantmentEffect::blockTagKey,
                                 ByteBufCodecs.holderSet(Registries.BLOCK), MoreOresEnchantmentEffect::block,
                                 ByteBufCodecs.FLOAT, MoreOresEnchantmentEffect::chance, MoreOresEnchantmentEffect::new);
}