package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.client.ShieldSpecialModelRenderer;
import net.karen.mccoursemod.trim.ModTrimPatterns;
import net.minecraft.client.data.AtlasProvider;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.renderer.MaterialMapper;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BellRenderer;
import net.minecraft.client.renderer.blockentity.ConduitRenderer;
import net.minecraft.client.renderer.blockentity.EnchantTableRenderer;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSources;
import net.minecraft.client.renderer.texture.atlas.sources.DirectoryLister;
import net.minecraft.client.renderer.texture.atlas.sources.PalettedPermutations;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.client.resources.model.AtlasIds;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.client.resources.model.Material;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import org.jetbrains.annotations.NotNull;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModAtlasProvider extends AtlasProvider {
    private static final ResourceLocation TRIM_PALETTE_KEY =
            ResourceLocation.withDefaultNamespace("trims/color_palettes/trim_palette");

    private static final List<String> items = List.of("alexandrite", "bismuth",
                                                      "pink");

    private static final Map<String, ResourceLocation> TRIM_PALETTE_VALUES =
           extractAllMaterialAssets().collect(Collectors.toMap(MaterialAssetGroup.AssetInfo::suffix,
           (info) -> items.contains(info.suffix())
                              ? ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "trims/color_palettes/" +
                                                                      info.suffix())
                              : ResourceLocation.withDefaultNamespace("trims/color_palettes/" + info.suffix())));

    private static final List<ResourceKey<TrimPattern>> VANILLA_PATTERNS =
            List.of(TrimPatterns.SENTRY, TrimPatterns.DUNE, TrimPatterns.COAST, TrimPatterns.WILD, TrimPatterns.WARD,
                    TrimPatterns.EYE, TrimPatterns.VEX, TrimPatterns.TIDE, TrimPatterns.SNOUT, TrimPatterns.RIB,
                    TrimPatterns.SPIRE, TrimPatterns.WAYFINDER, TrimPatterns.SHAPER, TrimPatterns.SILENCE,
                    TrimPatterns.RAISER, TrimPatterns.HOST, TrimPatterns.FLOW, TrimPatterns.BOLT, ModTrimPatterns.KAUPEN);

    private static final List<EquipmentClientInfo.LayerType> HUMANOID_LAYERS =
            List.of(EquipmentClientInfo.LayerType.HUMANOID, EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS);

    private final PackOutput.PathProvider pathProvider;

    public ModAtlasProvider(PackOutput output) {
        super(output);
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "atlases");
    }

    private static List<ResourceLocation> patternTextures() {
        List<ResourceLocation> list = new ArrayList<>(VANILLA_PATTERNS.size() * HUMANOID_LAYERS.size());
        for (ResourceKey<TrimPattern> resourcekey : VANILLA_PATTERNS) {
            ResourceLocation resourcelocation = TrimPatterns.defaultAssetId(resourcekey);
            for (EquipmentClientInfo.LayerType equipmentClientInfo : HUMANOID_LAYERS) {
                list.add(resourcelocation.withPath((string) -> {
                    String assetPrefix = equipmentClientInfo.trimAssetPrefix();
                    return assetPrefix + "/" + string;
                }));
            }
        }
        return list;
    }

    private static SpriteSource forMaterial(Material material) {
        return new SingleFile(material.texture());
    }

    private static SpriteSource forMapper(MaterialMapper mapper) {
        return new DirectoryLister(mapper.prefix(), mapper.prefix() + "/");
    }

    private static Stream<MaterialAssetGroup.AssetInfo> extractAllMaterialAssets() {
        return ModModelProvider.TRIM_MATERIAL_MODELS
                               .stream().map(ItemModelGenerators.TrimMaterialData::assets).flatMap((group) ->
                                             Stream.concat(Stream.of(group.base()), group.overrides().values().stream()))
                                                   .sorted(Comparator.comparing(MaterialAssetGroup.AssetInfo::suffix));
    }

    private static List<SpriteSource> armorTrims() {
        return List.of(new PalettedPermutations(patternTextures(), TRIM_PALETTE_KEY, TRIM_PALETTE_VALUES));
    }

    private static List<SpriteSource> blocksList() {
        return List.of(forMapper(Sheets.BLOCKS_MAPPER), forMapper(Sheets.ITEMS_MAPPER),
                       forMapper(ConduitRenderer.MAPPER), forMaterial(BellRenderer.BELL_RESOURCE_LOCATION),
                       forMaterial(Sheets.DECORATED_POT_SIDE), forMaterial(EnchantTableRenderer.BOOK_LOCATION),
                       new PalettedPermutations(List.of(ItemModelGenerators.TRIM_PREFIX_HELMET,
                                                        ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
                                                        ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
                                                        ItemModelGenerators.TRIM_PREFIX_BOOTS),
                                                TRIM_PALETTE_KEY, TRIM_PALETTE_VALUES));
    }

    private static List<SpriteSource> shieldPatterns() {
        return List.of(forMaterial(ShieldSpecialModelRenderer.ALEXANDRITE_SHIELD_BASE),
                       forMaterial(ShieldSpecialModelRenderer.ALEXANDRITE_SHIELD_BASE_NO_PATTERN));
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        return CompletableFuture.allOf(this.storeAtlas(output, AtlasIds.ARMOR_TRIMS, armorTrims()),
                                       this.storeAtlas(output, AtlasIds.BLOCKS, blocksList()),
                                       this.storeAtlas(output, AtlasIds.SHIELD_PATTERNS, shieldPatterns()));
    }

    private CompletableFuture<?> storeAtlas(CachedOutput output,
                                            ResourceLocation atlasId,
                                            List<SpriteSource> sources) {
        return DataProvider.saveStable(output, SpriteSources.FILE_CODEC, sources, this.pathProvider.json(atlasId));
    }
}