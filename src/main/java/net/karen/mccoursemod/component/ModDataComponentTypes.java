package net.karen.mccoursemod.component;

import com.mojang.serialization.Codec;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.enchantment.custom.MoreOresEnchantmentEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
           DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MccourseMod.MOD_ID);

    public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_COMPONENT_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, MccourseMod.MOD_ID);

    // Coordinates custom data component -> X, Y, Z positions
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES =
           register("coordinates", builder -> builder.persistent(BlockPos.CODEC));

    // Magnet custom data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> MAGNET =
           register("magnet", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    // Auto Smelt custom data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> AUTO_SMELT =
           register("auto_smelt", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    // Rainbow custom data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> RAINBOW =
           register("rainbow", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    // More Ores custom data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> MORE_ORES =
           register("more_ores", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    // Gem Effect custom data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> GEM_EFFECT =
           register("gem_effect", builder ->
                    builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));

    // Custom tooltip data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CustomTooltip>> CUSTOM_TOOLTIP =
           register("custom_tooltip", builder ->
                    builder.persistent(CustomTooltip.CODEC).networkSynchronized(CustomTooltip.STREAM_CODEC));

    // Mccourse Mod Bottle
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> STORED_LEVELS =
           register("stored_levels", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    // TEST
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MoreOresEnchantmentEffect>>
            MORE_ORES_ENCHANTMENT_EFFECT =
            ENCHANTMENT_COMPONENT_TYPES.register("more_ores_enchantment_effect", () ->
                    DataComponentType.<MoreOresEnchantmentEffect>builder()
                                     .persistent(MoreOresEnchantmentEffect.CODEC)
                                     .networkSynchronized(MoreOresEnchantmentEffect.STREAM_CODEC).build());

    // Registry all custom Data Component
    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>>
                   register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    // Registry all custom Data Component on bus group event
    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
        ENCHANTMENT_COMPONENT_TYPES.register(eventBus);
    }
}