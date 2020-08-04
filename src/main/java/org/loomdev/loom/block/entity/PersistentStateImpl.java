package org.loomdev.loom.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import org.checkerframework.checker.nullness.Opt;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.Material;
import org.loomdev.api.block.PersistentState;
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.block.BlockStateImpl;

import java.util.Optional;

public class PersistentStateImpl<T extends BlockEntity> extends BlockStateImpl implements PersistentState {

    private final T blockEntity;

    public PersistentStateImpl(T blockEntity) {
        super(BlockImpl.at(blockEntity.getWorld(), blockEntity.getPos()));
        this.blockEntity = blockEntity;
    }

    @Override
    public @NotNull Block getBlock() {
        return null;
    }

    @Override
    public @NotNull Material getType() {
        return null;
    }

    private Optional<T> createSnapshot(T blockEntity) {
        if (blockEntity == null) {
            return Optional.empty();
        }

        CompoundTag tag = blockEntity.toTag(new CompoundTag());
       // T snapshot = (T) BlockEntity.createFromTag(blockEntity, tag);
        return Optional.empty();
    }
}
