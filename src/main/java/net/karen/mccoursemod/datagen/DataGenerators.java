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
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        // Language file en_us.json
        event.addProvider(new ModLanguageProvider(packOutput));
        // Block models + Item models
        event.addProvider(new ModModelProvider(packOutput));
        // Recipes
        event.addProvider(new ModRecipeProvider.Runner(packOutput, lookupProvider));
        // Block Loot Table
        event.addProvider(new LootTableProvider(packOutput, Collections.emptySet(),
                          List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new,
                                                                         LootContextParamSets.BLOCK)), lookupProvider));
        // Block tags
        event.addProvider(new ModBlockTagGenerator(packOutput, lookupProvider));
        // Item tags
        event.addProvider(new ModItemTagGenerator(packOutput, lookupProvider));
        // Enchantment tags
        event.addProvider(new ModEnchantmentTagGenerator(packOutput, lookupProvider));
        // Data Map
        event.addProvider(new ModDataMapProvider(packOutput, lookupProvider));
        // Equipment Asset
        event.addProvider(new ModEquipmentAssetProvider(packOutput));
        // Datapack Provider
        event.addProvider(new ModDatapackProvider(packOutput, lookupProvider));
        // Entity Type tags
        event.addProvider(new ModEntityTypeTagGenerator(packOutput, lookupProvider));
        // Sound Provider
        event.addProvider(new ModSoundDefinitionsProvider(packOutput));
        // Painting Variant tags
        event.addProvider(new ModPaintingVariantTagGenerator(packOutput, lookupProvider));
    }
}