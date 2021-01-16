package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Cancelable;

import java.util.List;

public interface SpongeEvent extends BlockEvent {

    interface Absorb extends SpongeEvent, Cancelable {

        @NotNull
        List<BlockPointer> getAbsorbedBlocks();
    }
}
