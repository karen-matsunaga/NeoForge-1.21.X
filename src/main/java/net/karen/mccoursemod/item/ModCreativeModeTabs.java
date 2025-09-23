package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.fluid.ModFluids;
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
                 // PINK
                 output.accept(ModItems.PINK);
                 // ** CUSTOM Advanced items **
                 // CUSTOM Growth item
                 output.accept(ModItems.GROWTH);
                 // CUSTOM Level Charger items
                 output.accept(ModItems.LEVEL_CHARGER_GENERIC_PLUS);
                 output.accept(ModItems.LEVEL_CHARGER_GENERIC_MINUS);
                 output.accept(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE);
                 output.accept(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE);
                 // CUSTOM Found items
                 output.accept(ModItems.DATA_TABLET);
                 output.accept(ModItems.METAL_DETECTOR);
                 // CUSTOM Compactor items
                 output.accept(ModItems.ULTRA_COMPACTOR);
                 output.accept(ModItems.PINK_ULTRA_COMPACTOR);
                 // CUSTOM Luck items
                 output.accept(ModItems.LUCK_GENERAL);
                 output.accept(ModItems.LUCK_PICKAXE);
                 output.accept(ModItems.LUCK_WEAPON);
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
                 output.accept(ModItems.MINER_BOW);
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
                 // PINK
                 output.accept(ModItems.PINK_HELMET);
                 output.accept(ModItems.PINK_CHESTPLATE);
                 output.accept(ModItems.PINK_LEGGINGS);
                 output.accept(ModItems.PINK_BOOTS);
                 // COPPER
                 output.accept(ModItems.COPPER_HELMET);
                 output.accept(ModItems.COPPER_CHESTPLATE);
                 output.accept(ModItems.COPPER_LEGGINGS);
                 output.accept(ModItems.COPPER_BOOTS);
                 // LAPIS LAZULI
                 output.accept(ModItems.LAPIS_LAZULI_HELMET);
                 output.accept(ModItems.LAPIS_LAZULI_CHESTPLATE);
                 output.accept(ModItems.LAPIS_LAZULI_LEGGINGS);
                 output.accept(ModItems.LAPIS_LAZULI_BOOTS);
                 // REDSTONE
                 output.accept(ModItems.REDSTONE_HELMET);
                 output.accept(ModItems.REDSTONE_CHESTPLATE);
                 output.accept(ModItems.REDSTONE_LEGGINGS);
                 output.accept(ModItems.REDSTONE_BOOTS);
                 // PAXEL
                 output.accept(ModItems.PINK_PAXEL);
                 output.accept(ModItems.COPPER_PAXEL);
                 output.accept(ModItems.DIAMOND_PAXEL);
                 output.accept(ModItems.GOLD_PAXEL);
                 output.accept(ModItems.IRON_PAXEL);
                 output.accept(ModItems.STONE_PAXEL);
                 output.accept(ModItems.WOODEN_PAXEL);
                 output.accept(ModItems.NETHERITE_PAXEL);
                 output.accept(ModItems.LAPIS_LAZULI_PAXEL);
                 output.accept(ModItems.REDSTONE_PAXEL);
                 // HAMMER
                 output.accept(ModItems.PINK_HAMMER);
                 output.accept(ModItems.COPPER_HAMMER);
                 output.accept(ModItems.DIAMOND_HAMMER);
                 output.accept(ModItems.GOLD_HAMMER);
                 output.accept(ModItems.IRON_HAMMER);
                 output.accept(ModItems.STONE_HAMMER);
                 output.accept(ModItems.WOODEN_HAMMER);
                 output.accept(ModItems.NETHERITE_HAMMER);
                 output.accept(ModItems.LAPIS_LAZULI_HAMMER);
                 output.accept(ModItems.REDSTONE_HAMMER);
                 // SHOVEL
                 output.accept(ModItems.PINK_SHOVEL);
                 output.accept(ModItems.COPPER_SHOVEL);
                 output.accept(ModItems.LAPIS_LAZULI_SHOVEL);
                 output.accept(ModItems.REDSTONE_SHOVEL);
                 // AXE
                 output.accept(ModItems.PINK_AXE);
                 output.accept(ModItems.COPPER_AXE);
                 output.accept(ModItems.LAPIS_LAZULI_AXE);
                 output.accept(ModItems.REDSTONE_AXE);
                 // HOE
                 output.accept(ModItems.PINK_HOE);
                 output.accept(ModItems.COPPER_HOE);
                 output.accept(ModItems.LAPIS_LAZULI_HOE);
                 output.accept(ModItems.REDSTONE_HOE);
                 // PICKAXE
                 output.accept(ModItems.PINK_PICKAXE);
                 output.accept(ModItems.COPPER_PICKAXE);
                 output.accept(ModItems.LAPIS_LAZULI_PICKAXE);
                 output.accept(ModItems.REDSTONE_PICKAXE);
                 // SWORD
                 output.accept(ModItems.PINK_SWORD);
                 output.accept(ModItems.COPPER_SWORD);
                 output.accept(ModItems.LAPIS_LAZULI_SWORD);
                 output.accept(ModItems.REDSTONE_SWORD);
                 // ** CUSTOM elytra **
                 output.accept(ModItems.DIAMOND_ELYTRA);
                 // ** CUSTOM horse armor **
                 // BISMUTH
                 output.accept(ModItems.BISMUTH_HORSE_ARMOR);
                 // ALEXANDRITE
                 output.accept(ModItems.ALEXANDRITE_HORSE_ARMOR);
                 // ** CUSTOM foods **
                 output.accept(ModItems.COFFEE);
                 output.accept(ModItems.RADISH);
                 output.accept(ModItems.KOHLRABI);
                 output.accept(ModItems.CATTAIL);
                 // ** CUSTOM advanced items **
                 output.accept(ModItems.MCCOURSE_MOD_BOTTLE);
                 output.accept(ModItems.CHISEL);
                 output.accept(ModItems.RESTORE);
                 output.accept(ModItems.FARMER);
                 // ** CUSTOM music disc **
                 output.accept(ModItems.BAR_BRAWL_MUSIC_DISC);
                 // CUSTOM Smithing Template
                 output.accept(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE);
                 // ** CUSTOM seeds **
                 output.accept(ModItems.RADISH_SEEDS);
                 output.accept(ModItems.KOHLRABI_SEEDS);
                 output.accept(ModItems.CATTAIL_SEEDS);
                 // ** CUSTOM fuels **
                 output.accept(ModItems.FROSTFIRE_ICE);
                 output.accept(ModItems.STARLIGHT_ASHES);
                 output.accept(ModItems.PEAT_BRICK);
                 // ** CUSTOM bush **
                 output.accept(ModItems.GOJI_BERRIES);
                 // ** CUSTOM mob **
                 output.accept(ModItems.GECKO_SPAWN_EGG);
                 output.accept(ModItems.RHINO_SPAWN_EGG);
                 // ** CUSTOM Throwable Projectiles **
                 output.accept(ModItems.TOMAHAWK);
                 output.accept(ModItems.TORCH_BALL);
                 output.accept(ModItems.BOUNCY_BALLS);
                 output.accept(ModItems.DICE_ITEM);
                 // ** CUSTOM Particles **
                 output.accept(ModItems.BOUNCY_BALLS_PARTICLES);
                 // ** CUSTOM Animated Textures **
                 output.accept(ModItems.RADIATION_STAFF);
                 // ** CUSTOM Fishing Rod **
                 output.accept(ModItems.MCCOURSE_MOD_FISHING_ROD);
                 // ** CUSTOM Shield **
                 output.accept(ModItems.ALEXANDRITE_SHIELD);
                 // ** CUSTOM Fluid **
                 output.accept(ModFluids.SOAP_WATER_BUCKET);
                 // ** CUSTOM boats **
                 output.accept(ModItems.WALNUT_BOAT);
                 output.accept(ModItems.WALNUT_CHEST_BOAT);
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
                 // PINK
                 output.accept(ModBlocks.PINK_BLOCK);
                 output.accept(ModBlocks.PINK_ORE);
                 output.accept(ModBlocks.DEEPSLATE_PINK_ORE);
                 output.accept(ModBlocks.END_STONE_PINK_ORE);
                 output.accept(ModBlocks.NETHER_PINK_ORE);
                 // ** CUSTOM advanced blocks **
                 output.accept(ModBlocks.MAGIC);
                 output.accept(ModBlocks.ENCHANT);
                 output.accept(ModBlocks.DISENCHANT_GROUPED);
                 output.accept(ModBlocks.DISENCHANT_INDIVIDUAL);
                 output.accept(ModBlocks.MCCOURSEMOD_ELEVATOR);
                 output.accept(ModBlocks.MCCOURSEMOD_GENERATOR);
                 output.accept(ModBlocks.CRAFTING_PLUS);
                 output.accept(ModBlocks.SOUND);
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
                 // ** CUSTOM Crop block with two height **
                 output.accept(ModBlocks.CATTAIL_CROP);
                 // Bush crop block
                 output.accept(ModBlocks.GOJI_BERRY_BUSH);
                 // ** CUSTOM log **
                 // BLOODWOOD
                 output.accept(ModBlocks.BLOODWOOD_LOG);
                 output.accept(ModBlocks.BLOODWOOD_WOOD);
                 output.accept(ModBlocks.STRIPPED_BLOODWOOD_LOG);
                 output.accept(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
                 output.accept(ModBlocks.BLOODWOOD_PLANKS);
                 output.accept(ModBlocks.BLOODWOOD_SAPLING);
                 output.accept(ModBlocks.BLOODWOOD_LEAVES);
                 // WALNUT
                 output.accept(ModBlocks.WALNUT_LOG);
                 output.accept(ModBlocks.WALNUT_WOOD);
                 output.accept(ModBlocks.STRIPPED_WALNUT_LOG);
                 output.accept(ModBlocks.STRIPPED_WALNUT_WOOD);
                 output.accept(ModBlocks.WALNUT_PLANKS);
                 output.accept(ModBlocks.WALNUT_SAPLING);
                 output.accept(ModBlocks.WALNUT_LEAVES);
                 // ** CUSTOM Sittable blocks **
                 output.accept(ModBlocks.CHAIR);
                 // ** CUSTOM Block entity **
                 output.accept(ModBlocks.PEDESTAL);
                 output.accept(ModBlocks.GROWTH_CHAMBER);
                 // ** CUSTOM Glass blocks **
                 output.accept(ModBlocks.FORCED_STAINED_GLASS);
                 output.accept(ModBlocks.FORCED_STAINED_GLASS_PANE);
                 // ** CUSTOM Sign and Hanging Sing blocks **
                 output.accept(ModBlocks.WALNUT_SIGN);
                 output.accept(ModBlocks.WALNUT_HANGING_SIGN);
                 // ** CUSTOM Ender Pearl blocks **
                 output.accept(ModBlocks.ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.GREEN_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.BLACK_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.MAGENTA_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.PURPLE_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.ORANGE_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.PINK_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.CYAN_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.BROWN_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.GRAY_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.RED_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.LIME_GREEN_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.YELLOW_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.BLUE_ENDER_PEARL_BLOCK);
                 output.accept(ModBlocks.WHITE_ENDER_PEARL_BLOCK);
                 // ** CUSTOM mob blocks **
                 output.accept(ModBlocks.NETHER_STAR_BLOCK);
                 output.accept(ModBlocks.GUNPOWDER_BLOCK);
                 output.accept(ModBlocks.ROTTEN_FLESH_BLOCK);
                 output.accept(ModBlocks.BLAZE_ROD_BLOCK);
                 output.accept(ModBlocks.PHANTOM_MEMBRANE_BLOCK);
                 output.accept(ModBlocks.STRING_BLOCK);
                 output.accept(ModBlocks.SPIDER_EYE_BLOCK);
                 output.accept(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK);
                 output.accept(ModBlocks.SUGAR_BLOCK);
                 output.accept(ModBlocks.SUGAR_CANE_BLOCK);
                 // ** CUSTOM oxidizable blocks **
                 output.accept(ModBlocks.RUBY_BLOCK);
                 output.accept(ModBlocks.RUBY_BLOCK_1);
                 output.accept(ModBlocks.RUBY_BLOCK_2);
                 output.accept(ModBlocks.RUBY_BLOCK_3);
                 output.accept(ModBlocks.WAXED_RUBY_BLOCK);
                 output.accept(ModBlocks.WAXED_RUBY_BLOCK_1);
                 output.accept(ModBlocks.WAXED_RUBY_BLOCK_2);
                 output.accept(ModBlocks.WAXED_RUBY_BLOCK_3);
                 // ** CUSTOM flowers and pot flowers **
                 output.accept(ModBlocks.SNAPDRAGON);
                 output.accept(ModBlocks.POTTED_SNAPDRAGON);
                 // ** CUSTOM colored blocks **
                 output.accept(ModBlocks.COLORED_LEAVES);
                 // ** CUSTOM portal **
                 output.accept(ModBlocks.KAUPEN_PORTAL);
                 // ** CUSTOM furnace **
                 output.accept(ModBlocks.KAUPEN_FURNACE_BLOCK);
           }).build());

    // CUSTOM METHOD - Registry Creative Mode Tab on event bus
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}