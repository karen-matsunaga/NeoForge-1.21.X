package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagGenerator extends EntityTypeTagsProvider {
    public ModEntityTypeTagGenerator(PackOutput output,
                                     CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MccourseMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(ModTags.Entities.ALL_ENTITIES).addTag(ModTags.Entities.MONSTERS)
                                               .addTag(ModTags.Entities.ANIMALS)
                                               .addTag(ModTags.Entities.VILLAGER)
                                               .addTag(ModTags.Entities.WATER_ANIMALS);

        this.tag(ModTags.Entities.BOSSES).add(EntityType.WITHER, EntityType.ENDER_DRAGON);

        this.tag(ModTags.Entities.NETHER).add(EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.ZOMBIFIED_PIGLIN,
                                              EntityType.BLAZE, EntityType.ZOGLIN, EntityType.GHAST, EntityType.HOGLIN);

        this.tag(ModTags.Entities.END).add(EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.SHULKER);

        this.tag(ModTags.Entities.OVERWORLD).add(EntityType.SLIME, EntityType.SILVERFISH, EntityType.SPIDER,
                                                 EntityType.CAVE_SPIDER, EntityType.GIANT, EntityType.VEX,
                                                 EntityType.DROWNED, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER,
                                                 EntityType.HUSK, EntityType.CREEPER, EntityType.WARDEN, EntityType.PHANTOM,
                                                 EntityType.BREEZE, EntityType.CREAKING);

        // Monster entities
        this.tag(ModTags.Entities.MONSTERS).addTag(ModTags.Entities.OVERWORLD)
                                           .addTag(ModTags.Entities.NETHER)
                                           .addTag(ModTags.Entities.END)
                                           .addTag(ModTags.Entities.BOSSES)
                                           .addTag(EntityTypeTags.SKELETONS)
                                           .addTag(EntityTypeTags.RAIDERS)
                                           .addTag(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES)
                                           .addTag(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES);

        // Animal entities
        this.tag(ModTags.Entities.ANIMALS).add(EntityType.FOX, EntityType.PIG, EntityType.FROG, EntityType.STRIDER, EntityType.OCELOT,
                                               EntityType.PANDA, EntityType.GOAT, EntityType.COW, EntityType.MOOSHROOM,
                                               EntityType.RABBIT, EntityType.CHICKEN, EntityType.BEE, EntityType.HORSE,
                                               EntityType.CAMEL, EntityType.ZOMBIE_HORSE, EntityType.MULE, EntityType.LLAMA,
                                               EntityType.DONKEY, EntityType.SKELETON_HORSE, EntityType.TURTLE,
                                               EntityType.AXOLOTL, EntityType.SNIFFER, EntityType.WOLF, EntityType.PARROT,
                                               EntityType.CAT, EntityType.POLAR_BEAR, EntityType.SHEEP, EntityType.SNOW_GOLEM,
                                               EntityType.ALLAY, EntityType.BAT, EntityType.HAPPY_GHAST, ModEntities.GECKO.get(),
                                               ModEntities.RHINO.get());

        // Villager entities
        this.tag(ModTags.Entities.VILLAGER).add(EntityType.IRON_GOLEM, EntityType.VILLAGER,
                                                EntityType.WANDERING_TRADER, EntityType.TRADER_LLAMA);

        // Water Animal entities
        this.tag(ModTags.Entities.WATER_ANIMALS).add(EntityType.SQUID, EntityType.GLOW_SQUID, EntityType.DOLPHIN,
                                                     EntityType.PUFFERFISH, EntityType.TADPOLE, EntityType.COD,
                                                     EntityType.SALMON, EntityType.TROPICAL_FISH);
    }

    @Override
    public @NotNull String getName() { return "Mccourse Mod Entity Type Tags"; }
}