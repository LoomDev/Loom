package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.PhantomEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Phantom;

public class PhantomImpl extends MobEntityImpl implements Phantom {
    public PhantomImpl(PhantomEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PHANTOM;
    }

    @Override
    public PhantomEntity getMinecraftEntity() {
        return (PhantomEntity) super.getMinecraftEntity();
    }

    @Override
    public int getSize() {
        return getMinecraftEntity().getPhantomSize();
    }

    @Override
    public void setSize(int size) {
        getMinecraftEntity().setPhantomSize(size);
    }
}
