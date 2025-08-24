package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.villager.ModVillagers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class ModPoiTypeTagsProvider extends PoiTypeTagsProvider {
    public ModPoiTypeTagsProvider(PackOutput output,
                                  CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, MccourseMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        ResourceKey<PoiType> kaupenKey = ModVillagers.KAUPEN_POI.getKey(); // KAUPEN POI
        ResourceKey<PoiType> soundKey = ModVillagers.SOUND_POI.getKey(); // SOUND POI
        if (kaupenKey != null && soundKey != null) {
            this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).addOptional(kaupenKey).addOptional(soundKey);
        }
    }
}