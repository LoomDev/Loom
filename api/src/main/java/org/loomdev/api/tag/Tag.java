package org.loomdev.api.tag;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.registry.Keyed;

import java.util.Random;
import java.util.stream.Stream;

public interface Tag<T extends Keyed> {

    boolean contains(@NotNull T type);

    @NotNull
    Stream<T> getValues();

    @NotNull
    T getRandom(@NotNull Random random);
}
