package org.loomdev.api.entity.monster;

public interface Endermite extends Monster {

    boolean isPlayerSpawned();

    void setPlayerSpawned(boolean flag);

    int getLifeTime();

    void setLifeTime(int ticks);

    int getMaxLifeTime();

    void setMaxLifeTime(int ticks);

    void resetMaxLifeTime();
}
