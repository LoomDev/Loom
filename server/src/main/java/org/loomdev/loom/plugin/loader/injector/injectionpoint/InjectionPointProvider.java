package org.loomdev.loom.plugin.loader.injector.injectionpoint;

import com.google.common.reflect.TypeToken;
import com.google.inject.Binder;
import com.google.inject.Binding;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.DependencyAndSource;
import com.google.inject.spi.ProviderInstanceBinding;
import com.google.inject.spi.ProvisionListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Source SpongePowered
 * https://github.com/SpongePowered/SpongeCommon/pull/1435
 */
public class InjectionPointProvider extends AbstractMatcher<Binding<?>> implements Module, ProvisionListener, Provider<InjectionPoint> {

    private InjectionPoint injectionPoint;

    @Override
    public void configure(Binder binder) {
        binder.bind(InjectionPoint.class).toProvider(this);
        binder.bindListener(this, this);
    }

    @Override
    public InjectionPoint get() {
        return this.injectionPoint;
    }

    @Override
    public boolean matches(Binding<?> binding) {
        return binding instanceof ProviderInstanceBinding && ((ProviderInstanceBinding<?>) binding).getUserSuppliedProvider() == this;
    }

    @Override
    public <T> void onProvision(ProvisionListener.ProvisionInvocation<T> provision) {
        try {
            this.injectionPoint = findInjectionPoint(provision.getDependencyChain());
            provision.provision();
        } finally {
            this.injectionPoint = null;
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    private InjectionPoint findInjectionPoint(List<DependencyAndSource> dependencyChain) {
        if (dependencyChain.size() < 3) {
            throw new AssertionError("Provider is not included in the dependency chain");
        }

        for (int i = dependencyChain.size() - 2; i >= 0; i--) {
            final Dependency<?> dependency = dependencyChain.get(i).getDependency();
            if (dependency == null) {
                return null;
            }
            final com.google.inject.spi.InjectionPoint spiInjectionPoint = dependency.getInjectionPoint();
            if (spiInjectionPoint != null) {
                final TypeToken<?> source = TypeToken.of(spiInjectionPoint.getDeclaringType().getType());
                final Member member = spiInjectionPoint.getMember();
                if (member instanceof Field) {
                    final Field field = (Field) member;
                    return new InjectionPoint(source, TypeToken.of(field.getGenericType()), field.getAnnotations());
                } else if (member instanceof Executable) {
                    final Executable executable = (Executable) member;
                    final Annotation[][] parameterAnnotations = executable.getParameterAnnotations();
                    final Type[] parameterTypes = executable.getGenericParameterTypes();
                    final int index = dependency.getParameterIndex();
                    return new InjectionPoint(source, TypeToken.of(parameterTypes[index]), parameterAnnotations[index]);
                } else {
                    throw new IllegalStateException("Unsupported Member type: " + member.getClass().getName());
                }
            }
        }

        return null;
    }
}
