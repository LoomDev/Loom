package org.loomdev.api.entity.mob;

public interface Enderman extends HostileEntity, Angerable {

    boolean isProvoked();

    void setProvoked(boolean flag);

    // TODO v1 carried block
}
