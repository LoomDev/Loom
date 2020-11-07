package org.loomdev.api.entity.monster;

import org.loomdev.api.entity.monster.Monster;

public interface Spider extends Monster {

    boolean isClimbing();

    void setClimbing(boolean climbing);
}
