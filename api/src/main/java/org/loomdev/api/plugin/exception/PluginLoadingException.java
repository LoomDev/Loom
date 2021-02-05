package org.loomdev.api.plugin.exception;

public class PluginLoadingException extends PluginException {

    public PluginLoadingException(String pluginId) {
        super(pluginId);
    }

    public PluginLoadingException(String pluginId, String message) {
        super(pluginId, message);
    }

    public PluginLoadingException(String pluginId, String message, Throwable cause) {
        super(pluginId, message, cause);
    }

    public PluginLoadingException(String pluginId, Throwable cause) {
        super(pluginId, cause);
    }
}
