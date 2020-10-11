package org.loomdev.api.entity.passive;

import org.loomdev.api.entity.mob.AmbientEntity;

public interface Bat extends AmbientEntity {

    boolean isRoosting();

    void setRoosting(boolean flag);

}
