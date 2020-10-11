package org.loomdev.api.event.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.Event;

/**
 * Represents an event that is related to an entity in the world.
 */
public class EntityEvent implements Event {

    private final Entity entity;

    public EntityEvent(@NotNull Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return this.entity;
    }
}
