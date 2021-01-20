package org.loomdev.api.event.container;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.container.Container;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.Event;

public interface ContainerEvent extends Event {

    @NotNull
    Container getContainer();

    interface Open extends ContainerEvent, Cancelable {
    }
}
