package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;

public interface CommandExecutor {

    void execute(@NotNull CommandContext context);
}
