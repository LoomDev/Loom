package org.loomdev.loom.block.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.loomdev.api.block.PersistentState;
import org.loomdev.loom.block.BlockImpl;
import org.loomdev.loom.block.BlockStateImpl;

import java.util.Optional;

public class PersistentStateImpl<T extends BlockEntity> extends BlockStateImpl implements PersistentState {

    private final T blockEntity;

    public PersistentStateImpl(T blockEntity) {
        super(BlockImpl.at(blockEntity.getLevel(), blockEntity.getBlockPos()).getMinecraftBlockState());
        this.blockEntity = blockEntity;
    }

    private Optional<T> createSnapshot(T blockEntity) {
        if (blockEntity == null) {
            return Optional.empty();
        }

        CompoundTag tag = blockEntity.save(new CompoundTag());
       // T snapshot = (T) BlockEntity.createFromTag(blockEntity, tag);
        return Optional.empty();
    }
}
