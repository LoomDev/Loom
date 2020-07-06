package org.loomdev.loom.entity.mob;

import org.loomdev.api.entity.mob.HostileEntity;

public class HostileEntityImpl extends PathAwareEntityImpl implements HostileEntity {
    public HostileEntityImpl(net.minecraft.entity.mob.HostileEntity entity) {
        super(entity);
    }
}
