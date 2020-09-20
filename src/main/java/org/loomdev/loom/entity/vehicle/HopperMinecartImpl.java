package org.loomdev.loom.entity.vehicle;

import net.minecraft.entity.vehicle.HopperMinecartEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.HopperMinecart;

public class HopperMinecartImpl extends StorageMinecartImpl implements HopperMinecart {

    public HopperMinecartImpl(HopperMinecartEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<HopperMinecart> getType() {
        return EntityType.HOPPER_MINECART;
    }

    @Override
    public @NotNull HopperMinecartEntity getMinecraftEntity() {
        return (HopperMinecartEntity) super.getMinecraftEntity();
    }
}
