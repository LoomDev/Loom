package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.HuskEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Husk;

public class HuskImpl extends ZombieImpl implements Husk {

    public HuskImpl(HuskEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.HUSK;
    }

    @Override
    public HuskEntity getMinecraftEntity() {
        return (HuskEntity) super.getMinecraftEntity();
    }
}
