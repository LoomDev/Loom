package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.animal.TameableEntity;
import org.loomdev.api.util.DyeColor;

/**
 * Represents a Wolf entity.
 */
public interface Wolf extends TameableEntity {

    boolean isBegging();

    void setBegging(boolean flag);

    @NotNull DyeColor getCollarColor();

    void setCollarColor(@NotNull DyeColor color);

}
