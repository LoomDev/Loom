package org.loomdev.loom.entity.monster.illager;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.monster.illager.PatrollingMonster;
import org.loomdev.api.world.Location;
import org.loomdev.loom.entity.monster.MonsterImpl;

import java.util.Optional;

public abstract class AbstractPatrollingMonsterImpl extends MonsterImpl implements PatrollingMonster {

    public AbstractPatrollingMonsterImpl(net.minecraft.world.entity.monster.PatrollingMonster entity) {
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
    @NotNull
    public Optional<Location> getPatrolTarget() {
        return Optional.ofNullable(getMinecraftEntity().getPatrolTarget())
                .map(target -> new Location(getMinecraftEntity().level.getLoomWorld(), target.getX(), target.getY(), target.getZ()));
    }

    @Override
    public void setPatrolTarget(@Nullable Location location) {
        if (location == null) {
            getMinecraftEntity().setPatrolTarget(null);
            return;
        }

        getMinecraftEntity().setPatrolTarget(new BlockPos(location.getX(), location.getY(), location.getZ()));
    }
}
