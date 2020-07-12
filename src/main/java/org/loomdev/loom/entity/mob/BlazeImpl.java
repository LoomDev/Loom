package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.BlazeEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Blaze;

public class BlazeImpl extends HostileEntityImpl implements Blaze {

    public BlazeImpl(BlazeEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.BLAZE;
    }

    @Override
    public BlazeEntity getMinecraftEntity() {
        return (BlazeEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isFireActive() {
        return getMinecraftEntity().isFireActive();
    }

    @Override
    public void setFireActive(boolean flag) {
        getMinecraftEntity().setFireActive(flag);
    }

}
