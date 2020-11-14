package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.EventCause;
import org.loomdev.api.event.block.BlockEvent;

/**
 * Fired when a block is added to the world by an interaction.
 * The block will not change state if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>A player places a block in the world</li>
 * <li>An Enderman leaves its block when mob griefing is enabled</li>
 * </ul>
 */
public class BlockPlaceEvent extends BlockEvent implements Cancellable {

    private final EventCause cause;
    private final BlockState newState;
    private boolean cancelled;

    public BlockPlaceEvent(EventCause cause, BlockPointer block, BlockState newState) {
        super(block);
        this.cause = cause;
        this.newState = newState;
    }

    @NotNull
    public EventCause getCause() {
        return cause;
    }

    @NotNull
    public BlockState getNewState() {
        return newState;
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
