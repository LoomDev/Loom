package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;

/**
 * Fired when a player destroys a block in the world.
 * The block will not break if this event is cancelled.
 */
public class BlockBrokenEvent extends BlockEvent implements Cancellable {

    private final Player player;
    private boolean dropsItems;
    private boolean cancelled;

    public BlockBrokenEvent(@NotNull Block block, @NotNull Player player) {
        super(block);
        this.player = player;
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }

    public boolean dropsItems() {
        return this.dropsItems;
    }

    public void setDropsItems(boolean dropsItems) {
        this.dropsItems = dropsItems;
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
