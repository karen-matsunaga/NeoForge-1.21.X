package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraft.tags.ItemTags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends VanillaItemTagsProvider {
    public ModItemTagGenerator(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // CUSTOM Items
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(ModItems.BISMUTH.get(), ModItems.ALEXANDRITE.get());

        // ** CUSTOM Tools **
        // BISMUTH
        tag(ModTags.Items.BISMUTH_TOOLS).add(ModItems.BISMUTH_SWORD.get(), ModItems.BISMUTH_PICKAXE.get(),
                                             ModItems.BISMUTH_SHOVEL.get(), ModItems.BISMUTH_AXE.get(),
                                             ModItems.BISMUTH_HOE.get(), ModItems.BISMUTH_HAMMER.get(),
                                             ModItems.BISMUTH_PAXEL.get(), ModItems.KAUPEN_BOW.get());

        // ALEXANDRITE
        tag(ModTags.Items.ALEXANDRITE_TOOLS).add(ModItems.ALEXANDRITE_SWORD.get(), ModItems.ALEXANDRITE_PICKAXE.get(),
                                                 ModItems.ALEXANDRITE_SHOVEL.get(), ModItems.ALEXANDRITE_AXE.get(),
                                                 ModItems.ALEXANDRITE_HOE.get(), ModItems.ALEXANDRITE_HAMMER.get(),
                                                 ModItems.ALEXANDRITE_PAXEL.get(), ModItems.ALEXANDRITE_BOW.get());

        // CUSTOM Axe
        tag(ItemTags.AXES).add(ModItems.BISMUTH_AXE.get(), ModItems.ALEXANDRITE_AXE.get());

        // CUSTOM Pickaxe
        tag(ItemTags.PICKAXES).add(ModItems.BISMUTH_PICKAXE.get(), ModItems.BISMUTH_HAMMER.get(),
                                   ModItems.BISMUTH_PAXEL.get(), ModItems.ALEXANDRITE_PICKAXE.get(),
                                   ModItems.ALEXANDRITE_HAMMER.get(), ModItems.ALEXANDRITE_PAXEL.get());

        // CUSTOM Sword
        tag(ItemTags.SWORDS).add(ModItems.BISMUTH_SWORD.get(), ModItems.ALEXANDRITE_SWORD.get());

        // CUSTOM Shovel
        tag(ItemTags.SHOVELS).add(ModItems.BISMUTH_SHOVEL.get(), ModItems.ALEXANDRITE_SHOVEL.get());

        // CUSTOM Hoe
        tag(ItemTags.HOES).add(ModItems.BISMUTH_HOE.get(), ModItems.ALEXANDRITE_HOE.get());

        // CUSTOM Bow
        tag(ItemTags.SKELETON_PREFERRED_WEAPONS).add(ModItems.KAUPEN_BOW.get(), ModItems.ALEXANDRITE_BOW.get());
        tag(ItemTags.WITHER_SKELETON_DISLIKED_WEAPONS).add(ModItems.KAUPEN_BOW.get(), ModItems.ALEXANDRITE_BOW.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(ModItems.KAUPEN_BOW.get(), ModItems.ALEXANDRITE_BOW.get());
        tag(ItemTags.BOW_ENCHANTABLE).add(ModItems.KAUPEN_BOW.get(), ModItems.ALEXANDRITE_BOW.get());

        // ** CUSTOM Repair ARMOR - TOOL ingredients **
        // TOOL MATERIALS
        tag(ModTags.Items.BISMUTH_TOOL_MATERIALS).add(ModItems.BISMUTH.get());
        tag(ModTags.Items.ALEXANDRITE_TOOL_MATERIALS).add(ModItems.ALEXANDRITE.get());
        // ARMOR MATERIALS
        tag(ModTags.Items.REPAIRS_BISMUTH_ARMOR).add(ModItems.BISMUTH.get());
        tag(ModTags.Items.REPAIRS_ALEXANDRITE_ARMOR).add(ModItems.ALEXANDRITE.get());

        // CUSTOM Armors -> Trimmable armor + Enchanted Armor
        tag(ModTags.Items.BISMUTH_ARMOR).add(ModItems.BISMUTH_HELMET.get(), ModItems.BISMUTH_CHESTPLATE.get(),
                                             ModItems.BISMUTH_LEGGINGS.get(), ModItems.BISMUTH_BOOTS.get());

        tag(ModTags.Items.ALEXANDRITE_ARMOR).add(ModItems.ALEXANDRITE_HELMET.get(), ModItems.ALEXANDRITE_CHESTPLATE.get(),
                                                 ModItems.ALEXANDRITE_LEGGINGS.get(), ModItems.ALEXANDRITE_BOOTS.get());

        // CUSTOM TRIM MATERIALS
        tag(ItemTags.TRIM_MATERIALS).add(ModItems.BISMUTH.get(), ModItems.ALEXANDRITE.get());

        // CUSTOM Boots
        tag(ItemTags.FOOT_ARMOR).add(ModItems.BISMUTH_BOOTS.get(), ModItems.ALEXANDRITE_BOOTS.get());

        // CUSTOM Leggings
        tag(ItemTags.LEG_ARMOR).add(ModItems.BISMUTH_LEGGINGS.get(), ModItems.ALEXANDRITE_LEGGINGS.get());

        // CUSTOM Chestplate
        tag(ItemTags.CHEST_ARMOR).add(ModItems.BISMUTH_CHESTPLATE.get(), ModItems.ALEXANDRITE_CHESTPLATE.get());

        // CUSTOM Helmet
        tag(ItemTags.HEAD_ARMOR).add(ModItems.BISMUTH_HELMET.get(), ModItems.ALEXANDRITE_HELMET.get());

        // ** CUSTOM Advanced items **
        // Level Charger item tags
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
        this.tag(ModTags.Items.SPECIAL_EFFECT_ITEMS).add(ModItems.AUTO_SMELT.get());

        // Bloodwood item tag
        this.tag(ItemTags.LOGS_THAT_BURN).add(ModBlocks.BLOODWOOD_LOG.asItem(), ModBlocks.WALNUT_LOG.asItem())
                                         .add(ModBlocks.BLOODWOOD_WOOD.asItem(), ModBlocks.WALNUT_WOOD.asItem())
                                         .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.asItem(), ModBlocks.STRIPPED_WALNUT_LOG.asItem())
                                         .add(ModBlocks.STRIPPED_BLOODWOOD_WOOD.asItem(), ModBlocks.STRIPPED_WALNUT_WOOD.asItem());
        this.tag(ItemTags.PLANKS).add(ModBlocks.BLOODWOOD_PLANKS.asItem(), ModBlocks.WALNUT_PLANKS.asItem());
        this.tag(ItemTags.SAPLINGS).add(ModBlocks.BLOODWOOD_SAPLING.asItem(), ModBlocks.WALNUT_SAPLING.asItem());
        this.tag(ItemTags.LEAVES).add(ModBlocks.BLOODWOOD_LEAVES.asItem(), ModBlocks.WALNUT_LEAVES.asItem());
        this.tag(ModTags.Items.BLOODWOOD_LOGS).add(ModBlocks.BLOODWOOD_LOG.asItem(), ModBlocks.BLOODWOOD_WOOD.asItem(),
                                                   ModBlocks.STRIPPED_BLOODWOOD_LOG.asItem(), ModBlocks.STRIPPED_BLOODWOOD_WOOD.asItem());
        this.tag(ModTags.Items.WALNUT_LOGS).add(ModBlocks.WALNUT_LOG.asItem(), ModBlocks.WALNUT_WOOD.asItem(),
                                                ModBlocks.STRIPPED_WALNUT_LOG.asItem(), ModBlocks.STRIPPED_WALNUT_WOOD.asItem());
    }
}