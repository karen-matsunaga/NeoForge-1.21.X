package net.karen.mccoursemod.component;

import com.mojang.serialization.Codec;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.custom.FoundBlock;
import net.karen.mccoursemod.component.custom.HammerTooltip;
import net.karen.mccoursemod.component.custom.ItemTooltip;
import net.karen.mccoursemod.component.custom.ShiftTooltip;
import net.karen.mccoursemod.enchantment.custom.AutoSmeltEnchantmentEffect;
import net.karen.mccoursemod.enchantment.custom.MoreOresEnchantmentEffect;
import net.karen.mccoursemod.enchantment.custom.RainbowEnchantmentEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    // Data Component Type Registries
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
           DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MccourseMod.MOD_ID);

    // Enchantment Data Component Type Registries
    public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_COMPONENT_TYPES =
           DeferredRegister.createDataComponents(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, MccourseMod.MOD_ID);

    // Coordinates data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES =
           register("coordinates", builder -> builder.persistent(BlockPos.CODEC));

    // Found Block data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FoundBlock>> FOUND_BLOCK =
           register("found_block", builder -> builder.persistent(FoundBlock.CODEC));

    // Unlock data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> UNLOCK =
           register("unlock", builder ->
                    builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));

    // Shift tooltip data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ShiftTooltip>> SHIFT_TOOLTIP =
           register("shift_tooltip", builder ->
                    builder.persistent(ShiftTooltip.CODEC).networkSynchronized(ShiftTooltip.STREAM_CODEC));

    // Item tooltip data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemTooltip>> ITEM_TOOLTIP =
           register("item_tooltip", builder ->
                    builder.persistent(ItemTooltip.CODEC).networkSynchronized(ItemTooltip.STREAM_CODEC));

    // Hammer tooltip data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<HammerTooltip>> HAMMER_TOOLTIP =
           register("hammer_tooltip", builder ->
                    builder.persistent(HammerTooltip.CODEC).networkSynchronized(HammerTooltip.STREAM_CODEC));

    // Mccourse Mod Bottle data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> STORED_LEVELS =
           register("stored_levels", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    // More Ores Enchantment Effect data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MoreOresEnchantmentEffect>>
           MORE_ORES_ENCHANTMENT_EFFECT = registerEnch("more_ores_enchantment_effect",
                                                       builder ->
                                                       builder.persistent(MoreOresEnchantmentEffect.CODEC)
                                                              .networkSynchronized(MoreOresEnchantmentEffect.STREAM_CODEC));

    // Rainbow Enchantment Effect data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<RainbowEnchantmentEffect>>
           RAINBOW_ENCHANTMENT_EFFECT = registerEnch("rainbow_enchantment_effect", builder ->
                                                     builder.persistent(RainbowEnchantmentEffect.CODEC)
                                                            .networkSynchronized(RainbowEnchantmentEffect.STREAM_CODEC));

    // Auto Smelt Enchantment Effect data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AutoSmeltEnchantmentEffect>>
           AUTO_SMELT_ENCHANTMENT_EFFECT = registerEnch("auto_smelt_enchantment_effect",
                                                        builder ->
                                                        builder.persistent(AutoSmeltEnchantmentEffect.CODEC)
                                                               .networkSynchronized(AutoSmeltEnchantmentEffect.STREAM_CODEC));

    // Registry all custom Data Component
    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>>
                   register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    // Registry all custom Enchantment Component
    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>>
                   registerEnch(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return ENCHANTMENT_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    // Registry all custom Data Component on bus group event
    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
        ENCHANTMENT_COMPONENT_TYPES.register(eventBus);
    }
}