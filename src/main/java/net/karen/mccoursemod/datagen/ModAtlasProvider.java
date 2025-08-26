package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.entity.client.ShieldSpecialModelRenderer;
import net.minecraft.client.data.AtlasProvider;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSources;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.client.resources.model.AtlasIds;
import net.minecraft.client.resources.model.Material;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModAtlasProvider extends AtlasProvider {
    private final PackOutput.PathProvider pathProvider;

    public ModAtlasProvider(PackOutput output) {
        super(output);
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "atlases");
    }

    private static SpriteSource forMaterial(Material material) {
        return new SingleFile(material.texture());
    }

    private static List<SpriteSource> shieldPatterns() {
        return List.of(forMaterial(ShieldSpecialModelRenderer.ALEXANDRITE_SHIELD_BASE),
                       forMaterial(ShieldSpecialModelRenderer.ALEXANDRITE_SHIELD_BASE_NO_PATTERN));
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        return this.storeAtlas(output, AtlasIds.SHIELD_PATTERNS, shieldPatterns());
    }

    private CompletableFuture<?> storeAtlas(CachedOutput output,
                                            ResourceLocation atlasId,
                                            List<SpriteSource> sources) {
        return DataProvider.saveStable(output, SpriteSources.FILE_CODEC, sources, this.pathProvider.json(atlasId));
    }
}