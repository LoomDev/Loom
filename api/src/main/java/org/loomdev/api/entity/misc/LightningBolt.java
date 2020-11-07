package org.loomdev.api.entity.misc;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.player.Player;

public interface LightningBolt extends Entity {

    boolean isVisualOnly();

    void setVisualOnly(boolean visualOnly);

    @Nullable
    Player getCause();

    void setCause(@Nullable Player player);
}
