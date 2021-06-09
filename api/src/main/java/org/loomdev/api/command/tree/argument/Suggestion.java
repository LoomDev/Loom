package org.loomdev.api.command.tree.argument;

import java.util.Objects;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

public class Suggestion {

    private String text;
    private Component tooltip;

    public Suggestion(@NotNull String text) {
        this(text, (Component) null);
    }

    public Suggestion(@NotNull String text, @Nullable String tooltip) {
        this(text, Component.text(tooltip));
    }

    public Suggestion(@NotNull String text, @Nullable Component tooltip) {
        this.text = Objects.requireNonNull(text, "Text cannot be null");
        this.tooltip = tooltip;
    }

    @NotNull
    public String getText() {
        return text;
    }

    @Nullable
    public Component getTooltip() {
        return tooltip;
    }
    
}
