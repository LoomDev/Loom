package org.loomdev.loom.entity.vehicle;

import net.minecraft.world.entity.vehicle.AbstractMinecartContainer;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.vehicle.StorageMinecart;

public abstract class AbstractStorageMinecartImpl extends AbstractMinecartImpl implements StorageMinecart {

    public AbstractStorageMinecartImpl(AbstractMinecartContainer entity) {
        super(entity);
    }

    @Override
    @NotNull
    public AbstractMinecartContainer getMinecraftEntity() {
        return (AbstractMinecartContainer) super.getMinecraftEntity();
    }
}
