package org.loomdev.loom.world;

import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.particle.Particle;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.util.transformer.ParticleTransformer;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class WorldImpl implements World {

    private final ServerWorld world;
    private final UUID uuid;

    private WorldImpl(ServerWorld world) {
        this.world = world;
        this.uuid = UUID.randomUUID(); // TODO CHANGE THIS TO A DEFINED UUID PER WORLD
    }

    public static @NotNull World of(ServerWorld world) {
        return new WorldImpl(world);
    }

    public ServerWorld getMinecraftWorld() {
        return this.world;
    }

    @Override
    public @NotNull String getName() {
        return this.world.field_24456.getLevelName();
    }

    @Override
    public @NotNull UUID getUUID() {
        return this.uuid;
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
    public @NotNull Chunk getChunk(int x, int z) {
        return ChunkImpl.from(this.world.getChunk(x, z));
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
    public @NotNull Optional<Entity> spawnEntity(@NotNull EntityType type, @NotNull Location location) {
        return location.getWorld().map(world -> {
            ServerWorld mcWorld = ((WorldImpl) world).getMinecraftWorld();
            net.minecraft.entity.Entity mcEntity = Registry.ENTITY_TYPE.get(Identifier.tryParse(type.getId())).create(mcWorld);

            if (mcEntity != null) {
                mcEntity.updatePosition(location.getX(), location.getY(), location.getZ());
                mcWorld.spawnEntity(mcEntity);
            }

            return mcEntity.getLoomEntity();
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
                Registry.SOUND_EVENT.get(sound.getType().rawId()),
                SoundCategory.valueOf(sound.getCategory().name()),
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
