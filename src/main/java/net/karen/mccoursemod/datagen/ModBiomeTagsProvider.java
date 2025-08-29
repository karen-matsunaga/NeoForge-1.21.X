package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.util.ModTags;
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
        this.tag(ModTags.Biomes.HAS_KAUPEN_HOUSE).addTag(BiomeTags.IS_JUNGLE)
                                                 .addTag(BiomeTags.IS_FOREST)
                                                 .addTag(BiomeTags.IS_TAIGA)
                                                 .add(Biomes.DESERT)
                                                 .add(Biomes.PLAINS)
                                                 .add(Biomes.SNOWY_PLAINS)
                                                 .add(Biomes.SUNFLOWER_PLAINS)
                                                 .add(Biomes.SAVANNA)
                                                 .add(Biomes.SAVANNA_PLATEAU)
                                                 .add(Biomes.WINDSWEPT_SAVANNA)
                                                 .replace(false);
    }

    @Override
    public @NotNull String getName() {
        return "Mccourse Mod Biome Tags";
    }
}