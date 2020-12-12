package org.loomdev.api.event;

/**
 * Represents an event that is fired in the event bus and can be
 * handled by event listeners. This event can be either sync or async.
 */
public abstract class Event {

    private boolean canceled;

    public boolean isCancelable() {
        return false;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        if (!isCancelable()) {
            throw new IllegalArgumentException("Tried to cancel non-cancelable event.");
        }

        this.canceled = canceled;
    }
}
