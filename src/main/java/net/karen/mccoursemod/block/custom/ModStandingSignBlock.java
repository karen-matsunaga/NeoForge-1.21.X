package net.karen.mccoursemod.block.custom;

import net.karen.mccoursemod.block.entity.ModSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class ModStandingSignBlock extends StandingSignBlock {
    public ModStandingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new ModSignBlockEntity(pos, state);
    }
}