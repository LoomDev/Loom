package org.loomdev.api.entity.animal.golem;

import org.loomdev.api.entity.monster.Angerable;

public interface IronGolem extends Golem, Angerable {

    boolean isPlayerCreated();

    void setPlayerCreated(boolean flag);
}
