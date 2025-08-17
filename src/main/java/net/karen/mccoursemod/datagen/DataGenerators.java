package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = MccourseMod.MOD_ID)
public class DataGenerators {
    // ** CLIENT DATA **
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        // Language file en_us.json -> Language Provider
        generator.addProvider(true, new ModLanguageProvider(packOutput));
        // Block models + Item models -> Model Provider
        generator.addProvider(true, new ModModelProvider(packOutput));
        // Recipes -> Recipe Provider
        generator.addProvider(true, new ModRecipeProvider.Runner(packOutput, lookupProvider));
        // Block Loot Table Provider
        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                              List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new,
                                                                             LootContextParamSets.BLOCK)), lookupProvider));
        // Block tags
        generator.addProvider(true, new ModBlockTagGenerator(packOutput, lookupProvider));
        // Item tags
        generator.addProvider(true, new ModItemTagGenerator(packOutput, lookupProvider));
        // Enchantment tags
        generator.addProvider(true, new ModEnchantmentTagGenerator(packOutput, lookupProvider));
        // Data Map Provider
        generator.addProvider(true, new ModDataMapProvider(packOutput, lookupProvider));
        // Equipment Asset Provider
        generator.addProvider(true, new ModEquipmentAssetProvider(packOutput));
        // Datapack Provider
        generator.addProvider(true, new ModDatapackProvider(packOutput, lookupProvider));
        // Entity Type tags
        generator.addProvider(true, new ModEntityTypeTagGenerator(packOutput, lookupProvider));
        // Sound Provider
        generator.addProvider(true, new ModSoundDefinitionsProvider(packOutput));
        // Painting Variant tags
        generator.addProvider(true, new ModPaintingVariantTagGenerator(packOutput, lookupProvider));
        // Villager Poi Type tags
        generator.addProvider(true, new ModPoiTypeTagsProvider(packOutput, lookupProvider));
        // Loot Modifiers Provider
        generator.addProvider(true, new ModGlobalLootModifierProvider(packOutput, lookupProvider));
        // Particles Provider
        generator.addProvider(true, new ModParticleDescriptionProvider(packOutput));
    }

    // ** SERVER DATA **
    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        // Language file en_us.json -> Language Provider
        generator.addProvider(true, new ModLanguageProvider(packOutput));
        // Block models + Item models -> Model Provider
        generator.addProvider(true, new ModModelProvider(packOutput));
        // Recipes -> Recipe Provider
        generator.addProvider(true, new ModRecipeProvider.Runner(packOutput, lookupProvider));
        // Block Loot Table Provider
        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                              List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new,
                                                                             LootContextParamSets.BLOCK)), lookupProvider));
        // Block tags
        generator.addProvider(true, new ModBlockTagGenerator(packOutput, lookupProvider));
        // Item tags
        generator.addProvider(true, new ModItemTagGenerator(packOutput, lookupProvider));
        // Enchantment tags
        generator.addProvider(true, new ModEnchantmentTagGenerator(packOutput, lookupProvider));
        // Data Map Provider
        generator.addProvider(true, new ModDataMapProvider(packOutput, lookupProvider));
        // Equipment Asset Provider
        generator.addProvider(true, new ModEquipmentAssetProvider(packOutput));
        // Datapack Provider
        generator.addProvider(true, new ModDatapackProvider(packOutput, lookupProvider));
        // Entity Type tags
        generator.addProvider(true, new ModEntityTypeTagGenerator(packOutput, lookupProvider));
        // Sound Provider
        generator.addProvider(true, new ModSoundDefinitionsProvider(packOutput));
        // Painting Variant tags
        generator.addProvider(true, new ModPaintingVariantTagGenerator(packOutput, lookupProvider));
        // Villager Poi Type tags
        generator.addProvider(true, new ModPoiTypeTagsProvider(packOutput, lookupProvider));
        // Loot Modifiers Provider
        generator.addProvider(true, new ModGlobalLootModifierProvider(packOutput, lookupProvider));
        // Particles Provider
        generator.addProvider(true, new ModParticleDescriptionProvider(packOutput));
    }
}