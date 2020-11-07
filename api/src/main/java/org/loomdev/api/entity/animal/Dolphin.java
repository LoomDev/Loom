package org.loomdev.api.entity.animal;

import org.loomdev.api.entity.monster.WaterAnimal;
import org.loomdev.api.world.Location;

public interface Dolphin extends WaterAnimal {

    Location getTreasureLocation();

    void setTreasureLocation(Location location);

    boolean hasFish();

    void setHasFish(boolean flag);

    int getMoistness();

    void setMoistness(int moistnessLevel);

}
