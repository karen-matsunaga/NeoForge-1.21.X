package net.karen.mccoursemod.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.karen.mccoursemod.util.ChatUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;

public record ItemTooltip(String text,
                          ChatFormatting color, boolean isTranslatable) implements TooltipProvider {
    // CODEC
    public static final Codec<ItemTooltip> CODEC =
           RecordCodecBuilder.create(instance ->
                                     instance.group(Codec.STRING.fieldOf("name").forGetter(ItemTooltip::text),
                                                    ChatFormatting.CODEC.fieldOf("color").forGetter(ItemTooltip::color),
                                                    Codec.BOOL.fieldOf("isTranslatable").forGetter(ItemTooltip::isTranslatable))
                                     .apply(instance, ItemTooltip::new));

    // STREAM CODEC
    public static final StreamCodec<ByteBuf, ItemTooltip> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.STRING_UTF8, ItemTooltip::text,
                                 ByteBufCodecs.fromCodec(ChatFormatting.CODEC), ItemTooltip::color,
                                 ByteBufCodecs.BOOL, ItemTooltip::isTranslatable,
                                 ItemTooltip::new);

    // DEFAULT METHOD - Adds TEXT any line
    @Override
    public String text() {
        return text;
    }

    // DEFAULT METHOD - Adds COLOR any line
    @Override
    public ChatFormatting color() {
        return color;
    }

    // DEFAULT METHOD - (True) COMPONENT TRANSLATABLE or (False) COMPONENT LITERAL
    @Override
    public boolean isTranslatable() {
        return isTranslatable;
    }

    // DEFAULT METHOD - Adds TOOLTIP at the beginning
    @Override
    public void addToTooltip(Item.@NotNull TooltipContext context, @NotNull Consumer<Component> consumer,
                             @NotNull TooltipFlag flag, @NotNull DataComponentGetter getter) {
        if (isTranslatable) { consumer.accept(ChatUtils.componentTranslatable(text(), color())); }
        else { consumer.accept(ChatUtils.componentLiteral(text(), color())); }
    }
}