package org.loomdev.loom.entity.mob;

import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.mob.PatrolEntity;
import org.loomdev.api.world.Location;

import java.util.Optional;

public class PatrolEntityImpl extends HostileEntityImpl implements PatrolEntity {

    public PatrolEntityImpl(net.minecraft.entity.mob.PatrolEntity entity) {
        super(entity);
    }

    @Override
    public net.minecraft.entity.mob.@NotNull PatrolEntity getMinecraftEntity() {
        return (net.minecraft.entity.mob.PatrolEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isPatrolLeader() {
        return getMinecraftEntity().isPatrolLeader();
    }

    @Override
    public void setPatrolLeader(boolean b) {
        getMinecraftEntity().setPatrolLeader(b);
    }

    @Override
    public boolean isPatrolling() {
        return getMinecraftEntity().patrolling;
    }

    @Override
    public void setPatrolling(boolean flag) {
        getMinecraftEntity().patrolling = flag;
    }

    @Override
    public @NotNull Optional<Location> getPatrolTarget() {
        return Optional.ofNullable(getMinecraftEntity().getPatrolTarget())
                .map(bp -> new Location(null, bp.getX(), bp.getY(), bp.getZ())); // TODO world
    }

    @Override
    public void setPatrolTarget(@Nullable Location loc) {
        getMinecraftEntity().setPatrolTarget(new BlockPos(loc.getX(), loc.getY(), loc.getZ()));
    }
}
