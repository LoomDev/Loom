package org.loomdev.loom.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ServerLevelData;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.math.vector.Vector3i;
import org.loomdev.api.particle.Particle;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.Weather;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockPointerImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.event.LoomEventDispatcher;
import org.loomdev.loom.event.world.WorldEventImpl;
import org.loomdev.loom.transformer.Transformer;

import java.util.Optional;
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
        var mcType = Registry.ENTITY_TYPE.get(Transformer.NAMESPACED_KEY.toMinecraft(type.getKey()));
        return Optional.ofNullable(mcType.create(getMinecraftWorld()))
                .map(entity -> {
                    entity.moveTo(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
                    getMinecraftWorld().addFreshEntity(entity);
                    return (T) entity.getLoomEntity();
                });
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location) {
        ((ServerLevel) getMinecraftWorld()).sendParticles(
                Transformer.PARTICLE.toMinecraft(particle),
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
        var mcSound = Registry.SOUND_EVENT.get(Transformer.NAMESPACED_KEY.toMinecraft(sound.getSoundEffect().getKey()));
        var source = SoundSource.getByName(sound.getSoundCategory().getName());

        getMinecraftWorld().playSound(null, pos, mcSound, source, sound.getVolume(), sound.getPitch());
    }

    @Override
    @NotNull
    public Stream<Player> getPlayers() {
        return getMinecraftWorld().players().stream()
                .map(e -> (PlayerImpl) e.getLoomEntity());
    }

    @Override
    @NotNull
    public Weather getWeather() {
        var levelData = getMinecraftWorld().getLevelData();

        if (levelData.isThundering())
            return Weather.THUNDER;

        if (levelData.isRaining())
            return Weather.RAIN;

        return Weather.CLEAR;
    }

    @Override
    public int getWeatherTime(@NotNull Weather weather) {
        var levelData = (ServerLevelData) getMinecraftWorld().getLevelData();
        switch (weather) {
            case CLEAR:
                return levelData.getClearWeatherTime();
            case RAIN:
                return levelData.getRainTime();
            case THUNDER:
                return levelData.getThunderTime();
        }
        return -1;
    }

    @Override
    public void setWeather(@NotNull Weather weather) {
        // 6000 is the default amount used in the vanilla weather command
        setWeather(weather, 6000);
    }

    @Override
    public void setWeather(@NotNull Weather weather, int ticks) {
        var clearTicks = weather == Weather.CLEAR ? ticks : 0;
        var weatherTicks = weather != Weather.CLEAR ? ticks : 0;
        var rain = weather != Weather.CLEAR;
        var thunder = weather == Weather.THUNDER;

        var level = (ServerLevel) getMinecraftWorld();
        level.setWeatherParameters(clearTicks, weatherTicks, rain, thunder);
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
        var event = LoomEventDispatcher.fire(new WorldEventImpl.TimeSkipImpl(getMinecraftWorld(), ticks - getAbsoluteTime()));

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
