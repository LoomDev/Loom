package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a command context containing the {@link CommandSource},
 * alias (the alias that was used to execute the command) and arguments.
 */
public interface CommandContext {

    @NotNull
    CommandSource getSource();

    /**
     * Gets the alias that was used to execute the command.
     *
     * @return The alias.
     */
    @NotNull
    String getAlias();

    @NotNull
    String[] getArguments();
}
