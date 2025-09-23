package net.karen.mccoursemod.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.*;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class LuckItem extends Item {
    // Number of books, enchantments by book, enchantment level, enchantment type
    private final int BOOKS_TO_GENERATE, ENCHANTMENTS_PER_BOOK, ENCHANTMENT_LEVEL, ENCHANTMENT_TYPE, textColor;
    private final TagKey<Enchantment> ENCHANTMENT_TAG;
    private final @Nullable String textMessage;

    public LuckItem(Properties properties, int bookAmount, int enchAmount, int level,
                    int type, TagKey<Enchantment> enchTag, int textColor, @Nullable String textMessage) {
        super(properties);
        this.BOOKS_TO_GENERATE = bookAmount;
        this.ENCHANTMENTS_PER_BOOK = enchAmount;
        this.ENCHANTMENT_LEVEL = level;
        this.ENCHANTMENT_TYPE = type;
        this.ENCHANTMENT_TAG = enchTag;
        this.textColor = textColor;
        this.textMessage = textMessage;
    }

    // DEFAULT METHOD - LUCK item name
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return componentTranslatableIntColor(this.getDescriptionId(), textColor);
    }

    // CUSTOM METHOD - Added LUCK item description
    public Map<String, Integer> luckDescription() {
        Map<String, Integer> luckMessageColor = new HashMap<>();
        String upper = itemLine(this.getDescriptionId(), "item.mccoursemod.", "", "_", " ");
        String message = "Good luck! " + itemLines(upper) + " random ";
        if (textMessage == null) { luckMessageColor.put(message + "enchantments!", textColor); }
        else { luckMessageColor.put(message + textMessage + " enchantments!", textColor); }
        return luckMessageColor;
    }

    // DEFAULT METHOD - LUCK item interaction
    @Override
    public @NotNull InteractionResult use(Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand); // Player get item on main hand
        if (!level.isClientSide()) { // Player press Right-click ACTIVATED item
            Random random = new Random(); // Activated random enchantment
            for (int i = 0; i < BOOKS_TO_GENERATE; i++) { // Book quantity
                ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK); // Enchanted book to add the enchantments
                Map<Holder<Enchantment>, Integer> books = new HashMap<>(); // List to store the book's enchantments
                List<Holder<Enchantment>> enchantmentType = getEnchantmentHoldersByTag(level, ENCHANTMENT_TAG);
                for (int j = 0; j < ENCHANTMENTS_PER_BOOK; j++) { // Adding various enchantments on the book
                    switch (ENCHANTMENT_TYPE) {
                        case 0 -> { // RANDOM enchantments
                             Holder<Enchantment> randomEnchantment = getRandomEnchantment(random, level, ENCHANTMENT_TAG);
                             if (randomEnchantment != null) {
                                 books.put(randomEnchantment, randomEnchantment.value().getMaxLevel());
                             }
                        }
                        case 1 -> { // SOME enchantments
                             Holder<Enchantment> randomEnchant = enchantmentType.get(random.nextInt(enchantmentType.size()));
                             if (randomEnchant.value().getMaxLevel() == 1) { books.put(randomEnchant, ENCHANTMENT_LEVEL); }
                             else if (randomEnchant.value().getMaxLevel() >= 1) { books.put(randomEnchant, ENCHANTMENT_LEVEL); }
                        }
                        case 2 -> // ALL enchantments
                             enchantmentType.forEach(enchant -> books.put(enchant, ENCHANTMENT_LEVEL));
                    }
                }
                ItemEnchantments.Mutable itemEnchantmentsMutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                for (Map.Entry<Holder<Enchantment>, Integer> key : books.entrySet()) {
                    itemEnchantmentsMutable.set(key.getKey(), key.getValue());
                }
                // Applies the enchantments to the book
                EnchantmentHelper.setEnchantments(enchantedBook, itemEnchantmentsMutable.toImmutable());
                // Give the book to the player -> If INVENTORY is full drop on ground
                if (!player.getInventory().add(enchantedBook)) { player.drop(enchantedBook, false); }
            }
        }
        itemStack.shrink(1); // CONSUME LUCK item
        return InteractionResult.SUCCESS;
    }

    // CUSTOM METHOD - Get random enchantment by tag
    Holder<Enchantment> getRandomEnchantment(Random random, Level level, TagKey<Enchantment> tagKey) {
        List<Holder<Enchantment>> allEnchantments = getEnchantmentHoldersByTag(level, tagKey);
        return allEnchantments.get(random.nextInt(allEnchantments.size())); // Returns a RANDOM enchantment from the list
    }

    // CUSTOM METHOD - Filter enchantment by tag
    List<Holder<Enchantment>> getEnchantmentHoldersByTag(Level level, TagKey<Enchantment> tagKey) {
        Registry<Enchantment> enchantmentRegistry = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Optional<HolderSet.Named<Enchantment>> value = enchantmentRegistry.get(tagKey);
        return value.map(enchName -> enchName.stream().toList()).orElse(null);
    }
}