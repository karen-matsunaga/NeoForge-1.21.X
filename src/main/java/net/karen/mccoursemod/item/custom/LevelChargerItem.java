package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import java.util.Map;
import static net.karen.mccoursemod.util.ChatUtils.*;
import static net.karen.mccoursemod.util.Utils.*;

public class LevelChargerItem extends Item {
    private final int amount;
    private final @Nullable ResourceKey<Enchantment> enchantment;

    public LevelChargerItem(Properties properties, int amount,
                            @Nullable ResourceKey<Enchantment> enchantment) {
        super(properties);
        this.amount = amount;
        this.enchantment = enchantment;
    }

    // DEFAULT METHOD - Amount value
    public int getAmount() {
        return amount;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        InteractionHand otherHand = (hand == mainHand) ? offhand : mainHand; // Player's MAIN HAND and OFFHAND
        ItemStack changerStack = player.getItemInHand(hand);
        ItemStack targetStack = player.getItemInHand(otherHand); // Player's ARMOR and TOOLS
        boolean applied = applyTo(level, player, targetStack, changerStack);
        boolean generic = applied && changerStack.is(ModTags.Items.LEVEL_CHARGER_GENERIC);
        boolean specif = applied && changerStack.is(ModTags.Items.LEVEL_CHARGER_SPECIF);
        if (!level.isClientSide() && generic || specif) {
            changerStack.shrink(1); // Consumes Level Charger
            return InteractionResult.SUCCESS;
        }
        else { return InteractionResult.FAIL; }
    }

    public boolean applyTo(Level level, Player player,
                           ItemStack targetStack, ItemStack changerStack) {
        if (!(changerStack.getItem() instanceof LevelChargerItem self)) { return false; }
        // Get all enchantments and enchantment levels
        ItemEnchantments allEnch = EnchantmentHelper.getEnchantmentsForCrafting(targetStack);
        int amount = self.amount; // Amount of enchantment level
        if (allEnch.isEmpty() || targetStack.is(ModTags.Items.LEVEL_CHARGER_SPECIF) ||
                                 targetStack.is(ModTags.Items.LEVEL_CHARGER_GENERIC)) {
            player(player, "The item has no enchantments!", darkRed);
            return false;
        }
        ResourceKey<Enchantment> specifEnchName = self.enchantment;
        if (changerStack.is(ModTags.Items.LEVEL_CHARGER_SPECIF) && specifEnchName != null) {
            HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            Holder<Enchantment> specifEnch = ench.getOrThrow(specifEnchName).getDelegate(); // SPECIF enchantment
            if (changerStack.is(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE.get())) { // Check enchantment levels
                // Has specif enchantment and all enchantments are with min level is 1
                boolean hasSpecifEnchant = allEnch.entrySet().stream()
                                                             .filter(e -> e.getKey().equals(specifEnch))
                                                             .allMatch(e -> e.getIntValue() <= 1);
                if (hasSpecifEnchant) { // All enchantment are with min level is 1
                    player(player, "All enchantments are already at level 1!", aqua);
                    return false;
                }
            }
            // Specif enchantment types
            if (changerStack.is(ModTags.Items.LEVEL_CHARGER_SPECIF) && !allEnch.keySet().contains(specifEnch)) {
                player(player, "This item doesn't have the required enchantment!", gray);
                return false;
            }
            ench(player, allEnch, targetStack, changerStack, specifEnch);
            return true;
        }
        if (amount < 0 && changerStack.is(ModItems.LEVEL_CHARGER_GENERIC_MINUS.get())) { // Check enchantment levels
            boolean allMin = allEnch.entrySet().stream().allMatch(e -> e.getIntValue() <= 1);
            if (allMin) { // All enchantment are with min level is 1
                player(player, "All enchantments are already at level 1!", aqua);
                return false;
            }
        }
        ench(player, allEnch, targetStack, changerStack, null);
        return true;
    }

    // DEFAULT METHOD - Added NAME on all Level Charger -> Translatable en_us.json
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component baseName = super.getName(stack);
        if (stack.is(ModTags.Items.LEVEL_CHARGER_GENERIC) || stack.is(ModTags.Items.LEVEL_CHARGER_SPECIF)) {
            return baseName.copy().withStyle((amount == 1) ? green : red);
        }
        return baseName;
    }

    // CUSTOM METHOD - Message when consumed Level Charger (Plus / Minus) items
    private void itemHurt(Player player, ItemStack chargerStack, String ench) {
        boolean value = (this.amount == 1);
        if (chargerStack.is(ModTags.Items.LEVEL_CHARGER_GENERIC)) {
            String screen = amount + " level(s)!";
            player(player, value ? ("Increased +" + screen) : ("Decreased " + screen), value ? green : red);
        }
        if (chargerStack.is(ModTags.Items.LEVEL_CHARGER_SPECIF)) {
            String message = amount + " " + itemLines(ench.replace("minecraft:", "")) + " level!";
            player(player, value ? ("Increased +" + message) : ("Decreased " + message), value ? green : red);
        }
    }

    // CUSTOM METHOD - Added TOOLTIP on all Level Charger
    public String levelChargerItemDescription() {
        String nameHolder = this.getDescriptionId().replace("item.mccoursemod.", "");
        String levelChargerName = itemLines(splitWord(nameHolder)), general = amount + " level.";
        String positive = levelChargerName + " increase +" + general;
        String negative = levelChargerName + " decrease " + general;
        boolean value = (amount == 1);
        return value ? (positive) : (negative);
    }

    // CUSTOM METHOD - LEVEL CHARGER update ENCHANTMENT levels
    public void ench(Player player, ItemEnchantments allEnch,
                     ItemStack targetStack, ItemStack changerStack, @Nullable Holder<Enchantment> specifEnch) {
        // Create new map with increased levels and store original enchantment and level
        Map<Holder<Enchantment>, Integer> upgraded = new HashMap<>();
        if (changerStack.is(ModTags.Items.LEVEL_CHARGER_GENERIC)) {
            allEnch.entrySet().forEach((enc) -> upgraded.put(enc.getKey(), Math.max(1, enc.getIntValue() + amount)));
        }
        if (changerStack.is(ModTags.Items.LEVEL_CHARGER_SPECIF)) {
            allEnch.entrySet().forEach((enc) -> {
                // Store new enchantment level of all enchants or Store new specif enchantment level
                if (enc.getKey().equals(specifEnch)) { upgraded.put(enc.getKey(), Math.max(1, enc.getIntValue() + amount)); }
            });
        }
        // Apply the updated enchantments to the original item
        ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(allEnch);
        upgraded.forEach(enchantments::set);
        EnchantmentHelper.setEnchantments(targetStack, enchantments.toImmutable()); // New enchantment level
        // Message on screen
        if (specifEnch != null) { itemHurt(player, changerStack, specifEnch.getRegisteredName()); }
        else { itemHurt(player, changerStack, ""); }
    }
}