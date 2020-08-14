package org.loomdev.loom.entity.vehicle;

import net.minecraft.entity.vehicle.BoatEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.Boat;
import org.loomdev.loom.entity.EntityImpl;

public class BoatImpl extends EntityImpl implements Boat {

    public BoatImpl(BoatEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BOAT;
    }

    @Override
    public BoatEntity getMinecraftEntity() {
        return (BoatEntity) super.getMinecraftEntity();
    }

    @Override
    public Variant getVariant() {
        return Variant.valueOf(getMinecraftEntity().getBoatType().name());
    }

    @Override
    public void setVariant(Variant type) {
        getMinecraftEntity().setBoatType(BoatEntity.Type.valueOf(type.name()));
    }

    @Override
    public Placement getPlacement() {
        return Placement.valueOf(getMinecraftEntity().checkLocation().name());
    }
}
