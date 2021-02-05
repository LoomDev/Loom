package org.loomdev.api.plugin;

import org.loomdev.api.plugin.metadata.PluginMetadata;

import java.util.stream.Stream;

public interface PluginManager {
    Stream<PluginMetadata> getPlugins();
}
