package org.loomdev.api.plugin;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Represents metadata from a plugin.
 */
public interface PluginMetadata {

    /**
     * Pattern for validating the plugins id.
     */
    Pattern ID_PATTERN = Pattern.compile("[a-z][a-z0-9-]{0,63}");

    /**
     * Id of the plugin. This id should be unique so it does not conflict with any other plugins and
     * should only contain alphanumeric characters, dashed and underscored.
     *
     * @return The id of the plugin.
     */
    @NotNull String getId();

    /**
     * A human readable name of the plugin.
     *
     * @return The plugin name.
     */
    default Optional<String> getName() {
        return Optional.empty();
    }

    /**
     * The version of the plugin.
     *
     * @return The version of then plugin.
     */
    default Optional<String> getVersion() {
        return Optional.empty();
    }

    /**
     * A human readable description of the plugin.
     *
     * @return The description of the plugin.
     */
    default Optional<String> getDescription() {
        return Optional.empty();
    }

    /**
     * The author(s) of the plugin.
     *
     * @return The names of the author(s) of the plugin.
     */
    default List<String> getAuthors() {
        return ImmutableList.of();
    }

    /**
     * The ids of the plugins that this plugin depends on.
     *
     * @return List of dependency ids.
     */
    default List<PluginDependency> getDependencies() {
        return ImmutableList.of();
    }

    /**
     * The minimum api version required to run the plugin.
     *
     * @return The minimum api version
     */
    @NotNull ApiVersion getMinimumApiVersion();

    /**
     * Gets the path to the source location of the plugin.
     *
     * @return The source the plugin was loaded from or {@link Optional#empty()} if unknown.
     */
    @NotNull Path getSource();

    /**
     * Gets the main class of the plugin.
     *
     * @return The main class of the plugin.
     */
    @NotNull String getMain();

    /**
     * Gets the name of the plugin if present, else the id will be returened.
     *
     * @return The name if present or the id.
     */
    default @NotNull String getNameOrId() {
        return getName().orElse(getId());
    }

    State getState();

    enum State {
        /**
         * The plugin is enabled
         */
        ENABLED,

        /**
         * The plugin is disabled
         */
        DISABLED,

        /**
         * The plugin is unable to be enabled because of an error.
         * See console for more details.
         */
        ERROR
    }

    final class PluginDependency {
        private final String id;
        private final boolean optional;

        public PluginDependency(String id, boolean optional) {
            this.id = id;
            this.optional = optional;
        }

        public String getId() {
            return id;
        }

        public boolean isOptional() {
            return optional;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PluginDependency that = (PluginDependency) o;
            return optional == that.optional &&
                    Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, optional);
        }

        @Override
        public String toString() {
            return "PluginDependency{" +
                    "id='" + id + '\'' +
                    ", optional=" + optional +
                    '}';
        }
    }
}
