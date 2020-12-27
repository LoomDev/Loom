package org.loomdev.api.entity.vehicle.minecart;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.entity.Entity;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Represents any type of minecart entity.
 */
public interface Minecart extends Entity {

    /**
     * Gets the {@link BlockState} displayed in the minecart.
     *
     * @return The {@link BlockState} displayed in the minecart or null if empty.
     */
    @NotNull
    Optional<BlockState> getDisplayedBlock();

    /**
     * Sets the {@link BlockState} displayed in the minecart.
     *
     * @param blockState The new {@link BlockState} displayed in the minecart or null if empty.
     */
    void setDisplayedBlock(@Nullable BlockState blockState);

    /**
     * Gets the offset of the {@link BlockState} displayed in the minecart.
     *
     * @return The offset of the {@link BlockState} displayed in the minecart.
     */
    int getDisplayedBlockOffset();

    /**
     * Sets the offset of the {@link BlockState} displayed in the minecart.
     *
     * @param offset The new offset of the {@link BlockState} displayed in the minecart.
     */
    void setDisplayedBlockOffset(int offset);

}
