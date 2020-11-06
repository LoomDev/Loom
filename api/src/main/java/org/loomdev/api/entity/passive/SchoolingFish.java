package org.loomdev.api.entity.passive;

public interface SchoolingFish extends Fish {

    boolean hasLeader();

    void joinGroupOf(SchoolingFish schoolingFish);

    void leaveGroup();

    int getMaxGroupSize();

    void setMaxGroupSize(int count);

    void resetMaxGroupSize();
}
