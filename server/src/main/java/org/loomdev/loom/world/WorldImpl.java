package org.loomdev.loom.world;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameRules;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.block.Block;
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
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.event.LoomEventDispatcher;
import org.loomdev.loom.util.transformer.ParticleTransformer;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class WorldImpl implements World {

    private final WeakReference<ServerLevel> level;

    public WorldImpl(ServerLevel level) {
        this.level = new WeakReference<>(Preconditions.checkNotNull(level));
    }

    public static World of(ServerLevel world) {
        return Loom.getServer().getWorld(world.serverLevelData.getLevelName()); // TODO use uuid once it works
    }

    @Nullable
    public ServerLevel getMinecraftWorld() {
        return level.get();
    }

    @Override
    @NotNull
    public String getName() {
        return getMinecraftWorld().serverLevelData.getLevelName();
    }

    @Override
    public @NotNull UUID getUUID() {
        return UUID.randomUUID(); // TODO
    }

    @Override
    @NotNull
    public Block getBlock(@NotNull Vector3i pos) {
        return getBlock(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    @NotNull
    public Block getBlock(int x, int y, int z) {
        return BlockImpl.at(getMinecraftWorld(), new BlockPos(x, y, z));
    }

    @Override
    public void setBlock(int x, int y, int z, @NotNull BlockType type) {
        setBlock(new Vector3i(x, y, z), type);
    }

    @Override
    public void setBlock(@NotNull Vector3i pos, @NotNull BlockType type) {
        var block = Registry.BLOCK.get(new ResourceLocation(type.getKey().toString())).defaultBlockState();
        getMinecraftWorld().setBlockAndUpdate(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), block);
    }

    @Override
    public void setBlockControlledUpdate(@NotNull Vector3i pos, @NotNull BlockType type, @NotNull UpdateType updateType) {
        setBlockControlledUpdate(pos.getX(), pos.getY(), pos.getZ(), type, updateType);
    }

    @Override
    public void setBlockControlledUpdate(int x, int y, int z, BlockType type, UpdateType updateType) {
        var block = Registry.BLOCK.get(new ResourceLocation(type.getKey().toString())).defaultBlockState();

        int updateId;
        switch (updateType) {
            case NOTIFY:
                updateId = 2;
                break;
            case OBSERVER_IGNORED:
                updateId = 16;
                break;
            case NO_PLACE:
                updateId = 1024;
                break;
            default:
                updateId = 3;
        }

        getMinecraftWorld().setBlock(new BlockPos(x, y, z), block, updateId);
    }

    @Override
    @NotNull
    public Chunk getChunk(int x, int z) {
        return getMinecraftWorld().getChunk(x, z).getLoomChunk();

        // return ChunkImpl.of(getMinecraftWorld().getChunk(x, z));
    }

    @Override
    @NotNull
    public Chunk getChunk(Vector3i chunkpos) {
        return getChunk(chunkpos.getX(), chunkpos.getZ());
    }

    @Override
    public boolean isChunkGenerated(int i, int i1) {
        return false;
    }

    @Override
    public boolean isChunkGenerated(@NotNull Chunk chunk) {
        return false;
    }

    @Override
    public boolean isChunkLoaded(int i, int i1) {
        return false;
    }

    @Override
    public boolean isChunkLoaded(Chunk chunk) {
        return false;
    }

    @Override
    @Nullable
    public <T extends Entity> T spawnEntity(@NotNull EntityType<T> type, @NotNull Location location) {
        if (location.getWorld() == null) {
            return null;
        }

        var mcEntity = Registry.ENTITY_TYPE.get(new ResourceLocation(type.getKey().toString())).create(getMinecraftWorld());
        if (mcEntity != null) {
            mcEntity.moveTo(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
            getMinecraftWorld().addFreshEntity(mcEntity);
            System.out.println(mcEntity.getBlockX() + " " + mcEntity.getBlockY() + " " + mcEntity.getBlockZ());
            System.out.println(getMinecraftWorld().serverLevelData.getLevelName());
        }

        return (T) mcEntity.getLoomEntity();
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location) {
        getMinecraftWorld().sendParticles(
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

        getMinecraftWorld().setDayTime(getAbsoluteTime() + event.getChange());

        for (Player player : getPlayers()) {
            if (!player.isConnected()) {
                return;
            }

            PlayerImpl playerImpl = (PlayerImpl) player;
            ServerLevel world = ((WorldImpl) playerImpl.getWorld()).getMinecraftWorld();
            playerImpl.getMinecraftEntity().connection.send(new ClientboundSetTimePacket(
                    world.getGameTime(),
                    player.getTime(),
                    world.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)
            ));
        }
    }
}
