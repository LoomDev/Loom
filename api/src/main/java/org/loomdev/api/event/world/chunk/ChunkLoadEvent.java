package org.loomdev.api.event.world.chunk;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.world.Chunk;

public class ChunkLoadEvent extends ChunkEvent implements Cancellable {

    private boolean cancelled;

    public ChunkLoadEvent(@NotNull Chunk chunk) {
        super(chunk);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
