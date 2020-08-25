package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.SalmonEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Salmon;

public class SalmonImpl extends SchoolingFishImpl implements Salmon {

    public SalmonImpl(SalmonEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SALMON;
    }

    @Override
    public @NotNull SalmonEntity getMinecraftEntity() {
        return (SalmonEntity) super.getMinecraftEntity();
    }
}
