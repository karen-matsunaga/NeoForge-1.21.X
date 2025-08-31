package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.util.ModTags;
import net.karen.mccoursemod.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    public ModBiomeTagsProvider(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, MccourseMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // KAUPEN HOUSE STRUCTURE
        this.tag(ModTags.Biomes.HAS_KAUPEN_HOUSE).addOptionalTag(BiomeTags.IS_JUNGLE)
                                                 .addOptionalTag(BiomeTags.IS_FOREST)
                                                 .addOptionalTag(BiomeTags.IS_TAIGA)
                                                 .addOptional(Biomes.DESERT)
                                                 .addOptional(Biomes.PLAINS)
                                                 .addOptional(Biomes.SNOWY_PLAINS)
                                                 .addOptional(Biomes.SUNFLOWER_PLAINS)
                                                 .addOptional(Biomes.SAVANNA)
                                                 .addOptional(Biomes.SAVANNA_PLATEAU)
                                                 .addOptional(Biomes.WINDSWEPT_SAVANNA)
                                                 .addOptional(ModBiomes.TEST_BIOME)
                                                 .addOptional(ModBiomes.TEST_BIOME_2)
                                                 .replace(false);

        // STORAGE PLATFORM JIGSAW STRUCTURE
        this.tag(ModTags.Biomes.HAS_STORAGE_PLATFORM).addOptionalTag(BiomeTags.IS_JUNGLE)
                                                     .addOptionalTag(BiomeTags.IS_FOREST)
                                                     .addOptionalTag(BiomeTags.IS_TAIGA)
                                                     .addOptional(Biomes.DESERT)
                                                     .addOptional(Biomes.PLAINS)
                                                     .addOptional(Biomes.SNOWY_PLAINS)
                                                     .addOptional(Biomes.SUNFLOWER_PLAINS)
                                                     .addOptional(Biomes.SAVANNA)
                                                     .addOptional(Biomes.SAVANNA_PLATEAU)
                                                     .addOptional(Biomes.WINDSWEPT_SAVANNA)
                                                     .addOptional(ModBiomes.TEST_BIOME)
                                                     .addOptional(ModBiomes.TEST_BIOME_2)
                                                     .replace(false);
    }

    @Override
    public @NotNull String getName() {
        return "Mccourse Mod Biome Tags";
    }
}