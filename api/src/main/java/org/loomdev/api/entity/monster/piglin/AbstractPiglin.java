package org.loomdev.api.entity.monster.piglin;

import org.loomdev.api.entity.ConvertableEntity;
import org.loomdev.api.entity.monster.Monster;

/**
 * Base for the {@link Piglin} and {@link PiglinBrute}
 */
public interface AbstractPiglin extends Monster, ConvertableEntity {

    /**
     * Gets whether the Piglin entity is immune to zombification.
     *
     * @return True if the Piglin entity is immune.
     */
    boolean isImmuneToZombification();

    /**
     * Sets whether the Piglin entity is immune to zombification.
     *
     * @param flag True to make the Plugin entity immune.
     */
    void setImmuneToZombification(boolean flag);

}
