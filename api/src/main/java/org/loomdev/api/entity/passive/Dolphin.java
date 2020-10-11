package org.loomdev.api.entity.passive;

import org.loomdev.api.entity.mob.WaterCreature;
import org.loomdev.api.world.Location;

public interface Dolphin extends WaterCreature {

    Location getTreasureLocation();

    void setTreasureLocation(Location location);

    boolean hasFish();

    void setHasFish(boolean flag);

    int getMoistness();

    void setMoistness(int moistnessLevel);

}
