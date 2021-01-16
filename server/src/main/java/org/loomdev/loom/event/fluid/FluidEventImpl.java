package org.loomdev.loom.event.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.fluid.FluidEvent;
import org.loomdev.loom.block.BlockPointerImpl;
import org.loomdev.loom.block.BlockStateImpl;
import org.loomdev.loom.event.EventImpl;

public class FluidEventImpl extends EventImpl implements FluidEvent, Cancelable {

    private final BlockPointer pointer;
    private boolean canceled;

    public FluidEventImpl(Level level, BlockPos blockPos) {
        this.pointer = new BlockPointerImpl(level, blockPos);
    }

    @Override
    @NotNull
    public BlockPointer getPointer() {
        return pointer;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public static class LevelChange extends FluidEventImpl implements FluidEvent.LevelChange {

        private net.minecraft.world.level.block.state.BlockState newState;

        public LevelChange(Level level, BlockPos blockPos, net.minecraft.world.level.block.state.BlockState newState) {
            super(level, blockPos);
            this.newState = newState;
        }

        @Override
        @NotNull
        public BlockState getNewState() {
            return new BlockStateImpl(newState);
        }

        @Override
        public void setNewState(@NotNull BlockState blockState) {
            this.newState = ((BlockStateImpl) blockState).getMinecraftState();
        }

        public net.minecraft.world.level.block.state.BlockState getMinecraftState() {
            return newState;
        }
    }
}
