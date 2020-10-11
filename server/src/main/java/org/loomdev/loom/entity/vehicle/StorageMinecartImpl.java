package org.loomdev.loom.entity.vehicle;

import net.minecraft.entity.vehicle.StorageMinecartEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.vehicle.StorageMinecart;

public abstract class StorageMinecartImpl extends MinecartImpl implements StorageMinecart {

    public StorageMinecartImpl(StorageMinecartEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull StorageMinecartEntity getMinecraftEntity() {
        return (StorageMinecartEntity) super.getMinecraftEntity();
    }

}
