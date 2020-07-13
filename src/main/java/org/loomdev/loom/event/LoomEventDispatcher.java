package org.loomdev.loom.event;

import net.kyori.adventure.text.TextComponent;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.c2s.query.QueryRequestC2SPacket;
import net.minecraft.server.ServerMetadata;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.loomdev.api.Loom;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.block.BlockBrokenEvent;
import org.loomdev.api.event.block.BlockPlacedEvent;
import org.loomdev.api.event.block.entity.ArmorStandPlacedEvent;
import org.loomdev.api.event.block.fluid.FluidLevelChangedEvent;
import org.loomdev.api.event.block.plant.*;
import org.loomdev.api.event.block.sponge.SpongeAbsorbedEvent;
import org.loomdev.api.event.player.PlayerMessageSentEvent;
import org.loomdev.api.event.player.connection.PlayerDisconnectedEvent;
import org.loomdev.api.event.player.connection.PlayerJoinedEvent;
import org.loomdev.api.event.player.connection.PlayerLoggedInEvent;
import org.loomdev.api.event.server.ServerPingedEvent;
import org.loomdev.api.world.Location;
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.entity.decoration.ArmorStandImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

public final class LoomEventDispatcher {

    private LoomEventDispatcher() {
        throw new UnsupportedOperationException("LoomEventDispatcher should not be instantiated.");
    }

    private static <E extends Event> E fire(E event) {
        return Loom.getServer().getEventManager().fire(event);
    }

    private static <E extends Event> CompletableFuture<E> fireAsync(E event) {
        return Loom.getServer().getEventManager().fireAsync(event);
    }

    public static ArmorStandPlacedEvent onArmorStandPlaced(ArmorStandEntity armorStand, BlockPos blockpos, PlayerEntity player) {
        ArmorStandPlacedEvent event = new ArmorStandPlacedEvent(
                (ArmorStandImpl) armorStand.getLoomEntity(),
                new Location(null, blockpos.getX(), blockpos.getY(), blockpos.getZ()),
                (PlayerImpl) player.getLoomEntity()
        );
        return fire(event);
    }

    public static BlockBrokenEvent onBlockBroken(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fire(new BlockBrokenEvent(new BlockImpl(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static BlockPlacedEvent onBlockPlaced(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fire(new BlockPlacedEvent(new BlockImpl(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static CoralDiedEvent onCoralDied(WorldAccess world, BlockPos pos) {
        return fire(new CoralDiedEvent(new BlockImpl(world, pos)));
    }

    public static LeavesDecayedEvent onLeavesDecayed(WorldAccess world, BlockPos pos) {
        return fire(new LeavesDecayedEvent(new BlockImpl(world, pos)));
    }

    public static PlantGrewEvent onPlantGrew(WorldAccess world, BlockPos pos) {
        PlantGrewEvent event = new PlantGrewEvent(new BlockImpl(world, pos)); // TODO remove variable
        event.setCancelled(true);
        return fire(event);
    }

    public static PlantFertilizedEvent onPlantFertilized(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fire(new PlantFertilizedEvent(new BlockImpl(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static PlantHarvestedEvent onPlantHarvested(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fire(new PlantHarvestedEvent(new BlockImpl(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static FluidLevelChangedEvent onFluidLevelChanged(WorldAccess world, BlockPos pos) {
        return fire(new FluidLevelChangedEvent(new BlockImpl(world, pos)));
    }

    public static SpongeAbsorbedEvent onSpongeAbsorbed(WorldAccess world, BlockPos pos) {
        return fire(new SpongeAbsorbedEvent(new BlockImpl(world, pos)));
    }

    /*public static PlayerLoggedInEvent onPlayerLoggedIn(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(new PlayerLoggedInEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }*/

    public static PlayerJoinedEvent onPlayerJoined(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(new PlayerJoinedEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    public static CompletableFuture<PlayerDisconnectedEvent> onPlayerDisconnected(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fireAsync(new PlayerDisconnectedEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    public static CompletableFuture<PlayerMessageSentEvent> onPlayerMessageSent(ServerPlayerEntity serverPlayerEntity, String message) {
        return fireAsync(new PlayerMessageSentEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), message, new HashSet<>(Loom.getServer().getOnlinePlayers())));
    }

    public static CompletableFuture<ServerPingedEvent> onServerPinged(ClientConnection connection, ServerMetadata metadata) {
        return fireAsync(new ServerPingedEvent(
                ((InetSocketAddress) connection.getAddress()).getAddress(),
                (TextComponent) TextTransformer.toLoom(metadata.getDescription()),
                metadata.getPlayers().getOnlinePlayerCount(),
                metadata.getPlayers().getPlayerLimit(),
                metadata.getFavicon()
        ));
    }
}
