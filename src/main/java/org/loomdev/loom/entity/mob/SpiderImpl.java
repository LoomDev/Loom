package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.SpiderEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Spider;

public class SpiderImpl extends HostileEntityImpl implements Spider {

    public SpiderImpl(SpiderEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.SPIDER;
    }

    @Override
    public SpiderEntity getMinecraftEntity() {
        return (SpiderEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isClimbingWall() {
        return getMinecraftEntity().isClimbingWall();
    }

    @Override
    public void setClimbingWall(boolean b) {
        getMinecraftEntity().setClimbingWall(b);
    }
}
