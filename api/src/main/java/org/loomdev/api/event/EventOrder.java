package org.loomdev.api.event;

/**
 * Represents an event order.
 * Event handlers with later orders get more control over the outcome of the event (unless {@code ignoreCanceled} is used).
 */
public enum EventOrder {
    EARLIEST, EARLY, NORMAL, LATE, LATEST
}
