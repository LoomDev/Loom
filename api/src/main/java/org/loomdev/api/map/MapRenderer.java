package org.loomdev.api.map;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;

public interface MapRenderer {

    void render(@NotNull Map map, @NotNull Canvas canvas, @NotNull Player player);
}
