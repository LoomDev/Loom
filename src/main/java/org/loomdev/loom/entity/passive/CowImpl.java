package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.CowEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Cow;

public class CowImpl extends AnimalEntityImpl implements Cow {

    public CowImpl(CowEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.COW;
    }

    @Override
    public CowEntity getMinecraftEntity() {
        return (CowEntity) super.getMinecraftEntity();
    }
}
