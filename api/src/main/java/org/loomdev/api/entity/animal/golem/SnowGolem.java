package org.loomdev.api.entity.animal.golem;

import org.loomdev.api.entity.animal.golem.Golem;
import org.loomdev.api.entity.Shearable;

/**
 * Represents a SnowGolem entity.
 */
public interface SnowGolem extends Golem, Shearable {

    /**
     * Gets whether the SnowGolem has a Pumpkin on his head.
     *
     * @return True if the SnowGolem has a Pumpkin on his head.
     */
    boolean hasPumpkin();

    /**
     * Sets whether the SnowGolem has a Pumpkin on his head.
     *
     * @param flag True to set a Pumpkin on the SnowGolem's head, otherwise False.
     */
    void setHasPumpkin(boolean flag);

}
