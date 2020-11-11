package org.loomdev.loom.plugin.loader.injector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

public class PluginLoadingModule implements Module, Provider<Class<?>> {

    private final Class<?> mainClass;

    public PluginLoadingModule(Class<?> mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(new TypeLiteral<Class<?>>() {}).toInstance(mainClass);
    }

    @Override
    public Class<?> get() {
        return this.mainClass;
    }
}
