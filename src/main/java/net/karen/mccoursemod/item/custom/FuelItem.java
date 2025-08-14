package net.karen.mccoursemod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.FuelValues;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class FuelItem extends Item {
    private final int burnTime;

    public FuelItem(Properties properties, int burnTime) {
        super(properties);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(@NotNull ItemStack stack,
                           @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
        return this.burnTime;
    }
}