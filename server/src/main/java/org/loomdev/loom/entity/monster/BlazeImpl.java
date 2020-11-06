package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Blaze;

public class BlazeImpl extends MonsterImpl implements Blaze {

    public BlazeImpl(net.minecraft.world.entity.monster.Blaze entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Blaze> getType() {
        return EntityType.BLAZE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Blaze getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Blaze) super.getMinecraftEntity();
    }

    @Override
    public boolean isFireActive() {
        return getMinecraftEntity().isCharged();
    }

    @Override
    public void setFireActive(boolean flag) {
        getMinecraftEntity().setCharged(flag);
    }
}
