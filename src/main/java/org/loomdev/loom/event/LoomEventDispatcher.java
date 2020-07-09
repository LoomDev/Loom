package org.loomdev.loom.event;

import net.minecraft.block.BlockState;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.event.block.BlockBrokenEvent;
import org.loomdev.api.event.block.BlockPlacedEvent;
import org.loomdev.api.event.block.entity.ArmorStandPlacedEvent;
import org.loomdev.api.event.block.sponge.SpongeAbsorbedEvent;
import org.loomdev.api.event.player.PlayerJoinedEvent;
import org.loomdev.api.world.Location;
import org.loomdev.loom.util.transformer.TextTransformer;

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
                () -> null, // TODO change to block
                player.getLoomEntity()
        );
        return fire(event);
    }

    public static BlockPlacedEvent onBlockPlaced(BlockState state, PlayerEntity player) {
        BlockPlacedEvent event = new BlockPlacedEvent(
                () -> null, // TODO change to block
                player.getLoomEntity()
        );
        return fire(event);
    }

    public static SpongeAbsorbedEvent onSpongeAbsorbed(BlockState state) {
        SpongeAbsorbedEvent event = new SpongeAbsorbedEvent(
                () -> null
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


}
