package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.BatEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Bat;
import org.loomdev.loom.entity.mob.AmbientEntityImpl;

public class BatImpl extends AmbientEntityImpl implements Bat {

    public BatImpl(BatEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BAT;
    }

    @Override
    public BatEntity getMinecraftEntity() {
        return (BatEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isRoosting() {
        return getMinecraftEntity().isRoosting();
    }

    @Override
    public void setRoosting(boolean flag) {
        getMinecraftEntity().setRoosting(flag);
    }
}
