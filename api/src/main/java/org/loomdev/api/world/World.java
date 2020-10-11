package org.loomdev.api.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.particle.Particle;
import org.loomdev.api.sound.Sound;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface World {

    @NotNull String getName();

    @NotNull UUID getUUID();

    @NotNull Block getBlock(int x, int y, int z);

    @NotNull Block getBlock(@NotNull Location location);

    @NotNull Chunk getChunk(int x, int z);

    boolean isChunkGenerated(int x, int z);

    boolean isChunkGenerated(@NotNull Chunk chunk);

    boolean isChunkLoaded(int x, int z);

    boolean isChunkLoaded(@NotNull Chunk chunk);

    @NotNull <T extends Entity> Optional<T> spawnEntity(@NotNull EntityType<T> type, @NotNull Location location);

    void spawnParticle(@NotNull Particle particle, @NotNull Location location);

    void playSound(@NotNull Sound sound, @NotNull Location location);

    @NotNull Collection<? extends Player> getPlayers();

    long getTime();

    void setTime(long ticks);

    boolean isDay();

    boolean isNight();

    long getAbsoluteTime();

    void setAbsoluteTime(long ticks);
}
