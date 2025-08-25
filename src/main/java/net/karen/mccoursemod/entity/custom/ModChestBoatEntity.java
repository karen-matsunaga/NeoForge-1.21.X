package net.karen.mccoursemod.entity.custom;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

public class ModChestBoatEntity extends ChestBoat {
    private final NonNullList<ItemStack> itemStacks;

    public ModChestBoatEntity(EntityType<? extends ChestBoat> entityType, Level level,
                              Supplier<Item> item) {
        super(entityType, level, item);
        this.itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
    }

    protected double rideHeight(EntityDimensions entityDimensions) {
        return (entityDimensions.height() / 3.0F);
    }

    public @NotNull NonNullList<ItemStack> getItemStacks() {
        return this.itemStacks;
    }
}