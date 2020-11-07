package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.Phantom;

public class PhantomImpl extends MobEntityImpl implements Phantom {

    public PhantomImpl(net.minecraft.world.entity.monster.Phantom entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Phantom> getType() {
        return EntityType.PHANTOM;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Phantom getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Phantom) super.getMinecraftEntity();
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
