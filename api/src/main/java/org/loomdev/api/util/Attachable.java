package org.loomdev.api.util;

import org.jetbrains.annotations.NotNull;

public interface Attachable extends Directional {

    @NotNull Direction getAttachedDirection();

}
