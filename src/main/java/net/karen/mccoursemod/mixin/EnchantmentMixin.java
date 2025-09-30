package net.karen.mccoursemod.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;
import static net.karen.mccoursemod.util.ChatUtils.*;

@Mixin(value = Enchantment.class)
public abstract class EnchantmentMixin {
    // DEFAULT METHOD - Mccourse Mod and Vanilla NEW enchantment tooltips
    @Inject(method = "getFullname", at = @At(value = "TAIL"), cancellable = true)
    private static void getFullname(Holder<Enchantment> holder, int level, CallbackInfoReturnable<Component> cir) {
        Enchantment enchantment = holder.value(); // ENCHANTMENT name
        MutableComponent enchantmentComponent = enchantment.description().copy(); // Old enchantment TOOLTIP
        int maxLevel = enchantment.getMaxLevel(); // Enchantment MAX LEVEL
        ChatFormatting color = getEnchantmentColor(holder); // Enchantment COLOR by category
        String icon = icon(holder); // Enchantment ICON by category
        // Apply NEW enchantment tooltip
        boolean isCurse = holder.is(EnchantmentTags.CURSE);
        ChatFormatting colors = isCurse ? red : color, formats = isCurse ? italic : bold;
        ComponentUtils.mergeStyles(enchantmentComponent, Style.EMPTY.withColor(colors).applyFormat(formats));
        if (level != 0 || maxLevel != 0) { // Enchantment level equals 1+
            MutableComponent line =
                   enchantmentComponent.append(CommonComponents.SPACE)
                                       .append(standardLiteral(level + " / " + maxLevel)) // Level
                                       .append(CommonComponents.SPACE).append(standardLiteral(icon)); // Icon
            Enchantment enchName = holder.value();
            Level mcLevel = Minecraft.getInstance().level;
            if (mcLevel != null) {
                Registry<Enchantment> enchantmentRegistry = mcLevel.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                ResourceLocation enchantKey = enchantmentRegistry.getKey(enchName);
                if (enchantKey != null) {
                    String descriptionKey = "enchantment." + enchantKey.getNamespace() + "." + enchantKey.getPath() + ".desc";
                    if (I18n.exists(descriptionKey)) {
                        MutableComponent line1 = description(I18n.get(descriptionKey), colors, List.of(false, false));
                        Component combined = ComponentUtils.formatList(List.of(line, line1), CommonComponents.NEW_LINE);
                        // Return NEW enchantment tooltip
                        cir.setReturnValue(combined);
                    }
                }
            }
        }
    }

    // DEFAULT METHOD - Mccourse Mod and Vanilla MAX enchantment levels
    @Inject(at = @At("HEAD"), method = "getMaxLevel", cancellable = true)
    private void getMaxLevel(CallbackInfoReturnable<Integer> info) {
        Enchantment enchantment = (Enchantment) (Object) this;
        DataComponentMap dataCompMap = enchantment.effects();
        int maxLevel = enchantment.definition().maxLevel();
        // LURE enchantment MAX level
        if (dataCompMap.has(EnchantmentEffectComponents.FISHING_TIME_REDUCTION)) {
            info.setReturnValue(maxLevel);
        }
        // LOYALTY enchantment MAX level
        else if (dataCompMap.has(EnchantmentEffectComponents.TRIDENT_RETURN_ACCELERATION)) {
            info.setReturnValue(10);
        }
        // ALL enchantments with 2+ MAX LEVEL -> Ex: FORTUNE 255. (Except AQUA AFFINITY and MENDING enchantments)
        else if (maxLevel > 1 || dataCompMap.has(EnchantmentEffectComponents.ATTRIBUTES) ||
                 dataCompMap.has(EnchantmentEffectComponents.REPAIR_WITH_XP)) {
            info.setReturnValue(255);
        }
        // ALL enchantments with 1 MAX Level
        else { info.setReturnValue(1); }
    }
}