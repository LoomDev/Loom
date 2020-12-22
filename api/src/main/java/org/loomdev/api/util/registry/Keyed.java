package org.loomdev.api.util.registry;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.NamespacedKey;

/**
 * Represents an object with a namespaced key.
 */
public interface Keyed {

    /**
     * Gets the namespaced key of this object.
     * @return The namespaced key.
     */
    @NotNull NamespacedKey getKey();
}
