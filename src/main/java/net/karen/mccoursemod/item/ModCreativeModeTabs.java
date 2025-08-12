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

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("bismuth_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BISMUTH.get()))
                                           .title(Component.translatable("creativetab.mccoursemod.bismuth_items"))
                                           .displayItems((itemDisplayParameters, output) -> {
                     // CUSTOM items
                     output.accept(ModItems.BISMUTH);
                     output.accept(ModItems.RAW_BISMUTH);
                     // CUSTOM Special Effect items
                     output.accept(ModItems.AUTO_SMELT);
                     // CUSTOM Level Charger items
                     output.accept(ModItems.LEVEL_CHARGER_GENERIC_PLUS);
                     output.accept(ModItems.LEVEL_CHARGER_GENERIC_MINUS);
                     output.accept(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE);
                     output.accept(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE);
                     // CUSTOM tools
                     output.accept(ModItems.BISMUTH_HAMMER);
                     output.accept(ModItems.BISMUTH_PAXEL);
                     output.accept(ModItems.BISMUTH_SWORD);
                     output.accept(ModItems.BISMUTH_PICKAXE);
                     output.accept(ModItems.BISMUTH_SHOVEL);
                     output.accept(ModItems.BISMUTH_AXE);
                     output.accept(ModItems.BISMUTH_HOE);
                     // CUSTOM armors
                     output.accept(ModItems.BISMUTH_HELMET);
                     output.accept(ModItems.BISMUTH_CHESTPLATE);
                     output.accept(ModItems.BISMUTH_LEGGINGS);
                     output.accept(ModItems.BISMUTH_BOOTS);
                     // CUSTOM foods
                     output.accept(ModItems.COFFEE);
                     // CUSTOM advanced items
                     output.accept(ModItems.MCCOURSE_MOD_BOTTLE);
                  }).build());

    public static final Supplier<CreativeModeTab> BISMUTH_BLOCK_TAB = CREATIVE_MODE_TAB.register("bismuth_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BISMUTH_BLOCK))
                                           .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                                                 "bismuth_items_tab"))
                                           .title(Component.translatable("creativetab.mccoursemod.bismuth_blocks"))
                                           .displayItems((itemDisplayParameters, output) -> {
                     // CUSTOM blocks
                     // CUSTOM ores
                     output.accept(ModBlocks.BISMUTH_BLOCK);
                     output.accept(ModBlocks.BISMUTH_ORE);
                     output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);
                     output.accept(ModBlocks.BISMUTH_END_ORE);
                     output.accept(ModBlocks.BISMUTH_NETHER_ORE);
                     output.accept(ModBlocks.MAGIC);
                     // CUSTOM advanced blocks
                     output.accept(ModBlocks.ENCHANT);
                     output.accept(ModBlocks.DISENCHANT_GROUPED);
                     output.accept(ModBlocks.DISENCHANT_INDIVIDUAL);
                     output.accept(ModBlocks.MCCOURSEMOD_ELEVATOR);
                  }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}