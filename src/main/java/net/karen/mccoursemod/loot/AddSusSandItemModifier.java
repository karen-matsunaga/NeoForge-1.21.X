package net.karen.mccoursemod.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class AddSusSandItemModifier extends LootModifier {
    public static final MapCodec<AddSusSandItemModifier> CODEC =
           RecordCodecBuilder.mapCodec(inst ->
                                       LootModifier.codecStart(inst).and(BuiltInRegistries.ITEM.byNameCodec()
                                                                    .fieldOf("item")
                                                                    .forGetter(m -> m.item))
                                                                    .apply(inst, AddSusSandItemModifier::new));
    private final Item item;

    public AddSusSandItemModifier(LootItemCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.item = item;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> generatedLoot,
                                                          @NotNull LootContext context) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) { return generatedLoot; }
        }
        // THIS IS WAY TOO HIGH. Suggest something like 0.11f ish
        if (context.getRandom().nextFloat() < 0.20f) {
            generatedLoot.clear();
            generatedLoot.add(new ItemStack(this.item)); // Generate new suspicious sand item
        }
        return generatedLoot;
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() { return CODEC; }
}