package org.loomdev.loom.plugin;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginContainer;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.loom.command.CommandManagerImpl;
import org.loomdev.loom.plugin.data.LoomPluginMetadata;
import org.loomdev.loom.plugin.loader.PluginClassLoader;
import org.loomdev.loom.plugin.loader.PluginLoaderImpl;
import org.loomdev.loom.server.ServerImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PluginManagerImpl implements PluginManager {

    private static final Logger LOGGER = LogManager.getLogger("Plugin Manager");

    private final ServerImpl server;
    private final Path pluginDirectory;
    private final PluginLoaderImpl pluginLoader;

    public final Map<String, PluginMetadata> plugins = new HashMap<>();
    public final Map<String, PluginContainer> enabledPlugins = new HashMap<>();
    public final Map<Plugin, PluginContainer> enabledPluginsByInstance = new IdentityHashMap<>();

    public final Set<String> disabledPlugins = new HashSet<>();

    private final ExecutorService asyncExecutor;

    public PluginManagerImpl(ServerImpl server, File pluginDirectory) {
        this.server = server;
        this.pluginDirectory = pluginDirectory.toPath();
        this.pluginLoader = new PluginLoaderImpl(server, this.pluginDirectory);

        if (!pluginDirectory.exists()) {
            Preconditions.checkArgument(pluginDirectory.mkdirs(), "Failed to create plugins directory.");
        }

        this.asyncExecutor = new ThreadPoolExecutor(0, Runtime.getRuntime().availableProcessors(), 60L, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    }

    @Override
    public @NotNull Optional<PluginContainer> getPlugin(@NotNull String id) {
        return Optional.ofNullable(this.enabledPlugins.get(id));
    }

    @Override
    public @NotNull Optional<PluginContainer> fromInstance(@NotNull Plugin plugin) {
        return Optional.ofNullable(this.enabledPluginsByInstance.get(plugin));
    }

    @Override
    public @NotNull Collection<PluginContainer> getEnabledPlugins() {
        return this.enabledPlugins.values();
    }

    @Override
    public @NotNull Optional<PluginMetadata> getPluginMetadata(String id) {
        return Optional.ofNullable(this.plugins.get(id));
    }

    @Override
    public @NotNull Collection<PluginMetadata> getAllPlugins() {
        return this.plugins.values();
    }

    @Override
    public boolean wasFound(@NotNull String id) {
        return this.plugins.containsKey(id);
    }

    @Override
    public @NotNull Optional<Boolean> isEnabled(String id) {
        if (!this.plugins.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(enabledPlugins.containsKey(id));
    }

    @Override
    public @NotNull Optional<Boolean> isDisabled(String id) {
        if (!this.plugins.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(!enabledPlugins.containsKey(id));
    }


    @Override
    public @NotNull List<PluginMetadata> scanPluginDirectory() {
        List<PluginMetadata> metadataList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.pluginDirectory, path -> path.toFile().isFile() && path.toString().endsWith(".jar"))) {
            for(Path path : stream) {
                PluginMetadata metadata = this.loadMetaFromFile(path);
                if (metadata == null) continue; // already loaded
                metadataList.add(metadata);
            }

            this.plugins.putAll(metadataList.stream().collect(Collectors.toMap(PluginMetadata::getId, m -> m)));
            return metadataList;
        } catch (IOException e) {
            LOGGER.error("Something went wrong attempting to scan the plugin directory.", e);
            return ImmutableList.of();
        }
    }

    private @Nullable PluginMetadata loadMetaFromFile(@NotNull Path source) {
        Optional<PluginMetadata> metadataOpt = this.pluginLoader.loadMetadata(source);
        if (!metadataOpt.isPresent()) {
            return null;
        }

        PluginMetadata metadata = metadataOpt.get();

        if (plugins.containsKey(metadata.getId())) {
            return null;
        }

        LOGGER.info("Found {} ({}).", metadata.getNameOrId(), metadata.getVersion().orElse("Unknown version"));
        return metadata;
    }

    public void enableAll() { // that are not disabled
        // order plugin in correct loading sequence.
        List<PluginMetadata> resolved = new ArrayList<>(); // ordered in loading order
        for (PluginMetadata metadata : this.plugins.values()) {
            resolveDependency(metadata, resolved, new ArrayList<>());
        }

        for (PluginMetadata metadata : resolved) {
            internalEnablePlugin(metadata.getId());
        }
    }

    public boolean resolveDependency(PluginMetadata metadata, List<PluginMetadata> resolved, List<PluginMetadata> unresolved) {
        if (resolved.contains(metadata)) {
            return true;
        }

        if (this.disabledPlugins.contains(metadata.getId())) {
            return false;
        }

        unresolved.add(metadata);

        for (PluginMetadata.PluginDependency dependency : metadata.getDependencies()) {
            PluginMetadata dependencyMetadata = this.plugins.get(dependency.getId());
            if (dependencyMetadata == null) {
                ((LoomPluginMetadata) metadata).setState(PluginMetadata.State.ERROR);
                if (dependency.isOptional()) {
                    LOGGER.error("Missing optional dependency '{}' for '{}'.", dependency.getId(), metadata.getNameOrId());
                    continue;
                } else {
                    LOGGER.error("Missing required dependency '{}' for '{}'. Disabling plugin.", dependency.getId(), metadata.getNameOrId());
                    return false;
                }
            }

            if (!resolved.contains(dependencyMetadata)) {
                if (unresolved.contains(dependencyMetadata)) {
                    ((LoomPluginMetadata) metadata).setState(PluginMetadata.State.ERROR);
                    LOGGER.error("A circular reference was found while attempting to resolve dependencies for {}. Disabling plugin. {} -> {}", metadata.getNameOrId(), metadata.getNameOrId(), dependencyMetadata.getNameOrId());
                    return false;
                }
                if ((!resolveDependency(dependencyMetadata, resolved, unresolved) && !dependency.isOptional()) || this.disabledPlugins.contains(dependencyMetadata.getId())) {
                    unresolved.remove(dependencyMetadata);
                    return false;
                }
            }
        }
        resolved.add(metadata);
        unresolved.remove(metadata);
        return true;
    }

    @Override
    public boolean enablePlugin(@NotNull String id) {
        asyncExecutor.execute(() -> internalEnablePlugin(id)); // todo return completableFuture
        return false; 
    }

    private boolean internalEnablePlugin(@NotNull String id) { // todo unresolved list?
        if (!plugins.containsKey(id)) {
            // not found
            return false;
        }

        PluginMetadata metadata = this.plugins.get(id);
        // TODO check state to make sure that all required dependencies are present and the plugin can actually enable.

        LOGGER.info("Loading {} ({}).", metadata.getNameOrId(), metadata.getVersion().orElse("Unknown version"));

        Optional<PluginContainer> containerOpt = this.pluginLoader.loadPlugin(metadata);
        if (!containerOpt.isPresent()) {
            LOGGER.error("Failed to load plugin '{}'.", metadata.getNameOrId());
            return false;
        }

        PluginContainer container = containerOpt.get();
        Plugin plugin = container.getInstance();
        enabledPlugins.put(metadata.getId(), container);
        enabledPluginsByInstance.put(plugin, container);

        this.server.getEventManager().register(plugin, plugin);
        plugin.onPluginEnable();
        ((CommandManagerImpl) this.server.getCommandManager()).internalReload();

        ((LoomPluginMetadata) metadata).setState(PluginMetadata.State.ENABLED);
        LOGGER.info("Enabled {} ({}).", metadata.getNameOrId(), metadata.getVersion().orElse("Unknown version"));
        return true;
    }

    @Override
    public boolean disablePlugin(@NotNull String id) {
        asyncExecutor.execute(() -> internalDisablePlugin(id));  // todo return completableFuture
        return false;
    }

    private boolean internalDisablePlugin(@NotNull String id) {
        if (!this.enabledPlugins.containsKey(id)) {
            return false; // already in state
        }

        PluginContainer container = this.enabledPlugins.get(id);
        PluginMetadata metadata = container.getMetadata();
        Plugin plugin = container.getInstance();

        LOGGER.info("Disabling {} ({})", metadata.getNameOrId(), metadata.getVersion().orElse("Unknown version"));

        // TODO disable plugins that require this plugin.

        try {
            asyncExecutor.execute(plugin::onPluginDisable);
            this.server.getEventManager().unregister(plugin);
            this.server.getCommandManager().unregister(plugin);
            this.server.getScheduler().unregisterTasks(plugin);

            enabledPlugins.remove(metadata.getId());
            enabledPluginsByInstance.remove(plugin);

            ((PluginClassLoader) container.getClassLoader()).close();
            container = null;
            System.gc();

            ((LoomPluginMetadata) metadata).setState(PluginMetadata.State.DISABLED);
            LOGGER.info("Successfully disabled {} ({})", metadata.getNameOrId(), metadata.getVersion().orElse("Unknown version"));
            return true;
        } catch (IOException e) {
            LOGGER.error("Something went wrong when attempting to disable '{}'. This could have resulted in a memory leak. A server restart is recommended.", container.getMetadata().getNameOrId(), e);
            return false;
        }
    }

    @Override
    public boolean reloadPlugin(@NotNull String id) {  // todo return completableFuture
        asyncExecutor.execute(() -> {
            internalDisablePlugin(id);
            internalEnablePlugin(id);
        });
        return false;
    }

    @Override
    public boolean reloadAll() {
        asyncExecutor.execute(() -> {  // todo return completableFuture
            for(PluginContainer plugin : this.enabledPlugins.values()) {
                String id = plugin.getMetadata().getId();
                internalDisablePlugin(id);
                internalEnablePlugin(id);
            }
        });
        return false;
    }

}
