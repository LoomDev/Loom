package org.loomdev.api.entity.mob;

import org.loomdev.api.entity.Ageable;
import org.loomdev.api.entity.ConvertingEntity;

public interface Zombie extends Monster, ConvertingEntity, Ageable {
    boolean canBreakDoors();

    void setCanBreakDoors(boolean flag);

    // TODO v2 override burnsInDayLight

}
