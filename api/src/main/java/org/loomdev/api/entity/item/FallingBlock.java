package org.loomdev.api.entity.item;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.entity.Entity;

/**
 * Represents a falling block entity.
 */
public interface FallingBlock extends Entity {

    @NotNull BlockState getBlock();

    void setBlock(@NotNull BlockState block);

    boolean getDropsItem();

    void setDropsItem(boolean flag);
}
