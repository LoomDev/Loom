package org.loomdev.api.entity.boss.enderdragon;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.ComplexEntityPart;
import org.loomdev.api.entity.Entity;

public interface EnderDragonPart extends Entity, ComplexEntityPart {
    @Override
    @NotNull EnderDragon getParent();
}
