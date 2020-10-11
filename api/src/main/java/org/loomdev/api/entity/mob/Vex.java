package org.loomdev.api.entity.mob;

/**
 * Represents a Vex entity.
 */
public interface Vex extends HostileEntity {

    /**
     * Get the charging state of the Vex.
     *
     * @return The charging state.
     */
    boolean isCharging();

    /**
     * Set the charging state of the Vex.
     * @param flag The new charging state.
     */
    void setCharging(boolean flag);
}
