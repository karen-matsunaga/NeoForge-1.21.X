package net.karen.mccoursemod.worldgen.structure;

import com.mojang.datafixers.util.Pair;
import net.karen.mccoursemod.MccourseMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import java.util.List;

public class ModPools {
    // Register all custom template pools
    public static final ResourceKey<StructureTemplatePool> KAUPEN_HOUSE = create("kaupen_house/start_pool");

    public static final ResourceKey<StructureTemplatePool>
           STORAGE_PLATFORM_SIDE = create("storage_platform/side_pool");

    public static final ResourceKey<StructureTemplatePool>
           STORAGE_PLATFORM_START = create("storage_platform/start_pool");

    // CUSTOM METHOD - Register all custom template pools
    private static ResourceKey<StructureTemplatePool> create(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

    // DEFAULT METHOD - Data Generation of template pools for each structure and jigsaw structure (JSON file)
    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        // TEMPLATE POOL
        HolderGetter<StructureTemplatePool> templatePools = context.lookup(Registries.TEMPLATE_POOL);
        // EMPTY TEMPLATE POOL
        Holder.Reference<StructureTemplatePool> empty = templatePools.getOrThrow(Pools.EMPTY);

        // KAUPEN HOUSE - CUSTOM TEMPLATE POOL
        context.register(KAUPEN_HOUSE,
                         new StructureTemplatePool(empty,
                                                   List.of(Pair.of(StructurePoolElement.single("mccoursemod:kaupen_house"),
                                                                   1)),
                                                   StructureTemplatePool.Projection.RIGID));

        // STORAGE PLATFORM - CUSTOM JIGSAW TEMPLATE POOL
        // SIDE POOL
        context.register(STORAGE_PLATFORM_SIDE,
                         new StructureTemplatePool(empty,
                         List.of(Pair.of(StructurePoolElement.single("mccoursemod:diamond_storage_platform"), 2),
                                 Pair.of(StructurePoolElement.single("mccoursemod:gold_storage_platform"), 3),
                                 Pair.of(StructurePoolElement.single("mccoursemod:iron_storage_platform"), 5)),
                         StructureTemplatePool.Projection.RIGID));

        // START POOL
        context.register(STORAGE_PLATFORM_START,
                         new StructureTemplatePool(empty,
                         List.of(Pair.of(StructurePoolElement.single("mccoursemod:storage_platform"), 1)),
                         StructureTemplatePool.Projection.RIGID));
    }
}