package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.painting.ModPaintingVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class ModPaintingVariantTagGenerator extends PaintingVariantTagsProvider {
    public ModPaintingVariantTagGenerator(PackOutput output,
                                          CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, MccourseMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(PaintingVariantTags.PLACEABLE).addOptional(ModPaintingVariants.SAW_THEM)
                                          .addOptional(ModPaintingVariants.SHRIMP)
                                          .addOptional(ModPaintingVariants.WORLD);
    }
}