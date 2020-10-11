package org.loomdev.loom.plugin.loader.injector;

import com.google.common.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

/**
 * Source SpongePowered
 * https://github.com/SpongePowered/SpongeCommon/pull/1435
 */
public class LoomInjectionPoint implements AnnotatedElement {

    private final TypeToken<?> source;
    private final TypeToken<?> type;
    private final Annotation[] annotations;

    LoomInjectionPoint(TypeToken<?> source, TypeToken<?> type, Annotation[] annotations) {
        this.annotations = annotations;
        this.source = source;
        this.type = type;
    }

    public TypeToken<?> getSource() {
        return this.source;
    }

    public TypeToken<?> getType() {
        return this.type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return (A) Arrays.stream(this.annotations).filter(annotationClass::isInstance).findFirst().orElse(null);
    }

    @Override
    public Annotation[] getAnnotations() {
        return Arrays.copyOf(this.annotations, this.annotations.length);
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return Arrays.copyOf(this.annotations, this.annotations.length);
    }
}
