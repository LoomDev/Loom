package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.OcelotEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Ocelot;

public class OcelotImpl extends AnimalEntityImpl implements Ocelot {

    public OcelotImpl(OcelotEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.OCELOT;
    }

    @Override
    public @NotNull OcelotEntity getMinecraftEntity() {
        return (OcelotEntity) super.getMinecraftEntity();
    }
}
