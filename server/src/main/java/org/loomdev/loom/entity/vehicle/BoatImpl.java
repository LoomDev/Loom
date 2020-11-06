package org.loomdev.loom.entity.vehicle;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.Boat;
import org.loomdev.loom.entity.EntityImpl;

public class BoatImpl extends EntityImpl implements Boat {

    public BoatImpl(net.minecraft.world.entity.vehicle.Boat entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Boat> getType() {
        return EntityType.BOAT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.vehicle.Boat getMinecraftEntity() {
        return (net.minecraft.world.entity.vehicle.Boat) super.getMinecraftEntity();
    }

    @Override
    public Variant getVariant() {
        return Variant.valueOf(getMinecraftEntity().getBoatType().name());
    }

    @Override
    public void setVariant(Variant type) {
        getMinecraftEntity().setType(net.minecraft.world.entity.vehicle.Boat.Type.valueOf(type.name()));
    }

    @Override
    public Placement getPlacement() {
        return Placement.valueOf(getMinecraftEntity().getStatus().name());
    }
}
