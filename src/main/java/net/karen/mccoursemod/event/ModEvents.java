package net.karen.mccoursemod.event;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.item.custom.SpecialEffectItem;
import net.karen.mccoursemod.potion.ModPotions;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static net.karen.mccoursemod.item.custom.SpecialEffectItem.getEffectMultiplier;
import static net.karen.mccoursemod.util.ChatUtils.*;
import static net.karen.mccoursemod.util.Utils.block;
import static net.karen.mccoursemod.util.Utils.dropItem;

@EventBusSubscriber(modid = MccourseMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Sheep sheep && event.getSource().getDirectEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() == Items.END_ROD) {
                player.displayClientMessage(standardLiteral(player.getName().getString() +
                                            " just hit a sheep with an END ROD? YOU SICK FRICK!"), true);
                sheep.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 6));
                player.getMainHandItem().shrink(1);
            }
        }
    }

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        // SLIMEY POTION
        builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION);
        // FLY POTION
        builder.addMix(Potions.AWKWARD, Items.EMERALD, ModPotions.FLY_POTION);
        // FLY II POTION
        builder.addMix(ModPotions.FLY_POTION, Items.EMERALD_BLOCK, ModPotions.FLY_II_POTION);
        // NOTHING POTION
        builder.addMix(Potions.AWKWARD, Items.GLOWSTONE, ModPotions.NOTHING_POTION);
        // HASTE POTION
        builder.addMix(Potions.AWKWARD, Items.CARROT, ModPotions.HASTE_POTION);
    }

    // CUSTOM EVENT -> AUTO SMELT, MAGNET, MORE ORES, MULTIPLIER and RAINBOW custom effects
    @SubscribeEvent
    public static void onBlockBreakWithCustomEnchantments(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        LevelAccessor world = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();
        ItemStack tool = player.getMainHandItem();
        Level level = (Level) event.getLevel();
        if (tool.isEmpty()) { return; } // * PROBLEMS *
        HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        int fortune = EnchantmentHelper.getTagEnchantmentLevel(ench.getOrThrow(Enchantments.FORTUNE).getDelegate(), tool);
        boolean hasRainbow = SpecialEffectItem.getMultiplierBool(tool, ModDataComponentTypes.RAINBOW.get()),
                hasMoreOres = SpecialEffectItem.getMultiplierBool(tool, ModDataComponentTypes.MORE_ORES.get()),
                hasAutoSmelt = SpecialEffectItem.getMultiplierBool(tool, ModDataComponentTypes.AUTO_SMELT.get()),
                hasMagnet = SpecialEffectItem.getMultiplierBool(tool, ModDataComponentTypes.MAGNET.get());
        if (!level.isClientSide() && world instanceof ServerLevel serverLevel) {
            boolean cancelVanillaDrop = false; // Adapt the drop according to the enchantment being true
            List<ItemStack> finalDrops = new ArrayList<>(); // Items caused by enchantments are stored in the list
            int oresFortune = serverLevel.random.nextInt(fortune + 1),
                    hasFortune = (fortune > 0) ? (1 + oresFortune) : 1;
            if (hasRainbow) { // * RAINBOW EFFECT *
                Map<Block, TagKey<Block>> rainbowMap = Map.ofEntries(Map.entry(Blocks.COAL_BLOCK, Tags.Blocks.ORES_COAL),
                Map.entry(Blocks.COPPER_BLOCK, Tags.Blocks.ORES_COPPER), Map.entry(Blocks.DIAMOND_BLOCK, Tags.Blocks.ORES_DIAMOND),
                Map.entry(Blocks.EMERALD_BLOCK, Tags.Blocks.ORES_EMERALD), Map.entry(Blocks.GOLD_BLOCK, Tags.Blocks.ORES_GOLD),
                Map.entry(Blocks.IRON_BLOCK, Tags.Blocks.ORES_IRON), Map.entry(Blocks.LAPIS_BLOCK, Tags.Blocks.ORES_LAPIS),
                Map.entry(Blocks.REDSTONE_BLOCK, Tags.Blocks.ORES_REDSTONE),
                Map.entry(Blocks.NETHERITE_BLOCK, Tags.Blocks.ORES_NETHERITE_SCRAP));
                for (Map.Entry<Block, TagKey<Block>> entry : rainbowMap.entrySet()) {
                    if (state.is(entry.getValue())) {
                        block(world, pos, entry.getKey(), event);
                        return;
                    }
                }
                if (state.is(ModTags.Blocks.RAINBOW_DROPS)) {
                    ItemStack rainbowDrop = new ItemStack(state.getBlock());
                    rainbowDrop.setCount((rainbowDrop.getCount() * hasFortune) *
                            (getEffectMultiplier(tool, ModDataComponentTypes.RAINBOW.get(), 1)));
                    finalDrops.add(rainbowDrop);
                    cancelVanillaDrop = true;
                }
            }
            if (hasMoreOres) { // * MORE ORES EFFECT *
                if (state.is(ModTags.Blocks.MORE_ORES_BREAK_BLOCK)) {
                    Iterable<Holder<Block>> tagBlock = BuiltInRegistries.BLOCK.getTagOrEmpty(ModTags.Blocks.MORE_ORES_ALL_DROPS);
                    tagBlock.forEach((block -> {
                        if (serverLevel.random.nextFloat() < 0.01F) {
                            ItemStack drop = new ItemStack(block.value().asItem()); // Increase ore drop with Multiplier enchantment
                            drop.setCount((drop.getCount() * hasFortune) *
                                    (getEffectMultiplier(tool, ModDataComponentTypes.MORE_ORES.get(), 1)));
                            finalDrops.add(drop); // Break block and ore chance drop
                        }
                    }));
                    // Break block and ore chance drop
                    finalDrops.addAll(Block.getDrops(state, serverLevel, pos, null, player, tool));
                    cancelVanillaDrop = true;
                }
            }
            if (hasAutoSmelt) { // * AUTO SMELT EFFECT *
                SingleRecipeInput singleRecipe = new SingleRecipeInput(new ItemStack(state.getBlock()));
                ServerLevel worldServer = serverLevel.getLevel();
                Optional<RecipeHolder<SmeltingRecipe>> recipe =
                        serverLevel.getServer().getRecipeManager().getRecipeFor(RecipeType.SMELTING, singleRecipe, worldServer);
                recipe.ifPresent(result -> {
                    ItemStack recipeValue = result.value().assemble(singleRecipe, worldServer.registryAccess()),
                            drop = new ItemStack(recipeValue.getItem().asItem());
                    if (state.is(ModTags.Blocks.AUTO_SMELT_ORES)) {
                        drop.setCount((drop.getCount() * hasFortune) *
                                (getEffectMultiplier(tool, ModDataComponentTypes.AUTO_SMELT.get(), 1)));
                    }
                    finalDrops.add(drop);
                });
                if (recipe.isEmpty()) { finalDrops.addAll(Block.getDrops(state, serverLevel, pos, null, player, tool)); }
                cancelVanillaDrop = true;
            }
            if (hasMagnet && !state.isAir()) { // * MAGNETIC EFFECT *
                if (finalDrops.isEmpty()) { // FinalDrops empty list added all items on it is
                    finalDrops.addAll(Block.getDrops(state, serverLevel, pos, null, player, tool));
                }
                finalDrops.forEach(drop -> { // FinalDrops list added on Player's inventory
                    if (!player.getInventory().add(drop)) { player.drop(drop, false); }});
                block(serverLevel, pos, Blocks.AIR, event);
                return;
            }
            if (cancelVanillaDrop) { // FinalDrops list accumulate drop on world
                block(serverLevel, pos, Blocks.AIR, event);
                finalDrops.forEach(drop -> dropItem(serverLevel, pos, drop));
            }
        }
    }
}