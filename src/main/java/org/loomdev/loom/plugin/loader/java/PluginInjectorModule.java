package org.loomdev.loom.plugin.loader.java;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.api.plugin.annotation.PluginDirectory;
import org.loomdev.api.server.Server;
import org.loomdev.loom.plugin.loader.LoomPluginMetadata;

import java.nio.file.Path;

public class PluginInjectorModule implements Module {

    private final Server server;
    private final JavaLoomPluginMetadata metadata;
    private final Path basePluginPath;

    public PluginInjectorModule(Server server, JavaLoomPluginMetadata metadata, Path basePluginPath) {
        this.server = server;
        this.metadata = metadata;
        this.basePluginPath = basePluginPath;
    }

    @Override
    public void configure(Binder binder) {
        // binder.bind(metadata.getMainClass()).in(Scopes.SINGLETON);

        binder.bind(Logger.class).toInstance(LogManager.getLogger(this.metadata.getId()));
        binder.bind(Server.class).toInstance(this.server);
        binder.bind(Path.class).annotatedWith(PluginDirectory.class).toInstance(basePluginPath.resolve(metadata.getId()));
        binder.bind(PluginMetadata.class).toInstance(this.metadata);
        binder.bind(PluginManager.class).toInstance(this.server.getPluginManager());
        // TODO EventManager, CommandManager, ...
    }
}
