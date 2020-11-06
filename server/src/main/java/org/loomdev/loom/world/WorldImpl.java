package org.loomdev.loom.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameRules;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.world.WorldTimeChangeEvent;
import org.loomdev.api.particle.Particle;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.event.LoomEventDispatcher;
import org.loomdev.loom.util.transformer.ParticleTransformer;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class WorldImpl implements World {

    private final ServerLevel world;

    public WorldImpl(ServerLevel world) {
        this.world = world;
    }

    public static World of(ServerLevel world) {
        return Loom.getServer().getWorld(world.serverLevelData.getLevelName()); // TODO use uuid once it works
    }

    public ServerLevel getMinecraftWorld() {
        return world;
    }

    @Override
    @NotNull
    public String getName() {
        return world.serverLevelData.getLevelName();
    }

    @Override
    public @NotNull UUID getUUID() {
        return UUID.randomUUID(); // TODO
    }

    @Override
    @NotNull
    public Block getBlock(int x, int y, int z) {
        return BlockImpl.at(this.world, new BlockPos(x, y, z));
    }

    @Override
    @NotNull
    public Block getBlock(@NotNull Location location) {
        return getBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public void setBlock(Location location, BlockType type) {
        setBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ(), type);
    }

    @Override
    public void setBlock(int x, int y, int z, BlockType type) {
        var key = new ResourceLocation(type.getKey().toString());
        var block = Registry.BLOCK.get(key).defaultBlockState();
        var pos = new BlockPos(x, y, z);
        world.setBlockAndUpdate(pos, block);
    }

    @Override
    @NotNull
    public Chunk getChunk(int x, int z) {
        // return ChunkImpl.from(this.world.getChunk(x, z));
        return null;
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
    @NotNull
    public <T extends Entity> Optional<T> spawnEntity(@NotNull EntityType<T> type, @NotNull Location location) {
        return location.getWorld().map(world -> {
            var mcWorld = ((WorldImpl) world).getMinecraftWorld();
            var mcEntity = Registry.ENTITY_TYPE.get(new ResourceLocation(type.getKey().toString())).create(mcWorld);

            if (mcEntity != null) {
                mcEntity.setPos(location.getX(), location.getY(), location.getZ());
                mcWorld.addFreshEntity(mcEntity);
            }

            return (T) mcEntity.getLoomEntity();
        });
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location) {
        this.world.sendParticles(
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
        world.playSound(
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
        return this.world.players()
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
        return world.isDay();
    }

    @Override
    public boolean isNight() {
        return world.isNight();
    }

    @Override
    public long getAbsoluteTime() {
        return world.getGameTime();
    }

    @Override
    public void setAbsoluteTime(long ticks) {
        var event = LoomEventDispatcher.onWorldTimeChanged(this, ticks - getAbsoluteTime(), WorldTimeChangeEvent.Cause.TRIGGERED);

        if (event.isCancelled()) {
            return;
        }

        world.setDayTime(getAbsoluteTime() + event.getChange());

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
