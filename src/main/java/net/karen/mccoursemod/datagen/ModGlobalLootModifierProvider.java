package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.loot.AddItemModifier;
import net.karen.mccoursemod.loot.AddSusSandItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output,
                                         CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, MccourseMod.MOD_ID);
    }

    // STRUCTURE LOCATIONS
    private final Map<String, String> locations = Map.ofEntries( // CHESTS
            Map.entry("_from_abandoned_mineshaft", "chests/abandoned_mineshaft"), // Abandoned mine
            Map.entry("_from_ancient_city", "chests/ancient_city"),
            Map.entry("_from_ancient_city_ice_box", "chests/ancient_city_ice_box"), // Ancient City
            Map.entry("_from_bastion_bridge", "chests/bastion_bridge"),
            Map.entry("_from_bastion_hoglin_stable", "chests/bastion_hoglin_stable"),
            Map.entry("_from_bastion_other", "chests/bastion_other"),
            Map.entry("_from_bastion_treasure", "chests/bastion_treasure"), // Bastion
            Map.entry("_from_nether_bridge", "chests/nether_bridge"), // Nether
            Map.entry("_from_buried_treasure", "chests/buried_treasure"), // Beach
            Map.entry("_from_desert_pyramid", "chests/desert_pyramid"), // Desert
            Map.entry("_from_end_city_treasure", "chests/end_city_treasure"),
            Map.entry("_from_igloo_chest", "chests/igloo_chest"), // End
            Map.entry("_from_jungle_temple", "chests/jungle_temple"),
            Map.entry("_from_jungle_temple_dispenser", "chests/jungle_temple_dispenser"), // Jungle
            Map.entry("_from_pillager_outpost", "chests/pillager_outpost"),
            Map.entry("_from_woodland_mansion", "chests/woodland_mansion"), // Pillager mansion
            Map.entry("_from_ruined_portal", "chests/ruined_portal"), // Ruined portal
            Map.entry("_from_shipwreck_map", "chests/shipwreck_map"),
            Map.entry("_from_shipwreck_supply", "chests/shipwreck_supply"),
            Map.entry("_from_shipwreck_treasure", "chests/shipwreck_treasure"), // Shipwreck
            Map.entry("_from_simple_dungeon", "chests/simple_dungeon"), // Dungeon
            Map.entry("_from_spawn_bonus_chest", "chests/spawn_bonus_chest"), // Chest option
            Map.entry("_from_stronghold_corridor", "chests/stronghold_corridor"),
            Map.entry("_from_stronghold_crossing", "chests/stronghold_crossing"),
            Map.entry("_from_stronghold_library", "chests/stronghold_library"), // Stronghold
            Map.entry("_from_underwater_ruin_big", "chests/underwater_ruin_big"),
            Map.entry("_from_underwater_ruin_small", "chests/underwater_ruin_small"), // Underwater
            Map.entry("_from_village_armorer", "chests/village/village_armorer"), // VILLAGE
            Map.entry("_from_village_butcher", "chests/village/village_butcher"),
            Map.entry("_from_village_cartographer", "chests/village/village_cartographer"),
            Map.entry("_from_village_desert_house", "chests/village/village_desert_house"),
            Map.entry("_from_village_fisher", "chests/village/village_fisher"),
            Map.entry("_from_village_fletcher", "chests/village/village_fletcher"),
            Map.entry("_from_village_mason", "chests/village/village_mason"),
            Map.entry("_from_village_plains_house", "chests/village/village_plains_house"),
            Map.entry("_from_village_savanna_house", "chests/village/village_savanna_house"),
            Map.entry("_from_village_shepherd", "chests/village/village_shepherd"),
            Map.entry("_from_village_snowy_house", "chests/village/village_snowy_house"),
            Map.entry("_from_village_taiga_house", "chests/village/village_taiga_house"),
            Map.entry("_from_village_tannery", "chests/village/village_tannery"),
            Map.entry("_from_village_temple", "chests/village/village_temple"),
            Map.entry("_from_village_toolsmith", "chests/village/village_toolsmith"),
            Map.entry("_from_village_weaponsmith", "chests/village/village_weaponsmith"));

    @Override
    protected void start() {
        // ** CUSTOM LOOT TABLE items **
        addDropWithBlock("radish_seeds_to_short_grass", Blocks.SHORT_GRASS, 0.25F, ModItems.RADISH_SEEDS.get());
        addDropWithBlock("radish_seeds_to_tall_grass", Blocks.TALL_GRASS, 0.25F, ModItems.RADISH_SEEDS.get());

        addDropWithBlock("kohlrabi_seeds_from_grass", Blocks.GRASS_BLOCK, 0.35F, ModItems.KOHLRABI_SEEDS.get());
        addDropWithBlock("kohlrabi_seeds_from_fern", Blocks.FERN, 0.35F, ModItems.KOHLRABI_SEEDS.get());

        addDropOnStructure("metal_detector_from_jungle_temple", 0.1F, ModItems.METAL_DETECTOR.get());
        addDropOnStructure("chisel_from_jungle_temple", 0.5F, ModItems.CHISEL.get());

        addDropOnEntity("berry_from_creeper", "entities/creeper", ModItems.GOJI_BERRIES.get());

        // ** CUSTOM SUSPICIOUS SAND items **
        addDropOnSuspiciousSand("metal_detector_from_suspicious_sand",
                                "archaeology/desert_pyramid", ModItems.METAL_DETECTOR.get());
    }

    @Override
    public @NotNull String getName() {
        return "Mccourse Mod Global Loot Modifiers";
    }

    // ** CUSTOM METHODS - Global Loot Modifiers **

    // CUSTOM METHOD - Item on broken BLOCK
    private void addDropWithBlock(String name, Block block, float chance, Item drop) {
        LootItemCondition[] conditions =
            new LootItemCondition[] {
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).build(),
                    LootItemRandomChanceCondition.randomChance(chance).build()
            };
        // CUSTOM BLOCK DROP
        this.add(name, new AddItemModifier(conditions, drop));
    }

    // CUSTOM METHOD - Item on STRUCTURE
    private void addDropOnStructure(String name, float chance, Item drop) {
        for (Map.Entry<String, String> local : locations.entrySet()) {
            if (name.contains(local.getKey())) {
                LootItemCondition[] conditions =
                    new LootItemCondition[] {
                            new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(local.getValue())).build(),
                            LootItemRandomChanceCondition.randomChance(chance).build()
                    };
                // CUSTOM STRUCTURE DROP
                this.add(name, new AddItemModifier(conditions, drop));
            }
        }
    }

    // CUSTOM METHOD - Item on SUSPICIOUS SAND
    private void addDropOnSuspiciousSand(String name, String local, Item drop) {
        LootItemCondition[] conditions =
            new LootItemCondition[] {
                    new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(local)).build()
            };
        // CUSTOM SUSPICIOUS SAND DROP
        this.add(name, new AddSusSandItemModifier(conditions, drop));
    }

    // CUSTOM METHOD - Item on hit ENTITIES
    private void addDropOnEntity(String name, String entity, Item drop) {
        LootItemCondition[] conditions =
            new LootItemCondition[] {
                    new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(entity)).build()
            };
        // CUSTOM ENTITY DROP
        this.add(name, new AddItemModifier(conditions, drop));
    }
}