package net.karen.mccoursemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.karen.mccoursemod.block.entity.KaupenFurnaceBlockEntity;
import net.karen.mccoursemod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class KaupenFurnaceBlock extends AbstractFurnaceBlock {
    public static final MapCodec<KaupenFurnaceBlock> CODEC = simpleCodec(KaupenFurnaceBlock::new);

    public KaupenFurnaceBlock(Properties properties) { super(properties); }

    @Override
    protected @NotNull MapCodec<? extends AbstractFurnaceBlock> codec() {
        return CODEC;
    }

    @Override
    protected void openContainer(Level level, @NotNull BlockPos pos, @NotNull Player player) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof KaupenFurnaceBlockEntity) {
            player.openMenu(new SimpleMenuProvider((MenuProvider) blockentity,
                                                   Component.literal("Kaupen Furnace")), pos);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    public void animateTick(BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                            @NotNull RandomSource source) {
        if (state.getValue(LIT)) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = pos.getY();
            double d2 = (double)pos.getZ() + 0.5D;
            if (source.nextDouble() < 0.1D) {
                level.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS,
                                     1.0F, 1.0F, false);
            }

            Direction direction = state.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d4 = source.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
            double d6 = source.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
            level.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7,
                              0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7,
                              0.0D, 0.0D, 0.0D);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new KaupenFurnaceBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<T> entityType) {
        return createFurnaceTicker(level, entityType, ModBlockEntities.KAUPEN_FURNACE_BLOCK_ENTITY.get());
    }
}