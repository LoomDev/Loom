package org.loomdev.api.entity;

import org.loomdev.api.Loom;
import org.loomdev.api.util.registry.Keyed;

public interface SpawnGroup extends Keyed {

    // TODO redo the method names, they're confusing af

    // region :: SpawnGroups

    SpawnGroup MONSTER = getById("minecraft:monster");
    SpawnGroup CREATURE = getById("minecraft:creature");
    SpawnGroup AMBIENT = getById("minecraft:ambient");
    SpawnGroup WATER_CREATURE = getById("minecraft:water_creature");
    SpawnGroup WATER_AMBIENT = getById("minecraft:water_ambient");
    SpawnGroup MISC = getById("minecraft:misc");

    // endregion :: SpawnGroups

    static SpawnGroup getById(String id) {
        return Loom.getRegistry().getWrapped(SpawnGroup.class, id);
    }

    int getCapacity();

    boolean isPeaceful();

    boolean isAnimal();

    int getImmediateDespawnRange();

    int getDespawnStartRange();

}
