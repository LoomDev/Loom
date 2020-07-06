package org.loomdev.loom.entity.passive;

import org.loomdev.api.entity.passive.PassiveEntity;
import org.loomdev.loom.entity.mob.PathAwareEntityImpl;

public class PassiveEntityImpl extends PathAwareEntityImpl implements PassiveEntity {
    public PassiveEntityImpl(net.minecraft.entity.passive.PassiveEntity entity) {
        super(entity);
    }
}
