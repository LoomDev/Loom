package org.loomdev.loom.entity;

import org.loomdev.api.entity.mob.MobEntity;
import org.loomdev.loom.entity.monster.MobEntityImpl;

public abstract class PathfinderMob extends MobEntityImpl implements MobEntity {

    public PathfinderMob(net.minecraft.world.entity.PathfinderMob entity) {
        super(entity);
    }
}
