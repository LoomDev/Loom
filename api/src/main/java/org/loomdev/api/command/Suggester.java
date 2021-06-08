package org.loomdev.api.command;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;

public interface Suggester {

    /**
     * Gets a list of suggestions.
     * @param ctx The context.
     * @return The list.
     */
    @NotNull public Collection<Suggestion> suggest(CommandContext ctx);
    
}
