package org.loomdev.api.event.fluid;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.Event;

public interface FluidEvent extends Event {

    @NotNull
    BlockPointer getPointer();

    interface LevelChange extends FluidEvent, Cancelable {

        @NotNull
        BlockState getNewState();

        void setNewState(@NotNull BlockState blockState);
    }
}
