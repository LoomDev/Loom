package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.RavagerEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Ravager;
import org.loomdev.loom.entity.raid.RaiderImpl;

public class RavagerImpl extends RaiderImpl implements Ravager {

    public RavagerImpl(RavagerEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.RAVAGER;
    }

    @Override
    public @NotNull RavagerEntity getMinecraftEntity() {
        return (RavagerEntity) super.getMinecraftEntity();
    }
}
