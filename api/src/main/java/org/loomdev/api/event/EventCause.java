package org.loomdev.api.event;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Represents an in-game object that triggered an event. This
 * can represent a block, entity, or other type of world interaction.
 */
public class EventCause {

    private final Object cause;

    public EventCause(Object cause) {
        this.cause = cause;
    }

    @NotNull
    public Object raw() {
        return cause;
    }

    @NotNull
    public <T> Optional<T> ofType(@NotNull Class<T> type) {
        if (!type.isAssignableFrom(cause.getClass())) {
            return Optional.empty();
        }

        return Optional.of(type.cast(cause));
    }
}
