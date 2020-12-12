package org.loomdev.api.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.entity.LivingEntity;

import java.util.List;
import java.util.Optional;

public interface Explosion {

    @NotNull
    BlockPointer getPointer();

    @NotNull
    Optional<LivingEntity> getSourceMob();

    @NotNull
    List<BlockPointer> getAffectedBlocks();

    @NotNull
    DestructionType getDestructionType();

    void setDestructionType(@NotNull DestructionType destructionType);

    float getRadius();

    void setRadius(float radius);

    boolean hasFire();

    void setHasFire(boolean hasFire);

    enum DestructionType {

        NONE,
        BREAK,
        DESTROY
    }
}
