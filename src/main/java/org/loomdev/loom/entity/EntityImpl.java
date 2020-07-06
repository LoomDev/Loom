package org.loomdev.loom.entity;

import org.loomdev.api.entity.Entity;

import java.util.UUID;

public class EntityImpl implements Entity {

    private final net.minecraft.entity.Entity mcEntity;

    public EntityImpl(net.minecraft.entity.Entity entity) {
        mcEntity = entity;
    }

    @Override
    public int getEntityId() {
        return mcEntity.getEntityId();
    }

    @Override
    public UUID getUniqueId() {
        return mcEntity.getUuid();
    }
}
