package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
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
            // Adding all custom ADVANCEMENTS
            // ** Mccourse Mod root advancement -> ALEXANDRITE **
            AdvancementHolder rootAdvancement =
                Advancement.Builder.advancement()
                           .display(ModItems.ALEXANDRITE.get(),
                                    Component.literal("Mccourse Mod"),
                                    Component.literal("Collect all exclusive ores, armors, tools, etc. on your journey!" +
                                                      " The Power lies in the Alexandrite!"),
                                    ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "block/alexandrite_ore"),
                                    AdvancementType.TASK, true, true, false)
                           .addCriterion("has_alexandrite",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE.get()))
                           .save(consumer, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "root"));

            // ** CUSTOM items advancement -> METAL DETECTOR **
            AdvancementHolder metalDetector =
                Advancement.Builder.advancement()
                           .parent(rootAdvancement)
                           .display(ModItems.METAL_DETECTOR.get(),
                                    Component.literal("Metal Detector"),
                                    Component.literal("Batteries not included! (Actually doesn't need batteries)"),
                                    null, AdvancementType.TASK, true, true, false)
                           .addCriterion("has_metal_detector",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.METAL_DETECTOR.get()))
                           .save(consumer, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "metal_detector"));

            // ** CUSTOM tools advancement **
            // ** CUSTOM VANILLA TOOLS -> Sword, Pickaxe, Shovel, Axe, Hoe, etc. **
            AdvancementHolder alexandriteVanillaTools =
                Advancement.Builder.advancement()
                           .parent(rootAdvancement)
                           .display(ModItems.ALEXANDRITE_PICKAXE.get(),
                                    Component.literal("Alexandrite Vanilla Tools"),
                                    Component.literal("Collect all exclusive Alexandrite vanilla tools!"),
                                    null, AdvancementType.TASK, true, true, false)
                           .addCriterion("has_alexandrite_pickaxe",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE_PICKAXE.get()))
                           .addCriterion("has_alexandrite_axe",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE_AXE.get()))
                           .addCriterion("has_alexandrite_sword",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE_SWORD.get()))
                           .addCriterion("has_alexandrite_shovel",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE_SHOVEL.get()))
                           .addCriterion("has_alexandrite_hoe",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE_HOE.get()))
                           .requirements(AdvancementRequirements.Strategy.AND)
                           .save(consumer, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                                 "alexandrite_vanilla_tools"));

            // ** CUSTOM TOOLS -> Hammer, Paxel, etc. **
            AdvancementHolder alexandriteCustomTools =
                Advancement.Builder.advancement()
                           .parent(alexandriteVanillaTools)
                           .display(ModItems.ALEXANDRITE_HAMMER.get(),
                                    Component.literal("Alexandrite Custom Tools"),
                                    Component.literal("Collect all exclusive Alexandrite custom tools!"),
                                    null, AdvancementType.TASK, true, true, false)
                           .addCriterion("has_alexandrite_hammer",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE_HAMMER.get()))
                           .requirements(AdvancementRequirements.Strategy.AND)
                           .save(consumer, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                                 "alexandrite_custom_tools"));

            // ** CUSTOM ARMORS -> Helmet, Chestplate, Leggings, Boots or Elytra **
            AdvancementHolder alexandriteCustomArmors =
                Advancement.Builder.advancement()
                           .parent(rootAdvancement)
                           .display(ModItems.ALEXANDRITE_CHESTPLATE.get(),
                                    Component.literal("Alexandrite Custom Armors"),
                                    Component.literal("Collect all exclusive Alexandrite custom armors!"),
                                    null, AdvancementType.TASK, true, true, false)
                           .addCriterion("has_alexandrite_helmet",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE_HELMET.get()))
                           .addCriterion("has_alexandrite_chestplate",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE_CHESTPLATE.get()))
                           .addCriterion("has_alexandrite_leggings",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE_LEGGINGS.get()))
                           .addCriterion("has_alexandrite_boots",
                                         InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE_BOOTS.get()))
                           .requirements(AdvancementRequirements.Strategy.AND)
                           .save(consumer, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                                                 "alexandrite_custom_armors"));
        }
    }
}