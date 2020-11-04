package org.loomdev.api.event.entity.movement;

import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.entity.EntityEvent;

/**
 * Fired when an entity toggles swimming mode in water.
 * The entity will not change their swimming mode if this event is cancelled.
 */
public class EntitySwimEvent extends EntityEvent implements Cancellable { 

    private boolean cancelled;

    public EntitySwimEvent(Entity entity) {
        super(entity);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
