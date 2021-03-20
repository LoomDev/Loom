package org.loomdev.loom.plugin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.PluginState;
import org.loomdev.api.plugin.exception.IllegalPluginStateChangeException;
import org.loomdev.api.plugin.exception.PluginException;
import org.loomdev.api.plugin.exception.PluginNotFoundException;
import org.loomdev.api.plugin.exception.PluginLoadingException;
import org.loomdev.loom.plugin.lifecycle.LifecycleEventImpl;
import org.loomdev.loom.plugin.loader.PluginLoader;
import org.loomdev.loom.plugin.scanner.PluginScanner;
import org.loomdev.loom.server.ServerImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InternalPluginManager {

    private static final Logger LOGGER = LogManager.getLogger("PluginManager");

    private final @NotNull ServerImpl server;
    private final @NotNull PluginLoader pluginLoader;
    private final @NotNull PluginScanner scanner;

    private final PluginList plugins;

    public InternalPluginManager(@NotNull ServerImpl server, @NotNull Path pluginFolder) {
        this.server = server;
        this.pluginLoader = new PluginLoader(server);
        this.scanner = new PluginScanner(pluginFolder);

        this.plugins = new PluginList();

        var pluginFolderFile = pluginFolder.toFile();
        if (!pluginFolderFile.exists()) {
            var success = pluginFolderFile.mkdirs();
            if (!success) {
                LOGGER.error("Unable to create plugin folder. ({})", pluginFolder.getFileName());
                server.halt();
            }
        }
    }

    public void loadOnServerStart() {
        var pluginScans = this.scanner.scan();

        pluginScans.stream()
                .map(this.pluginLoader::loadMetadataFromJar)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(plugins::add);

        getLoadingOrderForPresent()
                .forEach(node -> {
                    //noinspection OptionalGetWithoutIsPresent
                    var container = plugins.getContainer(node.getId()).get();

                    for (var requiredDependency : node.getRequiredDependencies()) {
                        var dependencyContainer = plugins.getContainer(requiredDependency);

                        if (dependencyContainer.isEmpty()) {
                            LOGGER.error("Missing required dependency '{}' for '{}'", requiredDependency, container.getMetadata().getId());
                            container.setState(PluginState.INVALID);
                            return;
                        }

                        if (dependencyContainer.get().getState() != PluginState.LOADED) {
                            container.setState(PluginState.INVALID);
                            return;
                        }
                    }

                    try {
                        this.internalLoad(container);
                    } catch (Exception e) {
                        LOGGER.error("An error occurred while attempting to enable '{}'.", container.getMetadata().getId(), e);
                    }
                });
    }

    public void enableOnServerStart() {
        getLoadingOrderForPresent()
                .filter(this::filterRequiredDependenciesPresentAndEnabled)
                .map(node -> plugins.getContainer(node.getId()))
                .map(Optional::get)
                .forEach(container -> {
                    try {
                        this.internalEnable(container);
                    } catch (Exception e) {
                        container.setState(PluginState.INVALID);
                        LOGGER.error("An error occurred while attempting to enable '{}'.", container.getMetadata().getId(), e);
                    }
                });
    }

    public void disableOnServerStop() {
        var loadingOrder = getLoadingOrderForPresent()
                .filter(this::filterRequiredDependenciesPresentAndEnabled)
                .map(node -> plugins.getContainer(node.getId()))
                .map(Optional::get)
                .collect(Collectors.toList());

        Collections.reverse(loadingOrder);

        loadingOrder.forEach(container -> {
            try {
                this.internalDisable(container);
            } catch (Exception e) {
                LOGGER.error("An error occurred while attempting to disable '{}'.", container.getMetadata().getId(), e);
            }
        });
    }

    public void reloadAll() {
        disableOnServerStop();

        plugins.clear();
        loadOnServerStart();
        enableOnServerStart();
    }

    // TODO should be called async during runtime
    public void enable(@NotNull String pluginId) throws PluginNotFoundException, IllegalPluginStateChangeException, PluginLoadingException {
        var optionalContainer = this.plugins.getContainer(pluginId);
        if (optionalContainer.isEmpty()) {
            throw new PluginNotFoundException(pluginId);
        }

        var container = optionalContainer.get();
        if (container.getState() != PluginState.DISABLED && container.getState() != PluginState.INVALID) {
            throw new IllegalPluginStateChangeException(pluginId, container.getState(), PluginState.ENABLED);
        }

        internalLoad(container);
        internalEnable(container);
    }

    // TODO should be called async during runtime
    public void disable(@NotNull String pluginId)  throws PluginNotFoundException, IllegalPluginStateChangeException {
        var optionalContainer = this.plugins.getContainer(pluginId);
        if (optionalContainer.isEmpty()) {
            throw new PluginNotFoundException(pluginId);
        }

        var container = optionalContainer.get();
        if (container.getState() == PluginState.DISABLING || container.getState() == PluginState.DISABLED || container.getState() == PluginState.INVALID) {
            throw new IllegalPluginStateChangeException(pluginId, container.getState(), PluginState.DISABLED);
        }

        internalDisable(container);
    }

    // TODO should be called async during runtime
    public boolean reload(String pluginId) {
        var optionalContainer = this.plugins.getContainer(pluginId);
        if (optionalContainer.isEmpty()) {
            throw new PluginNotFoundException(pluginId);
        }

        var container = optionalContainer.get();

        var failedToDisable = false;
        if (container.getState() != PluginState.DISABLED && container.getState() != PluginState.INVALID) {
            try {
                internalDisable(container);
            } catch (Exception e) {
                failedToDisable = true;
                e.printStackTrace();
            }
        }

        if (!failedToDisable) {
            internalEnable(container);
        }

        return failedToDisable;
    }

    public PluginList getPlugins() {
        return plugins;
    }

    // region -- Internal logic

    private void internalLoad(PluginContainer container) throws PluginLoadingException {
        LOGGER.info("Loading '{}' ({}).", container.getMetadata().getName(), container.getMetadata().getVersion());

        try {
            container.setState(PluginState.LOADING);
            this.pluginLoader.load(container);
            container.setState(PluginState.LOADED);
        } catch (PluginLoadingException exception) {
            container.setState(PluginState.INVALID);
            throw exception;
        }
    }

    private void internalEnable(@NotNull PluginContainer pluginContainer) {
        if (pluginContainer.getState() == PluginState.ENABLED || pluginContainer.getState() == PluginState.INVALID) {
            return;
        }

        var metadata = pluginContainer.getMetadata();

        LOGGER.info("Enabling '{}' ({}).", metadata.getName(), metadata.getVersion());
        pluginContainer.setState(PluginState.ENABLING);

        var enableLifecycleEvent = new LifecycleEventImpl.Enable(pluginContainer.getProxiedServer());
        pluginContainer.fireLifecycleEvent(enableLifecycleEvent);

        pluginContainer.setState(PluginState.ENABLED);
        LOGGER.info("Enabled '{}' ({}).", metadata.getName(), metadata.getVersion());
    }

    private void internalDisable(@NotNull PluginContainer pluginContainer) throws PluginException {
        if (pluginContainer.getState() == PluginState.DISABLED || pluginContainer.getState() == PluginState.INVALID) {
            return;
        }

        var metadata = pluginContainer.getMetadata();
        LOGGER.info("Disabling '{}' ({}).", metadata.getName(), metadata.getVersion());

        pluginContainer.setState(PluginState.DISABLING);

        try {
            var disableLifecycleEvent = new LifecycleEventImpl.Disable(pluginContainer.getProxiedServer());
            pluginContainer.fireLifecycleEvent(disableLifecycleEvent);

            server.getEventManager().unregister(metadata);
            server.getCommandManager().unregister(metadata);
            server.getScheduler().unregisterTasks(metadata);

            pluginContainer.dispose();
            // System.gc(); // this should not be called manually? it causes a serious lag spike when reloading all plugins
        } catch (IOException e) {
            LOGGER.error("Something went wrong when attempting to disable '{}'. This could have resulted in a memory leak. A server restart is recommended.", metadata.getName(), e);
            throw new PluginException(metadata.getId(), "Something went wrong attempting to disable '" + metadata.getId() + "'.", e);
        }

        pluginContainer.setState(PluginState.DISABLED);
        LOGGER.info("Disabled '{}' ({}).", metadata.getName(), metadata.getVersion());
    }

    // endregion -- Internal logic

    // region -- Helpers

    private Stream<DependencyGraph.Node> getLoadingOrderForPresent() {
        return plugins.getDependencyGraph()
                .getLoadingOrder()
                .stream()
                .filter(node -> plugins.contains(node.getId()));
    }

    private boolean filterRequiredDependenciesPresentAndEnabled(DependencyGraph.Node node) {
        for (var requiredDependency : node.getRequiredDependencies()) {
            var dependencyContainer = plugins.getContainer(requiredDependency);
            var isPresentAndEnabled = dependencyContainer.map(container -> container.getState() == PluginState.ENABLED)
                    .orElse(false);

            if (!isPresentAndEnabled) {
                return false;
            }
        }
        return true;
    }

    // endregion -- Helpers
}
