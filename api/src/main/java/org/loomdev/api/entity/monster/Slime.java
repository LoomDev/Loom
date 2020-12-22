package org.loomdev.api.entity.monster;

public interface Slime extends MobEntity {

    /**
     * Gets the size of the slime.
     * @return The size of the slime.
     */
    int getSize();

    /**
     * Sets the size of the slime.
     * @param size The new size of the slime.
     */
    void setSize(int size);
}
