package org.loomdev.api.util;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a key which consists of two parts, a namespace and a key.
 *
 *
 * <p>
 *     As of Minecraft 1.13 all item, blocks, ... use a namespaced key instead of an id. This provides more felixbility for modding
 * </p>
 *
 * <p>
 *     Note: a namespace can only contain lowercase alphanumeric characters, periods, underscores ('_') and hyphens ('-').
 *     A key can contain lowercase alphanumeric characters, periods, underscores ('_'), hyphens ('-') and forward slashes ('/').
 * </p>
 */
public class NamespacedKey {

    public static final String SEPARATOR = ":";
    public static final Pattern REGEX = Pattern.compile("^[a-z0-9._-]*" + SEPARATOR + "[a-z0-9._/-]*$");

    // region static helpers

    /**
     * Parses a namespaced key from its string representation.
     *
     * @param value The string representation of the namespaced key. (format: "namespace:key")
     * @return A {@link NamespacedKey} of the string representation.
     * @throws InvalidNamespacedKeyException when the format of the string representation is invalid.
     */
    @NotNull
    public static NamespacedKey of(@NotNull String value) throws InvalidNamespacedKeyException {
        return new NamespacedKey(value);
    }

    /**
     * Creates a namespaced key with a specific namespace and key.
     *
     * @param namespace The namespace.
     * @param key The key.
     * @return A {@link NamespacedKey} of the provided namespace and key.
     * @throws InvalidNamespacedKeyException when the namespace or key is invalid.
     */
    @NotNull
    public static NamespacedKey of(@NotNull String namespace, @NotNull String key) throws InvalidNamespacedKeyException {
        return new NamespacedKey(namespace, key);
    }

    /**
     * Returns a namespaced key with "minecraft" as namespace.
     *
     * <p>This method should be used when creating a Minecraft namespaced key. Used for Minecraft items, blocks, ...</p>
     *
     * @param key The key to namespace with "minecraft".
     * @return A "minecraft" namespaced key.
     * @throws InvalidNamespacedKeyException when the key is invalid.
     */
    @NotNull
    public static NamespacedKey minecraft(@NotNull String key) throws InvalidNamespacedKeyException {
        return new NamespacedKey("minecraft", key);
    }

    /**
     * Returns a namespaced key with "loom" as namespace.
     *
     * @param key The key.
     * @return A loom namespaced key.
     * @throws InvalidNamespacedKeyException when the key is invalid.
     */
    @NotNull
    public static NamespacedKey loom(@NotNull String key) throws InvalidNamespacedKeyException {
        return new NamespacedKey("loom", key);
    }

    // endregion static helpers

    private final String namespace;
    private final String key;

    /**
     * Creates a namespaced key from its string representation.
     *
     * @param value The string representation of the namespaced key. (format: "namespace:key")
     * @throws InvalidNamespacedKeyException when the format of the string representation is invalid.
     */
    public NamespacedKey(@NotNull String value) throws InvalidNamespacedKeyException {
        if (!REGEX.asPredicate().test(value)) {
            throw new InvalidNamespacedKeyException(value);
        }

        var parts = value.split(SEPARATOR);
        this.namespace = parts[0];
        this.key = parts[1];
    }

    /**
     * Creates a namespaced key with a specific namespace and key.
     *
     * @param namespace The namespace.
     * @param key The key.
     * @throws InvalidNamespacedKeyException when the namespace or key is invalid.
     */
    public NamespacedKey(@NotNull String namespace, @NotNull String key) throws InvalidNamespacedKeyException {
        this.namespace = namespace;
        this.key = key;

        if (!REGEX.asPredicate().test(toString())) {
            throw new InvalidNamespacedKeyException(toString());
        }
    }

    /**
     * Gets the namespace of this namespaced key.
     *
     * @return The namespace of this namespaced key.
     */
    @NotNull
    public String getNamespace() {
        return namespace;
    }

    /**
     * Gets the key of this namespaced key.
     *
     * @return The key of this namespaced key.
     */
    @NotNull
    public String getKey() {
        return key;
    }

    /**
     * Returns a string representation of this namespaced key.
     *
     * @return A string representation of this namespaced key (formatted as <code>namespace:key</code>).
     */
    @Override
    @NotNull
    public String toString() {
        return namespace + SEPARATOR + key;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)  {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (getClass() != other.getClass()) {
            return false;
        }

        NamespacedKey otherNamespacedKey = (NamespacedKey) other;
        return Objects.equals(namespace, otherNamespacedKey.namespace)
                && Objects.equals(key, otherNamespacedKey.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, key);
    }
}
