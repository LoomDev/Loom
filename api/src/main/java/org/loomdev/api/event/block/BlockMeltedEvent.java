package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.event.Cancellable;

/**
 * Fired when a block melts and changes state because of surrounding light levels.
 * The block's state will not change if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>An ice block melts due to surrounding light levels</li>
 * <li>A snow block or snow layer melts due to surrounding light levels</li>
 * </ul>
 */
public class BlockMeltedEvent extends BlockEvent implements Cancellable {

    private final Cause cause;
    private boolean cancelled;

    public BlockMeltedEvent(@NotNull Block block, @NotNull Cause cause) {
        super(block);
        this.cause = cause;
    }

    public @NotNull Cause getCause() {
        return cause;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public enum Cause {

    }
}
