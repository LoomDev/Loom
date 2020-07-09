package org.loomdev.loom.event;

import net.kyori.adventure.text.Component;
import net.minecraft.block.BlockState;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.Material;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.event.block.BlockBrokenEvent;
import org.loomdev.api.event.block.BlockPlacedEvent;
import org.loomdev.api.event.block.entity.ArmorStandPlacedEvent;
import org.loomdev.api.event.block.fluid.FluidLevelChangedEvent;
import org.loomdev.api.event.block.plant.CoralDiedEvent;
import org.loomdev.api.event.block.plant.LeavesDecayedEvent;
import org.loomdev.api.event.block.sponge.SpongeAbsorbedEvent;
import org.loomdev.api.event.player.PlayerJoinedEvent;
import org.loomdev.api.math.BoundingBox;
import org.loomdev.api.math.Vector3d;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class LoomEventDispatcher {
    private LoomEventDispatcher() { throw new UnsupportedOperationException("LoomEventDispatcher should not be instantiated."); }

    private static EventManager eventManager;
    static void setEventManager(EventManager eventManager) {
        LoomEventDispatcher.eventManager = eventManager;
    }

    private static <E extends Event> E fire(E event) {
        return eventManager.fire(event);
    }

    public static ArmorStandPlacedEvent onArmorStandPlaced(ArmorStandEntity armorStand, BlockPos blockpos, PlayerEntity player) {
        ArmorStandPlacedEvent event = new ArmorStandPlacedEvent(
                armorStand.getLoomEntity(),
                new Location(null, blockpos.getX(), blockpos.getY(), blockpos.getZ()),
                player.getLoomEntity()
        );
        return fire(event);
    }

    public static BlockBrokenEvent onBlockBroken(BlockState state, ServerPlayerEntity player) {
        BlockBrokenEvent event = new BlockBrokenEvent(
                DUMMY_BLOCK,
                player.getLoomEntity()
        );
        return fire(event);
    }

    public static CoralDiedEvent onCoralDied(BlockState state) {
        CoralDiedEvent event = new CoralDiedEvent(
                DUMMY_BLOCK
        );
        event.setCancelled(true);
        return fire(event);
    }

    public static LeavesDecayedEvent onLeavesDecayed(BlockState state) {
        LeavesDecayedEvent event = new LeavesDecayedEvent(
                DUMMY_BLOCK
        );
        event.setCancelled(true);
        return fire(event);
    }

    public static FluidLevelChangedEvent onFluidLevelChanged(BlockState state) {
        FluidLevelChangedEvent event = new FluidLevelChangedEvent(
                DUMMY_BLOCK // TODO change to block
        );
        event.setCancelled(true);
        return fire(event);
    }

    public static SpongeAbsorbedEvent onSpongeAbsorbed(BlockState state) {
        SpongeAbsorbedEvent event = new SpongeAbsorbedEvent(
                DUMMY_BLOCK
        );
        return fire(event);
    }

    public static PlayerJoinedEvent firePlayerJoinedEvent(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(
                new PlayerJoinedEvent(
                        serverPlayerEntity.getLoomEntity(),
                        TextTransformer.toLoom(joinMessage)
                )
        );
    }

    private static Block DUMMY_BLOCK = new Block() {

        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 0;
        }

        @Override
        public int getZ() {
            return 0;
        }

        @Override
        public Chunk getChunk() {
            return null;
        }

        @Override
        public World getWorld() {
            return null;
        }

        @Override
        public Material getMaterial() {
            return null;
        }

        @Override
        public Material setMaterial() {
            return null;
        }
    };
}
