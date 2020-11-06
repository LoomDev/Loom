package org.loomdev.api.entity.mob;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.world.Location;

public interface PatrollingMonster extends Monster {

    boolean isPatrolLeader();

    void setPatrolLeader(boolean flag);

    boolean isPatrolling();

    void setPatrolling(boolean flag);

    @Nullable
    Location getPatrolTarget();

    void setPatrolTarget(@Nullable Location location);
}
