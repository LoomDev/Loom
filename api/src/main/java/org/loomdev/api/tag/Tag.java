package org.loomdev.api.tag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.loomdev.api.util.registry.Keyed;

import java.util.List;
import java.util.Random;

public interface Tag<T extends Keyed> {

    boolean contains(@NotNull T type);

    @NotNull
    @Unmodifiable
    List<T> getValues();

    @NotNull
    T getRandom(@NotNull Random random);
}
