package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentAssetProvider extends EquipmentAssetProvider {
    protected final PackOutput.PathProvider pathProvider;

    public ModEquipmentAssetProvider(PackOutput packOutput) {
        super(packOutput);
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    // List of EQUIPMENT ASSET
    public static final ResourceKey<EquipmentAsset> BISMUTH = createId("bismuth");
    public static final ResourceKey<EquipmentAsset> ALEXANDRITE = createId("alexandrite");

    // CUSTOM METHOD - Register all custom equipment assets -> Resource Key
    private static ResourceKey<EquipmentAsset> createId(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID,
                                  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

    // CUSTOM METHOD - Register all custom entities EQUIPMENT ASSETS
    public static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>,
                                 EquipmentClientInfo> consumer) {
        registerAssetWithLayers(consumer, BISMUTH, "bismuth");
        registerAssetWithLayers(consumer, ALEXANDRITE, "alexandrite");
    }

    // CUSTOM METHOD - Register all custom Horse EQUIPMENT ASSETS
    private static void registerAssetWithLayers(BiConsumer<ResourceKey<EquipmentAsset>,
                                                EquipmentClientInfo> consumer,
                                                ResourceKey<EquipmentAsset> asset, String name) {
        consumer.accept(asset, EquipmentClientInfo.builder()
                .addHumanoidLayers(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name))
                .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY,
                           new EquipmentClientInfo.Layer(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name)))
                .build());
    }

    // DEFAULT METHOD - Save all custom EQUIPMENT ASSETS
    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> map = new HashMap<>();
        bootstrap((key, info) -> {
            if (map.putIfAbsent(key, info) != null) {
                throw new IllegalStateException("Duplicate equipment model for id: " + key.location());
            }
        });
        return DataProvider.saveAll(output, EquipmentClientInfo.CODEC, this.pathProvider::json, map);
    }
}