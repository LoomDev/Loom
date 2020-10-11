package org.loomdev.loom.event;

import net.kyori.event.method.MethodScanner;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.event.Subscribe;

import java.lang.reflect.Method;

public class LoomMethodScanner implements MethodScanner<Object> {
    @Override
    public boolean shouldRegister(@NonNull Object listener, @NonNull Method method) {
        return method.isAnnotationPresent(Subscribe.class);
    }

    @Override
    public int postOrder(@NonNull Object listener, @NonNull Method method) {
        return method.getAnnotation(Subscribe.class).order().ordinal();
    }

    @Override
    public boolean consumeCancelledEvents(@NonNull Object listener, @NonNull Method method) {
        return !method.getAnnotation(Subscribe.class).ignoreCancelled();
    }
}
