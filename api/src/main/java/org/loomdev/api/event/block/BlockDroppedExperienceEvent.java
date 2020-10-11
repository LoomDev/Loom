package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.event.Cancellable;

/**
 * Fired when a block in the world drops experience.
 * The block will not drop experience if this event is cancelled.
 */
public class BlockDroppedExperienceEvent extends BlockEvent implements Cancellable {

    private int experience;
    private boolean cancelled;

    public BlockDroppedExperienceEvent(@NotNull Block block, int experience) {
        super(block);
        this.experience = experience;
    }

    public int getDroppedExperience() {
        return this.experience;
    }

    public void setDroppedExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
