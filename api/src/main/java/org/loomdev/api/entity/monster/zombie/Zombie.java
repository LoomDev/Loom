package org.loomdev.api.entity.monster.zombie;

import org.loomdev.api.entity.Ageable;
import org.loomdev.api.entity.ConvertableEntity;
import org.loomdev.api.entity.monster.Monster;

public interface Zombie extends Monster, ConvertableEntity, Ageable {
    boolean canBreakDoors();

    void setCanBreakDoors(boolean flag);

    // TODO v2 override burnsInDayLight

}
