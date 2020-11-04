package org.loomdev.api.event.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Fired when a player sends a chat message in the server chat.
 * The message will not show up in chat if this event it cancelled.
 */
public class PlayerChatEvent extends PlayerEvent implements Cancellable {

    private TextComponent prefix, message, suffix;
    private Collection<? extends Player> recipients;
    private boolean cancelled;

    public PlayerChatEvent(Player player, String message, Collection<? extends Player> recipients) {
        super(player);
        this.prefix = Component.text()
                .append(Component.text("<"))
                .append(player.getDisplayName())
                .append(Component.text("> "))
                .build();
        this.message = Component.text(message);
        this.recipients = recipients;
    }

    @Nullable
    public Component getPrefix() {
        return prefix;
    }

    public void setPrefix(@NotNull TextComponent prefix) {
        this.prefix = prefix;
    }

    @Nullable
    public Component getMessage() {
        return message;
    }

    public void setMessage(@NotNull TextComponent message) {
        this.message = message;
    }

    @Nullable
    public Component getSuffix() {
        return suffix;
    }

    public void setSuffix(@NotNull TextComponent suffix) {
        this.suffix = suffix;
    }

    public Collection<? extends Player> getRecipients() {
        return this.recipients;
    }

    public void setRecipients(@NotNull Set<Player> recipients) {
        this.recipients = recipients;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
