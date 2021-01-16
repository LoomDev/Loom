package org.loomdev.api.event.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.monster.Creeper;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventCause;

public interface CreeperEvent extends Event {

    @NotNull
    Creeper getCreeper();

    interface Ignite extends CreeperEvent, Cancelable {
    }

    interface Charge extends CreeperEvent, Cancelable {

        @NotNull
        EventCause getCause();
    }
}
