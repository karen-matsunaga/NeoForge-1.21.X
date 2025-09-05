package net.karen.mccoursemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.karen.mccoursemod.screen.custom.CraftingPlusMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class CraftingPlusBlock extends CraftingTableBlock {
    public static final MapCodec<CraftingPlusBlock> CODEC = simpleCodec(CraftingPlusBlock::new);
    private static final Component CONTAINER_TITLE =
            Component.translatable("mccoursemod.container.crafting");

    @Override
    public @NotNull MapCodec<? extends CraftingPlusBlock> codec() {
        return CODEC;
    }

    public CraftingPlusBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level,
                                                        @NotNull BlockPos pos, @NotNull Player player,
                                                        @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected @NotNull MenuProvider getMenuProvider(@NotNull BlockState state,
                                                    @NotNull Level level, @NotNull BlockPos pos) {
        return new SimpleMenuProvider((i, inventory, player) ->
                                      new CraftingPlusMenu(i, inventory, ContainerLevelAccess.create(level, pos)),
                                      CONTAINER_TITLE);
    }
}