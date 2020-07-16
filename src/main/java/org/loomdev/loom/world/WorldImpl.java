package org.loomdev.loom.world;

import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.particle.ParticleEffects;
import org.loomdev.api.sound.Sounds;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.entity.EntityImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.util.WorldUtil;;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class WorldImpl implements World {

    private final ServerWorld world;
    private final UUID uuid;

    public WorldImpl(ServerWorld world) {
        this.world = world;
        this.uuid = UUID.randomUUID(); // TODO CHANGE THIS TO A DEFINED UUID PER WORLD
    }

    public static World of(ServerWorld world) {
        return new WorldImpl(world);
    }

    @Override
    public @NotNull String getName() {
        return this.world.field_24456.getLevelName();
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return BlockImpl.of(this.world, new BlockPos(x, y, z));
    }

    @Override
    public Block getBlock(Location location) {
        return getBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public Chunk getChunk(int i, int i1) {
        return null;
    }

    @Override
    public boolean isChunkGenerated(int i, int i1) {
        return false;
    }

    @Override
    public boolean isChunkGenerated(Chunk chunk) {
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
    public Entity spawnEntity(Entity entity, Location location) {
        net.minecraft.entity.Entity mcEntity = ((EntityImpl) entity).getMinecraftEntity();
        this.world.spawnEntity(mcEntity);
        return mcEntity.getLoomEntity();
    }

    @Override
    public void spawnParticle(Location location, ParticleEffects particleEffect, int amount) {
        this.world.getServer().getPlayerManager().getPlayerList().forEach(player -> {
            this.world.spawnParticles(
                    (DefaultParticleType) Registry.PARTICLE_TYPE.get(new Identifier(particleEffect.getId())),
                    location.getX(), location.getY(), location.getZ(),
                    amount,
                    0, 0, 0, 0 // offset and data
            );
        }); // TODO ._.
    }

    @Override
    public void playSound(Location location, Sounds sound, float volume, float pitch) {
        // TODO implement sound category
        BlockPos pos = new BlockPos(location.getX(), location.getY(), location.getZ());
        this.world.playSound(null, pos, Registry.SOUND_EVENT.get(sound.rawId()), SoundCategory.NEUTRAL, volume, pitch);
    }

    @Override
    public Collection<? extends Player> getPlayers() {
        return this.world.getPlayers()
                .stream()
                .map(e -> (PlayerImpl) e.getLoomEntity())
                .collect(Collectors.toList());
    }

    @Override
    public long getTime() {
        long time = this.world.getTimeOfDay() % 24000;
        if (time < 0) time += 24000;
        return time;
    }

    @Override
    public void setTime(long ticks) {
        long margin = (ticks - this.world.getTimeOfDay()) % 24000;
        if (margin < 0) margin += 24000;
        this.world.method_29199(this.world.getTimeOfDay() + margin);

        // Forces the client to update to the new time immediately
        for (Player p : getPlayers()) {
            PlayerImpl cp = (PlayerImpl) p;
            if (cp.getMinecraftEntity().networkHandler == null) continue;

            // TODO replace second arg w/ player time
            cp.getMinecraftEntity().networkHandler.sendPacket(new WorldTimeUpdateS2CPacket(cp.getMinecraftEntity().world.getTime(), cp.getMinecraftEntity().world.getTimeOfDay(), cp.getMinecraftEntity().world.getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)));
        }
    }
}
