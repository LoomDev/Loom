package org.loomdev.api.entity.vehicle.minecart;

import org.loomdev.api.block.BlockState;
import org.loomdev.api.entity.Entity;

import javax.annotation.Nullable;

/**
 * Represents any type of minecart entity.
 */
public interface Minecart extends Entity {

    /**
     * Get the {@link BlockState} displayed in the minecart.
     *
     * @return The {@link BlockState} displayed in the minecart or null if empty.
     */
    @Nullable BlockState getDisplayedBlock();

    /**
     * Set the {@link BlockState} displayed in the minecart.
     *
     * @param blockState The new {@link BlockState} displayed in the minecart or null if empty.
     */
    void setDisplayedBlock(@Nullable BlockState blockState);

    /**
     * Get the offset of the {@link BlockState} displayed in the minecart.
     *
     * @return The offset of the {@link BlockState} displayed in the minecart.
     */
    int getDisplayedBlockOffset();

    /**
     * Set the offset of the {@link BlockState} displayed in the minecart.
     *
     * @param offset The new offset of the {@link BlockState} displayed in the minecart.
     */
    void setDisplayedBlockOffset(int offset);

}
