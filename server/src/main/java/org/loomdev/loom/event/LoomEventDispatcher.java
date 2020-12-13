package org.loomdev.loom.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.PositionSource;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.enums.Note;
import org.loomdev.api.event.ChatEvent;
import org.loomdev.api.event.*;
import org.loomdev.api.event.block.*;
import org.loomdev.api.event.connection.StatusPingEvent;
import org.loomdev.api.event.entity.CreeperEvent;
import org.loomdev.api.event.connection.ConnectionEvent;
import org.loomdev.api.event.world.BlockEvent;
import org.loomdev.api.event.world.ExplosionEvent;
import org.loomdev.api.event.world.FluidEvent;
import org.loomdev.api.event.world.WorldEvent;
import org.loomdev.api.util.Hand;
import org.loomdev.loom.block.BlockStateImpl;
import org.loomdev.loom.entity.monster.CreeperImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.util.transformer.TextTransformer;
import org.loomdev.loom.world.ExplosionImpl;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public final class LoomEventDispatcher {

    private static final Map<GameEvent, SculkSensorEvent.VibrationType> VIBRATION_MAP = new HashMap<>();

    private LoomEventDispatcher() {
        throw new UnsupportedOperationException("LoomEventDispatcher should not be instantiated.");
    }

    private static <E extends Event> E fire(@NotNull E event) {
        return Loom.getServer().getEventManager().fire(event);
    }

    private static <E extends Event> CompletableFuture<E> fireAsync(@NotNull E event) {
        return Loom.getServer().getEventManager().fireAsync(event);
    }

    public static void onPreTick(long tick) {
        fire(new TickEvent.Pre(tick));
    }

    public static void onPostTick(long tick) {
        fire(new TickEvent.Post(tick));
    }

    public static void onTickLevel(ServerLevel level, long tick) {
        fire(new TickEvent.World(level.getLoomWorld(), tick));
    }

    public static ConnectionEvent.Join onPlayerJoin(ServerPlayer serverPlayer, Component message) {
        return fire(new ConnectionEvent.Join((PlayerImpl) serverPlayer.getLoomEntity(), TextTransformer.toLoom(message)));
    }

    public static ConnectionEvent.Disconnect onPlayerDisconnect(ServerPlayer player, Component message) {
        return fire(new ConnectionEvent.Disconnect((PlayerImpl) player.getLoomEntity(), TextTransformer.toLoom(message)));
    }

    public static CompletableFuture<StatusPingEvent> onStatusPing(Connection connection, ServerStatus status) {
        return fireAsync(new StatusPingEvent(
                ((InetSocketAddress) connection.getRemoteAddress()).getAddress(),
                status.getVersion().getProtocol(),
                status.getVersion().getName(),
                status.getPlayers().getNumPlayers(),
                status.getPlayers().getMaxPlayers(),
                TextTransformer.toLoom(status.getDescription()),
                status.getFavicon()
        ));
    }

    public static BlockEvent.Break onBlockBreak(Entity entity, Level level, BlockPos blockPos) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new BlockEvent.Break(new EventCause(entity.getLoomEntity()), block));
    }

    public static BlockEvent.Place onBlockPlace(Entity entity, Level level, BlockPos blockPos, BlockState newState) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var state = new BlockStateImpl(newState);
        return fire(new BlockEvent.Place(new EventCause(entity.getLoomEntity()), block, state));
    }

    public static BlockEvent.DropExperience onBlockDropExperience(Level level, BlockPos blockPos, int experience) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new BlockEvent.DropExperience(block, experience));
    }

    public static BlockEvent.DropItem onBlockDropItem(Level level, BlockPos blockPos, ItemStack item) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new BlockEvent.DropItem(block, ItemStackImpl.of(item)));
    }

    public static BlockEvent.BurnUp onBlockBurnUp(Level level, BlockPos blockPos, BlockPos sourcePos) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var source = level.getLoomWorld().getBlock(sourcePos.getX(), sourcePos.getY(), sourcePos.getZ());
        return fire(new BlockEvent.BurnUp(block, source));
    }

    public static BlockEvent.Melt onBlockMelt(Level level, BlockPos blockPos) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new BlockEvent.Melt(block)); // TODO figure out causes
    }

    public static BlockEvent.ChangeOverTime onBlockChangeOverTime(Level level, BlockPos blockPos, BlockState newState) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var state = new BlockStateImpl(newState);
        return fire(new BlockEvent.ChangeOverTime(block, state));
    }

    public static NoteBlockEvent.Play onNoteBlockPlay(Level level, BlockPos blockPos, NoteBlockInstrument mcInstrument, int note, float pitch) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var instrument = org.loomdev.api.block.enums.Instrument.getByName(mcInstrument.getSerializedName());
        return fire(new NoteBlockEvent.Play(block, instrument, Note.getByUses(note), pitch));
    }

    public static NoteBlockEvent.ChangeNote onNoteBlockChangeNote(Level level, BlockPos blockPos, Player player, InteractionHand hand) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new NoteBlockEvent.ChangeNote(block, (PlayerImpl) player.getLoomEntity(), Hand.values()[hand.ordinal()]));
    }

    public static ExplosionEvent.Pre onPreExplosion(Explosion explosion) {
        return fire(new ExplosionEvent.Pre(new ExplosionImpl(explosion)));
    }

    public static ExplosionEvent.Detonate onExplosionDetonate(Explosion explosion) {
        return fire(new ExplosionEvent.Detonate(new ExplosionImpl(explosion)));
    }

    public static void onPostExplosion(Explosion explosion) {
        fire(new ExplosionEvent.Post(new ExplosionImpl(explosion)));
    }

    @NotNull
    public static FluidEvent.LevelChange onFluidLevelChange(Level level, BlockPos blockPos, BlockState newState) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var state = new BlockStateImpl(newState);
        return fire(new FluidEvent.LevelChange(block, state));
    }

    public static PlantEvent.Die onPlantDie(Level level, BlockPos blockPos, BlockState deadState) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var newState = new BlockStateImpl(deadState);
        return fire(new PlantEvent.Die(block, newState));
    }

    public static PlantEvent.Decay onPlantDecay(Level level, BlockPos blockPos) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new PlantEvent.Decay(block));
    }

    public static PlantEvent.Grow onPlantGrow(Level level, BlockPos blockPos, BlockState newState) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var state = new BlockStateImpl(newState);
        return fire(new PlantEvent.Grow(block, state));
    }

    public static SpongeEvent.Absorb onSpongeAbsorb(Level level, BlockPos blockPos, List<BlockPointer> absorbedBlocks) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new SpongeEvent.Absorb(block, absorbedBlocks));
    }

    public static SpongeEvent.Dry onSpongeDry(Level level, BlockPos blockPos) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new SpongeEvent.Dry(block));
    }

    public static CompletableFuture<ChatEvent.Entity> onEntityChat(Entity entity, Component text) {
        return fireAsync(new ChatEvent.Entity(new EventCause(entity.getLoomEntity()), TextTransformer.toLoom(text)));
    }

    public static CreeperEvent.Power onCreeperPower(Creeper creeper, net.minecraft.world.entity.LightningBolt lightning) {
        var cause = new EventCause(lightning);
        return fire(new CreeperEvent.Power(cause, (CreeperImpl) creeper.getLoomEntity()));
    }

    public static CreeperEvent.Ignite onCreeperIgnite(net.minecraft.world.entity.monster.Creeper creeper) {
        return fire(new CreeperEvent.Ignite((CreeperImpl) creeper.getLoomEntity()));
    }

    public static SculkSensorEvent.Activate onSculkSensorActivate(Level level, BlockPos blockPos, PositionSource sourcePos, GameEvent event) {
        var world = level.getLoomWorld();
        var block = world.getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var source = sourcePos.getPosition(level).map(pos -> level.getLoomWorld().getBlock(pos.getX(), pos.getY(), pos.getZ())).orElse(null);
        return fire(new SculkSensorEvent.Activate(block, source, VIBRATION_MAP.get(event)));
    }

    public static WorldEvent.TimeSkip onWorldTimeSkip(Level level, long skippedTicks) {
        return fire(new WorldEvent.TimeSkip(level.getLoomWorld(), skippedTicks));
    }

    static {
        Registry.GAME_EVENT.forEach(gameEvent -> {
            VIBRATION_MAP.put(gameEvent, SculkSensorEvent.VibrationType.valueOf(gameEvent.name.toUpperCase()));
        });
    }
}
