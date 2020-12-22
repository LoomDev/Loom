package org.loomdev.api.plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Manages plugins loaded by Loom.
 */
public interface PluginManager {

    /**
     * Gets a plugin container based on its id.
     *
     * @param id The id of the plugin to get.
     * @return The plugin, if available.
     */
    @Nullable PluginContainer getPlugin(@NotNull String id);

    /**
     * Gets a plugin container from an instance.
     *
     * @param pluginInstance The instance to get the plugin container from.
     * @return The plugin container or {@link Optional#empty()} if not a plugin container.
     */
    @Nullable PluginContainer fromInstance(@NotNull Object pluginInstance);

    /**
     * Gets all the plugins loaded by Loom.
     *
     * @return The plugins loaded by Loom.
     */
    @NotNull Collection<PluginContainer> getEnabledPlugins();

    @NotNull Optional<PluginMetadata> getPluginMetadata(String id);

    @NotNull Collection<PluginMetadata> getAllPlugins();

    boolean wasFound(String id);

    boolean isEnabled(String id);

    boolean isDisabled(String id);

    @NotNull List<PluginMetadata> scanPluginDirectory();

    // TODO get dependency graph

    boolean enablePlugin(@NotNull String id);

    boolean disablePlugin(@NotNull String id);

    boolean reloadPlugin(@NotNull String id);

    boolean reloadAll();


    /**
     * Results of a plugin enable/disable call.
     */
    enum Result {
        /**
         * The requested plugin was not found among the loaded plugins.
         */
        NOT_FOUND,

        /**
         * The plugin was already enabled/disabled. No state changes occurred.
         */
        ALREADY_IN_STATE,

        /**
         * The plugin was successfully enabled/disabled.
         */
        SUCCESS,

        /**
         * Failed to enable/disable the plugin.
         */
        FAILED;

        /**
         * Check if the results is an error result.
         *
         * @return {@code true} if {@link Result#FAILED} or {@link Result#NOT_FOUND}.
         */
        boolean isError() {
            return this == FAILED || this == NOT_FOUND;
        }

        /**
         * Check if the result is an success result.
         *
         * @return {@code true} if {@link Result#SUCCESS} or {@link Result#ALREADY_IN_STATE}.
         */
        boolean isSuccess() {
            return this == SUCCESS || this == ALREADY_IN_STATE;
        }
    }
}
