package org.loomdev.loom.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.world.World;
import org.loomdev.api.world.WorldManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WorldManagerImpl implements WorldManager {

    private final Map<NamespacedKey, World> worlds;

    public WorldManagerImpl() {
        this.worlds = new HashMap<>();
    }

    @Override
    @NotNull
    public Collection<World> getWorlds() {
        return worlds.values();
    }

    @Override
    @NotNull
    public Optional<World> getWorld(@NotNull NamespacedKey namespacedKey) {
        return Optional.ofNullable(worlds.get(namespacedKey));
    }

    public void registerWorld(@NotNull World world) {
        worlds.put(world.getDimension(), world);
    }
}
