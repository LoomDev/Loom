package org.loomdev.api.entity;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface ComplexEntity {
    @NotNull Set<ComplexEntityPart> getParts();
}
