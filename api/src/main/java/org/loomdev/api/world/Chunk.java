package org.loomdev.api.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.math.vector.Vector3i;

public interface Chunk {

    @NotNull
    World getWorld();

    @NotNull
    BlockPointer getBlock(int x, int y, int z);

    @NotNull
    Vector3i getPosition();

    @NotNull
    ChunkState getChunkState();

    void setChunkState(@NotNull ChunkState state);

    boolean isLoaded();

    long getInhabitedTime();

    void setInhabitedTime(long ticks);

    enum ChunkState {

        INACCESSIBLE,
        BORDER,
        TICKING,
        ENTITY_TICKING;

        public boolean isOrAfter(ChunkState state) {
            return ordinal() >= state.ordinal();
        }
    }
}
