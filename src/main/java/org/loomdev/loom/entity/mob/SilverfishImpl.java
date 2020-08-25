package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.SilverfishEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Silverfish;

public class SilverfishImpl extends HostileEntityImpl implements Silverfish {

    public SilverfishImpl(SilverfishEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SILVERFISH;
    }

    @Override
    public @NotNull SilverfishEntity getMinecraftEntity() {
        return (SilverfishEntity) super.getMinecraftEntity();
    }
}
