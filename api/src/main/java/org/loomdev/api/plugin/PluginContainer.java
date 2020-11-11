package org.loomdev.api.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * Wrapper around a loaded plugin
 */
public interface PluginContainer {

    /**
     * Returns the plugins metadata
     *
     * @return The metadata of the plugin.
     */
    @NotNull PluginMetadata getMetadata();

    /**
     * Returns the instance of the plugin class if available.
     *
     * @return The instance of the plugin.
     */
    @NotNull Object getInstance();

    /**
     * Returns this plugin's classloader.
     *
     * @return The classloader instance for this plugin.
     */
    @NotNull ClassLoader getClassLoader();
}
