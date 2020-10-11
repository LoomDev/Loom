package org.loomdev.api.event.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.world.World;

/**
 * Fired when the time in a world is changed. TODO more javadocs shit
 */
public class TimeChangedEvent extends WorldEvent implements Cancellable {

    private long change;
    private final Cause cause;
    private boolean cancelled;

    public TimeChangedEvent(@NotNull World world, long change, @NotNull Cause cause) {
        super(world);
        this.change = change;
        this.cause = cause;
    }

    public long getChange() {
        return change;
    }

    public void setChange(long change) {
        this.change = change;
    }

    public @NotNull Cause getCause() {
        return cause;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Represents the reason the time in
     * a world was changed.
     */
    public enum Cause {
        COMMAND,
        SLEEPING,
        TRIGGERED
    }
}
