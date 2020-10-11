package org.loomdev.api.event.player.connection;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.player.PlayerEvent;

import java.net.InetAddress;

public class PlayerLoggedInEvent extends PlayerEvent implements Cancellable {

    private final InetAddress address;
    private String hostname;
    private boolean cancelled;

    public PlayerLoggedInEvent(@NotNull Player player, @NotNull InetAddress address, @NotNull String hostname) {
        super(player);
        this.address = address;
        this.hostname = hostname;
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public String getHostname() {
        return this.hostname;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
