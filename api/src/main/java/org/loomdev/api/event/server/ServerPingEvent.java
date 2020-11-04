package org.loomdev.api.event.server;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.Event;

import java.net.InetAddress;
import java.util.Optional;

public class ServerPingEvent implements Event, Cancellable {

    private final InetAddress address;
    private int protocolVersion;
    private String versionName;
    private Component motd;
    private int onlinePlayers;
    private int slots;
    private String favicon;
    private boolean cancelled;

    public ServerPingEvent(InetAddress address, int protocolVersion, String versionName, Component motd, int onlinePlayers, int slots, @Nullable String favicon) {
        this.address = address;
        this.protocolVersion = protocolVersion;
        this.versionName = versionName;
        this.motd = motd;
        this.onlinePlayers = onlinePlayers;
        this.slots = slots;
        this.favicon = favicon;
    }

    @NotNull
    public InetAddress getAddress() {
        return this.address;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getVersionName() {
        return versionName;
    }

    public void getVersionName(@NotNull String versionName) {
        this.versionName = versionName;
    }

    @NotNull
    public Component getMotd() {
        return motd;
    }

    public void setMotd(@NotNull Component motd) {
        this.motd = motd;
    }

    public int getOnlinePlayers() {
        return onlinePlayers;
    }

    public void setOnlinePlayers(int onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    @Nullable
    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(@Nullable String favicon) {
        this.favicon = favicon;
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
