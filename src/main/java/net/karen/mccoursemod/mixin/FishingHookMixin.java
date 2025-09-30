package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.loot.ModLootTables;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

@Mixin(FishingHook.class)
public abstract class FishingHookMixin {
    @Shadow private int timeUntilHooked, timeUntilLured, nibble;
    @Shadow @Nullable public abstract Player getPlayerOwner();
    @Shadow @Nullable private Entity hookedIn;
    @Shadow @Final private int luck;

    // DEFAULT METHOD - Player fishes faster
    @Inject(method = "tick", at = @At("HEAD"))
    private void reduceFishingWaitTime(CallbackInfo ci) {
        Player player = getPlayerOwner(); // Player Fishing Rod OWNER
        if (player != null && !player.level().isClientSide()) {
            ItemStack fishingRod = player.getMainHandItem(); // Player has Mccourse Fishing Rod on MAIN HAND
            if (fishingRod.is(ModItems.MCCOURSE_MOD_FISHING_ROD)) {
                // 1- Waiting time until a FISH starts to approach
                this.timeUntilLured = Math.min(this.timeUntilLured, 10);
                // 2- Hook swing time (FISH agitation phase)
                this.nibble = Math.min(this.nibble, 10);
                // 3- Time remaining until the FISH actually bites the hook
                this.timeUntilHooked = Math.min(this.timeUntilHooked, 20);
            }
        }
    }

    // DEFAULT METHOD - Player retrieve fish
    @Inject(method = "retrieve", at = @At("HEAD"), cancellable = true)
    private void retrieveFish(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        FishingHook fishingHook = (FishingHook) (Object) this;
        Player player = fishingHook.getPlayerOwner();
        boolean isPlayer = player != null && !player.level().isClientSide() &&
                           player.getMainHandItem().is(Tags.Items.TOOLS_FISHING_ROD);
        if (isPlayer && !neoForge_1_21_X$shouldStopFishing(fishingHook, player)) {
            int i = 0;
            ItemFishedEvent event = null;
            Entity hooked = this.hookedIn;
            Level level = fishingHook.level();
            Entity owner = fishingHook.getOwner();
            if (hooked != null) {
                neoForge_1_21_X$pullEntity(fishingHook, hooked);
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer) player, stack, fishingHook, Collections.emptyList());
                level.broadcastEntityEvent(fishingHook, (byte) 31);
                i = hooked instanceof ItemEntity ? 3 : 5;
            }
            else if (this.nibble > 0 && owner != null) {
                LootParams lootparams =
                    new LootParams.Builder((ServerLevel) level)
                                  .withParameter(LootContextParams.ORIGIN, fishingHook.position())
                                  .withParameter(LootContextParams.TOOL, stack)
                                  .withParameter(LootContextParams.THIS_ENTITY, fishingHook)
                                  .withParameter(LootContextParams.ATTACKING_ENTITY, owner)
                                  .withLuck((float) this.luck + player.getLuck()).create(LootContextParamSets.FISHING);
                ReloadableServerRegistries.Holder loot = level.getServer().reloadableRegistries();
                if (player.getMainHandItem().is(ModItems.MCCOURSE_MOD_FISHING_ROD)) {
                    LootTable modFish = loot.getLootTable(ModLootTables.MCCOURSE_MOD_FISHING_ROD_TREASURE);
                    neoForge_1_21_X$fishLootTable(fishingHook, modFish, lootparams, level, player, stack, cir);
                }
                else {
                    LootTable vanillaFish = loot.getLootTable(BuiltInLootTables.FISHING);
                    neoForge_1_21_X$fishLootTable(fishingHook, vanillaFish, lootparams, level, player, stack, cir);
                }
                i = 1;
            }
            if (fishingHook.onGround()) { i = 2; }
            fishingHook.discard();
            cir.setReturnValue(event != null ? event.getRodDamage() : i);
        }
        else { cir.setReturnValue(0); }
    }

    // CUSTOM METHOD - Player stop fishing
    @Unique
    private boolean neoForge_1_21_X$shouldStopFishing(FishingHook fishingHook, Player player) {
        ItemStack itemstack = player.getMainHandItem();
        ItemStack itemstack1 = player.getOffhandItem();
        boolean flag = itemstack.canPerformAction(ItemAbilities.FISHING_ROD_CAST);
        boolean flag1 = itemstack1.canPerformAction(ItemAbilities.FISHING_ROD_CAST);
        if (!player.isRemoved() && player.isAlive() && (flag || flag1) &&
            !(fishingHook.distanceToSqr(player) > (double) 1024.0F)) {
            return false;
        }
        else {
            fishingHook.discard();
            return true;
        }
    }

    // CUSTOM METHOD - Player fishing position
    @Unique
    protected void neoForge_1_21_X$pullEntity(FishingHook fishingHook, Entity entity1) {
        Entity entity = fishingHook.getOwner();
        if (entity != null) {
            Vec3 vec3 = new Vec3(entity.getX() - fishingHook.getX(),
                                 entity.getY() - fishingHook.getY(),
                                 entity.getZ() - fishingHook.getZ()).scale(0.1);
            entity1.setDeltaMovement(entity1.getDeltaMovement().add(vec3));
        }
    }

    // CUSTOM METHOD - FISH loot table
    @Unique
    private void neoForge_1_21_X$fishLootTable(FishingHook fishingHook, LootTable lootTable,
                                               LootParams lootparams,
                                               Level level, Player player,
                                               ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        List<ItemStack> list = lootTable.getRandomItems(lootparams);
        ItemFishedEvent event = new ItemFishedEvent(list, fishingHook.onGround() ? 2 : 1, fishingHook);
        NeoForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            fishingHook.discard();
            cir.setReturnValue(event.getRodDamage());
        }
        CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer) player, stack, fishingHook, list);
        double x = fishingHook.getX();
        double y = fishingHook.getY();
        double z = fishingHook.getZ();
        double playerX = player.getX();
        double playerY = player.getY();
        double playerZ = player.getZ();
        for (ItemStack itemstack : list) {
            ItemEntity itementity = new ItemEntity(level, x, y, z, itemstack);
            double d0 = playerX - x;
            double d1 = playerY - y;
            double d2 = playerZ - z;
            itementity.setDeltaMovement(d0 * 0.1,
                                        d1 * 0.1 + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08,
                                        d2 * 0.1);
            level.addFreshEntity(itementity);
            player.level().addFreshEntity(new ExperienceOrb(player.level(), playerX,
                                                            playerY + (double) 0.5F,
                                                            playerZ + (double) 0.5F,
                                                            level.random.nextInt(6) + 1));
            if (itemstack.is(ItemTags.FISHES)) { player.awardStat(Stats.FISH_CAUGHT, 1); }
        }
    }
}