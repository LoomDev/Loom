package org.loomdev.loom.plugin.loader.injector;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.config.Configuration;
import org.loomdev.api.plugin.Config;
import org.loomdev.api.plugin.DataDirectory;
import org.loomdev.api.plugin.metadata.PluginMetadata;
import org.loomdev.api.server.Server;
import org.loomdev.loom.plugin.loader.injector.providers.ConfigurationProvider;
import org.loomdev.loom.plugin.loader.injector.providers.PluginLoggerProvider;

import java.nio.file.Path;

public class PluginInjectorModule extends AbstractModule {

    private final PluginMetadata metadata;
    private final Server scopedServer;
    private final Path dataDirectory;
    private final Class<?> mainClass;

    public PluginInjectorModule(@NotNull PluginMetadata metadata, @NotNull Server scopedServer, Path dataDirectory, Class<?> mainClass) {
        this.metadata = metadata;
        this.scopedServer = scopedServer;
        this.dataDirectory = dataDirectory;
        this.mainClass = mainClass;
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<Class<?>>() {}).annotatedWith(MainPluginClass.class).toInstance(mainClass);

        bind(PluginMetadata.class).toInstance(metadata);
        bind(Server.class).toInstance(scopedServer);
        bind(Logger.class).toProvider(new PluginLoggerProvider(metadata));
        bind(Path.class).annotatedWith(DataDirectory.class).toInstance(dataDirectory);
        bind(Configuration.class).annotatedWith(Config.class).toProvider(ConfigurationProvider.class);
    }
}
