package org.loomdev.api.event.block.plant;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

import java.util.Optional;

/**
 * Fired when bonemeal is used on a plant block and causes the plant
 * block to advance growth stages. The plant will not advance growth if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>A player right-clicks with bonemeal on a plant block</li>
 * <li>A dispenser fertilizes an adjacent plant block</li>
 * </ul>
 */
public class PlantFertilizedEvent extends BlockEvent implements Cancellable {

    private Player player;
    private Block dispenser;
    private final Cause cause;
    private boolean cancelled;

    public PlantFertilizedEvent(@NotNull Block block, @NotNull Player player) {
        super(block);
        this.player = player;
        this.cause = Cause.PLAYER;
    }

    public PlantFertilizedEvent(@NotNull Block block, @NotNull Block dispenser) {
        super(block);
        this.dispenser = dispenser;
        this.cause = Cause.DISPENSER;
    }

    public @NotNull Cause getCause() {
        return this.cause;
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }

    public @NotNull Optional<Block> getDispenser() {
        return Optional.ofNullable(this.dispenser);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Represents the entity or block that
     * fertilized the plant block.
     */
    public enum Cause {
        PLAYER,
        DISPENSER
    }
}
