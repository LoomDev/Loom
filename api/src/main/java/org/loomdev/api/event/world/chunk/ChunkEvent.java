package org.loomdev.api.event.world.chunk;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.world.WorldEvent;
import org.loomdev.api.world.Chunk;

public class ChunkEvent extends WorldEvent implements Event {

    private final Chunk chunk;

    public ChunkEvent(@NotNull Chunk chunk) {
        super(chunk.getWorld());
        this.chunk = chunk;
    }

    public @NotNull Chunk getChunk() {
        return chunk;
    }
}
