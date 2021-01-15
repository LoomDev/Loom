package org.loomdev.loom.world;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.loom.block.BlockPointerImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExplosionImpl implements org.loomdev.api.world.Explosion {

    private final Explosion explosion;
    private final BlockPointer pointer;
    private final List<BlockPointer> affectedBlocks;

    public ExplosionImpl(Explosion explosion) {
        this.explosion = explosion;
        this.pointer = new BlockPointerImpl(explosion.level, new BlockPos(explosion.x, explosion.y, explosion.z));
        this.affectedBlocks = explosion.getToBlow().stream()
                .map(blockPos -> new BlockPointerImpl(explosion.level, blockPos))
                .collect(Collectors.toList());
    }

    @Override
    @NotNull
    public BlockPointer getPointer() {
        return pointer;
    }

    @Override
    @NotNull
    public Optional<LivingEntity> getSourceMob() {
        var sourceMob = explosion.getSourceMob();
        if (sourceMob == null) {
            return Optional.empty();
        }

        return Optional.of((LivingEntity) sourceMob.getLoomEntity());
    }

    @Override
    @NotNull
    public List<BlockPointer> getAffectedBlocks() {
        return affectedBlocks;
    }

    @Override
    @NotNull
    public DestructionType getDestructionType() {
        return DestructionType.values()[explosion.blockInteraction.ordinal()];
    }

    @Override
    public void setDestructionType(@NotNull DestructionType destructionType) {
        explosion.blockInteraction = Explosion.BlockInteraction.values()[destructionType.ordinal()];
    }

    @Override
    public float getRadius() {
        return explosion.radius;
    }

    @Override
    public void setRadius(float radius) {
        explosion.radius = radius;
    }

    @Override
    public boolean hasFire() {
        return explosion.fire;
    }

    @Override
    public void setHasFire(boolean hasFire) {
        explosion.fire = hasFire;
    }
}
