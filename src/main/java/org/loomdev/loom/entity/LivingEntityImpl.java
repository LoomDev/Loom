package org.loomdev.loom.entity;

import org.loomdev.api.entity.LivingEntity;

public class LivingEntityImpl extends EntityImpl implements LivingEntity {
    public LivingEntityImpl(net.minecraft.entity.LivingEntity entity) {
        super(entity);
    }
}
