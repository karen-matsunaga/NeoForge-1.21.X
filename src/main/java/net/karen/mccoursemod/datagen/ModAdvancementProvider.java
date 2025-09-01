package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(PackOutput output,
                                  CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new ModAdvancementProvider.ModAdvancementSubProvider()));
    }

    public static class ModAdvancementSubProvider implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.@NotNull Provider provider,
                             @NotNull Consumer<AdvancementHolder> consumer) {
            // Adding all custom advancement
            // ALEXANDRITE advancement
            AdvancementHolder rootAdvancement =
                Advancement.Builder.advancement()
                           .display(ModItems.ALEXANDRITE.get(),
                            Component.literal("Alexandrite"),
                            Component.literal("The Power lies in the Alexandrite!"),
                            ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "textures/block/alexandrite_ore.png"),
                            AdvancementType.TASK, true, true, false)
                           .addCriterion("has_alexandrite",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE.get()))
                           // Alexandrite advancement
                           .save(consumer, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "alexandrite"));

            // METAL DETECTOR advancement
            AdvancementHolder metalDetector =
                Advancement.Builder.advancement()
                           .parent(rootAdvancement)
                           .display(ModItems.METAL_DETECTOR.get(),
                            Component.literal("Metal Detector"),
                            Component.literal("Batteries not included! (Actually doesn't need batteries)"),
                            null, AdvancementType.TASK, true, true, false)
                           .addCriterion("has_metal_detector",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.METAL_DETECTOR.get()))
                           // Metal Detector advancement
                           .save(consumer, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "metal_detector"));
        }
    }
}