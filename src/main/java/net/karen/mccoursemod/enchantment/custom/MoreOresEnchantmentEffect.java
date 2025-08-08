package net.karen.mccoursemod.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.block.Block;

public record MoreOresEnchantmentEffect(HolderSet<Block> blockTag, HolderSet<Block> block, float chance) {
    // CODEC
    public static final Codec<MoreOresEnchantmentEffect> CODEC =
           RecordCodecBuilder.create(instance ->
                    instance.group(RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("blockTag")
                                                 .forGetter(MoreOresEnchantmentEffect::blockTag), // Block Tag
                                    RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("block")
                                                    .forGetter(MoreOresEnchantmentEffect::block), // Block
                                   Codec.FLOAT.fieldOf("chance").forGetter(MoreOresEnchantmentEffect::chance)) // Block Chance
                            .apply(instance, MoreOresEnchantmentEffect::new));

    // STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, MoreOresEnchantmentEffect> STREAM =
           StreamCodec.composite(ByteBufCodecs.holderSet(Registries.BLOCK), MoreOresEnchantmentEffect::blockTag,
                                 ByteBufCodecs.holderSet(Registries.BLOCK), MoreOresEnchantmentEffect::block,
                                 ByteBufCodecs.FLOAT, MoreOresEnchantmentEffect::chance, MoreOresEnchantmentEffect::new);
}