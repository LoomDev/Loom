package org.loomdev.api.map;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface Canvas {

    @NotNull Map getMap();

    void drawImage(int x, int y, @NotNull Image image);
}
