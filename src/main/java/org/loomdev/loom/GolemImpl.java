package org.loomdev.loom;

import net.minecraft.entity.passive.GolemEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.loom.entity.mob.PathAwareEntityImpl;

public class GolemImpl extends PathAwareEntityImpl {

    public GolemImpl(GolemEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull GolemEntity getMinecraftEntity() {
        return (GolemEntity) super.getMinecraftEntity();
    }

}
