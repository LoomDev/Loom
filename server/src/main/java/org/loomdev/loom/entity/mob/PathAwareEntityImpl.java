package org.loomdev.loom.entity.mob;

import org.loomdev.api.entity.mob.MobEntity;

public abstract class PathAwareEntityImpl extends MobEntityImpl implements MobEntity {
    public PathAwareEntityImpl(net.minecraft.entity.mob.PathAwareEntity entity) {
        super(entity);
    }
}
