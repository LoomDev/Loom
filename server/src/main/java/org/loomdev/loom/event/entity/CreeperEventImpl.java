package org.loomdev.loom.event.entity;

import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.Creeper;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.EventCause;
import org.loomdev.api.event.entity.CreeperEvent;
import org.loomdev.loom.event.EventImpl;

public class CreeperEventImpl extends EventImpl implements CreeperEvent, Cancelable {

    private final Creeper creeper;
    private boolean canceled;

    public CreeperEventImpl(Creeper creeper) {
        this.creeper = creeper;
    }

    @Override
    @NotNull
    public Creeper getCreeper() {
        return creeper;
    }


    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public static class IgniteImpl extends CreeperEventImpl implements CreeperEvent.Ignite {

        public IgniteImpl(net.minecraft.world.entity.monster.Creeper creeper) {
            super((Creeper) creeper.getLoomEntity());
        }
    }

    public static class ChargeImpl extends CreeperEventImpl implements CreeperEvent.Charge {

        private final EventCause cause;

        public ChargeImpl(net.minecraft.world.entity.monster.Creeper creeper, Entity cause) {
            super((Creeper) creeper.getLoomEntity());
            this.cause = new EventCause(cause);
        }

        @Override
        @NotNull
        public EventCause getCause() {
            return cause;
        }
    }
}
