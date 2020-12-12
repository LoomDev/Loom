package org.loomdev.api.event.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.monster.Creeper;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventCause;

public class CreeperEvent extends Event {

    private final Creeper creeper;

    public CreeperEvent(Creeper creeper) {
        this.creeper = creeper;
    }

    @NotNull
    public Creeper getCreeper() {
        return creeper;
    }

    public static class Ignite extends CreeperEvent {

        public Ignite(Creeper creeper) {
            super(creeper);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public static class Power extends CreeperEvent {

        private final EventCause cause;

        public Power(EventCause cause, Creeper creeper) {
            super(creeper);
            this.cause = cause;
        }

        @NotNull
        public EventCause getCause() {
            return cause;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
}
