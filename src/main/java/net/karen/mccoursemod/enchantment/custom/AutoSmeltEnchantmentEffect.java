package net.karen.mccoursemod.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public record AutoSmeltEnchantmentEffect(TagKey<Block> blockTag) {
    // CODEC
    public static final Codec<AutoSmeltEnchantmentEffect> CODEC =
           RecordCodecBuilder.create(instance ->
                     instance.group(TagKey.codec(Registries.BLOCK).fieldOf("blockTag")
                                                                  .forGetter(AutoSmeltEnchantmentEffect::blockTag))
                             .apply(instance, AutoSmeltEnchantmentEffect::new));

    // STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, AutoSmeltEnchantmentEffect> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.fromCodec(TagKey.codec(Registries.BLOCK)),
                                 AutoSmeltEnchantmentEffect::blockTag, AutoSmeltEnchantmentEffect::new);

    // CUSTOM METHOD - AUTO SMELT Enchantment Effect EVENT
    public static void autoSmeltEnch(ItemStack tool, BlockState state, ServerLevel serverLevel,
                                     BlockPos pos, Player player, List<ItemStack> finalDrops,
                                     AtomicBoolean cancelVanillaDrop, int hasFortune) {
        EnchantmentHelper.runIterationOnItem(tool, (holder, holderLvl) -> {
            AutoSmeltEnchantmentEffect effects =
                holder.value().effects().get(ModDataComponentTypes.AUTO_SMELT_ENCHANTMENT_EFFECT.get());
            if (effects != null) {
                TagKey<Block> blockHolder = effects.blockTag();
                if (blockHolder != null) {
                    SingleRecipeInput singleRecipe = new SingleRecipeInput(new ItemStack(state.getBlock()));
                    ServerLevel worldServer = serverLevel.getLevel();
                    Optional<RecipeHolder<SmeltingRecipe>> recipe =
                            serverLevel.getServer().getRecipeManager().getRecipeFor(RecipeType.SMELTING, singleRecipe, worldServer);
                    recipe.ifPresentOrElse(result -> {
                           ItemStack recipeValue = result.value().assemble(singleRecipe, worldServer.registryAccess()),
                                            drop = recipeValue.copy();
                           if (state.is(blockHolder)) { drop.setCount((drop.getCount() * hasFortune) * holderLvl); }
                           finalDrops.add(drop);
                    },
                    () -> finalDrops.addAll(Block.getDrops(state, serverLevel, pos, null, player, tool)));
                    cancelVanillaDrop.set(true);
                }
            }
        });
    }
}