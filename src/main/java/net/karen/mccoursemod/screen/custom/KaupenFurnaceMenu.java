package net.karen.mccoursemod.screen.custom;

import net.karen.mccoursemod.recipe.ModRecipes;
import net.karen.mccoursemod.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;
import org.jetbrains.annotations.NotNull;

public class KaupenFurnaceMenu extends AbstractFurnaceMenu {
    public KaupenFurnaceMenu(int id, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
        this(id, inventory, new SimpleContainer(3));
    }

    public KaupenFurnaceMenu(int id, Inventory inventory,
                             Container container, ContainerData data) {
        super(ModMenuTypes.KAUPEN_FURNACE_MENU.get(), ModRecipes.KAUPEN_FURNACE_TYPE.get(),
              RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, id, inventory, container, data);
    }

    public KaupenFurnaceMenu(int id, Inventory inventory, SimpleContainer container) {
        super(ModMenuTypes.KAUPEN_FURNACE_MENU.get(), ModRecipes.KAUPEN_FURNACE_TYPE.get(),
              RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE,
              id, inventory, container, new SimpleContainerData(4));
    }

    @Override
    protected boolean isFuel(@NotNull ItemStack stack) {
        return true;
    }
}