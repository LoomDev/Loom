package org.loomdev.api.event.entity.creeper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.misc.LightningBolt;
import org.loomdev.api.entity.monster.Creeper;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.entity.EntityEvent;

/**
 * Fired when a creeper is struck by lightning and is about to become charged.
 * The creeper will not become charged will if this event is cancelled.
 */
public class CreeperChargeEvent extends EntityEvent implements Cancellable { // TODO generic entity charge?

    private LightningBolt lightning;
    private final Cause cause;
    private boolean cancelled;

    public CreeperChargeEvent(Creeper creeper) {
        super(creeper);
        this.cause = Cause.TRIGGERED;
    }

    public CreeperChargeEvent(Creeper creeper, LightningBolt lightning) {
        super(creeper);
        this.lightning = lightning;
        this.cause = Cause.LIGHTNING;
    }

    @NotNull
    public Creeper getCreeper() {
        return (Creeper) this.getEntity();
    }

    @Nullable
    public LightningBolt getLightning() {
        return lightning;
    }

    public Cause getCause() {
        return this.cause;
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

        LIGHTNING,
        TRIGGERED
    }
}
