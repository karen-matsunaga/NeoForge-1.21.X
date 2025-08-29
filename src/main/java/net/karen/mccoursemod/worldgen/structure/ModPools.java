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
    public static final ResourceKey<StructureTemplatePool> KAUPEN_HOUSE = create("kaupen_house/start_pool");

    // CUSTOM METHOD - Register all custom template pools
    private static ResourceKey<StructureTemplatePool> create(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

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
    }
}