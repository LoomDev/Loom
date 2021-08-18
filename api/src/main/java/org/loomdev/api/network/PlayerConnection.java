package org.loomdev.api.network;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;

import java.net.InetSocketAddress;

/**
 * Represents an online {@link Player}'s connection to the server.
 * <p>
 * Using this connection object, client-sided game updates may be sent to the
 * connected player.
 *
 * @since 1.17.1
 */
public interface PlayerConnection {

    boolean isConnected();

    int getLatency();

    @NotNull
    InetSocketAddress getRemoteAddress();

    void disconnect(@NotNull Component reason);
}
