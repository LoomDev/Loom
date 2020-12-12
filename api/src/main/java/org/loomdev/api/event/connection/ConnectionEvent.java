package org.loomdev.api.event.connection;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Event;

public class ConnectionEvent extends Event {

    // TODO login and maybe authenticate

    public static class Join extends ConnectionEvent {

        private final Player player;
        private Component joinMessage;

        public Join(Player player, Component joinMessage) {
            this.player = player;
            this.joinMessage = joinMessage;
        }

        @NotNull
        public Player getPlayer() {
            return player;
        }

        @NotNull
        public Component getJoinMessage() {
            return joinMessage;
        }

        public void setJoinMessage(@NotNull Component joinMessage) {
            this.joinMessage = joinMessage;
        }
    }

    public static class Disconnect extends ConnectionEvent {

        private final Player player;
        private Component disconnectMessage;

        public Disconnect(Player player, Component disconnectMessage) {
            this.player = player;
            this.disconnectMessage = disconnectMessage;
        }

        @NotNull
        public Player getPlayer() {
            return player;
        }

        @NotNull
        public Component getDisconnectMessage() {
            return disconnectMessage;
        }

        public void setDisconnectMessage(@NotNull Component disconnectMessage) {
            this.disconnectMessage = disconnectMessage;
        }
    }
}
