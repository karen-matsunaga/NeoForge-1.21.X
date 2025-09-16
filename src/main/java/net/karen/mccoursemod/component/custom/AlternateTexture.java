package net.karen.mccoursemod.component.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public record AlternateTexture(DataComponentType<?> dataComponentType)
              implements ConditionalItemModelProperty {
    public static final MapCodec<AlternateTexture> MAP_CODEC =
           RecordCodecBuilder.mapCodec((instance) ->
                     instance.group(BuiltInRegistries.DATA_COMPONENT_TYPE.byNameCodec().fieldOf("dataComponentType")
                                                                                       .forGetter(AlternateTexture::dataComponentType))
                             .apply(instance, AlternateTexture::new));

    public boolean get(@NotNull ItemStack stack, @Nullable ClientLevel level,
                       @Nullable LivingEntity entity, int i, @NotNull ItemDisplayContext itemDisplayContext) {
        DataComponentType<?> data = this.dataComponentType;
        if (data != null && data.equals(ModDataComponentTypes.STORED_LEVELS.get())) {
            Integer storedValue = stack.get(ModDataComponentTypes.STORED_LEVELS.get());
            return storedValue != null && storedValue > 0;
        }
        return false;
    }

    public @NotNull MapCodec<AlternateTexture> type() { return MAP_CODEC; }
}