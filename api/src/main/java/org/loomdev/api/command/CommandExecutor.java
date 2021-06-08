package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;

public interface CommandExecutor {

    /**
     * Executes the command.
     *
     * @param ctx Information about the context in which the command was executed.
     * @return An integer representing the result of the command.
     */
    int execute(@NotNull CommandContext ctx);

    /**
     * A version of {@link CommandExecutor} that doesn't need to return.
     */
    public interface VoidCommandExecutor extends CommandExecutor {

        default int execute(@NotNull CommandContext ctx) {
            executeVoid(ctx);
            return 0;
        }

        /**
         * Executes the command.
         *
         * @param ctx Information about the context in which the command was executed.
         */
        void executeVoid(@NotNull CommandContext ctx);
        
    }

    /**
     * A version of {@link CommandExecutor} that returns a boolean.
     */
    public interface BooleanCommandExecutor extends CommandExecutor {

        default int execute(@NotNull CommandContext ctx) {
            return executeBoolean(ctx) ? 1 : 0;
        }

        /**
         * Executes the command.
         *
         * @param ctx Information about the context in which the command was executed.
         * @return Whether the command was successful or not.
         */
        boolean executeBoolean(@NotNull CommandContext ctx);
        
    }
    
}
