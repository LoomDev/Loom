package org.loomdev.api.entity.animal.horse;

import org.loomdev.api.entity.animal.horse.AbstractHorse;

public interface ChestedHorse extends AbstractHorse {

    boolean hasChest();

    void setHasChest(boolean flag);
}
