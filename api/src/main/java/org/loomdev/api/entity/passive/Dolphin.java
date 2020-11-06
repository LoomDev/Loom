package org.loomdev.api.entity.passive;

import org.loomdev.api.entity.mob.WaterAnimal;
import org.loomdev.api.world.Location;

public interface Dolphin extends WaterAnimal {

    Location getTreasureLocation();

    void setTreasureLocation(Location location);

    boolean hasFish();

    void setHasFish(boolean flag);

    int getMoistness();

    void setMoistness(int moistnessLevel);

}
