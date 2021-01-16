package org.loomdev.api.event.server.connection;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Event;

public interface ConnectionEvent extends Event {

    @NotNull
    Player getPlayer();

    interface Join extends ConnectionEvent {

        @NotNull
        Component getJoinMessage();

        void setJoinMessage(@NotNull Component component);
    }

    interface Disconnect extends ConnectionEvent {

        @NotNull
        Component getDisconnectMessage();

        void setDisconnectMessage(@NotNull Component component);
    }
}
