package org.loomdev.api.event.block.sign;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

/**
 * Fired when a player finishes writing text on a newly placed sign.
 * The sign will remain blank if this event is cancelled.
 */
public class SignWrittenEvent extends BlockEvent implements Cancellable {

    private final Player player;
    private final String[] text;
    private boolean cancelled;

    public SignWrittenEvent(@NotNull Block sign, @NotNull Player player, @NotNull String[] text) {
        super(sign);
        this.player = player;
        this.text = text;
    }

    // TODO getSign()

    @NotNull
    public Player getPlayer() {
        return player;
    }

    @NotNull
    public String[] getText() {
        return text;
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
