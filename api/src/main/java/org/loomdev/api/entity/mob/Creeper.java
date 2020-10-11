package org.loomdev.api.entity.mob;

import org.loomdev.api.entity.Explosive;

public interface Creeper extends HostileEntity, Explosive {

    boolean isCharged();

    void setCharged(boolean charged);

    int getFuseTime();

    void setFuseTime(int ticks);

    int getCurrentFuseTime();

    boolean isIgnited();

    void ignite();

    void explode();
}
