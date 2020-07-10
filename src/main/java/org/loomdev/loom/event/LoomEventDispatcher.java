package org.loomdev.loom.event;

import net.minecraft.block.BlockState;;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.loomdev.api.Loom;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.Material;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.block.BlockBrokenEvent;
import org.loomdev.api.event.block.entity.ArmorStandPlacedEvent;
import org.loomdev.api.event.block.fluid.FluidLevelChangedEvent;
import org.loomdev.api.event.block.plant.CoralDiedEvent;
import org.loomdev.api.event.block.plant.LeavesDecayedEvent;
import org.loomdev.api.event.block.sponge.SpongeAbsorbedEvent;
import org.loomdev.api.event.player.PlayerJoinedEvent;
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

    public static BlockBrokenEvent onBlockBroken(WorldAccess world, BlockPos pos, PlayerEntity playerEntity) {
        Player player = playerEntity.getLoomEntity();
        BlockBrokenEvent event = new BlockBrokenEvent(new BlockImpl(world, pos), player);
        return fire(event);
    }

    public static BlockBrokenEvent onBlockPlaced(WorldAccess world, BlockPos pos, PlayerEntity player) {
        BlockBrokenEvent event = new BlockBrokenEvent(new BlockImpl(world, pos), player.getLoomEntity());
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

    public static FluidLevelChangedEvent onFluidLevelChanged(WorldAccess world, BlockPos pos) {
        FluidLevelChangedEvent event = new FluidLevelChangedEvent(new BlockImpl(world, pos));
        return fire(event);
    }

    public static SpongeAbsorbedEvent onSpongeAbsorbed(WorldAccess world, BlockPos pos) {
        SpongeAbsorbedEvent event = new SpongeAbsorbedEvent(new BlockImpl(world, pos));
        return fire(event);
    }

    public static PlayerJoinedEvent firePlayerJoinedEvent(ServerPlayerEntity serverPlayerEntity, Text joinMessage) {
        return fire(new PlayerJoinedEvent(serverPlayerEntity.getLoomEntity(), TextTransformer.toLoom(joinMessage)));
    }

    private static final Block DUMMY_BLOCK = new Block() {

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
