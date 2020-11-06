package org.loomdev.api.entity.mob;

import org.loomdev.api.entity.ConvertingEntity;
import org.loomdev.api.entity.passive.Animal;

public interface Hoglin extends Animal, Enemy, ConvertingEntity {

    boolean isImmuneToZombification();

    void setImmuneToZombification(boolean flag);

    boolean isHuntable();

    void setHuntable(boolean flag);
}
