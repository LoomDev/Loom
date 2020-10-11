package org.loomdev.api.entity.vehicle;

/**
 * Represents a minecart with a command block inside.
 */
public interface CommandBlockMinecart extends Minecart {

    /**
     * Get the command that this minecart will run when activated.
     *
     * @return The command that this minecart will run when activated.
     */
    String getCommand();

    /**
     * Set the command that this minecart will run when activated.
     *
     * @param command the new command that this minecart will run when activated.
     */
    void setCommand(String command);

}
