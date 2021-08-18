package org.loomdev.loom.network;

import net.kyori.adventure.text.Component;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.network.PlayerConnection;
import org.loomdev.loom.transformer.Transformer;

import java.net.InetSocketAddress;

public class PlayerConnectionImpl implements PlayerConnection {

    private final ServerGamePacketListenerImpl mcConnection;

    public PlayerConnectionImpl(@NotNull ServerGamePacketListenerImpl mcConnection) {
        this.mcConnection = mcConnection;
    }

    @Override
    public boolean isConnected() {
        return getMinecraftConnection().getConnection().isConnected();
    }

    @Override
    public int getLatency() {
        return isConnected()
                ? getMinecraftConnection().getPlayer().latency
                : -1;
    }

    @Override
    @NotNull
    public InetSocketAddress getRemoteAddress() {
        return (InetSocketAddress) getMinecraftConnection().getConnection().getRemoteAddress();
    }

    @Override
    public void disconnect(@NotNull Component reason) {
        var component = Transformer.COMPONENT.toMinecraft(reason);
        getMinecraftConnection().disconnect(component);
    }

    @NotNull
    public ServerGamePacketListenerImpl getMinecraftConnection() {
        return mcConnection;
    }
}
