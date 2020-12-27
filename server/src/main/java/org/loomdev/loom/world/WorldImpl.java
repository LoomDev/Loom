package org.loomdev.loom.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.math.vector.Vector3i;
import org.loomdev.api.particle.Particle;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockPointerImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.event.LoomEventDispatcher;
import org.loomdev.loom.util.transformer.ParticleTransformer;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldImpl implements World {

    private final Level level;
    private final NamespacedKey namespacedKey;

    public WorldImpl(@NotNull Level level) {
        this.level = level;
        var location = level.dimension().location();
        this.namespacedKey = new NamespacedKey(location.getNamespace(), location.getPath());
    }

    @NotNull
    public Level getMinecraftWorld() {
        return level;
    }

    @Override
    @NotNull
    public NamespacedKey getDimension() {
        return namespacedKey;
    }

    @Override
    @NotNull
    public String getName() {
        return ((ServerLevel) getMinecraftWorld()).serverLevelData.getLevelName();
    }

    @Override
    @NotNull
    public BlockPointer getBlock(Vector3i position) {
        return getBlock(position.getX(), position.getY(), position.getZ());
    }

    @Override
    @NotNull
    public BlockPointer getBlock(int x, int y, int z) {
        return new BlockPointerImpl(getMinecraftWorld(), new BlockPos(x, y, z));
    }

    @Override
    @NotNull
    public Chunk getChunk(int x, int z) {
        return getMinecraftWorld().getChunk(x, z).getLoomChunk();
    }

    @Override
    @NotNull
    public Chunk getChunk(Vector3i chunkpos) {
        return getChunk(chunkpos.getX(), chunkpos.getZ());
    }

    @Override
    @NotNull
    public <T extends Entity> Optional<T> spawnEntity(@NotNull EntityType<T> type, @NotNull Location location) {
        return Optional.ofNullable(Registry.ENTITY_TYPE.get(new ResourceLocation(type.getKey().toString())).create(getMinecraftWorld()))
                .map(entity -> {
                    entity.moveTo(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
                    getMinecraftWorld().addFreshEntity(entity);
                    return (T) entity.getLoomEntity();
                });
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location) {
        ((ServerLevel) getMinecraftWorld()).sendParticles(
                ParticleTransformer.toMinecraft(particle),
                location.getX(),
                location.getY(),
                location.getZ(),
                particle.getAmount(),
                particle.getOffsetX(),
                particle.getOffsetY(),
                particle.getOffsetZ(),
                particle.getExtraData()
        );
    }

    @Override
    public void playSound(@NotNull Sound sound, @NotNull Location location) {
        var pos = new BlockPos(location.getX(), location.getY(), location.getZ());
        getMinecraftWorld().playSound(
                null,
                pos,
                Registry.SOUND_EVENT.get(new ResourceLocation(sound.getSoundEffect().getKey().toString())),
                SoundSource.getByName(sound.getSoundCategory().getName()),
                sound.getVolume(),
                sound.getPitch()
        );
    }

    @Override
    @NotNull
    public Stream<Player> getPlayers() {
        return getMinecraftWorld().players().stream()
                .map(e -> (PlayerImpl) e.getLoomEntity());
    }

    @Override
    public long getTime() {
        long time = getAbsoluteTime() % 24000;
        if (time < 0) {
            time += 24000;
        }
        return time;
    }

    @Override
    public void setTime(long ticks) {
        long difference = (ticks - getAbsoluteTime()) % 24000;
        if (difference < 0) {
            difference += 24000;
        }

        setAbsoluteTime(getAbsoluteTime() + difference);
    }

    @Override
    public boolean isDay() {
        return getMinecraftWorld().isDay();
    }

    @Override
    public boolean isNight() {
        return getMinecraftWorld().isNight();
    }

    @Override
    public long getAbsoluteTime() {
        return getMinecraftWorld().getGameTime();
    }

    @Override
    public void setAbsoluteTime(long ticks) {
        var event = LoomEventDispatcher.onWorldTimeSkip(getMinecraftWorld(), ticks - getAbsoluteTime());

        if (event.isCanceled()) {
            return;
        }

        ((ServerLevel) getMinecraftWorld()).setDayTime(getAbsoluteTime() + event.getSkippedTicks());

        getPlayers()
                .filter(Player::isConnected)
                .map(PlayerImpl.class::cast)
                .forEach(player -> {
                    Level level = ((WorldImpl) player.getWorld()).getMinecraftWorld();
                    player.getMinecraftEntity().connection.send(new ClientboundSetTimePacket(
                            level.getGameTime(),
                            level.getDayTime(),
                            level.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)
                    ));
                });
    }
}
