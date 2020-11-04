package org.loomdev.api.event.block.plant;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.Entity;
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
public class PlantFertilizeEvent extends BlockEvent implements Cancellable {

    private Entity entity;
    private Block dispenser;
    private final Cause cause;
    private boolean cancelled;

    public PlantFertilizeEvent(Block block, Entity entity) {
        super(block);
        this.entity = entity;
        this.cause = Cause.PLAYER;
    }

    public PlantFertilizeEvent(Block block, Block dispenser) {
        super(block);
        this.dispenser = dispenser;
        this.cause = Cause.DISPENSER;
    }

    @Nullable // TODO possibly split into two events
    public Entity getEntity() {
        return entity;
    }

    @NotNull
    public Cause getCause() {
        return cause;
    }

    @Nullable
    public Block getDispenser() {
        return dispenser;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
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
