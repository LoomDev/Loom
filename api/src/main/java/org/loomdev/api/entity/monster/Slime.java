package org.loomdev.api.entity.monster;

public interface Slime extends MobEntity {

    /**
     * Get the size of the slime entity.
     * @return The size of the slime entity.
     */
    int getSize();

    /**
     * Set the size of the slime entity.
     * @param size The new size of the slime entity.
     */
    void setSize(int size);
}
