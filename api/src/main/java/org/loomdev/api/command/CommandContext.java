package org.loomdev.api.command;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a command context containing the {@link CommandSource}.
 */
public interface CommandContext {

    @NotNull CommandSource getSource();

    @Nullable <V> V getValue(String arg);

    default boolean hasValue(String arg) {
        return getValue(arg) != null;
    }

}
