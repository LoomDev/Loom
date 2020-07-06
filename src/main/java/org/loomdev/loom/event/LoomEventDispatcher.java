package org.loomdev.loom.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.event.block.BlockBrokenEvent;

public final class LoomEventDispatcher {

    private static EventManager eventManager;

    static void setEventManager(EventManager eventManager) {
        LoomEventDispatcher.eventManager = eventManager;
    }

    private static <E extends Event> E fire(E event) {
        return eventManager.fire(event);
    }

    public static BlockBrokenEvent fireBlockBrokenEvent(Block mcBlock, ServerPlayerEntity serverPlayerEntity, BlockState mcBlockState, boolean canceled) {
        BlockBrokenEvent event = new BlockBrokenEvent(
                () -> null, // TODO change to block
                serverPlayerEntity.getLoomEntity(),
                null
        );
        event.setCancelled(canceled);
        return fire(event);
    }

    private LoomEventDispatcher() {
        throw new UnsupportedOperationException("LoomEventDispatcher should not be instantiated");
    }
}
