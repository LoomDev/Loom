package org.loomdev.loom.item.property.data;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.property.data.LeatherDyeData;
import org.loomdev.api.util.Color;

public class LeatherDyeDataImpl implements LeatherDyeData {

    private Color color;

    public LeatherDyeDataImpl() {
        this(null);
    }

    public LeatherDyeDataImpl(Color color) {
        this.color = color;
    }

    @Override
    @NotNull
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(@NotNull Color color) {
        this.color = color;
    }

    @Override
    public void clearColor() {
        this.color = null;
    }

    @Override
    public boolean hasCustomColor() {
        return this.color != null;
    }
}
