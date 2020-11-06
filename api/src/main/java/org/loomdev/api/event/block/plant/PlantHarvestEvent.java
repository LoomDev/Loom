package org.loomdev.api.event.block.plant;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

/**
 * Fired when a plant block is harvested and reverted back to its
 * initial growth stage. The plant will not drop items and revert to
 * its initial growth stage if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>A player harvests a sweet berry bush</li>
 * <li>A fox harvests a sweet berry bush</li>
 * </ul>
 */
public class PlantHarvestEvent extends BlockEvent implements Cancellable {

    private final Entity entity;
    private final Cause cause;
    private boolean cancelled;

    public PlantHarvestEvent(Block block, Entity entity, Cause cause) {
        super(block);
        this.entity = entity;
        this.cause = cause;
    }

    @NotNull
    public Entity getEntity() {
        return entity;
    }

    @NotNull
    public Cause getCause() {
        return cause;
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
     * Represents the entity that harvested
     * the plant block.
     */
    public enum Cause {

        PLAYER,
        ANIMAL
    }
}
