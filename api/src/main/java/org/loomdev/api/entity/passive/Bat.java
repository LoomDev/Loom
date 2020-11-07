package org.loomdev.api.entity.passive;

import org.loomdev.api.entity.ambient.AmbientEntity;

public interface Bat extends AmbientEntity {

    boolean isResting();

    void setResting(boolean resting);
}
