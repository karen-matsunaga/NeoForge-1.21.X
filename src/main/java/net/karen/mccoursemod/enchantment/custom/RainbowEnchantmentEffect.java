package net.karen.mccoursemod.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

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

    // CUSTOM METHOD - RAINBOW Enchantment Effect EVENT
    public static void rainbowEnch(ItemStack tool, BlockState state,
                                   int hasFortune, List<ItemStack> finalDrops,
                                   AtomicBoolean cancelVanillaDrop) {
        EnchantmentHelper.runIterationOnItem(tool, (holder, holderLvl) -> {
            RainbowEnchantmentEffect effects = // Get all values of Rainbow Enchantment Data Component
                   holder.value().effects().get(ModDataComponentTypes.RAINBOW_ENCHANTMENT_EFFECT.get());
            if (effects != null && effects.map() != null) {
                Map<Block, TagKey<Block>> rainbowMap = effects.map();
                for (Map.Entry<Block, TagKey<Block>> entry : rainbowMap.entrySet()) {
                    Block entryKey = entry.getKey(); // Block Ore Blocks
                    TagKey<Block> entryValue = entry.getValue(); // Block Ores
                    if (state.is(entryValue)) {
                       ItemStack rainbowDrop = new ItemStack(entryKey);
                       rainbowDrop.setCount((rainbowDrop.getCount() * hasFortune) * holderLvl);
                       finalDrops.add(rainbowDrop);
                       cancelVanillaDrop.set(true);
                    }
                }
            }
        });
    }
}