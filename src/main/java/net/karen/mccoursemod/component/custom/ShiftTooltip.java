package net.karen.mccoursemod.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.*;

public record ShiftTooltip(List<String> texts,
                           List<ChatFormatting> colors, boolean isTrans) implements TooltipProvider {
    // CODEC
    public static final Codec<ShiftTooltip> CODEC =
           RecordCodecBuilder.create(instance ->
                                     instance.group(Codec.STRING.listOf().fieldOf("texts")
                                                                         .forGetter(ShiftTooltip::texts),
                                                    ChatFormatting.CODEC.listOf().fieldOf("colors")
                                                                        .forGetter(ShiftTooltip::colors),
                                                    Codec.BOOL.fieldOf("isTrans")
                                                              .forGetter(ShiftTooltip::isTrans))
                                             .apply(instance, ShiftTooltip::new));

    // STREAM CODEC
    public static final StreamCodec<ByteBuf, ShiftTooltip> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()), ShiftTooltip::texts,
                                 ByteBufCodecs.fromCodec(ChatFormatting.CODEC)
                                              .apply(ByteBufCodecs.list()), ShiftTooltip::colors,
                                 ByteBufCodecs.BOOL, ShiftTooltip::isTrans,
                                 ShiftTooltip::new);

    // DEFAULT METHOD - Adds TEXTS any line
    @Override
    public List<String> texts() {
        return texts;
    }

    // DEFAULT METHOD - Adds COLORS any line
    @Override
    public List<ChatFormatting> colors() {
        return colors;
    }

    // DEFAULT METHOD - (True) COMPONENT TRANSLATABLE or (False) COMPONENT LITERAL
    @Override
    public boolean isTrans() {
        return isTrans;
    }

    // DEFAULT METHOD - Adds TOOLTIP at the beginning
    @Override
    public void addToTooltip(Item.@NotNull TooltipContext context, @NotNull Consumer<Component> consumer,
                             @NotNull TooltipFlag flag, @NotNull DataComponentGetter dataComp) {
        if (isTrans) {
            if (Screen.hasShiftDown() && !texts().isEmpty() && texts().size() > 1) {
                consumer.accept(componentTranslatable(texts().getFirst(), colors().getFirst()));
            }
            else { consumer.accept(componentTranslatable(texts().get(1), colors().get(1))); }
        }
        else {
            if (Screen.hasShiftDown() && !texts().isEmpty() && texts().size() > 1) {
                consumer.accept(componentLiteral(texts().getFirst(), colors().getFirst()));
            }
            else { consumer.accept(componentLiteral(texts().get(1), colors().get(1))); }
        }
    }
}