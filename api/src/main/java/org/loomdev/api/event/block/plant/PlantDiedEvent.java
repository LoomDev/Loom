package org.loomdev.api.event.block.plant;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

/**
 * Fired when a block that is categorized as a plant dies or changes state.
 * The plant will not die if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>Coral-type blocks lack water and die</li>
 * </ul>
 */
public class PlantDiedEvent extends BlockEvent implements Cancellable {

    private boolean cancelled;

    public PlantDiedEvent(@NotNull Block block) {
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
