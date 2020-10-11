package org.loomdev.api.util;

import org.jetbrains.annotations.NotNull;

public interface Directional {

    @NotNull Direction getFacingDirection();

    void setFacingDirection(@NotNull Direction direction);

}
