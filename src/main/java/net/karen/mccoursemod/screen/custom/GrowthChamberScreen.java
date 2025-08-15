package net.karen.mccoursemod.screen.custom;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GrowthChamberScreen extends AbstractContainerScreen<GrowthChamberMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,"textures/gui/growth_chamber/growth_chamber_gui.png");

    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,"textures/gui/arrow_progress.png");

    public GrowthChamberScreen(GrowthChamberMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0,
                         imageWidth, imageHeight, 256, 256);
        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics graphics, int x, int y) {
        if (menu.isCrafting()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE,x + 73, y + 35, 0, 0,
                          menu.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }
}