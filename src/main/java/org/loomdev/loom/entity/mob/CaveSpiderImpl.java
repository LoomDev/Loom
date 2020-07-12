package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.CaveSpiderEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.CaveSpider;

public class CaveSpiderImpl extends SpiderImpl implements CaveSpider {

    public CaveSpiderImpl(CaveSpiderEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.CAVE_SPIDER;
    }

    @Override
    public CaveSpiderEntity getMinecraftEntity() {
        return (CaveSpiderEntity) super.getMinecraftEntity();
    }
}
