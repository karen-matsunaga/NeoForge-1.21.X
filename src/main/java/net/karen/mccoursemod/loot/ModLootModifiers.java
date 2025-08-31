package net.karen.mccoursemod.loot;

import com.mojang.serialization.MapCodec;
import net.karen.mccoursemod.MccourseMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
           DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MccourseMod.MOD_ID);

    // Register all custom loot table modifiers
    // CUSTOM loot table Structures, Entities, Blocks, Items etc.
    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
           LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);

    // CUSTOM loot table Suspicious Sand item
    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_SUS_SAND_ITEM =
           LOOT_MODIFIER_SERIALIZERS.register("add_sus_sand_item", () -> AddSusSandItemModifier.CODEC);

    // CUSTOM METHOD - Registry all custom loot table modifiers
    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}