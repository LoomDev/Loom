package org.loomdev.api.event.connection;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Event;

import java.net.InetAddress;

// TODO Hoverable GameProfile sample. Implement once we have a GameProfile API?
public class StatusPingEvent extends Event {

    private final InetAddress clientAddress;
    private int protocolVersion;
    private String versionName;
    private int onlinePlayers;
    private int playerSlots;
    private Component motd;
    private String favicon;

    public StatusPingEvent(InetAddress clientAddress, int protocolVersion, String versionName, int onlinePlayers, int playerSlots, Component motd, String favicon) {
        this.clientAddress = clientAddress;
        this.protocolVersion = protocolVersion;
        this.versionName = versionName;
        this.onlinePlayers = onlinePlayers;
        this.playerSlots = playerSlots;
        this.motd = motd;
        this.favicon = favicon;
    }

    @NotNull
    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @NotNull
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(@NotNull String versionName) {
        this.versionName = versionName;
    }

    public int getOnlinePlayers() {
        return onlinePlayers;
    }

    public void setOnlinePlayers(int onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }

    public int getPlayerSlots() {
        return playerSlots;
    }

    public void setPlayerSlots(int playerSlots) {
        this.playerSlots = playerSlots;
    }

    @NotNull
    public Component getMotd() {
        return motd;
    }

    public void setMotd(@NotNull Component motd) {
        this.motd = motd;
    }

    @NotNull
    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(@NotNull String favicon) {
        this.favicon = favicon;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
