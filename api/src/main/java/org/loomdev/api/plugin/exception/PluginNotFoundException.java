package org.loomdev.api.plugin.exception;

public class PluginNotFoundException extends PluginException {

    public PluginNotFoundException(String pluginId) {
        super(pluginId);
    }

    public PluginNotFoundException(String pluginId, String message) {
        super(pluginId, message);
    }

    public PluginNotFoundException(String pluginId, String message, Throwable cause) {
        super(pluginId, message, cause);
    }

    public PluginNotFoundException(String pluginId, Throwable cause) {
        super(pluginId, cause);
    }
}
