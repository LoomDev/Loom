package org.loomdev.loom.entity.passive;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.passive.PassiveEntity;
import org.loomdev.loom.entity.mob.PathAwareEntityImpl;

public abstract class PassiveEntityImpl extends PathAwareEntityImpl implements PassiveEntity {
    public PassiveEntityImpl(net.minecraft.entity.passive.PassiveEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull  net.minecraft.entity.passive.PassiveEntity getMinecraftEntity() {
        return (net.minecraft.entity.passive.PassiveEntity) super.getMinecraftEntity();
    }
}
