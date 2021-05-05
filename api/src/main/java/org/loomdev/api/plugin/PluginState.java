package org.loomdev.api.plugin;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public enum PluginState {

    LOADING(NamedTextColor.GRAY),
    LOADED(NamedTextColor.GRAY),
    ENABLING(NamedTextColor.GRAY),
    ENABLED(NamedTextColor.GREEN),
    DISABLING(NamedTextColor.RED),
    DISABLED(NamedTextColor.RED),
    INVALID(NamedTextColor.DARK_RED);

    private final TextColor color;

    PluginState(TextColor color) {
        this.color = color;
    }

    public TextColor getColor() {
        return color;
    }

    public boolean canChangeTo(PluginState state) {
        switch (this) {
            case LOADING:
                return state == PluginState.LOADED
                        || state == PluginState.INVALID;

            case LOADED:
                return state == PluginState.ENABLING
                        || state == PluginState.INVALID;

            case ENABLING:
                return state == PluginState.ENABLED
                        || state == PluginState.INVALID;

            case ENABLED:
                return state == PluginState.DISABLING
                        || state == PluginState.INVALID;

            case DISABLING:
                return state == PluginState.DISABLED
                        || state == PluginState.INVALID;

            case DISABLED:
            case INVALID:
                return state == PluginState.INVALID
                        || state == PluginState.LOADING;

            default:
                return false;
        }
    }
}
