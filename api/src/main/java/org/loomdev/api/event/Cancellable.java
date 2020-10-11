package org.loomdev.api.event;

/**
 * An interface used to represent events that can be cancelled. Cancelled methods
 * are still sent to all listeners, but actions are not executed in the server for
 * the cancellable event.
 */
public interface Cancellable {

    /**
     * Returns the current state of cancellation of this event.
     * If cancelled, server actions will not be executed for this event.
     *
     * @return Whether this event is cancelled.
     */
    boolean isCancelled();

    /**
     * Sets the cancellation state of this event.
     * If cancelled, server actions will not be executed for this event.
     *
     * @param cancel Whether this event should be marked as cancelled.
     */
    void setCancelled(boolean cancel);
}
