package org.loomdev.loom.entity.vehicle;

import net.minecraft.entity.vehicle.MinecartEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.RideableMinecart;

public class RideableMinecartImpl extends MinecartImpl implements RideableMinecart {

    public RideableMinecartImpl(MinecartEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<RideableMinecart> getType() {
        return EntityType.MINECART;
    }

    @Override
    public @NotNull MinecartEntity getMinecraftEntity() {
        return (MinecartEntity) super.getMinecraftEntity();
    }

}
