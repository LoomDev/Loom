package org.loomdev.api.event;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface EventHandler<T extends Event> {

    void execute(@NotNull T event);
}
