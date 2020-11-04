package org.loomdev.loom.event;

import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
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
import org.loomdev.api.event.block.entity.EntityBlockBreakEvent;
import org.loomdev.api.event.block.entity.EntityBlockPlaceEvent;
import org.loomdev.api.event.block.fluid.FluidLevelChangeEvent;
import org.loomdev.api.event.block.note.NoteBlockPlayEvent;
import org.loomdev.api.event.block.plant.*;
import org.loomdev.api.event.block.sign.SignWriteEvent;
import org.loomdev.api.event.block.BlockAbsorbEvent;
import org.loomdev.api.event.entity.equipment.EntityEquipEvent;
import org.loomdev.api.event.entity.creeper.CreeperChargeEvent;
import org.loomdev.api.event.entity.creeper.CreeperIgniteEvent;
import org.loomdev.api.event.entity.item.ItemEntityPlaceEvent;
import org.loomdev.api.event.entity.movement.EntityBounceEvent;
import org.loomdev.api.event.entity.movement.EntitySwimEvent;
import org.loomdev.api.event.player.PlayerChatEvent;
import org.loomdev.api.event.player.connection.PlayerDisconnectEvent;
import org.loomdev.api.event.player.connection.PlayerJoinEvent;
import org.loomdev.api.event.player.movement.PlayerEnteredFlightEvent;
import org.loomdev.api.event.entity.movement.EntityMoveEvent;
import org.loomdev.api.event.player.movement.PlayerRiptideEvent;
import org.loomdev.api.event.server.ServerPingEvent;
import org.loomdev.api.event.world.WorldTimeChangeEvent;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.block.BlockStateImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.net.InetSocketAddress;
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
    public static ItemEntityPlaceEvent onItemEntityPlace(@NotNull Entity entity, @NotNull PlayerEntity player) {
        return fire(new ItemEntityPlaceEvent(entity.getLoomEntity(), (PlayerImpl) player.getLoomEntity()));
    }

    // TODO dispenser armor stand place event

    @NotNull
    public static EntityBlockBreakEvent onEntityBlockBreak(@NotNull WorldAccess world, @NotNull BlockPos blockPos, @NotNull Entity entity) {
        return fire(new EntityBlockBreakEvent(BlockImpl.at(world, blockPos), entity.getLoomEntity()));
    }

    @NotNull
    public static EntityBlockPlaceEvent onEntityBlockPlace(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Entity entity) {
        return fire(new EntityBlockPlaceEvent(BlockImpl.at(world, pos), entity.getLoomEntity()));
    }

    @NotNull
    public static BlockDropExperienceEvent onBlockDropExperience(@NotNull WorldAccess world, @NotNull BlockPos pos, int experience) {
        return fire(new BlockDropExperienceEvent(BlockImpl.at(world, pos), experience));
    }

    @NotNull
    public static BlockIgniteEvent onBlockIgnite(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull BlockPos ignitingpos, @NotNull BlockIgniteEvent.Cause cause) {
        return fire(new BlockIgniteEvent(BlockImpl.at(world, pos), BlockImpl.at(world, ignitingpos), cause));
    }

    @NotNull
    public static BlockIgniteEvent onBlockIgnite(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Entity igniter, @NotNull BlockIgniteEvent.Cause cause) { // TODO add Fireball nms implementations for this
        return fire(new BlockIgniteEvent(BlockImpl.at(world, pos), igniter.getLoomEntity(), cause));
    }

    @NotNull
    public static BlockIgniteEvent onBlockIgnite(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Explosion explosion) {
        var igniter = explosion.getCausingEntity() == null ? null : explosion.getCausingEntity().getLoomEntity();
        return fire(new BlockIgniteEvent(BlockImpl.at(world, pos), igniter, BlockIgniteEvent.Cause.EXPLOSION));
    }

    @NotNull
    public static BlockBurnEvent onBlockBurn(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull BlockPos source) {
        return fire(new BlockBurnEvent(BlockImpl.at(world, pos), BlockImpl.at(world, source)));
    }

    @NotNull
    public static BlockEvaporateEvent onBlockEvaporate(@NotNull WorldAccess world, @NotNull BlockPos pos) {
        return fire(new BlockEvaporateEvent(BlockImpl.at(world, pos)));
    }

    @NotNull
    public static BlockMeltEvent onBlockMelt(@NotNull WorldAccess world, @NotNull BlockPos pos) {
        return fire(new BlockMeltEvent(BlockImpl.at(world, pos), null)); // TODO figure out causes
    }

    @NotNull
    public static BlockExplodeEvent onBlockExplode(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull List<BlockPos> explodedBlocks) {
        Set<Block> blocks = explodedBlocks.stream()
                .map(blockpos -> BlockImpl.at(world, blockpos))
                .collect(Collectors.toSet());

        return fire(new BlockExplodeEvent(BlockImpl.at(world, pos), blocks));
    }

    @NotNull
    public static NoteBlockPlayEvent onNoteBlockPlay(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Instrument instrument, int note, float pitch) {
        NoteBlockPlayEvent event = new NoteBlockPlayEvent(
                BlockImpl.at(world, pos),
                org.loomdev.api.block.enums.Instrument.getByName(instrument.asString()),
                Note.getByUses(note),
                pitch
        );

        return fire(event);
    }

    @NotNull
    public static PlantDieEvent onPlantDie(@NotNull WorldAccess world, @NotNull BlockPos pos) {
        return fire(new PlantDieEvent(BlockImpl.at(world, pos)));
    }

    @NotNull
    public static PlantDecayEvent onPlantDecay(@NotNull WorldAccess world, @NotNull BlockPos pos) {
        return fire(new PlantDecayEvent(BlockImpl.at(world, pos)));
    }

    @NotNull
    public static PlantGrowEvent onPlantGrow(WorldAccess world, BlockPos pos) {
        /*PlantGrewEvent event = new PlantGrewEvent(BlockImpl.of(world, pos)); // TODO remove variable
        event.setCancelled(true);
        return fire(event);*/
        return null;
    }

    @NotNull
    public static PlantFertilizeEvent onPlantFertilize(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull PlayerEntity player) {
        return fire(new PlantFertilizeEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity()));
    }

    @NotNull
    public static PlantHarvestEvent onPlantHarvested(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Entity entity) {
        return fire(new PlantHarvestEvent(BlockImpl.at(world, pos), entity.getLoomEntity(), null));
    }

    @NotNull
    public static FluidLevelChangeEvent onFluidLevelChange(@NotNull WorldAccess world, @NotNull BlockPos pos) {
        return fire(new FluidLevelChangeEvent(BlockImpl.at(world, pos), BlockStateImpl.of(world.getBlockState(pos))));
    }

    @NotNull
    public static SignWriteEvent onSignWrite(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull PlayerEntity player, @NotNull List<String> text) {
        return fire(new SignWriteEvent(BlockImpl.at(world, pos), (PlayerImpl) player.getLoomEntity(), text.toArray(new String[4])));
    }

    @NotNull
    public static BlockAbsorbEvent onBlockAbsorb(@NotNull WorldAccess world, @NotNull BlockPos pos, @NotNull Set<Block> absorbedBlocks) {
        return fire(new BlockAbsorbEvent(BlockImpl.at(world, pos), absorbedBlocks));
    }

    /*public static PlayerLoggedInEvent onPlayerLoggedIn(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(new PlayerLoggedInEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }*/

    @NotNull
    public static CompletableFuture<PlayerJoinEvent> onPlayerJoin(@NotNull ServerPlayerEntity serverPlayerEntity, @NotNull Text joinMessage) {
        return fireAsync(new PlayerJoinEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    @NotNull // TODO sync?
    public static CompletableFuture<PlayerDisconnectEvent> onPlayerDisconnect(@NotNull ServerPlayerEntity serverPlayerEntity, @NotNull Text joinMessage) {
        return fireAsync(new PlayerDisconnectEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    @NotNull
    public static CompletableFuture<PlayerChatEvent> onPlayerChat(@NotNull ServerPlayerEntity serverPlayerEntity, @NotNull String message) {
        return fireAsync(new PlayerChatEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), message, Loom.getServer().getOnlinePlayers()));
    }

    @NotNull
    public static PlayerEnteredFlightEvent onPlayerEnteredFlight(@NotNull PlayerEntity playerEntity) { // TODO
        return fire(new PlayerEnteredFlightEvent((PlayerImpl) playerEntity.getLoomEntity()));
    }

    @NotNull
    public static PlayerRiptideEvent onPlayerRiptideLaunched(@NotNull PlayerEntity playerEntity, int riptideLevel) {
        return fire(new PlayerRiptideEvent((PlayerImpl) playerEntity.getLoomEntity(), riptideLevel));
    }

    @NotNull
    public static EntityMoveEvent onEntityMove(@NotNull PlayerEntity playerEntity, @NotNull Location currentLocation, @NotNull Location newLocation) {
        return fire(new EntityMoveEvent((PlayerImpl) playerEntity.getLoomEntity(), currentLocation, newLocation));
    }

    @NotNull
    public static CreeperChargeEvent onCreeperCharge(@NotNull CreeperEntity creeper) {
        return fire(new CreeperChargeEvent((Creeper) creeper.getLoomEntity()));
    }

    @NotNull
    public static CreeperChargeEvent onCreeperCharge(@NotNull CreeperEntity creeper, @NotNull LightningEntity lightning) {
        return fire(new CreeperChargeEvent((Creeper) creeper.getLoomEntity(), (Lightning) lightning.getLoomEntity()));
    }

    @NotNull
    public static CreeperIgniteEvent onCreeperIgnite(@NotNull CreeperEntity creeper) {
        return fire(new CreeperIgniteEvent((Creeper) creeper.getLoomEntity()));
    }

    @NotNull
    public static EntityBounceEvent onEntityBounce(@NotNull Entity entity, double multiplier) {
        return fire(new EntityBounceEvent(entity.getLoomEntity(), BlockImpl.at(entity.world, entity.getBlockPos()), multiplier));
    }

    @NotNull
    public static EntitySwimEvent onEntitySwim(Entity entity) {
        EntitySwimEvent event = new EntitySwimEvent(entity.getLoomEntity());
        event.setCancelled(true);
        return fire(event);
    }

    @NotNull
    public static EntityEquipEvent onEntityEquipEvent(@NotNull Entity entity, @NotNull EquipmentSlot slot, @NotNull ItemStack equipment) {
        /*EntityEquippedEquipmentEvent event = new EntityEquippedEquipmentEvent(entity.getLoomEntity());
        event.cancel(true);
        return fireAsync(event);*/
        return null;
    }

    @NotNull
    public static WorldTimeChangeEvent onWorldTimeChanged(@NotNull World world, long change, @NotNull WorldTimeChangeEvent.Cause cause) {
        return fire(new WorldTimeChangeEvent(world, change, cause));
    }

    @NotNull
    public static WorldTimeChangeEvent onWorldTimeChanged(@NotNull ServerWorld world, long change, @NotNull WorldTimeChangeEvent.Cause cause) {
        WorldTimeChangeEvent event = new WorldTimeChangeEvent(
                Loom.getServer().getWorld(world.worldProperties.getLevelName()).orElse(null), // TODO maybe don't fire on invalid world? also fetch world by uuid since it'll be O(1)
                change,
                cause
        );
        return fire(event);
    }

    @NotNull
    public static CompletableFuture<ServerPingEvent> onServerPing(ClientConnection connection, ServerMetadata metadata) {
        return fireAsync(new ServerPingEvent(
                ((InetSocketAddress) connection.getAddress()).getAddress(),
                metadata.getVersion().getProtocolVersion(),
                metadata.getVersion().getGameVersion(),
                TextTransformer.toLoom(metadata.getDescription()),
                metadata.getPlayers().getOnlinePlayerCount(),
                metadata.getPlayers().getPlayerLimit(),
                metadata.getFavicon()
        ));
    }
}
