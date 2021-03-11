package org.loomdev.api.item.property.data;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.Color;

public interface LeatherDyeData extends ItemPropertyData<LeatherDyeData> {

    @NotNull
    Color getColor();

    void setColor(@NotNull Color color);

    void clearColor();

    boolean hasCustomColor();
}
