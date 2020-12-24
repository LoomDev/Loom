package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;

public interface CommandExecutor {

    /**
     * Executes the command.
     *
     * @param context Information about the context in which the command was executed.
     */
    void execute(@NotNull CommandContext context);
}
