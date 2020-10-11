package org.loomdev.api.event.entity.creeper;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.Creeper;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.entity.EntityEvent;

/**
 * Fired when a creeper's internal fuse counter is started.
 * The fuse will be reset if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>A player right-clicks a creeper with a flint and steel</li>
 * <li>A player is close to a creeper and the fuse timer begins</li>
 * </ul>
 */
public class CreeperIgnitedEvent extends EntityEvent implements Cancellable {

    private boolean cancelled;

    public CreeperIgnitedEvent(@NotNull Creeper creeper) {
        super(creeper);
    }

    public Creeper getCreeper() {
        return (Creeper) this.getEntity();
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
