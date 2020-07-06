package org.loomdev.loom.entity.mob;

import org.loomdev.api.entity.mob.MobEntity;
import org.loomdev.loom.entity.LivingEntityImpl;

public class MobEntityImpl extends LivingEntityImpl implements MobEntity {
    public MobEntityImpl(net.minecraft.entity.mob.MobEntity entity) {
        super(entity);
    }
}
