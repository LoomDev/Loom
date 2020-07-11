package org.loomdev.loom.entity.mob;

import org.loomdev.api.entity.mob.AmbientEntity;

public class AmbientEntityImpl extends MobEntityImpl implements AmbientEntity {

    public AmbientEntityImpl(net.minecraft.entity.mob.AmbientEntity entity) {
        super(entity);
    }

    @Override
    public net.minecraft.entity.mob.AmbientEntity getMinecraftEntity() {
        return (net.minecraft.entity.mob.AmbientEntity) super.getMinecraftEntity();
    }
}
