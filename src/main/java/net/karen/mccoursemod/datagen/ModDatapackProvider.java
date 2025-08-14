package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.worldgen.ModBiomeModifiers;
import net.karen.mccoursemod.worldgen.ModConfiguredFeatures;
import net.karen.mccoursemod.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER =
           new RegistrySetBuilder().add(Registries.ENCHANTMENT, ModEnchantments::bootstrap) // Enchantments
                                   .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap) // World Generation
                                   .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                                   .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
                                   .add(Registries.JUKEBOX_SONG, ModSounds::bootstrap); // Jukebox Songs

    public ModDatapackProvider(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MccourseMod.MOD_ID));
    }
}