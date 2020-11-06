package org.loomdev.api.entity.passive;

import java.util.Optional;
import java.util.UUID;

public interface TameableEntity extends Animal {

    Optional<UUID> getOwnerId();

    void setOwnerId(UUID id);

    boolean isTamed();

    void setTamed(boolean flag);

}
