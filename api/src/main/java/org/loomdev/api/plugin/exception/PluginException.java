package org.loomdev.api.plugin.exception;

public class PluginException extends RuntimeException {

    private final String pluginId;

    public PluginException(String pluginId) {
        this.pluginId = pluginId;
    }

    public PluginException(String pluginId, String message) {
        super(message);
        this.pluginId = pluginId;
    }

    public PluginException(String pluginId, String message, Throwable cause) {
        super(message, cause);
        this.pluginId = pluginId;
    }

    public PluginException(String pluginId, Throwable cause) {
        super(cause);
        this.pluginId = pluginId;
    }

    public String getPluginId() {
        return pluginId;
    }
}
