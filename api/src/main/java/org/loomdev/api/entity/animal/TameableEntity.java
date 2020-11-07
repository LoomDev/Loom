package org.loomdev.api.entity.animal;

import org.loomdev.api.entity.animal.Animal;

import java.util.Optional;
import java.util.UUID;

public interface TameableEntity extends Animal {

    UUID getOwnerId();

    void setOwnerId(UUID id);

    boolean isTamed();

    void setTamed(boolean flag);

}
