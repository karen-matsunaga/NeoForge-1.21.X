package net.karen.mccoursemod.block.custom;

import net.karen.mccoursemod.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public class KohlrabiCropBlock extends CropBlock {
    // Max age crop block
    public static final int MAX_AGE = 6;
    // Kohlrabi's stage 0 to 6
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);

    public KohlrabiCropBlock(Properties properties) { super(properties); }

    @Override
    protected @NotNull ItemLike getBaseSeedId() { return ModItems.KOHLRABI_SEEDS.get(); } // Kohlrabi's seeds

    @Override
    public @NotNull IntegerProperty getAgeProperty() { return AGE; } // Min and Max age crop block

    @Override
    public int getMaxAge() { return MAX_AGE; } // Max age crop block

    // Block state of Kohlrabi's seeds to transform on Kohlrabi's item
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(AGE); }
}