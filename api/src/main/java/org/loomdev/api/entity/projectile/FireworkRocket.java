package org.loomdev.api.entity.projectile;

public interface FireworkRocket extends Projectile {

    void detonate();

    boolean isShotAtAngle();

    void setShortAtAngle(boolean flag);

}
