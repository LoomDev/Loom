package org.loomdev.api.util;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;

import java.util.Objects;

/**
 * Represents a key which consists of two parts, a namespace and a key.
 *
 * <p>
 *     Note: a NamespacedKey key can only contain alphanumeric characters, periods, underscores and hyphens.
 * </p>
 */
public class NamespacedKey {

    /**
     * Parses a namespaced key.
     *
     * @param key The full key.
     * @return A namespaced key.
     * @deprecated This should never be used by plugins and if for internal use only.
     */
    @Deprecated
    public static NamespacedKey of(String key) {
        String[] split = key.split(":");
        return new NamespacedKey(split[0], split.length > 1 ? split[1] : split[0]);
    }

    /**
     * Used to represent an internal Minecraft key.
     * This should not be used by plugins.
     *
     * @param key The key.
     * @return A minecraft namespaced key.
     */
    public static NamespacedKey minecraft(@NotNull String key) {
        return new NamespacedKey("minecraft", key);
    }

    /**
     * Used to represent an internal Loom key.
     * This should not be used by plugins.
     *
     * @param key The key.
     * @return A loom namespaced key.
     */
    public static NamespacedKey loom(@NotNull String key) {
        return new NamespacedKey("loom", key);
    }

    private final String namespace;
    private final String key;

    /**
     * Creates a namespaced key with a namespace and key.
     *
     * @param namespace The namespace.
     * @param key The key.
     * @deprecated Use the plugin constructor instead.
     */
    @Deprecated
    public NamespacedKey(String namespace, String key) {
        this.namespace = namespace;
        this.key = key;
    }

    /**
     * Create a new NamespacedKey for a plugin.
     *
     * @param plugin The plugin to use as the namespace.
     * @param key The key to create.
     */
    public NamespacedKey(@NotNull Object plugin, @NotNull String key) {
        var pluginContainer = Loom.getPluginManager().fromInstance(plugin);
        if (pluginContainer == null) throw new NullPointerException();

        this.namespace = pluginContainer.getMetadata().getId();
        this.key = key;
    }

    /**
     * Gets the namespace of this namespaced key.
     *
     * @return The namespace of this namespaced key.
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Gets the key of this namespaced key.
     *
     * @return The key of this namespaced key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets a string representation of this namespaced key.
     *
     * @return A string representation of this namespaced key (with the format <code>namespace:key</code>).
     */
    @Override
    public String toString() {
        return namespace + ':' + key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamespacedKey that = (NamespacedKey) o;
        return Objects.equals(namespace, that.namespace) &&
                Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, key);
    }
}
