package org.loomdev.loom.util.registry;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.registry.Keyed;

public class GenericWrapped implements Keyed {

    private final NamespacedKey key;

    public GenericWrapped(@NotNull String key) {
        this.key = NamespacedKey.of(key);
    }

    public GenericWrapped(@NotNull NamespacedKey key) {
        this.key = key;
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return key;
    }
}
