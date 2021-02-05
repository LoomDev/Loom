package org.loomdev.loom.plugin.lifecycle;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.lifecycle.LifecycleEvent;
import org.loomdev.api.server.Server;

public class LifecycleEventImpl implements LifecycleEvent {

    @NotNull
    private final Server scopedServer;

    public LifecycleEventImpl(@NotNull Server scopedServer) {
        this.scopedServer = scopedServer;
    }

    @Override
    public Server getServer() {
        return this.scopedServer;
    }

    public static class Enable extends LifecycleEventImpl implements LifecycleEvent.Enable {

        public Enable(@NotNull Server scopedServer) {
            super(scopedServer);
        }
    }

    public static class Disable extends LifecycleEventImpl implements LifecycleEvent.Disable {

        public Disable(@NotNull Server scopedServer) {
            super(scopedServer);
        }
    }
}
