package net.karen.mccoursemod.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ImageTooltipComponent implements ClientTooltipComponent, TooltipComponent {
    private final ItemStack item;
    private final int width, height; // WIDTH and HEIGHT of texture
    private final String text; // TEXT appears next to ICON

    public ImageTooltipComponent(ItemStack item, int width, int height, String text) {
        this.item = item;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    @Override
    public void renderText(@NotNull GuiGraphics graphics, @NotNull Font font, int x, int y) {
        graphics.drawString(font, I18n.get("tooltip.mccoursemod.more_ores.tooltip"), x, y, 0xFFFFFF, true);
        graphics.renderItemDecorations(font, item, x + 95, y - 4, text); // Render TEXT
    }

    @Override
    public void renderImage(@NotNull Font font, int x, int y, int width, int height, @NotNull GuiGraphics graphics) {
        // Render Image -> Example: Icon 8x8, Icon 9x9, Icon 16x16, etc. (width x height)
        graphics.renderItem(item, x, y); // Render TEXTURE ITEM
        // Render Text -> Example: [ICON] text...
        renderText(graphics, font, x, y);
    }

    // Image height
    @Override public int getHeight(@NotNull Font font) { return height; }

    // Image width
    @Override public int getWidth(@NotNull Font font) { return width; }
}