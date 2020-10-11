package org.loomdev.api.entity;

import org.jetbrains.annotations.NotNull;

public interface ComplexEntityPart {
    @NotNull ComplexEntity getParent();
}
