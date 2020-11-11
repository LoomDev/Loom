package org.loomdev.api.event.block;

import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Cancellable;

/**
 * Fired when a block in the world drops experience.
 * The block will not drop experience if this event is cancelled.
 */
public class BlockDropExperienceEvent extends BlockEvent implements Cancellable {

    private int experience;
    private boolean cancelled;

    public BlockDropExperienceEvent(BlockPointer block, int experience) {
        super(block);
        this.experience = experience;
    }

    public int getDroppedExperience() {
        return experience;
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
