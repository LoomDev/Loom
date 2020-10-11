package org.loomdev.loom.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.SpawnGroup;
import org.loomdev.api.util.NamespacedKey;

public class SpawnGroupImpl implements SpawnGroup {

    private final NamespacedKey namespacedKey;
    private final net.minecraft.entity.SpawnGroup mcSpawnGroup;

    public SpawnGroupImpl(String key) {
        this.namespacedKey = NamespacedKey.of(key);
        this.mcSpawnGroup = net.minecraft.entity.SpawnGroup.byName(this.namespacedKey.getKey());
    }

    @Override
    public int getCapacity() {
        return this.mcSpawnGroup.getCapacity();
    }

    @Override
    public boolean isPeaceful() {
        return this.mcSpawnGroup.isPeaceful();
    }

    @Override
    public boolean isAnimal() {
        return this.mcSpawnGroup.isAnimal();
    }

    @Override
    public int getImmediateDespawnRange() {
        return this.mcSpawnGroup.getImmediateDespawnRange();
    }

    @Override
    public int getDespawnStartRange() {
        return this.mcSpawnGroup.getDespawnStartRange();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return this.namespacedKey;
    }
}
