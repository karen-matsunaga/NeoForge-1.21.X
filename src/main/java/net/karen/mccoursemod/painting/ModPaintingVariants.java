package net.karen.mccoursemod.painting;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import java.util.Optional;

public class ModPaintingVariants {
    public static final ResourceKey<PaintingVariant> WORLD = create("world");
    public static final ResourceKey<PaintingVariant> SHRIMP = create("shrimp");
    public static final ResourceKey<PaintingVariant> SAW_THEM = create("saw_them");

    // CUSTOM METHOD - Registry all custom Painting Variant Data Generation JSON file
    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, WORLD, 2, 2);
        register(context, SHRIMP, 2, 1);
        register(context, SAW_THEM, 2, 2);
    }

    // CUSTOM METHOD - Registry all custom Painting Variant with AUTHOR
    private static void register(BootstrapContext<PaintingVariant> context,
                                 ResourceKey<PaintingVariant> key, int width, int height) {
        register(context, key, width, height, true);
    }

    // CUSTOM METHOD - Registry all custom Painting Variant WITHOUT AUTHOR + TOOLTIP DESCRIPTION COLOR
    private static void register(BootstrapContext<PaintingVariant> context,
                                 ResourceKey<PaintingVariant> key, int width, int height,
                                 boolean hasAuthor) {
        context.register(key, new PaintingVariant(width, height, key.location(),
                                                  Optional.of(Component.translatable(
                                                              key.location().toLanguageKey("painting", "title"))
                                                                            .withStyle(ChatFormatting.YELLOW)),
                                                  hasAuthor ? Optional.of(Component.translatable(
                                                                          key.location().toLanguageKey("painting", "author"))
                                                                                        .withStyle(ChatFormatting.GRAY))
                                                            : Optional.empty()));
    }

    // CUSTOM METHOD - Registry all custom Painting Variant resource key
    private static ResourceKey<PaintingVariant> create(String name) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }
}