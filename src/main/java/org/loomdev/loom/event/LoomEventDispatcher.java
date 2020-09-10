package org.loomdev.loom.event;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
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
import org.loomdev.api.event.block.sponge.SpongeAbsorbedEvent;
import org.loomdev.api.event.entity.armor.EntityEquippedEquipmentEvent;
import org.loomdev.api.event.entity.creeper.CreeperChargedEvent;
import org.loomdev.api.event.entity.creeper.CreeperIgnitedEvent;
import org.loomdev.api.event.entity.decoration.ArmorStandPlacedEvent;
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

    public static CompletableFuture<ArmorStandPlacedEvent> onArmorStandPlaced(@NotNull ArmorStandEntity armorStand, @NotNull PlayerEntity player) { // TODO also make async in nms
        ArmorStandPlacedEvent event = new ArmorStandPlacedEvent((ArmorStandImpl) armorStand.getLoomEntity(), (PlayerImpl) player.getLoomEntity());
        return fireAsync(event);
    }

    // TODO dispenser armor stand place event

    @NotNull
    public static BlockBrokenEvent onBlockBroken(@NotNull WorldAccess world, @NotNull BlockPos blockPos, @NotNull PlayerEntity player) {
        return fire(new BlockBrokenEvent(BlockImpl.at(world, blockPos), (PlayerImpl) player.getLoomEntity()));
    }

    public static BlockPlacedEvent onBlockPlaced(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fire(new BlockPlacedEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static CompletableFuture<BlockDroppedExperienceEvent> onBlockDroppedExperience(WorldAccess world, BlockPos pos, int experience) {
        return fireAsync(new BlockDroppedExperienceEvent(BlockImpl.at(world, pos), experience));
    }

    public static CompletableFuture<BlockIgnitedEvent> onBlockIgnited(WorldAccess world, BlockPos pos, BlockPos ignitingpos, BlockIgnitedEvent.Cause cause) {
        return fireAsync(new BlockIgnitedEvent(BlockImpl.at(world, pos), BlockImpl.at(world, ignitingpos), cause));
    }

    public static CompletableFuture<BlockIgnitedEvent> onBlockIgnited(WorldAccess world, BlockPos pos, Entity igniter, BlockIgnitedEvent.Cause cause) { // TODO add Fireball nms implementations for this
        return fireAsync(new BlockIgnitedEvent(BlockImpl.at(world, pos), igniter.getLoomEntity(), cause));
    }

    public static CompletableFuture<BlockIgnitedEvent> onBlockIgnited(WorldAccess world, BlockPos pos, Explosion explosion) {
        org.loomdev.api.entity.Entity igniter = explosion.getCausingEntity() == null ? null : explosion.getCausingEntity().getLoomEntity();
        return fireAsync(new BlockIgnitedEvent(BlockImpl.at(world, pos), igniter, BlockIgnitedEvent.Cause.EXPLOSION));
    }

    public static CompletableFuture<BlockBurnedEvent> onBlockBurned(WorldAccess world, BlockPos pos, BlockPos ignitingpos) {
        return fireAsync(new BlockBurnedEvent(BlockImpl.at(world, pos), BlockImpl.at(world, ignitingpos)));
    }

    public static CompletableFuture<BlockEvaporatedEvent> onBlockEvaporated(WorldAccess world, BlockPos pos) {
        return fireAsync(new BlockEvaporatedEvent(BlockImpl.at(world, pos), null));
    }

    public static CompletableFuture<BlockEvaporatedEvent> onBlockEvaporated(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fireAsync(new BlockEvaporatedEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static CompletableFuture<BlockMeltedEvent> onBlockMelted(WorldAccess world, BlockPos pos) {
        

       // BlockMeltedEvent event = new BlockMeltedEvent();
        //event.cancel(true);
        //return fireAsync(event);
        return null;
    }

    public static CompletableFuture<BlockBouncedEvent> onBlockBounced(WorldAccess world, BlockPos pos, Entity entity, double multiplier) {
        return fireAsync(new BlockBouncedEvent(BlockImpl.at(world, pos), entity.getLoomEntity(), multiplier));
    } // TODO impl of this is fucked

    public static CompletableFuture<BlockExplodedEvent> onBlockExploded(@NotNull WorldAccess world, @NotNull BlockPos pos, float power, @NotNull List<BlockPos> explodedBlocks) {
        Set<Block> blocks = explodedBlocks.stream().map(blockpos -> BlockImpl.at(world, blockpos)).collect(Collectors.toSet());
        BlockExplodedEvent event = new BlockExplodedEvent(BlockImpl.at(world, pos), power, blocks);
        event.cancel(true); // TODO figure this out later
        return fireAsync(event);
    }

    public static CompletableFuture<NoteBlockPlayedEvent> onNoteBlockPlayed(WorldAccess world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        NoteBlockPlayedEvent event = new NoteBlockPlayedEvent(
                BlockImpl.at(world, pos),
                org.loomdev.api.block.enums.Instrument.getByName(state.get(NoteBlock.INSTRUMENT).asString()),
                Note.getByUses(state.get(NoteBlock.NOTE)),
                (float) Math.pow(2.0D, (double) (state.get(NoteBlock.NOTE) - 12) / 12.0D)
        );
        event.setInstrument(org.loomdev.api.block.enums.Instrument.DIDGERIDOO);
        //event.setPitch(100f);
        event.cancel(false);
        return fireAsync(event);
    }

    public static CompletableFuture<PlantDiedEvent> onPlantDied(WorldAccess world, BlockPos pos) {
        return fireAsync(new PlantDiedEvent(BlockImpl.at(world, pos)));
    }

    public static CompletableFuture<PlantDecayedEvent> onPlantDecayed(WorldAccess world, BlockPos pos) {
        return fireAsync(new PlantDecayedEvent(BlockImpl.at(world, pos)));
    }

    public static PlantGrewEvent onPlantGrew(WorldAccess world, BlockPos pos) {
        /*PlantGrewEvent event = new PlantGrewEvent(BlockImpl.of(world, pos)); // TODO remove variable
        event.setCancelled(true);
        return fire(event);*/
        return null;
    }

    public static PlantFertilizedEvent onPlantFertilized(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fire(new PlantFertilizedEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static PlantHarvestedEvent onPlantHarvested(WorldAccess world, BlockPos pos, PlayerEntity player) {
        return fire(new PlantHarvestedEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    public static CompletableFuture<FluidLevelChangedEvent> onFluidLevelChanged(WorldAccess world, BlockPos pos) {
        return fireAsync(new FluidLevelChangedEvent(BlockImpl.at(world, pos), null)); // TODO implement blockstate
    }

    public static CompletableFuture<SpongeAbsorbedEvent> onSpongeAbsorbed(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Set<Block> absorbedBlocks) {
        return fireAsync(new SpongeAbsorbedEvent(BlockImpl.at(world, pos), absorbedBlocks));
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

    public static CompletableFuture<PlayerEnteredFlightEvent> onPlayerEnteredFlight(PlayerEntity playerEntity) {
        return fireAsync(new PlayerEnteredFlightEvent((PlayerImpl) playerEntity.getLoomEntity()));
    }

    public static CompletableFuture<PlayerRiptideLaunchedEvent> onPlayerRiptideLaunched(@NotNull PlayerEntity playerEntity, int riptideLevel) {
        return fireAsync(new PlayerRiptideLaunchedEvent((PlayerImpl) playerEntity.getLoomEntity(), riptideLevel));
    }

    public static CompletableFuture<PlayerMovedEvent> onPlayerMoved(@NotNull PlayerEntity playerEntity, @NotNull Location currentLocation, @NotNull Location newLocation) {
        PlayerMovedEvent event = new PlayerMovedEvent((PlayerImpl) playerEntity.getLoomEntity(), currentLocation, newLocation);
        event.cancel(true);
        return fireAsync(event);
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

    public static CompletableFuture<EntityToggledSwimmingEvent> onEntityToggledSwimming(Entity entity) {
        EntityToggledSwimmingEvent event = new EntityToggledSwimmingEvent(entity.getLoomEntity());
        event.cancel(true);
        return fireAsync(event);
    }

    public static CompletableFuture<EntityEquippedEquipmentEvent> onEntityEquippedEquipmentEvent(@NotNull Entity entity, @NotNull EquipmentSlot slot, @NotNull ItemStack equipment) {
        /*EntityEquippedEquipmentEvent event = new EntityEquippedEquipmentEvent(entity.getLoomEntity());
        event.cancel(true);
        return fireAsync(event);*/
        return null;
    }

    public static CompletableFuture<TimeChangedEvent> onTimeChanged(@NotNull World world, long change, @NotNull TimeChangedEvent.Cause cause) {
        TimeChangedEvent event = new TimeChangedEvent(world, change, cause);
        return fireAsync(event);
    }

    public static CompletableFuture<TimeChangedEvent> onTimeChanged(@NotNull ServerWorld world, long change, @NotNull TimeChangedEvent.Cause cause) {
        TimeChangedEvent event = new TimeChangedEvent(
                Loom.getServer().getWorld(world.worldProperties.getLevelName()).orElse(null), // TODO maybe don't fire on invalid world? also fetch world by uuid since it'll be O(1)
                change,
                cause
        );
        return fireAsync(event);
    }

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
