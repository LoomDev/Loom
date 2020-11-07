package org.loomdev.api.entity.animal;

public interface Chicken extends Animal {

    boolean hasJockey();

    int getEggLayTime();

    void setEggLayTime(int ticks);
}
