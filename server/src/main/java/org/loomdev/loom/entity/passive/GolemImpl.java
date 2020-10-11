package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.GolemEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.loom.entity.mob.PathAwareEntityImpl;

public abstract class GolemImpl extends PathAwareEntityImpl {

    public GolemImpl(GolemEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull GolemEntity getMinecraftEntity() {
        return (GolemEntity) super.getMinecraftEntity();
    }

}
