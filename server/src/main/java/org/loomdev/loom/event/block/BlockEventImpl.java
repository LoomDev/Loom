package org.loomdev.loom.event.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.EventCause;
import org.loomdev.api.event.block.BlockEvent;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.block.BlockPointerImpl;
import org.loomdev.loom.block.BlockStateImpl;
import org.loomdev.loom.event.EventImpl;
import org.loomdev.loom.item.ItemStackImpl;

public class BlockEventImpl extends EventImpl implements BlockEvent, Cancelable {

    private final BlockPointer pointer;
    private boolean canceled;

    public BlockEventImpl(Level level, BlockPos blockPos) {
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

    public static class BreakImpl extends BlockEventImpl implements BlockEvent.Break {

        private final EventCause cause;

        public BreakImpl(Level level, BlockPos blockPos, Entity entity) {
            super(level, blockPos);
            this.cause = new EventCause(entity);
        }

        @Override
        @NotNull
        public EventCause getCause() {
            return cause;
        }
    }

    public static class PlaceImpl extends BlockEventImpl implements BlockEvent.Place {

        private final EventCause cause;
        private net.minecraft.world.level.block.state.BlockState newState;

        public PlaceImpl(Level level, BlockPos blockPos, Entity entity, net.minecraft.world.level.block.state.BlockState newState) {
            super(level, blockPos);
            this.cause = new EventCause(entity);
            this.newState = newState;
        }

        @Override
        @NotNull
        public EventCause getCause() {
            return cause;
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

    public static class MeltImpl extends BlockEventImpl implements BlockEvent.Melt {

        public MeltImpl(Level level, BlockPos blockPos) {
            super(level, blockPos);
        }
    }

    public static class DropExperienceImpl extends BlockEventImpl implements BlockEvent.DropExperience {

        private int experience;

        public DropExperienceImpl(Level level, BlockPos blockPos, int experience) {
            super(level, blockPos);
            this.experience = experience;
        }

        @Override
        public int getExperience() {
            return experience;
        }

        @Override
        public void setExperience(int experience) {
            this.experience = experience;
        }
    }

    public static class DropItemImpl extends BlockEventImpl implements BlockEvent.DropItem {

        private net.minecraft.world.item.ItemStack item;

        public DropItemImpl(Level level, BlockPos blockPos, net.minecraft.world.item.ItemStack item) {
            super(level, blockPos);
            this.item = item;
        }

        @Override
        @NotNull
        public ItemStack getItem() {
            return ItemStackImpl.of(item);
        }

        @Override
        public void setItem(@NotNull ItemStack item) {
            this.item = ((ItemStackImpl) item).getMinecraftItemStack();
        }

        public net.minecraft.world.item.ItemStack getMinecraftItemStack() {
            return item;
        }
    }

    public static class ChangeOverTimeImpl extends BlockEventImpl implements BlockEvent.ChangeOverTime {

        private net.minecraft.world.level.block.state.BlockState newState;

        public ChangeOverTimeImpl(Level level, BlockPos blockPos, net.minecraft.world.level.block.state.BlockState newState) {
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

    public static class BurnUpImpl extends BlockEventImpl implements BlockEvent.BurnUp {

        private final BlockPointer firePointer;

        public BurnUpImpl(Level level, BlockPos blockPos, BlockPos firePos) {
            super(level, blockPos);
            this.firePointer = new BlockPointerImpl(level, firePos);
        }

        @Override
        @NotNull
        public BlockPointer getFirePointer() {
            return firePointer;
        }
    }

    public static class DryOutImpl extends BlockEventImpl implements BlockEvent.DryOut {

        public DryOutImpl(Level level, BlockPos blockPos) {
            super(level, blockPos);
        }
    }
}
