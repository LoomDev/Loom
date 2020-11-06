package org.loomdev.loom.entity.monster;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.mob.PatrollingMonster;
import org.loomdev.api.world.Location;

public abstract class PatrollingMonsterImpl extends MonsterImpl implements PatrollingMonster {

    public PatrollingMonsterImpl(net.minecraft.world.entity.monster.PatrollingMonster entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.PatrollingMonster getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.PatrollingMonster) super.getMinecraftEntity();
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
    @Nullable
    public Location getPatrolTarget() {
        var target = getMinecraftEntity().getPatrolTarget();
        if (target == null) return null;
        return new Location(null, target.getX(), target.getY(), target.getZ()); // TODO world
    }

    @Override
    public void setPatrolTarget(@Nullable Location location) {
        if (location == null) getMinecraftEntity().setPatrolTarget(null);
        else getMinecraftEntity().setPatrolTarget(new BlockPos(location.getX(), location.getY(), location.getZ()));
    }
}
