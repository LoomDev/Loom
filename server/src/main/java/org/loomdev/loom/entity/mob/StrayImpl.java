package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.StrayEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Stray;

public class StrayImpl extends AbstractSkeletonImpl implements Stray {

    public StrayImpl(StrayEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.STRAY;
    }

    @Override
    public @NotNull StrayEntity getMinecraftEntity() {
        return (StrayEntity) super.getMinecraftEntity();
    }
}
