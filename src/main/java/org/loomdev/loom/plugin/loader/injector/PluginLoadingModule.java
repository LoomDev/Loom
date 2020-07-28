package org.loomdev.loom.plugin.loader.injector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import org.loomdev.api.plugin.Plugin;

public class PluginLoadingModule implements Module, Provider<Class<? extends Plugin>> {

    private final Class<? extends Plugin> mainClass;

    public PluginLoadingModule(Class<? extends Plugin> mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(new TypeLiteral<Class<? extends Plugin>>() {}).toInstance(mainClass);
    }

    @Override
    public Class<? extends Plugin> get() {
        return this.mainClass;
    }
}
