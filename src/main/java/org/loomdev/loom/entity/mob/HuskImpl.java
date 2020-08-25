package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.HuskEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Husk;

public class HuskImpl extends ZombieImpl implements Husk {

    public HuskImpl(HuskEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.HUSK;
    }

    @Override
    public @NotNull HuskEntity getMinecraftEntity() {
        return (HuskEntity) super.getMinecraftEntity();
    }
}
