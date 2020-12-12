package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.Event;

public class PlantEvent extends Event {

    private final BlockPointer pointer;

    public PlantEvent(BlockPointer pointer) {
        this.pointer = pointer;
    }

    @NotNull
    public BlockPointer getPointer() {
        return pointer;
    }

    public static class Grow extends PlantEvent {

        private BlockState newState;

        public Grow(BlockPointer pointer, BlockState newState) {
            super(pointer);
            this.newState = newState;
        }

        @NotNull
        public BlockState getNewState() {
            return newState;
        }

        public void setNewState(@NotNull BlockState newState) {
            this.newState = newState;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public static class Decay extends PlantEvent {

        public Decay(BlockPointer pointer) {
            super(pointer);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public static class Die extends PlantEvent {

        private BlockState newState;

        public Die(BlockPointer pointer, BlockState newState) {
            super(pointer);
            this.newState = newState;
        }

        @NotNull
        public BlockState getNewState() {
            return newState;
        }

        public void setNewState(@NotNull BlockState newState) {
            this.newState = newState;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    // TODO fertilize, needs dispenser and entity causes (dispenser might be separate in DispenserEvent potentially)

    // TODO harvest - same as fertilize. not directly related to the plant since an outside entity or block does the harvesting, not the plant
}
