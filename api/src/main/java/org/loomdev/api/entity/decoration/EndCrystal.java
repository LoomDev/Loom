package org.loomdev.api.entity.decoration;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.world.Location;

import java.util.Optional;

public interface EndCrystal extends Entity {

    @NotNull Optional<Location> getBeamTarget();

    void setBeamTarget(@Nullable Location location);

    boolean isBottomShown();

    void setBottomShown(boolean flag);

}
