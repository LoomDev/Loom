package org.loomdev.api.entity;

public interface JumpingMount {

    boolean canJump();

    void startJumping(int i);

    void stopJumping();
}
