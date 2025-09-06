package net.karen.mccoursemod.command;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerHomesData extends SavedData {
    private final Map<UUID, CompoundTag> playerHomes = new HashMap<>();

    public static final Codec<PlayerHomesData> CODEC =
           Codec.unboundedMap(Codec.STRING.xmap(UUID::fromString, UUID::toString), CompoundTag.CODEC)
                .xmap(PlayerHomesData::new, PlayerHomesData::getPlayerHomes);

    private static final SavedDataType<PlayerHomesData> playerHomesData =
            new SavedDataType<>("mccoursemod.pos",
                                () -> new PlayerHomesData(Collections.emptyMap()),
                                CODEC, DataFixTypes.PLAYER);

    public Map<UUID, CompoundTag> getPlayerHomes() {
        return playerHomes;
    }

    public PlayerHomesData(Map<UUID, CompoundTag> homes) {
        this.playerHomes.putAll(homes);
    }

    public static PlayerHomesData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(playerHomesData);
    }

    public @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        for (Map.Entry<UUID, CompoundTag> entry : playerHomes.entrySet()) {
            tag.put(entry.getKey().toString(), entry.getValue());
        }
        return tag;
    }

    // CUSTOM METHOD - LIST homes command
    public CompoundTag getHomes(UUID uuid) {
        return playerHomes.computeIfAbsent(uuid, u -> new CompoundTag());
    }

    // CUSTOM METHOD - SET home command
    public void setHome(UUID uuid, String name, BlockPos pos) {
        CompoundTag homes = getHomes(uuid);
        homes.putIntArray(name, new int[]{pos.getX(), pos.getY(), pos.getZ()});
        playerHomes.put(uuid, homes);
        setDirty();
    }

    // CUSTOM METHOD - DELETE home command
    public boolean removeHome(UUID uuid, String name) {
        CompoundTag homes = getHomes(uuid);
        if (homes.contains(name)) {
            homes.remove(name);
            setDirty();
            return true;
        }
        return false;
    }

    // DEFAULT METHOD - SAVE all home positions on disk
    @Override
    public boolean isDirty() { return true; }
}