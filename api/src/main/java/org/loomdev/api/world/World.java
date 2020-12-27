package org.loomdev.api.world;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.math.vector.Vector3i;
import org.loomdev.api.particle.Particle;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.util.NamespacedKey;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface World { // TODO rename to Level???

    @NotNull
    String getName();

    @NotNull
    NamespacedKey getDimension();

    @NotNull
    BlockPointer getBlock(Vector3i position);

    @NotNull
    BlockPointer getBlock(int x, int y, int z);

    @NotNull
    Chunk getChunk(Vector3i chunkpos);

    @NotNull
    Chunk getChunk(int x, int z);

    @NotNull
    <T extends Entity> Optional<T> spawnEntity(@NotNull EntityType<T> type, @NotNull Location location);

    void spawnParticle(@NotNull Particle particle, @NotNull Location location);

    void playSound(@NotNull Sound sound, @NotNull Location location);

    @NotNull
    Stream<Player> getPlayers();

    long getTime();

    void setTime(long ticks);

    boolean isDay();

    boolean isNight();

    long getAbsoluteTime();

    void setAbsoluteTime(long ticks);

    enum UpdateType {

        NOTIFY,
        OBSERVER_IGNORED,
        NO_PLACE // TODO figure out all possible update types
    }
}
