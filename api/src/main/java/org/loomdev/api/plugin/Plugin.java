package org.loomdev.api.plugin;

/**
 * Basic enable/disable functionality for plugins.
 */
public interface Plugin {
    /**
     * Called when the plugin is enabled.
     */
    void onPluginEnable();

    /**
     * Called when the plugin is disabled.
     */
    void onPluginDisable();
}
