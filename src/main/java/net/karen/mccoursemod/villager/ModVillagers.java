package net.karen.mccoursemod.villager;

import com.google.common.collect.ImmutableSet;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.sound.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagers {
    // Register all custom villager POI types
    public static final DeferredRegister<PoiType> POI_TYPES =
           DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, MccourseMod.MOD_ID);

    // Register all custom villager professions types
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
           DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, MccourseMod.MOD_ID);

    // Register all custom villager POI
    public static final Holder<PoiType> KAUPEN_POI = POI_TYPES.register("kaupen_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.CHAIR.get().getStateDefinition()
                                                                       .getPossibleStates()), 1, 1));

    // Register all custom villager professions
    public static final Holder<VillagerProfession> KAUPENGER = VILLAGER_PROFESSIONS.register("kaupenger",
            () -> new VillagerProfession(Component.literal("kaupenger"),
                                         holder -> holder.value() == KAUPEN_POI.value(),
                                         poiTypeHolder -> poiTypeHolder.value() == KAUPEN_POI.value(),
                                         ImmutableSet.of(), ImmutableSet.of(), ModSounds.MAGIC_BLOCK_HIT.get()));

    // CUSTOM METHOD - Registry all custom villagers on event
    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}