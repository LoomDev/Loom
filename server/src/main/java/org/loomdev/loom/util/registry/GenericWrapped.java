package org.loomdev.loom.util.registry;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.registry.Keyed;

public class GenericWrapped implements Keyed {

    private final NamespacedKey namespacedKey;

    public GenericWrapped(String key) {
        this.namespacedKey = NamespacedKey.of(key);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return namespacedKey;
    }
}
