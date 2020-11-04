package org.loomdev.loom.world;

import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
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

    private final ServerWorld world;

    public WorldImpl(ServerWorld world) {
        this.world = world;
    }

    public static /*@NotNull*/ World of(ServerWorld world) {
        return Loom.getServer().getWorld(world.worldProperties.getLevelName()).orElse(null); // TODO use uuid
    }

    public ServerWorld getMinecraftWorld() {
        return this.world;
    }

    @Override
    public @NotNull String getName() {
        return this.world.worldProperties.getLevelName();
    }

    @Override
    public @NotNull UUID getUUID() {
        return UUID.randomUUID(); // TODO
        // return world.field_24456.getUUID();
    }

    @Override
    public @NotNull Block getBlock(int x, int y, int z) {
        return BlockImpl.at(this.world, new BlockPos(x, y, z));
    }

    @Override
    public @NotNull Block getBlock(@NotNull Location location) {
        return getBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public void setBlock(Location location, BlockType type) {
        setBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ(), type);
    }

    @Override
    public void setBlock(int x, int y, int z, BlockType type) {
        var key = new Identifier(type.getKey().toString());
        System.out.println(key.toString());
        var block = Registry.BLOCK.get(key).getDefaultState();
        System.out.println(block.toString());
        var pos = new BlockPos(x, y, z);
        System.out.println(world.getWorldChunk(pos));
        world.setBlockState(pos, block);
    }

    @Override
    public @NotNull Chunk getChunk(int x, int z) {
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
            ServerWorld mcWorld = ((WorldImpl) world).getMinecraftWorld();
            net.minecraft.entity.Entity mcEntity = Registry.ENTITY_TYPE.get(new Identifier(type.getKey().toString())).create(mcWorld);

            if (mcEntity != null) {
                mcEntity.updatePosition(location.getX(), location.getY(), location.getZ());
                mcWorld.spawnEntity(mcEntity);
            }

            return (T) mcEntity.getLoomEntity();
        });
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location) {
        this.world.spawnParticles(
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
        BlockPos pos = new BlockPos(location.getX(), location.getY(), location.getZ());
        this.world.playSound(
                null,
                pos,
                Registry.SOUND_EVENT.get(new Identifier(sound.getSoundEffect().getKey().toString())),
                SoundCategory.getByName(sound.getSoundCategory().getName()),
                sound.getVolume(),
                sound.getPitch()
        );
    }

    @Override
    public @NotNull Collection<? extends Player> getPlayers() {
        return this.world.getPlayers()
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
        return world.getTimeOfDay();
    }

    @Override
    public void setAbsoluteTime(long ticks) {
        WorldTimeChangeEvent event = LoomEventDispatcher.onWorldTimeChanged(this, ticks - getAbsoluteTime(), WorldTimeChangeEvent.Cause.TRIGGERED);

        if (event.isCancelled()) {
            return;
        }

        world.setTimeOfDay(getAbsoluteTime() + event.getChange());

        for (Player player : getPlayers()) {
            if (!player.isConnected()) {
                return;
            }

            PlayerImpl playerImpl = (PlayerImpl) player;
            ServerWorld world = ((WorldImpl) playerImpl.getWorld()).getMinecraftWorld();
            playerImpl.getMinecraftEntity().networkHandler.sendPacket(new WorldTimeUpdateS2CPacket(
                    world.getTime(),
                    player.getTime(),
                    world.getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)
            ));
        }
    }
}
