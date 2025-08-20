package net.karen.mccoursemod.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.*;
import static net.karen.mccoursemod.util.ChatUtils.gold;

public record CustomTooltip(Component line1) implements TooltipProvider {
    // CODEC
    public static final Codec<CustomTooltip> CODEC =
           RecordCodecBuilder.create(instance ->
                     instance.group(ComponentSerialization.CODEC.fieldOf("Component")
                                                                .forGetter(CustomTooltip::line1))
                                                                .apply(instance, CustomTooltip::new));

    // STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, CustomTooltip> STREAM_CODEC =
           StreamCodec.composite(ComponentSerialization.STREAM_CODEC, CustomTooltip::line1, CustomTooltip::new);

    @Override
    public Component line1() {
        return line1;
    }

    @Override
    public void addToTooltip(Item.@NotNull TooltipContext context, @NotNull Consumer<Component> consumer,
                             @NotNull TooltipFlag flag, @NotNull DataComponentGetter dataComp) {
        consumer.accept(line1);
        consumer.accept(componentLiteral("tooltip.mccoursemod.auto_smelt.tooltip", gold));
    }
}