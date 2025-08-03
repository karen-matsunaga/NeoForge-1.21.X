package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraft.tags.ItemTags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends VanillaItemTagsProvider {
    public ModItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // CUSTOM Items
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(ModItems.BISMUTH.get());

        // CUSTOM Tools
        tag(ModTags.Items.BISMUTH_TOOLS).add(ModItems.BISMUTH_SWORD.get(), ModItems.BISMUTH_PICKAXE.get(),
                                                 ModItems.BISMUTH_SHOVEL.get(), ModItems.BISMUTH_AXE.get(),
                                                 ModItems.BISMUTH_HOE.get(), ModItems.BISMUTH_HAMMER.get());
        tag(ItemTags.AXES).add(ModItems.BISMUTH_AXE.get());
        tag(ItemTags.PICKAXES).add(ModItems.BISMUTH_PICKAXE.get(), ModItems.BISMUTH_HAMMER.get());
        tag(ItemTags.SWORDS).add(ModItems.BISMUTH_SWORD.get());
        tag(ItemTags.SHOVELS).add(ModItems.BISMUTH_SHOVEL.get());
        tag(ItemTags.HOES).add(ModItems.BISMUTH_HOE.get());

        // CUSTOM Tools ingredients repair
        tag(ModTags.Items.BISMUTH_TOOL_MATERIALS).add(ModItems.BISMUTH.get()); // Tools
        tag(ModTags.Items.REPAIRS_BISMUTH_ARMOR).add(ModItems.BISMUTH.get()); // Armors

        // CUSTOM Armors -> Trimmable armor + Enchanted Armor
        tag(ModTags.Items.BISMUTH_ARMOR).add(ModItems.BISMUTH_HELMET.get(), ModItems.BISMUTH_CHESTPLATE.get(),
                                             ModItems.BISMUTH_LEGGINGS.get(), ModItems.BISMUTH_BOOTS.get());
        tag(ItemTags.TRIMMABLE_ARMOR).addTag(ModTags.Items.BISMUTH_ARMOR);
        tag(ItemTags.FOOT_ARMOR).add(ModItems.BISMUTH_BOOTS.get());
        tag(ItemTags.LEG_ARMOR).add(ModItems.BISMUTH_LEGGINGS.get());
        tag(ItemTags.CHEST_ARMOR).add(ModItems.BISMUTH_CHESTPLATE.get());
        tag(ItemTags.HEAD_ARMOR).add(ModItems.BISMUTH_HELMET.get());

        // Level Charger
        tag(ModTags.Items.LEVEL_CHARGER_GENERIC).add(ModItems.LEVEL_CHARGER_GENERIC_PLUS.get(),
                                                     ModItems.LEVEL_CHARGER_GENERIC_MINUS.get());

        tag(ModTags.Items.LEVEL_CHARGER_SPECIF).add(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE.get(),
                                                    ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE.get());

        tag(ModTags.Items.LEVEL_CHARGER_ITEMS).addTag(ModTags.Items.LEVEL_CHARGER_GENERIC)
                                              .addTag(ModTags.Items.LEVEL_CHARGER_SPECIF);

        // Tooltip color
        tag(ModTags.Items.LEVEL_CHARGER_GREEN).add(ModItems.LEVEL_CHARGER_GENERIC_PLUS.get(),
                                                   ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE.get());

        tag(ModTags.Items.LEVEL_CHARGER_RED).add(ModItems.LEVEL_CHARGER_GENERIC_MINUS.get(),
                                                 ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE.get());

        // Magic block items
        tag(ModTags.Items.MAGIC_BLOCK).add(ModItems.BISMUTH.get());

        // Fly effect
        tag(ModTags.Items.HELMET_FLY).add(ModItems.BISMUTH_HELMET.get());
        tag(ModTags.Items.CHESTPLATE_FLY).add(ModItems.BISMUTH_CHESTPLATE.get());
        tag(ModTags.Items.LEGGINGS_FLY).add(ModItems.BISMUTH_LEGGINGS.get());
        tag(ModTags.Items.BOOTS_FLY).add(ModItems.BISMUTH_BOOTS.get());

        // Special Effect items
        this.tag(ModTags.Items.SPECIAL_EFFECT_ITEMS).add(ModItems.AUTO_SMELT.get(), ModItems.MAGNET.get(),
                                                         ModItems.MORE_ORES.get(), ModItems.RAINBOW.get());
    }
}