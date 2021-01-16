package org.loomdev.api.event;

public interface Event {

    /**
     * Gets whether the event can be canceled.
     *
     * @return Whether the event is cancelable.
     */
    default boolean isCancelable() {
        return this instanceof Cancelable;
    }
}
