package org.loomdev.api.entity.monster;

import org.loomdev.api.entity.Explosive;
import org.loomdev.api.entity.monster.Monster;

public interface Creeper extends Monster, Explosive {

    boolean isCharged();

    void setCharged(boolean charged);

    int getFuseTime();

    void setFuseTime(int ticks);

    int getCurrentFuseTime();

    boolean isIgnited();

    void ignite();

    void explode();
}
