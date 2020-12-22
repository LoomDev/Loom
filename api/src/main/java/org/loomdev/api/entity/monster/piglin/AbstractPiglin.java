package org.loomdev.api.entity.monster.piglin;

import org.loomdev.api.entity.ConvertableEntity;
import org.loomdev.api.entity.monster.Monster;

/**
 * Base for the {@link Piglin} and {@link PiglinBrute}
 */
public interface AbstractPiglin extends Monster, ConvertableEntity {

    /**
     * Gets whether the piglin entity is immune to zombification.
     *
     * @return {@code true} if the piglin entity is immune.
     */
    boolean isImmuneToZombification();

    /**
     * Sets whether the piglin entity is immune to zombification.
     *
     * @param flag {@code true} to make the piglin entity immune.
     */
    void setImmuneToZombification(boolean flag);

}
