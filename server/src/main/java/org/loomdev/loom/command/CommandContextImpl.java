package org.loomdev.loom.command;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.command.CommandSource;

import net.minecraft.commands.CommandSourceStack;

public class CommandContextImpl implements CommandContext {

    private final CommandSource source;
    private final com.mojang.brigadier.context.CommandContext<CommandSourceStack> context;

    public CommandContextImpl(CommandSource source, com.mojang.brigadier.context.CommandContext<CommandSourceStack> context) {
        this.source = source;
        this.context = context;
    }

    @Override
    public <V> V getValue(String arg, Class<V> type) {
        return context.getArgument(arg, type);
    }

    @Override
    @NotNull
    public CommandSource getSource() {
        return source;
    }

}
