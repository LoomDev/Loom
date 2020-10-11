package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;

/**
 * Fired when a player places a block in the world.
 * The block will not get placed if this event is cancelled.
 */
public class BlockPlacedEvent extends BlockEvent implements Cancellable {

    private final Player player;
    private boolean cancelled;

    public BlockPlacedEvent(@NotNull Block block, @NotNull Player player) {
        super(block);
        this.player = player;
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
