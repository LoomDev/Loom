package org.loomdev.api.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;

public interface Chunk {

    int getX();

    int getZ();

    long getKey();

    @NotNull World getWorld();

    @NotNull Block getBlock(int x, int y, int z);

    boolean isLoaded();

    long getInhabitedTime();

    void setInhabitedTime(long ticks);
}
