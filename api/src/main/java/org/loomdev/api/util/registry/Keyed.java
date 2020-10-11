package org.loomdev.api.util.registry;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.NamespacedKey;

public interface Keyed {

    /**
     * Get the NamespacedKey of this object.
     * @return The NamespacedKey.
     */
    @NotNull NamespacedKey getKey();
}
