package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.fluid.ModFluids;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
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
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(ModItems.BISMUTH.get(), ModItems.ALEXANDRITE.get(), ModItems.PINK.get());

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
        tag(ItemTags.AXES).add(ModItems.BISMUTH_AXE.get(),
                               ModItems.ALEXANDRITE_AXE.get(),
                               ModItems.PINK_AXE.get(),
                               ModItems.COPPER_AXE.get(),
                               ModItems.LAPIS_LAZULI_AXE.get(),
                               ModItems.REDSTONE_AXE.get());

        // CUSTOM Pickaxe
        tag(ItemTags.PICKAXES).add(ModItems.BISMUTH_PICKAXE.get(),
                                   ModItems.ALEXANDRITE_PICKAXE.get(),
                                   ModItems.PINK_PICKAXE.get(),
                                   ModItems.COPPER_PICKAXE.get(),
                                   ModItems.LAPIS_LAZULI_PICKAXE.get(),
                                   ModItems.REDSTONE_PICKAXE.get(),
                                   ModItems.BISMUTH_HAMMER.get(),
                                   ModItems.ALEXANDRITE_HAMMER.get(),
                                   ModItems.PINK_HAMMER.get(),
                                   ModItems.COPPER_HAMMER.get(),
                                   ModItems.DIAMOND_HAMMER.get(),
                                   ModItems.GOLD_HAMMER.get(),
                                   ModItems.IRON_HAMMER.get(),
                                   ModItems.STONE_HAMMER.get(),
                                   ModItems.WOODEN_HAMMER.get(),
                                   ModItems.NETHERITE_HAMMER.get(),
                                   ModItems.LAPIS_LAZULI_HAMMER.get(),
                                   ModItems.REDSTONE_HAMMER.get(),
                                   ModItems.BISMUTH_PAXEL.get(),
                                   ModItems.ALEXANDRITE_PAXEL.get(),
                                   ModItems.PINK_PAXEL.get(),
                                   ModItems.COPPER_PAXEL.get(),
                                   ModItems.DIAMOND_PAXEL.get(),
                                   ModItems.GOLD_PAXEL.get(),
                                   ModItems.IRON_PAXEL.get(),
                                   ModItems.STONE_PAXEL.get(),
                                   ModItems.WOODEN_PAXEL.get(),
                                   ModItems.NETHERITE_PAXEL.get(),
                                   ModItems.LAPIS_LAZULI_PAXEL.get(),
                                   ModItems.REDSTONE_PAXEL.get());

        // CUSTOM Sword
        tag(ItemTags.SWORDS).add(ModItems.BISMUTH_SWORD.get(),
                                 ModItems.ALEXANDRITE_SWORD.get(),
                                 ModItems.PINK_SWORD.get(),
                                 ModItems.COPPER_SWORD.get(),
                                 ModItems.LAPIS_LAZULI_SWORD.get(),
                                 ModItems.REDSTONE_SWORD.get());

        // CUSTOM Shovel
        tag(ItemTags.SHOVELS).add(ModItems.BISMUTH_SHOVEL.get(),
                                  ModItems.ALEXANDRITE_SHOVEL.get(),
                                  ModItems.PINK_SHOVEL.get(),
                                  ModItems.COPPER_SHOVEL.get(),
                                  ModItems.LAPIS_LAZULI_SHOVEL.get(),
                                  ModItems.REDSTONE_SHOVEL.get());

        // CUSTOM Hoe
        tag(ItemTags.HOES).add(ModItems.BISMUTH_HOE.get(),
                               ModItems.ALEXANDRITE_HOE.get(),
                               ModItems.PINK_HOE.get(),
                               ModItems.COPPER_HOE.get(),
                               ModItems.LAPIS_LAZULI_HOE.get(),
                               ModItems.REDSTONE_HOE.get());

        // CUSTOM Bow
        tag(ItemTags.SKELETON_PREFERRED_WEAPONS).add(ModItems.KAUPEN_BOW.get(), ModItems.ALEXANDRITE_BOW.get());
        tag(ItemTags.WITHER_SKELETON_DISLIKED_WEAPONS).add(ModItems.KAUPEN_BOW.get(), ModItems.ALEXANDRITE_BOW.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(ModItems.KAUPEN_BOW.get(),
                                                 ModItems.ALEXANDRITE_BOW.get(), ModItems.ALEXANDRITE_SHIELD.get());
        tag(ItemTags.BOW_ENCHANTABLE).add(ModItems.KAUPEN_BOW.get(), ModItems.ALEXANDRITE_BOW.get());

        // CUSTOM Shield
        this.tag(Tags.Items.TOOLS_SHIELD).add(ModItems.ALEXANDRITE_SHIELD.get());

        // ** CUSTOM Repair ARMOR - TOOL ingredients **
        // TOOL MATERIALS
        tag(ModTags.Items.BISMUTH_TOOL_MATERIALS).add(ModItems.BISMUTH.get());
        tag(ModTags.Items.ALEXANDRITE_TOOL_MATERIALS).add(ModItems.ALEXANDRITE.get());
        tag(ModTags.Items.PINK_TOOL_MATERIALS).add(ModItems.PINK.get());
        tag(ModTags.Items.COPPER_TOOL_MATERIALS).add(Items.COPPER_INGOT);
        tag(ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS).add(Items.LAPIS_LAZULI);
        tag(ModTags.Items.REDSTONE_TOOL_MATERIALS).add(Items.REDSTONE);
        // ARMOR MATERIALS
        tag(ModTags.Items.REPAIRS_BISMUTH_ARMOR).add(ModItems.BISMUTH.get());
        tag(ModTags.Items.REPAIRS_ALEXANDRITE_ARMOR).add(ModItems.ALEXANDRITE.get());
        tag(ModTags.Items.REPAIRS_PINK_ARMOR).add(ModItems.PINK.get());
        tag(ModTags.Items.REPAIRS_COPPER_ARMOR).add(Items.COPPER_INGOT);
        tag(ModTags.Items.REPAIRS_LAPIS_LAZULI_ARMOR).add(Items.LAPIS_LAZULI);
        tag(ModTags.Items.REPAIRS_REDSTONE_ARMOR).add(Items.REDSTONE);

        // CUSTOM Armors -> Trimmable armor + Enchanted Armor
        tag(ModTags.Items.BISMUTH_ARMOR).add(ModItems.BISMUTH_HELMET.get(), ModItems.BISMUTH_CHESTPLATE.get(),
                                             ModItems.BISMUTH_LEGGINGS.get(), ModItems.BISMUTH_BOOTS.get());

        tag(ModTags.Items.ALEXANDRITE_ARMOR).add(ModItems.ALEXANDRITE_HELMET.get(), ModItems.ALEXANDRITE_CHESTPLATE.get(),
                                                 ModItems.ALEXANDRITE_LEGGINGS.get(), ModItems.ALEXANDRITE_BOOTS.get());

        tag(ModTags.Items.PINK_ARMOR).add(ModItems.PINK_HELMET.get(), ModItems.PINK_CHESTPLATE.get(),
                                          ModItems.PINK_LEGGINGS.get(), ModItems.PINK_BOOTS.get());

        tag(ModTags.Items.COPPER_ARMOR).add(ModItems.COPPER_HELMET.get(), ModItems.COPPER_CHESTPLATE.get(),
                                            ModItems.COPPER_LEGGINGS.get(), ModItems.COPPER_BOOTS.get());

        tag(ModTags.Items.LAPIS_LAZULI_ARMOR).add(ModItems.LAPIS_LAZULI_HELMET.get(), ModItems.LAPIS_LAZULI_CHESTPLATE.get(),
                                                  ModItems.LAPIS_LAZULI_LEGGINGS.get(), ModItems.LAPIS_LAZULI_BOOTS.get());

        tag(ModTags.Items.REDSTONE_ARMOR).add(ModItems.REDSTONE_HELMET.get(), ModItems.REDSTONE_CHESTPLATE.get(),
                                              ModItems.REDSTONE_LEGGINGS.get(), ModItems.REDSTONE_BOOTS.get());

        // CUSTOM TRIM MATERIALS
        tag(ItemTags.TRIM_MATERIALS).add(ModItems.BISMUTH.get(),
                                         ModItems.ALEXANDRITE.get(),
                                         ModItems.PINK.get());

        // CUSTOM Boots
        tag(ItemTags.FOOT_ARMOR).add(ModItems.BISMUTH_BOOTS.get(),
                                     ModItems.ALEXANDRITE_BOOTS.get(),
                                     ModItems.PINK_BOOTS.get(),
                                     ModItems.COPPER_BOOTS.get(),
                                     ModItems.LAPIS_LAZULI_BOOTS.get(),
                                     ModItems.REDSTONE_BOOTS.get());

        // CUSTOM Leggings
        tag(ItemTags.LEG_ARMOR).add(ModItems.BISMUTH_LEGGINGS.get(),
                                    ModItems.ALEXANDRITE_LEGGINGS.get(),
                                    ModItems.PINK_LEGGINGS.get(),
                                    ModItems.COPPER_LEGGINGS.get(),
                                    ModItems.LAPIS_LAZULI_LEGGINGS.get(),
                                    ModItems.REDSTONE_LEGGINGS.get());

        // CUSTOM Chestplate
        tag(ItemTags.CHEST_ARMOR).add(ModItems.BISMUTH_CHESTPLATE.get(),
                                      ModItems.ALEXANDRITE_CHESTPLATE.get(),
                                      ModItems.PINK_CHESTPLATE.get(),
                                      ModItems.COPPER_CHESTPLATE.get(),
                                      ModItems.LAPIS_LAZULI_CHESTPLATE.get(),
                                      ModItems.REDSTONE_CHESTPLATE.get());

        // CUSTOM Helmet
        tag(ItemTags.HEAD_ARMOR).add(ModItems.BISMUTH_HELMET.get(),
                                     ModItems.ALEXANDRITE_HELMET.get(),
                                     ModItems.PINK_HELMET.get(),
                                     ModItems.COPPER_HELMET.get(),
                                     ModItems.LAPIS_LAZULI_HELMET.get(),
                                     ModItems.REDSTONE_HELMET.get());

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
                                         .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.asItem(),
                                              ModBlocks.STRIPPED_WALNUT_LOG.asItem())
                                         .add(ModBlocks.STRIPPED_BLOODWOOD_WOOD.asItem(),
                                              ModBlocks.STRIPPED_WALNUT_WOOD.asItem());
        this.tag(ItemTags.PLANKS).add(ModBlocks.BLOODWOOD_PLANKS.asItem(), ModBlocks.WALNUT_PLANKS.asItem());
        this.tag(ItemTags.SAPLINGS).add(ModBlocks.BLOODWOOD_SAPLING.asItem(), ModBlocks.WALNUT_SAPLING.asItem());
        this.tag(ItemTags.LEAVES).add(ModBlocks.BLOODWOOD_LEAVES.asItem(), ModBlocks.WALNUT_LEAVES.asItem(),
                                      ModBlocks.COLORED_LEAVES.asItem());
        this.tag(ModTags.Items.BLOODWOOD_LOGS).add(ModBlocks.BLOODWOOD_LOG.asItem(),
                                                   ModBlocks.BLOODWOOD_WOOD.asItem(),
                                                   ModBlocks.STRIPPED_BLOODWOOD_LOG.asItem(),
                                                   ModBlocks.STRIPPED_BLOODWOOD_WOOD.asItem());
        this.tag(ModTags.Items.WALNUT_LOGS).add(ModBlocks.WALNUT_LOG.asItem(),
                                                ModBlocks.WALNUT_WOOD.asItem(),
                                                ModBlocks.STRIPPED_WALNUT_LOG.asItem(),
                                                ModBlocks.STRIPPED_WALNUT_WOOD.asItem());
        // ** CUSTOM fluid **
        this.tag(Tags.Items.BUCKETS).add(ModFluids.SOAP_WATER_BUCKET.get());
        this.tag(Tags.Items.BUCKETS_WATER).add(ModFluids.SOAP_WATER_BUCKET.get());
        this.tag(Tags.Items.BUCKETS_ENTITY_WATER).add(ModFluids.SOAP_WATER_BUCKET.get());

        // ** CUSTOM flowers and pot flowers **
        this.tag(ItemTags.FLOWERS).add(ModBlocks.SNAPDRAGON.asItem());
        this.tag(ItemTags.SMALL_FLOWERS).add(ModBlocks.SNAPDRAGON.asItem());
        this.tag(ItemTags.BEE_FOOD).add(ModBlocks.SNAPDRAGON.asItem());
    }
}