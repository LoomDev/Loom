package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;

import java.util.Optional;

/**
 * Fired when a block is placed in an ultra warm (Nether) dimension and changes state.
 * The block will not change state if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>A player attempts to use a water bucket in an ultra warm dimension</li>
 * <li>A sponge dries out when a player places it in an ultra warm dimension</li>
 * <li>A player breaks an ice block in an ultra warn dimension</li>
 * <li>An ice block disappears when melting in an ultra warm dimension</li>
 * </ul>
 */
public class BlockEvaporatedEvent extends BlockEvent implements Cancellable {

    private final Player player;
    private boolean cancelled;

    public BlockEvaporatedEvent(@NotNull Block block, @Nullable Player player) {
        super(block);
        this.player = player;
    }

    public @NotNull Optional<Player> getPlayer() {
        return Optional.ofNullable(this.player);
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
