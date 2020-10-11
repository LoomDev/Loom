package org.loomdev.api.event.entity.creeper;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.misc.Lightning;
import org.loomdev.api.entity.mob.Creeper;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.entity.EntityEvent;

import java.util.Optional;

/**
 * Fired when a creeper is struck by lightning and is about to become charged.
 * The creeper will not become charged will if this event is cancelled.
 */
public class CreeperChargedEvent extends EntityEvent implements Cancellable {

    private Lightning lightning;
    private final Cause cause;
    private boolean cancelled;

    public CreeperChargedEvent(@NotNull Creeper creeper) {
        super(creeper);
        this.cause = Cause.TRIGGERED;
    }

    public CreeperChargedEvent(@NotNull Creeper creeper, @NotNull Lightning lightning) {
        super(creeper);
        this.lightning = lightning;
        this.cause = Cause.LIGHTNING;
    }

    public Creeper getCreeper() {
        return (Creeper) this.getEntity();
    }

    public Optional<Lightning> getLightning() {
        return Optional.ofNullable(this.lightning);
    }

    public Cause getCause() {
        return this.cause;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public enum Cause {
        LIGHTNING,
        TRIGGERED
    }
}
