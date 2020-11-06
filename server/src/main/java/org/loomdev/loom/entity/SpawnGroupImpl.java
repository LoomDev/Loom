package org.loomdev.loom.entity;

import net.minecraft.world.entity.MobCategory;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.SpawnGroup;
import org.loomdev.api.util.NamespacedKey;

public class SpawnGroupImpl implements SpawnGroup {

    private final NamespacedKey namespacedKey;
    private final MobCategory mcSpawnGroup;

    public SpawnGroupImpl(String key) {
        this.namespacedKey = NamespacedKey.of(key);
        this.mcSpawnGroup = MobCategory.byName(namespacedKey.getKey());
    }

    @Override
    public int getCapacity() {
        return mcSpawnGroup.getMaxInstancesPerChunk();
    }

    @Override
    public boolean isPeaceful() {
        return mcSpawnGroup.isFriendly();
    }

    @Override
    public boolean isAnimal() {
        return mcSpawnGroup.isPersistent();
    }

    @Override
    public int getImmediateDespawnRange() {
        return mcSpawnGroup.getDespawnDistance();
    }

    @Override
    public int getDespawnStartRange() {
        return mcSpawnGroup.getNoDespawnDistance();
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return namespacedKey;
    }
}
