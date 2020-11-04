package org.loomdev.api.event.player.connection;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.player.PlayerEvent;

import java.net.InetAddress;

public class PlayerLoginEvent extends PlayerEvent implements Cancellable {

    private final InetAddress address;
    private final String hostname;
    private boolean cancelled;

    public PlayerLoginEvent(Player player, InetAddress address, String hostname) {
        super(player);
        this.address = address;
        this.hostname = hostname;
    }

    @NotNull
    public InetAddress getAddress() {
        return this.address;
    }

    @NotNull
    public String getHostname() {
        return this.hostname;
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
