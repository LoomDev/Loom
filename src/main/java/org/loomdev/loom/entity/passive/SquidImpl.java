package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.SquidEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Squid;

public class SquidImpl extends WaterCreatureImpl implements Squid {

    public SquidImpl(SquidEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SQUID;
    }

    @Override
    public @NotNull SquidEntity getMinecraftEntity() {
        return (SquidEntity) super.getMinecraftEntity();
    }
}
