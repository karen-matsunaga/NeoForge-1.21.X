package net.karen.mccoursemod.util;

import com.mojang.datafixers.util.Either;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ChatUtils {
    // VANILLA colors
    public static ChatFormatting blue = ChatFormatting.BLUE, darkBlue = ChatFormatting.DARK_BLUE,
                                 aqua = ChatFormatting.AQUA, darkAqua = ChatFormatting.DARK_AQUA,
                                 purple = ChatFormatting.LIGHT_PURPLE, darkPurple = ChatFormatting.DARK_PURPLE,
                                 green = ChatFormatting.GREEN, darkGreen = ChatFormatting.DARK_GREEN,
                                 gray = ChatFormatting.GRAY, darkGray = ChatFormatting.DARK_GRAY,
                                 yellow = ChatFormatting.YELLOW, gold = ChatFormatting.GOLD,
                                 red = ChatFormatting.RED, darkRed = ChatFormatting.DARK_RED,
                                 black = ChatFormatting.BLACK, white = ChatFormatting.WHITE,
                                 bold = ChatFormatting.BOLD, italic = ChatFormatting.ITALIC;

    // RGB colors
    public static final int[] COLORS = { 0xff5555, 0xffaa00, 0xffff55, 0x55ff55,
                                         0x55ffff, 0x5555ff, 0xff55ff };

    // Glowing Blocks X-RAY block bounding boxes
    public static final Map<TagKey<Block>, Integer> renderColors =
           Map.ofEntries(Map.entry(Tags.Blocks.ORES_COAL, color(169, 169,169)),
                         Map.entry(Tags.Blocks.ORES_COPPER, color(255, 140, 0)),
                         Map.entry(Tags.Blocks.ORES_DIAMOND, color(0, 254, 255)),
                         Map.entry(Tags.Blocks.ORES_EMERALD, color(49, 200, 49)),
                         Map.entry(Tags.Blocks.ORES_GOLD, color(255, 215, 0)),
                         Map.entry(Tags.Blocks.ORES_IRON, color(211, 211, 211)),
                         Map.entry(Tags.Blocks.ORES_LAPIS, color(0, 0, 255)),
                         Map.entry(Tags.Blocks.ORES_REDSTONE, color(179, 0, 0)),
                         Map.entry(Tags.Blocks.ORES_NETHERITE_SCRAP, color(210, 44, 248)),
                         Map.entry(ModTags.Blocks.MCCOURSE_MOD_ORES, color(255, 192, 235)),
                         Map.entry(ModTags.Blocks.SPECIAL_METAL_DETECTOR_VALUABLES, color(204, 0, 0)));

    // CUSTOM METHOD - Glowing Blocks X-RAY block bounding box colors
    public static int color(int red, int green, int blue) {
        return ARGB.color(255, red, green, blue);
    }

    // CUSTOM METHOD - COMPONENT LITERAL without color
    public static Component standardLiteral(String message) {
        return Component.literal(message);
    }

    // CUSTOM METHOD - COMPONENT TRANSLATABLE without color
    public static Component standardTranslatable(String message) {
        return Component.translatable(message);
    }

    // CUSTOM METHOD - COMPONENT LITERAL with one color
    public static Component componentLiteral(String message, ChatFormatting color) {
        return Component.literal(message).withStyle(color);
    }

    // CUSTOM METHOD - COMPONENT LITERAL with int color
    public static Component componentLiteralIntColor(String message, int color) {
        return Component.literal(message).withStyle(Style.EMPTY).withColor(color);
    }

    // CUSTOM METHOD - COMPONENT TRANSLATABLE with one color
    public static Component componentTranslatable(String message, ChatFormatting color) {
        return Component.translatable(message).withStyle(color);
    }

    // CUSTOM METHOD - COMPONENT TRANSLATABLE with int color
    public static Component componentTranslatableIntColor(String message, int color) {
        return Component.translatable(message).withStyle(Style.EMPTY).withColor(color);
    }

    // CUSTOM METHOD - COMPONENT LITERAL with one color
    public static Component componentLiteralBold(String message, ChatFormatting color) {
        return Component.literal(message).withStyle(color).withStyle(bold);
    }

    // CUSTOM METHOD - COMPONENT TRANSLATABLE with one color
    public static Component componentTranslatableBold(String message, ChatFormatting color) {
        return Component.translatable(message).withStyle(color).withStyle(bold);
    }

    // CUSTOM METHOD - Message appears on screen without BOLD format
    public static void player(Player player, String message, ChatFormatting color) {
        player.displayClientMessage(componentLiteral(message, color), true);
    }

    // CUSTOM METHOD - Message appears on screen without COLOR
    public static void playerDefault(Player player, String message) {
        player.displayClientMessage(standardLiteral(message), true);
    }

    // CUSTOM METHOD - Message appears on screen without BOLD format (en_us.json file)
    public static void playerDisplayTranslatable(Player player, String message) {
        player.displayClientMessage(standardTranslatable(message), true);
    }

    // CUSTOM METHOD - Message appears on SCREEN without BOLD format
    public static void playDisplayLiteral(Player player, Component message) {
        player.displayClientMessage(message, true);
    }

    // CUSTOM METHOD - Message appears on CHAT without BOLD format
    public static void playDisplayLiteralFalse(Player player, Component message) {
        player.displayClientMessage(message, false);
    }

    // CUSTOM METHOD - Using RGB colors and BOLD format
    public static MutableComponent description(String tooltip, ChatFormatting color,
                                               List<Boolean> curse) {
        boolean curseFalse = curse.get(0), curseTrue = curse.get(1);
        return Component.translatable(tooltip)
                        .withStyle(Style.EMPTY.withColor(color).withBold(curseFalse).withItalic(curseTrue));
    }

    // CUSTOM METHOD - Tooltip Line Literal with RGB colors
    public static void tooltipLineLiteralRGB(List<Component> tooltip,
                                             int[] COLORS, ItemStack stack, String message) {
        int shift = (int) (System.currentTimeMillis() / 200L % COLORS.length); // Calculates color shift based on time
        MutableComponent minerText = Component.literal(""); // Animated text for "Miner Bow" with RGB wave effect
        String text = itemLine(stack.getItem().getDescriptionId(), "item.mccoursemod.", "", "_", " "),
                 on = itemLines(text) + message;
        for (int i = 0; i < on.length(); i++) {
            int colorIndex = (i - shift + COLORS.length) % COLORS.length; // Adjust to move colors from left to right
            minerText.append(Component.literal(String.valueOf(on.charAt(i)))
                     .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLORS[colorIndex]))));
        }
        tooltip.add(minerText);
    }

    // CUSTOM METHOD - Renamed string on TOOLTIP -> Ex: Fortune etc. (Only one word) -> Capitalize First Letters
    public static String itemLine(String var, String old, String value,
                                  String old2, String value2) {
        String name = var.replace(old, value).replace(old2, value2), firstIndex = name.substring(0, 1).toUpperCase();
        return firstIndex + name.substring(1);
    }

    // CUSTOM METHOD - Renamed string on TOOLTIP -> Ex: Mccourse Generator etc. (Two+ words) -> Capitalize First Letters
    public static String itemLines(String input) {
        String[] words = input.split(" "); // Separated words
        StringJoiner capitalized = new StringJoiner(" "); // Spaced words
        for (String word : words) { // For each word
            if (!word.isEmpty()) {
                capitalized.add(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
            }
        }
        return capitalized.toString(); // Joined words with capitalize words
    }

    // CUSTOM METHOD - SPLIT STRING
    public static String splitWord(String word) {
        return word.replace("_", " ");
    }

    // CUSTOM METHOD - Enchantment color
    public static ChatFormatting getEnchantmentColor(Holder<Enchantment> holder) {
        Map<TagKey<Enchantment>, ChatFormatting> enchantmentColors = new HashMap<>();
        enchantmentColors.put(EnchantmentTags.ARMOR_EXCLUSIVE, gold); // FULL ARMOR
        enchantmentColors.put(ModTags.Enchantments.HELMET_ENCHANTMENTS, darkAqua); // HELMET ARMOR
        enchantmentColors.put(ModTags.Enchantments.CHESTPLATE_ENCHANTMENTS, purple); // CHESTPLATE ARMOR
        enchantmentColors.put(ModTags.Enchantments.LEGGINGS_ENCHANTMENTS, darkGray); // LEGGINGS ARMOR
        enchantmentColors.put(ModTags.Enchantments.BOOTS_ENCHANTMENTS, blue); // BOOTS ARMOR
        enchantmentColors.put(ModTags.Enchantments.FISHING_ENCHANTMENTS, yellow); // FISHING ROD
        enchantmentColors.put(ModTags.Enchantments.MINING_ENCHANTMENTS, darkPurple); // PICKAXE, AXE, SHOVEL ETC.
        enchantmentColors.put(ModTags.Enchantments.DURABILITY_ENCHANTMENTS, darkBlue); // UNBREAKING, MENDING
        enchantmentColors.put(ModTags.Enchantments.TRIDENT_ENCHANTMENTS, aqua); // TRIDENT
        enchantmentColors.put(ModTags.Enchantments.SWORD_ENCHANTMENTS, darkRed); // SWORD
        enchantmentColors.put(ModTags.Enchantments.BOW_ENCHANTMENTS, green); // BOW
        enchantmentColors.put(ModTags.Enchantments.CROSSBOW_ENCHANTMENTS, darkGreen); // CROSSBOW
        enchantmentColors.put(ModTags.Enchantments.MACE_ENCHANTMENTS, white); // MACE
        for (Map.Entry<TagKey<Enchantment>, ChatFormatting> value : enchantmentColors.entrySet()) {
            if (holder.is(value.getKey())) { return value.getValue(); }
        }
        return gray;
    }

    // CUSTOM METHOD - Enchantment Icon compatibility
    public static String icon(Holder<Enchantment> holder) {
        String armor = "§6⭐", pick = "§5⛏", hammer = "§3🔨", shield = "§1🛡",
                 bow = "§a\uD83C\uDFF9", sword = "§4\uD83D\uDDE1", trident = "§b\uD83D\uDD31",
                fish = "§e\uD83C\uDFA3", axe = "§5\uD83E\uDE93";
        Map<List<TagKey<Enchantment>>, String> enchantmentIcons = new HashMap<>();
        enchantmentIcons.put(List.of(EnchantmentTags.CURSE), "§c🔥"); // CURSE
        enchantmentIcons.put(List.of(EnchantmentTags.ARMOR_EXCLUSIVE), armor); // ARMOR
        // HELMET, CHESTPLATE, LEGGINGS, BOOTS ARMORS AND MACE
        enchantmentIcons.put(List.of(ModTags.Enchantments.HELMET_ENCHANTMENTS, ModTags.Enchantments.CHESTPLATE_ENCHANTMENTS,
                                     ModTags.Enchantments.LEGGINGS_ENCHANTMENTS, ModTags.Enchantments.BOOTS_ENCHANTMENTS,
                                     ModTags.Enchantments.MACE_ENCHANTMENTS), "");
        enchantmentIcons.put(List.of(ModTags.Enchantments.FISHING_ENCHANTMENTS), fish); // FISHING ROD
        enchantmentIcons.put(List.of(ModTags.Enchantments.MINING_ENCHANTMENTS), pick + " " + axe); // PICKAXE + AXE + SHOVEL
        enchantmentIcons.put(List.of(ModTags.Enchantments.DURABILITY_ENCHANTMENTS),
                                     axe + " " + fish + " " + pick + " " + armor + " " + sword + " " +
                                     bow + " " + trident + " " + hammer + " " + shield); // DURABILITY
        enchantmentIcons.put(List.of(ModTags.Enchantments.TRIDENT_ENCHANTMENTS), trident); // TRIDENT
        enchantmentIcons.put(List.of(ModTags.Enchantments.SWORD_ENCHANTMENTS), sword); // SWORD
        enchantmentIcons.put(List.of(ModTags.Enchantments.BOW_ENCHANTMENTS,
                                     ModTags.Enchantments.CROSSBOW_ENCHANTMENTS), bow); // BOW or CROSSBOW
        for (Map.Entry<List<TagKey<Enchantment>>, String> value : enchantmentIcons.entrySet()) {
            for (TagKey<Enchantment> tagKey : value.getKey()) {
                if (holder.is(tagKey)) { return value.getValue(); }
            }
        }
        return "";
    }

    // CUSTOM METHOD - Display Icon message TOOLTIP -> IMAGE TOOLTIP COMPONENT
    public static void image(List<Either<FormattedText, TooltipComponent>> list,
                             Item item, int width, int height, String text, Boolean bool) {
        if (bool) { list.add(Either.right(new ImageTooltipComponent(new ItemStack(item), width, height, text))); }
    }

    // CUSTOM METHOD - Display Icon messages TOOLTIP -> MULTI IMAGE TOOLTIP COMPONENT
    public static void text(List<Either<FormattedText, TooltipComponent>> list,
                            List<Item> item, int size, String text, Boolean bool) {
        if (bool) {
            list.add(Either.right(new MultiImageTooltipComponent(List.of(new ItemStack(item.getFirst()),
                                                                         new ItemStack(item.get(1))),
                                                                 size, text)));
        }
    }

    // CUSTOM METHOD - GLOWING MOBS message
    public static void glow(Player player, boolean test,
                            String message, String message1) {
        player.displayClientMessage(componentLiteral("Glowing " + (test ? message : message1),
                                                     (test ? green : red)), true);
    }

    // CUSTOM METHOD - Player death message display
    public static Component chatMessage(ResourceKey<Level> level, Player player,
                                        BlockPos pos, ChatFormatting color) {
        String playerName = player.getGameProfile().getName();
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        String deathTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String dimensionPath = level.location().getPath();
        return Component.literal(playerName + " died at [X: " + x + ", Y: " + y + ", Z: " + z + "] " +
                                 deathTime + " " + dimensionName(dimensionPath) + " dimension.")
                        .withStyle(Style.EMPTY.withColor(color).withItalic(false));
    }

    // CUSTOM METHOD - Player death message
    public static String dimensionName(String dimensionPath) {
        return switch (dimensionPath) {
               case "overworld" -> "Overworld";
               case "the_nether" -> "Nether";
               case "the_end" -> "End";
               default -> "Kaupendim";
        };
    }

    // CUSTOM METHOD - Player dimension color name
    public static int dimensionColorName(String dimensionPath) {
        return switch (dimensionPath) {
            case "overworld" -> ARGB.color(125, 218, 88);
            case "the_nether" -> ARGB.color(133, 34, 36);
            case "the_end" -> ARGB.color(217, 91, 255);
            default -> ARGB.color(129, 214, 235);
        };
    }

    // CUSTOM METHOD - Player dimension
    public static Component dimension(String dimensionPath) {
        return Component.literal("Dimension: ")
                        .append(componentLiteralIntColor(dimensionName(dimensionPath), dimensionColorName(dimensionPath)));
    }

    // CUSTOM METHOD - Total light, Skylight and Block light + [X, Y, Z] Coordinates
    public static Component coordinate(double x, double y, double z) {
        return Component.literal("X: ").append(componentLiteral(String.format("%.3f", x), aqua))
                        .append(standardLiteral("  Y: ")).append(componentLiteral(String.format("%.5f", y), purple))
                        .append(standardLiteral("  Z: ")).append(componentLiteral(String.format("%.3f", z), gold));
    }

    // CUSTOM METHOD - Total light, Skylight and Block light numbers
    public static Component light(int totalLight, int skyLight, int blockLight) {
        return Component.literal("Light:").append(customStyle(" " + totalLight, totalLight))
                        .append(standardLiteral("  Sky:")).append(customStyle(" " + skyLight, skyLight))
                        .append(standardLiteral("  Block:")).append(customStyle(" " + blockLight, blockLight));
    }

    // CUSTOM METHOD - Total light, Skylight and Block light colors numbers
    public static Component customStyle(String number, int light) {
        return Component.literal(String.valueOf(number))
                        .setStyle(Style.EMPTY.withColor(light > 6 ? ARGB.color(50, 252, 118)
                                                                  : ARGB.color(255, 24, 24)));
    }

    // CUSTOM METHOD - ITEM NAME
    public static Component rgbItemName(ItemStack stack) {
        int shift = (int) (System.currentTimeMillis() / 200L % COLORS.length); // Calculates color shift based on time
        MutableComponent minerText = Component.literal(""); // Animated text for "Miner Bow" with RGB wave effect
        String text = itemLine(stack.getItem().getDescriptionId(), "item.mccoursemod.", "", "_", " ");
        String on = itemLines(text);
        for (int i = 0; i < on.length(); i++) {
            int colorIndex = (i - shift + COLORS.length) % COLORS.length; // Adjust to move colors from left to right
            minerText.append(Component.translatable(String.valueOf(on.charAt(i)))
                     .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLORS[colorIndex]))));
        }
        return minerText;
    }
}