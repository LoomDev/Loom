package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;

public interface CommandExecutor {

    void execute(@NotNull CommandSource source, String[] args);

}
