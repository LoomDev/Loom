package org.loomdev.api.entity.raid;

import org.loomdev.api.entity.mob.PatrolEntity;

public interface Raider extends PatrolEntity {

    boolean canJoinRaid();

    void setCanJoinRaid(boolean flag);

    // TODO
//    Optional<Raid> getRaid();
//    void setRaid(@Nullable Raid raid);

    boolean hasActiveRaid();

    int getWave();

    void setWave(int wave);

    boolean isCelebrating();

    void setCelebrating(boolean flag);

}
