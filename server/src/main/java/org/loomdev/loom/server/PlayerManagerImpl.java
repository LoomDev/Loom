package org.loomdev.loom.server;

import net.kyori.adventure.text.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.server.PlayerManager;
import org.loomdev.loom.entity.player.PlayerImpl;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerManagerImpl implements PlayerManager {

    private final ServerImpl server;

    public PlayerManagerImpl(ServerImpl server) {
        this.server = server;
    }

    @Override
    @NotNull
    public Stream<Player> getOnlinePlayers() {
        return server.getMinecraftServer().getPlayerList().getPlayers().stream()
                .map(Entity::getLoomEntity)
                .map(Player.class::cast);
    }

    @Override
    public void broadcastMessage(@NotNull String message) {
        this.broadcastMessage(Component.text(message));
    }

    @Override
    public void broadcastMessage(@NotNull Component component) {
        getOnlinePlayers().forEach(player -> player.sendMessage(component));
    }

    @Override
    public int getOnlinePlayerCount() {
        return server.getMinecraftServer().getPlayerCount();
    }

    @Override
    @NotNull
    public Optional<Player> getPlayer(@NotNull UUID uuid) {
        return Optional.ofNullable(server.getMinecraftServer().getPlayerList().getPlayer(uuid))
                .map(mcPlayer -> ((PlayerImpl)mcPlayer.getLoomEntity()));
    }

    @Override
    @NotNull
    public Optional<Player> getPlayer(@NotNull String username) {
        // TODO (optimisation) replace this with a map<string, player> lookup
        return Optional.ofNullable(server.getMinecraftServer().getPlayerList().getPlayerByName(username))
                .map(mcPlayer -> ((PlayerImpl)mcPlayer.getLoomEntity()));
    }

    @Override
    public boolean isOperator(@NotNull Player player) {
        var mcGameProfile = ((PlayerImpl) player).getMinecraftEntity().getGameProfile();
        return server.getMinecraftServer().getPlayerList().isOp(mcGameProfile);
    }
}
