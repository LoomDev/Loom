package org.loomdev.loom.util.registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.LoreData;
import org.loomdev.api.util.builder.BuilderBase;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.property.LoreItemProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RegistryImp implements Registry {

    private static final Logger LOGGER = LogManager.getLogger("Registry");
    private final Map<Class<?>, ItemProperty<?>> itemPropertyTypes = new HashMap<>();
    private final Map<Class<?>, Supplier<?>> builders = new HashMap<>();

    public RegistryImp() {
        // Item properties
        this.registerItemProperty(LoreData.class, new LoreItemProperty());

        // Builders
        this.registerBuilder(ItemStack.class, ItemStackImpl.BuilderImpl::new);
    }

    public <T> void registerItemProperty(Class<T> dataType, ItemProperty<T> itemProperty) {
        this.itemPropertyTypes.put(dataType, itemProperty);
    }

    @Override
    public <T> ItemProperty<T> getItemProperty(Class<T> aClass) {
        if (!this.itemPropertyTypes.containsKey(aClass)) {
            LOGGER.error("No property registered for data type {}.", aClass.getCanonicalName());
            return null;
        }
        return (ItemProperty<T>) this.itemPropertyTypes.get(aClass);
    }

    public <V, B extends BuilderBase<V, B>> void registerBuilder(Class<V> aClass, Supplier<B> supplier) {
        this.builders.put(aClass, supplier);
    }

    @Override
    public <V, B extends BuilderBase<V, B>> B createBuilder(Class<V> aClass) {
        if (!this.builders.containsKey(aClass)) {
            LOGGER.error("No builder registered for {}.", aClass.getCanonicalName());
            return null;
        }

        try {
            return (B) this.builders.get(aClass).get();
        } catch (Exception e) {
            LOGGER.error("Could not create builder for {}.", aClass.getCanonicalName());
            return null;
        }
    }
}
