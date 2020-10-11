package org.loomdev.api.event.block.plant;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

/**
 * Fired when a block that is categorized as a plant grows or changes state.
 * The plant will not advance growth if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>Crop blocks grow</li>
 * <li>Blocks with stems grow (i.e. pumpkins, melons..)</li>
 * <li>Chorus plants grow taller</li>
 * <li>Sweet berry bushes grow more berries</li>
 * <li>Turtle eggs advance growth stages</li>
 * </ul>
 */
public class PlantGrewEvent extends BlockEvent implements Cancellable {

    private final BlockState updatedState;
    private boolean cancelled;

    public PlantGrewEvent(@NotNull Block block, @NotNull BlockState updatedState) {
        super(block);
        this.updatedState = updatedState;
    }

    public @NotNull BlockState getUpdatedState() {
        return this.updatedState;
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
