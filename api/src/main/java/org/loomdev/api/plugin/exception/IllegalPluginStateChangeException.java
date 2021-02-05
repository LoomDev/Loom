package org.loomdev.api.plugin.exception;

import org.loomdev.api.plugin.PluginState;

public class IllegalPluginStateChangeException extends PluginException {

    private final PluginState from;
    private final PluginState to;

    public IllegalPluginStateChangeException(String pluginId, PluginState from, PluginState to) {
        super(pluginId);
        this.from = from;
        this.to = to;
    }

    public IllegalPluginStateChangeException(String pluginId, PluginState from, PluginState to, String message) {
        super(pluginId, message);
        this.from = from;
        this.to = to;
    }

    public IllegalPluginStateChangeException(String pluginId, PluginState from, PluginState to, String message, Throwable cause) {
        super(pluginId, message, cause);
        this.from = from;
        this.to = to;
    }

    public IllegalPluginStateChangeException(String pluginId, PluginState from, PluginState to, Throwable cause) {
        super(pluginId, cause);
        this.from = from;
        this.to = to;
    }

    public PluginState getFrom() {
        return from;
    }

    public PluginState getTo() {
        return to;
    }
}
