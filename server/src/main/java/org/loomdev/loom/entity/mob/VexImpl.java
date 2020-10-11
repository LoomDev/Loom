package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.VexEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Vex;

public class VexImpl extends HostileEntityImpl implements Vex {

    public VexImpl(VexEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.VEX;
    }

    @Override
    public @NotNull VexEntity getMinecraftEntity() {
        return (VexEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isCharging() {
        return getMinecraftEntity().isCharging();
    }

    @Override
    public void setCharging(boolean flag) {
        getMinecraftEntity().setCharging(flag);
    }
}
