package org.loomdev.api.event.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventCause;
import org.loomdev.api.item.ItemStack;

public class BlockEvent extends Event {

    private final BlockPointer pointer;

    public BlockEvent(BlockPointer pointer) {
        this.pointer = pointer;
    }

    @NotNull
    public BlockPointer getPointer() {
        return pointer;
    }

    public static class Break extends BlockEvent {

        private final EventCause cause;

        public Break(EventCause cause, BlockPointer pointer) {
            super(pointer);
            this.cause = cause;
        }

        @NotNull
        public EventCause getCause() {
            return cause;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    /**
     * Fired when a block is added to the world as a result of an interaction. The
     * block will not be placed if this event is canceled. This event is fired when:
     * <ul>
     *     <li>A player places a block</li>
     *     <li>An Enderman places its carried block</li>
     * </ul>
     */
    public static class Place extends BlockEvent {

        private final EventCause cause;
        private BlockState newState;

        public Place(EventCause cause, BlockPointer pointer, BlockState newState) {
            super(pointer);
            this.cause = cause;
            this.newState = newState;
        }

        @NotNull
        public BlockState getNewState() {
            return newState;
        }

        public void setNewState(@NotNull BlockState newState) {
            this.newState = newState;
        }

        @NotNull
        public EventCause getCause() {
            return cause;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    // TODO the block doesn't really do the interaction - might want to move this to an entity specific event
    // Then again, that logic makes Place invalid in this class since the block isn't the one doing the placing,
    // neither breaking for Break. However, for now it seems most logical to me to stick those in here. However,
    // interaction I'm still not sure about.
    public static class Interact extends BlockEvent {

        public Interact(BlockPointer pointer) {
            super(pointer);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public static class Melt extends BlockEvent {
        public Melt(BlockPointer pointer) {
            super(pointer);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    /**
     * Fired when a block that holds experience levels is broken and results in dropped
     * experience. The block will not drop experience if this event is canceled.
     */
    public static class DropExperience extends BlockEvent {

        private int experience;

        public DropExperience(BlockPointer pointer, int experience) {
            super(pointer);
            this.experience = experience;
        }

        public int getExperience() {
            return experience;
        }

        public void setExperience(int experience) {
            this.experience = experience;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    /**
     * Fired when a block that holds resources is broken and results in dropped items.
     * The block will not drop the item if this event is canceled.
     */
    public static class DropItem extends BlockEvent {

        private ItemStack item;

        public DropItem(BlockPointer pointer, ItemStack item) {
            super(pointer);
            this.item = item;
        }

        @NotNull
        public ItemStack getItem() {
            return item;
        }

        public void setItem(@NotNull ItemStack item) {
            this.item = item;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    /**
     * Fired when a block's state randomly changes as a result of time. The block's state
     * will not change if this event is canceled. This event is fired when:
     * <ul>
     *     <li>An unwaxed copper block oxidizes</li>
     * </ul>
     */
    public static class ChangeOverTime extends BlockEvent {

        private BlockState newState;

        public ChangeOverTime(BlockPointer pointer, BlockState newState) {
            super(pointer);
            this.newState = newState;
        }

        @NotNull
        public BlockState getNewState() {
            return newState;
        }

        public void setNewState(@NotNull BlockState blockState) {
            this.newState = blockState;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public static class BurnUp extends BlockEvent {

        private final BlockPointer fire;

        public BurnUp(BlockPointer pointer, BlockPointer fire) {
            super(pointer);
            this.fire = fire;
        }

        @NotNull
        public BlockPointer getFire() {
            return fire;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
}
