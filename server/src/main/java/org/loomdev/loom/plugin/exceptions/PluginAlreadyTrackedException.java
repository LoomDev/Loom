package org.loomdev.loom.plugin.exceptions;

import org.loomdev.api.plugin.exception.PluginException;

public class PluginAlreadyTrackedException extends PluginException {
    public PluginAlreadyTrackedException(String pluginId) {
        super(pluginId);
    }
}
