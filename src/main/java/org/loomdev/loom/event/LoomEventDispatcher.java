package org.loomdev.loom.event;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.ServerMetadata;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.enums.Note;
import org.loomdev.api.entity.misc.Lightning;
import org.loomdev.api.entity.mob.Creeper;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.block.*;
import org.loomdev.api.event.block.fluid.FluidLevelChangedEvent;
import org.loomdev.api.event.block.note.NoteBlockPlayedEvent;
import org.loomdev.api.event.block.plant.*;
import org.loomdev.api.event.block.sign.SignWrittenEvent;
import org.loomdev.api.event.block.sponge.SpongeAbsorbedEvent;
import org.loomdev.api.event.entity.armor.EntityEquippedEquipmentEvent;
import org.loomdev.api.event.entity.creeper.CreeperChargedEvent;
import org.loomdev.api.event.entity.creeper.CreeperIgnitedEvent;
import org.loomdev.api.event.entity.decoration.ArmorStandPlacedEvent;
import org.loomdev.api.event.entity.movement.EntityBouncedEvent;
import org.loomdev.api.event.entity.movement.EntityToggledSwimmingEvent;
import org.loomdev.api.event.player.PlayerMessagedEvent;
import org.loomdev.api.event.player.connection.PlayerDisconnectedEvent;
import org.loomdev.api.event.player.connection.PlayerJoinedEvent;
import org.loomdev.api.event.player.movement.PlayerEnteredFlightEvent;
import org.loomdev.api.event.player.movement.PlayerMovedEvent;
import org.loomdev.api.event.player.movement.PlayerRiptideLaunchedEvent;
import org.loomdev.api.event.server.ServerPingedEvent;
import org.loomdev.api.event.world.TimeChangedEvent;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.entity.decoration.ArmorStandImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public final class LoomEventDispatcher {

    private LoomEventDispatcher() {
        throw new UnsupportedOperationException("LoomEventDispatcher should not be instantiated.");
    }

    private static <E extends Event> E fire(@NotNull E event) {
        return Loom.getServer().getEventManager().fire(event);
    }

    private static <E extends Event> CompletableFuture<E> fireAsync(@NotNull E event) {
        return Loom.getServer().getEventManager().fireAsync(event);
    }

    @NotNull
    public static ArmorStandPlacedEvent onArmorStandPlaced(@NotNull ArmorStandEntity armorStand, @NotNull PlayerEntity player) {
        return fire(new ArmorStandPlacedEvent((ArmorStandImpl) armorStand.getLoomEntity(), (PlayerImpl) player.getLoomEntity()));
    }

    // TODO dispenser armor stand place event

    @NotNull
    public static BlockBrokenEvent onBlockBroken(@NotNull WorldAccess world, @NotNull BlockPos blockPos, @NotNull PlayerEntity player) {
        return fire(new BlockBrokenEvent(BlockImpl.at(world, blockPos), (PlayerImpl) player.getLoomEntity()));
    }

    @NotNull
    public static BlockPlacedEvent onBlockPlaced(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull PlayerEntity player) {
        return fire(new BlockPlacedEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    @NotNull
    public static BlockDroppedExperienceEvent onBlockDroppedExperience(@NotNull WorldAccess world, @NotNull BlockPos pos, int experience) {
        return fire(new BlockDroppedExperienceEvent(BlockImpl.at(world, pos), experience));
    }

    @NotNull
    public static BlockIgnitedEvent onBlockIgnited(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull BlockPos ignitingpos, @NotNull BlockIgnitedEvent.Cause cause) {
        return fire(new BlockIgnitedEvent(BlockImpl.at(world, pos), BlockImpl.at(world, ignitingpos), cause));
    }

    @NotNull
    public static BlockIgnitedEvent onBlockIgnited(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Entity igniter, @NotNull BlockIgnitedEvent.Cause cause) { // TODO add Fireball nms implementations for this
        return fire(new BlockIgnitedEvent(BlockImpl.at(world, pos), igniter.getLoomEntity(), cause));
    }

    @NotNull
    public static BlockIgnitedEvent onBlockIgnited(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Explosion explosion) {
        org.loomdev.api.entity.Entity igniter = explosion.getCausingEntity() == null ? null : explosion.getCausingEntity().getLoomEntity();
        return fire(new BlockIgnitedEvent(BlockImpl.at(world, pos), igniter, BlockIgnitedEvent.Cause.EXPLOSION));
    }

    @NotNull
    public static BlockBurnedEvent onBlockBurned(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull BlockPos source) {
        return fire(new BlockBurnedEvent(BlockImpl.at(world, pos), BlockImpl.at(world, source)));
    }

    @NotNull
    public static BlockEvaporatedEvent onBlockEvaporated(@NotNull WorldAccess world, @NotNull BlockPos pos) {
        return fire(new BlockEvaporatedEvent(BlockImpl.at(world, pos), null)); // TODO player variable?
    }

    @NotNull
    public static BlockEvaporatedEvent onBlockEvaporated(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull PlayerEntity player) {
        return fire(new BlockEvaporatedEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    @NotNull
    public static BlockMeltedEvent onBlockMelted(@NotNull WorldAccess world, @NotNull BlockPos pos) {
        return fire(new BlockMeltedEvent(BlockImpl.at(world, pos), null)); // TODO figure out causes
    }

    @NotNull
    public static BlockExplodedEvent onBlockExploded(@NotNull WorldAccess world, @NotNull BlockPos pos, float power, @NotNull List<BlockPos> explodedBlocks) {
        Set<Block> blocks = explodedBlocks.stream().map(blockpos -> BlockImpl.at(world, blockpos)).collect(Collectors.toSet());
        BlockExplodedEvent event = new BlockExplodedEvent(BlockImpl.at(world, pos), power, blocks);
        event.setCancelled(true); // TODO figure this out later
        return fire(event);
    }

    @NotNull
    public static NoteBlockPlayedEvent onNoteBlockPlayed(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Instrument instrument, int note, float pitch) {
        NoteBlockPlayedEvent event = new NoteBlockPlayedEvent(
                BlockImpl.at(world, pos),
                org.loomdev.api.block.enums.Instrument.getByName(instrument.asString()),
                Note.getByUses(note),
                pitch
        );

        return fire(event);
    }

    @NotNull
    public static PlantDiedEvent onPlantDied(@NotNull WorldAccess world, @NotNull BlockPos pos) {
        return fire(new PlantDiedEvent(BlockImpl.at(world, pos)));
    }

    @NotNull
    public static PlantDecayedEvent onPlantDecayed(@NotNull WorldAccess world, @NotNull BlockPos pos) {
        return fire(new PlantDecayedEvent(BlockImpl.at(world, pos)));
    }

    @NotNull
    public static PlantGrewEvent onPlantGrew(WorldAccess world, BlockPos pos) {
        /*PlantGrewEvent event = new PlantGrewEvent(BlockImpl.of(world, pos)); // TODO remove variable
        event.setCancelled(true);
        return fire(event);*/
        return null;
    }

    @NotNull
    public static PlantFertilizedEvent onPlantFertilized(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull PlayerEntity player) {
        return fire(new PlantFertilizedEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    @NotNull
    public static PlantHarvestedEvent onPlantHarvested(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull PlayerEntity player) {
        return fire(new PlantHarvestedEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    @NotNull
    public static FluidLevelChangedEvent onFluidLevelChanged(@NotNull WorldAccess world, @NotNull BlockPos pos) {
        return fire(new FluidLevelChangedEvent(BlockImpl.at(world, pos), null)); // TODO implement blockstate
    }

    @NotNull
    public static SignWrittenEvent onSignWritten(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull PlayerEntity player, @NotNull String[] text) {
        return fire(new SignWrittenEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity(), text));
    }

    @NotNull
    public static SpongeAbsorbedEvent onSpongeAbsorbed(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Set<Block> absorbedBlocks) {
        return fire(new SpongeAbsorbedEvent(BlockImpl.at(world, pos), absorbedBlocks));
    }

    /*public static PlayerLoggedInEvent onPlayerLoggedIn(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(new PlayerLoggedInEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }*/

    @NotNull
    public static CompletableFuture<PlayerJoinedEvent> onPlayerJoined(@NotNull ServerPlayerEntity serverPlayerEntity, @NotNull Text joinMessage) {
        return fireAsync(new PlayerJoinedEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    @NotNull // TODO sync?
    public static CompletableFuture<PlayerDisconnectedEvent> onPlayerDisconnected(@NotNull ServerPlayerEntity serverPlayerEntity, @NotNull Text joinMessage) {
        return fireAsync(new PlayerDisconnectedEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    @NotNull
    public static CompletableFuture<PlayerMessagedEvent> onPlayerMessageSent(@NotNull ServerPlayerEntity serverPlayerEntity, @NotNull String message) {
        return fireAsync(new PlayerMessagedEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), message, Loom.getServer().getOnlinePlayers()));
    }

    @NotNull
    public static PlayerEnteredFlightEvent onPlayerEnteredFlight(@NotNull PlayerEntity playerEntity) {
        return fire(new PlayerEnteredFlightEvent((PlayerImpl) playerEntity.getLoomEntity()));
    }

    @NotNull
    public static PlayerRiptideLaunchedEvent onPlayerRiptideLaunched(@NotNull PlayerEntity playerEntity, int riptideLevel) {
        return fire(new PlayerRiptideLaunchedEvent((PlayerImpl) playerEntity.getLoomEntity(), riptideLevel));
    }

    @NotNull
    public static CompletableFuture<PlayerMovedEvent> onPlayerMoved(@NotNull PlayerEntity playerEntity, @NotNull Location currentLocation, @NotNull Location newLocation) {
        PlayerMovedEvent event = new PlayerMovedEvent((PlayerImpl) playerEntity.getLoomEntity(), currentLocation, newLocation);
        event.setCancelled(true);
        return fireAsync(event);
    }

    @NotNull
    public static CreeperChargedEvent onCreeperCharged(@NotNull CreeperEntity creeper) {
        return fire(new CreeperChargedEvent((Creeper) creeper.getLoomEntity()));
    }

    @NotNull
    public static CreeperChargedEvent onCreeperCharged(@NotNull CreeperEntity creeper, @NotNull LightningEntity lightning) {
        return fire(new CreeperChargedEvent((Creeper) creeper.getLoomEntity(), (Lightning) lightning.getLoomEntity()));
    }

    @NotNull
    public static CreeperIgnitedEvent onCreeperIgnited(@NotNull CreeperEntity creeper) {
        return fire(new CreeperIgnitedEvent((Creeper) creeper.getLoomEntity()));
    }

    @NotNull
    public static EntityBouncedEvent onEntityBounced(@NotNull Entity entity, double multiplier) {
        return fire(new EntityBouncedEvent(entity.getLoomEntity(), BlockImpl.at(entity.world, entity.getBlockPos()), multiplier));
    }

    @NotNull
    public static EntityToggledSwimmingEvent onEntityToggledSwimming(Entity entity) {
        EntityToggledSwimmingEvent event = new EntityToggledSwimmingEvent(entity.getLoomEntity());
        event.setCancelled(true);
        return fire(event);
    }

    @NotNull
    public static EntityEquippedEquipmentEvent onEntityEquippedEquipmentEvent(@NotNull Entity entity, @NotNull EquipmentSlot slot, @NotNull ItemStack equipment) {
        /*EntityEquippedEquipmentEvent event = new EntityEquippedEquipmentEvent(entity.getLoomEntity());
        event.cancel(true);
        return fireAsync(event);*/
        return null;
    }

    @NotNull
    public static TimeChangedEvent onTimeChanged(@NotNull World world, long change, @NotNull TimeChangedEvent.Cause cause) {
        return fire(new TimeChangedEvent(world, change, cause));
    }

    @NotNull
    public static TimeChangedEvent onTimeChanged(@NotNull ServerWorld world, long change, @NotNull TimeChangedEvent.Cause cause) {
        TimeChangedEvent event = new TimeChangedEvent(
                Loom.getServer().getWorld(world.worldProperties.getLevelName()).orElse(null), // TODO maybe don't fire on invalid world? also fetch world by uuid since it'll be O(1)
                change,
                cause
        );
        return fire(event);
    }

    @NotNull
    public static CompletableFuture<ServerPingedEvent> onServerPinged(ClientConnection connection, ServerMetadata metadata) {
        return fireAsync(new ServerPingedEvent(
                ((InetSocketAddress) connection.getAddress()).getAddress(),
                TextTransformer.toLoom(metadata.getDescription()),
                metadata.getPlayers().getOnlinePlayerCount(),
                metadata.getPlayers().getPlayerLimit(),
                metadata.getVersion().getProtocolVersion(),
                metadata.getVersion().getGameVersion(),
                metadata.getFavicon()
        ));
    }
}
