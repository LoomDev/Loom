package org.loomdev.api.entity.monster;

import org.loomdev.api.entity.monster.Monster;

public interface Blaze extends Monster {

    boolean isFireActive();

    void setFireActive(boolean active);

}
