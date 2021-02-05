package org.loomdev.loom.plugin.loader.injector.providers;

import com.google.inject.Provider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.metadata.PluginMetadata;

public class PluginLoggerProvider implements Provider<Logger> {

    private final PluginMetadata metadata;

    public PluginLoggerProvider(@NotNull PluginMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Logger get() {
        return LogManager.getLogger(this.metadata.getId());
    }
}
