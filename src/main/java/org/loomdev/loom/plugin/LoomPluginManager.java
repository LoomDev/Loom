package org.loomdev.loom.plugin;

import com.google.common.base.Preconditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.plugin.PluginContainer;
import org.loomdev.api.plugin.PluginLoader;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.loom.plugin.loader.java.JavaPluginLoader;
import org.loomdev.loom.server.LoomServer;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class LoomPluginManager implements PluginManager {

    private static final Logger LOGGER = LogManager.getLogger(PluginManager.class);

    private final LoomServer loomServer;
    private final Map<String, PluginContainer> plugins = new HashMap<>();
    private final Map<Object, PluginContainer> pluginInstances = new IdentityHashMap<>();
    private final Map<String, PluginContainer> enabledPlugins = new HashMap<>();
    private final Map<String, PluginContainer> disabledPlugins = new HashMap<>();

    public LoomPluginManager(LoomServer loomServer) {
        this.loomServer = loomServer;
    }

    public void loadPlugins(Path directory) throws IOException {
        Preconditions.checkNotNull(directory);
        Preconditions.checkArgument(directory.toFile().isDirectory(), "provided path isn't a directory");

        LOGGER.info("Loading plugins!");

        List<PluginMetadata> found = new ArrayList<>();
        PluginLoader loader = new JavaPluginLoader(this.loomServer, directory);

        try (DirectoryStream<Path> stream = Files
                .newDirectoryStream(directory, p -> p.toFile().isFile() && p.toString().endsWith(".jar"))) {
            for (Path path : stream) {
                try {
                    found.add(loader.loadPlugin(path));
                } catch (Exception e) {
                    LOGGER.error("Unable to load plugin {}", path, e);
                }
            }
        }

        if (found.isEmpty()) {
            return;
        }

        for (PluginMetadata plugin : found) {
            PluginContainer pluginObject;
            try {
                pluginObject = loader.createPlugin(plugin);
            } catch (Exception e) {
                LOGGER.error("Can't create plugin {}", plugin.getId(), e);
                continue;
            }

            registerPlugin(pluginObject);
        }
    }

    private void registerPlugin(PluginContainer pluginObject) {
        this.plugins.put(pluginObject.getMetadata().getId(), pluginObject);
        pluginObject.getInstance().ifPresent(instance -> this.pluginInstances.put(instance, pluginObject));
        LOGGER.info("Loaded plugin: {} ({})", pluginObject.getMetadata().getName(), pluginObject.getMetadata().getVersion());
    }

    @Override
    public Optional<PluginContainer> getPlugin(String id) {
        return Optional.empty();
    }

    @Override
    public Collection<PluginContainer> getPlugins() {
        return null;
    }

    @Override
    public boolean isLoaded(String id) {
        return false;
    }

    @Override
    public boolean enablePlugin(String id) {
        return false;
    }

    @Override
    public boolean disablePlugin(String id) {
        return false;
    }
}
