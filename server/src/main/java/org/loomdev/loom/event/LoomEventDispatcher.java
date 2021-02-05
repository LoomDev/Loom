package org.loomdev.loom.event;

import org.jetbrains.annotations.NotNull;
import org.loomdev.loom.server.ServerImpl;

import java.util.concurrent.CompletableFuture;

public final class LoomEventDispatcher {

    private LoomEventDispatcher() {
    }

    public static <E extends EventImpl> E fire(@NotNull E event) {
        return ServerImpl.getInstance().getEventManager().fire(event);
    }

    public static <E extends EventImpl> CompletableFuture<E> fireAsync(@NotNull E event) {
        return ServerImpl.getInstance().getEventManager().fireAsync(event);
    }
}
