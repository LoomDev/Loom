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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.enums.Note;
import org.loomdev.api.entity.misc.LightningBolt;
import org.loomdev.api.entity.monster.Creeper;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventCause;
import org.loomdev.api.event.block.*;
import org.loomdev.api.event.block.BlockPlaceEvent;
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
    public static ItemEntityPlaceEvent onItemEntityPlace(Entity entity, Player player) {
        return fire(new ItemEntityPlaceEvent(entity.getLoomEntity(), (PlayerImpl) player.getLoomEntity()));
    }

    // TODO dispenser armor stand place event

    @NotNull
    public static BlockBreakEvent onBlockBreak(Entity entity, Level level, BlockPos blockPos) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new BlockBreakEvent(new EventCause(entity.getLoomEntity()), block));
    }

    @NotNull
    public static BlockPlaceEvent onBlockPlace(Entity entity, Level level, BlockPos blockPos, BlockState newState) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var state = new BlockStateImpl(newState);
        return fire(new BlockPlaceEvent(new EventCause(entity.getLoomEntity()), block, state));
    }

    @NotNull
    public static BlockDropExperienceEvent onBlockDropExperience(Level level, BlockPos blockPos, int experience) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new BlockDropExperienceEvent(block, experience));
    }

    @NotNull
    public static BlockIgniteEvent onBlockIgnite(Level level, BlockPos blockPos, BlockPos sourcePos, BlockIgniteEvent.Cause cause) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var source = level.getLoomWorld().getBlock(sourcePos.getX(), sourcePos.getY(), sourcePos.getZ());
        return fire(new BlockIgniteEvent(block, source, cause));
    }

    @NotNull
    public static BlockIgniteEvent onBlockIgnite(Level level, BlockPos blockPos, Entity source, BlockIgniteEvent.Cause cause) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new BlockIgniteEvent(block, source.getLoomEntity(), cause));
    }

    @NotNull
    public static BlockIgniteEvent onBlockIgnite(Level level, BlockPos blockPos, Explosion explosion) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var source = explosion.getSourceMob() == null ? null : explosion.getSourceMob().getLoomEntity();
        return fire(new BlockIgniteEvent(block, source, BlockIgniteEvent.Cause.EXPLOSION));
    }

    @NotNull
    public static BlockBurnEvent onBlockBurn(Level level, BlockPos blockPos, BlockPos sourcePos) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var source = level.getLoomWorld().getBlock(sourcePos.getX(), sourcePos.getY(), sourcePos.getZ());
        return fire(new BlockBurnEvent(block, source));
    }

    @NotNull
    public static BlockEvaporateEvent onBlockEvaporate(Level level, BlockPos blockPos) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new BlockEvaporateEvent(block));
    }

    @NotNull
    public static BlockMeltEvent onBlockMelt(Level level, BlockPos blockPos) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new BlockMeltEvent(block, null)); // TODO figure out causes
    }

    @NotNull
    public static BlockChangeEvent onBlockChange(Level level, BlockPos blockPos, BlockState newState) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var state = new BlockStateImpl(newState);
        return fire(new BlockChangeEvent(block, state));
    }

    @NotNull
    public static BlockExplodeEvent onBlockExplode(Level level, BlockPos blockPos, List<BlockPos> explodedBlocks) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        Set<BlockPointer> blocks = explodedBlocks.stream() // TODO streams are ass for performance, de-streamify this
                .map(pos -> level.getLoomWorld().getBlock(pos.getX(), pos.getY(), pos.getZ()))
                .collect(Collectors.toSet());
        return fire(new BlockExplodeEvent(block, blocks));
    }

    @NotNull
    public static NoteBlockPlayEvent onNoteBlockPlay(Level level, BlockPos blockPos, NoteBlockInstrument mcInstrument, int note, float pitch) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var instrument = org.loomdev.api.block.enums.Instrument.getByName(mcInstrument.getSerializedName());
        return fire(new NoteBlockPlayEvent(block, instrument, Note.getByUses(note), pitch));
    }

    @NotNull
    public static PlantDieEvent onPlantDie(Level level, BlockPos blockPos, BlockState deadState) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var newState = new BlockStateImpl(deadState);
        return fire(new PlantDieEvent(block, newState));
    }

    @NotNull
    public static PlantDecayEvent onPlantDecay(Level level, BlockPos blockPos) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new PlantDecayEvent(block));
    }

    @NotNull
    public static PlantGrowEvent onPlantGrow(Level level, BlockPos blockPos, BlockState newState) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var state = new BlockStateImpl(newState);
        return fire(new PlantGrowEvent(block, state));
    }

    @NotNull
    public static PlantFertilizeEvent onPlantFertilize(Level level, BlockPos blockPos, Entity entity) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new PlantFertilizeEvent(block, entity.getLoomEntity()));
    }

    @NotNull
    public static PlantHarvestEvent onPlantHarvested(Level level, BlockPos blockPos, Entity entity) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new PlantHarvestEvent(block, entity.getLoomEntity(), null)); // TODO add a cause depending on entity type (fox vs player)
    }

    @NotNull
    public static FluidLevelChangeEvent onFluidLevelChange(Level level, BlockPos blockPos, BlockState newState) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var state = new BlockStateImpl(newState);
        return fire(new FluidLevelChangeEvent(block, state));
    }

    @NotNull
    public static SignWriteEvent onSignWrite(Level level, BlockPos blockPos, Player player, List<String> text) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new SignWriteEvent(block, (PlayerImpl) player.getLoomEntity(), text));
    }

    @NotNull
    public static BlockAbsorbEvent onBlockAbsorb(Level level, BlockPos blockPos, Set<BlockPointer> absorbedBlocks) {
        var block = level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new BlockAbsorbEvent(block, absorbedBlocks));
    }

    /*public static PlayerLoggedInEvent onPlayerLoggedIn(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(new PlayerLoggedInEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }*/

    @NotNull
    public static PlayerJoinEvent onPlayerJoin(ServerPlayer serverPlayerEntity, Component message) {
        return fire(new PlayerJoinEvent((PlayerImpl) serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(message)));
    }

    @NotNull
    public static PlayerDisconnectEvent onPlayerDisconnect(ServerPlayer player, Component message) {
        return fire(new PlayerDisconnectEvent((PlayerImpl) player.getLoomEntity(), TextTransformer.toLoom(message)));
    }

    @NotNull
    public static CompletableFuture<PlayerChatEvent> onPlayerChat(ServerPlayer player, Component text) {
        return fireAsync(new PlayerChatEvent((PlayerImpl) player.getLoomEntity(), TextTransformer.toLoom(text), Loom.getServer().getOnlinePlayers()));
    }

    @NotNull
    public static PlayerEnteredFlightEvent onPlayerEnteredFlight(Player playerEntity) { // TODO
        return fire(new PlayerEnteredFlightEvent((PlayerImpl) playerEntity.getLoomEntity()));
    }

    @NotNull
    public static PlayerRiptideEvent onPlayerRiptideLaunched(Player playerEntity, int riptideLevel) {
        return fire(new PlayerRiptideEvent((PlayerImpl) playerEntity.getLoomEntity(), riptideLevel));
    }

    @NotNull
    public static EntityMoveEvent onEntityMove(Player player, Location currentLocation, Location newLocation) {
        return fire(new EntityMoveEvent((PlayerImpl) player.getLoomEntity(), currentLocation, newLocation));
    }

    @NotNull
    public static CreeperChargeEvent onCreeperCharge(net.minecraft.world.entity.monster.Creeper creeper) {
        return fire(new CreeperChargeEvent((Creeper) creeper.getLoomEntity()));
    }

    @NotNull
    public static CreeperChargeEvent onCreeperCharge(net.minecraft.world.entity.monster.Creeper creeper, net.minecraft.world.entity.LightningBolt lightning) {
        return fire(new CreeperChargeEvent((Creeper) creeper.getLoomEntity(), (LightningBolt) lightning.getLoomEntity()));
    }

    @NotNull
    public static CreeperIgniteEvent onCreeperIgnite(net.minecraft.world.entity.monster.Creeper creeper) {
        return fire(new CreeperIgniteEvent((Creeper) creeper.getLoomEntity()));
    }

    @NotNull
    public static EntityBounceEvent onEntityBounce(Entity entity, double multiplier) {
        var blockPos = entity.blockPosition();
        var block = entity.level.getLoomWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return fire(new EntityBounceEvent(entity.getLoomEntity(), block, multiplier));
    }

    @NotNull
    public static EntitySwimEvent onEntitySwim(Entity entity) {
        EntitySwimEvent event = new EntitySwimEvent(entity.getLoomEntity());
        event.setCancelled(true);
        return fire(event);
    }

    @NotNull
    public static EntityEquipEvent onEntityEquipEvent(Entity entity, EquipmentSlot slot, ItemStack equipment) {
        /*EntityEquippedEquipmentEvent event = new EntityEquippedEquipmentEvent(entity.getLoomEntity());
        event.cancel(true);
        return fireAsync(event);*/
        return null;
    }

    @NotNull
    public static WorldTimeChangeEvent onWorldTimeChanged(World world, long change, WorldTimeChangeEvent.Cause cause) {
        return fire(new WorldTimeChangeEvent(world, change, cause));
    }

    @NotNull
    public static WorldTimeChangeEvent onWorldTimeChanged(Level level, long change, WorldTimeChangeEvent.Cause cause) {
       /* WorldTimeChangeEvent event = new WorldTimeChangeEvent(
                Loom.getServer().getWorld(world.getLevelData().getLevelName()).orElse(null), // TODO maybe don't fire on invalid world? also fetch world by uuid since it'll be O(1)
                change,
                cause
        );
        return fire(event);*/
        return null;
    }

    @NotNull
    public static CompletableFuture<ServerPingEvent> onServerPing(Connection connection, ServerStatus status) {
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
