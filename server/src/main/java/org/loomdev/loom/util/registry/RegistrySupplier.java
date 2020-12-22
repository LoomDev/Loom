package org.loomdev.loom.util.registry;

@FunctionalInterface
public interface RegistrySupplier<T, U> {

    T get(U key);
}
