package org.loomdev.api.command.tree.argument;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.builder.BuilderBase;
import org.loomdev.api.util.registry.Registry;

import net.kyori.adventure.text.Component;

public interface Suggestions {

    static CompletableFuture<Suggestions> empty() {
        return builder("", 0).buildFuture();
    }
    static Builder builder() {
        return Registry.get().createBuilder(Suggestions.class);
    }

    static Builder builder(String input) {
        return builder().input(input);
    }

    static Builder builder(String input, int start) {
        return builder(input).start(start);
    }

    Collection<Suggestion> getSuggestions();

    public interface Builder extends BuilderBase<Suggestions, Builder> {

        Builder input(String input);

        Builder start(int start);

        /**
         * Suggest an option.
         * @param option The option.
         * @return The builder.
         */
        default Builder suggest(@NotNull String option) {
            return suggest(option, (Component) null);
        }

        /**
         * Suggest an option.
         * @param option The option.
         * @param tooltip The tooltip.
         * @return The builder.
         */
        Builder suggest(@NotNull String option, @Nullable Component tooltip);

        /**
         * Suggest an option.
         * @param option The option.
         * @param tooltip The tooltip (as a string).
         * @return The builder.
         */
        default Builder suggest(@NotNull String text, @Nullable String tooltip) {
            if(tooltip == null) {
               return suggest(text, (Component) null);
            }
            return suggest(text, Component.text(tooltip));
        }

        String getRemaining();

        String getRemainingLowerCase();

        CompletableFuture<Suggestions> buildFuture();

    }

    
}
