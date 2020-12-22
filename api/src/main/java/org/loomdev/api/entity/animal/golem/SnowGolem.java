package org.loomdev.api.entity.animal.golem;

import org.loomdev.api.entity.animal.golem.Golem;
import org.loomdev.api.entity.Shearable;

/**
 * Represents a snow golem entity.
 */
public interface SnowGolem extends Golem, Shearable {

    /**
     * Gets whether the snow golem has a Pumpkin on his head.
     *
     * @return True if the snow golem has a Pumpkin on his head.
     */
    boolean hasPumpkin();

    /**
     * Sets whether the snow golem has a pumpkin on his head.
     *
     * @param flag {@code true} to set a pumpkin on the snow golem's head, otherwise {@code false}.
     */
    void setHasPumpkin(boolean flag);

}
