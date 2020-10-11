package org.loomdev.api.util.builder;

public interface BuilderBase<T, B extends BuilderBase<T, B>> {

    B from(T value);

    T build();
}
