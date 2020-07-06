package org.loomdev.loom.plugin;

import com.google.common.base.Preconditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.plugin.*;
import org.loomdev.loom.plugin.loader.LoomPluginContainer;
import org.loomdev.loom.plugin.loader.java.JavaPluginLoader;
import org.loomdev.loom.server.LoomServer;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PluginManagerImpl implements PluginManager {

    private static final Logger LOGGER = LogManager.getLogger(PluginManager.class);

    private final LoomServer loomServer;
    private final Path pluginDirectory;
    private final PluginLoader loader;
    private final Map<String, PluginContainer> plugins = new HashMap<>(); // TODO find a better solution for tracking plugins.
    private final Map<Object, PluginContainer> pluginsByInstance = new HashMap(); // TODO find a better solution for tracking plugins. Is this event needed?

    public PluginManagerImpl(LoomServer loomServer, Path pluginDirectory) {
        Preconditions.checkNotNull(pluginDirectory);
        Preconditions.checkArgument(pluginDirectory.toFile().isDirectory(), "provided path isn't a directory");

        this.loomServer = loomServer;
        this.pluginDirectory = pluginDirectory;
        this.loader = new JavaPluginLoader(loomServer, pluginDirectory);
    }

    public void loadPlugins() throws IOException {
        LOGGER.info("Loading plugins...");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.pluginDirectory, this::validPluginFile)) {
            for (Path path : stream) {
                try {
                    PluginMetadata metadata = loader.loadPlugin(path);
                    this.plugins.put(metadata.getId(), new LoomPluginContainer(metadata, null));
                    LOGGER.info("Loaded plugin: {} ({})", metadata.getName().orElse(metadata.getId()), metadata.getVersion().orElse("Unknown version"));
                } catch (Exception e) {
                    LOGGER.error("Unable to load plugin {}", path, e);
                }
            }
        }

        LOGGER.info("Loaded {} plugins.", this.plugins.size());
    }

    public void enableAll() {
        this.plugins.keySet().forEach(this::enablePlugin);
    }

    @Override
    public Optional<PluginContainer> getPlugin(String id) {
        if (!plugins.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(this.plugins.get(id));
    }

    @Override
    public Optional<PluginContainer> fromInstance(Plugin o) {
        return Optional.empty();
    }

    @Override
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

            LOGGER.info("Enabled plugin {} ({})", container.getMetadata().getName().orElse(id), container.getMetadata().getVersion().orElse("Unknown version"));
            return Result.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("Can't enable plugin {}", container.getMetadata().getId(), e);
            return Result.FAILED;
        }
    }

    @Override
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
        pluginsByInstance.remove(plugin);
        container.setInstance(null);

        LOGGER.info("Disabled plugin {} ({})", container.getMetadata().getName().orElse(id), container.getMetadata().getVersion().orElse("Unknown version"));
        return Result.SUCCESS;
    }

    private boolean validPluginFile(Path path) {
        return path.toFile().isFile() && path.toString().endsWith(".jar");
    }
}
