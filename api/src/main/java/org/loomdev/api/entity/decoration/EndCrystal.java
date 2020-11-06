package org.loomdev.api.entity.decoration;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.world.Location;

public interface EndCrystal extends Entity {

    @Nullable
    Location getBeamTarget();

    void setBeamTarget(@Nullable Location location);

    boolean isBottomShown();

    void setBottomShown(boolean flag);
}
