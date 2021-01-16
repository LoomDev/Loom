package org.loomdev.api.event;

public interface Cancelable {

    boolean isCanceled();

    void setCanceled(boolean canceled);
}
