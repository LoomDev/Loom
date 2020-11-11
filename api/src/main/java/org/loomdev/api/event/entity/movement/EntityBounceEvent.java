package org.loomdev.api.event.entity.movement;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.entity.EntityEvent;

/**
 * Fired when an entity bounces on a bounceable block in the world and gains velocity.
 * The entity will not gain any extra velocity if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>An entity lands and successfully bounces on a slime block</li>
 * <li>An entity lands and successfully bounces on a bed block</li>
 * </ul>
 */
public class EntityBounceEvent extends EntityEvent implements Cancellable { // TODO EntityBlockBounceEvent?

    private final BlockPointer block;
    private double multiplier;
    private boolean cancelled;

    public EntityBounceEvent(Entity entity, BlockPointer block, double multiplier) {
        super(entity);
        this.block = block;
        this.multiplier = multiplier;
    }

    @NotNull
    public BlockPointer getBlock() {
        return block;
    }

    public double getBounceMultiplier() {
        return multiplier;
    }

    public void setBounceMultiplier(double multiplier) {
        this.multiplier = multiplier;
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
