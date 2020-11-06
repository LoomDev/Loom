package org.loomdev.api.entity.passive;

import org.loomdev.api.entity.Golem;
import org.loomdev.api.entity.mob.Angerable;

public interface IronGolem extends Golem, Angerable {

    boolean isPlayerCreated();

    void setPlayerCreated(boolean flag);
}
