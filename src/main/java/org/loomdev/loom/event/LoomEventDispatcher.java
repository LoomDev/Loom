package org.loomdev.loom.event;

import net.kyori.adventure.text.TextComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.ServerMetadata;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.loomdev.api.Loom;
import org.loomdev.api.entity.misc.Lightning;
import org.loomdev.api.entity.mob.Creeper;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.block.BlockBrokenEvent;
import org.loomdev.api.event.block.BlockBurnedEvent;
import org.loomdev.api.event.block.BlockIgnitedEvent;
import org.loomdev.api.event.block.BlockPlacedEvent;
import org.loomdev.api.event.block.fluid.FluidLevelChangedEvent;
import org.loomdev.api.event.block.plant.*;
import org.loomdev.api.event.block.sponge.SpongeAbsorbedEvent;
import org.loomdev.api.event.entity.creeper.CreeperChargedEvent;
import org.loomdev.api.event.entity.creeper.CreeperIgnitedEvent;
import org.loomdev.api.event.entity.decoration.ArmorStandPlacedEvent;
import org.loomdev.api.event.player.PlayerMessagedEvent;
import org.loomdev.api.event.player.connection.PlayerDisconnectedEvent;
import org.loomdev.api.event.player.connection.PlayerJoinedEvent;;
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

    public static CompletableFuture<ArmorStandPlacedEvent> onArmorStandPlaced(ArmorStandEntity armorStand, BlockPos blockpos, PlayerEntity player) { // TODO also make async in nms
        ArmorStandPlacedEvent event = new ArmorStandPlacedEvent(
                (ArmorStandImpl) armorStand.getLoomEntity(),
                (PlayerImpl) player.getLoomEntity(),
                ArmorStandPlacedEvent.Cause.PLAYER
        );
        return fireAsync(event);
    }

    // TODO dispenser armor stand place event

    public static CompletableFuture<BlockBrokenEvent> onBlockBroken(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fireAsync(new BlockBrokenEvent(BlockImpl.of(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static BlockPlacedEvent onBlockPlaced(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fire(new BlockPlacedEvent(BlockImpl.of(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static CompletableFuture<BlockIgnitedEvent> onBlockIgnited(WorldAccess world, BlockPos pos, BlockPos ignitingpos) {
        net.minecraft.block.Block ignitingBlock = world.getBlockState(ignitingpos).getBlock();
        BlockIgnitedEvent.Cause cause = BlockIgnitedEvent.Cause.FIRE_SPREAD;

        if (ignitingBlock.is(Blocks.LAVA)) {
            cause = BlockIgnitedEvent.Cause.LAVA;
        } else if (ignitingBlock.is(Blocks.DISPENSER)) {
            cause = BlockIgnitedEvent.Cause.FLINT_AND_STEEL;
        }

        return fireAsync(new BlockIgnitedEvent(BlockImpl.of(world, pos), BlockImpl.of(world, ignitingpos), cause));
    }

    public static CompletableFuture<BlockIgnitedEvent> onBlockIgnited(WorldAccess world, BlockPos pos, Entity igniter) {
        return null; // TODO
    }

    public static CompletableFuture<BlockBurnedEvent> onBlockBurned(WorldAccess world, BlockPos pos, BlockPos ignitingpos) {
        return fireAsync(new BlockBurnedEvent(BlockImpl.of(world, pos), BlockImpl.of(world, ignitingpos)));
    }

    public static CompletableFuture<PlantDiedEvent> onPlantDied(WorldAccess world, BlockPos pos) {
        return fireAsync(new PlantDiedEvent(BlockImpl.of(world, pos)));
    }

    public static CompletableFuture<PlantDecayedEvent> onPlantDecayed(WorldAccess world, BlockPos pos) {
        return fireAsync(new PlantDecayedEvent(BlockImpl.of(world, pos)));
    }

    public static PlantGrewEvent onPlantGrew(WorldAccess world, BlockPos pos) {
        /*PlantGrewEvent event = new PlantGrewEvent(BlockImpl.of(world, pos)); // TODO remove variable
        event.setCancelled(true);
        return fire(event);*/
        return null;
    }

    public static PlantFertilizedEvent onPlantFertilized(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fire(new PlantFertilizedEvent(BlockImpl.of(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static PlantHarvestedEvent onPlantHarvested(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fire(new PlantHarvestedEvent(BlockImpl.of(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static CompletableFuture<FluidLevelChangedEvent> onFluidLevelChanged(WorldAccess world, BlockPos pos) {
        return fireAsync(new FluidLevelChangedEvent(BlockImpl.of(world, pos), null)); // TODO implement blockstate
    }

    public static CompletableFuture<SpongeAbsorbedEvent> onSpongeAbsorbed(WorldAccess world, BlockPos pos) {
        return fireAsync(new SpongeAbsorbedEvent(BlockImpl.of(world, pos), new HashSet<>())); // TODO add absorbedBlocks set + implement async in the nms sponge class
    }

    /*public static PlayerLoggedInEvent onPlayerLoggedIn(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(new PlayerLoggedInEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }*/

    public static CompletableFuture<PlayerJoinedEvent> onPlayerJoined(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fireAsync(new PlayerJoinedEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    public static CompletableFuture<PlayerDisconnectedEvent> onPlayerDisconnected(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fireAsync(new PlayerDisconnectedEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    public static CompletableFuture<PlayerMessagedEvent> onPlayerMessageSent(ServerPlayerEntity serverPlayerEntity, String message) {
        return fireAsync(new PlayerMessagedEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), message, new HashSet<>(Loom.getServer().getOnlinePlayers())));
    }

    public static CompletableFuture<CreeperChargedEvent> onCreeperCharged(CreeperEntity creeper) {
        return fireAsync(new CreeperChargedEvent((Creeper) creeper.getLoomEntity()));
    }

    public static CompletableFuture<CreeperChargedEvent> onCreeperCharged(CreeperEntity creeper, LightningEntity lightning) {
        return fireAsync(new CreeperChargedEvent((Creeper) creeper.getLoomEntity(), (Lightning) lightning.getLoomEntity()));
    }

    public static CompletableFuture<CreeperIgnitedEvent> onCreeperIgnited(CreeperEntity creeper) {
        return fireAsync(new CreeperIgnitedEvent((Creeper) creeper.getLoomEntity()));
    }

    public static CompletableFuture<ServerPingedEvent> onServerPinged(ClientConnection connection, ServerMetadata metadata) {
        return fireAsync(new ServerPingedEvent(
                ((InetSocketAddress) connection.getAddress()).getAddress(),
                (TextComponent) TextTransformer.toLoom(metadata.getDescription()),
                metadata.getPlayers().getOnlinePlayerCount(),
                metadata.getPlayers().getPlayerLimit(),
                metadata.getVersion().getProtocolVersion(),
                metadata.getVersion().getGameVersion(),
                metadata.getFavicon()
        ));
    }
}
