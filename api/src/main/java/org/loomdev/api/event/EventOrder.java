package org.loomdev.api.event;

/**
 * Represents an event order.
 * Event handlers with lower orders will be executed first and are likely of low importance.
 */
public enum EventOrder {
    EARLIEST, EARLY, NORMAL, LATE, LATEST
}
