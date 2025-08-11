package net.karen.mccoursemod.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public record RainbowEnchantmentEffect(HolderSet<Block> block, TagKey<Block> blockTagKey) {
    // Rainbow Enchantment Effect -> CODEC
    public static final Codec<RainbowEnchantmentEffect> CODEC =
           RecordCodecBuilder.create(instance ->
                     instance.group(RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("block")
                                                  .forGetter(RainbowEnchantmentEffect::block),
                                    TagKey.codec(Registries.BLOCK).fieldOf("blockTagKey")
                                                                  .forGetter(RainbowEnchantmentEffect::blockTagKey))
                             .apply(instance, RainbowEnchantmentEffect::new));

    // Rainbow Enchantment Effect -> STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, RainbowEnchantmentEffect> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.holderSet(Registries.BLOCK), RainbowEnchantmentEffect::block,
                                 ByteBufCodecs.fromCodec(TagKey.codec(Registries.BLOCK)), RainbowEnchantmentEffect::blockTagKey,
                                 RainbowEnchantmentEffect::new);
}