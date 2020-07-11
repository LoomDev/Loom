package org.loomdev.loom.event;

import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.loomdev.api.Loom;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.Material;
import org.loomdev.api.entity.decoration.ArmorStand;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.block.BlockBrokenEvent;
import org.loomdev.api.event.block.BlockPlacedEvent;
import org.loomdev.api.event.block.entity.ArmorStandPlacedEvent;
import org.loomdev.api.event.block.fluid.FluidLevelChangedEvent;
import org.loomdev.api.event.block.plant.CoralDiedEvent;
import org.loomdev.api.event.block.plant.LeavesDecayedEvent;
import org.loomdev.api.event.block.plant.PlantFertilizedEvent;
import org.loomdev.api.event.block.sponge.SpongeAbsorbedEvent;
import org.loomdev.api.event.player.connection.PlayerDisconnectedEvent;
import org.loomdev.api.event.player.connection.PlayerJoinedEvent;
import org.loomdev.api.math.EulerAngle;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

public final class LoomEventDispatcher {

    private LoomEventDispatcher() {
        throw new UnsupportedOperationException("LoomEventDispatcher should not be instantiated.");
    }

    private static <E extends Event> E fire(E event) {
        return Loom.getServer().getEventManager().fire(event);
    }

    public static ArmorStandPlacedEvent onArmorStandPlaced(ArmorStandEntity armorStand, BlockPos blockpos, PlayerEntity player) {
        ArmorStandPlacedEvent event = new ArmorStandPlacedEvent(
                armorStand.getLoomEntity(),
                new Location(null, blockpos.getX(), blockpos.getY(), blockpos.getZ()),
                player.getLoomEntity()
        );
        return fire(event);
    }

    public static BlockBrokenEvent onBlockBroken(WorldAccess world, BlockPos pos, PlayerEntity player) {
        BlockBrokenEvent event = new BlockBrokenEvent(new BlockImpl(world, pos), player.getLoomEntity());
        return fire(event);
    }

    public static BlockPlacedEvent onBlockPlaced(WorldAccess world, BlockPos pos, PlayerEntity player) {
        BlockPlacedEvent event = new BlockPlacedEvent(new BlockImpl(world, pos), player.getLoomEntity());
        return fire(event);
    }

    public static CoralDiedEvent onCoralDied(WorldAccess world, BlockPos pos) {
        CoralDiedEvent event = new CoralDiedEvent(new BlockImpl(world, pos));
        return fire(event);
    }

    public static LeavesDecayedEvent onLeavesDecayed(WorldAccess world, BlockPos pos) {
        LeavesDecayedEvent event = new LeavesDecayedEvent(new BlockImpl(world, pos));
        return fire(event);
    }

    public static PlantFertilizedEvent onPlantFertilized(WorldAccess world, BlockPos pos, PlayerEntity player) {
        PlantFertilizedEvent event = new PlantFertilizedEvent(new BlockImpl(world, pos), player.getLoomEntity());
        return fire(event);
    }

    public static FluidLevelChangedEvent onFluidLevelChanged(WorldAccess world, BlockPos pos) {
        FluidLevelChangedEvent event = new FluidLevelChangedEvent(new BlockImpl(world, pos));
        return fire(event);
    }

    public static SpongeAbsorbedEvent onSpongeAbsorbed(WorldAccess world, BlockPos pos) {
        SpongeAbsorbedEvent event = new SpongeAbsorbedEvent(new BlockImpl(world, pos));
        return fire(event);
    }

    public static PlayerJoinedEvent onPlayerJoined(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(new PlayerJoinedEvent(serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    public static PlayerDisconnectedEvent onPlayerDisconnected(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(new PlayerDisconnectedEvent(serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

}
