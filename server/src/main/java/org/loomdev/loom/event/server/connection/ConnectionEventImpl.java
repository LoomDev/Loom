package org.loomdev.loom.event.server.connection;

import net.kyori.adventure.text.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.server.connection.ConnectionEvent;
import org.loomdev.loom.event.EventImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

public class ConnectionEventImpl extends EventImpl implements ConnectionEvent {

    private final Player player;

    public ConnectionEventImpl(Player player) {
        this.player = player;
    }

    @Override
    @NotNull
    public Player getPlayer() {
        return player;
    }

    public static class JoinImpl extends ConnectionEventImpl implements ConnectionEvent.Join {

        private Component message;

        public JoinImpl(ServerPlayer player, net.minecraft.network.chat.Component component) {
            super((Player) player.getLoomEntity());
            this.message = TextTransformer.toLoom(component);
        }

        @Override
        @NotNull
        public Component getJoinMessage() {
            return message;
        }

        @Override
        public void setJoinMessage(@NotNull Component component) {
            this.message = component;
        }
    }

    public static class DisconnectImpl extends ConnectionEventImpl implements ConnectionEvent.Disconnect {

        private Component message;

        public DisconnectImpl(ServerPlayer player, net.minecraft.network.chat.Component component) {
            super((Player) player.getLoomEntity());
            this.message = TextTransformer.toLoom(component);
        }

        @Override
        @NotNull
        public Component getDisconnectMessage() {
            return message;
        }

        @Override
        public void setDisconnectMessage(@NotNull Component component) {
            this.message = component;
        }
    }
}
