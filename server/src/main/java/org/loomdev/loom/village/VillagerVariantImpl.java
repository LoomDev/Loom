package org.loomdev.loom.village;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.village.VillagerVariant;

public class VillagerVariantImpl implements VillagerVariant {

    private final NamespacedKey namespacedKey;

    public VillagerVariantImpl(String key) {
        this.namespacedKey = NamespacedKey.of(key);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return this.namespacedKey;
    }
}
