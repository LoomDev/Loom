package org.loomdev.api.command.tree.argument;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.command.CommandReader;

public interface CustomArgumentType<V> {

    V read(CommandReader reader);

    /**
     * Gets a list of suggestions.
     * @param ctx The context.
     * @return The list.
     */
    @NotNull
    default CompletableFuture<Suggestions> suggest(CommandContext ctx, Suggestions.Builder builder) {
        return Suggestions.empty();
    }
    
}
