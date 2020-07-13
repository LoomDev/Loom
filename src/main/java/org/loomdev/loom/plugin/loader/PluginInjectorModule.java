package org.loomdev.loom.plugin.loader;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.api.plugin.annotation.PluginDirectory;
import org.loomdev.api.server.Server;
import org.loomdev.loom.plugin.data.LoomPluginMetadata;

import java.nio.file.Path;

public class PluginInjectorModule implements Module {

    private final Server server;
    private final LoomPluginMetadata metadata;
    private final Path basePluginPath;

    public PluginInjectorModule(Server server, LoomPluginMetadata metadata, Path basePluginPath) {
        this.server = server;
        this.metadata = metadata;
        this.basePluginPath = basePluginPath;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(Logger.class).toInstance(LogManager.getLogger(this.metadata.getName().orElse(this.metadata.getId())));
        binder.bind(Server.class).toInstance(this.server);
        binder.bind(Path.class).annotatedWith(PluginDirectory.class).toInstance(basePluginPath.resolve(metadata.getId()));
        binder.bind(PluginMetadata.class).toInstance(this.metadata);
        binder.bind(PluginManager.class).toInstance(this.server.getPluginManager());
        binder.bind(EventManager.class).toInstance(this.server.getEventManager());
        binder.bind(CommandManager.class).toInstance(this.server.getCommandManager());
    }
}
