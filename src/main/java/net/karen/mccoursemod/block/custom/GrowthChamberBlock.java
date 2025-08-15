package net.karen.mccoursemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.karen.mccoursemod.block.entity.GrowthChamberBlockEntity;
import net.karen.mccoursemod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class GrowthChamberBlock extends BaseEntityBlock {
    public static final MapCodec<GrowthChamberBlock> CODEC = simpleCodec(GrowthChamberBlock::new);

    public GrowthChamberBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new GrowthChamberBlockEntity(blockPos, blockState);
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected @NotNull InteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state,
                                                   Level level, @NotNull BlockPos pos,
                                                   @NotNull Player player, @NotNull InteractionHand hand,
                                                   @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof GrowthChamberBlockEntity growthChamberBlockEntity) {
                (player).openMenu(new SimpleMenuProvider(growthChamberBlockEntity,
                                                                        Component.literal("Growth Chamber")), pos);
            }
            else { throw new IllegalStateException("Our Container provider is missing!"); }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) { return null; }
        return createTickerHelper(blockEntityType, ModBlockEntities.GROWTH_CHAMBER_BE.get(),
                                  (level1, blockPos, blockState,
                                  blockEntity) -> blockEntity.tick(level1, blockPos, blockState));
    }
}