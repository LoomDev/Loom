package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventCause;
import org.loomdev.api.item.ItemStack;

public interface BlockEvent extends Event  {

    @NotNull
    BlockPointer getPointer();

    interface Break extends BlockEvent, Cancelable {

        @NotNull
        EventCause getCause();
    }

    /**
     * Fired when a block is added to the world as a result of an interaction. The
     * block will not be placed if this event is canceled. This event is fired when:
     * <ul>
     *     <li>A player places a block</li>
     *     <li>An Enderman places its carried block</li>
     * </ul>
     */
    interface Place extends BlockEvent, Cancelable {

        @NotNull
        EventCause getCause();

        @NotNull
        BlockState getNewState();

        void setNewState(@NotNull BlockState blockState);
    }

    interface Melt extends BlockEvent, Cancelable {
    }

    /**
     * Fired when a block that holds experience levels is broken and results in dropped
     * experience. The block will not drop experience if this event is canceled.
     */
    interface DropExperience extends BlockEvent, Cancelable {

        int getExperience();

        void setExperience(int experience);
    }

    /**
     * Fired when a block that holds resources is broken and results in dropped items.
     * The block will not drop the item if this event is canceled.
     */
    interface DropItem extends BlockEvent, Cancelable {

        @NotNull
        ItemStack getItem();

        void setItem(@NotNull ItemStack item);
    }

    /**
     * Fired when a block's state randomly changes as a result of time. The block's state
     * will not change if this event is canceled. This event is fired when:
     * <ul>
     *     <li>An unwaxed copper block oxidizes</li>
     * </ul>
     */
    interface ChangeOverTime extends BlockEvent, Cancelable {

        @NotNull
        BlockState getNewState();

        void setNewState(@NotNull BlockState blockState);
    }

    interface BurnUp extends BlockEvent, Cancelable {

        @NotNull
        BlockPointer getFirePointer();
    }

    interface DryOut extends BlockEvent, Cancelable {
    }
}
