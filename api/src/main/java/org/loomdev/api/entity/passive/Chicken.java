package org.loomdev.api.entity.passive;

public interface Chicken extends AnimalEntity {

    boolean hasJockey();

    int getEggLayTime();

    void setEggLayTime(int ticks);
}
