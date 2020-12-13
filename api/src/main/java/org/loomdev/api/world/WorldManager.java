package org.loomdev.api.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.NamespacedKey;

import java.util.Collection;
import java.util.Optional;

public interface WorldManager {

    @NotNull
    Collection<World> getWorlds();

    @NotNull
    Optional<World> getWorld(@NotNull NamespacedKey namespacedKey);
}
