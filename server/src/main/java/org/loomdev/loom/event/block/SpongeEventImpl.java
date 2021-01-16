package org.loomdev.loom.event.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.block.SpongeEvent;

import java.util.List;

public class SpongeEventImpl extends BlockEventImpl implements SpongeEvent {

    public SpongeEventImpl(Level level, BlockPos blockPos) {
        super(level, blockPos);
    }

    public static class AbsorbImpl extends SpongeEventImpl implements SpongeEvent.Absorb {

        private final List<BlockPointer> absorbedBlocks;

        public AbsorbImpl(Level level, BlockPos blockPos, List<BlockPointer> absorbedBlocks) {
            super(level, blockPos);
            this.absorbedBlocks = absorbedBlocks;
        }

        @Override
        @NotNull
        public List<BlockPointer> getAbsorbedBlocks() {
            return absorbedBlocks;
        }
    }
}
