package net.karen.mccoursemod.sound;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
           DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MccourseMod.MOD_ID);

    // Chisel item sounds
    public static final Supplier<SoundEvent> CHISEL_USE = registerSoundEvent("chisel_use");

    /* Magic block sounds   * Position 1: Break sound (when block is broken)
                            * Position 2: Step sound (when walking on block)
                            * Position 3: Place sound (when block is placed)
                            * Position 4: Hit sound (when block is punched)
                            * Position 5: Fall sound (when falling onto block) */
    public static final Supplier<SoundEvent> MAGIC_BLOCK_BREAK = registerSoundEvent("magic_block_break");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_STEP = registerSoundEvent("magic_block_step");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_PLACE = registerSoundEvent("magic_block_place");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_HIT = registerSoundEvent("magic_block_hit");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_FALL = registerSoundEvent("magic_block_fall");
    public static final DeferredSoundType MAGIC_BLOCK_SOUNDS =
           new DeferredSoundType(1f, 1f, ModSounds.MAGIC_BLOCK_BREAK, ModSounds.MAGIC_BLOCK_STEP,
                                 ModSounds.MAGIC_BLOCK_PLACE, ModSounds.MAGIC_BLOCK_HIT, ModSounds.MAGIC_BLOCK_FALL);

    // Bar Brawl item sounds
    public static final ResourceKey<JukeboxSong> BAR_BRAWL_KEY = createSong("bar_brawl");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_BAR_BRAWL =
           registerSoundEvents("bar_brawl");

    // CUSTOM METHOD - Registry all custom songs on Jukebox
    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

    // CUSTOM METHOD - Registry all custom supplier sound event type -> Used ITEM or BLOCK
    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    // CUSTOM METHOD - Registry all custom deferred holder sound event type -> MUSIC DISC
    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    // CUSTOM METHOD - Registry all custom sounds on event bus
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    // CUSTOM METHOD - Register all parameters of Data Generation JSON file
    private static void registerGen(BootstrapContext<JukeboxSong> context,
                                    ResourceKey<JukeboxSong> key,
                                    DeferredHolder<SoundEvent, SoundEvent> soundEvent,
                                    int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent,
                Component.translatable(Util.makeDescriptionId("item",
                                                              key.location().withSuffix("_music_disc.desc"))),
                (float) lengthInSeconds, comparatorOutput));
    }

    // CUSTOM METHOD - Data generation of all custom sound events on JSON file
    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        registerGen(context, ModSounds.BAR_BRAWL_KEY, ModSounds.MUSIC_DISC_BAR_BRAWL, 162, 15);
    }
}