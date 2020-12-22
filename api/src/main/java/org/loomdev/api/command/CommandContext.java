package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a command context containing the source, alias and arguments.
 */
public interface CommandContext {

    @NotNull
    CommandSource getSource();

    @NotNull
    String getAlias();

    @NotNull
    String[] getArguments();
}
