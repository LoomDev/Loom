package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Event;

import java.util.List;
import java.util.Set;

public class SpongeEvent extends Event {

    private final BlockPointer pointer;

    public SpongeEvent(BlockPointer pointer) {
        this.pointer = pointer;
    }

    @NotNull
    public BlockPointer getPointer() {
        return pointer;
    }

    public static class Absorb extends SpongeEvent {

        private final List<BlockPointer> absorbedBlocks;

        public Absorb(BlockPointer pointer, List<BlockPointer> absorbedBlocks) {
            super(pointer);
            this.absorbedBlocks = absorbedBlocks;
        }

        @NotNull
        public List<BlockPointer> getAbsorbedBlocks() {
            return absorbedBlocks;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public static class Dry extends SpongeEvent {

        public Dry(BlockPointer pointer) {
            super(pointer);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
}
