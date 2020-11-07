package org.loomdev.api.entity.monster;

public interface Enderman extends Monster, Angerable {

    boolean isProvoked(); // TODO rename?

    void setProvoked(boolean flag);

    // TODO v1 carried block
}
