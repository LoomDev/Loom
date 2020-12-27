package org.loomdev.api.entity.monster.illager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.monster.Monster;
import org.loomdev.api.world.Location;

import java.util.Optional;

public interface PatrollingMonster extends Monster {

    boolean isPatrolLeader();

    void setPatrolLeader(boolean flag);

    boolean isPatrolling();

    void setPatrolling(boolean flag);

    @NotNull
    Optional<Location> getPatrolTarget();

    void setPatrolTarget(@Nullable Location location);
}
