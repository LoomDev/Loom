package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.MuleEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Mule;

public class MuleImpl extends AbstractDonkeyImpl implements Mule {
    public MuleImpl(MuleEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MULE;
    }

    @Override
    public MuleEntity getMinecraftEntity() {
        return (MuleEntity) super.getMinecraftEntity();
    }
}
