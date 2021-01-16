package org.loomdev.loom.event.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.block.PlantEvent;
import org.loomdev.loom.block.BlockStateImpl;

public class PlantEventImpl extends BlockEventImpl implements PlantEvent {

    public PlantEventImpl(Level level, BlockPos blockPos) {
        super(level, blockPos);
    }

    // TODO this is LARGELY unimplemented
    // Some plants change more than one block when growing, so something like
    // a stream of modified blocks might be a field added to this event
    // at some point in the future.
    public static class GrowImpl extends PlantEventImpl implements PlantEvent.Grow {

        private net.minecraft.world.level.block.state.BlockState newState;

        public GrowImpl(Level level, BlockPos blockPos, net.minecraft.world.level.block.state.BlockState newState) {
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

    public static class DecayImpl extends PlantEventImpl implements PlantEvent.Decay {

        public DecayImpl(Level level, BlockPos blockPos) {
            super(level, blockPos);
        }
    }

    public static class DieImpl extends PlantEventImpl implements PlantEvent.Die {

        private net.minecraft.world.level.block.state.BlockState newState;

        public DieImpl(Level level, BlockPos blockPos, net.minecraft.world.level.block.state.BlockState newState) {
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
