package net.karen.mccoursemod.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModBoatEntity extends Boat {
    private static final EntityDataAccessor<Boolean> DATA_ID_PADDLE_LEFT =
            SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> DATA_ID_PADDLE_RIGHT =
            SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> DATA_ID_BUBBLE_TIME =
            SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.INT);

    @Nullable private Leashable.LeashData leashData;

    public ModBoatEntity(EntityType<? extends Boat> entityType, Level level,
                         Supplier<Item> item) {
        super(entityType, level, item);
    }

    @Override
    public void setInitialPos(double x, double y, double z) {
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected double rideHeight(@NotNull EntityDimensions entityDimensions) {
        return (entityDimensions.height() / 3.0F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_PADDLE_LEFT, false);
        builder.define(DATA_ID_PADDLE_RIGHT, false);
        builder.define(DATA_ID_BUBBLE_TIME, 0);
    }

    @Nullable
    @Override
    public LeashData getLeashData() {
        return this.leashData;
    }

    @Override
    protected void addAdditionalSaveData(@NotNull ValueOutput output) {
        this.writeLeashData(output, this.leashData);
    }

    @Override
    protected void readAdditionalSaveData(@NotNull ValueInput input) {
        this.readLeashData(input);
    }
}