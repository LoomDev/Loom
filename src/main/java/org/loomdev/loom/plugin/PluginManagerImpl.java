package org.loomdev.loom.plugin;

import com.google.common.base.Preconditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.plugin.*;
import org.loomdev.loom.command.CommandManagerImpl;
import org.loomdev.loom.plugin.loader.LoomPluginContainer;
import org.loomdev.loom.plugin.loader.java.JavaPluginLoader;
import org.loomdev.loom.server.LoomServer;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PluginManagerImpl implements PluginManager {

    private static final Logger LOGGER = LogManager.getLogger("Plugin Manager");

    private final LoomServer loomServer;
    private final Path pluginDirectory;
    private final PluginLoader loader;

    private final Map<String, PluginContainer> plugins = new HashMap<>();
    private final Map<Object, PluginContainer> pluginsByInstance = new IdentityHashMap<>();

    public PluginManagerImpl(@NonNull LoomServer loomServer, @NonNull Path pluginDirectory) {
        Preconditions.checkArgument(pluginDirectory.toFile().isDirectory(), "provided path isn't a directory");

        this.loomServer = loomServer;
        this.pluginDirectory = pluginDirectory;
        this.loader = new JavaPluginLoader(loomServer, pluginDirectory);
    }

    public Result loadPlugin(Path path) {
        try {
            PluginContainer plugin = loader.loadPlugin(path);
            PluginMetadata metadata = plugin.getMetadata();

            if (plugins.containsKey(metadata.getId())) {
                return Result.ALREADY_IN_STATE;
            }

            this.plugins.put(metadata.getId(), plugin);
            LOGGER.info("Loaded plugin: {} ({})", metadata.getName().orElse(metadata.getId()), metadata.getVersion().orElse("Unknown version"));
            return Result.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("Failed to load plugin: {}", path, e);
            return Result.FAILED;
        }
    }


    public void loadPlugins() throws IOException {
        LOGGER.info("Loading plugins...");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.pluginDirectory, this::validPluginFile)) {
            stream.forEach(this::loadPlugin);
        }

        LOGGER.info("Loaded {} plugins.", this.plugins.size());
    }

    public void enableAll() {
        this.plugins.keySet().forEach(this::enablePlugin);

        // Update command map
        ((CommandManagerImpl) this.loomServer.getCommandManager()).updateCommandMap();
    }

    @Override
    @NonNull
    public Optional<PluginContainer> getPlugin(String id) {
        return Optional.ofNullable(this.plugins.get(id));
    }

    @Override
    @NonNull
    public Optional<PluginContainer> fromInstance(Plugin plugin) {
        return Optional.ofNullable(this.pluginsByInstance.get(plugin));
    }

    @Override
    @NonNull
    public Collection<PluginContainer> getPlugins() {
        return this.plugins.values();
    }

    @Override
    public boolean isLoaded(String id) {
        return this.plugins.containsKey(id);
    }

    @Override
    public boolean isEnabled(String id) {
        return this.plugins.containsKey(id) && this.plugins.get(id).getInstance().isPresent();
    }

    @Override
    public boolean isDisabled(String id) {
        return this.plugins.containsKey(id) && !this.plugins.get(id).getInstance().isPresent();
    }

    @Override
    @NonNull
    public Result enablePlugin(String id) {
        LoomPluginContainer container = (LoomPluginContainer) this.plugins.get(id);
        if (container == null) {
            return Result.NOT_FOUND;
        }

        if (container.isEnabled()) {
            return Result.ALREADY_IN_STATE;
        }

        try {
            Plugin instance = loader.createPlugin(container.getMetadata());
            if (instance == null) {
                return Result.FAILED;
            }

            container.setInstance(instance);
            pluginsByInstance.put(instance, container);
            this.loomServer.getEventManager().register(instance, instance);
            instance.onPluginEnable();

            LOGGER.info("Enabled plugin {} ({})", container.getMetadata().getName().orElse(id),
                    container.getMetadata().getVersion().orElse("Unknown version"));
            return Result.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("Can't enable plugin {}", container.getMetadata().getId(), e);
            return Result.FAILED;
        }
    }

    @Override
    @NonNull
    public Result disablePlugin(String id) {
        LoomPluginContainer container = (LoomPluginContainer) this.plugins.get(id);
        if (container == null) {
            return Result.NOT_FOUND;
        }

        if (container.isDisabled()) {
            return Result.ALREADY_IN_STATE;
        }

        Plugin plugin = container.getInstance().get();

        plugin.onPluginDisable();
        this.loomServer.getEventManager().unregister(plugin);
        this.loomServer.getCommandManager().unregister(plugin);
        pluginsByInstance.remove(plugin);
        container.setInstance(null);

        LOGGER.info("Disabled plugin {} ({})", container.getMetadata().getName().orElse(id), container.getMetadata().getVersion().orElse("Unknown version"));
        return Result.SUCCESS;
    }

    @Override
    public Result unloadPlugin(@NonNull String id) {
        PluginContainer plugin = this.plugins.get(id);

        if (plugin == null) {
            return Result.NOT_FOUND;
        }

        try {
            PluginMetadata metadata = plugin.getMetadata();
            this.disablePlugin(metadata.getId());
            this.plugins.remove(metadata.getId());
            // TODO remove from plugin instances map as well

            ((PluginClassLoader) plugin.getClassLoader()).close();
            LOGGER.info("Unloaded plugin: {} ({})", metadata.getName().orElse(metadata.getId()), metadata.getVersion().orElse("Unknown version"));
            return Result.SUCCESS;
        } catch (IOException e) {
            LOGGER.error("Failed to unload plugin: {}", plugin.getMetadata().getId(), e);
            return Result.FAILED;
        }
    }

    private boolean validPluginFile(Path path) {
        return path.toFile().isFile() && path.toString().endsWith(".jar");
    }

    public static final PluginMetadata DUMMY_METADATA = new PluginMetadata() {
        @Override
        public @NonNull String getId() {
            return "loom";
        }
    };
}
