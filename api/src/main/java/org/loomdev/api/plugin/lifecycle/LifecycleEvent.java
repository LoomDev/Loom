package org.loomdev.api.plugin.lifecycle;

import org.loomdev.api.server.Server;

public interface LifecycleEvent {

    Server getServer();

    interface Enable extends LifecycleEvent {
    }

    interface Disable extends LifecycleEvent {
    }
}
