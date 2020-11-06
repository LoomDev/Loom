package org.loomdev.api.entity.passive;

public interface Chicken extends Animal {

    boolean hasJockey();

    int getEggLayTime();

    void setEggLayTime(int ticks);
}
