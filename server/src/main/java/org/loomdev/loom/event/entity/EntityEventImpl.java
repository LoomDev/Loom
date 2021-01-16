package org.loomdev.loom.event.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.entity.EntityEvent;
import org.loomdev.loom.event.EventImpl;

public class EntityEventImpl extends EventImpl implements EntityEvent {

    private final Entity entity;

    public EntityEventImpl(Entity entity) {
        this.entity = entity;
    }

    @Override
    @NotNull
    public Entity getEntity() {
        return entity;
    }
}
