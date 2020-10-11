package org.loomdev.api.entity.mob;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.world.Location;

import java.util.Optional;

public interface PatrolEntity extends HostileEntity {

    boolean isPatrolLeader();

    void setPatrolLeader(boolean flag);

    boolean isPatrolling();

    void setPatrolling(boolean flag);

    @NotNull Optional<Location> getPatrolTarget();

    void setPatrolTarget(@Nullable Location location);

}
