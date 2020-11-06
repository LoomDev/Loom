package org.loomdev.loom.event;

import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.enums.Note;
import org.loomdev.api.entity.misc.LightningBolt;
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
    public static ItemEntityPlaceEvent onItemEntityPlace(@NotNull Entity entity, @NotNull Player player) {
        return fire(new ItemEntityPlaceEvent(entity.getLoomEntity(), (PlayerImpl) player.getLoomEntity()));
    }

    // TODO dispenser armor stand place event

    @NotNull
    public static EntityBlockBreakEvent onEntityBlockBreak(@NotNull Level world, @NotNull BlockPos blockPos, @NotNull Entity entity) {
        return fire(new EntityBlockBreakEvent(BlockImpl.at(world, blockPos), entity.getLoomEntity()));
    }

    @NotNull
    public static EntityBlockPlaceEvent onEntityBlockPlace(@NotNull Level world, @NotNull BlockPos pos, @NotNull Entity entity) {
        return fire(new EntityBlockPlaceEvent(BlockImpl.at(world, pos), entity.getLoomEntity()));
    }

    @NotNull
    public static BlockDropExperienceEvent onBlockDropExperience(@NotNull Level world, @NotNull BlockPos pos, int experience) {
        return fire(new BlockDropExperienceEvent(BlockImpl.at(world, pos), experience));
    }

    @NotNull
    public static BlockIgniteEvent onBlockIgnite(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockPos ignitingpos, @NotNull BlockIgniteEvent.Cause cause) {
        return fire(new BlockIgniteEvent(BlockImpl.at(world, pos), BlockImpl.at(world, ignitingpos), cause));
    }

    @NotNull
    public static BlockIgniteEvent onBlockIgnite(@NotNull Level world, @NotNull BlockPos pos, @NotNull Entity igniter, @NotNull BlockIgniteEvent.Cause cause) { // TODO add Fireball nms implementations for this
        return fire(new BlockIgniteEvent(BlockImpl.at(world, pos), igniter.getLoomEntity(), cause));
    }

    @NotNull
    public static BlockIgniteEvent onBlockIgnite(@NotNull Level world, @NotNull BlockPos pos, @NotNull Explosion explosion) {
        var igniter = explosion.getSourceMob() == null ? null : explosion.getSourceMob().getLoomEntity();
        return fire(new BlockIgniteEvent(BlockImpl.at(world, pos), igniter, BlockIgniteEvent.Cause.EXPLOSION));
    }

    @NotNull
    public static BlockBurnEvent onBlockBurn(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockPos source) {
        return fire(new BlockBurnEvent(BlockImpl.at(world, pos), BlockImpl.at(world, source)));
    }

    @NotNull
    public static BlockEvaporateEvent onBlockEvaporate(@NotNull Level world, @NotNull BlockPos pos) {
        return fire(new BlockEvaporateEvent(BlockImpl.at(world, pos)));
    }

    @NotNull
    public static BlockMeltEvent onBlockMelt(@NotNull Level world, @NotNull BlockPos pos) {
        return fire(new BlockMeltEvent(BlockImpl.at(world, pos), null)); // TODO figure out causes
    }

    @NotNull
    public static BlockExplodeEvent onBlockExplode(@NotNull Level world, @NotNull BlockPos pos, @NotNull List<BlockPos> explodedBlocks) {
        Set<Block> blocks = explodedBlocks.stream() // TODO streams are ass for performance, de-streamify this
                .map(blockpos -> BlockImpl.at(world, blockpos))
                .collect(Collectors.toSet());

        return fire(new BlockExplodeEvent(BlockImpl.at(world, pos), blocks));
    }

    @NotNull
    public static NoteBlockPlayEvent onNoteBlockPlay(@NotNull Level world, @NotNull BlockPos pos, @NotNull NoteBlockInstrument instrument, int note, float pitch) {
        NoteBlockPlayEvent event = new NoteBlockPlayEvent(
                BlockImpl.at(world, pos),
                org.loomdev.api.block.enums.Instrument.getByName(instrument.getSerializedName()),
                Note.getByUses(note),
                pitch
        );

        return fire(event);
    }

    @NotNull
    public static PlantDieEvent onPlantDie(@NotNull Level level, @NotNull BlockPos pos) {
        return fire(new PlantDieEvent(BlockImpl.at(level, pos)));
    }

    @NotNull
    public static PlantDecayEvent onPlantDecay(@NotNull Level level, @NotNull BlockPos pos) {
        return fire(new PlantDecayEvent(BlockImpl.at(level, pos)));
    }

    @NotNull
    public static PlantGrowEvent onPlantGrow(Level level, BlockPos pos) {
        /*PlantGrewEvent event = new PlantGrewEvent(BlockImpl.of(world, pos)); // TODO remove variable
        event.setCancelled(true);
        return fire(event);*/
        return null;
    }

    @NotNull
    public static PlantFertilizeEvent onPlantFertilize(@NotNull Level world, @NotNull BlockPos pos, @NotNull Entity entity) {
        return fire(new PlantFertilizeEvent(BlockImpl.at(world, pos), entity.getLoomEntity()));
    }

    @NotNull
    public static PlantHarvestEvent onPlantHarvested(@NotNull Level world, @NotNull BlockPos pos, @NotNull Entity entity) {
        return fire(new PlantHarvestEvent(BlockImpl.at(world, pos), entity.getLoomEntity(), null));
    }

    @NotNull
    public static FluidLevelChangeEvent onFluidLevelChange(@NotNull Level world, @NotNull BlockPos pos) {
        return fire(new FluidLevelChangeEvent(BlockImpl.at(world, pos), BlockStateImpl.of(world.getBlockState(pos))));
    }

    @NotNull
    public static SignWriteEvent onSignWrite(@NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull List<String> text) {
        return fire(new SignWriteEvent(BlockImpl.at(level, pos), (PlayerImpl) player.getLoomEntity(), text.toArray(new String[4])));
    }

    @NotNull
    public static BlockAbsorbEvent onBlockAbsorb(@NotNull Level world, @NotNull BlockPos pos, @NotNull Set<Block> absorbedBlocks) {
        return fire(new BlockAbsorbEvent(BlockImpl.at(world, pos), absorbedBlocks));
    }

    /*public static PlayerLoggedInEvent onPlayerLoggedIn(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(new PlayerLoggedInEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }*/

    @NotNull
    public static PlayerJoinEvent onPlayerJoin(@NotNull ServerPlayer serverPlayerEntity, @NotNull Component joinMessage) {
        return fire(new PlayerJoinEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    @NotNull
    public static PlayerDisconnectEvent onPlayerDisconnect(@NotNull ServerPlayer serverPlayerEntity, @NotNull Component joinMessage) {
        return fire(new PlayerDisconnectEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    @NotNull
    public static CompletableFuture<PlayerChatEvent> onPlayerChat(@NotNull ServerPlayer serverPlayerEntity, @NotNull Component component) {
        return fireAsync(new PlayerChatEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(component), Loom.getServer().getOnlinePlayers()));
    }

    @NotNull
    public static PlayerEnteredFlightEvent onPlayerEnteredFlight(@NotNull Player playerEntity) { // TODO
        return fire(new PlayerEnteredFlightEvent((PlayerImpl) playerEntity.getLoomEntity()));
    }

    @NotNull
    public static PlayerRiptideEvent onPlayerRiptideLaunched(@NotNull Player playerEntity, int riptideLevel) {
        return fire(new PlayerRiptideEvent((PlayerImpl) playerEntity.getLoomEntity(), riptideLevel));
    }

    @NotNull
    public static EntityMoveEvent onEntityMove(@NotNull Player playerEntity, @NotNull Location currentLocation, @NotNull Location newLocation) {
        return fire(new EntityMoveEvent((PlayerImpl) playerEntity.getLoomEntity(), currentLocation, newLocation));
    }

    @NotNull
    public static CreeperChargeEvent onCreeperCharge(@NotNull net.minecraft.world.entity.monster.Creeper creeper) {
        return fire(new CreeperChargeEvent((Creeper) creeper.getLoomEntity()));
    }

    @NotNull
    public static CreeperChargeEvent onCreeperCharge(@NotNull net.minecraft.world.entity.monster.Creeper creeper, @NotNull net.minecraft.world.entity.LightningBolt lightning) {
        return fire(new CreeperChargeEvent((Creeper) creeper.getLoomEntity(), (LightningBolt) lightning.getLoomEntity()));
    }

    @NotNull
    public static CreeperIgniteEvent onCreeperIgnite(@NotNull net.minecraft.world.entity.monster.Creeper creeper) {
        return fire(new CreeperIgniteEvent((Creeper) creeper.getLoomEntity()));
    }

    @NotNull
    public static EntityBounceEvent onEntityBounce(@NotNull Entity entity, double multiplier) {
        return fire(new EntityBounceEvent(entity.getLoomEntity(), BlockImpl.at(entity.level, entity.blockPosition()), multiplier));
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
    public static WorldTimeChangeEvent onWorldTimeChanged(@NotNull Level world, long change, @NotNull WorldTimeChangeEvent.Cause cause) {
       /* WorldTimeChangeEvent event = new WorldTimeChangeEvent(
                Loom.getServer().getWorld(world.getLevelData().getLevelName()).orElse(null), // TODO maybe don't fire on invalid world? also fetch world by uuid since it'll be O(1)
                change,
                cause
        );
        return fire(event);*/
        return null;
    }

    @NotNull
    public static CompletableFuture<ServerPingEvent> onServerPing(@NotNull Connection connection, @NotNull ServerStatus status) {
        return fireAsync(new ServerPingEvent(
                ((InetSocketAddress) connection.getRemoteAddress()).getAddress(),
                status.getVersion().getProtocol(),
                status.getVersion().getName(),
                TextTransformer.toLoom(status.getDescription()),
                status.getPlayers().getNumPlayers(),
                status.getPlayers().getMaxPlayers(),
                status.getFavicon()
        ));
    }
}
