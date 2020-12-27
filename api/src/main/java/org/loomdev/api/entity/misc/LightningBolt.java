package org.loomdev.api.entity.misc;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.player.Player;

import java.util.Optional;

public interface LightningBolt extends Entity {

    boolean isVisualOnly();

    void setVisualOnly(boolean visualOnly);

    @NotNull
    Optional<Player> getCause();

    void setCause(@Nullable Player player);
}
