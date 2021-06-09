package org.loomdev.api.command;

import org.loomdev.api.util.registry.Registry;

public abstract class CommandSyntaxExeception extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static CommandSyntaxExeception create(String text, String input, int cursor) {
        return Registry.get().createCommandSyntaxException(text, input, cursor);
    }

}
