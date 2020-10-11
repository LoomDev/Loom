package org.loomdev.api.particle.data;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.Color;

public class DustData implements ParticleData {

    private final @NotNull Color color;
    private final int size;

    public DustData(@NotNull Color color, int size) {
        this.color = color;
        this.size = size;
    }

    public @NotNull Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }
}