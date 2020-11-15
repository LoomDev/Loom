package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;

public interface CommandContext {

    @NotNull
    CommandSource getSource();

    @NotNull
    String getAlias();

    @NotNull
    String[] getArguments();
}
