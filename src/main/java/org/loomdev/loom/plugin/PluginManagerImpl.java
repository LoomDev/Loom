package org.loomdev.loom.plugin;

import com.google.common.base.Preconditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.plugin.*;
import org.loomdev.api.scheduler.Task;
import org.loomdev.loom.Loom;
import org.loomdev.loom.plugin.data.LoomPluginContainer;
import org.loomdev.loom.plugin.loader.PluginClassLoader;
import org.loomdev.loom.plugin.loader.PluginLoaderImpl;
import org.loomdev.loom.server.ServerImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PluginManagerImpl implements PluginManager {

    private static final Logger LOGGER = LogManager.getLogger("Plugin Manager");

    private final ServerImpl server;
    private final Path pluginDirectory;
    private final PluginLoader pluginLoader;

    private final Map<String, PluginContainer> plugins = new HashMap<>();
    private final Map<Plugin, PluginContainer> pluginsByInstance = new IdentityHashMap<>();

    public PluginManagerImpl(@NonNull ServerImpl server, @NonNull File pluginDirectory) {
        this.server = server;
        this.pluginDirectory = pluginDirectory.toPath();
        this.pluginLoader = new PluginLoaderImpl(server, this.pluginDirectory);

        if (!pluginDirectory.exists()) {
            Preconditions.checkArgument(pluginDirectory.mkdirs(), "Failed to create plugins directory.");
        }
    }

    @Override
    public @NonNull Collection<PluginContainer> getPlugins() {
        return this.plugins.values();
    }

    @Override
    public @NonNull Optional<PluginContainer> getPlugin(@NonNull String id) {
        return Optional.ofNullable(this.plugins.get(id));
    }

    @Override
    public @NonNull Optional<PluginContainer> fromInstance(@NonNull Plugin plugin) {
        return Optional.ofNullable(this.pluginsByInstance.get(plugin));
    }

    @Override
    public boolean isLoaded(@NonNull String id) {
        return this.plugins.containsKey(id);
    }

    @Override
    public boolean isEnabled(@NonNull String id) {
        return this.plugins.containsKey(id) && this.plugins.get(id).getInstance().isPresent();
    }

    @Override
    public boolean isDisabled(@NonNull String id) {
        return this.plugins.containsKey(id) && !this.plugins.get(id).getInstance().isPresent();
    }

    @Override
    public @NonNull Result loadPlugin(String id) {
        return null; // TODO
    }

    @Override
    public @NonNull Result loadPlugin(Path path) {
        try {
            PluginContainer plugin = pluginLoader.loadPlugin(path);
            PluginMetadata metadata = plugin.getMetadata();

            if (plugins.containsKey(metadata.getId())) {
                return Result.ALREADY_IN_STATE;
            }

            this.plugins.put(metadata.getId(), plugin);
            LOGGER.info("Loaded {} ({}).", metadata.getName().orElse(metadata.getId()), metadata.getVersion().orElse("Unknown version"));
            return Result.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("Failed to load {}.", path, e);
            return Result.FAILED;
        }
    }

    @Override
    public @NonNull Result unloadPlugin(@NonNull String id) {
        PluginContainer plugin = this.plugins.get(id);

        if (plugin == null) {
            return Result.ALREADY_IN_STATE;
        }

        try {
            PluginMetadata metadata = plugin.getMetadata();
            this.disablePlugin(metadata.getId());
            this.plugins.remove(metadata.getId());

            ((PluginClassLoader) plugin.getClassLoader()).close();
            System.gc(); // TODO see if this is possible to remove

            LOGGER.info("Unloaded {} ({}).", metadata.getName().orElse(metadata.getId()), metadata.getVersion().orElse("Unknown version"));
            return Result.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("Failed to unload {}.", plugin.getMetadata().getId(), e);
            return Result.FAILED;
        }
    }

    @Override
    public @NonNull Result enablePlugin(String id) {
        LoomPluginContainer container = (LoomPluginContainer) this.plugins.get(id);

        if (container == null) {
            return Result.NOT_FOUND;
        }

        if (container.isEnabled()) {
            return Result.ALREADY_IN_STATE;
        }

        try {
            PluginMetadata metadata = container.getMetadata();
            Plugin instance = pluginLoader.createPlugin(metadata);

            if (instance == null) {
                return Result.FAILED;
            }

            container.setInstance(instance);
            pluginsByInstance.put(instance, container);

            this.server.getEventManager().register(instance, instance);
            instance.onPluginEnable(); // TODO fire async

            LOGGER.info("Enabled {} ({}).", metadata.getName().orElse(id), metadata.getVersion().orElse("Unknown version"));
            return Result.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("Failed to enable {}.", container.getMetadata().getId(), e);
            return Result.FAILED;
        }
    }

    @Override
    public @NonNull Result disablePlugin(String id) {
        LoomPluginContainer container = (LoomPluginContainer) this.plugins.get(id);
        if (container == null) {
            return Result.NOT_FOUND;
        }

        if (container.isDisabled()) {
            return Result.ALREADY_IN_STATE;
        }

        PluginMetadata metadata = container.getMetadata();
        Plugin plugin = container.getInstance().get();

        plugin.onPluginDisable(); // TODO fire async
        this.server.getEventManager().unregister(plugin);
        this.server.getCommandManager().unregister(plugin);
        this.server.getScheduler().unregisterSchedulers(plugin);
        pluginsByInstance.remove(plugin);
        container.setInstance(null);
        System.gc();

        LOGGER.info("Disabled {} ({}).", metadata.getName().orElse(id), metadata.getVersion().orElse("Unknown version"));
        return Result.SUCCESS;
    }

    public void loadPlugins() throws IOException {
        LOGGER.info("Loading plugins...");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.pluginDirectory, this::isPlugin)) {
            stream.forEach(this::loadPlugin);
        }

        LOGGER.info("Loaded {} plugins.", this.plugins.size());
    }

    public void enableAll() {
        this.plugins.keySet().forEach(this::enablePlugin);
    }

    private boolean isPlugin(@NonNull Path path) {
        return path.toFile().isFile() && path.toString().endsWith(".jar");
    }

}
