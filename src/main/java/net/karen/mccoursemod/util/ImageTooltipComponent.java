package net.karen.mccoursemod.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;

public class ImageTooltipComponent implements ClientTooltipComponent, TooltipComponent {
    private final ResourceLocation texture; // IMAGE appears on screen
    private final int width, height; // WIDTH and HEIGHT of texture
    private final Component text; // TEXT appears next to ICON

    public ImageTooltipComponent(ResourceLocation texture, int width, int height, Component text) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    @Override
    public void renderImage(@NotNull Font font, int x, int y, int width, int height, @NotNull GuiGraphics graphics) {
        Matrix3x2fStack matrix = graphics.pose();
        // Render Image -> Example: Icon 8x8, Icon 9x9, Icon 16x16, etc. (width x height)
        matrix.pushMatrix();
        graphics.blit(texture, x, y, 0, 0, width, height, width, height); // Render TEXTURE
        matrix.popMatrix();
        // Render Text -> Example: [ICON] text...
        matrix.pushMatrix();
        int textX = x + width + 4; // 4px image spacing
        int textY = y + (height - font.lineHeight) / 2; // Center vertically
        graphics.drawString(font, text, textX, textY, 0xFFFFFF, true); // Render TEXT
        matrix.popMatrix();
    }

    // Image height
    @Override public int getHeight(@NotNull Font font) { return height; }

    // Image width + spacing + text width
    @Override public int getWidth(@NotNull Font font) { return width + 4 + font.width(text); }
}