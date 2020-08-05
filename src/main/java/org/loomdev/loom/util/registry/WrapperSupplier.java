package org.loomdev.loom.util.registry;

import org.loomdev.api.util.registry.Keyed;

@FunctionalInterface
public interface WrapperSupplier<T extends Keyed> {
    T get(String key);
}
