package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;

public interface PlantEvent extends BlockEvent {

    interface Grow extends PlantEvent {

        @NotNull
        BlockState getNewState();

        void setNewState(@NotNull BlockState blockState);
    }

    interface Decay extends PlantEvent {
    }

    interface Die extends PlantEvent {

        @NotNull
        BlockState getNewState();

        void setNewState(@NotNull BlockState blockState);
    }

    // TODO fertilize, needs dispenser and entity causes (dispenser might be separate in DispenserEvent potentially)

    // TODO harvest - same as fertilize. not directly related to the plant since an outside entity or block does the harvesting, not the plant
}
