package net.karen.mccoursemod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class MinerBowArrowEntity extends AbstractArrow {
    public MinerBowArrowEntity(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public MinerBowArrowEntity(LivingEntity owner, Level level) {
        super(EntityType.ARROW, owner, level, new ItemStack(Items.ARROW), null);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);
        BlockPos center = result.getBlockPos();
        Level level = this.level();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    BlockPos pos = center.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    if (state.getDestroySpeed(level, pos) >= 0 && !state.isAir()) {
                        level.destroyBlock(pos, true);
                    }
                }
            }
        }
        this.discard(); // Remove the arrow after impact
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.ARROW);
    }
}