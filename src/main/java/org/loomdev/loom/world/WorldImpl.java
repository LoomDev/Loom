package org.loomdev.loom.world;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.particle.ParticleEffects;
import org.loomdev.api.sound.Sounds;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.entity.EntityImpl;;

import java.util.UUID;

public class WorldImpl implements World {

    private final ServerWorld world;

    public WorldImpl(ServerWorld world) {
        this.world = world;
    }

    public static World of(ServerWorld world) {
        return new WorldImpl(world);
    }

    @Override
    public UUID getUUID() {
        return UUID.randomUUID();
        //return this.world.uuid; // TODO
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
}
