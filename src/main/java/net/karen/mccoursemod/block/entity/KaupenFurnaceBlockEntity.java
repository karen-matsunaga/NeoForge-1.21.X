package net.karen.mccoursemod.block.entity;

import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.recipe.ModRecipes;
import net.karen.mccoursemod.screen.custom.KaupenFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KaupenFurnaceBlockEntity extends AbstractFurnaceBlockEntity implements MenuProvider {
    // List of items that burned on Kaupen custom furnace using ticks
    private static final Map<Item, Integer> BURN_DURATION_MAP =
            Map.of(ModItems.PEAT_BRICK.get(), 400, ModItems.KOHLRABI.get(), 200, Items.BLAZE_POWDER, 800);

    public KaupenFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KAUPEN_FURNACE_BLOCK_ENTITY.get(), pos, state, ModRecipes.KAUPEN_FURNACE_TYPE.get());
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("block.mccoursemod.kaupen_furnace");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int container, @NotNull Inventory inventory) {
        return new KaupenFurnaceMenu(container, inventory, this, this.dataAccess);
    }

    @Override
    protected int getBurnDuration(@NotNull FuelValues fuelValues, @NotNull ItemStack stack) {
        return BURN_DURATION_MAP.getOrDefault(stack.getItem(), 0);
    }

    // CUSTOM METHOD - List of items that burned on Kaupen custom furnace
    public static List<ItemStack> getValidFuels() {
        List<ItemStack> fuels = new ArrayList<>();
        BuiltInRegistries.ITEM.forEach(item -> {
            if (BURN_DURATION_MAP.containsKey(item)) { fuels.add(new ItemStack(item)); }
        });
        return fuels;
    }
}