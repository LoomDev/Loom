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

        /**
         * Gets the chat message's content.
         *
         * @return The content.
         */
        @NotNull
        Component getMessage();

        /**
         * Sets the chat message's content.
         * @param message The content.
         */
        void setMessage(@NotNull Component message);

        /**
         * Gets the chat message's format.
         *
         * <p>Example usage:</p>
         * <p><code>Component formattedMessage = event.getFormat().apply(event.getPlayer().getDisplayName(), event.getMessage());</code></p>
         *
         * @return The format.
         */
        @NotNull
        Format getFormat();

        /**
         * Sets the chat message's format.
         *
         * <p>Example usage:</p>
         * <p><code>event.setFormat((name, message) -&gt; Component.text().append(name).append(Component.text(": ")).append(message).build());</code></p>
         *
         * @param format The format.
         */
        void setFormat(@NotNull Format format);

        /**
         * Represents a chat message format.
         */
        @FunctionalInterface
        interface Format {

            /**
             * Formats a chat message.
             *
             * @param name The display name of the sender.
             * @param message The sent message.
             * @return The formatted message.
             */
            public Component apply(Component name, Component message);
        }
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
