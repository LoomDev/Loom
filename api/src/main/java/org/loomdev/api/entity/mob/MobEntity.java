package org.loomdev.api.entity.mob;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.player.Player;

public interface MobEntity extends LivingEntity {

    boolean hasAi();

    void setAi(boolean flag);

    boolean canPickupItems();

    void setCanPickItems(boolean flag);

    boolean isPersistent();

    void setPersistent(boolean remove);

    boolean canBeLeashedBy(@NotNull Player player);
}
