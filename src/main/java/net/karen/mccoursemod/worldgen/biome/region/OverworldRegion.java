package net.karen.mccoursemod.worldgen.biome.region;

import com.mojang.datafixers.util.Pair;
import net.karen.mccoursemod.worldgen.biome.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import java.util.function.Consumer;

public class OverworldRegion extends Region {
    public OverworldRegion(ResourceLocation name,
                           int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry,
                          Consumer<Pair<Climate.ParameterPoint,
                          ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
                                               // FOREST BIOME is replaced to TEST BIOME
                                               modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FOREST,
                                                                                            ModBiomes.TEST_BIOME);
                                               // PLAINS BIOME is replaced to TEST BIOME 2
                                               modifiedVanillaOverworldBuilder.replaceBiome(Biomes.PLAINS,
                                                                                            ModBiomes.TEST_BIOME_2);
        });
    }
}