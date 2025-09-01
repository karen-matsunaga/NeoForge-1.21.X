package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.painting.ModPaintingVariants;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.trim.ModTrimMaterials;
import net.karen.mccoursemod.trim.ModTrimPatterns;
import net.karen.mccoursemod.worldgen.ModBiomeModifiers;
import net.karen.mccoursemod.worldgen.ModConfiguredFeatures;
import net.karen.mccoursemod.worldgen.ModPlacedFeatures;
import net.karen.mccoursemod.worldgen.biome.ModBiomes;
import net.karen.mccoursemod.worldgen.dimension.ModDimensions;
import net.karen.mccoursemod.worldgen.structure.ModPools;
import net.karen.mccoursemod.worldgen.structure.ModStructureSets;
import net.karen.mccoursemod.worldgen.structure.ModStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    // Registry all custom trims, enchantments, world generations, jukebox songs, painting variants, etc.
    public static final RegistrySetBuilder BUILDER =
           new RegistrySetBuilder().add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap) // Trim materials
                                   // Trim patterns
                                   .add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap)
                                   // Enchantments
                                   .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
                                   // World Generation
                                   .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
                                   .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                                   .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
                                   .add(Registries.BIOME, ModBiomes::bootstrap)
                                   // Jukebox Songs
                                   .add(Registries.JUKEBOX_SONG, ModSounds::bootstrap)
                                   // Painting Variants
                                   .add(Registries.PAINTING_VARIANT, ModPaintingVariants::bootstrap)
                                   // Structures
                                   .add(Registries.TEMPLATE_POOL, ModPools::bootstrap)
                                   .add(Registries.STRUCTURE, ModStructures::bootstrap)
                                   .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
                                   // Dimension Types and Level Stems
                                   .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
                                   .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType);

    // CUSTOM METHOD - Registry all custom trims, enchantments, world generations, jukebox songs, painting variants, etc.
    public ModDatapackProvider(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MccourseMod.MOD_ID));
    }
}