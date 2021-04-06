package org.loomdev.loom.event.entity;

import net.kyori.adventure.text.Component;
import net.minecraft.network.protocol.game.ServerboundResourcePackPacket;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.entity.PlayerEvent;
import org.loomdev.loom.event.EventImpl;
import org.loomdev.loom.util.transformer.ResourcePackActionTransformer;
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
        private Format format;

        public ChatImpl(ServerPlayer player, Component message) {
            super((Player) player.getLoomEntity());
            this.message = message;
            this.format = (name, processedMessage) -> Component.translatable("chat.type.text").args(name, processedMessage);
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
        @NotNull
        public Format getFormat() {
            return format;
        }

        @Override
        public void setFormat(@NotNull Format format) {
            this.format = format;
        }

        @Override
        public boolean isCanceled() {
            return canceled;
        }

        @Override
        public void setCanceled(boolean canceled) {
            this.canceled = canceled;
        }

        @NotNull
        public net.minecraft.network.chat.Component getMinecraftComponent() {
            return TextTransformer.toMinecraft(getFormat().apply(getPlayer().getDisplayName(), getMessage()));
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

    public static class ResourcePackStatusImpl extends PlayerEventImpl implements PlayerEvent.ResourcePackStatus {

        private final org.loomdev.api.util.ResourcePackStatus status;
        private boolean required;

        public ResourcePackStatusImpl(ServerPlayer player, ServerboundResourcePackPacket.Action action, boolean resourcePackRequired) {
            super((Player) player.getLoomEntity());
            this.status = ResourcePackActionTransformer.toLoom(action);
            this.required = resourcePackRequired;
        }

        @Override
        @NotNull
        public org.loomdev.api.util.ResourcePackStatus getStatus() {
            return status;
        }

        @Override
        public boolean isRequired() {
            return required;
        }

        @Override
        public void setRequired(boolean required) {
            this.required = required;
        }
    }
}
