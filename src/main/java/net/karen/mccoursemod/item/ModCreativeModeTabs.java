package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
           DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MccourseMod.MOD_ID);

    // Show all items in Creative Mode -> First Creative Mode Tab
    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB =
           CREATIVE_MODE_TAB.register("bismuth_items_tab",
           () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BISMUTH.get()))
                                          .title(Component.translatable("creativetab.mccoursemod.bismuth_items"))
                                          .displayItems((itemDisplayParameters,
                                                         output) -> {
                 // ** CUSTOM items **
                 // ** CUSTOM Mccourse Mod ore items **
                 // BISMUTH
                 output.accept(ModItems.BISMUTH);
                 output.accept(ModItems.RAW_BISMUTH);
                 // ALEXANDRITE
                 output.accept(ModItems.ALEXANDRITE);
                 output.accept(ModItems.RAW_ALEXANDRITE);
                 // ** CUSTOM Advanced items **
                 // CUSTOM Special Effect items
                 output.accept(ModItems.AUTO_SMELT);
                 // CUSTOM Level Charger items
                 output.accept(ModItems.LEVEL_CHARGER_GENERIC_PLUS);
                 output.accept(ModItems.LEVEL_CHARGER_GENERIC_MINUS);
                 output.accept(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE);
                 output.accept(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE);
                 // CUSTOM FOUND ORES
                 output.accept(ModItems.DATA_TABLET);
                 output.accept(ModItems.METAL_DETECTOR);
                 // ** CUSTOM tools **
                 // BISMUTH
                 output.accept(ModItems.BISMUTH_HAMMER);
                 output.accept(ModItems.BISMUTH_PAXEL);
                 output.accept(ModItems.BISMUTH_SWORD);
                 output.accept(ModItems.BISMUTH_PICKAXE);
                 output.accept(ModItems.BISMUTH_SHOVEL);
                 output.accept(ModItems.BISMUTH_AXE);
                 output.accept(ModItems.BISMUTH_HOE);
                 output.accept(ModItems.KAUPEN_BOW);
                 // ALEXANDRITE
                 output.accept(ModItems.ALEXANDRITE_HAMMER);
                 output.accept(ModItems.ALEXANDRITE_PAXEL);
                 output.accept(ModItems.ALEXANDRITE_SWORD);
                 output.accept(ModItems.ALEXANDRITE_PICKAXE);
                 output.accept(ModItems.ALEXANDRITE_SHOVEL);
                 output.accept(ModItems.ALEXANDRITE_AXE);
                 output.accept(ModItems.ALEXANDRITE_HOE);
                 output.accept(ModItems.ALEXANDRITE_BOW);
                 // ** CUSTOM armors **
                 // BISMUTH
                 output.accept(ModItems.BISMUTH_HELMET);
                 output.accept(ModItems.BISMUTH_CHESTPLATE);
                 output.accept(ModItems.BISMUTH_LEGGINGS);
                 output.accept(ModItems.BISMUTH_BOOTS);
                 // ALEXANDRITE
                 output.accept(ModItems.ALEXANDRITE_HELMET);
                 output.accept(ModItems.ALEXANDRITE_CHESTPLATE);
                 output.accept(ModItems.ALEXANDRITE_LEGGINGS);
                 output.accept(ModItems.ALEXANDRITE_BOOTS);
                 // ** CUSTOM horse armor **
                 // BISMUTH
                 output.accept(ModItems.BISMUTH_HORSE_ARMOR);
                 // ALEXANDRITE
                 output.accept(ModItems.ALEXANDRITE_HORSE_ARMOR);
                 // CUSTOM foods
                 output.accept(ModItems.COFFEE);
                 output.accept(ModItems.RADISH);
                 output.accept(ModItems.KOHLRABI);
                 // CUSTOM advanced items
                 output.accept(ModItems.MCCOURSE_MOD_BOTTLE);
                 output.accept(ModItems.CHISEL);
                 // CUSTOM music disc
                 output.accept(ModItems.BAR_BRAWL_MUSIC_DISC);
                 // CUSTOM Smithing Template
                 output.accept(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE);
                 // ** CUSTOM seeds **
                 output.accept(ModItems.RADISH_SEEDS);
                 output.accept(ModItems.KOHLRABI_SEEDS);
                 // CUSTOM fuels
                 output.accept(ModItems.FROSTFIRE_ICE);
                 output.accept(ModItems.STARLIGHT_ASHES);
                 // CUSTOM bush
                 output.accept(ModItems.GOJI_BERRIES);
                 // CUSTOM mob
                 output.accept(ModItems.GECKO_SPAWN_EGG);
                 // CUSTOM Throwable Projectiles
                 output.accept(ModItems.TOMAHAWK);
                 // CUSTOM Animated Textures
                 output.accept(ModItems.RADIATION_STAFF);
                 // CUSTOM Fishing Rod
                 output.accept(ModItems.MCCOURSE_MOD_FISHING_ROD);
           }).build());

    // Show all blocks in Creative Mode -> Second Creative Mode Tab
    public static final Supplier<CreativeModeTab> BISMUTH_BLOCK_TAB =
           CREATIVE_MODE_TAB.register("bismuth_blocks_tab",
           () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BISMUTH_BLOCK))
                                          .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                                                "bismuth_items_tab"))
                                          .title(Component.translatable("creativetab.mccoursemod.bismuth_blocks"))
                                          .displayItems((itemDisplayParameters,
                                                         output) -> {
                 // ** CUSTOM blocks **
                 // ** CUSTOM ores **
                 // BISMUTH
                 output.accept(ModBlocks.BISMUTH_BLOCK);
                 output.accept(ModBlocks.BISMUTH_ORE);
                 output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);
                 output.accept(ModBlocks.BISMUTH_END_ORE);
                 output.accept(ModBlocks.BISMUTH_NETHER_ORE);
                 // ALEXANDRITE
                 output.accept(ModBlocks.ALEXANDRITE_BLOCK);
                 output.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK);
                 output.accept(ModBlocks.ALEXANDRITE_ORE);
                 output.accept(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);
                 output.accept(ModBlocks.END_STONE_ALEXANDRITE_ORE);
                 output.accept(ModBlocks.NETHER_ALEXANDRITE_ORE);
                 // ** CUSTOM advanced blocks **
                 output.accept(ModBlocks.MAGIC);
                 output.accept(ModBlocks.ENCHANT);
                 output.accept(ModBlocks.DISENCHANT_GROUPED);
                 output.accept(ModBlocks.DISENCHANT_INDIVIDUAL);
                 output.accept(ModBlocks.MCCOURSEMOD_ELEVATOR);
                 // ** CUSTOM Block families **
                 // BISMUTH
                 output.accept(ModBlocks.BISMUTH_STAIRS);
                 output.accept(ModBlocks.BISMUTH_SLAB);
                 output.accept(ModBlocks.BISMUTH_PRESSURE_PLATE);
                 output.accept(ModBlocks.BISMUTH_BUTTON);
                 output.accept(ModBlocks.BISMUTH_FENCE);
                 output.accept(ModBlocks.BISMUTH_FENCE_GATE);
                 output.accept(ModBlocks.BISMUTH_WALL);
                 output.accept(ModBlocks.BISMUTH_DOOR);
                 output.accept(ModBlocks.BISMUTH_TRAPDOOR);
                 // ALEXANDRITE
                 output.accept(ModBlocks.ALEXANDRITE_STAIRS);
                 output.accept(ModBlocks.ALEXANDRITE_SLABS);
                 output.accept(ModBlocks.ALEXANDRITE_PREASSURE_PLATE);
                 output.accept(ModBlocks.ALEXANDRITE_BUTTON);
                 output.accept(ModBlocks.ALEXANDRITE_FENCE);
                 output.accept(ModBlocks.ALEXANDRITE_FENCE_GATE);
                 output.accept(ModBlocks.ALEXANDRITE_WALL);
                 output.accept(ModBlocks.ALEXANDRITE_DOOR);
                 output.accept(ModBlocks.ALEXANDRITE_TRAPDOOR);
                 // ** CUSTOM Blockstate block **
                 // BISMUTH
                 output.accept(ModBlocks.BISMUTH_LAMP);
                 // ALEXANDRITE
                 output.accept(ModBlocks.ALEXANDRITE_LAMP);
                 // ** CUSTOM Crop block **
                 output.accept(ModBlocks.RADISH_CROP);
                 output.accept(ModBlocks.KOHLRABI_CROP);
                 // Bush crop block
                 output.accept(ModBlocks.GOJI_BERRY_BUSH);
                 // Bloodwood log + Bloodwood wood
                 output.accept(ModBlocks.BLOODWOOD_LOG);
                 output.accept(ModBlocks.BLOODWOOD_WOOD);
                 output.accept(ModBlocks.STRIPPED_BLOODWOOD_LOG);
                 output.accept(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
                 output.accept(ModBlocks.BLOODWOOD_PLANKS);
                 output.accept(ModBlocks.BLOODWOOD_SAPLING);
                 output.accept(ModBlocks.BLOODWOOD_LEAVES);
                 // Sittable blocks
                 output.accept(ModBlocks.CHAIR);
                 // Block entity
                 output.accept(ModBlocks.PEDESTAL);
                 output.accept(ModBlocks.GROWTH_CHAMBER);
                 // Glass blocks
                 output.accept(ModBlocks.FORCED_STAINED_GLASS);
                 output.accept(ModBlocks.FORCED_STAINED_GLASS_PANE);
           }).build());

    // CUSTOM METHOD - Registry Creative Mode Tab on event bus
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}