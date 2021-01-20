package org.loomdev.loom.event.container;

import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.container.Container;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.container.ContainerEvent;
import org.loomdev.loom.container.ContainerImpl;
import org.loomdev.loom.event.EventImpl;

public class ContainerEventImpl extends EventImpl implements ContainerEvent, Cancelable {

    private final AbstractContainerMenu container;
    private boolean canceled;

    public ContainerEventImpl(AbstractContainerMenu container) {
        this.container = container;
    }

    @Override
    @NotNull
    public Container getContainer() {
        return new ContainerImpl(container);
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public AbstractContainerMenu getMinecraftContainer() {
        return container;
    }

    public static class OpenImpl extends ContainerEventImpl implements ContainerEvent.Open {

        public OpenImpl(AbstractContainerMenu container) {
            super(container);
        }
    }
}
