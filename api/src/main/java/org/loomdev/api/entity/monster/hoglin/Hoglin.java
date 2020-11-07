package org.loomdev.api.entity.monster.hoglin;

import org.loomdev.api.entity.ConvertableEntity;
import org.loomdev.api.entity.monster.Enemy;
import org.loomdev.api.entity.animal.Animal;

public interface Hoglin extends Animal, Enemy, ConvertableEntity {

    boolean isImmuneToZombification();

    void setImmuneToZombification(boolean flag);

    boolean isHuntable();

    void setHuntable(boolean flag);
}
