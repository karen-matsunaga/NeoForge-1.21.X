package net.karen.mccoursemod.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.karen.mccoursemod.util.ChatUtils;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;

public record HammerTooltip(int radius, int color) implements TooltipProvider {
    // CODEC
    public static final Codec<HammerTooltip> CODEC =
           RecordCodecBuilder.create(instance ->
                                     instance.group(Codec.INT.optionalFieldOf("radius", 1)
                                                             .forGetter(HammerTooltip::radius),
                                                    Codec.INT.fieldOf("color")
                                                             .forGetter(HammerTooltip::color))
                                             .apply(instance, HammerTooltip::new));

    // STREAM CODEC
    public static final StreamCodec<ByteBuf, HammerTooltip> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.INT, HammerTooltip::radius,
                                 ByteBufCodecs.INT, HammerTooltip::color,
                                 HammerTooltip::new);

    // DEFAULT METHOD - Hammer tooltip description
    @Override
    public void addToTooltip(Item.@NotNull TooltipContext context, @NotNull Consumer<Component> consumer,
                             @NotNull TooltipFlag flag, @NotNull DataComponentGetter getter) {
        int value = radius * 2 + 1;
        consumer.accept(ChatUtils.componentLiteralIntColor("§6Hammer breaks: §r" + value + "§fx§r" + value +
                                                           " §6radius area.§r", color));
    }
}