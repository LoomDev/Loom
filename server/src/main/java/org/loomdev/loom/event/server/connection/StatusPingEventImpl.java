package org.loomdev.loom.event.server.connection;

import net.kyori.adventure.text.Component;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.status.ServerStatus;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.server.connection.StatusPingEvent;
import org.loomdev.loom.event.EventImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class StatusPingEventImpl extends EventImpl implements StatusPingEvent {

    private final Connection connection;
    private final ServerStatus serverStatus;
    private boolean canceled;

    public StatusPingEventImpl(Connection connection, ServerStatus serverStatus) {
        this.connection = connection;
        this.serverStatus = serverStatus;
    }

    @Override
    @NotNull
    public InetAddress getClientAddress() {
        return ((InetSocketAddress) connection.getRemoteAddress()).getAddress();
    }

    @Override
    public int getProtocolVersion() {
        return serverStatus.getVersion().getProtocol();
    }

    @Override
    public int setProtocolVersion(int version) {
        return serverStatus.getVersion().protocol = version;
    }

    @Override
    @NotNull
    public String getVersionName() {
        return serverStatus.getVersion().getName();
    }

    @Override
    public void setVersionName(@NotNull String name) {
        serverStatus.getVersion().name = name;
    }

    @Override
    public int getOnlinePlayers() {
        return serverStatus.getPlayers().getNumPlayers();
    }

    @Override
    public void setOnlinePlayers(int online) {
        serverStatus.getPlayers().numPlayers = online;
    }

    @Override
    public int getPlayerSlots() {
        return serverStatus.getPlayers().getMaxPlayers();
    }

    @Override
    public void setPlayerSlots(int slots) {
        serverStatus.getPlayers().maxPlayers = slots;
    }

    @Override
    @NotNull
    public Component getMotd() {
        return TextTransformer.toLoom(serverStatus.getDescription());
    }

    @Override
    public void setMotd(@NotNull Component motd) {
        serverStatus.setDescription(TextTransformer.toMinecraft(motd));
    }

    @Override
    @NotNull
    public String getFavicon() {
        return serverStatus.getFavicon();
    }

    @Override
    public void setFavicon(@NotNull String favicon) {
        serverStatus.setFavicon(favicon);
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public ServerStatus getMinecraftStatus() {
        return serverStatus;
    }
}
