package net.karen.mccoursemod.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import java.util.HashMap;
import java.util.Map;

public record RainbowEnchantmentEffect(Map<Block, TagKey<Block>> map) {
    // Rainbow Enchantment Effect -> CODEC
    public static final Codec<RainbowEnchantmentEffect> CODEC =
           RecordCodecBuilder.create(instance ->
                     instance.group(Codec.unboundedMap(BuiltInRegistries.BLOCK.byNameCodec(),
                                                       TagKey.codec(Registries.BLOCK)).fieldOf("map")
                                         .forGetter(RainbowEnchantmentEffect::map))
                             .apply(instance, RainbowEnchantmentEffect::new));

    // Rainbow Enchantment Effect -> MAP STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, Map<Block, TagKey<Block>>> MAP_STREAM_CODEC =
           ByteBufCodecs.map(HashMap::new, ByteBufCodecs.fromCodec(BuiltInRegistries.BLOCK.byNameCodec()),
                                           ByteBufCodecs.fromCodec(TagKey.codec(Registries.BLOCK)));

    // Rainbow Enchantment Effect -> STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, RainbowEnchantmentEffect> STREAM_CODEC =
           MAP_STREAM_CODEC.map(RainbowEnchantmentEffect::new, RainbowEnchantmentEffect::map);
}