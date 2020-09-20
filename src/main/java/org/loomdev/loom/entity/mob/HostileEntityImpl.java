package org.loomdev.loom.entity.mob;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.HostileEntity;

public abstract class HostileEntityImpl extends PathAwareEntityImpl implements HostileEntity {
    public HostileEntityImpl(net.minecraft.entity.mob.HostileEntity entity) {
        super(entity);
    }

    @Override
    public net.minecraft.entity.mob.@NotNull HostileEntity getMinecraftEntity() {
        return (net.minecraft.entity.mob.HostileEntity) super.getMinecraftEntity();
    }
}
