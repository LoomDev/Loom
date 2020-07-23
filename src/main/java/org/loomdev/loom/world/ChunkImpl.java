package org.loomdev.loom.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.World;

public class ChunkImpl implements Chunk {

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public long getKey() {
        return 0;
    }

    @Override
    public @NotNull World getWorld() {
        return null;
    }

    @Override
    public @NotNull Block getBlock(int i, int i1, int i2) {
        return null;
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public long getInhabitedTime() {
        return 0;
    }

    @Override
    public void setInhabitedTime(long l) {

    }
}
