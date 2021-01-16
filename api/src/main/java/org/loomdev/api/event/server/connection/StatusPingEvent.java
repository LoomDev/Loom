package org.loomdev.api.event.server.connection;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.Event;

import java.net.InetAddress;

public interface StatusPingEvent extends Event, Cancelable {

    @NotNull
    InetAddress getClientAddress();

    // TODO The stuff below should probably be converted into a
    // ServerStatus class, or something similar.

    int getProtocolVersion();

    int setProtocolVersion(int version);

    @NotNull
    String getVersionName();

    void setVersionName(@NotNull String name);

    int getOnlinePlayers();

    void setOnlinePlayers(int online);

    int getPlayerSlots();

    void setPlayerSlots(int slots);

    @NotNull
    Component getMotd();

    void setMotd(@NotNull Component motd);

    @NotNull
    String getFavicon();

    void setFavicon(@NotNull String favicon);
}
