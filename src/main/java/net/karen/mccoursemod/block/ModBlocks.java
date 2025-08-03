package net.karen.mccoursemod.block;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.custom.DisenchantBlock;
import net.karen.mccoursemod.block.custom.EnchantBlock;
import net.karen.mccoursemod.block.custom.MagicBlock;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MccourseMod.MOD_ID);

    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
            () -> new Block(BlockBehaviour.Properties.of()
                                          .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)
                                          .setId(ResourceKey.create(Registries.BLOCK,
                                                 ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "bismuth_block")))));

    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock("bismuth_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                      BlockBehaviour.Properties.of().strength(3f)
                                    .requiresCorrectToolForDrops().sound(SoundType.STONE)
                                    .setId(ResourceKey.create(Registries.BLOCK,
                                           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "bismuth_ore")))));

    public static final DeferredBlock<Block> BISMUTH_DEEPSLATE_ORE = registerBlock("bismuth_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6),
                      BlockBehaviour.Properties.of().strength(4f)
                                    .requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)
                                    .setId(ResourceKey.create(Registries.BLOCK,
                                                              ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                              "bismuth_deepslate_ore")))));

    public static final DeferredBlock<Block> MAGIC = registerBlock("magic",
            () -> new MagicBlock(BlockBehaviour.Properties.of().strength(2f)
                                               .setId(ResourceKey.create(Registries.BLOCK,
                                                      ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "magic")))));

    // CUSTOM Enchant block
    public static final DeferredBlock<Block> ENCHANT = registerBlock("enchant",
            () -> new EnchantBlock(BlockBehaviour.Properties.of().strength(5.0F, 3600000.0F)
                  .requiresCorrectToolForDrops()
                  .setId(ResourceKey.create(Registries.BLOCK,
                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "enchant")))));

    // CUSTOM Disenchant individual block
    public static final DeferredBlock<Block> DISENCHANT_INDIVIDUAL = registerBlock("disenchant_individual",
            () -> new DisenchantBlock(BlockBehaviour.Properties.of().strength(5.0F, 3600000.0F)
                  .requiresCorrectToolForDrops()
                  .setId(ResourceKey.create(Registries.BLOCK,
                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "disenchant_individual"))), 1));

    // CUSTOM Disenchant grouped block
    public static final DeferredBlock<Block> DISENCHANT_GROUPED = registerBlock("disenchant_grouped",
            () -> new DisenchantBlock(BlockBehaviour.Properties.of().strength(5.0F, 3600000.0F)
                  .requiresCorrectToolForDrops()
                  .setId(ResourceKey.create(Registries.BLOCK,
                         ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "disenchant_grouped"))), 2));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name)))));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}