package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.CodEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Cod;

public class CodImpl extends SchoolingFishImpl implements Cod {

    public CodImpl(CodEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.COD;
    }

    @Override
    public CodEntity getMinecraftEntity() {
        return (CodEntity) super.getMinecraftEntity();
    }
}
