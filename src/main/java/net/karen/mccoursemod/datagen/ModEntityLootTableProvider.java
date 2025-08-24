package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import java.util.stream.Stream;

public class ModEntityLootTableProvider extends EntityLootSubProvider {
    protected ModEntityLootTableProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        // ** CUSTOM mob entities **
        // GECKO
        add(ModEntities.GECKO.get(),
            LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                     .add(LootItem.lootTableItem(ModItems.GECKO_SPAWN_EGG.get())
                     .when(LootItemRandomChanceCondition.randomChance(0.3f))
                     .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                     .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F))))));
        // RHINO
        add(ModEntities.RHINO.get(),
            LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                     .add(LootItem.lootTableItem(ModItems.DATA_TABLET.get())
                     .apply(SetItemCountFunction.setCount(UniformGenerator.between(0F, 2.0F))))));
        // ** CUSTOM Throwable Projectiles **
        add(ModEntities.TOMAHAWK.get(), LootTable.lootTable());
        add(ModEntities.TORCH_BALL.get(), LootTable.lootTable());
        add(ModEntities.BOUNCY_BALLS.get(), LootTable.lootTable());
        // ** CUSTOM Sittable Block Model **
        add(ModEntities.CHAIR_ENTITY.get(), LootTable.lootTable());
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return BuiltInRegistries.ENTITY_TYPE
                                .stream()
                                .filter(type -> BuiltInRegistries.ENTITY_TYPE.getKey(type)
                                                                                          .getNamespace()
                                                                                          .equals(MccourseMod.MOD_ID));
    }
}