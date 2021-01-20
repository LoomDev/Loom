package org.loomdev.api.event.container;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.container.Container;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.player.PlayerEvent;

public class ContainerEvent extends PlayerEvent {

    private final Container container;

    public ContainerEvent(@NotNull Container container, @NotNull Player player) {
        super(player);
        this.container = container;
    }

    @NotNull
    public Container getContainer() {
        return container;
    }

    public static class Open extends ContainerEvent {

        public Open(@NotNull Container container, @NotNull Player player) {
            super(container, player);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
}
