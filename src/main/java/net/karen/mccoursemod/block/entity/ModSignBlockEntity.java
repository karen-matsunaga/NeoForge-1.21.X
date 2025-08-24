package net.karen.mccoursemod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ModSignBlockEntity extends SignBlockEntity {
    public ModSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MOD_SIGN.get(), pos, state);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() { return ModBlockEntities.MOD_SIGN.get(); }
}