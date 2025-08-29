package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.util.ModTags;
import net.karen.mccoursemod.worldgen.structure.ModStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class ModStructureTagsProvider extends StructureTagsProvider {
    public ModStructureTagsProvider(PackOutput output,
                                    CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, MccourseMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(ModTags.Structures.MCCOURSEMOD_STRUCTURES).addOptional(ModStructures.KAUPEN_HOUSE)
                                                           .addOptional(ModStructures.STORAGE_PLATFORM)
                                                           .replace(false);
    }

    @Override
    public @NotNull String getName() {
        return "Mccourse Mod Structure Tags";
    }
}