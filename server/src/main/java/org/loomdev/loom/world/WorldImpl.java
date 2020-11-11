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
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.world.WorldTimeChangeEvent;
import org.loomdev.api.math.vector.Vector3i;
import org.loomdev.api.particle.Particle;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockPointerImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.event.LoomEventDispatcher;
import org.loomdev.loom.util.transformer.ParticleTransformer;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class WorldImpl implements World {

    private final Level level;

    public WorldImpl(@NotNull Level level) {
        this.level = level;
    }

    @NotNull
    public Level getMinecraftWorld() {
        return level;
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
    @Nullable
    public <T extends Entity> T spawnEntity(@NotNull EntityType<T> type, @NotNull Location location) {
        var mcEntity = Registry.ENTITY_TYPE.get(new ResourceLocation(type.getKey().toString())).create(getMinecraftWorld());
        if (mcEntity == null) {
            return null;
        }

        mcEntity.moveTo(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        getMinecraftWorld().addFreshEntity(mcEntity);
        return (T) mcEntity.getLoomEntity();
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
    public Collection<? extends Player> getPlayers() {
        return this.getMinecraftWorld().players()
                .stream()
                .map(e -> (PlayerImpl) e.getLoomEntity())
                .collect(Collectors.toList());
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
        var event = LoomEventDispatcher.onWorldTimeChanged(this, ticks - getAbsoluteTime(), WorldTimeChangeEvent.Cause.TRIGGERED);

        if (event.isCancelled()) {
            return;
        }

        ((ServerLevel) getMinecraftWorld()).setDayTime(getAbsoluteTime() + event.getChange());

        for (Player player : getPlayers()) {
            if (!player.isConnected()) {
                return;
            }

            PlayerImpl playerImpl = (PlayerImpl) player;
            Level level = ((WorldImpl) playerImpl.getWorld()).getMinecraftWorld();
            playerImpl.getMinecraftEntity().connection.send(new ClientboundSetTimePacket(
                    level.getGameTime(),
                    player.getTime(),
                    level.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)
            ));
        }
    }
}
