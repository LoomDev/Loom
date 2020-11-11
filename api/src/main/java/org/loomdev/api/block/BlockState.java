package org.loomdev.api.block;

import org.jetbrains.annotations.NotNull;

public interface BlockState {

    @NotNull
    BlockType getBlockType();

    boolean isAir();

    boolean hasBlockEntity();
}
