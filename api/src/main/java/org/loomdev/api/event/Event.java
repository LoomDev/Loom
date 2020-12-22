package org.loomdev.api.event;

/**
 * Represents an event that is fired in the event bus and can be
 * handled by event listeners. This event can be either sync or async.
 */
public abstract class Event {

    private boolean canceled;

    /**
     * Gets whether the event can be canceled.
     *
     * @return Whether the event is cancelable.
     */
    public boolean isCancelable() {
        return false;
    }

    /**
     * Gets whether the event is canceled.
     *
     * @return Whether the event is canceled.
     */
    public boolean isCanceled() {
        return canceled;
    }

    /**
     * Sets whether the event is canceled.
     *
     * @param canceled Whether the event is canceled.
     * @throws IllegalArgumentException if the event is not cancelable.
     */
    public void setCanceled(boolean canceled) {
        if (!isCancelable()) {
            throw new IllegalArgumentException("Tried to cancel non-cancelable event.");
        }

        this.canceled = canceled;
    }
}
