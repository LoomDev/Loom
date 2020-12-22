package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a command executor.
 */
public interface CommandExecutor {

    void execute(@NotNull CommandContext context);
}
