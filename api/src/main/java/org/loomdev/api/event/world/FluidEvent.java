package org.loomdev.api.event.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.Event;

public class FluidEvent extends Event {

    private final BlockPointer pointer;

    public FluidEvent(BlockPointer pointer) {
        this.pointer = pointer;
    }

    @NotNull
    public BlockPointer getPointer() {
        return pointer;
    }

    public static class LevelChange extends FluidEvent {

        private BlockState newState;

        public LevelChange(BlockPointer pointer, BlockState newState) {
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
}
