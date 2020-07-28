package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.GhastEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Ghast;

public class GhastImpl extends MobEntityImpl implements Ghast {

    public GhastImpl(GhastEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.GHAST;
    }

    @Override
    public GhastEntity getMinecraftEntity() {
        return (GhastEntity) super.getMinecraftEntity();
    }

}
