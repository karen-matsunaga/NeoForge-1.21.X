package net.karen.mccoursemod.screen.custom;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.*;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class CraftingPlusScreen extends AbstractRecipeBookScreen<CraftingPlusMenu>
                                implements RecipeUpdateListener {
    private static final ResourceLocation CRAFTING_TABLE_LOCATION =
            ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                  "textures/gui/crafting_plus/crafting_plus_gui.png");

    public CraftingPlusScreen(CraftingPlusMenu menu,
                              Inventory inventory, Component title) {
        super(menu, new CraftingRecipeBookComponent(menu), inventory, title);
    }

    @Override
    protected void init() {
        this.imageHeight = 217;
        this.imageWidth = 176;
        super.init();
        this.titleLabelX = 29;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int k = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, CRAFTING_TABLE_LOCATION, k, j,
                         0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    protected @NotNull ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(this.leftPos + 148, this.topPos + 70);
    }
}