package org.loomdev.api.entity.mob;

import org.loomdev.api.entity.ConvertingEntity;
import org.loomdev.api.entity.passive.AnimalEntity;

public interface Hoglin extends AnimalEntity, Monster, ConvertingEntity {

    boolean isImmuneToZombification();

    void setImmuneToZombification(boolean flag);

    boolean isHuntable();

    void setHuntable(boolean flag);
}
