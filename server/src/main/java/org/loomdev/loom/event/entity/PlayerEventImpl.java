package org.loomdev.loom.event.entity;

import net.kyori.adventure.text.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.entity.PlayerEvent;
import org.loomdev.loom.event.EventImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

public class PlayerEventImpl extends EventImpl implements PlayerEvent {

    private final Player player;

    public PlayerEventImpl(Player player) {
        this.player = player;
    }

    @Override
    @NotNull
    public Player getPlayer() {
        return player;
    }

    public static class ChatImpl extends PlayerEventImpl implements PlayerEvent.Chat {

        private Component message;
        private boolean canceled;

        public ChatImpl(ServerPlayer player, net.minecraft.network.chat.Component message) {
            super((Player) player.getLoomEntity());
            this.message = TextTransformer.toLoom(message);
        }

        @Override
        @NotNull
        public Component getMessage() {
            return message;
        }

        @Override
        public void setMessage(@NotNull Component component) {
            this.message = component;
        }

        @Override
        public boolean isCanceled() {
            return canceled;
        }

        @Override
        public void setCanceled(boolean canceled) {
            this.canceled = canceled;
        }
    }

    public static class JoinImpl extends PlayerEventImpl implements PlayerEvent.Join {

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

    public static class DisconnectImpl extends PlayerEventImpl implements PlayerEvent.Disconnect {

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
