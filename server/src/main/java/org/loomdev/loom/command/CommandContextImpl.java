package org.loomdev.loom.command;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.command.CommandSource;

public class CommandContextImpl implements CommandContext {

    private final CommandSource source;
    private final String alias;
    private final String[] arguments;

    public CommandContextImpl(CommandSource source, String alias, String[] arguments) {
        this.source = source;
        this.alias = alias;
        this.arguments = arguments;
    }

    @NotNull
    @Override
    public CommandSource getSource() {
        return source;
    }

    @Override
    @NotNull
    public String getAlias() {
        return alias;
    }

    @Override
    @NotNull
    public String[] getArguments() {
        return arguments;
    }
}
