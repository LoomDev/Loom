package org.loomdev.loom.entity.passive;

import org.loomdev.api.entity.passive.AnimalEntity;

public class AnimalEntityImpl extends PassiveEntityImpl implements AnimalEntity {
    public AnimalEntityImpl(net.minecraft.entity.passive.AnimalEntity entity) {
        super(entity);
    }
}
