package org.loomdev.api.event.entity;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.Event;

public interface PlayerEvent extends Event {

    @NotNull
    Player getPlayer();

    interface Chat extends PlayerEvent, Cancelable {

        @NotNull
        Component getMessage();

        void setMessage(@NotNull Component component);
    }

    interface Join extends PlayerEvent {

        @NotNull
        Component getJoinMessage();

        void setJoinMessage(@NotNull Component component);
    }

    interface Disconnect extends PlayerEvent {

        @NotNull
        Component getDisconnectMessage();

        void setDisconnectMessage(@NotNull Component component);
    }
}