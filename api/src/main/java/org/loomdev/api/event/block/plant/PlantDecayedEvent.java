package org.loomdev.api.event.block.plant;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

/**
 * Fired when a block that is categorized as a plant decays or changes state
 * into an empty air block. The plant will not decay if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>Leaves decay when a tree is broken</li>
 * </ul>
 */
public class PlantDecayedEvent extends BlockEvent implements Cancellable {

    private boolean cancelled;

    public PlantDecayedEvent(@NotNull Block block) {
        super(block);
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
