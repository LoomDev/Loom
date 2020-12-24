package org.loomdev.api.util;

import org.jetbrains.annotations.NotNull;

/**
 * Represents something that has direction.
 */
public interface Directional {

    @NotNull Direction getFacingDirection();

    void setFacingDirection(@NotNull Direction direction);

}
