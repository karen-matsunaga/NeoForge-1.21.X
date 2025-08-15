package net.karen.mccoursemod.block.entity;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
           DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MccourseMod.MOD_ID);

    // Registry all custom BLOCK ENTITIES
    public static final Supplier<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
           BLOCK_ENTITIES.register("pedestal_be",
           () -> new BlockEntityType<>(PedestalBlockEntity::new, ModBlocks.PEDESTAL.get()));

    // CUSTOM METHOD - Registry all custom BLOCK ENTITIES on event
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}