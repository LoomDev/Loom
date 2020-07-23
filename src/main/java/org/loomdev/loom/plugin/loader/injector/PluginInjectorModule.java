package org.loomdev.loom.plugin.loader.injector;

import com.google.inject.*;
import com.google.inject.Module;
import com.google.inject.internal.asm.$ByteVector;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.config.Configuration;
import org.loomdev.api.config.file.TomlConfiguration;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.api.plugin.annotation.Config;
import org.loomdev.api.plugin.annotation.PluginDirectory;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.server.Server;
import org.loomdev.loom.plugin.data.LoomPluginMetadata;
import org.loomdev.loom.plugin.loader.injector.providers.ConfigProvider;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;

public class PluginInjectorModule implements Module {

    private final Server server;
    private final LoomPluginMetadata metadata;
    private final Path basePluginPath;

    public PluginInjectorModule(Server server,LoomPluginMetadata metadata, Path basePluginPath) {
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
        binder.bind(Scheduler.class).toInstance(this.server.getScheduler());
        binder.bind(Configuration.class).annotatedWith(Config.class).toProvider(ConfigProvider.class);
    }
}
