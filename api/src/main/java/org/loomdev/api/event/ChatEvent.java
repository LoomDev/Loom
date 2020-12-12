package org.loomdev.api.event;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class ChatEvent extends Event {

    public static class Entity extends ChatEvent {

        private final EventCause sender;
        private Component message;

        public Entity(EventCause sender, Component message) {
            this.sender = sender;
            this.message = message;
        }

        @NotNull
        public EventCause getSender() {
            return sender;
        }

        @NotNull
        public Component getMessage() {
            return message;
        }

        public void setMessage(@NotNull Component message) {
            this.message = message;
        }
    }

    // TODO command blocks (blocks), console, and rcon are also able to send messages
}
