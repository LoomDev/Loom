package org.loomdev.api.entity.passive;

import org.loomdev.api.entity.Golem;
import org.loomdev.api.entity.Shearable;

/**
 * Represents a SnowGolem entity.
 */
public interface SnowGolem extends Golem, Shearable {

    /**
     * Get whether the SnowGolem has a Pumpkin on his head.
     *
     * @return True if the SnowGolem has a Pumpkin on his head.
     */
    boolean hasPumpkin();

    /**
     * Set whether the SnowGolem has a Pumpkin on his head.
     *
     * @param flag True to set a Pumpkin on the SnowGolem's head, otherwise False.
     */
    void setHasPumpkin(boolean flag);

}
