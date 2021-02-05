package org.loomdev.api.plugin.exception;

public class IllegalPluginStateException extends PluginException {

    public IllegalPluginStateException(String pluginId) {
        super(pluginId);
    }

    public IllegalPluginStateException(String pluginId, String message) {
        super(pluginId, message);
    }

    public IllegalPluginStateException(String pluginId, String message, Throwable cause) {
        super(pluginId, message, cause);
    }

    public IllegalPluginStateException(String pluginId, Throwable cause) {
        super(pluginId, cause);
    }
}
