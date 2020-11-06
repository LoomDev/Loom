package org.loomdev.api.entity.raid;

import org.loomdev.api.entity.mob.PatrollingMonster;

public interface Raider extends PatrollingMonster {

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
