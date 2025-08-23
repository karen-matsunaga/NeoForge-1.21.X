package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.fluid.ModFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class ModFluidTagGenerator extends FluidTagsProvider {
    public ModFluidTagGenerator(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, MccourseMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(FluidTags.WATER).add(ModFluids.SOURCE_SOAP_WATER.get(), ModFluids.FLOWING_SOAP_WATER.get());
    }
}