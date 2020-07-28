package org.loomdev.loom.entity.vehicle;

import net.minecraft.entity.vehicle.BoatEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.Boat;
import org.loomdev.loom.entity.EntityImpl;

public class BoatImpl extends EntityImpl implements Boat {

    public BoatImpl(BoatEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.BOAT;
    }

    @Override
    public BoatEntity getMinecraftEntity() {
        return (BoatEntity) super.getMinecraftEntity();
    }

    @Override
    public Type getBoatType() {
        return Type.valueOf(getMinecraftEntity().getBoatType().name());
    }

    @Override
    public void setBoatType(Type type) {
        getMinecraftEntity().setBoatType(BoatEntity.Type.valueOf(type.name()));
    }

    @Override
    public Placement getPlacement() {
        return Placement.valueOf(getMinecraftEntity().checkLocation().name());
    }
}
