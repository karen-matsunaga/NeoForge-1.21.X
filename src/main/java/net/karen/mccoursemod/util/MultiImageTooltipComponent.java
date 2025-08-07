package net.karen.mccoursemod.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class MultiImageTooltipComponent implements ClientTooltipComponent, TooltipComponent {
    private final List<ItemStack> icons;
    private final int size;
    private final String text;

    public MultiImageTooltipComponent(List<ItemStack> icons, int size, String text) {
        this.icons = icons;
        this.size = size;
        this.text = text;
    }

    @Override
    public int getHeight(@NotNull Font font) { return size; }

    @Override
    public int getWidth(@NotNull Font font) {
        return font.width(text) + icons.size() * (size + 2);
    }

    @Override
    public void renderText(@NotNull GuiGraphics graphics, @NotNull Font font, int x, int y) {
        for (ItemStack stack : icons) {
            graphics.renderItemDecorations(font, stack, x + 75, y - 4, text);
        }
    }

    @Override
    public void renderImage(@NotNull Font font, int x, int y, int width, int height, @NotNull GuiGraphics graphics) {
        renderText(graphics, font, x, y);
        int offsetX = x + font.width(text) + 5; // Space between text and icons
        for (ItemStack stack : icons) {
            graphics.renderItem(stack, offsetX, y);
            offsetX += size + 2;
        }
    }
}