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
     * This should never be used by plugins and if for internal use only.
     *
     * @param key The full key.
     * @return A namespaced key.
     */
    @Deprecated
    public static NamespacedKey of(String key) {
        String[] split = key.split(":");
        return new NamespacedKey(split[0], split.length > 1 ? split[1] : split[0]);
    }

    /**
     * This should never be used by plugins and if for internal use only.
     *
     * @param key The key.
     * @return A minecraft namespaced key.
     */
    @Deprecated
    public static NamespacedKey forMinecraft(String key) {
        return new NamespacedKey("minecraft", key);
    }

    /**
     * This should never be used by plugins and if for internal use only.
     *
     * @param key The key.
     * @return A loom namespaced key.
     */
    @Deprecated
    public static NamespacedKey forLoom(String key) {
        return new NamespacedKey("loom", key);
    }

    private final String namespace;
    private final String key;

    /**
     * This should never be used by plugins and if for internal use only.
     *
     * @param namespace The namespace.
     * @param key The key.
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
     * Get the namespace of this NamespacedKey.
     *
     * @return The namespace of this NamespacedKey.
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Get the key of this NamespacedKey.
     *
     * @return The key of this NamespacedKey.
     */
    public String getKey() {
        return key;
    }

    /**
     * Get the String representation of this NamespacedKey.
     *
     * @return The String representation of this NamespacedKey.
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
